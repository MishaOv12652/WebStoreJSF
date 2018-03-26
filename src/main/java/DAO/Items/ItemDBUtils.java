package DAO.Items;

import ModelManagedBeans.Items.Item;
import Utils.DBManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.*;

/**
 * Created by Misha on 24/03/2018.
 */
public class ItemDBUtils {
    private DBManager dbManager;

    public ItemDBUtils() {
        this.dbManager = new DBManager();
    }

    /**
     *
     * @param item
     * @param idOfUser
     * @return
     * @throws SQLException
     */
    public int addItemForSale(Item item ,int idOfUser) throws SQLException {
        this.dbManager.Connect();
        return this.addItemForSale(item,idOfUser,this.dbManager.getConnection());
    }
    protected int addItemForSale(Item item ,int idOfUser,Connection con) throws SQLException{
        String sql = "INSERT INTO dreambuy.products(name, price, item_desc, category, condition_id, seller_id,item_spec_id,numOfItems)" +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setFloat(2, item.getPrice());
            stmt.setString(3, item.getItemDesc());
            stmt.setInt(4, item.getCategory());
            stmt.setInt(5, item.getCondition());
            //stmt.setBlob(6, item.getUploadedFile());
            stmt.setInt(6, idOfUser);
            stmt.setObject(7, item.getItemSpecs());
            stmt.setInt(8, item.getNumOfItems());
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
