import javafx.scene.image.Image;

/*
 * Commented out code is currently useless, but was part of plans for an initially more ambitious project that we ended up not having time for
 */

public class Book
{// Deals with collecting & storing details of an individual book inside a Book
 // object
    private String name;
    private String author;
    private double basePrice;
    private double finalPrice;
    // private double discountPercent;
    private User seller;
    private String category;
    private String condition;
    private int bookID;
    // private boolean isPurchasable; // useless, no impact on program
    // private boolean isApproved; // useless, no impact on program
    private Image image;
    // private double stock; // useless, no impact on program

    // public Book(String name, String author, double basePrice, User seller,
    // String category, String condition)
    // {
    // this.name = name;
    // this.author = author;
    // this.basePrice = basePrice;
    // this.finalPrice = basePrice;
    // this.seller = seller;
    // this.category = category;
    // this.condition = condition;
    // // this.isPurchasable = false; // will change otherwise
    // // this.isApproved = false; // will change otherwise
    // this.image = new
    // Image("https://m.media-amazon.com/images/I/71fL+SKczgL.jpg");
    // // this.stock = 0;
    // }

    public Book(String name, String author, double basePrice, User seller, String category, String condition, int bookID)
    {
        this.name = name;
        this.author = author;
        this.basePrice = basePrice;
        this.finalPrice = basePrice;
        this.seller = seller;
        this.category = category;
        this.condition = condition;
        this.bookID = bookID;
        // this.isPurchasable = false; // will change otherwise
        // this.isApproved = false; // will change otherwise
        this.image = new Image("https://m.media-amazon.com/images/I/71fL+SKczgL.jpg");
        // this.stock = 0;
    }

    // public Book(String name, String author, double basePrice, User seller,
    // String category, String condition, int bookID, String link)
    // {
    // this.name = name;
    // this.author = author;
    // this.basePrice = basePrice;
    // this.finalPrice = basePrice;
    // this.seller = seller;
    // this.category = category;
    // this.condition = condition;
    // this.bookID = bookID;
    // // this.isPurchasable = false; // will change otherwise
    // // this.isApproved = false; // will change otherwise
    // this.image = new Image(link);
    // // this.stock = 0;
    // }

    public Book(String name, String author, double basePrice, User seller, String category, String condition, String link)
    {
        this.name = name;
        this.author = author;
        this.basePrice = basePrice;
        this.finalPrice = basePrice;
        this.seller = seller;
        this.category = category;
        this.condition = condition;
        // this.isPurchasable = false; // will change otherwise
        // this.isApproved = false; // will change otherwise
        this.image = new Image(link);
        // this.stock = 0;
    }

    public String getName()
    {
        return name;
    }

    public String getAuthor()
    {
        return author;
    }

    public double getBasePrice()
    {
        return basePrice;
    }

    public double getFinalPrice()
    {
        return finalPrice;
    }

    // public double getDiscountPercent()
    // {
    // return discountPercent;
    // }

    public User getSeller()
    {
        return seller;
    }

    public String getCategory()
    {
        return category;
    }

    public String getCondition()
    {
        return condition;
    }

    public int getBookID()
    {
        return bookID;
    }

    // public boolean isPurchasable()
    // {
    // return isPurchasable;
    // }

    // public boolean isApproved()
    // {
    // return isApproved;
    // }

    public Image getImage()
    {
        return image;
    }

    // public double getStock()
    // {
    // return stock;
    // };

    // public void price(double price)
    // {// set both prices
    // this.basePrice = price;
    // this.finalPrice = calcFinalPrice();
    // }

    // public void discount(double discountPercent)
    // {// set the discount
    // if (discountPercent >= 0 && discountPercent <= 100)
    // {// the if statement checks if the discount is between 1 and 99 percent
    // this.discountPercent = discountPercent;
    // this.finalPrice = calcFinalPrice();
    // } else
    // {
    // System.out.println("Out of bounds discount");
    // }
    // }

    // public void approveBook()
    // {
    // this.isApproved = true;
    // }

    // public void removeBook()
    // {
    // this.isApproved = false;
    // this.isPurchasable = false;
    // }

    // @Override
    // public boolean equals(Object o)
    // {
    // if (o == this)
    // {
    // return true;
    // }
    // if (!(o instanceof Book))
    // {
    // return false;
    // }
    // Book b = (Book) o;

    // return ((name == b.getName()) && (basePrice == b.getBasePrice()) &&
    // (category == b.getCategory()) && (condition == b.getCondition()));
    // }

    // private double calcFinalPrice()
    // {
    // return basePrice * (1 - discountPercent / 100);
    // }

}
