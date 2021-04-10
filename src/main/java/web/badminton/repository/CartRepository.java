package web.badminton.repository;

import java.util.List;

import web.badminton.vo.Cart;

public interface CartRepository {
	int inserCart(int idUser ,int idProduct , int numberOfSize, int size,String nameProduct, float price , int statusProduct ,String nameSize);
	List<Cart> listCart(int idUser);
	int checkExist(int idProduct,int size,int idUSer);
	int updateCart( int numberOfSize,int idProduct,int size,int idUser);
	int upDownCart(int numberOfSize,int idProduct,int size,int idUser);
	int deleteCart(int idProduct,int size,int idUser);
	int deleteAllProductInTheCart(int idUser);
}
