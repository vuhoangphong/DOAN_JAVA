package web.badminton.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import web.badminton.modal.OrderDetailOJ;
import web.badminton.vo.OrderVO;

@Repository
public class OrderRepositoryImlp implements OrderRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int insertOrder(int idUser, int idCompanyShipper, int totalBought, float totalMoney, int statusOrder,
			int shippingStatus) {
			Date dt = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(dt); 
			c.add(Calendar.DATE, 2);
			dt = c.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = formatter.format(dt);
		String sql = "INSERT INTO badminton.order (idUser,idCompanyShipper,dateBuy,totalBought,totalMoney,statusOrder,shippingStatus,deliveryDate) VALUES(?, ?, now(), ?, ?, ?, ?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idUser);
				ps.setInt(2, idCompanyShipper);
				ps.setInt(3, totalBought);
				ps.setFloat(4, totalMoney);
				ps.setInt(5, statusOrder);
				ps.setInt(6, shippingStatus);
				ps.setString(7, strDate);
				return ps;
			}
		}, keyHolder);
		if (result == 1) {
			return keyHolder.getKey().intValue();
		} else {
			return 0;
		}

	}

	@Override
	public int updateStatus(int idOrder, int status) {
		String sql = "UPDATE badminton.order SET statusOrder = ? WHERE idOrder = ?";
		return jdbcTemplate.update(sql, status, idOrder);
	}

	@Override
	public List<OrderVO> getListOrder(int idUser) {
		String sql = "SELECT * FROM badminton.order WHERE idUser = ? ORDER BY dateBuy DESC";
		return jdbcTemplate.query(sql, new Object[] { idUser }, new RowMapper<OrderVO>() {

			@Override
			public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderVO order = new OrderVO();
				order.setDateBuy(rs.getDate("dateBuy"));
				order.setDeliveryDate(rs.getString("deliveryDate"));
				order.setIdCompanyShipper(rs.getInt("idCompanyShipper"));
				order.setIdOrder(rs.getInt("idOrder"));
				order.setIdUser(rs.getInt("idUser"));
				order.setStatusOrder(rs.getInt("statusOrder"));
				order.setTotalBought(rs.getInt("totalBought"));
				order.setTotalMoney(rs.getFloat("totalMoney"));
				order.setShippingStatus(rs.getInt("shippingStatus"));
				return order;
			}

		});
	}

	@Override
	public int updateShippingStatus(int idOrder, int status) {
		String sql = "UPDATE badminton.order SET shippingStatus = ? WHERE idOrder = ?";
		return jdbcTemplate.update(sql, status, idOrder);
	}

	@Override
	public List<OrderDetailOJ> listOrderDetails(String id) {
		String sql =
		"SELECT nameProduct,orderdetails.numberProductPurchased,product.price,shop.nameShop,orderdetails.idTypeSize,orderdetails.statusDetails,shippers.nameCompanyShipper"
		+" FROM badminton.order"
		+" JOIN badminton.orderdetails"
		+" ON order.idOrder = orderdetails.idOrder"
		+" JOIN badminton.product"
		+" ON product.idProduct = orderdetails.idProduct"
		+" JOIN badminton.shop"
		+" ON product.idShop = shop.idShop"
		+" JOIN badminton.shippers"
		+" ON order.idCompanyShipper = shippers.idCompanyShipper"
		+" WHERE order.idOrder = ?";
		return jdbcTemplate.query(sql,new Object[]{id},new RowMapper<OrderDetailOJ>(){

			@Override
			public OrderDetailOJ mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderDetailOJ o = new OrderDetailOJ();
				o.setNameProduct(rs.getString("nameProduct"));
				o.setNameCompanyShipper(rs.getString("nameCompanyShipper"));
				o.setNameShop(rs.getString("nameShop"));
				o.setNumberProduct(rs.getString("numberProductPurchased"));
				o.setPrice(rs.getString("price"));
				o.setSize(rs.getString("idTypeSize"));
				o.setStatus(rs.getString("statusDetails"));
				return o;
			}
			
		});
	}
}
