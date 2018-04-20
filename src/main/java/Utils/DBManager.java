package Utils; /**
 * Created by Misha on 2/3/2018.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Properties;
import java.util.TimeZone;

public class DBManager {
    private final String USERNAME = "root";
    private final String PASSWORD = "Mrort987";
    private final String HOST = "localhost:3306";
    private final String DBNAME = "Mysql";

    private Connection connection = null;

    /**
     * handles connecting to database
     */
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties connProps = new Properties();
            connProps.put("user",USERNAME);
            connProps.put("password",PASSWORD);

            this.connection = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DBNAME + "?useLegacyDatetimeCode=false&serverTimezone=Asia/Jerusalem",connProps);
            System.out.println("Connected Successfully To DataBase");
        }catch (SQLException e){
            System.out.format("Error occured when connecting to DB: %s\n", e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles disconnecting from database
     */
    public void Disconnect()
    {
        try
        {
            this.connection.close();
        }
        catch(Exception ex)
        {
            //Ignore
        }
    }

    /**
     * get connection to db
     * @return - connection
     */
    public Connection getConnection() {
        return connection;
    }
}
