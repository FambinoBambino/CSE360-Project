import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

public class SQLDatabase
{// static SQLdatabase instance as singleton aka only intialize once

    private static SQLDatabase instance = null;
    final private String SQL_PASSWORD = "1nstallingR00t!";
    // OLD PASS = R00tt!!r

    private SQLDatabase()
    {// SQLDatabase really only needs to be initialized once as it is just an
     // interface for the data
     // could add like a cache here
    }

    public static SQLDatabase getInstance()
    {
        if (instance == null)
        {
            instance = new SQLDatabase();
        }
        return instance;
    }

    public void addUser(String first, String last, String email, String password, String userType)
    {
        System.out.println("Welcome to adding users");

        String tableName = userType.toLowerCase() + "s";
        String SQL = "INSERT INTO " + tableName + " (first_name, last_name, email, pass_word) VALUES (?, ?, ?, ?)";
        // Specify column name that we will insert values into. We only use
        // 4 parameters since we skip user_id as it is auto-generated.

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {// closure of dbConnect and PreparedStatement is auto-handled by
         // try-catch
            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, email);
            stmt.setString(4, password);

            int rowsInserted = stmt.executeUpdate();
            // executeUpdate is used for INSERT, DELETE, UPDATE
            // executeQuery is used for SELECT
            System.out.println("Rows inserted: " + rowsInserted);
            // debugger / logging print
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!! " + sqle.getMessage());
        }
    }

    public User getUser(int userID, String userType)
    {
        System.out.println("Welcome to getting user info from SQL");

        String tableName = userType.toLowerCase() + "s";
        String SQL = "SELECT * FROM " + tableName + " WHERE user_id = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, userID);

            ResultSet result = stmt.executeQuery();

            if (result.next())
            {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                String password = result.getString("pass_word");
                int id = result.getInt("user_id");

                String output = firstName + " " + lastName + " " + email + " " + password + " " + id;
                System.out.println("\n" + output);

                switch (userType) {
                case "Buyer":
                    return new Buyer(firstName, lastName, password, email, "n/a", "n/a", id);
                case "Seller":
                    return new Seller(firstName, lastName, password, email, "n/a", "n/a", id);
                case "Admin":
                    return new Admin(firstName, lastName, password, email, "n/a", "n/a", id);
                }
            } else
            {
                System.out.println("No user found for ID: " + userID);
                return null;
            }
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!! " + sqle.getMessage());
        }
        return null;
    }

    public void addBook(Book book, int sellerID)
    {// Allows sellers to add books to database
        System.out.println("Welcome to adding books");

        String SQL = "INSERT INTO books (title, authorName, sellerID, book_category, book_condition, price) VALUES (?,?,?,?,?,?)";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setString(1, book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, sellerID);
            stmt.setString(4, book.getCategory());
            stmt.setString(5, book.getCondition());
            stmt.setDouble(6, book.getFinalPrice());
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
    }

    public Book getBook(int bookID)
    {// allows for a single book to be retrieved from databse
        System.out.println("Welcome to getting a book info from SQL");

        String SQL = "SELECT * FROM books WHERE book_id = ?";
        Book temp = null;

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, bookID);
            ResultSet result = stmt.executeQuery();

            if (result.next())
            {
                String title = result.getString("title");
                String author = result.getString("authorName");
                double price = result.getDouble("price");
                int sellerID = result.getInt("sellerID");
                String category = result.getString("book_category");
                String condition = result.getString("book_condition");
                int book_id = result.getInt("book_id");

                temp = new Book(title, author, price, getUser(sellerID, "Seller"), category, condition, book_id);

                System.out.println("Book retrieved: bookID - " + book_id + " " + title + " " + author + " $" + price + " SellerID: " + sellerID + "\n");
            } else
            {
                System.out.println("No book found with ID: " + bookID);
            }
            return temp;
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return temp;
    }

    public ArrayList<Book> getAllBooks()
    {// Retrieves all books to populate buyer page
        ArrayList<Book> books = new ArrayList<Book>();

        System.out.println("Welcome to getting all books from SQL");

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement statement = dbConnect.prepareStatement("SELECT * FROM books"))
        {
            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                Book temp = new Book(result.getString("title"), result.getString("authorName"), result.getDouble("price"), getUser(result.getInt("sellerID"), "Seller"), result.getString("book_category"), result.getString("book_condition"), result.getInt("book_id"));
                System.out.println("Adding book titled -> " + result.getString("title"));
                books.add(temp);
                System.out.println(result.getString("title") + " " + result.getString("authorName") + " " + result.getDouble("price") + " " + result.getString("sellerID") + " " + result.getString("book_category") + " " + result.getString("book_condition") + " " + result.getInt("book_id"));
            }
            System.out.println("All books gathered");
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return books;
    }

    public ArrayList<Book> getAllBooks(int sellID)
    {// Retrieves all books of current seller to populate seller listings page
        ArrayList<Book> books = new ArrayList<Book>();

        String SQL = "SELECT * FROM books WHERE sellerID = ?";

        System.out.println("Welcome to getting all books for certain seller from SQL");

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, sellID);
            ResultSet result = stmt.executeQuery();

            while (result.next())
            {
                String output = result.getString("title") + " " + result.getString("authorName") + " " + result.getString("price") + " " + result.getString("sellerID") + " " + result.getString("book_category") + " " + result.getString("book_condition") + " " + result.getString("book_id");
                Book temp = new Book(result.getString("title"), result.getString("authorName"), result.getDouble("price"), getUser(result.getInt("sellerID"), "Seller"), result.getString("book_category"), result.getString("book_condition"), result.getInt("book_id"));
                books.add(temp);
                System.out.println("\n" + output);
            }
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return books;
    }

    public int validateFromEmail(String email, String password, String userType)
    {// Validates user at login page through email+password check
        System.out.println("Welcome to getting user info from SQL");
        String tableName = userType.toLowerCase() + "s";
        String SQL = "SELECT * FROM " + tableName + " WHERE email = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL);)
        {
            stmt.setString(1, email);

            ResultSet result = stmt.executeQuery();

            if (result.next())
            {// Compares password of found user to expected password
                String output = result.getString("first_name") + " " + result.getString("last_name") + " " + result.getString("email") + " " + result.getString("pass_word");
                System.out.println(output);
                return result.getInt("user_id");
            } else
            {
                System.out.println("No user was found with email: " + email);
                return -1;
            }
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return -1; // -1 indicates no user was found
    }

    public boolean ExistingUser(String email, String userType)
    {// checks if user trying to sign up already exists
        System.out.println("Welcome to getting user info from SQL");
        String tableName = userType.toLowerCase() + "s";
        String SQL = "SELECT * FROM " + tableName + " WHERE email = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setString(1, email);

            ResultSet result = stmt.executeQuery();

            if (result.next())
            {
                String output = result.getString("first_name") + " " + result.getString("last_name") + " " + result.getString("email") + " " + result.getString("pass_word");
                System.out.println("\n" + output);
                return true; // user exists
            } else
            {
                System.out.println("User with email " + email + " does not exist");
                return false;// user does not exist
            }
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return false;// user does not exist
    }

    public void addToCart(int buyerID, int bookID, int amount)
    {
        System.out.println("Welcome to adding books to cart");
        String SQL = "INSERT INTO cart (user_id, book_id, amount) " + "VALUES (?, ?, ?) " + "ON DUPLICATE KEY UPDATE amount = amount + VALUES(amount)";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, buyerID);
            stmt.setInt(2, bookID);
            stmt.setInt(3, amount);
            stmt.executeUpdate();
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
    }

    public void removeFromCart(int bookID, int buyerID)
    {
        System.out.println("Welcome to removing books from cart");
        String SQL = "DELETE FROM cart WHERE book_id = ? AND user_id = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, bookID);
            stmt.setInt(2, buyerID);
            stmt.executeUpdate();
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
    }

    public void removeAllFromCart(int buyerID)
    {
        System.out.println("Welcome to removing all books from cart");
        String SQL = "DELETE FROM cart WHERE user_id = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, buyerID);
            stmt.executeUpdate();
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
    }

    public void removeBook(int bookID)
    {// allows seller to de-list book
        System.out.println("Welcome to removing book from listing by seller");
        String SQL = "DELETE FROM books WHERE book_id = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, bookID);
            stmt.executeUpdate();

        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
    }

    public List<Pair<Book, Integer>> getCart(int buyerID)
    {// returns book info from current users cart
        List<Pair<Book, Integer>> cartBooks = new ArrayList<>();
        String SQL = "SELECT b.*, c.amount FROM books b JOIN cart c ON b.book_id = c.book_id WHERE c.user_id = ?";
        // Above String = "Select book ids from book table where book id
        // equals the one found in cart table but only where cart user id = ? "

        System.out.println("Welcome to getting all books from cart for certain buyer from SQL");

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root", SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, buyerID);
            ResultSet result = stmt.executeQuery();
            while (result.next())
            {
                String output = result.getString("title") + " " + result.getString("authorName") + " " + result.getString("price") + " " + result.getString("sellerID") + " " + result.getString("book_category") + " " + result.getString("book_condition") + " " + result.getString("book_id");
                Book temp = new Book(result.getString("title"), result.getString("authorName"), result.getDouble("price"), getUser(result.getInt("sellerID"), "Seller"), result.getString("book_category"), result.getString("book_condition"), result.getInt("book_id"));
                cartBooks.add(new Pair<Book, Integer>(temp, result.getInt("amount")));
                System.out.println("\n" + output);
            }
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLDatabase.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return cartBooks;
    }
}