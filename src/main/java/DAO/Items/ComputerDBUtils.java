package DAO.Items;

import ModelManagedBeans.Items.CellPhone;
import ModelManagedBeans.Items.Computer;
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
public class ComputerDBUtils extends ItemDBUtils {

    public ComputerDBUtils() {
        super();
    }

    @Override
    public int addItemForSale(Item item, int sellerId) throws SQLException {
        this.getDbManager().Connect();
        item.setCompSpecs(this.addComputerSpecs((Computer) item));
        return this.addItemForSale(item, sellerId, this.getDbManager().getConnection());
    }

    private int addComputerSpecs(Computer computer) throws SQLException {
        Connection con = this.getDbManager().getConnection();
        String sql = "INSERT INTO dreambuy.computer_specs(type, os, cpu,cpu_speed," +
                "ram,gpu,brand,screen_size,release_year,ssd,hdd,model) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement prpStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prpStmt.setString(1, computer.getType());
            prpStmt.setInt(2, computer.getOs());
            prpStmt.setInt(3, computer.getCpu());
            prpStmt.setDouble(4, computer.getCpuSpeed());
            prpStmt.setInt(5, computer.getMemory());//ram
            prpStmt.setInt(6, computer.getGpu());
            prpStmt.setInt(7, computer.getBrand());
            prpStmt.setDouble(8, computer.getScreenSize());
            prpStmt.setInt(9, computer.getReleaseYear());
            prpStmt.setInt(10, computer.getSsd());
            prpStmt.setDouble(11, computer.getHdd());
            prpStmt.setString(12, computer.getModel());
            prpStmt.execute();

            ResultSet generatedKey = prpStmt.getGeneratedKeys();
            if (generatedKey.next()) {
                // generatedKey.next();
                return generatedKey.getInt(1);
            } else {
                //throw new SQLException("add book failed");
                return -1;
            }


    }
}
