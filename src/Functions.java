import java.time.LocalTime;
import java.util.*;

public class Functions
{
    static Scanner input = new Scanner(System.in);
    static LocalTime currentTime = LocalTime.now();
    static String brightPurple = "\u001B[38;5;207m";
    static String pureWhite = "\u001B[97m";
    static String brightRed = "\u001B[38;5;196m";
    static String brightGreen = "\u001B[38;5;82m";
    static String brightYellow = "\u001B[38;5;226m";
    static String brightPeach = "\u001B[38;5;9m";

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
            System.out.println(brightRed + "Error: The input is out of bounds." + pureWhite);
            input.nextLine();
        }
        return choice;
    }
    // to add new user

    public static void addTask(UserLogin info)
    {
        String name;
        boolean flag;
        System.out.println(brightPurple + "\t\t\t\t\t Add a Task \n" + pureWhite);
        Tasks myTasks = new Tasks();
        System.out.println(brightPeach + "Hit Enter if nothing appears" + pureWhite);
        input.nextLine();
        do
        {
            flag = false;
            System.out.print("Task: ");
            name = input.nextLine().toLowerCase();
            if (name.trim().isEmpty())
            {
                System.out.println(brightRed + "Error: Task's field cannot be empty." + pureWhite);
                flag = true;
            }
            else if (name.trim().length() < 3)
            {
                System.out.println(brightRed + "Error: Task's field must be at least 3 characters long." + pureWhite);
                flag = true;
            }
            else if (name.length() > 80)
            {
                System.out.println(brightRed + "Error: Task must be less than 80 characters." + pureWhite);
                flag = true;
            }
            Database.retrieveDataIntoLinkedList(info);
            //comparing task names with existing ongoing tasks
            if (Database.storeTasksName.contains(name))
            {
                System.out.println(brightRed + "Error: Task already exists." + pureWhite);
                flag = true;
            }
        }
        while (flag);
        // to add habit into db
        myTasks.setTaskTitle(name);
        boolean isWritten = Database.addTaskIntoDatabase(myTasks, info);
        if (isWritten)
        {
            System.out.println(" ");
            System.out.println(brightGreen + "Hurrah!! new task, new challenge" + pureWhite);
        }
        else
        {
            System.out.println(brightRed + "Error: Something went wrong :(" + pureWhite);
        }
    }

    //add notes to db
    public static void addNotes(UserLogin info)
    {
        String name;
        String description;
        boolean flag;
        System.out.println(brightPurple + "\t\t\t\t\t Add Notes \n" + pureWhite);
        Notes myNotes = new Notes("", "");
        System.out.println(brightPeach + "Hit Enter if nothing appears" + pureWhite);
        input.nextLine();
        do
        {
            flag = false;
            System.out.print("Title: ");
            name = input.nextLine().toLowerCase();
            if (name.trim().isEmpty())
            {
                System.out.println(brightRed + "Error: Note's Title cannot be empty." + pureWhite);
                flag = true;
            }
            else if (name.trim().length() < 3)
            {
                System.out.println(brightRed + "Error: Note's Title must be at least 3 characters long." + pureWhite);
                flag = true;
            }
            else if (name.length() > 50)
            {
                System.out.println(brightRed + "Error: Note's title must be less than 50 characters." + pureWhite);
                flag = true;
            }
        }
        while (flag);
        //notes description
        do
        {
            flag = false;
            System.out.print("Description: ");
            description = input.nextLine();
            if (description.trim().isEmpty())
            {
                System.out.println(brightRed + "Error: Note's description cannot be empty." + pureWhite);
                flag = true;
            }
            else if (description.trim().length() < 3)
            {
                System.out.println(brightRed + "Error: Note's description must be at least 3 characters long." + pureWhite);
                flag = true;
            }
        }
        while (flag);
        myNotes.setNotesName(name);
        myNotes.setNotesDescription(description);
        boolean isWritten = Database.addNoteIntoDatabase(myNotes, info);
        if (isWritten)
        {
            System.out.println(" ");
            System.out.println(brightGreen + "Bravo!! Memory ++ " + pureWhite);
        }
        else
        {
            System.out.println(brightRed + "Error: Something went wrong :(" + pureWhite);
        }
    }

    // to show habit from db
    public static void showOngoingTasks(UserLogin info)
    {
        System.out.println(brightPurple + "\t\t\t\t\t Ongoing Tasks \n" + pureWhite);
        boolean isData=Database.isTaskExists(info);
        if(isData)
        {
            viewCalendar(info);
        }
    }

    public static void showCompletedTasks(UserLogin info)
    {
        System.out.println(brightPurple + "\t\t\t\t\t Accomplished Tasks \n" + pureWhite);
        Database.displayAccomplishedTasks(info);
    }

    public static void showAddedNotes(UserLogin info)
    {
        System.out.println(brightPurple + "\t\t\t\t\t Notes \n" + pureWhite);
        Database.displayWrittenNotes(info);
    }

    // to mark a task completed
    public static void markTaskCompleted(UserLogin info)
    {
        System.out.println(brightPurple + "\t\t\t\t\t Conquer a Task \n" + pureWhite);
        // to check if the task exists
        boolean isDataExists = Database.displayTasks(info);
        if (isDataExists)
        {
            int delId;
            // data delete
            System.out.println(" ");
            System.out.println(brightPeach + "Hit Enter if nothing appears" + pureWhite);
           // input.nextLine();
            System.out.println("Which task would you like to delete? ");
            System.out.print("Task's ID: ");
            try
            {
                delId = input.nextInt();
                int userID = Database.activeUserId(info);
                // to check if the task id is valid
                boolean hasHabitFound = Database.checkTaskId(delId, userID);
                if (hasHabitFound)
                {
                    String habitName = Database.taskName(delId);
                    Tasks data = new Tasks();
                    data.setTaskTitle(habitName);
                    //to write data into a task history table
                    Database.writeAccomplishedTasks(info, data);
                    // to delete task from ongoing task table
                    boolean ckh = Database.deleteTask(delId);
                    if (ckh)
                    {
                        System.out.println(" ");
                        System.out.println(brightGreen + "Task Conquered, Mission Accomplished!!" + pureWhite);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(brightRed + "Error: Incorrect task ID." + pureWhite);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(brightRed + "Oops! That doesn't seem to be a valid input." + pureWhite);
                input.nextLine();
            }
        }
    }

    public static void deleteNotes(UserLogin info)
    {
        System.out.println(brightPurple + "\t\t\t\t\t Delete Notes \n" + pureWhite);
        // to check if notes exists
        boolean isDataExists = Database.displayNotes(info);
        if (isDataExists)
        {
            int delId;
            System.out.println(brightPeach + "Hit Enter if nothing appears" + pureWhite);
            input.nextLine();
            System.out.println("Which note would you like to delete? ");
            System.out.print("Note ID: ");
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
                        System.out.println(" ");
                        System.out.println(brightGreen + "Note Successfully Erased!" + pureWhite);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(brightRed + "Error: Incorrect Note ID." + pureWhite);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(brightRed + "Error: The input is out of bounds" + pureWhite);
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

    //    public static void addRandomTask(UserLogin info)
    //    {
    //        String name;
    //        boolean flag;
    //        System.out.println(brightPurple + "\t\t\t\t\t Random Task Hunt\n" + pureWhite);
    //        Tasks myTasks = new Tasks("");
    //        System.out.println(brightRed + "Hit Enter if nothing appears" + pureWhite);
    //            input.nextLine();
    //            LinkedList<String> tasksTitleList = new LinkedList<>();
    //            int totalTasks = 0;
    //            System.out.print("how many tasks you wanna randomize? ");
    //            totalTasks = input.nextInt();
    //            input.nextLine();
    //            for (int i = 0; i < totalTasks;)
    //            {
    //                do
    //                {
    //                    flag = false;
    //                    System.out.print("Task " + (i+1) +" : ");
    //                    name = input.nextLine().toLowerCase();
    //                    if (name.trim().isEmpty())
    //                    {
    //                        System.out.println(brightRed + "Error: Task's cannot be empty." + pureWhite);
    //                        flag = true;
    //                    }
    //                    else if (name.trim().length() < 3)
    //                    {
    //                        System.out.println(brightRed + "Error: Task's must be at least 3 characters long." + pureWhite);
    //                        flag = true;
    //                    }
    //                    //retrieving task name into array
    //                    Database.retrieveDataIntoLinkedList(info);
    //                    //comparison between task names
    //                    if (Database.storeTasksName.contains(name))
    //                    {
    //                        System.out.println(brightRed + "Error: Task's already exists." + pureWhite);
    //                        flag = true;
    //                    }
    //                }
    //                while (flag);
    //                tasksTitleList.add(name);
    //                i++;
    //            }
    //            Random rand = new Random();
    //            int randIndex = rand.nextInt(tasksTitleList.size());
    //            String randomTask = tasksTitleList.get(randIndex);
    //            System.out.println("Today's target is: " + brightYellow + randomTask + pureWhite);
    //            // to add tasks into db
    //            myTasks.setTaskTitle(randomTask);
    //            boolean isWritten = Database.writeTaskData(myTasks, info);
    //            if (isWritten)
    //            {
    //                System.out.println(brightGreen + "Hurrah!! new task, new challenge" + pureWhite);
    //            }
    //            else
    //            {
    //                System.out.println(brightRed + "Error: Something went wrong :(" + pureWhite);
    //            }
    //    }
    public static void addRandomTask(UserLogin info)
    {
        String name;
        boolean flag;
        System.out.println(brightPurple + "\t\t\t\t\t Random Task Hunt\n" + pureWhite);
        Tasks myTasks = new Tasks("");
        System.out.println(brightPeach + "Hit Enter if nothing appears" + pureWhite);

        try
        {
            input.nextLine();
            LinkedList<String> tasksTitleList = new LinkedList<>();
            int totalTasks = 0;

            System.out.print("How many tasks you wanna randomize? ");
            totalTasks = input.nextInt();
            input.nextLine();

            for (int i = 0; i < totalTasks; )
            {
                do
                {
                    flag = false;
                    System.out.print("Task " + (i + 1) + " : ");
                    name = input.nextLine().toLowerCase();

                    if (name.trim().isEmpty())
                    {
                        System.out.println(brightRed + "Error: Task's cannot be empty." + pureWhite);
                        flag = true;
                    }
                    else if (name.trim().length() < 3)
                    {
                        System.out.println(brightRed + "Error: Task's must be at least 3 characters long." + pureWhite);
                        flag = true;
                    }

                    // Retrieving task name into array
                    Database.retrieveDataIntoLinkedList(info);

                    // Comparison between task names
                    if (Database.storeTasksName.contains(name))
                    {
                        System.out.println(brightRed + "Error: Task's already exists." + pureWhite);
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
            System.out.println(" ");
            System.out.println("Today's target is: " + brightYellow + randomTask + pureWhite);
            // To add tasks into the database
            myTasks.setTaskTitle(randomTask);
            boolean isWritten = Database.addTaskIntoDatabase(myTasks, info);

            if (isWritten)
            {
                System.out.println(" ");
                System.out.println(brightGreen + "Hurrah!! new task, new challenge" + pureWhite);
            }
            else
            {
                System.out.println(brightRed + "Error: Something went wrong :(" + pureWhite);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ");
            System.out.println(brightRed + "Oops! That doesn't seem to be a valid input." + pureWhite);
            //e.printStackTrace(); // Print the exception details for debugging purposes
        }
    }

    //    public static void viewCalendar(UserLogin info)
    //    {
    //        Scanner scanner = new Scanner(System.in);
    //
    //        System.out.print("Enter year: ");
    //        int year = scanner.nextInt();
    //        boolean isCorrect = true;
    //        boolean isTrue = true;
    //        int month = 0;
    //        while (isCorrect)
    //        {
    //            System.out.print("Enter month (1-12): ");
    //            month = scanner.nextInt();
    //
    //            if (month < 1 || month > 12)
    //            {
    //                System.out.println(brightRed+ "Invalid month. Please enter a value between 1 and 12."+ pureWhite);
    //                //isCorrect=true;
    //            }
    //            else
    //            {
    //                isCorrect = false;
    //            }
    //        }
    //
    //        // Create a GregorianCalendar object
    //        Calendar calendar = new GregorianCalendar(year, month - 1, 1);
    //
    //        // Display tasks for the selected month
    //        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    //        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    //
    //        // Display tasks for the selected month
    //        boolean[] hasDataForDay = new boolean[maxDay + 1];
    //        Database.displayGeneralTaskInfoforCalendar(year, month, hasDataForDay, info);
    //
    //        // Print the calendar header
    //        System.out.println("\n" + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault()) + " " + year);
    //        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
    //
    //        // Print leading spaces
    //        for (int i = 0; i < startDay; i++)
    //        {
    //            System.out.print("    ");
    //        }
    //
    //        // Print the days of the month with colors based on data presence
    //        for (int i = 1; i <= maxDay; i++)
    //        {
    //            if (hasDataForDay[i])
    //            {
    //                System.out.print("\u001B[38;5;200m"); // color for days with data
    //            }
    //
    //            System.out.printf("%3d ", i);
    //
    //            if (hasDataForDay[i])
    //            {
    //                System.out.print(pureWhite); // Reset color after printing the day
    //            }
    //
    //            // Move to the next line if it's the last day of the week
    //            if ((startDay + i) % 7 == 0)
    //            {
    //                System.out.println();
    //            }
    //        }
    //        int day = 0;
    //        while (isTrue)
    //        {
    //            System.out.print("\nEnter a day to view tasks for that date (1-31): ");
    //            day = scanner.nextInt();
    //
    //            if (day < 1 || day > 31)
    //            {
    //                System.out.println(" ");
    //                System.out.println(brightRed + "Error: Invalid date. Please enter a value between 1 and 31." + pureWhite);
    //                //isTrue=true;
    //            }
    //            else
    //            {
    //                isTrue = false;
    //            }
    //        }
    //
    //        // Print the tasks for the selected date
    //        System.out.println(brightYellow + "\nTasks for " + day + "-" + month + "-" + year );
    //        System.out.println(" ");
    //        Database.printTasksForDate(year, month, day, info);
    //
    //        System.out.println("\n");
    //    }
    public static void viewCalendar(UserLogin info)
    {
        Scanner scanner = new Scanner(System.in);

        try
        {
            System.out.print("Year: ");
            int year = scanner.nextInt();
            boolean isCorrect = true;
            boolean isTrue = true;
            int month = 0;

            while (isCorrect)
            {
                System.out.print("Month (1-12): ");
                month = scanner.nextInt();

                if (month < 1 || month > 12)
                {
                    System.out.println(brightRed + "Invalid month. Please enter a value between 1 and 12." + pureWhite);
                }
                else
                {
                    isCorrect = false;
                }
            }

            // Create a GregorianCalendar object
            Calendar calendar = new GregorianCalendar(year, month - 1, 1);

            // Display tasks for the selected month
            int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            // Display tasks for the selected month
            boolean[] hasDataForDay = new boolean[maxDay + 1];
            Database.divideTasksAccordingToDates(year, month, hasDataForDay, info);

            // Print the calendar header
            System.out.println(brightYellow+ "\n" + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault()) + " " + year+pureWhite+"\n");
            System.out.println(brightYellow+"Sun Mon Tue Wed Thu Fri Sat"+pureWhite);
            System.out.print("\u001B[38;5;221m");
            // Print leading spaces
            for (int i = 0; i < startDay; i++)
            {
                System.out.print("    ");
            }

            // Print the days of the month with colors based on data presence
            for (int i = 1; i <= maxDay; i++)
            {
                if (hasDataForDay[i])
                {
                    System.out.print("\u001B[38;5;200m"); // color for days with data
                }

                System.out.printf("%3d ", i);

                if (hasDataForDay[i])
                {
                    System.out.print("\u001B[38;5;221m");
                }

                // Move to the next line if it's the last day of the week
                if ((startDay + i) % 7 == 0)
                {
                    System.out.println();
                }
            }
            System.out.println(pureWhite);
            int day = 0;
            System.out.println(" ");
            while (isTrue)
            {
                System.out.print("Enter a day to view tasks for that date (1-31): ");
                day = scanner.nextInt();

                if (day < 1 || day > 31)
                {
                    System.out.println(" ");
                    System.out.println(brightRed + "Error: Invalid date. Please enter a value between 1 and 31." + pureWhite);
                }
                else
                {
                    isTrue = false;
                }
            }

            // Print the tasks for the selected date
            System.out.println(brightYellow + "\nTasks for " + day + "-" + month + "-" + year);
            System.out.println(" ");
            Database.printTasksForDate(year, month, day, info);

//            System.out.println("\n");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println(brightRed + "Oops! That doesn't seem to be a valid input." + pureWhite);
           // e.printStackTrace(); // Print the exception details for debugging purposes
        }
    }

    public static void searchTask(UserLogin info)
    {
        System.out.println(brightPurple + "\t\t\t\t\t Search a Task \n" + pureWhite);

        String title;
        System.out.print("Task: ");
        title = input.nextLine();
        Database.retrieveDataIntoArray(info);
        Collections.sort(Database.storeTasksNameForSearch);
        int index = Collections.binarySearch(Database.storeTasksNameForSearch, title);
        if (index >= 0)
        {
            System.out.println(" ");
            System.out.println(brightGreen + "Task Found" + pureWhite);
        }
        else
        {
            System.out.println(" ");
            System.out.println(brightPeach+"No Task Found"+pureWhite);
        }
    }
}