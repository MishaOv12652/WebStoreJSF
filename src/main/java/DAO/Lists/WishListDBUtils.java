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

    /**
     * loads wish list by email of buyer
     * @param email - email of buyer
     * @return - array list of items in wish list of the buyer
     * @throws SQLException
     */
    public ArrayList<Item> loadWishList(String email) throws SQLException {
        ArrayList<Item> arrayOfItems = new ArrayList<>();
        String sql = "SELECT * FROM dreamdb.products AS allP INNER JOIN dreamdb.wish_list_products AS" +
                " wishP ON allP.id=wishP.product_id WHERE wishP.wish_list_id=?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, this.getWishListIdByBuyerId(CommonUtils.getUserIdByEmail(email)));
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

    /**
     * handles adding item to wish list
     * @param email - email of buyer
     * @param itemId - item id to add to wish list
     * @throws SQLException
     */
    public void addItemToWishList(String email,int itemId) throws SQLException {
        String sql = "INSERT INTO dreamdb.wish_list_products SET wish_list_id=?,product_id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,this.getWishListIdByBuyerId(CommonUtils.getUserIdByEmail(email)));
        preparedStatement.setInt(2,itemId);
        preparedStatement.execute();
        this.dbManager.Disconnect();
    }

    /**
     * handles removing item from wish list by id
     * @param itemId - item id
     * @throws SQLException
     */
    public void removeItemFromWishList(int itemId) throws SQLException {
        String sql = "DELETE FROM dreamdb.wish_list_products WHERE product_id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,itemId);
        preparedStatement.execute();
        this.dbManager.Disconnect();

    }

    /**
     * return a wish list id by buyer id
     * @param buyerId - id of the buyer that owns the wish list
     * @return - id of wish list
     */
    private  int getWishListIdByBuyerId(int buyerId){
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT id FROM dreamdb.wish_lists WHERE buyer_id='" + buyerId+ "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("id");
            }
            manager.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            manager.Disconnect();
            return -1;
        }
        manager.Disconnect();
        return -1;
    }
}
