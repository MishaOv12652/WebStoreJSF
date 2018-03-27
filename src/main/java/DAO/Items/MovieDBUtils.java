package DAO.Items;

import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Items.Movie;
import Utils.DBManager;
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
}

