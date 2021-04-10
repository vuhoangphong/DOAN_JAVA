package web.badminton.repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

import web.badminton.dto.RegisterShopDTO;
import web.badminton.enums.ShopEnum;
import web.badminton.vo.Shop;

@Repository
public class ShopRepositoryImpl implements ShopRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int checkExistShop(int idUser) {
		String sql = "SELECT COUNT(idUser) FROM shop WHERE idUser = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idUser }, Integer.class);
	}

	@Override
	public int checkExistNameShop(String nameShop) {
		String sql = "SELECT COUNT(nameShop) FROM shop WHERE nameShop = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { nameShop }, Integer.class);
	}

	@Override
	public int insertShop(RegisterShopDTO registerShopDTO, int idUser) {
		String sql = "INSERT INTO shop (idUser,nameShop,addressShop,numberPhoneShop,statusShop) VALUES(?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, idUser, registerShopDTO.getNameShop(), registerShopDTO.getAddressShop(),
				registerShopDTO.getNumberPhoneShop(), 1);
	}

	@Override
	public int getidShop(int idUser) {
		return jdbcTemplate.queryForObject("SELECT idShop FROM shop WHERE idUser  = ?", new Object[] { idUser },
				Integer.class);
	}

	@Override
	public Shop getShop(int idUser) {
		return jdbcTemplate.queryForObject("SELECT * FROM shop WHERE idUser = ?", new Object[] { idUser },
				new RowMapper<Shop>() {

					@Override
					public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
						Shop shop = new Shop();
						shop.setIdUser(rs.getInt("idUser"));
						shop.setAddressShop(rs.getString("addressShop"));
						shop.setIdShop(rs.getInt("idShop"));
						shop.setNameShop(rs.getString("nameShop"));
						shop.setNumberPhoneShop(rs.getString("numberPhoneShop"));
						shop.setStatusShop(rs.getInt("statusShop"));
						shop.setAvatarShop(rs.getBytes("avatarShop"));
						return shop;
					}
				});
	}

	@Override
	public int updateShop(int idShop, String nameShop, String addressShop, String numberPhoneShop, byte[] avatarShop) {
		String sql = "UPDATE shop SET nameShop = ? , addressShop = ?, numberPhoneShop = ? ,avatarShop = ? WHERE idShop = ?  ";
		return jdbcTemplate.update(sql, nameShop, addressShop, numberPhoneShop, avatarShop, idShop);
	}

	@Override
	public Blob getAvatarShop(int idShop) {
		String sql = " SELECT avatarShop FROM shop WHERE idShop = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idShop }, Blob.class);
	}

	@Override
	public String findNameShop(int id) {
		String sql = " SELECT nameShop FROM shop WHERE idShop = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}

	@Override
	public List<Shop> getListShops(int status) {
		String sql = "SELECT * FROM shop WHERE statusShop = ?";
		return jdbcTemplate.query(sql, new Object[]{status},new BeanPropertyRowMapper(Shop.class));
	}
}
	
	

