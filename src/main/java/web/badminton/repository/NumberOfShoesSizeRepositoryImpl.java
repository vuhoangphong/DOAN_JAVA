package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.modal.Shoes;

@Repository
public class NumberOfShoesSizeRepositoryImpl implements NumberOfShoesSizeRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int insertNumberOfShoesSize(int idProduct, int idTypeShoesSize, int numberSize) {
		String sql = "INSERT INTO numberOfShoesSize(idTypeShoesSize,idProduct,numberSize) VALUES(?, ?, ?)";
		return jdbcTemplate.update(sql,new Object[] {idTypeShoesSize,idProduct,numberSize});
	}
	@Override
	public List<Shoes> ListNumberShoes(int idProduct) {
		String sql = "SELECT * FROM numberofshoessize join shoessize on numberofshoessize.idTypeShoesSize = shoesSize.idTypeShoesSize WHERE idProduct=?";
		return jdbcTemplate.query(sql, new Object[] {idProduct}, new RowMapper<Shoes>() {

			@Override
			public Shoes mapRow(ResultSet rs, int rowNum) throws SQLException {
				Shoes shoes = new  Shoes();
				shoes.setIdProduct(rs.getInt("idProduct"));
				shoes.setIdShoesSize(rs.getInt("idShoesSize"));
				shoes.setIdTypeShoesSize(rs.getInt("idTypeShoesSize"));
				shoes.setNumberSize(rs.getInt("numberSize"));
				shoes.setShoesSize(rs.getInt("shoesSize"));
				return shoes;
			}			
		});
	}
	@Override
	public int getNumberShoes(int idProduct, int idTypeShoesSize) {
		String sql = "SELECT numberSize FROM numberofshoessize WHERE idProduct = ? AND idTypeShoesSize =? ";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,idTypeShoesSize}, Integer.class);
	}
	@Override
	public int updateNumberSize(int idProduct, int idTypeSize, int numberSize) {
		String sql = "UPDATE numberofshoessize SET numberSize = numberSize - ? WHERE idProduct = ? AND idTypeShoesSize = ?";
		return jdbcTemplate.update(sql, numberSize, idProduct ,idTypeSize );
	}

}
