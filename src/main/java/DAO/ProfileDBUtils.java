package DAO;

import ModelManagedBeans.User;
import Utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Misha on 23/03/2018.
 */
public class ProfileDBUtils {
    private DBManager dbManager;

    public ProfileDBUtils() {
        this.dbManager = new DBManager();
    }

    /**
     * loads profile info by email
     * @param email - email of the user
     * @return - user object containing his personal info
     * @throws SQLException -
     */
    public User loadProfileInfo(String email) throws SQLException {
        String query = "SELECT * FROM dreamdb.account WHERE email=?";
        this.dbManager.Connect();
        try {
            Connection con = this.dbManager.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new Integer(resultSet.getString("phone_number").substring(0, 2)),//phone start
                        new Integer(Integer.toString(resultSet.getInt("phone_number")).substring(2)),//phone number
                        resultSet.getInt("city"),
                        resultSet.getString("address").replaceAll("[^A-Za-z]", ""),
                        Integer.parseInt(resultSet.getString("address").replaceAll("\\D+", "")),
                        resultSet.getInt("zip"),
                        resultSet.getLong("credit_card_number"),
                        resultSet.getInt("credit_card_comp"),
                        Integer.parseInt(resultSet.getString("credit_card_exp").split("/")[0]),
                        Integer.parseInt(resultSet.getString("credit_card_exp").split("/")[1])
                );
            }
        }finally {
            this.dbManager.Disconnect();
        }
        return null;
    }

    /**
     * updates personal info of the user
     * @param user - user to update
     * @throws SQLException -
     */
    public void updateProfileInfo(User user) throws SQLException{
        String sql = "UPDATE dreamdb.account SET f_name=?, l_name = ?, email = ?, phone_number=?, password = ?" +
                ",address = ?,city = ?,zip = ?,credit_card_number = ?,credit_card_exp = ?,credit_card_comp = ? WHERE email =?";
        this.dbManager.Connect();
        try{
            Connection con = this.dbManager.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, Integer.toString(user.getPhoneStart()) + Integer.toString(user.getPhoneNum()));
            statement.setString(5, user.getPassword());
//            this.setCity(comUtil.getKeyByValue(this.cities,this.cityName));
            statement.setString(6, user.getStreet() + Integer.toString(user.getStreetNum()));
            statement.setInt(7, user.getCity());
            statement.setInt(8, user.getZip());

            statement.setLong(9, user.getCreditCardNumber());
            statement.setString(10, Integer.toString(user.getCreditCardExpMonth()) + '/' + Integer.toString(user.getCreditCardExpYear()));
//            CommonUtils comUtil = new CommonUtils();
//            this.setCredit_card_comp(Integer.toString(comUtil.getKeyByValue(this.credit_companies,this.creditCompanyName)));
            statement.setInt(11, user.getCreditCardComp());
            statement.setString(12,user.getEmail());
            statement.execute();
        }finally {
            this.dbManager.Disconnect();
        }
    }
}
