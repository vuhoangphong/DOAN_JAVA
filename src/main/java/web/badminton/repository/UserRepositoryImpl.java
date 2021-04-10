package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

import web.badminton.dto.RegisterUserDTO;
import web.badminton.dto.UserDTO;
import web.badminton.vo.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	

	@Override
	public int checkUserExist(String account) {
		String sql = "SELECT COUNT(*) FROM user WHERE account = ? ";
		return jdbcTemplate.queryForObject(sql,new  Object[] {account}, Integer.class);
	}
	
	@Override
	public String getName(String account, String password) {
		String sql = "SELECT fullName FROM user WHERE account = ? AND passWord = ? ";
		return jdbcTemplate.queryForObject(sql,new  Object[] {account, password}, String.class);
	}

	@Override
	public User getUser(String account) {
		String sql = "SELECT * FROM user WHERE account = ? AND statusUser = 1";
		return jdbcTemplate.queryForObject(sql,new Object[] {account}, new RowMapper<User>() {
			@Override
		    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        return SetUser(rs);

		    }
		});
	}
	
	@Override
	public int insertUser(RegisterUserDTO register) {
		String sql = "INSERT INTO  user (fullName, account, password, email, phoneNumber,sex,dateCreate,statusUser) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,register.getFullName(),register.getAccount(),register.getPassword(),register.getEmail(),register.getPhoneNumber(),register.getSex(),LocalDateTime.now(),0);
			  
	}

	@Override
	public int checkAccountExist(RegisterUserDTO register) {
		String sql = "SELECT COUNT(*) FROM user WHERE account = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {register.getAccount() }, Integer.class);
	}

	@Override
	public int updateUser(RegisterUserDTO register, String address, int idUser, MultipartFile imageUpload) {
		try {
			byte[] bytesImage = imageUpload.getBytes();
			String sql = "UPDATE user SET fullName = ?, email = ?, phoneNumber = ?, address = ?, sex = ? , avatar = ? WHERE idUser = ?";
			return jdbcTemplate.update(sql,register.getFullName(),register.getEmail(),register.getPhoneNumber(),address,register.getSex(),bytesImage,idUser);
		} catch (Exception e) {
			return 0 ;
		}
		
	}

	@Override
	public Blob getAvatarUser(int idUser) {
		String sql = "SELECT avatar FROM user WHERE idUser = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {idUser} , Blob.class);
	}

	@Override
	public int UpdateUserPayment(UserDTO user,int idUser) {
		String sql = "UPDATE user SET fullName = ?, email = ?, phoneNumber = ?, address = ? WHERE idUser = ?";
		return jdbcTemplate.update(sql,user.getFullName(),user.getEmail(),user.getPhoneNumber(),user.getAddress(),idUser);
	}

	@Override
	public User findByName(String account) {
		String sql="SELECT   account , password FROM user u INNER JOIN user_role ur ON u.idUser = ur.idUser INNER JOIN role r on ur.idRole = r.idRole WHERE account = ?  AND statusUser != 0 AND statusUser != 2 GROUP BY account  ";	
		return jdbcTemplate.queryForObject(sql, new Object[]{account},new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setPassWord(rs.getString("passWord"));
				return user;
			}
			
		});
	}

	private User SetUser(ResultSet rs) throws SQLException {
		User user = new User();
				user.setIdUser(rs.getInt("idUser"));
				user.setFullName(rs.getString("fullName"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setAddress(rs.getString("address"));
				user.setSex(rs.getByte("sex"));
				user.setAvatar(rs.getBytes("avatar"));
				user.setDateCreate(rs.getDate("dateCreate"));
				user.setAccount(rs.getString("account"));
				user.setPassWord(rs.getString("passWord"));
				user.setStatusUser(rs.getInt("statusUser"));
		        return user;
	}

	@Override
	public int verifyUser( String status,String account) {
	String sql = "UPDATE user SET statusUser = ? WHERE account = ?";
		return jdbcTemplate.update(sql,status,account);
	}

	@Override
	public int getIdUser(String account) {
		String sql = "SELECT idUser FROM user WHERE account = ?";
		return  jdbcTemplate.queryForObject(sql,new Object[]{account},Integer.class);
	}

	@Override
	public List<User> getAllUser(int status) {
		String sql = "SELECT * FROM user WHERE statusUser = ?";
		return jdbcTemplate.query(sql,new Object[]{status},new BeanPropertyRowMapper(User.class));
	}



	@Override
	public int updateStatusUser(String idUser, String status) {
		String sql = "UPDATE user SET statusUser = ? WHERE idUser = ?";
		return jdbcTemplate.update(sql,status,idUser);
	}


	
}
