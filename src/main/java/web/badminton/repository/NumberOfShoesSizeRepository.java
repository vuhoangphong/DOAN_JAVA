package web.badminton.repository;

import java.util.List;

import web.badminton.modal.Shoes;
import web.badminton.vo.NumberOfShoesSize;

public interface NumberOfShoesSizeRepository {
	int insertNumberOfShoesSize(int idProduct,int idTypeShoesSize, int numberSize);
	List<Shoes> ListNumberShoes(int idProduct);
	int getNumberShoes(int idProduct, int idTypeShoesSize);
	int updateNumberSize(int idProduct,  int idTypeSize, int numberSize);
}
