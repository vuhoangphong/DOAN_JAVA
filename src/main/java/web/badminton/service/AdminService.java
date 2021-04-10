package web.badminton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.badminton.enums.RoleEnum;
import web.badminton.enums.StatusProductEnum;
import web.badminton.modal.Month;
import web.badminton.repository.OrderDetailRepository;
import web.badminton.repository.ProductBrandsRepository;
import web.badminton.repository.ProductPortfolioRepository;
import web.badminton.repository.ProductRepository;
import web.badminton.repository.ProductReviewRepository;
import web.badminton.repository.RoleRepository;
import web.badminton.repository.ShopRepository;
import web.badminton.repository.UserRepository;
import web.badminton.vo.Product;
import web.badminton.vo.ProductBrands;
import web.badminton.vo.ProductPortfolio;
import web.badminton.vo.ProductReview;
import web.badminton.vo.Shop;
import web.badminton.vo.User;

@Service
public class AdminService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductPortfolioRepository portfolioRepository;
    @Autowired
    ProductBrandsRepository productBrandsRepository;
    @Autowired 
    ProductReviewRepository productReviewRepository;
    @Autowired
    UserRepository repositoryUser;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    RoleRepository roleRepository;


     // order statistical 
     public String statisticalAllOrder (int status,int year,int value){
        List<Month>  months = orderDetailRepository.statisticalAllOrder(status, year);
       if(value == 0) // return sum order of all year(order cancel or success)
          return  sum(months);
       else //return string data of all month
        return  format(months);
    
    }

    // statistical all order
    public String statisticalAllOrderYear (int year,int value){
        List<Month>  months = orderDetailRepository.statisticalAllOrderYear(year);
        if(value == 0) // return order sum of year
           return  sum(months);
        else //return string data of all month
             return  format(months);
        
    }



    //categories
    //get all categories
    public List<ProductPortfolio> getNamePortfolio() {
        return portfolioRepository.getNamePortfolio();
    }
    // get all brand
    public List<ProductBrands> getBrands() {
        return productBrandsRepository.getBrands();
    }

    public void insertPortfolio(String name) {
        portfolioRepository.insertPortfolio(name);
    }
    
    public void deletePortfolio(String id ){
        portfolioRepository.deletePortfolio(id);
    }

    public void insertBrand(String name ,String idType ,String status) {
        productBrandsRepository.insertBrand(name,idType,status);
    }
    
    public void deleteBrand(String id ){
        productBrandsRepository.deleteBrand(id);
    }
	
	public List<ProductBrands> getTypeStatus1(int idType){
		return productBrandsRepository.getTypeStatus1(idType);
    }
    
    // comment management
    public List<ProductReview> findRank(String rank) {
        int getRank = Integer.parseInt(rank);
        return productReviewRepository.findRank(getRank);
    }
    //account management 
    //find all user by type
    public List<User> getAllUser(String status) {
        return repositoryUser.getAllUser(Integer.parseInt(status));
    }

    public int updateStatusUser(String idUser,String status) {
        if(status.equals("lock")){
            return  repositoryUser.updateStatusUser(idUser, "2"); // lock user  
        }
        if(status.equals("unlock")){
            return repositoryUser.updateStatusUser(idUser, "1");  // unlock user
        }
        if(status.equals("activate")){
            roleRepository.insertRole(Integer.parseInt(idUser),RoleEnum.ROLE_MEMBER.get());
            return repositoryUser.updateStatusUser(idUser, "1");  // unlock user
        }
        return 0;
    }

    public List<Product> AdminSearchProduct(String  brand , String portfolio, String keySearch , String status){
        List<Product> ListProduct = Search(brand, portfolio, keySearch, status);
        for (Product product : ListProduct) {
           product.setNameShop(findNameShop(product.getIdShop()));
        }
        return ListProduct;
    }


    // find all shop

    public String findNameShop(int idShop) {
        return shopRepository.findNameShop(idShop);
    }

    public  List<Product> Search(String  brand , String portfolio, String keySearch , String status) {
        int idBrand;
		int idType;
		try {
			idBrand = Integer.parseInt(brand);		
		} catch (Exception e) {
			idBrand = -1;
		}
		
		try {
			idType = Integer.parseInt(portfolio);
		} catch (Exception e) {
			idType = -1;
        }		
        if(status.equals("0")){
            return productRepository.AdminSearchProduct(idBrand, idType, keySearch,StatusProductEnum.HIDE.get());
        }else if( status.equals("1")){
            return productRepository.AdminSearchProduct(idBrand, idType, keySearch,StatusProductEnum.SHOW.get());

        }else if(status.equals("2")){
            return productRepository.AdminSearchProduct(idBrand, idType, keySearch,StatusProductEnum.SOLDOUT.get());
        }else{
            return productRepository.AdminSearchProduct(idBrand, idType, keySearch,StatusProductEnum.BAN.get());
        }		
    }

    public List<Shop>  findAllShop(int status) {
        return shopRepository.getListShops(status);
    }

    

    //

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
