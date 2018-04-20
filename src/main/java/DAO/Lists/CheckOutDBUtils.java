package DAO.Lists;

import ModelManagedBeans.Items.Item;
import Utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by Misha on 4/7/2018.
 */
public class CheckOutDBUtils {
    private DBManager dbManager;

    public CheckOutDBUtils(){
        this.dbManager = new DBManager();
    }

    /**
     * confirms payment fom shopping cart
     * @param items - hash table of items and number of items to buy
     * @param userId - buyer id
     * @throws SQLException
     */
    public void confirmPayment(Hashtable<Item,Integer> items,int userId) throws SQLException {
        // TODO: 4/7/2018 need to handle when numof items is less then num of items to buy
        String sql = "UPDATE dreamdb.products SET numOfItems = numOfItems - ? WHERE id=?";
        ShoppingCartDBUtils shoppingCartDBUtils = new ShoppingCartDBUtils();
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        int shoppingCartId = shoppingCartDBUtils.getShoppingCartIdByBuyerId(userId,connection);
        Set<Item> keys = items.keySet();
        for (Item key : keys) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,items.get(key));
            preparedStatement.setInt(2,key.getId());
            preparedStatement.execute();
            String deleteFromCart = "DELETE FROM dreamdb.shopping_cart_products WHERE product_id=? AND shopping_cart_id=?";
            preparedStatement = connection.prepareStatement(deleteFromCart);
            preparedStatement.setInt(1,key.getId());
            preparedStatement.setInt(2,shoppingCartId);
            preparedStatement.execute();
        }
        this.dbManager.Disconnect();

    }
    //load checkout

}
