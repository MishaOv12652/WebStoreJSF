package DAO.Items;

import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.DBManager;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Misha on 24/03/2018.
 */
@Getter
@Setter
public class ItemDBUtils {
    private DBManager dbManager;

    public ItemDBUtils() {
        this.dbManager = new DBManager();
    }

    /**
     * @param item
     * @param idOfUser
     * @return
     * @throws SQLException
     */
    public int addItemForSale(Item item, int idOfUser) throws SQLException {
        this.dbManager.Connect();
        return this.addItemForSale(item, idOfUser, this.dbManager.getConnection());
    }

    public ArrayList<Item> loadItemListForSale(String email) throws SQLException {
        ArrayList<Item> arrayOfItems = new ArrayList<>();
        String query = "SELECT * FROM dreambuy.products WHERE seller_id = ?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, CommonUtils.getUserIdByEmail(email));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayOfItems.add(new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getString("item_desc"),
                    resultSet.getInt("category"),
                    resultSet.getInt("condition_id"),
                    null,
//                    resultSet.getBlob("img"),
                    resultSet.getInt("numOfItems"),
                    resultSet.getInt("book_spec_id"),
                    resultSet.getInt("movie_spec_id"),
                    resultSet.getInt("cellphone_spec_id"),
                    resultSet.getInt("computer_spec_id")
            ));
        }
        return arrayOfItems;
    }

    public Item loadItemForSale(int id) throws SQLException {
        String query = "SELECT * FROM dreambuy.products WHERE id = ?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Item item = new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getString("item_desc"),
                    resultSet.getInt("category"),
                    resultSet.getInt("condition_id"),
                    null,
//                    resultSet.getBlob("img"),
                    resultSet.getInt("numOfItems"),
                    resultSet.getInt("book_spec_id"),
                    resultSet.getInt("movie_spec_id"),
                    resultSet.getInt("cellphone_spec_id"),
                    resultSet.getInt("computer_spec_id")
            );
            this.dbManager.Disconnect();
            return item;
        }
        this.dbManager.Disconnect();
        return null;

    }

    protected int addItemForSale(Item item, int idOfUser, Connection con) throws SQLException {
        String sql = "INSERT INTO dreambuy.products(name, price, item_desc, category, condition_id, seller_id,numOfItems,book_spec_id,cellphone_spec_id,computer_spec_id,movie_spec_id)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setFloat(2, item.getPrice());
            stmt.setString(3, item.getItemDesc());
            stmt.setInt(4, item.getCategory());
            stmt.setInt(5, item.getCondition());
            //stmt.setBlob(6, item.getUploadedFile());
            stmt.setInt(6, idOfUser);
            stmt.setInt(7, item.getNumOfItems());
            stmt.setObject(8, item.getBookSpecs());
            stmt.setObject(9, item.getCellSpecs());
            stmt.setObject(10, item.getCompSpecs());
            stmt.setObject(11, item.getMovieSpecs());

            stmt.execute();
            ResultSet generatedKey = stmt.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1);
            } else {
                return -1;
            }
        } finally {
            this.dbManager.Disconnect();
        }
    }
}
