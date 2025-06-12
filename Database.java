// // Kyle House

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Date;

// public class Database
// {// First, I need to setup my database in SQL

// // static database instance as singleton aka only intialize once
// private static Database instance = null;
// private static final String BUYERS_PATH = "Database/buyers_users.txt";
// private static final String SELLERS_PATH = "Database/sellers_users.txt";
// private static final String ADMIN_PATH = "Database/admin_users.txt";
// private static final String UID_PATH = "Database/lastUserID.txt";
// private static final String BOOKS_PATH = "Database/books.txt";
// private static final String TRANSACTIONS_PATH = "Database/transactions.txt";
// private static final String BOOKID_PATH = "Database/lastBookID.txt";

// private Database()
// {
// // Database really only needs to be initialized once as it is just an
// // interface for the data
// // could add like a cache here
// }

// public static Database getInstance()
// {
// if (instance == null)
// {
// instance = new Database();
// }
// return instance;
// }

// public int getUID()
// {
// int lastUserID = 0;

// // Get last user ID
// try (BufferedReader reader = new BufferedReader(new FileReader(UID_PATH)))
// {
// String line = reader.readLine();
// if (line != null)
// {
// lastUserID = Integer.parseInt(line.strip());
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }

// int newUserID = lastUserID + 1;

// // Set new user ID in file
// try (BufferedWriter writer = new BufferedWriter(new FileWriter(UID_PATH)))
// {
// writer.write(String.valueOf(newUserID));
// } catch (IOException e)
// {
// e.printStackTrace();
// }

// return newUserID;
// }

// // probably fine to use
// public void addUser(String first, String last, String email, String password,
// String userType)
// {
// String filePath = null;
// if (userType.equals("Buyer"))
// {
// filePath = BUYERS_PATH;
// } else if (userType.equals("Seller"))
// {
// filePath = SELLERS_PATH;
// } else if (userType.equals("Admin"))
// {
// filePath = ADMIN_PATH;
// }

// if (filePath != null)
// {
// User newUser = new User(first, last, password, email, "n/a", "n/a");
// try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,
// true)))
// {
// writer.newLine();
// writer.write(newUser.getUserID() + "," + newUser.getFirstName() + "," +
// newUser.getLastName() + "," + newUser.geteAddress() + "," +
// newUser.getPassword() + "," + newUser.getPhoneNumber() + "," +
// newUser.getShippingAddress());
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }
// }

// // public User(String firstName, String lastName, String password, String
// // eAddress, String phoneNumber, String shippingAddress)
// public void addUser(Buyer user)
// {

// try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUYERS_PATH,
// true)))
// {
// writer.newLine();
// writer.write(user.getUserID() + "," + user.getFirstName() + "," +
// user.getLastName() + "," + user.geteAddress() + "," + user.getPhoneNumber() +
// "," + user.getShippingAddress() + "," + user.getPassword());
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }

// // Polymorphed method to add a seller to the database
// public void addUser(Seller user)
// {

// try (BufferedWriter writer = new BufferedWriter(new FileWriter(SELLERS_PATH,
// true)))
// {
// writer.newLine();
// // writer.write(user.getUserID() + "," + user.getFirstName() + "," +
// // user.getLastName() + "," + user.geteAddress() + "," +
// // user.getPhoneNumber() + "," + user.getShippingAddress() + "," +
// // user.getPassword());
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }

// // Polymorphed method to add an admin to the database
// public void addUser(Admin user)
// {

// try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_PATH,
// true)))
// {
// writer.newLine();
// // writer.write(user.getUserID() + "," + user.getFirstName() + "," +
// // user.getLastName() + "," + user.geteAddress() + "," +
// // user.getPhoneNumber() + "," + user.getShippingAddress() + "," +
// // user.getPassword());
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }

// // returns the user object with the given userID and userType
// public User getUser(int userID, String userType)
// {
// String filePath = null;
// if (userType.equals("Buyer"))
// {
// filePath = BUYERS_PATH;
// } else if (userType.equals("Seller"))
// {
// filePath = SELLERS_PATH;
// } else if (userType.equals("Admin"))
// {
// filePath = ADMIN_PATH;
// }
// // String firstName, String lastName, String password, String eAddress,
// // String phoneNumber, String shippingAddress
// if (filePath != null)
// {
// try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// if ((Integer.parseInt(parts[0]) == userID) && (parts.length > 1))
// {
// if (userType.equals("Buyer"))
// {
// return new Buyer(parts[1], parts[2], parts[4], parts[3], parts[5], parts[6],
// Integer.parseInt(parts[0]));
// } else if (userType.equals("Seller"))
// {
// return new Seller(parts[1], parts[2], parts[4], parts[3], parts[5], parts[6],
// Integer.parseInt(parts[0]));
// } else if (userType.equals("Admin"))
// {
// return new Admin(parts[1], parts[2], parts[4], parts[3], parts[5], parts[6],
// Integer.parseInt(parts[0]));
// }
// }
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }
// return null;
// }

// public int getIDfromUser(String firstName, String lastName, String password,
// String eAddress, String phoneNumber, String shippingAddress)
// {
// return -1;
// }

// public void addBook(Book book, int sellerID)
// {
// try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_PATH,
// true)))
// {
// writer.newLine();
// writer.write(book.getBookID() + "," + book.getName() + "," + book.getAuthor()
// + "," + sellerID + "," + book.getCategory() + "," + book.getCondition() + ","
// + book.getBasePrice());
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return;
// }

