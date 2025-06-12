import java.util.ArrayList;

// Kyle House

public class Admin extends User
{

    private ArrayList<Transaction> sales;

    // we actually want to retrieve this from the database
    public Admin(String firstName, String lastName, String password, String eAddress, String phoneNumber, String shippingAddress)
    {
        super(firstName, lastName, password, eAddress, phoneNumber, shippingAddress);
        this.sales = new ArrayList<>();
    }

    public Admin(String firstName, String lastName, String password, String eAddress, String phoneNumber, String shippingAddress, int buyerID)
    {
        super(firstName, lastName, password, eAddress, phoneNumber, shippingAddress, buyerID);
        this.sales = new ArrayList<>();
    }

    public ArrayList<Transaction> getSales()
    {
        return sales;
    }
}