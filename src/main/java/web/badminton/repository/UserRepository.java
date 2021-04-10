package web.badminton.repository;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

import web.badminton.dto.RegisterUserDTO;
import web.badminton.dto.UserDTO;
import web.badminton.vo.User;

public interface UserRepository  {
	int checkUserExist(String account );
	String getName(String account , String password);	
	User getUser(String account);
	int insertUser(RegisterUserDTO register);
	int checkAccountExist(RegisterUserDTO register);
	int updateUser(RegisterUserDTO register,String address, int idUser,MultipartFile imageUpload );
	int UpdateUserPayment(UserDTO user,int idUser);
	Blob getAvatarUser(int idUser);
	User findByName(String account);
	int verifyUser(String account,String status);
	int getIdUser(String account);
	List<User> getAllUser(int status);
	int updateStatusUser(String idUser,String status);
	
	
}
