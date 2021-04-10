package web.badminton.repository;


import java.sql.Blob;
import java.util.List;

import web.badminton.dto.RegisterShopDTO;
import web.badminton.vo.Shop;

public interface ShopRepository {
	int checkExistShop(int idUser);
	int checkExistNameShop(String nameShop);
	int insertShop(RegisterShopDTO registerShopDTO,int idUser);
	int getidShop(int idUser);
	Shop getShop(int idUser);
	int updateShop(int idShop,String nameShop , String addressShop, String numberPhoneShop , byte[] avatarShop);
	Blob getAvatarShop(int idShop);
	String findNameShop(int id);
	List<Shop> getListShops(int status);
}
