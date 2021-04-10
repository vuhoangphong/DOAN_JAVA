package web.badminton.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.constant.Constant;
import web.badminton.enums.ProductEnum;
import web.badminton.enums.SizeEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.modal.CheckCart;
import web.badminton.repository.CartRepository;
import web.badminton.repository.ClothesSizeRepository;
import web.badminton.repository.NumberOfClothesSizeRepository;
import web.badminton.repository.NumberOfShoesSizeRepository;
import web.badminton.repository.ProductRepository;

import web.badminton.repository.ShoesSizeRepository;
import web.badminton.vo.Cart;
import web.badminton.vo.NumberOfShoesSize;
import web.badminton.vo.User;

@Service
public class CartSevice {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ShoesSizeRepository shoesSizeRepository;
	@Autowired
	ClothesSizeRepository clothesSizeRepository;
	@Autowired
	HttpSession session;
	@Autowired
	NumberOfClothesSizeRepository numberOfClothesSizeRepository;
	@Autowired
	NumberOfShoesSizeRepository numberOfShoesSizeRepository;
	// insert cart

	public  int insertCart(int idUser, int idProduct, int numberOfSize, int size) {
		// //long start = System.nanoTime(); 
		// final String[] nameProduct = new String[1];
		// Thread t1 = new Thread() {
		// 	public void run() {
		// 		nameProduct[0] = productRepository.getNameProduct(idProduct, StatusProductEnum.SHOW.get());
		// 	}
		// };
		// t1.start();
		// final float[] price = new float[1];
		// Thread t2 = new Thread() {
		// 	public void run() {
		// 		price[0] = productRepository.getPriceProduct(idProduct, StatusProductEnum.SHOW.get());
		// 	}
		// };
		// t2.start();

		// final int[] idTypeProduct = new int[1];
		// Thread t3 = new Thread() {
		// 	public void run() {
		// 		idTypeProduct[0] = productRepository.getType(idProduct, StatusProductEnum.SHOW.get());
		// 	}
		// };
		// t3.start();

		// try {
		// 	t1.join();
		// 	t2.join();
		// 	t3.join();
		// } catch (Exception e) {
		// 	//TODO: handle exception
		// }
			
		// 	// long elapsedTime = System.nanoTime() - start;
		// 	// System.out.println(elapsedTime);

			
			
		// 	if(idTypeProduct[0] == ProductEnum.AO.get() || idTypeProduct[0] == ProductEnum.QUAN.get()){
		// 		String nameSize = clothesSizeRepository.getClothesSize(size);
		// 		if(cartRepository.checkExist(idProduct, size,idUser)==1) {
		// 			return cartRepository.updateCart(numberOfSize,idProduct, size,idUser);			
		// 		}else {
				
		// 			return cartRepository.inserCart(idUser, idProduct, numberOfSize, size,nameProduct[0],price[0],StatusProductEnum.SHOW.get(),nameSize);
		// 		}
		// 	}
			
		// 	if(idTypeProduct[0] ==  ProductEnum.GIAY.get()) {
		// 		String nameSize = String.valueOf(shoesSizeRepository.getShoesSize(size));
		// 		if(cartRepository.checkExist(idProduct, size,idUser)==1) {
		// 			return cartRepository.updateCart(numberOfSize,idProduct, size,idUser);			
		// 		}else {
		// 			return cartRepository.inserCart(idUser, idProduct, numberOfSize, size,nameProduct[0],price[0],StatusProductEnum.SHOW.get(),nameSize);
		// 		}		
		// 	}		
			
		// 	if(cartRepository.checkExist(idProduct, size,idUser)>=1) {
		// 		return cartRepository.updateCart(numberOfSize,idProduct, size,idUser);			
		// 	}else {
		// 		return cartRepository.inserCart(idUser, idProduct, numberOfSize, size,nameProduct[0],price[0],StatusProductEnum.SHOW.get(),"");
		// 	}
			
			
		

		 long start = System.nanoTime(); 
		 String nameProduct  = productRepository.getNameProduct(idProduct, StatusProductEnum.SHOW.get());
		
		 float price = productRepository.getPriceProduct(idProduct, StatusProductEnum.SHOW.get());
			

		 int idTypeProduct  = productRepository.getType(idProduct, StatusProductEnum.SHOW.get());
		
		long elapsedTime = System.nanoTime() - start;
		System.out.println(elapsedTime);
		if(idTypeProduct== ProductEnum.AO.get() || idTypeProduct == ProductEnum.QUAN.get()){
			String nameSize = clothesSizeRepository.getClothesSize(size);
			if(cartRepository.checkExist(idProduct, size,idUser)==1) {
				return cartRepository.updateCart(numberOfSize,idProduct, size,idUser);			
			}else {
				return cartRepository.inserCart(idUser, idProduct, numberOfSize, size,nameProduct,price,StatusProductEnum.SHOW.get(),nameSize);
			}
		}
		
		if(idTypeProduct ==  ProductEnum.GIAY.get()) {
			String nameSize = String.valueOf(shoesSizeRepository.getShoesSize(size));
			if(cartRepository.checkExist(idProduct, size,idUser)==1) {
				return cartRepository.updateCart(numberOfSize,idProduct, size,idUser);			
			}else {
				return cartRepository.inserCart(idUser, idProduct, numberOfSize, size,nameProduct,price,StatusProductEnum.SHOW.get(),nameSize);
			}		
		}
		
		if(cartRepository.checkExist(idProduct, size,idUser)>=1) {
			return cartRepository.updateCart(numberOfSize,idProduct, size,idUser);			
		}else {
			return cartRepository.inserCart(idUser, idProduct, numberOfSize, size,nameProduct,price,StatusProductEnum.SHOW.get(),"");
		}
		
	}
	
