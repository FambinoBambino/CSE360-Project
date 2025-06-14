// 11-4-24
// Adrian Sida Q.

/*
 * Commented out code was initially planned but never got fleshed out
 */

public class User
{
    private String firstName;
    private String lastName;
    private String password;
    private String eAddress; // don't provide setter method
    private int userID; // don't provide setter method
    // private String shippingAddress;
    // private String phoneNumber;
    // private boolean dualUser;
    // private boolean suspendedStatus;// might have to include
    // private int suspendedTime;

    // might have to find a data type that can store the real-world time for the
    // suspension -- Date class getTime() in Java returns a long of milliseconds
    // - kyle

    // Since no user can exist without sign up, there will be no default
    // constructor
    public User(String firstName, String lastName, String password, String eAddress, String phoneNumber, String shippingAddress, int buyerID)
    {// Set items that will be needed for User sign up, rest will be assigned
     // later.
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.eAddress = eAddress;
        this.userID = buyerID;
        // this.phoneNumber = phoneNumber;
        // this.shippingAddress = shippingAddress;
    }

    // public void setFirstName(String firstName)
    // {
    // this.firstName = firstName;
    // }

    public String getFirstName()
    {
        return firstName;
    }

    // public void setLastName(String lastName)
    // {
    // this.lastName = lastName;
    // }

    public String getLastName()
    {
        return lastName;
    }

    // public void setDualUser(boolean dualUser)
    // {
    // this.dualUser = dualUser;
    // }

    // public boolean getdualUser()
    // {
    // return dualUser;
    // }

    // public void setPassword(String password)
    // {
    // this.password = password;
    // }

    public String getPassword()
    {
        return password;
    }

    // public void setPhoneNumber(String phoneNumber)
    // {
    // this.phoneNumber = phoneNumber;
    // }

    // public String getPhoneNumber()
    // {
    // return phoneNumber;
    // }

    // public void setShippingAddress(String shippingAddress)
    // {
    // this.shippingAddress = shippingAddress;
    // }

    // public String getShippingAddress()
    // {
    // return shippingAddress;
    // }

    // public void setSuspendedStatus(boolean suspendedStatus)
    // {
    // this.suspendedStatus = suspendedStatus;
    // }

    // public boolean getSuspendedStatus()
    // {
    // return suspendedStatus;
    // }

    // public int getSuspendedTime()
    // {
    // return suspendedTime;
    // }

    public String geteAddress()
    {
        return eAddress;
    }

    public String getUserID()
    {
        return "" + userID;
    }
}
