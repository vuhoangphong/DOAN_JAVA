package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.vo.Cart;

@Repository
public class CartRepositoryImpl implements CartRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int inserCart(int idUser,int idProduct, int numberOfSize, int size, String nameProduct, float price , int statusProduct,String nameSize) {
		String sql ="INSERT INTO cart (idUser,idProduct,numberOfSize,size,nameProduct,price,statusProduct,nameSize) VALUE(?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, new Object[] {idUser,idProduct,numberOfSize,size,nameProduct,price,statusProduct,nameSize});
	}
	@Override
	public List<Cart> listCart(int idUser) {
		String sql ="SELECT * FROM cart WHERE idUser = ?";
		return jdbcTemplate.query(sql, new Object[] {idUser}, new RowMapper<Cart>() {

			@Override
			public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cart c = new Cart();
				c.setIdUser(rs.getInt("idUser"));
				c.setIdProduct(rs.getInt("idProduct"));
				c.setNumberOfSize(rs.getInt("numberOfSize"));
				c.setSize(rs.getInt("size"));
				c.setNameProduct(rs.getString("nameProduct"));
				c.setPrice(rs.getFloat("price"));
				c.setStatusProduct(rs.getInt("statusProduct"));
				c.setNameSize(rs.getString("nameSize"));
				return c;
			}});
	}
	@Override
	public int checkExist(int idProduct, int size,int idUSer) {
		String sql ="SELECT COUNT(*) FROM cart WHERE idProduct = ? AND size=? AND idUser=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,size,idUSer}, Integer.class);
	}
	
	@Override
	public int updateCart( int numberOfSize,int idProduct, int size,int idUser) {
		String sql = "UPDATE cart SET numberOfSize = ("+numberOfSize+"+numberOfSize) WHERE idProduct = "+idProduct+" AND size = "+size+" AND idUser = "+idUser+"";
		return jdbcTemplate.update(sql);
	}
	@Override
	public int upDownCart(int numberOfSize, int idProduct, int size, int idUser) {
		String sql =  "UPDATE cart SET numberOfSize = "+numberOfSize+" WHERE idProduct = "+idProduct+" AND size = "+size+" AND idUser = "+idUser+"";
		return jdbcTemplate.update(sql);
	}
	@Override
	public int deleteCart(int idProduct, int size, int idUser) {
		String sql = "DELETE FROM cart WHERE idProduct = "+idProduct+" AND size = "+size+" AND idUser = "+idUser+" ";
		return jdbcTemplate.update(sql);
	}
	@Override
	public int deleteAllProductInTheCart(int idUser) {
		String sql = "DELETE FROM cart WHERE idUser = "+idUser+"";
		return jdbcTemplate.update(sql);
	}
	
}
