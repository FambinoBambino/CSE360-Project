import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SellerView extends Windows {
    private Stage loginStage;
    private int sellerID = -1;

    private static final Database db = Database.getInstance();

    private ArrayList<Book> listedItems = new ArrayList<>();
    private BorderPane mainLayout;

    public SellerView(Stage stage) {
        super();
        this.loginStage = stage;

        mainLayout = new BorderPane();

        Pane sellerPane = new Pane();
		sellerPane.setStyle("-fx-background-color: rgb(140,29,64), rgb(255,198,39); -fx-background-insets: 0, 5");
		sellerPane.getChildren().addAll(mainLayout);

        super.add(sellerPane, 0, 0);
		super.pane.setAlignment(Pos.CENTER);

        displayHomePage();
    }

    private void displayHomePage() {
        mainLayout.setTop(null);
        mainLayout.setCenter(null);

        //title
        Label title = new Label("Sun Devil Books");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setStyle("-fx-text-fill: black;");

        //buttons
        Button myBooksButton = new Button("My Books");
        Button listBookButton = new Button("List A Book");
        Button logoutButton = new Button("Log Out");

        myBooksButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        listBookButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        logoutButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");

        //button actions
        myBooksButton.setOnAction(event -> displayMyBooks());
        listBookButton.setOnAction(event -> displayListABookForm());
        logoutButton.setOnAction(event -> logout());

        //layout for buttons
        VBox buttonLayout = new VBox(20, myBooksButton, listBookButton, logoutButton);
        buttonLayout.setAlignment(Pos.CENTER);

        //overall layout for home view
        VBox homeLayout = new VBox(50, title, buttonLayout);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setPadding(new Insets(20));

        mainLayout.setCenter(homeLayout);

    }

    private void displayMyBooks() {
        mainLayout.setTop(null);
        mainLayout.setCenter(null);

        //title
        Label title = new Label("Inventory");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setStyle("-fx-text-fill: black;");
        title.setAlignment(Pos.CENTER); // Center the label
        BorderPane.setAlignment(title, Pos.CENTER);

        //back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setOnAction(event -> displayHomePage());

        //list a Book button
        Button listBookButton = new Button("List A Book");
        listBookButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        listBookButton.setOnAction(event -> displayListABookForm());

        //navigation bar with back and list buttons
        HBox topNavigation = new HBox();
        topNavigation.setPadding(new Insets(10, 20, 10, 20));
        topNavigation.setSpacing(800); //add spacing to spread buttons apart
        topNavigation.getChildren().addAll(backButton, listBookButton);
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setAlignment(listBookButton, Pos.TOP_RIGHT);

        //book grid
        GridPane bookGrid = new GridPane();
        bookGrid.setHgap(20);
        bookGrid.setVgap(10);
        bookGrid.setPadding(new Insets(20));
        bookGrid.setAlignment(Pos.CENTER);

        //populate "grid" with books
        if (listedItems.isEmpty()) {
            Label noBooksLabel = new Label("No books available."); //when there are no books listed
            noBooksLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            bookGrid.add(noBooksLabel, 0, 0);
        } else {
            for (int i = 0; i < listedItems.size(); i++) {
                Book book = listedItems.get(i);

                //attempt to separate title, price, and buttons for better spacing
                Label titleLabel = new Label(book.getName());
                titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                Label priceLabel = new Label("$" + String.format("%.2f", book.getBasePrice()) + " (" + book.getCondition() + ")");
                priceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

                Button delistButton = new Button("De-list");
                delistButton.setStyle("-fx-font-size: 14px; -fx-padding: 5px 15px;");
                int bookIndex = i; //capture index for lambda
                delistButton.setOnAction(event -> {
                    listedItems.remove(bookIndex);
                    db.removeLineFromFile("Database/books.txt", book.getBookID());
                    displayMyBooks(); //refresh view
                });

                HBox bookRow = new HBox(20, titleLabel, priceLabel, delistButton);
                bookRow.setAlignment(Pos.CENTER_LEFT);

                bookGrid.add(bookRow, 0, i);
            }
        }

        //add elements to layout
        VBox contentLayout = new VBox(20, title, bookGrid); //center title and grid
        contentLayout.setAlignment(Pos.CENTER);

        mainLayout.setTop(topNavigation);
        mainLayout.setCenter(contentLayout);
    }

    private void displayListABookForm() {
        mainLayout.setTop(null);
        mainLayout.setCenter(null);

        ComboBox<String> conditions = new ComboBox<>();
			conditions.getItems().addAll("New", "Like New", "Used");
			conditions.setValue("New");
            
        //title for the List a Book page
        Label title = new Label("List a Book");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setStyle("-fx-text-fill: black;");
        title.setAlignment(Pos.CENTER);

        //back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setOnAction(event -> displayHomePage());

        //HBox for back navigation
        HBox topNavigation = new HBox(backButton);
        topNavigation.setAlignment(Pos.TOP_LEFT);
        topNavigation.setPadding(new Insets(10, 10, 10, 10));

        //form for listing a book
        Label nameLabel = new Label("Book Name");
        TextField nameField = new TextField();
        Label conditionLabel = new Label("Condition");
        //TextField conditionField = new TextField();
        Label categoryLabel = new Label("Category");
        TextField categoryField = new TextField();
        Label priceLabel = new Label("Original Price");
        TextField priceField = new TextField();

        Label finalPriceLabel = new Label("Final Price: $");
        Label finalPriceValue = new Label("0.00");

        //button to calculate the final price
        Button calculatePriceButton = new Button("Calculate Final Price");
        calculatePriceButton.setOnAction(event -> {
            try {
                double originalPrice = Double.parseDouble(priceField.getText());
                String condition = conditions.getValue().toLowerCase();

                double finalPrice = originalPrice;
                if (condition.equals("used")) {
                    finalPrice *= 0.80; //20% off
                } else if (condition.equals("like new")) {
                    finalPrice *= 0.90; //10% off
                }
                finalPriceValue.setText(String.format("%.2f", finalPrice));
            } catch (NumberFormatException e) {
                finalPriceValue.setText("Invalid input");
            }
        });

        Button listBookButton = new Button("List my Book");
        listBookButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");
        listBookButton.setOnAction(event -> {
            try {
                if (nameField.getText().strip().isEmpty() || categoryField.getText().strip().isEmpty() || priceField.getText().strip().isEmpty() || finalPriceValue.getText().strip().isEmpty())
                {//do nothing if missing input detected
                    System.out.println("Missing input");
                }
                else 
                {
                String name = nameField.getText();
                String condition = conditions.getValue();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());
                double finalPrice = Double.parseDouble(finalPriceValue.getText());

                //add the new book to the seller's inventory
                Book newBook = new Book(name, "author to be added", finalPrice, null, category, condition, db.getLastBookID(), "https://m.media-amazon.com/images/I/71fL+SKczgL.jpg");
                listedItems.add(newBook);
                db.addBook(newBook,sellerID);

                displaySuccessPage(); //confirm book listing to user
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

        //layout for the form
        GridPane formLayout = new GridPane();
        formLayout.setHgap(20);
        formLayout.setVgap(15);
        formLayout.setAlignment(Pos.CENTER);

        formLayout.addRow(0, nameLabel, nameField);
        formLayout.addRow(1, conditionLabel, conditions);
        formLayout.addRow(2, categoryLabel, categoryField);
        formLayout.addRow(3, priceLabel, priceField);
        formLayout.addRow(4, finalPriceLabel, finalPriceValue);
        formLayout.add(calculatePriceButton, 1, 5);
        formLayout.add(listBookButton, 1, 6);

        VBox contentLayout = new VBox(20, title, formLayout);
        contentLayout.setAlignment(Pos.CENTER);
        topNavigation.setPadding(new Insets(10));
        contentLayout.setPadding(new Insets(10));

        mainLayout.setTop(topNavigation);
        mainLayout.setCenter(contentLayout);
    }

    private void displaySuccessPage() {
        mainLayout.setTop(null);
        mainLayout.setCenter(null);

        //success Message
        Label successLabel = new Label("Successfully listed!");
        successLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        successLabel.setStyle("-fx-text-fill: black;");
        successLabel.setAlignment(Pos.CENTER);

        //back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setOnAction(event -> displayHomePage());

        //list Another Book button
        Button listAnotherBookButton = new Button("List Another Book");
        listAnotherBookButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px 20px;");
        listAnotherBookButton.setOnAction(event -> displayListABookForm());

        //navigation bar with back and list another book buttons
        HBox topNavigation = new HBox();
        topNavigation.setPadding(new Insets(10, 20, 10, 20));
        topNavigation.setSpacing(800);
        topNavigation.getChildren().addAll(backButton, listAnotherBookButton);
        BorderPane.setAlignment(backButton, Pos.TOP_LEFT);
        BorderPane.setAlignment(listAnotherBookButton, Pos.TOP_RIGHT);

        //add elements to layout
        mainLayout.setTop(topNavigation);
        mainLayout.setCenter(successLabel);
    }


    private void logout() {
        this.close();
        loginStage.show();
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
        System.out.println(this.sellerID);
        listedItems = db.getAllBooks(sellerID);
    }
}
