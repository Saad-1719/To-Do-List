import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpIntoDB
{
    //TO WRITE USER DATA INTO DATABASE
    public static boolean signUp(UserSignup info)
    {
        boolean flag = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "insert ignore into user(username,password,first_name,last_name,age)values(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, info.getUsername());
            pstmt.setString(2, info.getPassword());
            pstmt.setString(3, info.getFirstName());
            pstmt.setString(4, info.getLastName());
            pstmt.setInt(5, info.getAge());
            pstmt.executeUpdate();
            flag = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("An error occurred :" + e.getMessage());
        }
        return flag;
    }
    //TO CHECK IF THERE ARE MORE THAN ONE USER WITH SAME USERNAME
    public static boolean userCheck(UserSignup info)
    {
        boolean flag = true;
        boolean chk = true;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "SELECT COUNT(*) FROM user WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, info.getUsername());
            //execute query
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count == 1)
            {
                chk = false;
            }
            con.close();
            flag = false;
            return chk;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("An error occurred :" + e.getMessage());
        }
        return true;
    }
}
