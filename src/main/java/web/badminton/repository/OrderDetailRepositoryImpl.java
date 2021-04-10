package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.modal.Month;
import web.badminton.modal.OrderDetail;
import web.badminton.vo.Product;

@Repository
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int insertOrderDetail(int idOrder, int idProduct, int numberProductPurchased, float totalMoneyProduct,
			int idTypeSize, int status) {
		String sql = "INSERT INTO orderdetails (idOrder,idProduct,numberProductPurchased,totalMoneyProduct,idTypeSize,statusDetails,dateBuy) VALUES(?, ?, ?, ?, ?, ?, now())";
		return jdbcTemplate.update(sql, idOrder, idProduct, numberProductPurchased, totalMoneyProduct, idTypeSize,status);
	}

	@Override
	public List<Product> getProductsBought(int idUser) {
		String sql = "SELECT p.*,o.dateBuy FROM badminton.order o , badminton.orderdetails d, badminton.product p WHERE o.idOrder = d.idOrder AND  d.idProduct = p.idProduct AND idUser = ? GROUP BY  p.idProduct ORDER BY dateBuy DESC";
		return jdbcTemplate.query(sql, new Object[] { idUser }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setIdProduct(rs.getInt("idProduct"));
				product.setIdShop(rs.getInt("idShop"));
				product.setNameProduct(rs.getString("nameProduct"));
				product.setPriceProduct(rs.getFloat("priceProduct"));
				product.setOrigin(rs.getString("origin"));
				product.setPriceDiscount(rs.getInt("priceDiscount"));
				product.setIdBrand(rs.getInt("idBrand"));
				product.setNumberProduct(rs.getInt("numberProduct"));
				product.setDateInput(rs.getDate("dateInput"));
				product.setStatusProduct(rs.getInt("statusProduct"));
				product.setProductDescription(rs.getString("productDescription"));
				product.setReasonBanned(rs.getString("reasonBanned"));
				product.setDayBanned(rs.getDate("dayBanned"));
				product.setRanking(rs.getInt("ranking"));
				product.setPrice(rs.getFloat("price"));
				return product;
			}

		});
	}

	@Override
	public List<OrderDetail> getDetails(int status , int idShop) {
		String sql ="SELECT idOrder,product.idProduct,numberProductPurchased,totalMoneyProduct,idTypeSize,statusDetails,dateBuy,idShop,nameProduct FROM badminton.orderdetails "
					+"INNER JOIN product "
					+"WHERE product.idProduct = orderdetails.idProduct AND product.idShop = ? AND statusDetails = ?";
		
		List<OrderDetail> orderDetails =  jdbcTemplate.query(
			sql,
			new Object[]{idShop,status},
			new BeanPropertyRowMapper(OrderDetail.class));
		return orderDetails;
	}

	@Override
	public int updateOrderDetails(int status,int idOrder , String idProduct) {
		String sql = "update orderdetails set statusDetails = ? where idOrder = ? AND idProduct = ? ";
		return jdbcTemplate.update(sql, new Object[]{status,idOrder,idProduct});
	}

	@Override
	public List<Month> orderStatistical(int status, int idShop, int year) {
		String sql ="SELECT MONTH(dateBuy) as month,count(DISTINCT idOrder) as value FROM badminton.orderdetails"
		+" INNER JOIN product"
		+" WHERE product.idProduct = orderdetails.idProduct AND product.idShop = ? AND statusDetails = ? AND year(dateBuy) = ?"
		+" group by MONTH(dateBuy)"
		+" order by MONTH(dateBuy) asc";
		List<Month> months = jdbcTemplate.query(sql,
		new Object[]{idShop,status,year},
		new BeanPropertyRowMapper(Month.class));
		return months;
	}

	@Override
	public List<Month> orderStatisticalYear(int idShop, int year) {
		String sql ="SELECT MONTH(dateBuy) as month,count(idOrder) as value FROM badminton.orderdetails"
		+" INNER JOIN product"
		+" WHERE product.idProduct = orderdetails.idProduct AND product.idShop = ? AND year(dateBuy) = ?"
		+" group by MONTH(dateBuy)"
		+" order by MONTH(dateBuy) asc";
		List<Month> months = jdbcTemplate.query(sql,
		new Object[]{idShop,year},
		new BeanPropertyRowMapper(Month.class));
		return months;
	}

	@Override
	public List<Month> statisticalAllOrder(int status, int year) {
		String sql = "SELECT  MONTH(dateBuy) as month,count( idOrder) as value FROM badminton.orderdetails where statusDetails = ? AND year(dateBuy) = ? group by MONTH(dateBuy)  order by MONTH(dateBuy) asc";
		List<Month> months = jdbcTemplate.query(sql,
		new Object[]{status,year},
		new BeanPropertyRowMapper(Month.class));
		return months;
	}

	@Override
	public List<Month> statisticalAllOrderYear(int year) {
		String sql ="SELECT  MONTH(dateBuy) as month,count( idOrder) as value FROM badminton.orderdetails where year(dateBuy) = ? group by MONTH(dateBuy)  order by MONTH(dateBuy) asc";
		List<Month> months = jdbcTemplate.query(sql,
		new Object[]{year},
		new BeanPropertyRowMapper(Month.class));
		return months;
	}

	@Override
	public int countProduct(int idOrder) {
		String sql = "select count(*)FROM badminton.orderdetails Where idOrder = ?";
		return jdbcTemplate.queryForObject(sql,new  Object[] {idOrder}, Integer.class);
	}

	@Override
	public int countStatus(int idOrder, int status) {
		String sql = "select count(*)FROM badminton.orderdetails Where idOrder = ? AND statusDetails = ?";
		return jdbcTemplate.queryForObject(sql,new  Object[] {idOrder,status}, Integer.class);
	}

}
