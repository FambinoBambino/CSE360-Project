import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BuyerView extends Windows {
	//Variables
	Map<Book, Integer> cartItems; // cart items
	List<Book> books; // a list of book that is presented to the buyer
	int buyerID = -1;
	private static final Database db = Database.getInstance();

	Stage loginStage = new Stage();

	public BuyerView(Stage loginStage){
		super(); // initiate the buyerView as a window.
		this.loginStage = loginStage;
		books = new ArrayList<>();
		cartItems = new HashMap<>();
		Populate();
		setUp();
	}
	
	private void setUp() {
		
		VBox display = new VBox(20);
		Button cart = new Button("Cart");
		Button userInfo = new Button("Profile");
		Button logOut = new Button("Log out");
		ComboBox<String> sort = new ComboBox<>();
		sort.getItems().addAll("By Conditions", "By Category");
		sort.setValue("By Conditions");
		
		VBox choice = new VBox(15);
		choice.getChildren().addAll(cart, sort);
		HBox choice1 = new HBox(super.WINDOW_WIDTH - 300);
		VBox choice2 = new VBox(15);
		choice2.getChildren().addAll(userInfo, logOut);
		choice1.getChildren().addAll(choice, choice2);
		choice1.setPadding(new Insets(50, 0, 0, 50));
		display.getChildren().add(choice1);
		
		this.Default(display);
		sort.setOnAction(event-> {
			String sortVal = sort.getValue();
			if (sortVal.equals("By Conditions")) {
				if(display.getChildren().size() == 2) {
					display.getChildren().remove(1);
				}
				Default(display);
			} else if(sortVal.equals("By Category")) {
				if(display.getChildren().size() == 2) {
					display.getChildren().remove(1);
				}
				Alternate(display);
			}
		});
		
		userInfo.setOnAction(event -> {
			super.windowStage.hide();
			ProfileView profileView = new ProfileView(super.windowStage, db.getUser(buyerID, "Buyer"));
			super.windowStage.hide();
			profileView.display();
		});
		
		cart.setOnAction(event-> {
			super.windowStage.hide();
			System.out.println(buyerID);
			CartView cartView = new CartView(this.cartItems, super.windowStage, buyerID);
			super.windowStage.hide();
			cartView.display();
		});
		
		logOut.setOnAction(event-> {
			try
			{
				FileWriter cartFile = new FileWriter("Database/cart" + Integer.toString(buyerID) + ".txt");
				for(Book item: cartItems.keySet()) 
				{//save cart tiems for next login
					cartFile.write(item.getName() + "," + String.valueOf(cartItems.get(item)) + "," + Double.toString(item.getBasePrice()) + "," + item.getCategory() + "," + item.getCondition() + "\n");
				}
				cartFile.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			cartItems.clear();
			
			this.close();
			loginStage.show();
			
		});
		super.add(display, 0, 0);
	
	}
	private VBox createListing(Book item) {
		VBox book = new VBox(5);
		HBox buttons = new HBox(2);
		Button addToCart =  new Button("Add to cart");
		ComboBox<Integer> number = new ComboBox<>();
		number.getItems().addAll(1,2, 3, 4, 5, 6);
		number.setValue(1);
		
		buttons.getChildren().addAll(addToCart, number);
		ImageView image = new ImageView(item.getImage());
		image.setFitHeight(80);
		image.setFitWidth(80);
		book.getChildren().addAll(image, new Label(item.getName()), new Label(String.valueOf(item.getBasePrice())),buttons);
		
		addToCart.setOnAction(event->{
			cartItems.put(item, number.getValue());
			//open file and add item to user's cart file
		});
		return book;
	}
	
	private VBox add(List<Book> books) {
		VBox ret = new VBox(10);
		for(int i = 0; i < books.size(); i+= 3) {
			
			HBox row = new HBox(100);
			if (i + 3 > books.size() - 1) {
				int val = i;
				while (val < books.size()) {
					Book item = books.get(val);
					VBox listing = createListing(item);
					row.getChildren().add(listing);
					val += 1;
				}
			} else {
				row.getChildren().addAll(createListing(books.get(i)), createListing(books.get(i + 1)), createListing(books.get(i + 2)));
			}
			ret.getChildren().add(row);
			continue;
			
		}
		return ret;
	}
	
	public void Default(VBox display) {
		List<Book> newBook = new ArrayList<>();
		List<Book> likeNew = new ArrayList<>();
		List<Book> oldBook = new ArrayList<>();
		for(int i = 0; i < this.books.size(); i++) {
			Book item = this.books.get(i);
			if(item.getCondition().equals("New")) {
				newBook.add(item);
			} else if (item.getCondition().equals("Like New")){
				likeNew.add(item);
			} else {
				oldBook.add(item);
			}
		}
		
		VBox ret = new VBox(10);
		ret.setSpacing(25);
		HBox New = new HBox();
		Label CondNewLabel = new Label("Condition: New");
		CondNewLabel.setFont(new Font(25));
		New.getChildren().add(CondNewLabel);

		HBox likeNew1 = new HBox();
		Label CondLikeNewLabel = new Label("Condition: Like New");
		CondLikeNewLabel.setFont(new Font(25));
		likeNew1.getChildren().add(CondLikeNewLabel);

		HBox used = new HBox();
		Label CondUsedLabel = new Label("Condition: Used");
		CondUsedLabel.setFont(new Font(25));
		used.getChildren().add(CondUsedLabel);
		
		ret.getChildren().addAll(New, add(newBook));
		ret.getChildren().addAll(likeNew1, add(likeNew));
		ret.getChildren().addAll(used, add(oldBook));
		
		ret.setPadding(new Insets(0,0,0, 400));
		display.getChildren().add(ret);
	}
	
	public void Alternate(VBox display) {
		Populate();
		List<Book> copy = this.books;
		VBox ret = new VBox();
		ret.setSpacing(25);
		List<String> category = new ArrayList<>();
		for(Book item: copy) {
			if(!category.contains(item.getCategory())) {
				category.add(item.getCategory());
			}
		}
		
		for(String i: category) {

			List<Book> filterCategory = new ArrayList<>();
			
			for (Book item: copy) {
				if (item.getCategory().equals(i)) {
					filterCategory.add(item);
				}
			}
			
			Label CatLabel = new Label("Category: " + i);
			CatLabel.setFont(new Font(25));
			
			ret.getChildren().addAll(CatLabel, add(filterCategory));
			
		}
		ret.setPadding(new Insets(0,0,0,400));
		display.getChildren().add(ret);
		
	}
	
	public void Populate() {
		books = db.getAllBooks();
	}
	
	public void addItems(Book book, int quantity) {
		cartItems.put(book, quantity);
	}
	public void removeItemFromCart(Book book) {
		cartItems.remove(book);
	}

	public void setBuyerID(int buyerID) 
	{
		this.buyerID = buyerID;
		try
		{//populate cart w/ previous cart from last session
			File cartFile = new File ("Database/cart" + Integer.toString(buyerID) + ".txt");
			cartFile.createNewFile();
			Scanner readPrevCart = new Scanner(cartFile);
			while (readPrevCart.hasNextLine()) 
			{
				String image = "https://m.media-amazon.com/images/I/71fL+SKczgL.jpg";
				String line =readPrevCart.nextLine(); //title, amount of book, price, category, condition
					String[] info = line.split(",");
				if (info.length == 5)
				{
					cartItems.put( new Book(
							info[0].strip(), //title
							"Author", 
							Double.parseDouble(info[2]), //price
							null, //User Seller
							info[3].strip(), //category
							info[4].strip(), //condition
							0, //bookID
							image), Integer.parseInt(info[1].strip())); //image for book
				}
			}
			readPrevCart.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error creating file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public int getBuyerID() {
		return buyerID;
	}
}
