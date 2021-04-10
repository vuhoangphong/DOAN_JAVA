package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.vo.ClothesSize;
@Repository
public class ClothesSizeRepositryImpl implements ClothesSizeRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<ClothesSize> getListClothesSize() {
		String sql = "SELECT * FROM clothesSize";
		return jdbcTemplate.query(sql, new RowMapper<ClothesSize>() {

			@Override
			public ClothesSize mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClothesSize clothesSize = new ClothesSize();
				clothesSize.setIdTypeClothesSize(rs.getInt("idTypeClothesSize"));
				clothesSize.setClotheSize(rs.getString("ClothesSize"));
				return clothesSize;
			}
		});
	}
	@Override
	public int getIdTypeClothesSize(String clothesSize) {
		String sql = "SELECT * FROM clothesSize WHERE clothesSize = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {clothesSize},Integer.class);
	}
	@Override
	public String getClothesSize(int idTypeClothesSize) {
		String sql = "SELECT clothesSize FROM clothesSize WHERE idTypeClothesSize = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idTypeClothesSize},String.class);
	}

}
