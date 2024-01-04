import java.time.LocalTime;
import java.util.*;

public class Functions
{
    static final Scanner input = new Scanner(System.in);
    static final LocalTime currentTime = LocalTime.now();

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
            System.out.println(colorCodes.brightRed + "Error: The input is out of bounds." + colorCodes.pureWhite);
            input.nextLine();
        }
        return choice;
    }
    // to add new user

    public static void addTask(UserLogin info)
    {
        String name;
        boolean flag;
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Add a Task \n" + colorCodes.pureWhite);
        Tasks myTasks = new Tasks();
        System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);
        input.nextLine();
        do
        {
            flag = false;
            System.out.print("Task: ");
            name = input.nextLine().toLowerCase();
            if (name.trim().isEmpty())
            {
                System.out.println(colorCodes.brightRed + "Error: Task's field cannot be empty." + colorCodes.pureWhite);
                flag = true;
            }
            else if (name.trim().length() < 3)
            {
                System.out.println(colorCodes.brightRed + "Error: Task's field must be at least 3 characters long." + colorCodes.pureWhite);
                flag = true;
            }
            else if (name.length() > 80)
            {
                System.out.println(colorCodes.brightRed + "Error: Task must be less than 80 characters." + colorCodes.pureWhite);
                flag = true;
            }
            Database.retrieveDataIntoLinkedList(info);
            //comparing task names with existing ongoing tasks
            if (Database.storeTasksName.contains(name))
            {
                System.out.println(colorCodes.brightRed + "Error: Task already exists." + colorCodes.pureWhite);
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
            System.out.println(colorCodes.brightGreen + "Hurrah!! new task, new challenge" + colorCodes.pureWhite);
        }
        else
        {
            System.out.println(colorCodes.brightRed + "Error: Something went wrong :(" + colorCodes.pureWhite);
        }
    }

    //add notes to db
    public static void addNotes(UserLogin info)
    {
        String name;
        String description;
        boolean flag;
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Add Notes \n" + colorCodes.pureWhite);
        Notes myNotes = new Notes("", "");
        System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);
        input.nextLine();
        do
        {
            flag = false;
            System.out.print("Title: ");
            name = input.nextLine().toLowerCase();
            if (name.trim().isEmpty())
            {
                System.out.println(colorCodes.brightRed + "Error: Note's Title cannot be empty." + colorCodes.pureWhite);
                flag = true;
            }
            else if (name.trim().length() < 3)
            {
                System.out.println(colorCodes.brightRed + "Error: Note's Title must be at least 3 characters long." + colorCodes.pureWhite);
                flag = true;
            }
            else if (name.length() > 50)
            {
                System.out.println(colorCodes.brightRed + "Error: Note's title must be less than 50 characters." + colorCodes.pureWhite);
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
                System.out.println(colorCodes.brightRed + "Error: Note's description cannot be empty." + colorCodes.pureWhite);
                flag = true;
            }
            else if (description.trim().length() < 3)
            {
                System.out.println(colorCodes.brightRed + "Error: Note's description must be at least 3 characters long." + colorCodes.pureWhite);
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
            System.out.println(colorCodes.brightGreen + "Bravo!! Memory ++ " + colorCodes.pureWhite);
        }
        else
        {
            System.out.println(colorCodes.brightRed + "Error: Something went wrong :(" + colorCodes.pureWhite);
        }
    }

    // to show habit from db
    public static void showOngoingTasks(UserLogin info)
    {
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Ongoing Tasks \n" + colorCodes.pureWhite);
        boolean isData=Database.isTaskExists(info);
        if(isData)
        {
            viewCalendar(info);
        }
    }

    public static void showCompletedTasks(UserLogin info)
    {
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Accomplished Tasks \n" + colorCodes.pureWhite);
        Database.displayAccomplishedTasks(info);
    }

    public static void showAddedNotes(UserLogin info)
    {
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Notes \n" + colorCodes.pureWhite);
        Database.displayWrittenNotes(info);
    }

    // to mark a task completed
    public static void markTaskCompleted(UserLogin info)
    {
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Conquer a Task \n" + colorCodes.pureWhite);
        // to check if the task exists
        boolean isDataExists = Database.displayTasks(info);
        if (isDataExists)
        {
            int delId;
            // data delete
            System.out.println(" ");
            System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);
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
                        System.out.println(colorCodes.brightGreen + "Task Conquered, Mission Accomplished!!" + colorCodes.pureWhite);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(colorCodes.brightRed + "Error: Incorrect task ID." + colorCodes.pureWhite);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid input." + colorCodes.pureWhite);
                input.nextLine();
            }
        }
    }

    public static void deleteNotes(UserLogin info)
    {
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Delete Notes \n" + colorCodes.pureWhite);
        // to check if notes exists
        boolean isDataExists = Database.displayNotes(info);
        if (isDataExists)
        {
            int delId;
            System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);
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
                        System.out.println(colorCodes.brightGreen + "Note Successfully Erased!" + colorCodes.pureWhite);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(colorCodes.brightRed + "Error: Incorrect Note ID." + colorCodes.pureWhite);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(colorCodes.brightRed + "Error: The input is out of bounds" + colorCodes.pureWhite);
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

    @SuppressWarnings("DuplicatedCode")
    public static void addRandomTask(UserLogin info)
    {
        String name;
        boolean flag;
        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Random Task Hunt\n" + colorCodes.pureWhite);
        Tasks myTasks = new Tasks("");
        System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);

        try
        {
            input.nextLine();
            LinkedList<String> tasksTitleList = new LinkedList<>();
            int totalTasks;

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
                        System.out.println(colorCodes.brightRed + "Error: Task's cannot be empty." + colorCodes.pureWhite);
                        flag = true;
                    }
                    else if (name.trim().length() < 3)
                    {
                        System.out.println(colorCodes.brightRed + "Error: Task's must be at least 3 characters long." + colorCodes.pureWhite);
                        flag = true;
                    }

                    // Retrieving task name into array
                    Database.retrieveDataIntoLinkedList(info);

                    // Comparison between task names
                    if (Database.storeTasksName.contains(name))
                    {
                        System.out.println(colorCodes.brightRed + "Error: Task's already exists." + colorCodes.pureWhite);
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
            System.out.println("Today's target is: " + colorCodes.brightYellow + randomTask + colorCodes.pureWhite);
            // To add tasks into the database
            myTasks.setTaskTitle(randomTask);
            boolean isWritten = Database.addTaskIntoDatabase(myTasks, info);

            if (isWritten)
            {
                System.out.println(" ");
                System.out.println(colorCodes.brightGreen + "Hurrah!! new task, new challenge" + colorCodes.pureWhite);
            }
            else
            {
                System.out.println(colorCodes.brightRed + "Error: Something went wrong :(" + colorCodes.pureWhite);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ");
            System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid input." + colorCodes.pureWhite);
            //e.printStackTrace(); // Print the exception details for debugging purposes
        }
    }

    @SuppressWarnings("MagicConstant")
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
                    System.out.println(colorCodes.brightRed + "Invalid month. Please enter a value between 1 and 12." + colorCodes.pureWhite);
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
            System.out.println(colorCodes.brightYellow+ "\n" + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault()) + " " + year+colorCodes.pureWhite+"\n");
            System.out.println(colorCodes.brightYellow+"Sun Mon Tue Wed Thu Fri Sat"+colorCodes.pureWhite);
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
            System.out.println(colorCodes.pureWhite);
            int day = 0;
            System.out.println(" ");
            while (isTrue)
            {
                System.out.print("Enter a day to view tasks for that date (1-31): ");
                day = scanner.nextInt();

                if (day < 1 || day > 31)
                {
                    System.out.println(" ");
                    System.out.println(colorCodes.brightRed + "Error: Invalid date. Please enter a value between 1 and 31." + colorCodes.pureWhite);
                }
                else
                {
                    isTrue = false;
                }
            }

            // Print the tasks for the selected date
            System.out.println(colorCodes.brightYellow + "\nTasks for " + day + "-" + month + "-" + year);
            System.out.println(" ");
            Database.printTasksForDate(year, month, day, info);

//            System.out.println("\n");
        }
        catch (Exception e)
        {
            System.out.println();
            System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid input." + colorCodes.pureWhite);
           // e.printStackTrace(); // Print the exception details for debugging purposes
        }
    }

    public static void searchTask(UserLogin info)
    {

        System.out.println(colorCodes.brightPurple + "\t\t\t\t\t Search a Task \n" + colorCodes.pureWhite);
        System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);
input.nextLine();
        String title;
        System.out.print("Task: ");
        title = input.nextLine();
        Database.retrieveDataIntoArray(info);
        Collections.sort(Database.storeTasksNameForSearch);
        int index = Collections.binarySearch(Database.storeTasksNameForSearch, title);
        if (index >= 0)
        {
            System.out.println(" ");
            System.out.println(colorCodes.brightGreen + "Task Found" + colorCodes.pureWhite);
        }
        else
        {
            System.out.println(" ");
            System.out.println(colorCodes.brightPeach+"No Task Found"+colorCodes.pureWhite);
        }
    }
}