	// get list cart
	public List<Cart> listCart (int idUser){
		return cartRepository.listCart(idUser);
	}
	 
	// up and down product
	public int upDownCart( int idProduct , int numberOfSize , int size) {
		User user = (User) session.getAttribute(Constant.USER);
		return cartRepository.upDownCart(numberOfSize, idProduct, size, user.getIdUser());
	}
	
	// delete product
	public int  deleteCart(int idProduct,int size) {
		User user = (User) session.getAttribute(Constant.USER);
		return cartRepository.deleteCart(idProduct, size, user.getIdUser());
		
	}
	
	public List<CheckCart>  checkCart() {
		User user = (User) session.getAttribute(Constant.USER);
		List<CheckCart> ListheckCart = new ArrayList<CheckCart>();
		List<Cart> cart =	cartRepository.listCart(user.getIdUser());
		for (Cart c : cart) {
			CheckCart checkCart = new CheckCart();
			String type ;
			try {
				 type = 	Integer.toHexString(productRepository.getType(c.getIdProduct(), 1));
			} catch (Exception e) {
				type = null;
			}
			
			if(type == null) { //product sold out
				checkCart.setNameProduct(c.getNameProduct());
				checkCart.setMessageError("Mặt hàng tạm ngừng kinh doanh");
				ListheckCart.add(checkCart);
				continue;
			}		
			// product other
			if(type != null && Integer.parseInt(type) != ProductEnum.AO.get() && Integer.parseInt(type) != ProductEnum.QUAN.get() &&   Integer.parseInt(type) != ProductEnum.GIAY.get()) {
				int numberProduct = productRepository.getNumberProduct(c.getIdProduct(), 1);
				if(c.getNumberOfSize() > numberProduct) {
					checkCart.setNameProduct(c.getNameProduct());
					checkCart.setMessageError("Chỉ còn "+ numberProduct+" sản phảm");
					ListheckCart.add(checkCart);
					continue;
				}
				continue;
			}
			
			if(Integer.parseInt(type) != ProductEnum.GIAY.get()) { //product clothes
				int numberOfClothingProducts = numberOfClothesSizeRepository.getNumberClothes(c.getIdProduct(), c.getSize()); //all size of the product 
				if(c.getNumberOfSize() > numberOfClothingProducts) {
					checkCart.setNameProduct(c.getNameProduct()+" "+c.getNameSize());
					checkCart.setMessageError("Chỉ còn "+ numberOfClothingProducts+" sản phảm");
					ListheckCart.add(checkCart);
					continue;
				}
				continue;
			}
			
			int numberOfShoesProducts = numberOfShoesSizeRepository.getNumberShoes(c.getIdProduct(), c.getSize()); //all size of the product 
			if(c.getNumberOfSize() > numberOfShoesProducts) { // product shoes
				checkCart.setNameProduct(c.getNameProduct()+" size "+c.getNameSize());
				checkCart.setMessageError("Chỉ còn "+ numberOfShoesProducts+" sản phảm");
				ListheckCart.add(checkCart);
				continue;
			}
			continue;
		}
		return ListheckCart;
		
	}
	
	
}
