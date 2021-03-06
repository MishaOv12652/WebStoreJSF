package DAO.Items;


import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.CellPhone;
import ModelManagedBeans.Items.Item;
import Utils.CommonUtils;
import Utils.DBManager;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;

/**
 * Created by Misha on 3/25/2018.
 */
@Getter
@Setter
public class CellPhoneDBUtils extends ItemDBUtils {

    public CellPhoneDBUtils() {
        super();
    }

    @Override
    public int addItemForSale(Item item, int sellerId) throws SQLException {
        this.getDbManager().Connect();
        item.setCellSpecs(this.addCellPhoneSpecs((CellPhone) item));
        return this.addItemForSale(item, sellerId,this.getDbManager().getConnection());
    }

    private int addCellPhoneSpecs(CellPhone cellPhone) throws SQLException {
        this.getDbManager().Connect();

        String sql = "INSERT INTO dreamdb.cellphone_specs(screen_size, model, ram,brand," +
                "mem_card_type,os,storage,battery_cap) " + "VALUES (?,?,?,?,?,?,?,?)";

            Connection con = this.getDbManager().getConnection();
            PreparedStatement prpStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prpStmt.setDouble(1, cellPhone.getScreenSize());
            prpStmt.setString(2, cellPhone.getModel());
            prpStmt.setInt(3, cellPhone.getRam());
            //prpStmt.setInt(4, this.colour);
            prpStmt.setInt(4, cellPhone.getBrand());
            prpStmt.setInt(5, cellPhone.getMemoryCardType());
            prpStmt.setInt(6, cellPhone.getOs());
            prpStmt.setInt(7, cellPhone.getStorage());
            prpStmt.setInt(8, cellPhone.getBatteryCapacity());
            prpStmt.execute();
            ResultSet generatedKey = prpStmt.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getInt(1);
            } else {
                return -1;
            }
    }

    public CellPhone loadCellPhoneForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "SELECT * FROM dreamdb.cellphone_specs where id = ?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            CellPhone cellPhone =  new CellPhone(
                    resultSet.getInt("id"),
                    resultSet.getDouble("screen_size"),
                    resultSet.getInt("ram"),
                    resultSet.getInt("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("mem_card_type"),
                    resultSet.getInt("os"),
                    resultSet.getInt("storage"),
                    resultSet.getInt("battery_cap"),
                    CommonUtils.getConstLists("dreamdb.cellphone_brands", "brand"),
                    CommonUtils.getConstLists("dreamdb.os_systems", "os"),
                    CommonUtils.getConstLists("dreamdb.storage_type", "storage_type"),
                    CommonUtils.getConstLists("dreamdb.storage_capacity", "capacity")
            );
            this.getDbManager().Disconnect();
            return cellPhone;

        }
        return null;
    }

    public void updateCellPhoneSpecs(CellPhone cellPhone) throws SQLException {
        this.getDbManager().Connect();
        String sql = "UPDATE dreamdb.cellphone_specs SET screen_size=?, model=?, ram=?,brand=?," +
                "mem_card_type=?,os=?,storage=?,battery_cap=? WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, cellPhone.getScreenSize());
        preparedStatement.setString(2, cellPhone.getModel());
        preparedStatement.setInt(3, cellPhone.getRam());
        preparedStatement.setInt(4, cellPhone.getBrand());
        preparedStatement.setInt(5, cellPhone.getMemoryCardType());
        preparedStatement.setInt(6, cellPhone.getOs());
        preparedStatement.setInt(7, cellPhone.getStorage());
        preparedStatement.setInt(8, cellPhone.getBatteryCapacity());
        preparedStatement.setInt(9, cellPhone.getId());
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }

    public void deleteCellPhoneForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "DELETE FROM dreamdb.cellphone_specs WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }
}
