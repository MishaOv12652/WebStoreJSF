package DAO.Items;

import ModelManagedBeans.Items.Book;
import ModelManagedBeans.Items.CellPhone;
import ModelManagedBeans.Items.Computer;
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

    public Computer loadComputerForSale(Integer id) throws SQLException {
        this.getDbManager().Connect();
        String sql = "SELECT * FROM dreambuy.computer_specs where id = ?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Computer computer =  new Computer(
                    resultSet.getString("type"),
                    resultSet.getString("model"),
                    resultSet.getInt("os"),
                    resultSet.getInt("cpu"),
                    resultSet.getDouble("cpu_speed"),
                    resultSet.getInt("ram"),
                    resultSet.getInt("gpu"),
                    resultSet.getInt("brand"),
                    resultSet.getDouble("screen_size"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("hdd"),
                    resultSet.getInt("ssd"),
                    CommonUtils.getConstLists("dreambuy.os_systems", "os"),
                    CommonUtils.getConstLists("dreambuy.cpu_list", "cpu"),
                    CommonUtils.getConstLists("dreambuy.gpu_list", "gpu"),
                    CommonUtils.getConstLists("dreambuy.storage_capacity", "capacity"),
                    CommonUtils.getConstLists("dreambuy.cellphone_brands", "brand")
            );
            this.getDbManager().Disconnect();
            return computer;

        }
        return null;
    }

    public void updateCOmputerSpecs(Computer computer) throws SQLException {
        this.getDbManager().Connect();
        String sql = "UPDATE dreambuy.computer_specs SET type=?, os=?, cpu=?,cpu_speed=?," +
                "ram=?,gpu=?,brand=?,screen_size=?,release_year=?,ssd=?,hdd=?,model=? WHERE id=?";
        Connection connection = this.getDbManager().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, computer.getType());
        preparedStatement.setInt(2, computer.getOs());
        preparedStatement.setInt(3, computer.getCpu());
        preparedStatement.setDouble(4, computer.getCpuSpeed());
        preparedStatement.setInt(5, computer.getMemory());//ram
        preparedStatement.setInt(6, computer.getGpu());
        preparedStatement.setInt(7, computer.getBrand());
        preparedStatement.setDouble(8, computer.getScreenSize());
        preparedStatement.setInt(9, computer.getReleaseYear());
        preparedStatement.setInt(10, computer.getSsd());
        preparedStatement.setDouble(11, computer.getHdd());
        preparedStatement.setString(12, computer.getModel());
        preparedStatement.setInt(13,computer.getId());
        preparedStatement.execute();
        this.getDbManager().Disconnect();
    }
}
