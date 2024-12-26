// Kyle House
import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<Book> listings;

    public Seller(String firstName, String lastName, String password, String eAddress, String phoneNumber, String shippingAddress) {
        super(firstName, lastName, password, eAddress, phoneNumber, shippingAddress);
        this.listings = new ArrayList<Book>();
    }
    
    public Seller(String firstName, String lastName, String password, String eAddress, String phoneNumber, String shippingAddress, int buyerID) {
        super(firstName, lastName, password, eAddress, phoneNumber, shippingAddress, buyerID);
        this.listings = new ArrayList<Book>();
    }

    public ArrayList<Book> getListings() {
        return listings;
    }
    
}