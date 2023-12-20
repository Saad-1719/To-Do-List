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

//            System.out.println(mintColorCode + "\t\t\t\t\t 🎩 Discover Dotify - Where Tasks Unravel with Elegance! ✨" + whiteColorCode);

//            System.out.println(mintColorCode + "\t\t\t\t\t ＷＥＬＣＯＭＥ ＴＯ ΛＴＬΛＳＭＩＮＤ" + whiteColorCode);
            System.out.println();
                        System.out.println(mintColorCode + "\t\t\t\t Dotify"+whiteColorCode);
            System.out.println(" ");
                        System.out.println("🌟Your Personal Productivity Companion! 🌟");
            System.out.println(" ");
            System.out.println("🌟 [1]: Embark on Your Journey - Login");
            System.out.println("🚀 [2]: Join Our Community and Explore");
            System.out.println("🔒 [3]: Rediscover Your Path - Forgot Password");
            System.out.println("🏞️ [4]: Bid Farewell & Exit the Adventure");
            System.out.print("🤔 Your journey awaits! Choose a number to proceed: ");
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
                            System.out.println("📝 [1]: Embark on a New Task.");
                            System.out.println("🌟 [2]: Venture into the Unknown with a Random Task.");
                            System.out.println("📔 [3]: Chronicle Your Thoughts - Add Notes.");
                            System.out.println("✅ [4]: Conquer and Mark a Task as Completed.");
                            System.out.println("🔍 [5]: Explore Ongoing Tasks.");
                            System.out.println("🎉 [6]: Celebrate Victories - View Completed Tasks.");
                            System.out.println("🗒️ [7]: Unfold the Pages - View Notes.");
                            System.out.println("🕵️‍♂️ [8]: Commence a Task Hunt.");
                            System.out.println("🕵️‍♂️ [9]: delete notes.");
                            System.out.println("🚪 [10]: Logout and Disembark from Your Odyssey.");
                            System.out.print("🤔 Enter the number corresponding to your chosen quest: ");
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
                                    System.out.println(mintColorCode + "Farewell, Explorer. You've gracefully concluded your journey for now." + whiteColorCode);
                                    break;
                                default:
                                    System.out.println(redColorCode + "Oops! That doesn't seem to be a valid path on our map." + whiteColorCode);
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
//                    Functions.signup();
                    obj.signup();
                    break;
                case 3:
                    // Update Password.
//                    Functions.forgetPassword();
                    obj.forgetPassword();
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