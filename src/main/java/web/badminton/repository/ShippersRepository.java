package web.badminton.repository;

import java.util.List;

import web.badminton.vo.Shipper;

public interface ShippersRepository {
	List<Shipper> getListShippers(int statusShipper);
	String getNameCompanyShipper(int idCompanyShiper);
}
