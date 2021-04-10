package web.badminton.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.eclipse.jdt.internal.compiler.env.IModule.IPackageExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;


import web.badminton.dto.ProductDTO;

import web.badminton.vo.Product;
@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int inserProduct(ProductDTO addProductDTO,int idShop,MultipartFile avatarProduct) {	
	    String sql = "INSERT INTO product (idShop,nameProduct,priceProduct,priceDiscount,origin,idBrand,numberProduct,productDescription,dateInput,statusProduct,avatarProduct,price,ranking) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException { 
				PreparedStatement ps = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);				
				ps.setInt(1, idShop);
				ps.setString(2, addProductDTO.getNameProduct());
				ps.setFloat(3, addProductDTO.getPriceProduct());
				ps.setInt(4, addProductDTO.getPriceDiscount());
				ps.setString(5, addProductDTO.getOrigin());
				ps.setInt(6, addProductDTO.getIdBrand());
				ps.setInt(7, addProductDTO.getNumberProduct());
				ps.setString(8, addProductDTO.getProductDescription());
				ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));
				ps.setInt(10, 1);				
				try {
					byte[] avatar = avatarProduct.getBytes();
					ps.setBytes(11, avatar);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(addProductDTO.getPriceDiscount() != 0)
					ps.setFloat(12, addProductDTO.getPriceProduct() - (addProductDTO.getPriceProduct()*addProductDTO.getPriceDiscount()/100));
				else
					ps.setFloat(12, addProductDTO.getPriceProduct());
				ps.setInt(13, 0);		
				return ps;
			}
		},keyHolder);
		if(result == 1) {
			return keyHolder.getKey().intValue();
		}else {
			return 0;
		}
	}
	@Override
	public List<Product> getProductOfShop(int idShop,int status) {
		 String sql = "SELECT * FROM product WHERE idShop = ? AND statusProduct = ?";
		return jdbcTemplate.query(sql,new Object[] {idShop,status} ,new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {			
				return productVo(rs);
			}
			
		});
	}
	
	@Override
	public Blob getAvatarProduct(int idProduct) {
		 String sql = " SELECT avatarProduct FROM product WHERE idProduct = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct}, Blob.class);
	}
	@Override
	public int checkNameExist(String nameProduct, int idBrand, int idShop) {
		 String sql ="SELECT COUNT(*) FROM product WHERE nameProduct = ? AND idBrand = ? AND idShop = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {nameProduct,idBrand,idShop}, Integer.class);
	}

	

	@Override
	public List<Product> searchProduct(int idShop, int idBrand, int idType, String keySearch , int statusProduct) {
		String sql ;
		Object[] object = new Object[] {};
		
		sql = "SELECT * FROM product LEFT JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE idShop = ? AND statusProduct = ? AND productbrands.idType = ? AND product.idBrand = ? AND product.nameproduct like '%' ? '%'";
		object =  new Object[] {idShop,statusProduct,idType,idBrand,keySearch};
		
		if(idType == -1 && idBrand == -1 ) {
			sql =  "SELECT * FROM product WHERE idShop = ?  AND statusProduct = ? AND  nameproduct like '%' ? '%'";
			object = new Object[] {idShop,statusProduct,keySearch};
		}
		
		if(idType != -1 && idBrand == -1) {
			sql = "SELECT * FROM product LEFT JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE idShop = ? AND statusProduct = ? AND productbrands.idType = ?  AND product.nameproduct like '%' ? '%'";
			object =  new Object[] {idShop,statusProduct,idType,keySearch};
		}
		
		return jdbcTemplate.query(sql,object,new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				return productVo(rs);
			}
			
		});
	}
	@Override
	public List<Product> getAllProduct(int status) {
		 String sql = "SELECT * FROM product WHERE  statusProduct = ?";
			return jdbcTemplate.query(sql,new Object[] {status} ,new RowMapper<Product>() {
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					return productVo(rs);
				}				
			});	
	}
	@Override
	public List<Product> getListProduct(int status, int idType) {
		 String sql = "	SELECT * FROM product  JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE statusProduct = ? AND productbrands.idType = ? ";
			return jdbcTemplate.query(sql,new Object[] {status,idType} ,new RowMapper<Product>() {
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					return productVo(rs);
				}				
			});	
	}
	@Override
	public List<Product> getProductLowToHight(int status, int idType) {
		String sql = "SELECT * FROM product JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE  statusProduct = ? AND idType = ? ORDER BY price ASC";
		return jdbcTemplate.query(sql,new Object[] {status,idType} ,new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				return productVo(rs);
			}				
		});	
	}
	@Override
	public List<Product> getProductHightToLow(int status, int idType) {
		String sql = "SELECT * FROM product JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE  statusProduct = ? AND idType = ? ORDER BY price DESC";
		return jdbcTemplate.query(sql,new Object[] {status,idType} ,new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				return productVo(rs);
			}				
		});	
	}
	@Override
	public List<Product> getAllProductLowToHight(int status) {
		 String sql = "SELECT * FROM product WHERE  statusProduct = ? ORDER BY price ASC";
			return jdbcTemplate.query(sql,new Object[] {status} ,new RowMapper<Product>() {
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					return productVo(rs);
				}				
			});	
	}
	@Override
	public List<Product> getAllProductHightToLow(int status) {
		 String sql = "SELECT * FROM product WHERE  statusProduct = ? ORDER BY price DESC";
			return jdbcTemplate.query(sql,new Object[] {status} ,new RowMapper<Product>() {
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					return productVo(rs);
				}				
			});	
	}
	@Override
	public Product getProduct(int idProduct, int status) {
		String sql = "SELECT * FROM product WHERE idProduct = ? AND statusProduct = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,status}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				return productVo(rs);
			}
			
		});
	}
	@Override
	public int countProductType(int idType, int status) {
		String sql ;
		Object[] object= new Object[] {};
		sql ="SELECT count(*) FROM product JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE  statusProduct = ? AND idType = ?";
		object = new Object[] {status,idType};
		if(idType==0) {
			sql = "SELECT count(*) FROM product JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE  statusProduct = ?";
			object = new Object[] {status};
		}
		return jdbcTemplate.queryForObject(sql, object, Integer.class);
	}

	private Product productVo(ResultSet rs) throws SQLException {
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
		product.setRanking(rs.getInt("ranking"));
		product.setPrice(rs.getFloat("price"));
		return product;
	}
	@Override
	public int getType(int idProduct, int Status) {
		String sql = "SELECT idType FROM product JOIN productbrands ON product.idBrand = productbrands.idBrand WHERE idProduct = ? AND statusProduct = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,Status},Integer.class);
	}
	@Override
	public String getNameProduct(int idProduct, int status) {
		String sql = "SELECT nameProduct FROM product WHERE idProduct =? AND statusProduct = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,status}, String.class);
	}
	
	@Override
	public float getPriceProduct(int idProduct, int status) {
		String sql = "SELECT price FROM product WHERE idProduct =? AND statusProduct = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,status}, Float.class);
	}
	@Override
	public int getNumberProduct(int idProduct, int status) {
		String sql = "SELECT numberProduct FROM product WHERE idProduct =? AND statusProduct = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,status}, Integer.class);
	}
	@Override
	public int updateProduct(int idProduct , int number) {
		String sql = "UPDATE product SET numberProduct = numberProduct - ?,sold = sold + ? WHERE idProduct = ?";
		
		return jdbcTemplate.update(sql,number,number,idProduct);
	}

	@Override
	public int changeStatusProduct(int idProduct, int status) {
		String sql = "UPDATE product SET statusProduct = ? WHERE idProduct = ?";
		return jdbcTemplate.update(sql,status,idProduct);
	}

	@Override
	public List<Product> findProduct(int idBrand) {
		String sql = "SELECT * FROM product WHERE  idBrand = ?";
		return jdbcTemplate.query(sql,new Object[] {idBrand} ,new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				return productVo(rs);
			}				
		});	
	}

	@Override
	public List<Product> AdminSearchProduct(int idBrand, int idType, String keySearch, int statusProduct) {
		String sql ;
		Object[] object = new Object[] {};
		
		sql = "SELECT * FROM product LEFT JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE  statusProduct = ? AND productbrands.idType = ? AND product.idBrand = ? AND product.nameproduct like '%' ? '%'";
		object =  new Object[] {statusProduct,idType,idBrand,keySearch};
		
		if(idType == -1 && idBrand == -1 ) {
			sql =  "SELECT * FROM product WHERE statusProduct = ? AND  nameproduct like '%' ? '%'";
			object = new Object[] {statusProduct,keySearch};
		}
		
		if(idType != -1 && idBrand == -1) {
			sql = "SELECT * FROM product LEFT JOIN productbrands ON product.idBrand = productbrands.idBrand  WHERE statusProduct = ? AND productbrands.idType = ?  AND product.nameproduct like '%' ? '%'";
			object =  new Object[] {statusProduct,idType,keySearch};
		}
		
		return jdbcTemplate.query(sql,object,new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				return productVo(rs);
			}
			
		});
	}
	
}

