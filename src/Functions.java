import java.time.LocalTime;
import java.util.*;

public class Functions
{
    static Scanner input = new Scanner(System.in);
    static LocalTime currentTime = LocalTime.now();
    static String mintColorCode = "\u001B[38;5;85m";
    static String whiteColorCode = "\u001B[97m";
    static String redColorCode = "\u001B[31m";
    static String greenColorCode = "\u001B[92m";
    static String yellowColor = "\u001B[93m";

    // User Choice for both main loops.
    public static int getUserChoice(Scanner input)
    {
        int choice = 0;
        try
        {
            choice = input.nextInt();
            input.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.out.println(" ");
            System.out.println(redColorCode + "Error: The input is out of bounds." + whiteColorCode);
            input.nextLine();
        }
        return choice;
    }
    // to add new user

    public static void addTask(UserLogin info)
    {
        String name;
        boolean flag;
        System.out.println(mintColorCode + "\t\t\t\t\t Add a Task \n" + whiteColorCode);
        Tasks myTasks = new Tasks();
        System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
        input.nextLine();
        do
        {
            flag = false;
            System.out.print("Enter Task: ");
            name = input.nextLine().toLowerCase();
            if (name.trim().isEmpty())
            {
                System.out.println(redColorCode + "Error: Task's field cannot be empty." + whiteColorCode);
                flag = true;
            }
            else if (name.trim().length() < 3)
            {
                System.out.println(redColorCode + "Error: Task's field must be at least 3 characters long." + whiteColorCode);
                flag = true;
            }
            else if (name.length() > 80)
            {
                System.out.println(redColorCode + "Error: Task must be less than 80 characters." + whiteColorCode);
                flag = true;
            }
            Database.retrieveDataIntoLinkedList(info);
            //comparing habit names with existing habits
            if (Database.storeTasksName.contains(name))
            {
                System.out.println(redColorCode + "Error: Task already exists." + whiteColorCode);
                flag = true;
            }
        }
        while (flag);
        // to add habit into db
        myTasks.setTaskTitle(name);
        boolean isWritten = Database.writeTaskData(myTasks, info);
        if (isWritten)
        {
            System.out.println(greenColorCode + "Completed, Data Added!" + whiteColorCode);
        }
        else
        {
            System.out.println(redColorCode + "Error: Something went wrong :(" + whiteColorCode);
        }
    }

    //add notes to db
    public static void addNotes(UserLogin info)
    {
        String name;
        String description;
        boolean flag;
        System.out.println(mintColorCode + "\t\t\t\t\t Add Notes \n" + whiteColorCode);
        Notes myNotes = new Notes("", "");
        System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
        input.nextLine();
        do
        {
            flag = false;
            System.out.print("Title: ");
            name = input.nextLine().toLowerCase();
            if (name.trim().isEmpty())
            {
                System.out.println(redColorCode + "Error: Note's Title cannot be empty." + whiteColorCode);
                flag = true;
            }
            else if (name.trim().length() < 3)
            {
                System.out.println(redColorCode + "Error: Note's Title must be at least 3 characters long." + whiteColorCode);
                flag = true;
            }
            else if (name.length() > 50)
            {
                System.out.println(redColorCode + "Error: Note's title must be less than 50 characters." + whiteColorCode);
                flag = true;
            }
        }
        while (flag);
        //habit description
        do
        {
            flag = false;
            System.out.print("Description: ");
            description = input.nextLine();
            if (description.trim().isEmpty())
            {
                System.out.println(redColorCode + "Error: Note's description cannot be empty." + whiteColorCode);
                flag = true;
            }
            else if (description.trim().length() < 3)
            {
                System.out.println(redColorCode + "Error: Note's description must be at least 3 characters long." + whiteColorCode);
                flag = true;
            }
        }
        while (flag);
        myNotes.setNotesName(name);
        myNotes.setNotesDescription(description);
        boolean isWritten = Database.writeNotesData(myNotes, info);
        if (isWritten)
        {
            System.out.println(greenColorCode + "Completed, Data Added!" + whiteColorCode);
        }
        else
        {
            System.out.println(redColorCode + "Error: Something went wrong :(" + whiteColorCode);
        }
    }
    // to show habit from db
    public static void showOngoingTasks(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Ongoing Tasks \n" + whiteColorCode);
        viewCalendar(info);
    }

