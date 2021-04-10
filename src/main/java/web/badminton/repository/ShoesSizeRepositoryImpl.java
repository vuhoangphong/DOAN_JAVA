package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.vo.ShoesSize;

@Repository
public class ShoesSizeRepositoryImpl implements ShoesSizeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<ShoesSize> getListShoesSize() {
		String sql = "SELECT * FROM shoesSize ";
		return jdbcTemplate.query(sql, new RowMapper<ShoesSize>() {

			@Override
			public ShoesSize mapRow(ResultSet rs, int rowNum) throws SQLException {
				ShoesSize shoesSize = new ShoesSize();
				shoesSize.setIdTypeShoesSize(rs.getInt("idTypeShoesSize"));
				shoesSize.setShoesSize(rs.getInt("shoesSize"));
				return shoesSize;
			}
		}) ;
	}
	@Override
	public int getShoesSize(int idTypeShoesSize) {
		String sql = "SELECT shoesSize FROM shoesSize WHERE idTypeShoesSize = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idTypeShoesSize},Integer.class);
	}

}
