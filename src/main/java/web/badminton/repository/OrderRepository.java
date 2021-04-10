package web.badminton.repository;

import java.util.List;

import web.badminton.modal.OrderDetailOJ;
import web.badminton.vo.OrderVO;

public interface OrderRepository {
	int insertOrder(int idUser ,int idCompanyShipper  ,int totalBought ,float totalMoney , int statusOrder, int shippingStatus);
	int updateStatus(int idOrder,int status);
	List<OrderVO> getListOrder (int idUser);
	int updateShippingStatus(int idOrder,int status);
	List<OrderDetailOJ> listOrderDetails(String id);
}
