package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.vo.Shipper;

@Repository
public class ShippersRepositoryImlp implements ShippersRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<Shipper> getListShippers(int statusShipper) {
		String sql = "SELECT * FROM shippers WHERE statusShipper = ?";
		return jdbcTemplate.query(sql, new Object[] {statusShipper} , new RowMapper<Shipper>() {

			@Override
			public Shipper mapRow(ResultSet rs, int rowNum) throws SQLException {
				Shipper s = new Shipper();
				s.setIdCompanyShipper(rs.getInt("idCompanyShipper"));
				s.setNameCompanyShipper(rs.getString("nameCompanyShipper"));
				s.setFreight(rs.getFloat("freight"));
				s.setStatusShipper(rs.getInt("statusShipper"));
				return s;
			}});
	}
	@Override
	public String getNameCompanyShipper(int idCompanyShiper) {
		String sql = "SELECT nameCompanyShipper FROM shippers WHERE idCompanyShipper = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {idCompanyShiper},String.class);
	}

}
