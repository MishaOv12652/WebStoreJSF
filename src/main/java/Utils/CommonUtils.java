package Utils;

import java.sql.*;
import java.util.*;

/**
 * Created by Misha on 24/02/2018.
 */
public class CommonUtils {


    /**
     * get Const list values by table name and column name
     * @param tableName
     * @param columnName
     * @return - a hastable of key and value of const
     */
    public static Hashtable<Integer, String> getConstLists(String tableName, String columnName) {
        DBManager manager = new DBManager();
        manager.Connect();
        Hashtable<Integer, String> map = new Hashtable<>();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String sql = "SELECT * From " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                map.put(rs.getInt("id"), rs.getString(columnName));
            }

        } catch (SQLException e) {
            e.getMessage();
            manager.Disconnect();
            return null;
        }
        manager.Disconnect();
        return map;
    }

    /**
     * gets user id by his eamil
     * @param email - email of the user
     * @return - the id of the user
     */
    public static int getUserIdByEmail(String email) {
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT id FROM dreamdb.account WHERE email='" + email + "'";
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

    /**
     * gets Email of the user by its id
     * @param id - id of the user
     * @return - email of the user
     */
    public static String getEmailByUserId(Integer id) {
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT email FROM dreamdb.account WHERE id='" + id + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("email");
            }
            manager.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            manager.Disconnect();
            return null;
        }
        manager.Disconnect();
        return null;
    }

    /**
     * gets seller if by item id his selling
     * @param id - item id the seller sells
     * @return - return the seller id
     */
    public static int getSellerIdByItemId(int id){
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT seller_id FROM dreamdb.products WHERE id='" + id+ "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("seller_id");
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

    /**
     * get the value from hash table by key
     * @param hashtable - hash table
     * @param key - key of the value
     * @return - the value
     */
    public static String getValueByKeyFromHash(Hashtable<Integer,String>hashtable,String key){
        return hashtable.get(Integer.parseInt(key));
    }


}
