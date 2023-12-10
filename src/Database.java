import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
public class Database
{
    static String yellowColor = "\u001B[93m";
    static String whiteColorCode = "\u001B[90m";
    static String redColorCode = "\u001B[31m";
    static LocalDate currentDate = LocalDate.now();
    
    // To store habit name of active user
    protected static LinkedList<String> storeTasksName = new LinkedList<>();
    //TO obtain id of active user
    public static int activeUserId(UserLogin info)
    {
        int id = 0;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM user WHERE username = '" + info.getUsername() + "' AND password = '" + info.getPassword() + "';";
            Statement smt = con.createStatement();
            ResultSet show = smt.executeQuery(query);
            show.next();
            id = show.getInt(1);
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return id;
    }

    //to fetch data of active user
    public static void retrieveDataIntoLinkedList(UserLogin info)
    {
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int userId;
            userId = activeUserId(info);
            String query = "select * from tasks where userID = '" + userId + "';";
            Statement smt = con.createStatement();
            ResultSet show = smt.executeQuery(query);
            storeTasksName.clear();
            while (show.next())
            {
                String name = show.getString(2);
                storeTasksName.add(name);
            }
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

  //   TO WRITE DATA INTO DATABASE
    
    public static boolean writeTaskData(Tasks info, UserLogin id)
    {
        boolean entrychk = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            String query = "insert into tasks(task_title,added_date,userID)values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, info.getName());
            pstmt.setDate(2, Date.valueOf(currentDate));
            pstmt.setInt(3, fetchId);
            pstmt.executeUpdate();
            entrychk = true;
            con.close();
            pstmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return entrychk;
    }public static boolean writeNotesData(Tasks info, UserLogin id)
    {
        boolean entrychk = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            LocalDate currentDate = LocalDate.now();
            //System.out.println("Current Date: " + currentDate);
            String query = "insert into notes(notes_title,notes_description,added_date,userID)values(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, info.getName());
            pstmt.setString(2, info.getDescription());
            pstmt.setDate(3, Date.valueOf(currentDate));
            pstmt.setInt(4, fetchId);
            pstmt.executeUpdate();
            entrychk = true;
            con.close();
            pstmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return entrychk;
    }

    //TO REMOVE DATA FROM DATABASE
    public static boolean deleteData(int id)
    {
        boolean flag = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "DELETE FROM tasks WHERE `task_ID`=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setInt(1, id);
            //execute query
            int deletion = pstmt.executeUpdate();
            if (deletion > 0)
            {
                flag = true;
            }
            con.close();
            pstmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    //to show habits of active user
    public static void displayCompletedTaskInfo(UserLogin info)//change from boolean
    {
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int userId;
            userId = activeUserId(info);
            String query = "select * from completed_tasks where userID = ?";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1, userId);
            ResultSet show = smt.executeQuery();
            boolean hasData = false;
            while (show.next())
            {
                System.out.print(yellowColor);
                int taskID = show.getInt(1);
                String taskTitle = show.getString(2);
                String time = show.getString(3);
                System.out.println("Task ID: " + taskID);
                System.out.println("Task Title: " + taskTitle);
                System.out.println("Completion Date: " + time);
                System.out.println("--------------------------------------");
                System.out.print(whiteColorCode);
                hasData = true;
            }
            if (!hasData)
            {
                System.out.println(redColorCode + "No Data available" + whiteColorCode);
            }
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // to display data for other options
    public static boolean displayGeneralTaskInfo(UserLogin info)
    {
        boolean chk = true;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int userId;
            userId = activeUserId(info);
            String query = "select * from tasks where userID = ?";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1, userId);
            ResultSet show = smt.executeQuery();
            boolean hasData = false;
            while (show.next())
            {
                System.out.print(yellowColor);
                int id = show.getInt(1);
                String taskTitle = show.getString(2);
                String addedDate = show.getString(3);
                //int days = show.getInt("completeddays");
                System.out.println("Task ID: " + id);
                System.out.println("Task Title: " + taskTitle);
                System.out.println("Date Added: " + addedDate);
                //System.out.println("Number of days completed: " + days);
                System.out.println("-----------------------------------------");
                hasData = true;
                System.out.println(whiteColorCode);
            }
            if (!hasData)
            {
                System.out.println(redColorCode + "No Data available" + whiteColorCode);
                chk = false;
            }
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return chk;
    }

    //TO UPDATE DATA IN DATABASE
    public static boolean updateData(int id, String bar, int completedDays)
    {
        boolean flag = false;
        try
        {
            Connection con = Connector.createConnection();
            String query = "Update activity set bar=? , completeddays=? where number=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, bar);
            pstmt.setInt(2, completedDays);
            pstmt.setInt(3, id);
            int count = pstmt.executeUpdate();
            if (count > 0)
            {
                flag = true;
            }
            con.close();
            pstmt.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return flag;
    }

    // to add data from history
    public static boolean writeHistory(UserLogin id, Tasks data)
    {
        boolean flag = false;
        try
        {
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            PreparedStatement pst;
            String query = "insert into history(name,finalImpression,userId)values(?,?,?)";
            pst = con.prepareStatement(query);
            //set values of parameter
            pst.setString(1, data.getName());
            pst.setString(2, data.getAchievement());
            pst.setInt(3, fetchId);
            int count = pst.executeUpdate();
            if (count > 0)
            {
                flag = true;
            }
            con.close();
            pst.close();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return flag;
    }

    // to get habit name from habit id
    public static String taskName(int id)
    {
        String name = null;
        try
        {
            Connection con = Connector.createConnection();
            String query = "SELECT task_title FROM tasks WHERE task_ID = ?";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1, id);
            ResultSet show = smt.executeQuery();
            if (show.next())
            {
                name = show.getString("task_title");
            }
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return name;
    }

    // to show data from history
    public static void displayHistory(UserLogin info)
    {
        try
        {
            Connection con = Connector.createConnection();
            int id = activeUserId(info);
            String query = "select * from history where userId =?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet show = pst.executeQuery();
            boolean hasData = false;
            while (show.next())
            {
                System.out.print(yellowColor);
                String habitName = show.getString(2);
                String impression = show.getString(3);
                String fTime = show.getString(4);
                System.out.println("Name: " + habitName);
                System.out.println("Final Impression: " + impression);
                System.out.println("Completed Time: " + fTime);
                System.out.println("        -----------------------------       ");
                System.out.print(whiteColorCode);
                hasData = true;
            }
            if (!hasData)
            {
                System.out.println(redColorCode + "No history available." + whiteColorCode);
            }
            con.close();
            pst.close();
            show.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //current habit days
    public static int habitDays(int id)
    {
        int days = 0;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM activity WHERE number = '" + id + "';";
            Statement smt = con.createStatement();
            ResultSet show = smt.executeQuery(query);
            show.next();
            days = show.getInt("completeddays");
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return days;
    }

    //to show user info
    public static void showUserInfo(UserLogin info)
    {

        try
        {
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM user WHERE username = '" + info.getUsername() + "' AND password = '" + info.getPassword() + "';";
            Statement smt = con.createStatement();
            ResultSet rst = smt.executeQuery(query);
            while (rst.next())
            {
                System.out.print(yellowColor);
                String username = rst.getString(2);
                String FirstName = rst.getString(5);
                String LastName = rst.getString(6);
                String Age = rst.getString(7);
                String cTime = rst.getString(4);
                System.out.println("UserName: " + username);
                System.out.println("First Name: " + FirstName);
                System.out.println("Last Name: " + LastName);
                System.out.println("Age: " + Age);
                System.out.println("Created Time: " + cTime);
                System.out.print(whiteColorCode);
            }
            con.close();
            smt.close();
            rst.close();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    //to check habit id
    public static boolean checkTaskId(int habitId, int userId)
    {
        boolean hasFound = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM tasks WHERE task_ID = '" + habitId + "' AND userID = '" + userId + "';";
            Statement smt = con.createStatement();
            ResultSet show = smt.executeQuery(query);
            if (show.next())
            {
                hasFound = true;
            }
            con.close();
            smt.close();
            show.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hasFound;
    }

    //to get habit days
    public static boolean habitCounter(UserLogin id)
    {
        boolean entrychk = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            String count = "Select * from tasks where userID =?";
            PreparedStatement pst = con.prepareStatement(count);
            pst.setInt(1, fetchId);
            ResultSet set = pst.executeQuery();
            int total = 0;
            while (set.next())
            {
                total++;
            }
            //                System.out.println(redColorCode + "Error: You cannot have more than 5 habits at a time." + whiteColorCode);
            entrychk = total < 5;
            con.close();
            pst.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return entrychk;
    }

    //to write data in deleted table
    public static boolean writeTaskHistory(UserLogin id, Tasks data)
    {
        boolean flag = false;
        try
        {
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            PreparedStatement pst;
            String query = "insert into completed_tasks(task_title,date_added,userID)values(?,?,?)";
            pst = con.prepareStatement(query);
            
            //set values of parameter
            pst.setString(1, data.getName());
            pst.setDate(2, Date.valueOf(currentDate));
            pst.setInt(3, fetchId);
            int count = pst.executeUpdate();
            if (count > 0)
            {
                flag = true;
            }
            con.close();
            pst.close();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return flag;
    }

    //to display deleted habit info
    public static void displayDeletedHabitInfo(UserLogin info)
    {
        try
        {
            Connection con = Connector.createConnection();
            int id = activeUserId(info);
            String query = "select * from deleted where user_id =?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet show = pst.executeQuery();
            boolean hasData = false;
            while (show.next())
            {
                System.out.print(yellowColor);
                String habitName = show.getString(2);
                String fTime = show.getString(3);
                System.out.println("Name: " + habitName);
                System.out.println("Deleted Time: " + fTime);
                System.out.println("        -----------------------------       ");
                System.out.print(whiteColorCode);
                hasData = true;
            }
            if (!hasData)
            {
                System.out.println(redColorCode + "No deleted history available." + whiteColorCode);
            }
            con.close();
            pst.close();
            show.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}