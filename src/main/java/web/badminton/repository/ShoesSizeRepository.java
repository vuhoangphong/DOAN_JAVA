package web.badminton.repository;

import java.util.List;

import web.badminton.vo.ShoesSize;

public interface ShoesSizeRepository {
	List<ShoesSize> getListShoesSize();
	int getShoesSize(int idTypeShoesSize);
}
