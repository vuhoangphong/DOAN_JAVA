package web.badminton.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import web.badminton.constant.Constant;
import web.badminton.enums.OrderDetailsEnum;
import web.badminton.enums.ProductEnum;
import web.badminton.modal.CheckCart;
import web.badminton.modal.Order;
import web.badminton.modal.OrderDetailOJ;
import web.badminton.modal.RequestCheckRefundStatus;
import web.badminton.modal.RequestRefund;
import web.badminton.modal.ResponseCheckRefundStatus;
import web.badminton.modal.ResponseRefund;
import web.badminton.repository.CartRepository;
import web.badminton.repository.NumberOfClothesSizeRepository;
import web.badminton.repository.NumberOfShoesSizeRepository;
import web.badminton.repository.OrderDetailRepository;
import web.badminton.repository.OrderRepository;
import web.badminton.repository.ProductRepository;
import web.badminton.repository.ShippersRepository;
import web.badminton.vo.Cart;
import web.badminton.vo.OrderVO;
import web.badminton.vo.Product;
import web.badminton.vo.Shipper;
import web.badminton.vo.User;

@Service
public class ProfileService {
	@Autowired
	CartRepository cartRepository;
	@Autowired 
	HttpSession session;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	CartSevice cartService;
	@Autowired 
	PayService payService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ShippersRepository shippersRepository;
	@Autowired
	NumberOfClothesSizeRepository	numberOfClothesSizeRepository;
	@Autowired
	NumberOfShoesSizeRepository	numberOfShoesSizeRepository;
	@Autowired
	ProductRepository productRepository;
	
	
	
	public int checkTransaction(String idOrder, String amount , String transId) {
		User user = (User) session.getAttribute(Constant.USER);
		List<CheckCart> checkCart = cartService.checkCart();
		if(checkCart.isEmpty() == true) { // in stock
			try {
				 insertOrderDetail(Integer.parseInt(idOrder), user.getIdUser());
				 orderRepository.updateStatus(Integer.parseInt(idOrder), 2);
				 orderRepository.updateShippingStatus(Integer.parseInt(idOrder), 0);
				 cartRepository.deleteAllProductInTheCart(user.getIdUser());
			} catch (Exception e) {
				return 0;
			}
			
			return 1;
		}else // out stock
		{
			ResponseRefund responseRefund = refund(idOrder, amount, transId);	
			if(responseRefund.getErrorCode()==0) {
				orderRepository.updateStatus(Integer.parseInt(idOrder), 3); 
				orderRepository.updateShippingStatus(Integer.parseInt(idOrder), 3);
				return 2;
			}else {
				orderRepository.updateStatus(Integer.parseInt(idOrder), 4); 
				orderRepository.updateShippingStatus(Integer.parseInt(idOrder), 3);
				return 3;
			}
		}
	}
	
	private int  insertOrderDetail(int idOrder , int idUser ) {
		List<Cart> cart = cartRepository.listCart(idUser);
		for (Cart c : cart) {
			try {
				orderDetailRepository.insertOrderDetail(idOrder, c.getIdProduct(), c.getNumberOfSize(),c.getNumberOfSize()*c.getPrice(),c.getSize(),OrderDetailsEnum.ORDER_SUCCESS.get());
				productReduction( c.getIdProduct(),c.getSize(),c.getNumberOfSize());
			} catch (Exception e) {
				return 0;
			}
		}	
		//delete all product in the cart
		try {
			cartRepository.deleteAllProductInTheCart(idUser);
		} catch (Exception e) {
			return 0;
		}
		return 1; // payment success	
	}
	
	private ResponseRefund refund(String orderId, String amount , String transId ) {
		final String url = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
		RequestRefund requestRefund = new RequestRefund();
		requestRefund.setPartnerCode("MOMOHDRK20200430");
		requestRefund.setAccessKey("68tVdaHzCcvtfzwH");
		requestRefund.setRequestId("RF"+orderId);
		requestRefund.setAmount(amount);
		requestRefund.setOrderId("RF"+orderId);
		requestRefund.setTransId(transId);
		requestRefund.setRequestType("refundMoMoWallet");
		String signature = "partnerCode=MOMOHDRK20200430&accessKey=68tVdaHzCcvtfzwH&requestId=RF"+orderId+"&amount="+amount+"&orderId=RF"+orderId+"&transId="+transId+"&requestType=refundMoMoWallet";
		String key = "8AWejATXBF96XL3CqeICtqiiKwheEUAv";
		requestRefund.setSignature(payService.hmac_sha256(key, signature));
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(url, requestRefund, ResponseRefund.class);
	}
	
