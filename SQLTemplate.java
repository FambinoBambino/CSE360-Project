// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// public class SQLTemplate
// {// This can be used as a template!!! Still need to learn how to write back
// // though
// public static void main(String[] args) throws Exception
// {
// System.out.println("Welcome to Henry Books... I mean Parks and Rec!");

// Statement sqlSt;// runs SQL statements
// // String useSQL = new String("use parks_and_recreation");
// String output;
// ResultSet result; // holds output from SQL

// String SQL = "SELECT * FROM employee_demographics ORDER BY first_name";

// try
// {
// Class.forName("com.mysql.cj.jdbc.Driver");
// String dbURL = "jdbc:mysql://localhost:3306/parks_and_recreation";
// Connection dbConnect = DriverManager.getConnection(dbURL, "root",
// "R00tt!!r");
// sqlSt = dbConnect.createStatement(); // allows SQL to be executed
// // AKA a bridge from here to
// // SQL
// result = sqlSt.executeQuery(SQL); // result holds output from the
// // SQL
// while (result.next() != false)
// {
// output = result.getString("first_name") + " " + result.getString("last_name")
// + " " + result.getString("age") + " " + result.getString("gender") + " " +
// result.getString("birth_date");
// System.out.println();
// System.out.println(output);
// }
// // SQL = "INSERT INTO employee_demographics VALUES (13, 'Kakania',
// // 'Vingler', 20, 'Female', '1914-06-26')";
// // System.out.println(SQL);
// // (12, 'Craig', 'Middlebrooks', 37, 'Male', '1986-07-27');
// // sqlSt.execute(SQL);
// sqlSt.close();
// } catch (ClassNotFoundException cnfe)
// {
// Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, cnfe);
// System.out.println("Class not found, check the JAR");
// } catch (SQLException sqle)
// {
// Logger.getLogger(SQLTemplate.class.getName()).log(Level.SEVERE, null, sqle);
// System.out.println("SQL IS BAD!!" + sqle.getMessage());
// }
// }
// }
