package web.badminton.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.constant.Constant;
import web.badminton.enums.OrderDetailsEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.modal.Month;
import web.badminton.modal.OrderDetail;
import web.badminton.repository.OrderDetailRepository;
import web.badminton.repository.OrderRepository;
import web.badminton.repository.ProductRepository;
import web.badminton.repository.ShopRepository;
import web.badminton.vo.Shop;
import web.badminton.vo.User;

@Service
public class SalesChannelService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    HttpSession session;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
	OrderRepository orderRepository;
    public int  changeStatusProduct(int idProduct,int status) {
        return productRepository.changeStatusProduct(idProduct, status);
    }
    
    // find all store orders by order detail status
    public List<OrderDetail> getDetails (int status){
        User user = (User) session.getAttribute(Constant.USER);
        return orderDetailRepository.getDetails(status,shopRepository.getidShop(user.getIdUser()));
    }

    // get information shop
    public Shop getShop() {
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		return shopRepository.getShop(idUser);
    }
    
    // update order detail status
    public int updateOrderDetail(int status,int idOrder ,String idProduct){
        orderDetailRepository.updateOrderDetails(status,idOrder,idProduct);
        if(orderDetailRepository.countProduct(idOrder) == orderDetailRepository.countStatus(idOrder, OrderDetailsEnum.ORDER_SUCCESS.get()))
			orderRepository.updateShippingStatus(idOrder,0);
		else if(orderDetailRepository.countProduct(idOrder) == orderDetailRepository.countStatus(idOrder, OrderDetailsEnum.ORDER_CANCELLATION.get()))
			orderRepository.updateShippingStatus(idOrder,3);
		else if(orderDetailRepository.countStatus(idOrder, OrderDetailsEnum.BEING_TRANSPORT.get()) >0)
			orderRepository.updateShippingStatus(idOrder,2);
		else
            orderRepository.updateShippingStatus(idOrder,1);
        return 1;
    }

    // order statistical shop
    public String orderStatistical (int status,int year,int value){
        User user = (User) session.getAttribute(Constant.USER);
        List<Month>  months = orderDetailRepository.orderStatistical(status,shopRepository.getidShop(user.getIdUser()), year);
       if(value == 0) // return sum order of all year(order cancel or success)
          return  sum(months);
       else //return string data of all month
        return  format(months);
    
    }
    // order statistical shop
    public String orderStatisticalYear (int year,int value){
        User user = (User) session.getAttribute(Constant.USER);
        List<Month>  months = orderDetailRepository.orderStatisticalYear(shopRepository.getidShop(user.getIdUser()), year);
        if(value == 0) // return order sum of year
           return  sum(months);
        else //return string data of all month
             return  format(months);
        
    }




    private String format(List<Month>  months  ){
        String s ="";int data = 0;
        for(int i =1 ; i<=12 ; i++){
            try {
                String month = months.get(data).getMonth();
                if(i == Integer.parseInt(month)){
                    s+= months.get(data).getValue()+" ";
                    data++;
                }else
                s+="0 ";
            } catch (Exception e) {
               s+="0 ";
            }
         }
        return s;
    }

    private String sum(List<Month>  months ){
        int sum = 0;
        for (Month month : months) {
            sum+=Integer.parseInt( month.getValue());
        }
        return String.valueOf(sum);
    }
}