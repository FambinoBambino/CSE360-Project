
// Kyle House & Adrian Sida Q.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// ADD PREPARED STATEMENTS TO ALL METHODS !!!

//Still need:
// Add to user cart
// delete any row

public class SQLDatabase
{

    // static SQLdatabase instance as singleton aka only intialize once

    private static SQLDatabase instance = null;
    final private String SQL_PASSWORD = "1nstallingR00t!"; // OLD PASS =
                                                           // R00tt!!r

    // private Statement sqlSt;// runs SQL statements
    // private String output;
    // private ResultSet result; // holds output from SQL

    private SQLDatabase()
    {
        // SQLDatabase really only needs to be initialized once as it is just an
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

    // public void addUser(String first, String last, String email, String
    // password, String userType)
    // {// IMPLEMENTED
    // System.out.println("Welcome to adding buyers");

    // try
    // {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a bridge from here to
    // // SQL

    // String SQL = String.format("INSERT INTO %ss VALUES (%s,%s,%s,%s,%s)",
    // userType.toLowerCase(), first, last,
    // email, password);
    // sqlSt.execute(SQL);

    // sqlSt.close();
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // }

    // public void addUser(String first, String last, String email, String
    // password, String userType)
    // {
    // System.out.println("Welcome to adding users");

    // try
    // {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);

    // String tableName = userType.toLowerCase() + "s";
    // String SQL = "INSERT INTO " + tableName + " (first_name, last_name,
    // email, pass_word) VALUES (?, ?, ?, ?)";
    // // Specify column name that we will insert values into. We only use
    // // 4 parameters since we skip user_id as it is auto-generated.

    // PreparedStatement stmt = dbConnect.prepareStatement(SQL);
    // stmt.setString(1, first);
    // stmt.setString(2, last);
    // stmt.setString(3, email);
    // stmt.setString(4, password);

    // int rowsInserted = stmt.executeUpdate();
    // // executeUpdate is used for INSERT, DELETE, UPDATE
    // // executeQuery is used for SELECT
    // System.out.println("Rows inserted: " + rowsInserted);
    // // debugger / logging print

    // stmt.close();
    // dbConnect.close();
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!! " + sqle.getMessage());
    // }
    // }

    // public User getUser(int userID, String userType) {// IMPLEMENTED
    // System.out.println("Welcome to getting user info from SQL");

    // try {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed AKA a
    // bridge from here to SQL
    // String SQL = String.format("SELECT * FROM %ss WHERE user_id = %d",
    // userType.toLowerCase(), userID);
    // result = sqlSt.executeQuery(SQL); // result holds output from the SQL

    // while (result.next() != false) {
    // output = result.getString("first_name") + " " +
    // result.getString("last_name")
    // + " "
    // + result.getString("email") + " " + result.getString("pass_word") + " "
    // + result.getString("user_id");
    // System.out.println();
    // System.out.println(output);

    // }
    // if (userType.equals("Buyer")) { // added result.next == true on 5-28-25
    // return new Buyer(result.getString("first_name"),
    // result.getString("last_name"),
    // result.getString("pass_word"), result.getString("email"), "n/a", "n/a",
    // Integer.valueOf(result.getString("user_id")));
    // } else if (userType.equals("Seller")) {
    // // System.out.println("Creating seller User");
    // return new Seller(result.getString("first_name"),
    // result.getString("last_name"),
    // result.getString("pass_word"), result.getString("email"), "n/a", "n/a",
    // Integer.valueOf(result.getString("user_id")));
    // } else if (userType.equals("Admin")) {
    // return new Admin(result.getString("first_name"),
    // result.getString("last_name"),
    // result.getString("pass_word"), result.getString("email"), "n/a", "n/a",
    // Integer.valueOf(result.getString("user_id")));
    // }
    // // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
    // 'Vingler',
    // // 20, 'Female', '1914-06-26')";
    // // System.out.println(SQL);
    // // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
    // // sqlSt.execute(SQL);
    // sqlSt.close();
    // } catch (ClassNotFoundException cnfe) {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle) {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // return null;
    // }

    // public User getUser(int userID, String userType)
    // {
    // System.out.println("Welcome to getting user info from SQL");

    // try
    // {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement();

    // String SQL = String.format("SELECT * FROM %ss WHERE user_id = %d",
    // userType.toLowerCase(), userID);
    // result = sqlSt.executeQuery(SQL);

    // if (result.next())
    // {
    // String firstName = result.getString("first_name");
    // String lastName = result.getString("last_name");
    // String email = result.getString("email");
    // String password = result.getString("pass_word");
    // int id = result.getInt("user_id");

    // output = firstName + " " + lastName + " " + email + " " + password + " "
    // + id;
    // System.out.println("\n" + output);

    // switch (userType) {
    // case "Buyer":
    // return new Buyer(firstName, lastName, password, email, "n/a", "n/a", id);
    // case "Seller":
    // return new Seller(firstName, lastName, password, email, "n/a", "n/a",
    // id);
    // case "Admin":
    // return new Admin(firstName, lastName, password, email, "n/a", "n/a", id);
    // }
    // } else
    // {
    // System.out.println("No user found for ID: " + userID);
    // }

    // sqlSt.close();
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!! " + sqle.getMessage());
    // }

    // return null;
    // }

    public void addUser(String first, String last, String email, String password, String userType)
    {
        System.out.println("Welcome to adding users");

        String tableName = userType.toLowerCase() + "s";
        String SQL = "INSERT INTO " + tableName + " (first_name, last_name, email, pass_word) VALUES (?, ?, ?, ?)";
        // Specify column name that we will insert values into. We only use
        // 4 parameters since we skip user_id as it is auto-generated.

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
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
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!! " + sqle.getMessage());
        }
    }