    public static void showCompletedTasks(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Completed Tasks \n" + whiteColorCode);
        Database.displayCompletedTaskInfo(info);
    }

    public static void showAddedNotes(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Notes \n" + whiteColorCode);
        Database.displayWrittenNotes(info);
    }

    // to delete habit from db
    public static void markTaskCompleted(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Mark Task Complete \n" + whiteColorCode);
        // to check if the task exists
        boolean isDataExists = Database.displayGeneralTaskInfo(info);
        if (isDataExists)
        {
            int delId;
            // data delete
            System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
            input.nextLine();
            System.out.println("Which task would you like to delete? ");
            System.out.print("Enter task's ID: ");
            try
            {
                delId = input.nextInt();
                int userID = Database.activeUserId(info);
                // to check if the habit id is valid
                boolean hasHabitFound = Database.checkTaskId(delId, userID);
                if (hasHabitFound)
                {
                    String habitName = Database.taskName(delId);
                    Tasks data = new Tasks();
                    data.setTaskTitle(habitName);
                    //to write data into a task history table
                    Database.writeTaskHistory(info, data);
                    // to delete task from ongoing task table
                    boolean ckh = Database.deleteData(delId);
                    if (ckh)
                    {
                        System.out.println(greenColorCode + "task has been marked completed!" + whiteColorCode);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(redColorCode + "Error: Incorrect task ID." + whiteColorCode);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(redColorCode + "Error: The input is out of bounds" + whiteColorCode);
                input.nextLine();
            }
        }
    }

    public static void deleteNotes(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Delete Notes \n" + whiteColorCode);
        // to check if notes exists
        boolean isDataExists = Database.displayGeneralNotesInfo(info);
        if (isDataExists)
        {
            int delId;
            System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
            input.nextLine();
            System.out.println("Which note would you like to delete? ");
            System.out.print("Enter Note ID: ");
            try
            {
                delId = input.nextInt();
                int userID = Database.activeUserId(info);
                // to check if the note id is valid
                boolean hasHabitFound = Database.checkNotesId(delId, userID);
                if (hasHabitFound)
                {
                    boolean ckh = Database.deleteNotes(delId);
                    if (ckh)
                    {
                        System.out.println(greenColorCode + "Note has been Deleted!" + whiteColorCode);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(redColorCode + "Error: Incorrect Note ID." + whiteColorCode);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(redColorCode + "Error: The input is out of bounds" + whiteColorCode);
                input.nextLine();
            }
        }
    }

    public static void greetings(UserLogin info)
    {
        if (currentTime.isBefore(LocalTime.NOON))
        {
            System.out.println("        Good Morning \uD83C\uDF04\uD83C\uDF04, " + info.getUsername() + "!");
        }
        else if (currentTime.isBefore(LocalTime.of(18, 0)))
        {
            System.out.println("        Good Afternoon \uD83C\uDF1E\uD83C\uDF1E, " + info.getUsername() + "!");
        }
        else
        {
            System.out.println("        Good Evening \uD83C\uDF06\uD83C\uDF06, " + info.getUsername() + "!");
        }
    }


    public static void addRandomTask(UserLogin info)
    {
        String name;
        boolean flag;
        System.out.println(mintColorCode + "\t\t\t\t\t Add a Random Task \n" + whiteColorCode);
        Tasks myTasks = new Tasks("");
        System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
            input.nextLine();
            LinkedList<String> tasksTitleList = new LinkedList<>();
            int totalTasks = 0;
            System.out.print("how many tasks you wanna to to randomize it? ");
            totalTasks = input.nextInt();
            input.nextLine();
            for (int i = 0; i < totalTasks;)
            {
                do
                {
                    flag = false;
                    System.out.print("Task " + (i+1) +" : ");
                    name = input.nextLine().toLowerCase();
                    if (name.trim().isEmpty())
                    {
                        System.out.println(redColorCode + "Error: Task's Title cannot be empty." + whiteColorCode);
                        flag = true;
                    }
                    else if (name.trim().length() < 3)
                    {
                        System.out.println(redColorCode + "Error: Task's Title must be at least 3 characters long." + whiteColorCode);
                        flag = true;
                    }
                    //retrieving habit name into array
                    Database.retrieveDataIntoLinkedList(info);
                    //comparing habit names with existing habits
                    if (Database.storeTasksName.contains(name))
                    {
                        System.out.println(redColorCode + "Error: Task's Title already exists." + whiteColorCode);
                        flag = true;
                    }
                }
                while (flag);
                tasksTitleList.add(name);
                i++;
            }
            Random rand = new Random();
            int randIndex = rand.nextInt(tasksTitleList.size());
            String randomTask = tasksTitleList.get(randIndex);
            System.out.println("Today's target is: " + yellowColor + randomTask + whiteColorCode);
            // to add habit into db
            myTasks.setTaskTitle(randomTask);
            boolean isWritten = Database.writeTaskData(myTasks, info);
            if (isWritten)
            {
                System.out.println(greenColorCode + "Completed, Data Added!" + whiteColorCode);
            }
            else
            {
                System.out.println(redColorCode + "Error: Something went wrong :(" + whiteColorCode);
            }
    }


    public static void viewCalendar(UserLogin info) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        boolean isCorrect=true;
        boolean isTrue=true;
        int month=0;
        while(isCorrect)
        {
            System.out.print("Enter month (1-12): ");
            month = scanner.nextInt();

            if (month < 1 || month > 12)
            {
                System.out.println("Invalid month. Please enter a value between 1 and 12.");
                isCorrect=true;
            }
            else {
                isCorrect=false;
            }
        }

        // Create a GregorianCalendar object
        Calendar calendar = new GregorianCalendar(year, month - 1, 1);

        // Display tasks for the selected month
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Display tasks for the selected month
        boolean[] hasDataForDay = new boolean[maxDay + 1];
        Database.displayGeneralTaskInfoforCalendar(year, month, hasDataForDay,info);

        // Print the calendar header
        System.out.println("\n" + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault()) + " " + year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        // Print leading spaces
        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        // Print the days of the month with colors based on data presence
        for (int i = 1; i <= maxDay; i++) {
            if (hasDataForDay[i]) {
                System.out.print("\u001B[31m"); // Red color for days with data
            }

            System.out.printf("%3d ", i);

            if (hasDataForDay[i]) {
                System.out.print("\u001B[0m"); // Reset color after printing the day
            }

            // Move to the next line if it's the last day of the week
            if ((startDay + i) % 7 == 0) {
                System.out.println();
            }
        }
        int day = 0;
        while(isTrue)
        {
            System.out.print("\nEnter a day to view tasks for that date (1-31): ");
            day = scanner.nextInt();

            if (day < 1 || day > 31)
            {
                System.out.println("Invalid date. Please enter a value between 1 and 31.");
                isTrue=true;
            }
            else {
                isTrue=false;
            }
        }


        // Print the tasks for the selected date
        System.out.println(yellowColor+"\nTasks for " + year + "-" + month + "-" + day + ":");
        Database.printTasksForDate(year, month, day, info);


        System.out.println("\n");
    }

    public static void searchTask(UserLogin info)
    {
        String title;
        System.out.print("Enter task to search it: ");
        title=input.nextLine();
        Database.retrieveDataIntoArray(info);
        int index=Collections.binarySearch(Database.storeTasksNameForSearch,title);
       if(index>=0)
       {
           System.out.println("task found");
       }
       else {
           System.out.println("No task found");
       }
    }
}