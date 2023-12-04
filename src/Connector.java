import java.sql.*;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Connector
{
    static Connection con;

    public static Connection createConnection()
    {
        try
        {
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection
            String user = "Saad";
            String password = "!Adk4597";
            String url = "jdbc:mysql://localhost:3301/student";
            con = DriverManager.getConnection(url, user, password);
            //con= DriverManager.getConnection("jdbc:mysql://localhost:3301/database","Saad","!Adk4597");
        }
        catch (SQLException e)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        return con;
    }
}