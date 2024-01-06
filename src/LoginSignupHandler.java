import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSignupHandler
{
    private String username, createPassword, firstName, lastName;
    private int age;
    
    public LoginSignupHandler()
    {
        username="";
        createPassword="";
        firstName="";
        lastName="";
        age=0;
    }
    
    public LoginSignupHandler(String username, String createPassword, String firstName, String lastName, int age)
    {
        this.username = username;
        this.createPassword = createPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
     final Scanner input = new Scanner(System.in);
    public void createUserName()
    {
        input.nextLine();
        while (true)
        {
            System.out.print("Enter a username: ");
            username = input.nextLine().toLowerCase();
            if (username.length() < 5)
            {
                System.out.println(colorCodes.brightRed + "Error: Username must be atleast 5 characters long." + colorCodes.pureWhite);
                continue;
            }
            else if (username.contains(" "))
            {
                System.out.println(colorCodes.brightRed + "Error: Username cannot contain spaces." + colorCodes.pureWhite);
                continue;
            }
            else if (username.length() > 20)
            {
                System.out.println(colorCodes.brightRed + "Error: Username must be less than 20 characters." + colorCodes.pureWhite);
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
                System.out.println(colorCodes.brightRed + "Error: Username does not contain letters and digits." + colorCodes.pureWhite);
                continue;
            }
            UserSignup info = new UserSignup(username);
            boolean isAvailable = SignUpIntoDB.userCheck(info);
            if (!isAvailable)
            {
                System.out.println(colorCodes.brightRed + "Error: That doesn't seem to be a unique username." + colorCodes.pureWhite);
            }
            else
            {
                System.out.println(colorCodes.brightGreen + "Username Added!" + colorCodes.pureWhite);
                break;
            }
        }
    }
    // Creating New Password.
    public void introducePassword()
    {
        boolean isValidPassword = false;
        while (!isValidPassword)
        {
            System.out.print("Create a password: ");
            createPassword = input.nextLine();
            if (createPassword.length() < 5)
            {
                System.out.println(colorCodes.brightRed + "Error: Password must be a minimum of 5 characters." + colorCodes.pureWhite);
            }
            else if (createPassword.length() > 30)
            {
                System.out.println(colorCodes.brightRed + "Error: Password must be a maximum of 30 characters." + colorCodes.pureWhite);
            }
            else if (createPassword.contains(" "))
            {
                System.out.println(colorCodes.brightRed + "Error: Password cannot contain spaces." + colorCodes.pureWhite);
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
                    System.out.println(colorCodes.brightRed + "Error: Password has not been confirmed." + colorCodes.pureWhite);
                }
            }
        }
        System.out.println(colorCodes.brightGreen + "Hurrah, Password Added!!!" + colorCodes.pureWhite);
    }
    // Creating New First Name.
    public void introduceFirstName()
    {
        boolean flag = true;
        while (flag)
        {
            System.out.print("Enter First name: ");
            firstName = input.nextLine().trim().toLowerCase();
            if (firstName.length() < 3)
            {
                System.out.println(colorCodes.brightRed + "Error: First Name must be at least 3 characters long." + colorCodes.pureWhite);
            }
            else if (firstName.length() > 30)
            {
                System.out.println(colorCodes.brightRed + "Error: First Name must be less than 30 characters." + colorCodes.pureWhite);
            }
            else if (firstName.matches(".*[^a-zA-Z0-9\\s].*"))
            {
                System.out.println(colorCodes.brightRed + "Error: First name contains special characters." + colorCodes.pureWhite);
            }
            else if (firstName.matches(".*\\d+.*"))
            {
                System.out.println(colorCodes.brightRed + "Error: First name contains digits." + colorCodes.pureWhite);
            }
            else
            {
                flag = false;
                System.out.println(colorCodes.brightGreen + "Nice First Name ;)" + colorCodes.pureWhite);
            }
        }
    }
    
    // Creating New Last Name.
    public void introduceLastName()
    {
        boolean flag = true;
        while (flag)
        {
            System.out.print("Enter Last name: ");
            lastName = input.nextLine().trim().toLowerCase();
            if (lastName.length() < 3)
            {
                System.out.println(colorCodes.brightRed + "Error: Last Name must be at least 3 characters long." + colorCodes.pureWhite);
            }
            else if (lastName.length() > 30)
            {
                System.out.println(colorCodes.brightRed + "Error: Last Name must be less than 30 characters." + colorCodes.pureWhite);
            }
            else if (lastName.matches(".*[^a-zA-Z0-9\\s].*"))
            {
                System.out.println(colorCodes.brightRed + "Error: Last name contains special characters." + colorCodes.pureWhite);
            }
            else if (lastName.matches(".*\\d+.*"))
            {
                System.out.println(colorCodes.brightRed + "Error: Last name contains digits." + colorCodes.pureWhite);
            }
            else
            {
                flag = false;
                System.out.println(colorCodes.brightGreen + "Charming Last Name!" + colorCodes.pureWhite);
            }
        }
    }
    
    // Setting New User's Age.
    public void introduceAge()
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
                    System.out.println(colorCodes.brightRed + "Error: A valid age must be entered." + colorCodes.pureWhite);
                }
                else
                {
                    flag = false;
                    System.out.println(colorCodes.brightGreen + "Age Added!" + colorCodes.pureWhite);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid path." + colorCodes.pureWhite);
                input.nextLine();
            }
        }
    }
    
    //signup function to add new user
    public void signup()
    {
        System.out.println(colorCodes.brightPeach + "Hit Enter if nothing appears" + colorCodes.pureWhite);
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
            System.out.println(colorCodes.brightGreen + "Completed, Thank you for Signing up! :)" + colorCodes.pureWhite);
            Main.main(new String[]{});
        }
        else
        {
            System.out.println(colorCodes.brightRed + "Error: Something went wrong :(" + colorCodes.pureWhite);
        }
    }
    
    // to forget password
    public void forgetPassword()
    {
        try
        {
            System.out.print(colorCodes.brightPurple + "\t\t\t\t   Forget Password \n" + colorCodes.pureWhite);
            System.out.print(colorCodes.brightPurple + "\t\t\t   We'll help you recover \n" + colorCodes.pureWhite);
            System.out.println(" ");
            System.out.println(colorCodes.brightPeach + "Hit Enter to nothing appears" + colorCodes.pureWhite);
            input.nextLine();
            do
            {
                System.out.print("Enter a username: ");
                username = input.nextLine().toLowerCase();
                if (username.isEmpty())
                {
                    System.out.println(colorCodes.brightRed + "Error: Input Field mustn't be empty." + colorCodes.pureWhite);
                }
            }
            while (username.isEmpty());
            do
            {
                System.out.print("Enter First name: ");
                firstName = input.nextLine().toLowerCase();
                if (firstName.isEmpty())
                {
                    System.out.println(colorCodes.brightRed + "Error: Input Field mustn't be empty." + colorCodes.pureWhite);
                }
            }
            while (firstName.isEmpty());
            do
            {
                System.out.print("Enter Last name: ");
                lastName = input.nextLine().toLowerCase();
                if (lastName.isEmpty())
                {
                    System.out.println(colorCodes.brightRed + "Error: Input Field mustn't be empty." + colorCodes.pureWhite);
                }
            }
            while (lastName.isEmpty());
            System.out.print("Enter Age: ");
            age = input.nextInt();
        }
        catch (Exception e)
        {
            System.out.println(colorCodes.brightRed + "Oops! That doesn't seem to be a valid path." + colorCodes.pureWhite);
            input.nextLine();
            return;
        }
        //matching credentials from database
        boolean isMatched = password.matchInfo(username, firstName, lastName, age);
        if (isMatched)
        {
            System.out.println(" ");
            System.out.println(colorCodes.brightGreen + "Your memory is commendable :)" + colorCodes.pureWhite);
            System.out.println(colorCodes.brightGreen + "Credentials have been matched!" + colorCodes.pureWhite);
            System.out.println(" ");
            input.nextLine();
            // Update Old Password.
            introducePassword();
            //updating password in the database
            boolean isUpdated = password.updatePassword(username, createPassword, firstName, lastName, age);
            if (isUpdated)
            {
                System.out.println("\n"+ colorCodes.brightGreen + "Congratulations, Password has been updated!" + colorCodes.partyEmoji+ colorCodes.pureWhite);
                System.out.println(" ");
            }
            else
            {
                System.out.println("\n"+ colorCodes.brightRed + "Error: Password has not been updated." + colorCodes.pureWhite);
                System.out.println(" ");
            }
        }
        else
        {
            System.out.println("\n"+ colorCodes.brightRed + "Error: There's a clash with your credentials :(" + colorCodes.pureWhite);
            System.out.println(" ");
        }
    }
}
