package DAO.Lists;

import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.DBManager;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Misha on 4/8/2018.
 */
public class WishListDBUtils {
    private DBManager dbManager;
    public WishListDBUtils(){
        this.dbManager = new DBManager();
    }
    public ArrayList<Item> loadWishList(String email) throws SQLException {
        ArrayList<Item> arrayOfItems = new ArrayList<>();
        String sql = "SELECT * FROM dreamdb.products AS allP INNER JOIN dreamdb.wish_list_products AS" +
                " wishP ON allP.id=wishP.product_id WHERE wishP.wish_list_id=?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, CommonUtils.getWishListIdByBuyerId(CommonUtils.getUserIdByEmail(email)));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayOfItems.add(new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getString("item_desc"),
                    resultSet.getInt("category"),
                    resultSet.getInt("condition_id"),
                    resultSet.getFloat("shippingPrice"),
                    null,
//                    resultSet.getBlob("img"),
                    resultSet.getInt("numOfItems"),
                    resultSet.getInt("book_spec_id"),
                    resultSet.getInt("movie_spec_id"),
                    resultSet.getInt("cellphone_spec_id"),
                    resultSet.getInt("computer_spec_id"),
                    CommonUtils.getConstLists("dreamdb.product_condition", "condition"),
                    CommonUtils.getConstLists("dreamdb.categories", "category_name")
            ));
        }
        this.dbManager.Disconnect();
        return arrayOfItems;
    }

    public void addItemToWishList(String email,int itemId) throws SQLException {
        String sql = "INSERT INTO dreamdb.wish_list_products SET wish_list_id=?,product_id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,CommonUtils.getWishListIdByBuyerId(CommonUtils.getUserIdByEmail(email)));
        preparedStatement.setInt(2,itemId);
        preparedStatement.execute();
        this.dbManager.Disconnect();
    }

    public void removeItemFromWishList(int itemId) throws SQLException {
        String sql = "DELETE FROM dreamdb.wish_list_products WHERE product_id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,itemId);
        preparedStatement.execute();
        this.dbManager.Disconnect();

    }
}
