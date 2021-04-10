package web.badminton.service;



import java.nio.charset.Charset;

import java.security.NoSuchAlgorithmException;


import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import web.badminton.dto.UserDTO;
import web.badminton.enums.OrderDetailsEnum;
import web.badminton.enums.ProductEnum;
import web.badminton.enums.ShippesEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.modal.RequestCheckTransactionStatusMOMO;
import web.badminton.modal.RequestMOMO;
import web.badminton.modal.ResponseCheckTransactionStatusMOMO;
import web.badminton.modal.ResponseMOMO;
import web.badminton.repository.CartRepository;
import web.badminton.repository.NumberOfClothesSizeRepository;
import web.badminton.repository.NumberOfShoesSizeRepository;
import web.badminton.repository.OrderDetailRepository;
import web.badminton.repository.OrderRepository;
import web.badminton.repository.ProductRepository;
import web.badminton.repository.ShippersRepository;
import web.badminton.repository.UserRepository;

import web.badminton.vo.Cart;
import web.badminton.vo.Shipper;
import web.badminton.vo.User;

import java.security.InvalidKeyException;

@Service
public class PayService {
	@Autowired
	ShippersRepository shippersRepository;
	@Autowired
	UserRepository	userRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	OrderRepository statusOrder;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	NumberOfClothesSizeRepository numberOfClothesSizeRepository;
	@Autowired
	NumberOfShoesSizeRepository numberOfShoesSizeRepository;
	
	// get list shippers
	public List<Shipper> getListShippers() {
		return shippersRepository.getListShippers(ShippesEnum.NOBAN.get());
	}
	
	// check change the user payment
	public int checkChangeUserPayment(UserDTO userDTO, User user) {
		if(!userDTO.getFullName().equals(user.getFullName()) || !userDTO.getEmail().equals(user.getEmail()) || !userDTO.getAddress().equals(user.getAddress()) || !userDTO.getPhoneNumber().equals(user.getPhoneNumber())) {
			return userRepository.UpdateUserPayment(userDTO,user.getIdUser()); // return = 1 have changed 
		}
		return 0; // not change
	}
	
	// payment method
	public int  paymentOnDelivery( User user, float totalMoney , int idCompanyShipper) {
		
		//payment on delivery
		
			List<Cart> cart = cartRepository.listCart(user.getIdUser());
			int idOrder = 0;
			 idOrder = statusOrder.insertOrder(user.getIdUser(), idCompanyShipper, cart.size(), totalMoney, 0,0);
			// insert fail
			
			//insert success
			if(idOrder != 0 ) {
				for (Cart c : cart) {
					try {
						int a = orderDetailRepository.insertOrderDetail(idOrder, c.getIdProduct(), c.getNumberOfSize(),c.getNumberOfSize()*c.getPrice(),c.getSize(),OrderDetailsEnum.ORDER_SUCCESS.get());
						int numberProduct = productRepository.getNumberProduct(c.getIdProduct(), 1);
						if(numberProduct == c.getNumberOfSize()){
							productReduction(c.getIdProduct(),c.getSize(),c.getNumberOfSize());
							productRepository.changeStatusProduct(c.getIdProduct(),StatusProductEnum.SOLDOUT.get());
						}else{
							productReduction(c.getIdProduct(),c.getSize(),c.getNumberOfSize());
						}
					} catch (Exception e) {
						return 0;
					}
				}	
				//delete all product in the cart
				try {
					cartRepository.deleteAllProductInTheCart(user.getIdUser());
				} catch (Exception e) {
					return 0;
				}
			}
			
			return 1; // payment success	
	}
	
