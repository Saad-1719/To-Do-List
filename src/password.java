import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class password
{
    public static boolean matchInfo(String userName, String firstName, String lastName, int age)
    {
        boolean flag = false;
        try
        {
            Connection con = Connector.createConnection();
            String query = "select * from user where binary username=? and binary first_name=? and binary last_name=? and age=?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userName);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setInt(4, age);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next())
            {
                flag = true;
            }
            con.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public static boolean updatePassword(String userName, String password, String firstName, String lastName, int age)
    {
        boolean flag = false;
        try
        {
            Connection con = Connector.createConnection();
            String query = "Update user set password=? where username=? and first_name=? and last_name=? and age=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, password);
            pstmt.setString(2, userName);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setInt(5, age);

            int count = pstmt.executeUpdate();
            if (count > 0)
            {
                flag = true;
            }
            con.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return flag;
    }
}
