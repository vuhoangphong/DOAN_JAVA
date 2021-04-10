package web.badminton.service;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Blob;

import web.badminton.constant.Constant;
import web.badminton.dto.RegisterShopDTO;
import web.badminton.enums.RoleEnum;
import web.badminton.enums.ShopEnum;
import web.badminton.repository.RoleRepository;
import web.badminton.repository.ShopRepository;
import web.badminton.vo.Shop;
import web.badminton.vo.User;

@Service
public class ShopService {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ShopRepository shopRepository;
	@Autowired 
	RoleRepository roleRepository;
	

	
	public int  checkExistShop() {
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		return shopRepository.checkExistShop(idUser);
	}
	
	public int getIdShop(int idUser) {
		return shopRepository.getidShop(idUser);
	}
	
	public int checkExistNameShop(String nameShop) {
		return shopRepository.checkExistNameShop(nameShop);
	}
	
	public int	insertShop(RegisterShopDTO registerShopDTO) {
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		roleRepository.insertRole(idUser, RoleEnum.ROLE_SHOP.get());
		return shopRepository.insertShop(registerShopDTO, idUser);
	}
	
	public Shop getShop() {
		User user = (User) session.getAttribute(Constant.USER);
		int idUser = user.getIdUser();
		return shopRepository.getShop(idUser);
	}
	
	public int updateShop(int idShop,String nameShop,String addressShop,String numberPhoneShop, MultipartFile imageUpload) {
		try {			
			byte[] avatarShop = imageUpload.getBytes();
			Shop shop = getShop();
			if(shop.getNameShop().equals(nameShop) != false && shop.getAddressShop().equals(addressShop) != false && shop.getNumberPhoneShop().equals(numberPhoneShop) != false )   {
				if(Arrays.equals(shop.getAvatarShop(), avatarShop) == true) {
				return ShopEnum.INFORMATIONFAIL.get();
				}
			}
			return shopRepository.updateShop(idShop, nameShop, addressShop, numberPhoneShop,avatarShop);			
		} catch (Exception e) {
			return ShopEnum.IMAGEFAIL.get();
		}
		
	}
	
	public Blob getAvatarShop(int idShop) {
		return shopRepository.getAvatarShop(idShop);
	}
	
	
}