	public ResponseMOMO  paymentWithMOMO(User user, int totalMoney , int idCompanyShipper,int idOrder ) {
		final String url = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
		if(idOrder != 0) {
			 RequestMOMO request = new RequestMOMO();
			 request.setPartnerCode("MOMOHDRK20200430");
			 request.setAccessKey("68tVdaHzCcvtfzwH");
			 request.setRequestId(Integer.toString(idOrder));
			 request.setOrderId(Integer.toString(idOrder));
			 request.setAmount(Integer.toString(totalMoney));
			 request.setOrderInfo("Thanh toan hoa don badminton");
			 request.setReturnUrl("http://localhost:8080/profile-ordermanagement");
			 request.setNotifyUrl("https://test-payment.momo.vn/gw_payment/transactionProcessor");
			 request.setRequestType("captureMoMoWallet");
			 String signature = "partnerCode=MOMOHDRK20200430&accessKey=68tVdaHzCcvtfzwH&requestId="+idOrder+"&amount="+totalMoney+"&orderId="+idOrder+"&orderInfo=Thanh toan hoa don badminton&returnUrl=http://localhost:8080/profile-ordermanagement&notifyUrl=https://test-payment.momo.vn/gw_payment/transactionProcessor&extraData=";
			 String secretKey = "8AWejATXBF96XL3CqeICtqiiKwheEUAv";
			 String key = hmac_sha256(secretKey,signature);
			 request.setSignature(key);
			 request.setExtraData("");
			 
			 RestTemplate restTemplate = new RestTemplate();
			 return  restTemplate.postForObject( url, request, ResponseMOMO.class);			
		}
	   
		
	    return null;
	}
	
	public ResponseCheckTransactionStatusMOMO checkTransactionStatusMOMO(int idOrder ) {
		final String url = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
		RequestCheckTransactionStatusMOMO request = new RequestCheckTransactionStatusMOMO();
		request.setPartnerCode("MOMOHDRK20200430");
		request.setAccessKey("68tVdaHzCcvtfzwH");
		request.setRequestId(Integer.toString(idOrder));
		request.setOrderId(Integer.toString(idOrder));
		request.setRequestType("transactionStatus");
		String signature = "partnerCode=MOMOHDRK20200430&accessKey=68tVdaHzCcvtfzwH&requestId="+idOrder+"&orderId="+idOrder+"&requestType=transactionStatus";
		String secretKey ="8AWejATXBF96XL3CqeICtqiiKwheEUAv";
		request.setSignature(hmac_sha256(secretKey, signature));
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(url, request, ResponseCheckTransactionStatusMOMO.class);
	}
	
	public int  getIdOrderAndInsertOrder(User user, float totalMoney , int idCompanyShipper) {
		List<Cart> cart = cartRepository.listCart(user.getIdUser());
		 return  statusOrder.insertOrder(user.getIdUser(), idCompanyShipper, cart.size(), totalMoney, 1,4);
	}
	
	
	
	public String hmac_sha256(String key , String data) {
		final Charset asciiCs = Charset.forName("US-ASCII"); 
	    Mac sha256_HMAC;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");		
			final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(asciiCs.encode(key).array(), "HmacSHA256"); 
			try {
				sha256_HMAC.init(secret_key);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} 
		    final byte[] mac_data = sha256_HMAC.doFinal(asciiCs.encode(data).array()); 
		    String result = ""; 
		    for (final byte element : mac_data) 
		    { 
		     result += Integer.toString((element & 0xff) + 0x100, 16).substring(1); 
		    } 
		  return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
	    return null;
	}
	

	private int productReduction(int idProduct,int idTypeSize, int numberSize) {
		int idTypeProduct = productRepository.getType(idProduct, 1);
		if(idTypeProduct == ProductEnum.GIAY.get()) {
			return numberOfShoesSizeRepository.updateNumberSize(idProduct, idTypeSize, numberSize);
		}
		if(idTypeProduct == ProductEnum.AO.get() || idTypeProduct == ProductEnum.QUAN.get()) {
			return numberOfClothesSizeRepository.updateNumberSize(idProduct, idTypeSize, numberSize);
		}
			return productRepository.updateProduct(idProduct, numberSize);
		
	}
}
