package DAO.Items;

import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Items.Movie;
import Utils.DBManager;
import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;

/**
 * Created by Misha on 3/25/2018.
 */
@Getter
@Setter
public class MovieDBUtils extends ItemDBUtils {

    public MovieDBUtils() {
        super();
    }

    @Override
    public int addItemForSale(Item item, int sellerId) throws SQLException {
        this.getDbManager().Connect();
        item.setMovieSpecs(this.addMovieSpecs((Movie) item));
        return this.addItemForSale(item, sellerId,this.getDbManager().getConnection());
    }

    private int addMovieSpecs(Movie movie) throws SQLException {
        this.getDbManager().Connect();
        String sql = "INSERT INTO dreambuy.movie_specs(director, length, year,actor,age_lvl,genre) " +
                "VALUES (?,?,?,?,?,?)";

            Connection connection = this.getDbManager().getConnection();
            PreparedStatement prpStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prpStmt.setInt(1, movie.getDirector());
            prpStmt.setInt(2, movie.getLength());
            prpStmt.setInt(3, movie.getYear());
            prpStmt.setInt(4, movie.getKnownActor());
            prpStmt.setInt(5, movie.getAgeLvl());
            prpStmt.setInt(6, movie.getGenre());
            prpStmt.execute();

            ResultSet generatedKey = prpStmt.getGeneratedKeys();
            if (generatedKey.next()) {
                // generatedKey.next();
                return generatedKey.getInt(1);
            } else {
                //throw new SQLException("add book failed");
                return -1;
            }
    }

    public Movie loadMovieForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "SELECT * FROM dreambuy.movie_specs WHERE id = ?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Movie movie = new Movie(
                    resultSet.getInt("director"),//director
                    resultSet.getInt("length"),//length
                    resultSet.getInt("year"),//year
                    resultSet.getInt("actor"),//age_lvl
                    resultSet.getInt("age_lvl"),//actor
                    resultSet.getInt("genre")//genre

            );
            this.getDbManager().Disconnect();
            return movie;
        }
        this.getDbManager().Disconnect();
        return null;
    }
}

