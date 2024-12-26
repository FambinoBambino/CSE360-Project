import java.util.Date;

public class Transaction {
    private int transactionID;
    private Buyer buyer;
    private Seller seller;
    private Book book;
    private Date transactionDate;
    private String status;
    private double amount;

    public Transaction(int transactionID, Buyer buyer, Seller seller, Book book, Date transactionDate, String status, double amount) {
        this.transactionID = transactionID;
        this.buyer = buyer;
        this.seller = seller;
        this.book = book;
        this.transactionDate = transactionDate;
        this.status = status;
        this.amount = amount;
    }

    public void processTransaction() {
        //transaction processing logic
        this.status = "processed";
    }

    public void cancelTransaction() {
        //cancellation logic
        this.status = "cancelled";
    }

    public void refundTransaction() {
        //refund logic
        this.status = "refunded";
    }

    public Transaction getTransaction() {
        return this;
    }

    //getters and setters for every attribute
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