    public User getUser(int userID, String userType)
    {
        System.out.println("Welcome to getting user info from SQL");

        String tableName = userType.toLowerCase() + "s";
        String SQL = "SELECT * FROM " + tableName + " WHERE user_id = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {// closure of dbConnect and PreparedStatement is auto-handled by
         // try-catch
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
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!! " + sqle.getMessage());
        }
        return null;
    }

    // public void addBook(Book book, int sellerID)
    // {// IMPLEMENTED
    // System.out.println("Welcome to adding books");

    // try
    // {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a bridge from here to
    // // SQL

    // String SQL = String.format("INSERT INTO books VALUES
    // (%s,%s,%d,%s,%s,%f)", book.getName(), book.getAuthor(),
    // sellerID, book.getCategory(), book.getCondition(), book.getFinalPrice());
    // sqlSt.execute(SQL);

    // sqlSt.close();
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // }

    public void addBook(Book book, int sellerID)
    {// IMPLEMENTED
        System.out.println("Welcome to adding books");

        String SQL = "INSERT INTO books (title, authorName, sellerID, book_category, book_condition, price) VALUES (?,?,?,?,?,?)";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
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
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
    }

    // public Book getBook(int bookID)
    // {// IMPLEMENTED
    // System.out.println("Welcome to getting a book info from SQL");
    // Book temp = null;

    // try
    // {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a bridge from here to
    // // SQL
    // String SQL = String.format("SELECT * FROM books WHERE book_id = %d",
    // bookID);
    // result = sqlSt.executeQuery(SQL); // result holds output from the
    // // SQL
    // while (result.next() != false)
    // {
    // output = result.getString("title") + " " + result.getString("authorName")
    // + "
    // " + result.getString("price")
    // + " " + result.getString("sellerID") + " " +
    // result.getString("book_category") + " "
    // + result.getString("book_condition") + " " + result.getString("book_id");
    // temp = new Book(result.getString("title"),
    // result.getString("authorName"),
    // Double.valueOf(result.getString("price")),
    // getUser(Integer.valueOf(result.getString("sellerID")), "Seller"),
    // result.getString("book_category"), result.getString("book_condition"),
    // Integer.valueOf(result.getString("book_id")));
    // System.out.println();// return new Book(title, author, price,
    // // getUser(sellerID, "Seller"), category,
    // // condition, bookID);
    // System.out.println(output);

    // } // (title, authorName, sellerID, category, book_condition,price)
    // // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
    // // 'Vingler',
    // // 20, 'Female', '1914-06-26')";
    // // System.out.println(SQL);
    // // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
    // // sqlSt.execute(SQL);
    // sqlSt.close();
    // return temp;
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // return null;
    // }

    public Book getBook(int bookID)
    {// IMPLEMENTED
        System.out.println("Welcome to getting a book info from SQL");

        String SQL = "SELECT * FROM books WHERE book_id = ?";
        Book temp = null;

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, bookID);
            ResultSet result = stmt.executeQuery();
            // result holds output from the SQL
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

                System.out.println("Book retrieved: bookID - " + book_id + " " + title + " " + author + " $" + price
                        + " SellerID: " + sellerID + "\n");
            } else
            {
                System.out.println("No book found with ID: " + bookID);
            }
            // (title, authorName, sellerID, category, book_condition,price)
            // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
            // 'Vingler',
            // 20, 'Female', '1914-06-26')";
            // System.out.println(SQL);
            // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
            return temp;
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return temp;
    }

    // public ArrayList<Book> getAllBooks() {
    // ArrayList<Book> books = new ArrayList<>();
    // System.out.println("Welcome to getting all books from SQL");

    // try {
    //
    //
    // try (Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // PreparedStatement statement = dbConnect.prepareStatement("SELECT * FROM
    // books");
    // ResultSet result = statement.executeQuery()) {

    // while (result.next()) {
    // System.out.println("Meow 1");
    // Book temp = new Book(
    // result.getString("title"),
    // result.getString("authorName"),
    // result.getDouble("price"),
    // getUser(result.getInt("sellerID"), "Seller"),
    // result.getString("book_category"),
    // result.getString("book_condition"),
    // result.getInt("book_id"));
    // books.add(temp);
    // System.out.println("Adding books now!");
    // System.out.printf("%s %s %.2f %d %s %s %d%n",
    // temp.getTitle(), temp.getAuthorName(), temp.getPrice(),
    // temp.getSeller().getUserID(), temp.getCategory(),
    // temp.getCondition(), temp.getBookID());
    // }

    // System.out.println("All books gathered");
    // }

    // } catch (ClassNotFoundException cnfe) {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle) {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }

    // return books;
    // }

    public ArrayList<Book> getAllBooks()
    {// IMPLEMENTED
        ArrayList<Book> books = new ArrayList<Book>();

        System.out.println("Welcome to getting all books from SQL");
        // Book temp = null;

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement statement = dbConnect.prepareStatement("SELECT * FROM books"))
        {
            ResultSet result = statement.executeQuery();

            while (result.next())
            { // changed from result.next() != false to result.next() == true on
              // 5-28-25
              // output = result.getString("title") + " " +
              // result.getString("authorName") + " " +
              // result.getString("price") + " " +
              // result.getString("sellerID") + " " +
              // result.getString("category") + " " +
              // result.getString("book_condition") + " "
              // + result.getString("book_id");
                Book temp = new Book(result.getString("title"), result.getString("authorName"),
                        result.getDouble("price"), getUser(result.getInt("sellerID"), "Seller"),
                        result.getString("book_category"), result.getString("book_condition"),
                        result.getInt("book_id"));
                System.out.println("Adding book titled -> " + result.getString("title"));
                books.add(temp);
                // return new Book(title, author,
                // price, getUser(sellerID,
                // "Seller"),
                // category, condition, bookID);
                System.out.println(result.getString("title") + " " + result.getString("authorName") + " "
                        + result.getDouble("price") + " " + result.getString("sellerID") + " "
                        + result.getString("book_category") + " " + result.getString("book_condition") + " "
                        + result.getInt("book_id"));

            } // (title, authorName, sellerID, category, book_condition,price)
              // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
              // 'Vingler',
              // 20, 'Female', '1914-06-26')";
              // System.out.println(SQL);
              // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
              // sqlSt.execute(SQL);
            System.out.println("All books gathered");
            // return books;
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return books;
    }

    // public ArrayList<Book> getAllBooks()
    // {// IMPLEMENTED
    // ArrayList<Book> books = new ArrayList<Book>();

    // System.out.println("Welcome to getting all books from SQL");
    // // Book temp = null;

    // try
    // {

    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a bridge from here to
    // // SQL
    // // String SQL = String.format("SELECT * FROM books");

    // // *** Add preparedStatements to all queries!!! */ Prepared
    // // Statements prevent
    // // SQL injection
    // PreparedStatement statement = dbConnect.prepareStatement("SELECT * FROM
    // books");
    // // statement.setInt(1, h);
    // ResultSet result = statement.executeQuery();

    // // result = sqlSt.executeQuery(statement); // result holds output
    // // from the SQL
    // while (result.next() == true)
    // { // changed from result.next() != false to result.next() == true on
    // // 5-28-25
    // System.out.println("Meow 1");
    // // output = result.getString("title") + " " +
    // // result.getString("authorName") + " " +
    // // result.getString("price") + " " +
    // // result.getString("sellerID") + " " +
    // // result.getString("category") + " " +
    // // result.getString("book_condition") + " "
    // // + result.getString("book_id");
    // Book temp = new Book(result.getString("title"),
    // result.getString("authorName"),
    // Double.valueOf(result.getString("price")),
    // getUser(Integer.valueOf(result.getString("sellerID")), "Seller"),
    // result.getString("book_category"), result.getString("book_condition"),
    // Integer.valueOf(result.getString("book_id")));
    // System.out.println("Adding books now!");
    // books.add(temp);
    // System.out.println("Meow 2");// return new Book(title, author,
    // // price, getUser(sellerID,
    // // "Seller"),
    // // category, condition, bookID);
    // System.out.println(result.getString("title") + " " +
    // result.getString("authorName") + " "
    // + Double.valueOf(result.getString("price")) + " " +
    // result.getString("sellerID") + " "
    // + result.getString("book_category") + " " +
    // result.getString("book_condition") + " "
    // + Integer.valueOf(result.getString("book_id")));

    // } // (title, authorName, sellerID, category, book_condition,price)
    // // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
    // // 'Vingler',
    // // 20, 'Female', '1914-06-26')";
    // // System.out.println(SQL);
    // // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
    // // sqlSt.execute(SQL);
    // sqlSt.close();
    // System.out.println("All books gathered");
    // // return books;
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // return books;
    // }

    public ArrayList<Book> getAllBooks(int sellID)
    {// over load method for Seller to gather all their books from
     // SQLdatabase
     // IMPLEMENTED
        ArrayList<Book> books = new ArrayList<Book>();

        String SQL = "SELECT * FROM books WHERE sellerID = ?";

        System.out.println("Welcome to getting all books for certain seller from SQL");

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setInt(1, sellID);
            ResultSet result = stmt.executeQuery();
            // result holds output from the SQL
            while (result.next())
            {
                String output = result.getString("title") + " " + result.getString("authorName") + " "
                        + result.getString("price") + " " + result.getString("sellerID") + " "
                        + result.getString("category") + " " + result.getString("book_condition") + " "
                        + result.getString("book_id");

                Book temp = new Book(result.getString("title"), result.getString("authorName"),
                        result.getDouble("price"), getUser(result.getInt("sellerID"), "Seller"),
                        result.getString("book_category"), result.getString("book_condition"),
                        result.getInt("book_id"));
                books.add(temp);
                // return new Book(title, author, price,
                // getUser(sellerID, "Seller"), category,
                // condition, bookID);
                System.out.println("\n" + output);

            } // (title, authorName, sellerID, category, book_condition,price)
              // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
              // 'Vingler',
              // 20, 'Female', '1914-06-26')";
              // System.out.println(SQL);
              // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
              // sqlSt.execute(SQL);
              // return books;
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return books;
    }

    // public ArrayList<Book> getAllBooks(int sellID)
    // {// over load method for Seller to gather all their books from
    // // SQLdatabase
    // // IMPLEMENTED
    // ArrayList<Book> books = new ArrayList<Book>();

    // System.out.println("Welcome to getting all books for certain seller from
    // SQL");
    // Book temp = null;

    // try
    // {

    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a bridge from here to
    // // SQL
    // String SQL = String.format("SELECT * FROM books WHERE sellerID = %d",
    // sellID);
    // result = sqlSt.executeQuery(SQL); // result holds output from the
    // // SQL
    // while (result.next() != false)
    // {
    // // output = result.getString("title") + " " +
    // // result.getString("authorName") + " " +
    // // result.getString("price") + " " +
    // // result.getString("sellerID") + " " +
    // // result.getString("category") + " " +
    // // result.getString("book_condition") + " "
    // // + result.getString("book_id");
    // temp = new Book(result.getString("title"),
    // result.getString("authorName"),
    // Double.valueOf(result.getString("price")),
    // getUser(Integer.valueOf(result.getString("sellerID")), "Seller"),
    // result.getString("book_category"), result.getString("book_condition"),
    // Integer.valueOf(result.getString("book_id")));
    // books.add(temp);
    // System.out.println();// return new Book(title, author, price,
    // // getUser(sellerID, "Seller"), category,
    // // condition, bookID);
    // System.out.println(output);

    // } // (title, authorName, sellerID, category, book_condition,price)
    // // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
    // // 'Vingler',
    // // 20, 'Female', '1914-06-26')";
    // // System.out.println(SQL);
    // // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
    // // sqlSt.execute(SQL);
    // sqlSt.close();
    // // return books;
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // return books;
    // }

    // // Adds a transaction into the file on next empty line
    // public void addTransaction(Transaction transaction)
    // {//SKIPPED FOR NOW SINCE TRANSACTIONS ARE NOT UTILIZED YET
    // // Add transaction to SQLdatabase
    // // transactionID, buyer id, seller id, book title, time in milliseconds,
    // status, amount
    // try (BufferedWriter writer = new BufferedWriter(new
    // FileWriter(BOOKS_PATH,
    // true))) {
    // writer.newLine();
    // writer.write(transaction.getTransactionID() + "," +
    // transaction.getBuyer().getUserID() + "," +
    // transaction.getSeller().getUserID() + "," +
    // transaction.getBook().getBookID()
    // + "," + transaction.getTransactionDate().getTime() + "," +
    // transaction.getStatus() + "," + transaction.getAmount());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return;
    // }

    // public Transaction(int transactionID, Buyer buyer, User seller, Book
    // book,
    // Date transactionDate, String status, double amount)
    // returns transaction object with the given transactionID
    // public Transaction getTransaction(int transactionID)
    // {//SKIPPED FOR NOW SINCE TRANSACTIONS ARE NOT UTILIZED YET
    // try (BufferedReader reader = new BufferedReader(new
    // FileReader(TRANSACTIONS_PATH))) {
    // String line;
    // while ((line = reader.readLine()) != null) {
    // String[] parts = line.split(",");
    // if (Integer.parseInt(parts[0]) == transactionID) {
    // int buyerID = Integer.parseInt(parts[1]);
    // int sellerID = Integer.parseInt(parts[2]);
    // int bookID = Integer.parseInt(parts[3]);
    // long timeInMillis = Long.parseLong(parts[4]);
    // String status = parts[5];
    // double amount = Double.parseDouble(parts[6]);

    // Buyer buyer = (Buyer) getUser(buyerID, "Buyer");
    // Seller seller = (Seller) getUser(sellerID, "Seller");
    // Book book = getBook(bookID);

    // return new Transaction(transactionID, buyer, seller, book, new
    // Date(timeInMillis), status, amount);
    // }
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }
    // public boolean validate(String name, String password, String userType)
    // int bID = db.validateFromUsername(name, password, userType);
    // result = validate(userName.getText(), password.getText(), "Admin");

    // public int validateFromUsernames(String username, String password, String
    // userType)
    // {
    // System.out.println("Welcome to getting user info from SQL");

    // try
    // {
    //
    //
    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);

    // // Build SQL using a parameterized query (safe and avoids injection)
    // String SQL = "SELECT * FROM " + userType.toLowerCase() + " WHERE
    // first_name = ?";
    // PreparedStatement stmt = dbConnect.prepareStatement(SQL);
    // stmt.setString(1, username); // bind username safely

    // ResultSet result = stmt.executeQuery();

    // while (result.next())
    // {
    // String fetchedPassword = result.getString("pass_word");

    // // Case-insensitive check, like your original logic
    // if (fetchedPassword.equalsIgnoreCase(password))
    // {
    // return result.getInt("user_id");
    // }

    // // Debug output
    // String output = result.getString("first_name") + " " +
    // result.getString("last_name") + " "
    // + result.getString("email") + " " + fetchedPassword;
    // System.out.println(output);
    // }

    // stmt.close();
    // dbConnect.close();

    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!! " + sqle.getMessage());
    // }

    // return -1; // if no match found
    // }

    public int validateFromUsername(String username, String password, String userType)
    {// IMPLEMENTED * Rework method to validate from email since email is the
     // only unique data a user has? (Besides userID but that is SQL only)
        System.out.println("Welcome to getting user info from SQL");
        String tableName = userType.toLowerCase() + "s";
        String SQL = "SELECT * FROM " + tableName + " WHERE first_name = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL);)
        {
            stmt.setString(1, username);
            // bind username safely, 1 = 1st parameter placeholder and this line
            // replaces 1st parameter with username

            ResultSet result = stmt.executeQuery();

            if (result.next())
            {
                String output = result.getString("first_name") + " " + result.getString("last_name") + " "
                        + result.getString("email") + " " + result.getString("pass_word");
                if (result.getString("pass_word").equals(password))
                {// previously ignored case sensitivity, but password should be
                 // case sensitive
                    return result.getInt("user_id");
                }
                System.out.println("\n" + output);
            } else
            {
                System.out.println("No user was found with username: " + username);
                return -1;
            }
            // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
            // 'Vingler',
            // 20, 'Female', '1914-06-26')";
            // System.out.println(SQL);
            // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
            // sqlSt.execute(SQL);
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return -1; // -1 indicates no user was found
    }

    // public int validateFromUsername(String username, String password, String
    // userType)
    // {// IMPLEMENTED
    // System.out.println("Welcome to getting user info from SQL");

    // try
    // {

    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a
    // // bridge from here to SQL

    // // Build SQL using a parameterized query (safe and avoids injection)
    // String SQL = "SELECT * FROM " + userType.toLowerCase() + " WHERE
    // first_name = ?";
    // // ? is a parameter placeholder
    // PreparedStatement stmt = dbConnect.prepareStatement(SQL);
    // stmt.setString(1, username);
    // // bind username safely, 1 = 1st parameter placeholder and this line
    // // replaces 1st parameter with username

    // ResultSet result = stmt.executeQuery();

    // while (result.next())
    // {
    // output = result.getString("first_name") + " " +
    // result.getString("last_name") + " "
    // + result.getString("email") + " " + result.getString("pass_word");
    // if (result.getString("pass_word").equalsIgnoreCase(password))
    // {
    // // return Integer.valueOf(result.getString("user_id"));
    // return result.getInt("user_id");
    // }
    // System.out.println();
    // System.out.println(output);

    // }
    // // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
    // // 'Vingler',
    // // 20, 'Female', '1914-06-26')";
    // // System.out.println(SQL);
    // // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
    // // sqlSt.execute(SQL);
    // stmt.close();
    // dbConnect.close();
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // return -1;
    // }

    public boolean ExistingUser(String email, String userType)
    {// IMPLEMENTED
        System.out.println("Welcome to getting user info from SQL");
        String tableName = userType.toLowerCase() + "s";
        String SQL = "SELECT * FROM " + tableName + " WHERE email = ?";

        try (Connection dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books", "root",
                SQL_PASSWORD); PreparedStatement stmt = dbConnect.prepareStatement(SQL))
        {
            stmt.setString(1, email);

            ResultSet result = stmt.executeQuery();

            if (result.next())
            {
                String output = result.getString("first_name") + " " + result.getString("last_name") + " "
                        + result.getString("email") + " " + result.getString("pass_word");
                System.out.println("\n" + output);
                return true; // user exists

            } else
            {
                System.out.println("User with email " + email + " does not exist");
                return false;// user does not exist
            }
        } catch (SQLException sqle)
        {
            Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
            System.out.println("SQL IS BAD!!" + sqle.getMessage());
        }
        return false;// user does not exist
    }

    // public boolean ExistingUser(String email, String userType)
    // {// IMPLEMENTED
    // boolean existing = false;

    // System.out.println("Welcome to getting user info from SQL");

    // try
    // {

    // Connection dbConnect =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/SunDevil_Books",
    // "root",
    // SQL_PASSWORD);
    // sqlSt = dbConnect.createStatement(); // allows SQL to be executed
    // // AKA a bridge from here to
    // // SQL
    // String SQL = String.format("SELECT * FROM %ss WHERE email = %s",
    // userType.toLowerCase(), email);
    // result = sqlSt.executeQuery(SQL); // result holds output from the
    // // SQL
    // while (result.next() != false)
    // {
    // output = result.getString("first_name") + " " +
    // result.getString("last_name") + " "
    // + result.getString("email") + " " + result.getString("pass_word");
    // if (result.getString("email") == null)
    // {
    // existing = false;
    // } else
    // {
    // existing = true;
    // }
    // System.out.println();
    // System.out.println(output);

    // }
    // // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
    // // 'Vingler',
    // // 20, 'Female', '1914-06-26')";
    // // System.out.println(SQL);
    // // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
    // // sqlSt.execute(SQL);
    // sqlSt.close();
    // } catch (ClassNotFoundException cnfe)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // cnfe);
    // System.out.println("Class not found, check the JAR");
    // } catch (SQLException sqle)
    // {
    // Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null,
    // sqle);
    // System.out.println("SQL IS BAD!!" + sqle.getMessage());
    // }
    // return existing;
    // }

    public void removeLineFromFile(String filePath, int idToRemove)
    {// ADDED to doc !!!
        File inputFile = new File(filePath);
        File tempFile = new File("tempFile.txt");
        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id != idToRemove)
                {
                    writer.write(line);
                    writer.newLine();
                } else
                {
                    removed = true;
                }
            }
            if (removed == false)
            {
                System.err.println("ID " + idToRemove + " not removed from file " + filePath + " in SQLDatabase");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (!inputFile.delete())
        {
            System.out.println("Could not delete file");
            return;
        }

        if (!tempFile.renameTo(inputFile))
        {
            System.out.println("Could not rename file");
        }
    }

}