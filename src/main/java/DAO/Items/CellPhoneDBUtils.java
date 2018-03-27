package DAO.Items;


import ModelManagedBeans.Items.CellPhone;
import ModelManagedBeans.Items.Item;
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

        String sql = "INSERT INTO dreambuy.cellphone_specs(screen_size, model, ram,brand," +
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
}