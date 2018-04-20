package DAO.Items;

import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Item;
import ModelManagedBeans.Items.Movie;
import Utils.CommonUtils;
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

    /**
     * handels adding movie for sale
     * @param item - item specs - without movie specs id
     * @param sellerId - seller id
     * @return - item id
     * @throws SQLException
     */
    @Override
    public int addItemForSale(Item item, int sellerId) throws SQLException {
        this.getDbManager().Connect();
        item.setMovieSpecs(this.addMovieSpecs((Movie) item));
        return this.addItemForSale(item, sellerId,this.getDbManager().getConnection());
    }

    /**
     * handles adding movie specs
     * @param movie - movie object
     * @return - movie specs id
     * @throws SQLException
     */
    private int addMovieSpecs(Movie movie) throws SQLException {
        this.getDbManager().Connect();
        String sql = "INSERT INTO dreamdb.movie_specs(director, length, year,actor,age_lvl,genre) " +
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
                return generatedKey.getInt(1);
            } else {
                return -1;
            }
    }

    /**
     * loads movie for sale
     * @param id - id of the movie specs
     * @return - movie object
     * @throws SQLException
     */
    public Movie loadMovieForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "SELECT * FROM dreamdb.movie_specs WHERE id = ?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Movie movie = new Movie(
                    resultSet.getInt("id"),
                    resultSet.getInt("director"),//director
                    resultSet.getInt("length"),//length
                    resultSet.getInt("year"),//year
                    resultSet.getInt("age_lvl"),//age_lvl
                    resultSet.getInt("actor"),//actor
                    resultSet.getInt("genre"),//genre
                    CommonUtils.getConstLists("dreamdb.directors", "name"),
                    CommonUtils.getConstLists("dreamdb.actors", "name"),
                    CommonUtils.getConstLists("dreamdb.age_lvl", "age_lvl"),
                    CommonUtils.getConstLists("dreamdb.genres", "genre")
            );
            this.getDbManager().Disconnect();
            return movie;
        }
        this.getDbManager().Disconnect();
        return null;
    }

    /**
     * handles updating movie specs
     * @param movie - movie object with new attributes
     * @throws SQLException
     */
    public void updateMovieSpecs(Movie movie) throws SQLException {
        this.getDbManager().Connect();
        String sql = "UPDATE dreamdb.movie_specs SET director=?, length=?, year=?,actor=?,age_lvl=?,genre=?" +
                " WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, movie.getDirector());
        preparedStatement.setInt(2, movie.getLength());
        preparedStatement.setInt(3, movie.getYear());
        preparedStatement.setInt(4, movie.getKnownActor());
        preparedStatement.setInt(5, movie.getAgeLvl());
        preparedStatement.setInt(6, movie.getGenre());
        preparedStatement.setInt(7, movie.getId());
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }

    /**
     * handles deleting movie specs
     * @param id - id of movie specs
     * @throws SQLException
     */
    public void deleteMovieForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "DELETE FROM dreamdb.movie_specs WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }
}

