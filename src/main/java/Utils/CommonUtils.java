package Utils;

import java.sql.*;
import java.util.*;

/**
 * Created by Misha on 24/02/2018.
 */
public class CommonUtils {


    public static int getKeyByValue(Hashtable<Integer, String> hash, String value) {
        Set set = hash.entrySet();

        for (Object aSet : set) {
            Map.Entry entry = (Map.Entry) aSet;
            if (Objects.equals(entry.getValue(), value)) {
                return (Integer) entry.getKey();
            }
        }
        return -1;
    }

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

    public static int getUserIdByEmail(String email) {
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT id FROM dreambuy.user WHERE email='" + email + "'";
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

    public static String getEmailByUserId(Integer id) {
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT email FROM dreambuy.user WHERE id='" + id + "'";
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

    public static int getSellerIdByItemId(int id){
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT seller_id FROM dreambuy.products WHERE id='" + id+ "'";
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
    public static String getValueByKeyFromHash(Hashtable<Integer,String>hashtable,String key){
        return hashtable.get(Integer.parseInt(key));
    }

    public static int getWishListIdByBuyerId(int buyerId){
        DBManager manager = new DBManager();
        manager.Connect();
        try {
            Connection con = manager.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT id FROM dreambuy.wish_lists WHERE buyer_id='" + buyerId+ "'";
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
