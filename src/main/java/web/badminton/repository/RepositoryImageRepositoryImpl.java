package web.badminton.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import web.badminton.vo.ReponsitoryImage;

@Repository
public class RepositoryImageRepositoryImpl implements RepositoryImageRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Override
	public int addImageProduct(int idProduct, List<MultipartFile> listImage) {
		for (MultipartFile image : listImage) {
			try {
				byte[] byteImage = image.getBytes() ;
				if(byteImage.length > 0 && byteImage != null ) {
					String sql = "INSERT INTO reponsitoryImage (idProduct,imageProduct) VALUES (?,?)";		
					jdbcTemplate.update(sql,new Object[] {idProduct,byteImage});
				}else {
					continue;
				}				
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}				
		}				
		return 1;
	}
	@Override
	public Blob getImage(int idProduct,int idImage) {
		String  sql = "SELECT imageProduct  FROM reponsitoryImage WHERE idproduct = ? AND idImage = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idProduct,idImage}, Blob.class);
	}
	@Override
	public List<ReponsitoryImage> getListIdImage(int idProduct) {
		String  sql = "SELECT *  FROM reponsitoryImage WHERE idproduct = ?";
		return jdbcTemplate.query(sql, new Object[] {idProduct}, new RowMapper<ReponsitoryImage>() {

			@Override
			public ReponsitoryImage mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReponsitoryImage reponsitoryImage = new ReponsitoryImage();
				reponsitoryImage.setIdImage(rs.getInt("idImage"));
				reponsitoryImage.setIdProduct(rs.getInt("idProduct"));
				return reponsitoryImage;
			}
			
		});
	}
	
}


