package web.badminton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Blob;

import web.badminton.dto.RegisterUserDTO;
import web.badminton.repository.UserRepository;
import web.badminton.vo.User;
@Service
public class UserService {

	@Autowired 
	UserRepository userRepository;
	// public int checkExist(String accountLogin , String passwordLogin) {
	// 	return userRepository.checkUserExist(accountLogin, passwordLogin);	
	// }
	
	public User getUser(String accountLogin ) {
		return userRepository.getUser(accountLogin);
	}
	
	
	
	
	
	public  int  uploadUser(RegisterUserDTO register,String address , int idUser, MultipartFile imageUpload) {
		return userRepository.updateUser(register, address, idUser,imageUpload);
	}
	
	public Blob getAvatarUser(int idUser) {
		return userRepository.getAvatarUser(idUser);	
	}

}
