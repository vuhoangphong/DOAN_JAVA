package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.vo.ProductBrands;

@Repository
public class ProductBrandsRepositoryImpl implements ProductBrandsRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ProductBrands> getBrands() {
		return jdbcTemplate.query("SELECT *FROM productBrands", new RowMapper<ProductBrands>() {

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
	public List<ProductBrands> getTypeStatus1(int idType) {
		return jdbcTemplate.query("SELECT idBrand,idType,nameBrand,statusBrand FROM productBrands WHERE idType = ? AND statusBrand = ? ", new Object[] {idType,1}, new RowMapper<ProductBrands>() {

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
	public List<ProductBrands> findBrands(String id) {
		return jdbcTemplate.query("SELECT *FROM productBrands WHERE idBrand = ?",new Object[]{id} ,new RowMapper<ProductBrands>() {

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
	public int insertBrand(String name,String idType,String status) {
		String sql = "insert into productbrands (nameBrand,idType,statusBrand) value(?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{name,idType,status});
	}

	@Override
	public int deleteBrand(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
