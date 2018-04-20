package DAO.Items;

import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.DBManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;

/**
 * Created by Misha on 3/25/2018.
 */
@Getter
@Setter
public class BookDBUtils extends ItemDBUtils {

    public BookDBUtils() {
        super();
    }

    /**
     * loads book specs for sale by its id
     * @param id - id of book specs
     * @return - a book object
     * @throws SQLException
     */
    public Book loadBooksForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "SELECT * FROM dreamdb.books_specs WHERE id = ?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Book book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getInt("author"),
                    resultSet.getInt("genre"),
                    resultSet.getString("series"),
                    resultSet.getInt("age_lvl"),
                    CommonUtils.getConstLists("dreamdb.known_authers", "auther_name"),
                    CommonUtils.getConstLists("dreamdb.genres", "genre"),
                    CommonUtils.getConstLists("dreamdb.age_lvl", "age_lvl")
            );
            this.getDbManager().Disconnect();
            return book;
        }
        this.getDbManager().Disconnect();
        return null;
    }

    /**
     *  handles adding a book for sale
     * @param item - item specs - without book specs id
     * @param sellerId - ther seller id
     * @return - item id
     * @throws SQLException
     */
    @Override
    public int addItemForSale(Item item, int sellerId) throws SQLException {
        this.getDbManager().Connect();
        item.setBookSpecs(this.addBookSpecs((Book) item));
        return this.addItemForSale(item, sellerId, this.getDbManager().getConnection());
    }

    /**
     * handles updating book specs
     * @param book - book object with attributes to update
     * @throws SQLException
     */
    public void updateBookSpecs(Book book) throws SQLException {
        this.getDbManager().Connect();
        String sql = "UPDATE dreamdb.books_specs SET genre=?, series=?, age_lvl=?, author=? WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, book.getGenre());
        preparedStatement.setString(2, book.getSeries());
        preparedStatement.setInt(3, book.getAgeLvl());
        preparedStatement.setInt(4, book.getAuthor());
        preparedStatement.setInt(5, book.getId());
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }

    /**
     * handles deleting book specs
     * @param id - id of the book specs
     * @throws SQLException
     */
    public void deleteBookForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "DELETE FROM dreamdb.books_specs WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }

    /**
     * handels adding book specs - for add item
     * @param book - book object to add
     * @return - book specs id
     * @throws SQLException
     */
    private int addBookSpecs(Book book) throws SQLException {
        this.getDbManager().Connect();
        String sql = "INSERT INTO dreamdb.books_specs(genre, series, age_lvl, author) " +
                "VALUES (?,?,?,?)";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, book.getGenre());
        preparedStatement.setString(2, book.getSeries());
        preparedStatement.setInt(3, book.getAgeLvl());
        preparedStatement.setInt(4, book.getAuthor());
        preparedStatement.execute();
        ResultSet generatedKey = preparedStatement.getGeneratedKeys();
        if (generatedKey.next()) {
            return generatedKey.getInt(1);
        } else {
            return -1;
        }
    }


}
