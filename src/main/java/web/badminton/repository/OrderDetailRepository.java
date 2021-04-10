package web.badminton.repository;

import java.util.List;

import web.badminton.modal.Month;
import web.badminton.modal.OrderDetail;
import web.badminton.vo.Product;

public interface OrderDetailRepository {
	int insertOrderDetail(int idOrder , int idProduct , int numberProductPurchased ,float totalMoneyProduct,int idTypeSize, int status);
	List<Product> getProductsBought(int idUser);
	List<OrderDetail> getDetails(int status,int idShop);
	int updateOrderDetails(int status,int idOrder,String idProduct);
	List<Month> orderStatistical(int status,int idShop,int year);
	List<Month> orderStatisticalYear(int idShop,int year);
	List<Month> statisticalAllOrder(int status ,int year);
	List<Month> statisticalAllOrderYear(int year);
	int countProduct(int idOrder);
	int countStatus(int  idOrder,int status);
}