	private ResponseCheckRefundStatus checkRefund(String orderId) {
		final String url = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
		RequestCheckRefundStatus requestCheckRefundStatus = new RequestCheckRefundStatus();
		requestCheckRefundStatus.setPartnerCode("MOMOHDRK20200430");
		requestCheckRefundStatus.setAccessKey("68tVdaHzCcvtfzwH");
		requestCheckRefundStatus.setRequestId("CR"+orderId);
		requestCheckRefundStatus.setOrderId("RF"+orderId);
		requestCheckRefundStatus.setRequestType("refundMoMoWallet");
		String signature = "partnerCode=MOMOHDRK20200430&accessKey=68tVdaHzCcvtfzwH&requestId=CR"+orderId+"&orderId=RF"+orderId+"&requestType=refundMoMoWallet";
		String key = "8AWejATXBF96XL3CqeICtqiiKwheEUAv";
		requestCheckRefundStatus.setSignature(payService.hmac_sha256(key, signature));
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(url, requestCheckRefundStatus, ResponseCheckRefundStatus.class);
	}
	
	public List<Order> getListOrders() {
		User user = (User) session.getAttribute(Constant.USER);
		List<Order> listOrder = new ArrayList<Order>();
		List<OrderVO> listOrderVO = orderRepository.getListOrder(user.getIdUser());
		for (OrderVO o : listOrderVO) {
			Order order = new Order();
			order.setDateBuy(o.getDateBuy());
			order.setDeliveryDate(o.getDeliveryDate());
			order.setIdCompanyShipper(shippersRepository.getNameCompanyShipper(o.getIdCompanyShipper()));
			order.setIdOrder(o.getIdOrder());
			order.setShippingStatus(shippingStatus(o.getShippingStatus()));
			order.setStatusOrder(statusOrder(o.getStatusOrder()));
			order.setTotalBought(o.getTotalBought());
			order.setTotalMoney(Math.round(o.getTotalMoney()));
			listOrder.add(order);
		}
		return listOrder;
	}
	private String shippingStatus(int shippingStatus) {
		
		if(shippingStatus == 0) {
			return "Chờ xác nhận";
		}
		if(shippingStatus == 1) {
			return "Đang đóng gói";
		}	
		if(shippingStatus == 2) {
			return "Đang giao";
		}
		if(shippingStatus == 3) {
			return "Đã giao";
		}	
			return "Đã hủy";

			// if(shippingStatus == 0) {
			// 	return "Đã giao";
			// }
			// if(shippingStatus == 1) {
			// 	return "Đang giao";
			// }	
			// 	return "Đã hủy";
		
	}
	
	private String statusOrder(int statusOrder) {
		if(statusOrder == 0) {
			return "Chưa thanh toán";
		}
		if(statusOrder == 1) {
			return " chưa thanh toán";
		}
		if(statusOrder == 2) {
			return "Đã thanh toán";
		}
		if(statusOrder == 3) {
			return "Đã hoàn tiền";
		}
			return "Chưa hoàn tiền";	
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

	// get all products bought 

	public List<Product> getProductsBought(int idUser){
		return orderDetailRepository.getProductsBought(idUser);
	}

	public List<OrderDetailOJ> ListDetail(String id) {
		List<OrderDetailOJ> list = orderRepository.listOrderDetails(id);
		for (OrderDetailOJ orderDetailOJ : list) {
			if(orderDetailOJ.getStatus().equals("0")){
				orderDetailOJ.setStatus("Chờ xác nhận");
			}
			if(orderDetailOJ.getStatus().equals("1")){
				orderDetailOJ.setStatus("Đang đóng gói");
			}
			if(orderDetailOJ.getStatus().equals("2")){
				orderDetailOJ.setStatus("Đang giao");
			}
			if(orderDetailOJ.getStatus().equals("3")){
				orderDetailOJ.setStatus("Đã giao");
			}
			if(orderDetailOJ.getStatus().equals("4")){
				orderDetailOJ.setStatus("Đã hủy");
			}

		}
		return list;
	}
}
