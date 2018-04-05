package DAO;

import ModelManagedBeans.User;
import Utils.DBManager;
import Utils.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Created by Misha on 3/21/2018.
 */
public class UserDBUtils {

    private DBManager dbManager;

    public UserDBUtils() {
        this.dbManager = new DBManager();
    }

    /**
     * inserts data of the signning user to db
     * @param user - user Object of the signed person
     * @throws SQLException -
     */
    public void signUp(User user) throws SQLException {
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        try {
            String sql = "INSERT INTO dreambuy.user(f_name, l_name, email, password, city, address, credit_card_number, credit_card_comp, credit_card_exp, phone_number, zip) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            ResultSet generatedKey = prepStmt.getGeneratedKeys();
            if(generatedKey.next()){
                int buyerId = generatedKey.getInt(1);//also seller id
                String sqlToAddBuyerId ="UPDATE dreambuy.user SET buyer_id=? WHERE id=?";
                PreparedStatement preparedStatement = con.prepareStatement(sqlToAddBuyerId);
                preparedStatement.setInt(1,buyerId);
                preparedStatement.setInt(2,buyerId);
                preparedStatement.execute();

                String wishList = "INSERT INTO dreambuy.wish_lists SET buyer_id=?";
                PreparedStatement prepStmtWishList = con.prepareStatement(wishList);
                prepStmtWishList.setInt(1,buyerId);
                prepStmtWishList.execute();
            }

            this.dbManager.Disconnect();

        } finally {
            this.dbManager.Disconnect();
        }
    }

    /**
     * checks if the user is allowed to enter the site
     * @param user - user object for login
     * @return true on successful login else false
     * @throws SQLException
     */
    public boolean login(User user) throws SQLException{
        this.dbManager.Connect();
        Connection con = this.dbManager.getConnection();
        try{
            String query = "SELECT password,f_name FROM dreambuy.user WHERE email=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                String passFromDB = res.getString("password");
                String firstName = res.getString("f_name");
                if (passFromDB.equals(user.getPassword())) {
                    HttpSession session = SessionUtils.getSession();
                    session.setAttribute("name", firstName);
                    session.setAttribute("email", user.getEmail());
                    this.dbManager.Disconnect();
                    return true;
                } else {
                    this.dbManager.Disconnect();
                    return false;
                }
            }
        }finally {
            this.dbManager.Disconnect();
        }
        return false;
    }

}
