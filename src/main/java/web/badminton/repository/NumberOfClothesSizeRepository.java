package web.badminton.repository;

import java.util.List;

import web.badminton.modal.Clothes;


public interface NumberOfClothesSizeRepository {
	int insertNumberOfClothesSize(int idTypeClothesSize , int idProduct ,int numberSize ) ;
	List<Clothes> listNumberClothes(int idProduct);
	int getNumberClothes(int idProduct, int idTypeClothesSize);
	int updateNumberSize(int idProduct,  int idTypeSize, int numberSize);
	
}
