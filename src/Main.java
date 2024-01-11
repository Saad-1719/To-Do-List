//import java.io.Console;
import java.util.Scanner;
import static java.lang.System.exit;
public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        LoginSignupHandler obj = new LoginSignupHandler();
        while (true)
        {
            // Display Main Menu.
            System.out.println();
            System.out.println(colorCodes.brightBlue + "\t\t\t\t\t Tᗩ-ᗪOᑎE " + colorCodes.pureWhite);
            System.out.println(" ");
            System.out.println(colorCodes.brightPurple + "\t\t\t  🌟 ᖴᖇOᗰ TO-ᗪO TO Tᗩ-ᗪᗩ!🌟" + colorCodes.pureWhite);
            System.out.println(" ");
            // Displaying options for the user to choose from
            System.out.println("[1]: Log in");
            System.out.println("[2]: Sign-Up, Join Our Community and Explore");
            System.out.println("[3]: Forgot Your Password? Reset Here");
            System.out.println("[4]: Say Goodbye");

            // Asking the user to choose an option
            //System.out.println("");
            System.out.println("\nWhat would you like to do?");
            System.out.print("Enter the number of your choice: ");

            // Get user choice from the main menu.
            int choice = Functions.getUserChoice(input);
            switch (choice)
            {
                // Logging In.
                case 1:
                    System.out.println(" ");
                    System.out.println(colorCodes.brightPurple + "< ------------------- Start Your Journey ------------------ >" + colorCodes.pureWhite);
                    System.out.println(" ");
                    System.out.print("Enter Username: ");
                    String username = input.nextLine().toLowerCase();
                    System.out.print("Enter Password: ");
                    String password = input.nextLine();

                    //password masking code, but not supported in intellij

//                    Console console = System.console();
//                    if (console == null)
//                    {
//                        System.out.println("Error: Unable to obtain console");
//                        return;
//                    }
//
//                    char[] password = console.readPassword();
//
//                    // Print asterisks for each character in the password
//                    System.out.print("You entered: ");
//                    for (char ch : password) {
//                        System.out.print("*");
//                    }
//                    System.out.println();
//                    String maskedPassword = new String(password);
                    // Checking Username & Password From Database.1

                    UserLogin info = new UserLogin(username, password);
                    boolean dataFound = LoginCheckFromDB.checkLogin(info);
                    System.out.println(" ");
                    if (dataFound)
                    {
                        System.out.println(colorCodes.brightPurple + "🌟 Bravo! Your Journey Begins Here" + colorCodes.partyEmoji + colorCodes.pureWhite);
                        // Greeting the user.
                        Functions.greetings(info);
                       // Functions.addPause();
                        while (true)
                        {
                            // Menu Within After Login.
                            System.out.println(" ");
                            System.out.println(colorCodes.brightBlue + "\t\t\t\t\t Tᗩ-ᗪOᑎE \n" + colorCodes.pureWhite);
                            // Displaying options for the user to choose from in a task-related context
                            System.out.println("📝 [1]: Start a New Task.");
                            System.out.println("🌟 [2]: Try something new with a Random Task.");
                            System.out.println("📔 [3]: Add Notes - Jot down your thoughts.");
                            System.out.println("✅ [4]: Complete a Task and Mark it Done.");
                            System.out.println("🔍 [5]: View Your Ongoing Tasks.");
                            System.out.println("🗒️ [6]: View Notes - Open your Notebook.");
                            System.out.println("🎉 [7]: View Completed Tasks - Celebrate Victories.");
                            System.out.println("🗑️ [8]: Delete a Note - Discard It.");
                            System.out.println("🕵️‍♂️ [9]: Search a Task - Begin a Hunt.");
                            System.out.println("🚪 [10]: Logout.");
                            System.out.print("🤔 Enter the number corresponding to your chosen option: ");
                            int selection = Functions.getUserChoice(input);
                            System.out.println();
                            switch (selection)
                            {
                                case 1:
                                   // Functions.addSpaces();
                                    Functions.addTask(info);
                                    //Functions.addPause();
                                    break;
                                case 2:
                                    //Functions.addSpaces();
                                    Functions.addRandomTask(info);
                                    //Functions.addPause();
                                    break;
                                case 3:
                                    //Functions.addSpaces();
                                    Functions.addNotes(info);
                                    //Functions.addPause();
                                    break;
                                case 4:
                                    //Functions.addSpaces();
                                    Functions.markTaskCompleted(info);
                                    //Functions.addPause();
                                    break;
                                case 5:
                                    //Functions.addSpaces();
                                    Functions.showOngoingTasks(info);
                                    //Functions.addPause();
                                    break;
                                case 6:
                                    //Functions.addSpaces();
                                    Functions.showAddedNotes(info);
                                    //Functions.addPause();
                                    break;
                                case 7:
                                    //Functions.addSpaces();
                                    Functions.showCompletedTasks(info);
                                    //Functions.addPause();
                                    break;
                                case 8:
                                    //Functions.addSpaces();
                                    Functions.deleteNotes(info);
                                    //Functions.addPause();
                                    break;
                                case 9:
                                    //Functions.addSpaces();
                                    Functions.searchTask(info);
                                    //Functions.addPause();
                                    break;
                                case 10:
                                    //Functions.addSpaces();
                                    System.out.println(colorCodes.brightPurple + "🚪 Farewell, You've successfully logged out. Until our paths cross again!" + colorCodes.pureWhite);
                                    //Functions.addPause();
                                    break;
                                default:
                                    //Functions.addSpaces();
                                    System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid path. 😕" + colorCodes.pureWhite);
                                    //Functions.addPause();
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
                        System.out.println(colorCodes.brightRed + "It seems there's a clash with your credentials. 😕" + colorCodes.pureWhite);
                    }
                    break;
                // Signing Up.
                case 2:
                    //Functions.addSpaces();
                    System.out.println(" ");
                    System.out.println(colorCodes.brightBlue + "\t\t\t\t\t Tᗩ-ᗪOᑎE \n" + colorCodes.pureWhite);
                    System.out.println(colorCodes.brightPurple + "🚀 Ready to start a new adventure? Sign up and join the journey now! 🌟" + colorCodes.pureWhite);
                    System.out.println(" ");
                    obj.signup();
                    break;
                case 3:
                    // Update Password.
                    //Functions.addSpaces();
                    System.out.println(" ");
                    System.out.println(colorCodes.brightBlue + "\t\t\t\t\t Tᗩ-ᗪOᑎE \n" + colorCodes.pureWhite);
                    obj.forgetPassword();
                    break;
                case 4:
                    // Depart and Exit.
                    System.out.println(" ");
                    System.out.println(colorCodes.brightPurple + "See you soon! 👋" + colorCodes.pureWhite);
                    exit(0);
                    break;
                default:
                    System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid input. 🤔" + colorCodes.pureWhite);
                    //Functions.addPause();
                    break;
            }
        }
    }
}