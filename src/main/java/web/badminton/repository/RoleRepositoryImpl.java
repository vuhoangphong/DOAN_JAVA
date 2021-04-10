package web.badminton.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import web.badminton.modal.Role;



@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getListRole(String account) {
        String sql = "SELECT nameRole"
                    +" FROM user_role"
                    +" INNER JOIN role ON user_role.idRole = role.idRole"
                    +" INNER JOIN user ON user_role.idUser = user.idUser"
                    +" WHERE account = ?";
        List<String> data = jdbcTemplate.query(sql, new Object[] {account},new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
            }
        });
    return data;
    }

    @Override
    public int insertRole(int idUser , int role) {
        String sql = "INSERT INTO user_role(idRole,idUser) VALUES(?,?)";
        return jdbcTemplate.update(sql, role,idUser);
    }
}