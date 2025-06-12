// import java.io.FileNotFoundException;
// import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Windows
{
	private TextField email;
	private PasswordField password;
	private Button login;
	private Button signUp;
	private int buyerID = -1;
	private SQLDatabase db = SQLDatabase.getInstance();

	private AdminView adminPage = new AdminView(super.windowStage);
	private BuyerView buyerPage = new BuyerView(super.windowStage);
	private SellerView sellerPage = new SellerView(super.windowStage);

	public LoginView()
	{
		super(); // call initializer for parent class Windows

		Label wrongCred = new Label("You have entered the wrong credentials");
		// label for displaying wrong message.
		password = new PasswordField();
		email = new TextField();
		ComboBox<String> roles = new ComboBox<>();
		roles.getItems().addAll("Admin", "Buyer", "Seller");
		roles.setValue("Buyer");
		login = new Button("Login");
		signUp = new Button("Sign up");
		email.setPromptText("Enter your email");
		password.setPromptText("Enter your password");
		VBox userInput = new VBox(10);
		userInput.getChildren().addAll(email, password, roles, login, signUp);
		super.add(userInput, 0, 0);
		super.pane.setAlignment(Pos.CENTER);

		// set functionality for buttons
		login.setOnAction(event ->
		{
			String type = roles.getValue();
			Boolean result = false;
			if (type.equals("Admin"))
			{
				result = validate(email.getText(), password.getText(), "Admin");
				if (result == true)
				{
					wrongCred.setVisible(false);
					email.clear();
					password.clear();
					this.windowStage.hide();
					adminPage.display();
				}

			} else if (type.equals("Seller"))
			{
				result = validate(email.getText(), password.getText(), "Seller");
				if (result == true)
				{
					wrongCred.setVisible(false);
					email.clear();
					password.clear();
					this.windowStage.hide();
					sellerPage.setSellerID(buyerID);
					sellerPage.display();
				}

			} else if (type.equals("Buyer"))
			{
				result = validate(email.getText(), password.getText(), "Buyer");
				if (result == true)
				{
					wrongCred.setVisible(false);
					email.clear();
					password.clear();
					this.windowStage.hide();
					buyerPage.setBuyerID(buyerID);
					buyerPage.display();
				}

			}
			if (!result)
			{
				wrongCred.setStyle("-fx-text-fill: red;");
				if (!userInput.getChildren().contains(wrongCred))
				{
					userInput.getChildren().addAll(wrongCred);
				}
				wrongCred.setVisible(true);
			}
		});

		signUp.setOnAction(event ->
		{
			signUp(super.windowStage);
			for (int i = 0; i < userInput.getChildren().size(); i++)
			{
				if (userInput.getChildren().get(i) instanceof Label)
				{
					Label remove = (Label) userInput.getChildren().get(i);
					if (remove.getText().equals("You have entered the wrong credentials"))
					{
						userInput.getChildren().remove(i);
					}
				}
			}
			email.clear();
			password.clear();
		});
	}

	public boolean validate(String email, String password, String userType)
	{
		int bID = db.validateFromEmail(email, password, userType);
		if (bID != -1)
		{
			buyerID = bID;
			return true;
		}
		return false;
	}

	public AdminView getAdminPage()
	{
		return adminPage;
	}

	public void signUp(Stage stage)
	{
		stage.hide();
		Windows signUp = new Windows();
		VBox signupWindow = new VBox(10);
		TextField first = new TextField();
		TextField last = new TextField();
		TextField email = new TextField();
		TextField password = new TextField();
		ComboBox<String> roles = new ComboBox<>();
		Button signUpButton = new Button("Sign Up");
		Windows success = new Windows();
		Button backToLogin = new Button("Back to Login page");
		Label error = new Label();

		first.setPromptText("Enter your first name");
		last.setPromptText("enter your last name");
		email.setPromptText("Enter your email");
		password.setPromptText("Create your password");
		roles.getItems().addAll("Seller", "Buyer");
		roles.setValue("Buyer");
		signupWindow.getChildren().addAll(first, last, email, password, roles, signUpButton);
		signUp.pane.setAlignment(Pos.CENTER);
		signUp.add(signupWindow, 0, 0);
		success.pane.setAlignment(Pos.CENTER);
		success.add(new Label("You have signed up"), 0, 0);
		success.add(backToLogin, 0, 1);
		signupWindow.getChildren().addAll(error);
		error.setStyle("-fx-text-fill: red;");

		signUp.display();
		error.setText("");
		signUpButton.setOnAction(event ->
		{
			if (!email.getText().strip().isEmpty() && !password.getText().strip().isEmpty() && !first.getText().strip().isEmpty() && !last.getText().strip().isEmpty())
			{
				String role = roles.getValue();
				if (db.ExistingUser(email.getText().strip(), role) == false)
				{
					// note: Only buyer and seller can signup, admin comes
					// preloaded in the text file.
					db.addUser(first.getText(), last.getText(), email.getText(), password.getText(), role);
					signUp.close();
					success.display();
					error.setText("");
				} else
				{
					error.setText("An account with that email already exists");
				}
			} else
			{
				error.setText("There is at least 1 field that is empty");
				error.setStyle("-fx-text-fill: red;");
			}
		});

		backToLogin.setOnAction(event ->
		{
			email.clear();
			password.clear();
			roles.setValue("buyer");
			success.close();
			signUp.close();
			signupWindow.getChildren().remove(error);
			stage.show();
		});
	}
}