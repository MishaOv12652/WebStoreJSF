package DAO.Items;

import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.Item;
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

    @Override
    public int addItemForSale(Item item,int sellerId) throws SQLException {
        this.getDbManager().Connect();
        item.setBookSpecs(this.addBookSpecs((Book)item));
        return this.addItemForSale(item, sellerId,this.getDbManager().getConnection());
    }

    private int addBookSpecs(Book book) throws SQLException {
        this.getDbManager().Connect();
        String sql = "INSERT INTO dreambuy.books_specs(genre, series, age_lvl, author) " +
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

    public Book loadBooksForSale(Integer id) throws SQLException{
        if(id != null){
            this.getDbManager().Connect();
            String sql = "SELECT * FROM dreambuy.books_specs where id = ?";
            Connection connection = this.getDbManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Book(
                        resultSet.getInt("author"),
                        resultSet.getInt("genre"),
                        resultSet.getString("series"),
                        resultSet.getInt("age_lvl")
                );
            }
        }
        return null;
    }
}
