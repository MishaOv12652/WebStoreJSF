package DAO.Lists;

import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.DBManager;

import java.sql.*;
import java.util.Hashtable;

/**
 * Created by Misha on 4/7/2018.
 */
public class ShoppingCartDBUtils {

    private DBManager dbManager;

    public ShoppingCartDBUtils(){
        this.dbManager = new DBManager();
    }

    //add item to cart
    public void addItemToCart(String email,int itemId,int numOfItemsToBuy) throws SQLException {
        String sql = "INSERT INTO dreamdb.shopping_cart_products SET product_id=?,shopping_cart_id=?,buyAmount=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,itemId);
        preparedStatement.setInt(2, this.getShoppingCartIdByBuyerId(CommonUtils.getUserIdByEmail(email),connection));
        preparedStatement.setInt(3,numOfItemsToBuy);
        preparedStatement.execute();
        this.dbManager.Disconnect();
    }
    //update item in cart

    //remove item from cart
    public void removeItemFromCart(String email,int itemId) throws SQLException {
        String sql = "DELETE FROM dreamdb.shopping_cart_products WHERE product_id=? AND shopping_cart_id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,itemId);
        preparedStatement.setInt(2, this.getShoppingCartIdByBuyerId(CommonUtils.getUserIdByEmail(email),connection));
        preparedStatement.execute();
        this.dbManager.Disconnect();
    }
    //load cart
    public Hashtable<Item,Integer> loadShoppingCart(String email) throws SQLException {
        Hashtable<Item,Integer> itemsInCart = new Hashtable<>();
        String sql = "SELECT * FROM dreamdb.products AS allP INNER JOIN dreamdb.shopping_cart_products AS" +
                " shop ON allP.id=shop.product_id WHERE shop.shopping_cart_id=?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, this.getShoppingCartIdByBuyerId(CommonUtils.getUserIdByEmail(email),con));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            itemsInCart.put(new Item(
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
            ),resultSet.getInt("buyAmount"));
        }
        this.dbManager.Disconnect();
        return itemsInCart;
    }

    protected int getShoppingCartIdByBuyerId(int buyerId,Connection connection){
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM dreamdb.shopping_carts WHERE buyer_id='" + buyerId+ "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }
}
