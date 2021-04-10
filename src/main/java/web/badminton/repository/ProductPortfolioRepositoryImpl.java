package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.vo.ProductBrands;
import web.badminton.vo.ProductPortfolio;
@Repository
public class ProductPortfolioRepositoryImpl implements ProductPortfolioRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ProductPortfolio> getNamePortfolio() {
		return jdbcTemplate.query("SELECT * FROM productportfolio", new RowMapper<ProductPortfolio>(){

			@Override
			public ProductPortfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductPortfolio productPortfolio = new ProductPortfolio();
				productPortfolio.setIdType(rs.getInt("idType"));
				productPortfolio.setNameType(rs.getString("nameType"));
				return productPortfolio;
			}						
		});
	}

	@Override
	public int countType() {	
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM productportfolio",Integer.class);
	}

	@Override
	public List<ProductBrands> findNamePortfolio(String id) {
		return jdbcTemplate.query("SELECT * FROM productBrands WHERE idType = ?", new Object[]{id},new RowMapper<ProductBrands>(){

			@Override
			public ProductBrands mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductBrands productBrands = new ProductBrands();
				productBrands.setIdBrand(rs.getInt("idBrand"));
				productBrands.setIdType(rs.getInt("idType"));
				productBrands.setNameBrand(rs.getString("nameBrand"));
				productBrands.setStatusBrand(rs.getInt("statusBrand"));
				return productBrands;
			}						
		});
	}

	@Override
	public int insertPortfolio(String name) {
		String sql = "insert into productportfolio (nameType) value(?)";
		return jdbcTemplate.update(sql, new Object[]{name});
	}

	@Override
	public int deletePortfolio(String id) {
		String sql ="DELETE from productportfolio WHERE idType = ?";
		return jdbcTemplate.update(sql,new Object[]{id});
	}
}
