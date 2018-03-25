package DAO.Items;


import ModelManagedBeans.Items.CellPhone;
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
    private DBManager dbManager;

    public CellPhoneDBUtils() {
        super();
    }

    public int addCellPhoneForSale(CellPhone cellPhone, int sellerId) throws SQLException {
        cellPhone.setItemSpecs(this.addCellPhoneSpecs(cellPhone));
        return this.addItemForSale(cellPhone, sellerId);
    }

    private int addCellPhoneSpecs(CellPhone cellPhone) throws SQLException {
        this.dbManager.Connect();

        String sql = "INSERT INTO dreambuy.cellphone_specs(screen_size, model, ram,brand," +
                "mem_card_type,os,storage,battery_cap) " + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection con = this.dbManager.getConnection();
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
        } finally {
            dbManager.Disconnect();
        }
    }
}
