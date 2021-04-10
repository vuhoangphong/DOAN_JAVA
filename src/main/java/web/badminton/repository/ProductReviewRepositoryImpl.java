package web.badminton.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.conversion.DbAction.WithPropertyPath;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import web.badminton.vo.ProductReview;

@Repository
public class ProductReviewRepositoryImpl implements ProductReviewRepository {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public int addComment(int rank, int idUser, int idProduct, String review) {
        String sql = "INSERT INTO productreview(ranking,review,idUser,idProduct,reviewDate) VALUES(?,?,?,?,now())";
        return jdbc.update(sql, new Object[] { rank, review, idUser, idProduct });
    }

    @Override
    public List<ProductReview> getReviews(int idProduct) {
        String sql = "SELECT idproductreview,ranking,review,user.idUser,idProduct,fullName,reviewDate FROM productreview  INNER JOIN user ON productreview.idUser = user.idUser WHERE idProduct = ?";
        return jdbc.query(sql, new Object[] { idProduct }, new RowMapper<ProductReview>() {

            @Override
            public ProductReview mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProductReview product = new ProductReview();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setIdUser(rs.getInt("idUser"));
                product.setRanking(rs.getInt("ranking"));
                product.setReview(rs.getString("review"));
                product.setFullName(rs.getString("fullName"));
                product.setReviewDate(rs.getDate("reviewDate"));
                return product;
            }
        });
    }

    @Override
    public List<Integer> getAllRank(int idProduct) {
        List<Integer> rank = new ArrayList<>();
     SimpleJdbcCall call =new SimpleJdbcCall(jdbc)
        .withProcedureName("get_all_rank")
            .declareParameters( 
                new SqlParameter("id", Types.INTEGER),
                new SqlOutParameter("rank1",Types.INTEGER ),
                new SqlOutParameter("rank2",Types.INTEGER ),
                new SqlOutParameter("rank3",Types.INTEGER ),
                new SqlOutParameter("rank4",Types.INTEGER ),
                new SqlOutParameter("rank5",Types.INTEGER )
            );
            Map<String, Object> execute = call.execute(new MapSqlParameterSource("id", idProduct));
            int sumStart = -1;
            List<Integer> temp = new ArrayList<>();
             // get and add rank 1 to rank 5
            for(Map.Entry<String,Object> e :execute.entrySet()){ 
                temp.add((Integer) e.getValue());
               sumStart+=(Integer) e.getValue();
            }
            //calculate the percentage
           for(int i = 1 ; i<temp.size();i++){
               rank.add((temp.get(i)*100)/sumStart);
           }
           return rank;
    }

    @Override
    public List<ProductReview> findRank(int rank) {
        String sql ="SELECT * FROM productreview WHERE ranking = ? ";
        String sqlAll ="SELECT * FROM productreview";
        if(rank != 0 )
            return jdbc.query(sql,new Object[]{rank},new BeanPropertyRowMapper(ProductReview.class));
        return jdbc.query(sqlAll,new BeanPropertyRowMapper(ProductReview.class));
    }
     
}
