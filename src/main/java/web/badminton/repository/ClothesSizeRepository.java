package web.badminton.repository;

import java.util.List;

import web.badminton.vo.ClothesSize;

public interface ClothesSizeRepository {
	List<ClothesSize> getListClothesSize();
	int getIdTypeClothesSize(String clothesSize);
	String getClothesSize(int idTypeClothesSize);
}