// public Book getBook(int bookID)
// {
// try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_PATH)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// if (Integer.parseInt(parts[0]) == bookID)
// {
// String title = parts[1];
// String author = parts[2];
// int sellerID = Integer.parseInt(parts[3]);
// String category = parts[4];
// String condition = parts[5];
// double price = Double.parseDouble(parts[6]);

// return new Book(title, author, price, getUser(sellerID, "Seller"), category,
// condition, bookID);
// }
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return null;
// }

// public int getLastBookID()
// {
// try (BufferedReader reader = new BufferedReader(new FileReader(BOOKID_PATH)))
// {
// int newID = Integer.parseInt(reader.readLine()) + 1;
// System.out.println(newID);

// BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKID_PATH,
// false));
// writer.write(Integer.toString(newID));

// writer.close();
// return newID;
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return -1;
// }

// public ArrayList<Book> getAllBooks()
// {
// ArrayList<Book> books = new ArrayList<Book>();
// try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_PATH)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// int bookID = Integer.parseInt(parts[0]);
// String title = parts[1];
// String author = parts[2];
// int sellerID = Integer.parseInt(parts[3]);
// String category = parts[4];
// String condition = parts[5];
// double price = Double.parseDouble(parts[6]);
// Book book = new Book(title, author, price, getUser(sellerID, "Seller"),
// category, condition, bookID);
// books.add(book);
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return books;
// }

// public ArrayList<Book> getAllBooks(int sellID)
// {// over load method for Seller to gather all their books from database
// ArrayList<Book> books = new ArrayList<Book>();
// try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_PATH)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// int bookID = Integer.parseInt(parts[0]);
// String title = parts[1];
// String author = parts[2];
// int sellerID = Integer.parseInt(parts[3]);
// String category = parts[4];
// String condition = parts[5];
// double price = Double.parseDouble(parts[6]);
// if (sellerID == sellID)
// {
// Book book = new Book(title, author, price, getUser(sellerID, "Seller"),
// category, condition, bookID);
// books.add(book);
// }
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return books;
// }

// // Adds a transaction into the file on next empty line
// public void addTransaction(Transaction transaction)
// {
// // Add transaction to database
// // transactionID, buyer id, seller id, book title, time in milliseconds,
// // status, amount
// try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_PATH,
// true)))
// {
// writer.newLine();
// writer.write(transaction.getTransactionID() + "," +
// transaction.getBuyer().getUserID() + "," +
// transaction.getSeller().getUserID() + "," + transaction.getBook().getBookID()
// + "," + transaction.getTransactionDate().getTime() + "," +
// transaction.getStatus() + "," + transaction.getAmount());
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return;
// }

// // public Transaction(int transactionID, Buyer buyer, User seller, Book
// // book, Date transactionDate, String status, double amount)
// // returns transaction object with the given transactionID
// public Transaction getTransaction(int transactionID)
// {
// try (BufferedReader reader = new BufferedReader(new
// FileReader(TRANSACTIONS_PATH)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// if (Integer.parseInt(parts[0]) == transactionID)
// {
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
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// return null;
// }

// public int validateFromUsername(String username, String password, String
// userType)
// {
// String filePath = null;
// if (userType.equals("Buyer"))
// {
// filePath = BUYERS_PATH;
// } else if (userType.equals("Seller"))
// {
// filePath = SELLERS_PATH;
// } else if (userType.equals("Admin"))
// {
// filePath = ADMIN_PATH;
// }

// if (filePath != null)
// {
// try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// // if username in file is equal to username from login
// if (parts[1].equals(username))
// {
// // checks if passwords match
// if (parts[4].equals(password))
// {
// return Integer.parseInt(parts[0]);
// } else
// {
// return -1;
// }
// }
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }

// return -1;
// }

// public boolean ExistingUser(String email, String userType)
// {
// boolean existing = false;
// String filePath = null;
// if (userType.equals("Buyer"))
// {
// filePath = BUYERS_PATH;
// } else if (userType.equals("Seller"))
// {
// filePath = SELLERS_PATH;
// } else if (userType.equals("Admin"))
// {
// filePath = ADMIN_PATH;
// }

// if (filePath != null)
// {
// try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
// {
// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// if (parts[3].equals(email))
// {// checks if emails match
// existing = true;
// }
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// }
// return existing;
// }

// public void removeLineFromFile(String filePath, int idToRemove)
// {
// File inputFile = new File(filePath);
// File tempFile = new File("tempFile.txt");
// boolean removed = false;

// try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
// BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)))
// {

// String line;
// while ((line = reader.readLine()) != null)
// {
// String[] parts = line.split(",");
// int id = Integer.parseInt(parts[0]);
// if (id != idToRemove)
// {
// writer.write(line);
// writer.newLine();
// } else
// {
// removed = true;
// }
// }
// if (removed == false)
// {
// System.err.println("ID " + idToRemove + " not removed from file " + filePath
// + " in Database");
// }
// } catch (IOException e)
// {
// e.printStackTrace();
// }
// if (!inputFile.delete())
// {
// System.out.println("Could not delete file");
// return;
// }

// if (!tempFile.renameTo(inputFile))
// {
// System.out.println("Could not rename file");
// }
// }

// }