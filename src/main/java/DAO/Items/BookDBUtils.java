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
    private DBManager dbManager;

    public BookDBUtils() {
        super();
        //this.dbManager = new DBManager();
    }


    //add a book
//    public int addBookForSale(Book book, int sellerId) throws SQLException {
//        book.setItemSpecs(this.addBookSpecs(book));
//        return this.addItemForSale(book, sellerId);
//    }
    @Override
    public int addItemForSale(Item item,int sellerId) throws SQLException {
        this.dbManager.Connect();
        item.setItemSpecs(this.addBookSpecs((Book)item));
        return this.addItemForSale((Book) item, sellerId,this.dbManager.getConnection());
    }

    private int addBookSpecs(Book book) throws SQLException {
        this.dbManager.Connect();
        String sql = "INSERT INTO dreambuy.books_specs(genre, series, age_lvl, author) " +
                "VALUES (?,?,?,?)";
        try {
            Connection connection = dbManager.getConnection();
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
        } finally {
            dbManager.Disconnect();
        }
    }
}
