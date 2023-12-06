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
        boolean isRunning = true;
        while (isRunning)
        {
            // Display Main Menu.
            System.out.println(mintColorCode + "\t\t\t\t\t ＷＥＬＣＯＭＥ ＴＯ ΛＴＬΛＳＭＩＮＤ" + whiteColorCode);
            System.out.println(" ");
            System.out.println("[1]: Login and Step Into Your World.");
            System.out.println("[2]: Join Us.");
            System.out.println("[3]: Forgot your password.");
            System.out.println("[4]: Depart & Exit.");
            System.out.print("Enter Your Desired Choice: ");
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
                        System.out.println(mintColorCode + "Congratulations, You've made it in!" + partyEmoji + whiteColorCode);
                        // Greeting the user.
                        Functions.greetings(info);
                        while (true)
                        {
                            // Menu Within After Login.
                            System.out.println(" ");
                            System.out.println(mintColorCode + "\t\t\t\t\t ΛＴＬΛＳＭＩＮＤ \n" + whiteColorCode);
                            System.out.println("[1]: Introduce a New Task.");
//                            System.out.println("[2]: Showcase Current Habits.");
//                            System.out.println("[3]: Enhance Progress.");
//                            System.out.println("[4]: Delete a Habit.");
//                            System.out.println("[5]: View Completed Habit History.");
//                            System.out.println("[6]: View Deleted Habit History.");
                            System.out.println("[7]: View Inspiring Quote of the Day.");
                            System.out.println("[8]: View User Information.");
                            System.out.println("[9]: Learn About the Team.");
                            System.out.println("[10]: Logout and Disembark.");
                            System.out.print("Enter your choice: ");
                            int selection = Functions.getUserChoice(input);
                            System.out.println();
                            switch (selection)
                            {
                                case 1:
                                    Functions.addTask(info);
                                    break;
//                                case 2:
//                                    Functions.showHabit(info);
//                                    break;
//                                case 3:
//                                    Functions.updateHabit(info);
//                                    break;
//                                case 4:
//                                    Functions.deleteHabit(info);
//                                    break;
//                                case 5:
//                                    Functions.showHistory(info);
//                                    break;
//                                case 6:
//                                    Functions.showDeletedHabit(info);
//                                    break;
                                case 7:
                                    Functions.quotes();
                                    break;
//                                case 8:
//                                    Functions.showUserInfo(info);
//                                    break;
                                case 9:
                                    Functions.developerInfo();
                                    break;
                                case 10:
                                    System.out.println(mintColorCode + "Logged Out..." + whiteColorCode);
                                    break;
                                default:
                                    System.out.println(redColorCode + "Error: Invalid Menu Choice" + whiteColorCode);
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
                        System.out.println(redColorCode + "Credentials Clash: Authentication Aborted! \uD83D\uDE15" + whiteColorCode);
                    }
                    break;
                // Signing Up.
                case 2:
                    System.out.println(" ");
                    System.out.println(mintColorCode+"< ------------------- A New World, A New Journey ------------------ >"+whiteColorCode);
                    System.out.println(" ");
                    Functions.signup();
                    break;
                case 3:
                    // Update Password.
                    Functions.forgetPassword();
                    break;
                case 4:
                    // Depart and Exit.
                    isRunning = false;
                    System.out.println(" ");
                    System.out.println(mintColorCode + "Application Terminated. " + whiteColorCode);
                    exit(0);
                    break;
                default:
                    System.out.println(redColorCode + "Error: Invalid Menu Choice" + whiteColorCode);
                    break;
            }
        }
        input.close();
    }
}