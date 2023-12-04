import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCheckFromDB
{
    // TO CHECK USER INFO FROM DATABASE
    public static boolean checkLogin(UserLogin info)
    {
        boolean flag = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM user WHERE BINARY username = ? AND BINARY password = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, info.getUsername());
            pstmt.setString(2, info.getPassword());
            ResultSet rst = pstmt.executeQuery();
            if (rst.next())
            {
                flag = true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("An error occurred :" + e.getMessage());
        }
        return flag;
    }
}
