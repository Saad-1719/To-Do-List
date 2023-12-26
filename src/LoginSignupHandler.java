import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSignupHandler {
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
     Scanner input = new Scanner(System.in);
     String brightPurple = "\u001B[38;5;207m";
     String pureWhite = "\u001B[97m";
     String brightRed = "\u001B[38;5;196m";
     String brightGreen = "\u001B[38;5;82m";
    public void createUserName()
    {
        input.nextLine();
        while (true)
        {
            System.out.print("Enter a username: ");
            username = input.nextLine().toLowerCase();
            if (username.length() < 5)
            {
                System.out.println(brightRed + "Error: Username must be atleast 5 characters long." + pureWhite);
                continue;
            }
            else if (username.contains(" "))
            {
                System.out.println(brightRed + "Error: Username cannot contain spaces." + pureWhite);
                continue;
            }
            else if (username.length() > 20)
            {
                System.out.println(brightRed + "Error: Username must be less than 20 characters." + pureWhite);
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
                System.out.println(brightRed + "Error: Username does not contain letters and digits." + pureWhite);
                continue;
            }
            UserSignup info = new UserSignup(username);
            boolean isAvailable = SignUpIntoDB.userCheck(info);
            if (!isAvailable)
            {
                System.out.println(brightRed + "Error: That doesn't seem to be a unique username." + pureWhite);
            }
            else
            {
                System.out.println(brightGreen + "Username Added!" + pureWhite);
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
                System.out.println(brightRed + "Error: Password must be a minimum of 5 characters." + pureWhite);
            }
            else if (createPassword.length() > 10)
            {
                System.out.println(brightRed + "Error: Password must be a maximum of 10 characters." + pureWhite);
            }
            else if (createPassword.contains(" "))
            {
                System.out.println(brightRed + "Error: Password cannot contain spaces." + pureWhite);
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
                    System.out.println(brightRed + "Error: Password has not been confirmed." + pureWhite);
                }
            }
        }
        System.out.println(brightGreen + "Hurrah, Password Added!!!" + pureWhite);
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
                System.out.println(brightRed + "Error: First Name must be atleast 3 characters long." + pureWhite);
            }
            else if (firstName.length() > 30)
            {
                System.out.println(brightRed + "Error: First Name must be less than 30 characters." + pureWhite);
            }
            else if (firstName.matches(".*[^a-zA-Z0-9\\s].*"))
            {
                System.out.println(brightRed + "Error: First name contains special characters." + pureWhite);
            }
            else if (firstName.matches(".*\\d+.*"))
            {
                System.out.println(brightRed + "Error: First name contains digits." + pureWhite);
            }
            else
            {
                flag = false;
                System.out.println(brightGreen + "Nice First Name ;)" + pureWhite);
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
                System.out.println(brightRed + "Error: Last Name must be atleast 3 characters long." + pureWhite);
            }
            else if (lastName.length() > 30)
            {
                System.out.println(brightRed + "Error: Last Name must be less than 30 characters." + pureWhite);
            }
            else if (lastName.matches(".*[^a-zA-Z0-9\\s].*"))
            {
                System.out.println(brightRed + "Error: Last name contains special characters." + pureWhite);
            }
            else if (lastName.matches(".*\\d+.*"))
            {
                System.out.println(brightRed + "Error: Last name contains digits." + pureWhite);
            }
            else
            {
                flag = false;
                System.out.println(brightGreen + "Charming Last Name!" + pureWhite);
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
                    System.out.println(brightRed + "Error: A valid age must be entered." + pureWhite);
                }
                else
                {
                    flag = false;
                    System.out.println(brightGreen + "Age Added!" + pureWhite);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println(brightRed + "Oops! That doesn't seem to be a valid path." + pureWhite);
                input.nextLine();
            }
        }
    }
    
    //signup function to add new user
    public void signup()
    {
        System.out.println(brightRed + "Hit Enter if nothing appears" + pureWhite);
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
            System.out.println(brightGreen + "Completed, Thank you for Signing up! :)" + pureWhite);
            Main.main(new String[]{});
        }
        else
        {
            System.out.println(brightRed + "Error: Something went wrong :(" + pureWhite);
        }
    }
    
    // to forget password
    public void forgetPassword()
    {
        try
        {
            System.out.print(brightPurple + "\t\t\t\t\t Forget Password \n" + pureWhite);
            System.out.print(brightPurple + "\t\t\t\t\t We'll help you recover \n" + pureWhite);
            System.out.println(" ");
            System.out.println(brightRed + "Hit Enter to nothing appears" + pureWhite);
            input.nextLine();
            do
            {
                System.out.print("Enter a username: ");
                username = input.nextLine().toLowerCase();
                if (username.isEmpty())
                {
                    System.out.println(brightRed + "Error: Input Field mustn't be empty." + pureWhite);
                }
            }
            while (username.isEmpty());
            do
            {
                System.out.print("Enter First name: ");
                firstName = input.nextLine().toLowerCase();
                if (firstName.isEmpty())
                {
                    System.out.println(brightRed + "Error: Input Field mustn't be empty." + pureWhite);
                }
            }
            while (firstName.isEmpty());
            do
            {
                System.out.print("Enter Last name: ");
                lastName = input.nextLine().toLowerCase();
                if (lastName.isEmpty())
                {
                    System.out.println(brightRed + "Error: Input Field mustn't be empty." + pureWhite);
                }
            }
            while (lastName.isEmpty());
            System.out.print("Enter Age: ");
            age = input.nextInt();
        }
        catch (Exception e)
        {
            System.out.println(brightRed + "Oops! That doesn't seem to be a valid path." + pureWhite);
            input.nextLine();
            return;
        }
        //matching credentials from database
        boolean isMatched = password.matchInfo(username, firstName, lastName, age);
        if (isMatched)
        {
            System.out.println(brightGreen + "Your memory is commendable :)" + pureWhite);
            System.out.println(brightGreen + "Credentials have been matched!" + pureWhite);
            System.out.println(" ");
            input.nextLine();
            // Update Old Password.
            introducePassword();
            //updating password in the database
            boolean isUpdated = password.updatePassword(username, createPassword, firstName, lastName, age);
            if (isUpdated)
            {
                System.out.println(brightGreen + "Congratulations, Password has been updated!" + Main.partyEmoji+ pureWhite);
                System.out.println(" ");
            }
            else
            {
                System.out.println(brightRed + "Error: Password has not been updated." + pureWhite);
                System.out.println(" ");
            }
        }
        else
        {
            System.out.println(brightRed + "Error: There's a clash with your credentials :(" + pureWhite);
            System.out.println(" ");
        }
    }
    
}
