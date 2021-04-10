package web.badminton.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.modal.Clothes;


@Repository
public class NumberOfClothesSizeRepositoryImpl implements NumberOfClothesSizeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertNumberOfClothesSize(int idTypeClothesSize , int idProduct ,int numberSize ) {
		String sql = "INSERT INTO numberOfClothesSize(idTypeClothesSize,idProduct,numberSize) VALUES(?, ?, ?)";
		return jdbcTemplate.update(sql,new Object[] {idTypeClothesSize,idProduct,numberSize});
	}

	@Override
	public List<Clothes> listNumberClothes(int idProduct) {
		String sql = "SELECT * FROM numberofclothessize join clothessize on numberofclothessize.idTypeClothesSize = clothessize.idTypeClothesSize WHERE idProduct=?";	
		return jdbcTemplate.query(sql, new Object[] {idProduct}, new RowMapper<Clothes>() {

			@Override
			public Clothes mapRow(ResultSet rs, int rowNum) throws SQLException {
				Clothes clothes = new Clothes();
				clothes.setIdClothesSize(rs.getInt("idClothesSize"));
				clothes.setIdProduct(rs.getInt("idProduct"));
				clothes.setIdTypeClothesSize(rs.getInt("idTypeClothesSize"));
				clothes.setNumberSize(rs.getInt("numberSize"));
				clothes.setClothesSize(rs.getString("clothesSize"));
				return clothes;
			}});
	}

	@Override
	public int getNumberClothes(int idProduct, int idTypeClothesSize) {
		String sql = "SELECT numberSize FROM numberofclothessize WHERE idProduct = ? AND idTypeClothesSize =? ";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,idTypeClothesSize}, Integer.class);
	}

	@Override
	public int updateNumberSize(int idProduct, int idTypeSize, int numberSize) {
		String sql = "UPDATE numberofclothessize SET numberSize = numberSize - ? WHERE idProduct = ? AND idTypeClothesSize = ?";
		return jdbcTemplate.update(sql, numberSize, idProduct ,idTypeSize );
	}


}
