import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions
{
    static Scanner input = new Scanner(System.in);
    static LocalTime currentTime = LocalTime.now();
    static String username, createPassword, firstName, lastName;
    static int age = 0;
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
    public static void createUserName()
    {
        input.nextLine();
        while (true)
        {
            System.out.print("Enter a username: ");
            username = input.nextLine().toLowerCase();
            if (username.length() < 5)
            {
                System.out.println(redColorCode + "Error: Username must be atleast 5 characters long." + whiteColorCode);
                continue;
            }
            else if (username.contains(" "))
            {
                System.out.println(redColorCode + "Error: Username cannot contain spaces." + whiteColorCode);
                continue;
            }
            else if (username.length() > 20)
            {
                System.out.println(redColorCode + "Error: Username must be less than 20 characters." + whiteColorCode);
                continue;
            }
            // to check if username has letters
            boolean hasLetters = false;
            for (int i = 0; i < username.length(); i++)
            {
                if (Character.isLetter(username.charAt(i)))
                {
                    hasLetters = true;
                }
            }
            if (!hasLetters)
            {
                System.out.println(redColorCode + "Error: Username does not contain letters and digits." + whiteColorCode);
                continue;
            }
            UserSignup info = new UserSignup(username);
            boolean isAvailable = SignUpIntoDB.userCheck(info);
            if (!isAvailable)
            {
                System.out.println(redColorCode + "Error: Username Unavailable." + whiteColorCode);
            }
            else
            {
                System.out.println(greenColorCode + "Username Added!" + whiteColorCode);
                break;
            }
        }
    }

    // Creating New Password.
    public static void introducePassword()
    {
        boolean isValidPassword = false;
        while (!isValidPassword)
        {
            System.out.print("Create a password: ");
            createPassword = input.nextLine();
            if (createPassword.length() < 5)
            {
                System.out.println(redColorCode + "Error: Password must be a minimum of 5 characters." + whiteColorCode);
            }
            else if (createPassword.length() > 10)
            {
                System.out.println(redColorCode + "Error: Password must be a maximum of 10 characters." + whiteColorCode);
            }
            else if (createPassword.contains(" "))
            {
                System.out.println(redColorCode + "Error: Password cannot contain spaces." + whiteColorCode);
            }
            else
            {
                System.out.print("Confirm password: ");
                String confirmPassword;
                confirmPassword = input.nextLine();
                if (createPassword.equals(confirmPassword))
                {
                    isValidPassword = true;
                }
                else
                {
                    System.out.println(redColorCode + "Error: Password has not been confirmed." + whiteColorCode);
                }
            }
        }
        System.out.println(greenColorCode + "Password Added!" + whiteColorCode);
    }

    // Creating New First Name.
    public static void introduceFirstName()
    {
        boolean flag = true;
        while (flag)
        {
            System.out.print("Enter First name: ");
            firstName = input.nextLine().trim().toLowerCase();
            if (firstName.length() < 3)
            {
                System.out.println(redColorCode + "Error: First Name must be atleast 3 characters long." + whiteColorCode);
            }
            else if (firstName.length() > 30)
            {
                System.out.println(redColorCode + "Error: First Name must be less than 30 characters." + whiteColorCode);
            }
            else if (firstName.matches(".*[^a-zA-Z0-9\\s].*"))
            {
                System.out.println(redColorCode + "Error: First name contains special characters." + whiteColorCode);
            }
            else if (firstName.matches(".*\\d+.*"))
            {
                System.out.println(redColorCode + "Error: First name contains digits." + whiteColorCode);
            }
            else
            {
                flag = false;
                System.out.println(greenColorCode + "First Name Added!" + whiteColorCode);
            }
        }
    }

    // Creating New Last Name.
    public static void introduceLastName()
    {
        boolean flag = true;
        while (flag)
        {
            System.out.print("Enter Last name: ");
            lastName = input.nextLine().trim().toLowerCase();
            if (lastName.length() < 3)
            {
                System.out.println(redColorCode + "Error: Last Name must be atleast 3 characters long." + whiteColorCode);
            }
            else if (lastName.length() > 30)
            {
                System.out.println(redColorCode + "Error: Last Name must be less than 30 characters." + whiteColorCode);
            }
            else if (lastName.matches(".*[^a-zA-Z0-9\\s].*"))
            {
                System.out.println(redColorCode + "Error: Last name contains special characters." + whiteColorCode);
            }
            else if (lastName.matches(".*\\d+.*"))
            {
                System.out.println(redColorCode + "Error: Last name contains digits." + whiteColorCode);
            }
            else
            {
                flag = false;
                System.out.println(greenColorCode + "Last Name Added!" + whiteColorCode);
            }
        }
    }

    // Setting New User's Age.
    public static void introduceAge()
    {
        boolean flag = true;
        while (flag)
        {
            System.out.print("Enter Age: ");
            try
            {
                age = input.nextInt();
                input.nextLine();
                if (age < 5 || age > 100)
                {
                    System.out.println(redColorCode + "Error: A valid age must be entered." + whiteColorCode);
                }
                else
                {
                    flag = false;
                    System.out.println(greenColorCode + "Age Added!" + whiteColorCode);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(redColorCode + "Error: The input is out of bounds." + whiteColorCode);
                input.nextLine();
            }
        }
    }

    //signup function to add new user
    public static void signup()
    {
        System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
        //function to create username
        createUserName();
        //function to introduce password
        introducePassword();
        //function to introduce first name
        introduceFirstName();
        //function to introduce last name
        introduceLastName();
        //function to introduce age
        introduceAge();
        //assigning data
        UserSignup info = new UserSignup(username, createPassword, firstName, lastName, age);
        //to check if signup is completed
        boolean isSignUp = SignUpIntoDB.signUp(info);
        if (isSignUp)
        {
            System.out.println(" ");
            System.out.println(greenColorCode + "Completed, Thank you for Signing up! :)" + whiteColorCode);
            Main.main(new String[]{});
        }
        else
        {
            System.out.println(redColorCode + "Error: Something went wrong :(" + whiteColorCode);
        }
    }

    // to forget password
    public static void forgetPassword()
    {
        try
        {
            System.out.print(mintColorCode + "\t\t\t\t\t Forget Password \n" + whiteColorCode);
            System.out.print(mintColorCode + "\t\t\t\t\t We'll help you recover \n" + whiteColorCode);
            System.out.println(" ");
            System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
            input.nextLine();
            do
            {
                System.out.print("Enter a username: ");
                username = input.nextLine().toLowerCase();
                if (username.isEmpty())
                {
                    System.out.println(redColorCode + "Error: Input Field mustn't be empty." + whiteColorCode);
                }
            }
            while (username.isEmpty());
            do
            {
                System.out.print("Enter First name: ");
                firstName = input.nextLine().toLowerCase();
                if (firstName.isEmpty())
                {
                    System.out.println(redColorCode + "Error: Input Field mustn't be empty." + whiteColorCode);
                }
            }
            while (firstName.isEmpty());
            do
            {
                System.out.print("Enter Last name: ");
                lastName = input.nextLine().toLowerCase();
                if (lastName.isEmpty())
                {
                    System.out.println(redColorCode + "Error: Input Field mustn't be empty." + whiteColorCode);
                }
            }
            while (lastName.isEmpty());
            System.out.print("Enter Age: ");
            age = input.nextInt();
        }
        catch (Exception e)
        {
            System.out.println(redColorCode + "Error: The input is out of bounds." + whiteColorCode);
            input.nextLine();
            return;
        }
        //matching credentials from database
        boolean isMatched = password.matchInfo(username, firstName, lastName, age);
        if (isMatched)
        {
            System.out.println(greenColorCode + "Credentials have been matched!" + whiteColorCode);
            System.out.println(" ");
            input.nextLine();
            // Update Old Password.
            introducePassword();
            //updating password in the database
            boolean isUpdated = password.updatePassword(username, createPassword, firstName, lastName, age);
            if (isUpdated)
            {
                System.out.println(greenColorCode + "Completed, Password has been updated!" + whiteColorCode);
                System.out.println(" ");
            }
            else
            {
                System.out.println(redColorCode + "Error: Password has not been updated." + whiteColorCode);
                System.out.println(" ");
            }
        }
        else
        {
            System.out.println(redColorCode + "Error: Credentials have not been matched." + whiteColorCode);
            System.out.println(" ");
        }
    }

    // to add a task into db
    public static void addTask(UserLogin info)
    {
        String name;
        String description;
        String goal;
        int completedDays = 0;
       // String progressBar = "▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯";
        boolean flag;
        System.out.println(mintColorCode + "\t\t\t\t\t Add a Habit \n" + whiteColorCode);
        Activity myActivity = new Activity("", "");
        System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
        boolean canAddHabit = Database.habitCounter(info);
        if (canAddHabit)
        {
            input.nextLine();
            do
            {
                flag = false;
                System.out.print("Task Title please: ");
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
                // to check if the habit name contains special characters
//                else if (name.trim().matches(".*[^a-zA-Z0-9\\s].*"))
//                {
//                    System.out.println(redColorCode + "Error: Task's Title contains special characters." + whiteColorCode);
//                    flag = true;
//                }
                else if (name.length() > 50)
                {
                    System.out.println(redColorCode + "Error: Habit name must be less than 50 characters." + whiteColorCode);
                    flag = true;
                }
//                else if (name.contains(" "))
//                {
//                    System.out.println(redColorCode + "Error: Habit name contains spaces" + whiteColorCode);
//                    flag = true;
//                }
                // to check if the habit name contains digits
//                boolean containsDigits = false;
//                for (int i = 0; i < name.length(); i++)
//                {
//                    if (Character.isDigit(name.charAt(i)))
//                    {
//                        containsDigits = true;
//                        flag = true;
//                        break;
//                    }
//                }
//                if (containsDigits)
//                {
//                    System.out.println(redColorCode + "Error: Habit name contains digits" + whiteColorCode);
//                }
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
            //habit description
            do
            {
                flag = false;
                System.out.print("Task's description please? ");
                description = input.nextLine();
                if (description.trim().isEmpty())
                {
                    System.out.println(redColorCode + "Error: Task's description cannot be empty." + whiteColorCode);
                    flag = true;
                }
                else if (description.trim().length() < 3)
                {
                    System.out.println(redColorCode + "Error: Task's description must be at least 3 characters long." + whiteColorCode);
                    flag = true;
                }
                else if (description.length() > 200)
                {
                    System.out.println(redColorCode + "Error: Task's description must be less than 200 characters." + whiteColorCode);
                    flag = true;
                }
            }
            while (flag);
            
            //habit goal
//            do
//            {
//                flag = false;
//                System.out.print("What is your goal? ");
//                goal = input.nextLine();
//                if (goal.trim().isEmpty())
//                {
//                    System.out.println(redColorCode + "Error: Habit goal cannot be empty." + whiteColorCode);
//                    flag = true;
//                }
//                else if (goal.trim().length() < 3)
//                {
//                    System.out.println(redColorCode + "Error: Habit goal must be at least 3 characters long." + whiteColorCode);
//                    flag = true;
//                }
//                else if (goal.length() > 100)
//                {
//                    System.out.println(redColorCode + "Error: Habit goal must be less than 100 characters." + whiteColorCode);
//                    flag = true;
//                }
//            }
            
            while (flag);
            // to add habit into db
            myActivity.setName(name);
            myActivity.setDescription(description);
           // myActivity.setGoal(goal);
            // setting default values of completed days and progress bar
            //myActivity.setCompletedDays(completedDays);
            //myActivity.setProgressBar(progressBar);
            boolean isWritten = Database.writeData(myActivity, info);
            if (isWritten)
            {
                System.out.println(greenColorCode + "Completed, Data Added!" + whiteColorCode);
            }
            else
            {
                System.out.println(redColorCode + "Error: Something went wrong :(" + whiteColorCode);
            }
        }
        else
        {
            System.out.println(redColorCode + "Error: You can only add 5 habits at a time." + whiteColorCode);
        }
    }

    // to update existing habit
    public static void updateHabit(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Enhance Progress \n" + whiteColorCode);
        // to get the user id
        int userId = Database.activeUserId(info);
        // to check if the habit exists
        boolean isDataExist = Database.displayGeneralHabitInfo(info);
        if (isDataExist)
        {
            String progressBar = "▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯▯";
            int completedDays;
            int habitId;
            String achievement;
            System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
            input.nextLine();
            System.out.println("Which Habit would you like to update? ");
            System.out.print("Enter Habit's ID: ");
            try
            {
                habitId = input.nextInt();
                input.nextLine();
                boolean flag = false;
                // to check if the habit id is valid
                boolean isHabitExist = Database.checkHabitId(habitId, userId);
                if (isHabitExist)
                {
                    do
                    {
                        // fetching number of day have been completed so far
                        int habitDays = Database.habitDays(habitId);
                        System.out.print("How many days have been completed so far? ");
                        completedDays = input.nextInt();
                        if (completedDays <= habitDays)
                        {
                            System.out.println(redColorCode + "Error: You can't decline your progress days." + whiteColorCode);
                            flag = true;
                        }
                        else
                        {
                            try
                            {
                                if (completedDays >= 1 && completedDays <= 29)
                                {
                                    StringBuilder progressBarBuilder = new StringBuilder(progressBar);
                                    for (int i = 0; i < completedDays; i++)
                                    {
                                        progressBarBuilder.setCharAt(i, '▮');
                                    }
                                    progressBar = progressBarBuilder.toString();
                                    boolean ckh = Database.updateData(habitId, progressBar, completedDays);
                                    if (ckh)
                                    {
                                        System.out.println(greenColorCode + "Habit has been updated!" + whiteColorCode);
                                    }
                                    break;
                                }
                                // to check if the habit is completed
                                else if (completedDays == 30)
                                {
                                    flag = false;
                                    System.out.println(" ");
                                    System.out.println(mintColorCode + "Congratulations, you've successfully completed your habit!" + whiteColorCode);
                                    System.out.print("What are your Achievements? ");
                                    input.nextLine();
                                    achievement = input.nextLine();
                                    do
                                    {
                                        if (achievement.trim().isEmpty())
                                        {
                                            System.out.println(redColorCode + "Error: Habit achievement cannot be empty." + whiteColorCode);
                                            flag = true;
                                        }
                                        else if (achievement.trim().length() < 3)
                                        {
                                            System.out.println(redColorCode + "Error: Habit achievement must be at least 3 characters long." + whiteColorCode);
                                            flag = true;
                                        }
                                        else if (achievement.length() > 150)
                                        {
                                            System.out.println(redColorCode + "Error: Habit achievement must be less than 150 characters." + whiteColorCode);
                                            flag = true;
                                        }
                                        else
                                        {
                                            //to fetch habit name
                                            String habitName = Database.habitName(habitId);
                                            //to delete habit from general habit table
                                            Database.deleteData(habitId);
                                            //to add habit into history table
                                            Activity data = new Activity(habitName, achievement);
                                            boolean chk = Database.writeHistory(info, data);
                                            if (chk)
                                            {
                                                System.out.println(greenColorCode + "Habit has been updated!" + whiteColorCode);
                                            }
                                            else
                                            {
                                                System.out.println(redColorCode + "Error: Something went wrong :(" + whiteColorCode);
                                            }
                                        }
                                    }
                                    while (flag);
                                }
                                else
                                {
                                    System.out.println(redColorCode + "Error: Completed Days must range between 1 - 30." + whiteColorCode);
                                    flag = true;
                                }
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println(" ");
                                System.out.println(redColorCode + "Error: The input is out of bounds." + whiteColorCode);
                                input.nextLine();
                            }
                        }
                    }
                    while (flag);
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(redColorCode + "Error: Incorrect Habit ID." + whiteColorCode);
                    System.out.println(" ");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(" ");
                System.out.println(redColorCode + "Error: Input out of bounds" + whiteColorCode);
                input.nextLine();
            }
        }
    }

    // to show habit from db
    public static void showHabit(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Ongoing Habits \n" + whiteColorCode);
        Database.displayCompleteHabitInfo(info);
    }

    // to delete habit from db
    public static void deleteHabit(UserLogin info)
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Delete a Habit \n" + whiteColorCode);
        // to check if the habit exists
        boolean isDataExists = Database.displayGeneralHabitInfo(info);
        if (isDataExists)
        {
            int delId;
            // data delete
            System.out.println(redColorCode + "Press Enter to continue if no input option appears" + whiteColorCode);
            input.nextLine();
            System.out.println("Which Habit would you like to delete? ");
            System.out.print("Enter Habit's ID: ");
            try
            {
                delId = input.nextInt();
                int userID = Database.activeUserId(info);
                // to check if the habit id is valid
                boolean hasHabitFound = Database.checkHabitId(delId, userID);
                if (hasHabitFound)
                {
                    String habitName = Database.habitName(delId);
                    Activity data = new Activity();
                    data.setName(habitName);
                    //to write data into a deleted habit table
                    Database.writeDeleted(info, data);
                    // to delete habit from general habit table
                    boolean ckh = Database.deleteData(delId);
                    if (ckh)
                    {
                        System.out.println(greenColorCode + "Habit has been deleted!" + whiteColorCode);
                    }
                }
                else
                {
                    System.out.println(" ");
                    System.out.println(redColorCode + "Error: Incorrect Habit ID." + whiteColorCode);
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

    //Welcome notes
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

    //quotes
    public static void quotes()
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Quote of the day \n" + whiteColorCode);
        ArrayList<String> inspiration = new ArrayList<>();
        inspiration.add("It's the smallest actions everyday that determine who you are ...");
        inspiration.add("All big things come from small beginnings. The seed of every habit is a single, tiny decision.");
        inspiration.add("Break bad habits, Build good habits.");
        inspiration.add("Habits stay with you, even when you don't have the motivation.");
        inspiration.add("Good habits formed at youth make all the difference.");
        inspiration.add("Consistency is what transforms average into excellence.");
        inspiration.add("Success isn't always about greatness. It's about consistency. Consistent hard work leads to success greatness will come.");
        inspiration.add("Continuous improvement is better than delayed.");
        Random rand = new Random();
        int randIndex = rand.nextInt(inspiration.size());
        String randomString = inspiration.get(randIndex);
        System.out.println(yellowColor + randomString + whiteColorCode);
    }

//    //to show history
//    public static void showHistory(UserLogin info)
//    {
//        System.out.println(mintColorCode + "\t\t\t\t\t Completed Habit History \n" + whiteColorCode);
//        Database.displayHistory(info);
//    }
//
//    //to show user info
//    public static void showUserInfo(UserLogin info)
//    {
//        System.out.println(mintColorCode + "\t\t\t\t\t User's Info \n" + whiteColorCode);
//        Database.showUserInfo(info);
//    }
//
//    //to show deleted habit
//    public static void showDeletedHabit(UserLogin info)
//    {
//        System.out.println(mintColorCode + "\t\t\t\t\t Deleted Habit Habit \n" + whiteColorCode);
//        Database.displayDeletedHabitInfo(info);
//    }

    //to show developer info
    public static void developerInfo()
    {
        System.out.println(mintColorCode + "\t\t\t\t\t Developers' Info \n" + whiteColorCode);
        System.out.print(yellowColor);
        System.out.println("Name: Muhammad Saad Jamil");
        System.out.println("ID: 01-131222-023");
        System.out.println("Database Designer");
        System.out.println(" ");
        System.out.println("Name: Zainab Asif");
        System.out.println("ID: 01-131222-050");
        System.out.println("Habit Functionality Designer");
        System.out.println(" ");
        System.out.println("Name: Faaiz Muzzammil");
        System.out.println("ID: 01-131222-054");
        System.out.println("Login and Signup Designer");
        System.out.print(whiteColorCode);
    }
}