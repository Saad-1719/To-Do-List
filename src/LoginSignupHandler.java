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
     String mintColorCode = "\u001B[38;5;85m";
     String whiteColorCode = "\u001B[97m";
     String redColorCode = "\u001B[31m";
     String greenColorCode = "\u001B[92m";
     String yellowColor = "\u001B[93m";
    public void createUserName()
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
    public void introducePassword()
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
    public void introduceFirstName()
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
    public void introduceLastName()
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
    public void signup()
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
    public void forgetPassword()
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
    
}
