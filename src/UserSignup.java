public class UserSignup extends UserLogin
{
    private String firstName;
    private String lastName;
    private int age;

    public UserSignup(String username, String password,
                      String firstName, String lastName, int age)
    {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserSignup(String username)
    {
        super(username);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAge()
    {
        return age;
    }
}