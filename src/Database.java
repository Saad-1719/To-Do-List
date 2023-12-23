import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Database
{
    static String brightYellow = "\u001B[38;5;226m";
    static String pureWhite = "\u001B[90m";
    static String brightPeach = "\u001B[38;5;9m";
    static LocalDate currentDate = LocalDate.now();
    static LocalTime currentTime = LocalTime.now();
    // Create a formatter for seconds precision
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    // Format and print the current time with seconds precision
    static String formattedTime = currentTime.format(formatter);
    protected static LinkedList<String> storeTasksName = new LinkedList<>();
    protected static LinkedList<String> storeNotes = new LinkedList<>();
    protected static ArrayList<String> storeTasksNameForSearch = new ArrayList<>();

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

    public static void retrieveDataIntoArray(UserLogin info)
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
            storeTasksNameForSearch.clear();
            while (show.next())
            {
                String name = show.getString(2);
                storeTasksNameForSearch.add(name);
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
    public static boolean addTaskIntoDatabase(Tasks info, UserLogin id)
    {
        boolean entrychk = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            String query = "insert into tasks(task_title,added_date,added_time,userID)values(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, info.getTaskTitle());
            pstmt.setDate(2, Date.valueOf(currentDate));
            pstmt.setString(3, String.valueOf(formattedTime));
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

    public static boolean addNoteIntoDatabase(Notes obj, UserLogin id)
    {
        boolean entrychk = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            LocalDate currentDate = LocalDate.now();
            String query = "insert into notes(notes_title,notes_description,added_date,userID)values(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            //set values of parameter
            pstmt.setString(1, obj.getNotesName());
            pstmt.setString(2, obj.getNotesDescription());
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
    public static boolean deleteTask(int id)
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

    public static void displayAccomplishedTasks(UserLogin info)
    {
        try
        {
            // jdbc code
            Connection con = Connector.createConnection();
            int userId = activeUserId(info);
            String query = "select * from completed_tasks where userID = ?";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1, userId);
            ResultSet show = smt.executeQuery();
            LinkedList<Tasks> completedTasks = new LinkedList<>();
            while (show.next())
            {
                int taskID = show.getInt(1);
                String taskTitle = show.getString(2);
                String date = show.getString(3);
                String time = show.getString(4);

                Tasks completedTaskObj = new Tasks(taskID, taskTitle, date, time);
                completedTasks.add(completedTaskObj);
            }

            if (completedTasks.isEmpty())
            {
                System.out.println(brightPeach + "You didn't mark any task complete" + pureWhite);
            }
            else
            {
                Collections.sort(completedTasks, Comparator.comparing(Tasks::getTaskID).reversed());
                for (Tasks task : completedTasks)
                {
                    System.out.print(brightYellow);
                    System.out.println("Title: " + task.getTaskTitle());
                    System.out.println("Completion Date: " + task.getCompletionDate());
                    System.out.println("Completion Time: " + task.getAddedTime());
                    System.out.println("< --------------------------------------------------------- >");
                    System.out.print(pureWhite);
                }
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

    public static void displayWrittenNotes(UserLogin info)
    {
        try
        {
            // jdbc code
            Connection con = Connector.createConnection();
            int userId = activeUserId(info);
            String query = "select * from notes where userID = ?";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1, userId);
            ResultSet show = smt.executeQuery();
            LinkedList<Notes> addedNotes = new LinkedList<>();
            while (show.next())
            {
                int notesID = show.getInt(1);
                String notesTitle = show.getString(2);
                String notesDescription = show.getString(3);
                String time = show.getString(4);
                Notes myNotes = new Notes(notesID, notesTitle, notesDescription, time);
                addedNotes.add(myNotes);
            }

            if (addedNotes.isEmpty())
            {
                System.out.println(brightPeach + "You didn't add any note" + pureWhite);
            }
            else
            {
                for (Notes obj : addedNotes)
                {
                    System.out.print(brightYellow);
                    System.out.println("Title: " + obj.getNotesName());
                    System.out.println("Description: " + obj.getNotesDescription());
                    System.out.println("Added Date: " + obj.getAddedDate());
                    System.out.println("< --------------------------------------------------------- >");
                    System.out.print(pureWhite);
                }
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

    public static boolean deleteNotes(int id)
    {
        boolean flag = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "DELETE FROM notes WHERE `notes_ID`=?";
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

    // to display data for other options
    public static boolean displayTasks(UserLogin info)
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
            LinkedList<Tasks> ongoingTasks = new LinkedList<>();

            boolean hasData = false;
            while (show.next())
            {
                int taskID = show.getInt(1);
                String taskTitle = show.getString(2);
                String date = show.getString(3);
                String time = show.getString(4);

                Tasks ongoingTaskObj = new Tasks(taskID, taskTitle, date, time);
                ongoingTasks.add(ongoingTaskObj);
                hasData = true;
            }
            if (!hasData)
            {
                System.out.println(brightPeach + "No Data available" + pureWhite);
                chk = false;
            }
            else
            {
                Collections.sort(ongoingTasks, Comparator.comparing(Tasks::getTaskID).reversed());
                for (Tasks task : ongoingTasks)
                {

                    System.out.print(brightYellow);
                    System.out.println("ID: " + task.getTaskID());
                    System.out.println("Title: " + task.getTaskTitle());
                    System.out.println("Added Date: " + task.getCompletionDate());
                    System.out.println("Added Time: " + task.getAddedTime());
                    System.out.println("< --------------------------------------------------------- >");
                    System.out.print(pureWhite);
                }
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
    public static boolean isTaskExists(UserLogin info)
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
            LinkedList<Tasks> ongoingTasks = new LinkedList<>();

            boolean hasData = false;
            while (show.next())
            {
                int taskID = show.getInt(1);
                String taskTitle = show.getString(2);
                String date = show.getString(3);
                String time = show.getString(4);

                Tasks ongoingTaskObj = new Tasks(taskID, taskTitle, date, time);
                ongoingTasks.add(ongoingTaskObj);
                hasData = true;
            }
            if (!hasData)
            {
                System.out.println(brightPeach + "You haven't any accomplishment till now :/" + pureWhite);
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

    public static boolean displayNotes(UserLogin info)
    {
        boolean chk = true;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            int userId;
            userId = activeUserId(info);
            String query = "select * from notes where userID = ?";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1, userId);
            ResultSet show = smt.executeQuery();
            LinkedList<Notes> addedNotes = new LinkedList<>();

            boolean hasData = false;
            while (show.next())
            {
                int notesID = show.getInt(1);
                String notesTitle = show.getString(2);
                String description = show.getString(3);
                Notes notesObj = new Notes(notesID, notesTitle, description);
                addedNotes.add(notesObj);
                hasData = true;
            }
            if (!hasData)
            {
                System.out.println(brightPeach + "No Data available" + pureWhite);
                chk = false;
            }
            else
            {
                Collections.sort(addedNotes, Comparator.comparing(Notes::getNotesID).reversed());
                for (Notes obj : addedNotes)
                {

                    System.out.print(brightYellow);
                    System.out.println("ID: " + obj.getNotesID());
                    System.out.println("Title: " + obj.getNotesName());
                    System.out.println("Description: " + obj.getNotesDescription());
                    System.out.println("< --------------------------------------------------------- >");
                    System.out.print(pureWhite);
                }
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

    public static boolean checkNotesId(int NotesId, int userId)
    {
        boolean hasFound = false;
        try
        {
            //jdbc code
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM notes WHERE notes_ID = '" + NotesId + "' AND userID = '" + userId + "';";
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

    //to write data in deleted table
    public static void writeAccomplishedTasks(UserLogin id, Tasks data)
    {
        boolean flag = false;
        try
        {
            Connection con = Connector.createConnection();
            int fetchId = activeUserId(id);
            PreparedStatement pst;
            String query = "insert into completed_tasks(task_title,date_added,time_added,userID)values(?,?,?,?)";
            pst = con.prepareStatement(query);

            //set values of parameter
            pst.setString(1, data.getTaskTitle());
            pst.setDate(2, Date.valueOf(currentDate));
            pst.setString(3, String.valueOf(formattedTime));
            pst.setInt(4, fetchId);
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
    }

    public static void divideTasksAccordingToDates(int year, int month, boolean[] hasDataForDay, UserLogin info)
    {
        try
        {
            Connection con = Connector.createConnection();
            String query = "SELECT added_date FROM tasks WHERE userID=? and YEAR(added_date) = ? AND MONTH(added_date) = ? ";
            PreparedStatement smt = con.prepareStatement(query);
            int getId = activeUserId(info);
            smt.setInt(2, year);
            smt.setInt(3, month);
            smt.setInt(1, getId);

            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next())
            {
                String addedDate = resultSet.getString("added_date");
                int day = Integer.parseInt(addedDate.split("-")[2]); // Assuming date format is "YYYY-MM-DD"
                hasDataForDay[day] = true;
            }

            con.close();
            smt.close();
            resultSet.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void printTasksForDate(int year, int month, int day, UserLogin info)
    {
        try
        {
            Connection con = Connector.createConnection();
            String query = "SELECT * FROM tasks WHERE userID=? AND YEAR(added_date) = ? AND MONTH(added_date) = ? AND DAY(added_date) = ?";
            PreparedStatement smt = con.prepareStatement(query);
            int getId = activeUserId(info);
            smt.setInt(1, getId);
            smt.setInt(2, year);
            smt.setInt(3, month);
            smt.setInt(4, day);

            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next())
            {
                String taskTitle = resultSet.getString("task_title");
                String addedTime = resultSet.getString("added_time");
                System.out.print(brightYellow);
                System.out.println("Title: " + taskTitle);
                System.out.println("Added Time: " + addedTime);
                System.out.println("< --------------------------------------------------------- >");
                System.out.print(pureWhite);
            }

            con.close();
            smt.close();
            resultSet.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}