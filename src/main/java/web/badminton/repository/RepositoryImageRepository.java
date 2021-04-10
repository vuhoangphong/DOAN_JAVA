package web.badminton.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

import web.badminton.vo.ReponsitoryImage;

public interface RepositoryImageRepository {
	int addImageProduct(int idProduct, List<MultipartFile> listImage);
	Blob getImage(int idProduct,int idImage);
	List<ReponsitoryImage> getListIdImage(int idProduct);
}
