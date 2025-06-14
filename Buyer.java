// import java.util.ArrayList;

/*
 * These User classes might be useless, but we thought it was the right approahc at the time
 */

public class Buyer extends User
{
    // private ArrayList<Book> cart;

    // public Buyer(String firstName, String lastName, String password, String
    // eAddress, String phoneNumber, String shippingAddress)
    // {
    // super(firstName, lastName, password, eAddress, phoneNumber,
    // shippingAddress);
    // // this.cart = new ArrayList<Book>();
    // }

    public Buyer(String firstName, String lastName, String password, String eAddress, String phoneNumber, String shippingAddress, int buyerID)
    {
        super(firstName, lastName, password, eAddress, phoneNumber, shippingAddress, buyerID);
        // this.cart = new ArrayList<Book>();
    }

    // public Buyer(String firstName, String lastName, String password, String
    // eAddress, int buyerID) {
    // super(firstName, lastName, password, eAddress, buyerID);
    // this.cart = new ArrayList<Book>();
    // }

    // public void addCart(Book book)
    // {
    // cart.add(book);
    // }

    // public void removeCart(Book book)
    // {
    // cart.remove(book);
    // }

    // public ArrayList<Book> getCart()
    // {
    // return cart;
    // }

}