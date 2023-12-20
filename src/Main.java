import java.util.Scanner;

import static java.lang.System.exit;
public class Main
{
    static String whiteColorCode = "\u001B[97m";
    static String redColorCode = "\u001B[31m";
    static String mintColorCode = "\u001B[38;5;85m";
    static String partyEmoji = "\uD83C\uDF89"; //🎉
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        LoginSignupHandler obj=new LoginSignupHandler();
        boolean isRunning = true;
        while (isRunning)
        {
            // Display Main Menu.
            System.out.println();
            System.out.println(mintColorCode + "\t\t\t\t Dotify"+whiteColorCode);
            System.out.println(" ");
            System.out.println("🌟Your Personal Productivity Companion! 🌟");
            System.out.println(" ");
            // Displaying options for the user to choose from
            System.out.println("[1]: Start Your Journey - Log in");
            System.out.println("[2]: Join Our Community and Explore");
            System.out.println("[3]: Forgot Your Password? Reset Here");
            System.out.println("[4]: Say Goodbye & Exit the Program");

            // Asking the user to choose an option
            System.out.print("What would you like to do? Enter the number of your choice: ");

            // Get user choice from the main menu.
            int choice = Functions.getUserChoice(input);
            switch (choice)
            {
                // Logging In.
                case 1:
                    System.out.println(" ");
                    System.out.println(mintColorCode+"< ------------------- Let's Do This ------------------ >"+whiteColorCode);
                    System.out.println(" ");
                    System.out.print("Enter Username: ");
                    String username = input.nextLine().toLowerCase();
                    System.out.print("Enter Password: ");
                    String password = input.nextLine();
                    // Checking Username & Password From Database.
                    UserLogin info = new UserLogin(username, password);
                    boolean dataFound = LoginCheckFromDB.checkLogin(info);
                    System.out.println(" ");
                    if (dataFound)
                    {
                        System.out.println(mintColorCode + "🌟 Bravo! Your Quest Begins Here, Explorer!" + partyEmoji + whiteColorCode);
                        // Greeting the user.
                        Functions.greetings(info);
                        while (true)
                        {
                            // Menu Within After Login.
                            System.out.println(" ");
                            System.out.println(mintColorCode + "\t\t\t\t\tDOTIFY\n" + whiteColorCode);
                            // Displaying options for the user to choose from in a task-related context
                            System.out.println("📝 [1]: Start a New Task.");
                            System.out.println("🌟 [2]: Try something new with a Random Task.");
                            System.out.println("📔 [3]: Add Notes - Jot down your thoughts.");
                            System.out.println("✅ [4]: Complete a Task and Mark it Done.");
                            System.out.println("🔍 [5]: Check on Your Ongoing Tasks.");
                            System.out.println("🎉 [6]: View Completed Tasks - Celebrate Victories.");
                            System.out.println("🗒️ [7]: View Notes - Open your Notebook.");
                            System.out.println("🕵️‍♂️ [8]: Search a Task - Begin a Hunt.");
                            System.out.println("🗑️ [9]: Delete a Note - Discard It.");
                            System.out.println("🚪 [10]: Logout and Finish Your Session.");
                            System.out.print("🤔 Enter the number corresponding to your chosen option: ");

                            int selection = Functions.getUserChoice(input);
                            System.out.println();
                            switch (selection)
                            {
                                case 1:
                                    Functions.addTask(info);
                                    break;
                                case 2:
                                    Functions.addRandomTask(info);
                                    break;
                                case 3:
                                    Functions.addNotes(info);
                                    break;
                                case 4:
                                    Functions.markTaskCompleted(info);
                                    break;
                                case 5:
                                    Functions.showOngoingTasks(info);
                                    break;
                                case 6:
                                    Functions.showCompletedTasks(info);
                                    break;
                                case 7:
                                    Functions.showAddedNotes(info);
                                    break;
                                case 8:
                                    Functions.searchTask(info);
                                    break;
                                case 9:
                                    Functions.deleteNotes(info);
                                    break;
                                case 10:
                                    System.out.println(mintColorCode+"🚪 Farewell, You've successfully logged out. Until our paths cross again!"+whiteColorCode);
                                    break;
                                default:
                                    System.out.println(redColorCode + "Oops! That doesn't seem to be a valid path." + whiteColorCode);
                                    break;
                            }
                            if (selection == 10)
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("It seems there's a clash with your credentials. \uD83D\uDE15");
                    }
                    break;
                // Signing Up.
                case 2:
                    System.out.println(" ");
                    System.out.println(mintColorCode+"🚀 Ready to start a new adventure? Sign up and join the journey now! 🌟"+whiteColorCode);
                    System.out.println(" ");
                    obj.signup();
                    break;
                case 3:
                    // Update Password.
                    obj.forgetPassword();
                    break;
                case 4:
                    // Depart and Exit.
                    isRunning = false;
                    System.out.println(" ");
                    System.out.println(mintColorCode + "See you soon! " + whiteColorCode);
                    exit(0);
                    break;
                default:
                    System.out.println(redColorCode + "Oops! That doesn't seem to be a valid path." + whiteColorCode);
                    break;
            }
        }
        input.close();
    }
}