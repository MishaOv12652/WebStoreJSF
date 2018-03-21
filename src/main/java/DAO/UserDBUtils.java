package DAO;

import ModelManagedBeans.User;
import Utils.DBManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Misha on 3/21/2018.
 */
public class UserDBUtils {

    private DBManager dbManager;

    public UserDBUtils() {
        dbManager = new DBManager();
    }

    public void signUp(User user) throws SQLException {
        dbManager.Connect();
        Connection con = dbManager.getConnection();
        try {
            String sql = "INSERT INTO dreambuy.user(f_name, l_name, email, password, city, address, credit_card_number, credit_card_comp, credit_card_exp, phone_number, zip) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1, user.getFirstName());
            prepStmt.setString(2, user.getLastName());
            prepStmt.setString(3, user.getEmail());
            prepStmt.setString(4, user.getPassword());
            prepStmt.setInt(5, user.getCity());
            prepStmt.setString(6, user.getStreet() + user.getStreetNum());
            prepStmt.setLong(7, user.getCreditCardNumber());
            prepStmt.setInt(8, user.getCreditCardComp());
            prepStmt.setString(9, Integer.toString(user.getCreditCardExpMonth()) + '/' + Integer.toString(user.getCreditCardExpYear()));
            prepStmt.setString(10, '0' + Integer.toString(user.getPhoneStart()) + Integer.toString(user.getPhoneNum()));
            prepStmt.setInt(11, user.getZip());
            prepStmt.execute();
            dbManager.Disconnect();

        } finally {
            dbManager.Disconnect();
        }
    }
}
