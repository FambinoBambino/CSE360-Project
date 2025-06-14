import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartView extends Windows
{
	private int buyerID;
	Label emptyCartLabel = new Label("Cart is Empty, Keep Shopping :)");
	HBox emptyCartBox = new HBox(emptyCartLabel);
	private static final SQLDatabase db = SQLDatabase.getInstance();

	public CartView(Map<Book, Integer> book, Stage stage, int buyerID)
	{
		super();
		super.changeSize(600, 600);
		setUp(book, stage);
		this.buyerID = buyerID;
	}

	public void setUp(Map<Book, Integer> book, Stage view)
	{
		double total = 0;
		VBox ret = new VBox(10);
		Label receiptConfirmationLabel = new Label("Order Confirmed ✅");
		VBox receipt = new VBox(10);
		receipt.getChildren().addAll(receiptConfirmationLabel);
		for (Book item : book.keySet())
		{
			HBox count = new HBox(15);
			HBox receiptCount = new HBox(15);
			count.getChildren().addAll(new Label(item.getName() + " x " + String.valueOf(book.get(item))), new Label("$" + String.valueOf(trunc(book.get(item) * item.getBasePrice()))));
			receiptCount.getChildren().addAll(new Label(item.getName() + " x " + String.valueOf(book.get(item))), new Label("$" + String.valueOf(trunc(book.get(item) * item.getBasePrice()))));
			total += item.getBasePrice() * book.get(item);
			ret.getChildren().add(count);
			receipt.getChildren().add(receiptCount);
		}
		ret.setPadding(new Insets(20));
		receipt.setPadding(new Insets(20));
		HBox totalPrice = new HBox(10);
		totalPrice.getChildren().addAll(new Label("Subtotal:"), new Label("$" + String.valueOf(total)));
		HBox receiptTotalPrice = new HBox(10);
		receiptTotalPrice.getChildren().addAll(new Label("Subtotal:"), new Label("$" + String.valueOf(total)));

		HBox tax = new HBox(10);
		tax.getChildren().addAll(new Label("Tax:"), new Label("8%"));
		HBox receiptTax = new HBox(10);
		receiptTax.getChildren().addAll(new Label("Tax:"), new Label("8%"));

		HBox finalPrice = new HBox(10);
		finalPrice.getChildren().addAll(new Label("Total:"), new Label("$" + String.valueOf(total * 108 / 100)));
		HBox receiptFinalPrice = new HBox(10);
		receiptFinalPrice.getChildren().addAll(new Label("Total:"), new Label("$" + String.valueOf(total * 108 / 100)));

		ret.getChildren().addAll(totalPrice, tax, finalPrice);
		receipt.getChildren().addAll(receiptTotalPrice, receiptTax, receiptFinalPrice);

		HBox choice = new HBox(10);
		Button checkOut = new Button("Checkout");
		Button cancel = new Button("Cancel");
		Button clearCart = new Button("Clear Cart");
		choice.getChildren().addAll(checkOut, cancel, clearCart);
		choice.setPadding(new Insets(5));
		choice.setAlignment(Pos.CENTER);

		Pane cartPane = new Pane();
		cartPane.setStyle("-fx-background-color: rgb(140,29,64), rgb(255,198,39); -fx-background-insets: 0, 5");
		cartPane.getChildren().addAll(ret);// ASU colors used for these panes
		// cartPane.setVisible(false);

		Pane confirmationPane = new Pane();
		confirmationPane.setVisible(false);
		confirmationPane.setStyle("-fx-background-color: rgb(140,29,64), rgb(255,198,39); -fx-background-insets: 0, 5");
		Label confirmLabel = new Label("Are you sure you want to check out?");

		Pane receiptPane = new Pane();
		receiptPane.setStyle("-fx-background-color: rgb(140,29,64), rgb(255,198,39); -fx-background-insets: 0, 5");
		receiptPane.setVisible(false);

		Button yesButton = new Button("yes");
		yesButton.setOnAction(event ->
		{
			receiptPane.setVisible(true);
			book.clear();

			db.removeAllFromCart(buyerID);
		});

		Button noButton = new Button("No");
		noButton.setOnAction(event ->
		{
			confirmationPane.setVisible(false);
			cartPane.setVisible(true);
			choice.setVisible(true);
		});

		Button homeButton = new Button("Home");
		homeButton.setOnAction(event ->
		{
			this.close();
			view.show();
		});

		HBox buttonBox = new HBox(10, yesButton, noButton);
		buttonBox.setAlignment(Pos.CENTER);
		VBox finalConfirmBox = new VBox(10, confirmLabel, buttonBox);
		confirmationPane.getChildren().addAll(finalConfirmBox);
		finalConfirmBox.setAlignment(Pos.CENTER);
		finalConfirmBox.setPadding(new Insets(15));

		receipt.getChildren().addAll(homeButton);
		receipt.setAlignment(Pos.CENTER);
		receiptPane.getChildren().addAll(receipt);

		checkOut.setOnAction(event ->
		{// show confirmed screen along with transaction added to list.
			cartPane.setVisible(false);
			choice.setVisible(false);
			confirmationPane.setVisible(true);
		});

		cancel.setOnAction(event ->
		{
			this.close();
			view.show();
			totalPrice.setVisible(true);
			finalPrice.setVisible(true);
			tax.setVisible(true);
			checkOut.setVisible(true);
			clearCart.setVisible(true);
			cartPane.getChildren().remove(emptyCartBox);
		});

		clearCart.setOnAction(event ->
		{
			book.clear();
			db.removeAllFromCart(buyerID);
			this.close();
			view.show();
		});

		if (book.keySet().isEmpty())
		{
			totalPrice.setVisible(false);
			finalPrice.setVisible(false);
			tax.setVisible(false);
			checkOut.setVisible(false);
			clearCart.setVisible(false);
			cartPane.getChildren().add(emptyCartBox);
			emptyCartBox.setPadding(new Insets(10));
		}

		super.pane.setAlignment(Pos.CENTER);
		super.add(cartPane, 0, 0);
		super.add(confirmationPane, 0, 0);
		super.add(receiptPane, 0, 0);
		super.add(choice, 0, 1);
	}

	private Double trunc(Double value)
	{// Truncates price of books to two decimals so they do not appear long
		double truncated = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return truncated;
	}
}