package web.badminton.service;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import web.badminton.repository.RepositoryImageRepository;
import web.badminton.vo.ReponsitoryImage;

@Service
public class ImageProductService {
	@Autowired
	RepositoryImageRepository repositoryImageRepository;
	
	public int addImageProduct(int idProduct , List<MultipartFile> image) {
		return repositoryImageRepository.addImageProduct(idProduct, image);
	}
	
	public List<ReponsitoryImage>  getListIdImage(int idProduct){
		return repositoryImageRepository.getListIdImage(idProduct);
	}
	
	public Blob getPhotoDescriptionProduct(int idProduct,int idImage) {
		return repositoryImageRepository.getImage(idProduct,idImage);		
	}
}

