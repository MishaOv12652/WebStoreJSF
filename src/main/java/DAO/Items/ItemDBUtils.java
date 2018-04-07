package DAO.Items;

import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.DBManager;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;

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

    public String loadImageOfItemByItemId(int itemId) throws SQLException {
        String sql = "SELECT img FROM dreamdb.products WHERE id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,itemId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("img");
        }
        return null;

    }

    /**
     * updates an item of the category other
     * @param item
     * @throws SQLException
     */
    public void updateItemForSale(Item item) throws SQLException {
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        this.updateItemForSale(item, connection);

    }

    /**
     * loads list of items the user with email sells
     * @param email - user email
     * @return - list of items the user with email sells
     * @throws SQLException -
     */
    public ArrayList<Item> loadItemListForSale(String email) throws SQLException {
        ArrayList<Item> arrayOfItems = new ArrayList<>();
        String query = "SELECT * FROM dreamdb.products WHERE seller_id = ?";
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
                    resultSet.getFloat("shippingPrice"),
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

    /**
     * loads
     * @param id
     * @return
     * @throws SQLException
     */
    public Item loadItemForSale(int id) throws SQLException {
        String query = "SELECT * FROM dreamdb.products WHERE id = ?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Item item = new Item(
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
            );
            this.dbManager.Disconnect();
            return item;
        }
        this.dbManager.Disconnect();
        return null;

    }

    protected int addItemForSale(Item item, int idOfUser, Connection con) throws SQLException {
        String sql = "INSERT INTO dreamdb.products(name, price, item_desc, category, condition_id, seller_id,numOfItems,book_spec_id,cellphone_spec_id,computer_spec_id,movie_spec_id,shippingPrice)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
            stmt.setFloat(12, item.getShippingPrice());
            stmt.execute();

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if (generatedKey.next()) {
                int idOfItem = generatedKey.getInt(1);
                sql = "UPDATE dreamdb.products SET img=? WHERE id=?";
                String extension = FilenameUtils.getExtension(item.getImg().getSubmittedFileName());
                stmt = con.prepareStatement(sql);
                stmt.setString(1,"../resources/imageUploads/" + CommonUtils.getEmailByUserId(idOfUser) + "-" + idOfItem + "." + extension);
                stmt.setInt(2,idOfItem);
                stmt.execute();
                return idOfItem;
            } else {
                return -1;
            }
        } finally {
            this.dbManager.Disconnect();
        }
    }

    protected void updateItemForSale(Item item, Connection connection) throws SQLException {
        String sql = "UPDATE dreamdb.products SET name=?, price=?, item_desc=?, category=?, condition_id=?," +
                "numOfItems=?,book_spec_id=?,cellphone_spec_id=?,computer_spec_id=?,movie_spec_id=?,shippingPrice=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, item.getName());
        stmt.setFloat(2, item.getPrice());
        stmt.setString(3, item.getItemDesc());
        stmt.setInt(4, item.getCategory());
        stmt.setInt(5, item.getCondition());
        //stmt.setBlob(6, item.getUploadedFile());
        stmt.setInt(6, item.getNumOfItems());
        stmt.setObject(7, item.getBookSpecs());
        stmt.setObject(8, item.getCellSpecs());
        stmt.setObject(9, item.getCompSpecs());
        stmt.setObject(10, item.getMovieSpecs());
        stmt.setObject(11, item.getShippingPrice());
        stmt.setInt(12, item.getId());
        stmt.execute();
        this.dbManager.Disconnect();
    }

    public void deleteItemForSale(Integer id) throws SQLException {
        String sql = "DELETE FROM dreamdb.products WHERE id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        this.dbManager.Disconnect();
    }

    public ArrayList<Item> searchForItemsToBuy(String inputSearchString, Integer id, int category) throws SQLException {
        ArrayList<Item> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM dreamdb.products WHERE name LIKE ? AND seller_id <> ? AND category = ?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + inputSearchString + "%");
        preparedStatement.setObject(2, id);
        preparedStatement.setInt(3, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new Item(
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
        return arrayList;
    }

    public void confirmPayment(String sellerEmail,int numOfItemsBought) throws SQLException {

        String sql = "UPDATE dreamdb.products SET numOfItems=numOfItems-? WHERE seller_id=?";
        this.dbManager.Connect();
        Connection connection = this.dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,numOfItemsBought);
        preparedStatement.setInt(2,CommonUtils.getUserIdByEmail(sellerEmail));
        preparedStatement.execute();
        this.dbManager.Disconnect();
    }

    public ArrayList<Item> loadWishList(String email) throws SQLException {
        ArrayList<Item> arrayOfItems = new ArrayList<>();
//        String query = "SELECT * FROM dreamdb.wish_lists_products AS wishProducts" +
//                " JOIN dreamdb.products AS allProducts WHERE allProducts.seller_id = ?" +
//                " AND wishProducts.wish_list_id = ?";
        String sql = "SELECT * FROM dreamdb.products AS allP INNER JOIN dreamdb.wish_list_products AS" +
                " wishP ON allP.id=wishP.product_id WHERE wishP.wish_list_id=?";
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //preparedStatement.setInt(1, CommonUtils.getUserIdByEmail(email));
        preparedStatement.setInt(1,CommonUtils.getWishListIdByBuyerId(CommonUtils.getUserIdByEmail(email)));
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
