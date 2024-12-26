import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Windows {
		private TextField userName;
		private PasswordField password;
		private Button login;
		private Button signUp;
		private int buyerID = -1;
		private Database db = Database.getInstance();

		private AdminView adminPage = new AdminView(super.windowStage);
		private BuyerView buyerPage = new BuyerView(super.windowStage);
		private SellerView sellerPage = new SellerView(super.windowStage);
		
		public LoginView() throws IOException
		{
			super(); //call initializer for parent class Windows
			
			Label wrongCred = new Label("You have entered the wrong credentials"); // label for displaying wrong message.
			password = new PasswordField();
			userName = new TextField();
			ComboBox<String> roles = new ComboBox<>();
			roles.getItems().addAll("Admin", "Buyer", "Seller");
			roles.setValue("Buyer");
			login = new Button("Login");
			signUp = new Button("Sign up");
			userName.setPromptText("Enter your username");
			password.setPromptText("Enter your password");
			VBox userInput = new VBox(10);
			userInput.getChildren().addAll(userName, password, roles, login, signUp);
			super.add(userInput, 0, 0);
			super.pane.setAlignment(Pos.CENTER);
			
			//set functionality for buttons
			login.setOnAction(event-> {
				String type = roles.getValue();
				Boolean result = false;
				if (type.equals("Admin")) {
					try {
						result = validate(userName.getText(), password.getText(), "Admin");
						if (result == true)
						{
							wrongCred.setVisible(false);
							userName.clear();
							password.clear();
							this.windowStage.hide();
							adminPage.display();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(type.equals("Seller")) {
					try {
						result = validate(userName.getText(), password.getText(), "Seller");
						if (result == true)
						{
							wrongCred.setVisible(false);
							userName.clear();
							password.clear();
							this.windowStage.hide(); 
							sellerPage.setSellerID(buyerID);
							sellerPage.display();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(type.equals("Buyer")) {
					try {
						result = validate(userName.getText(), password.getText(), "Buyer");
						if (result == true)
						{
							wrongCred.setVisible(false);
							userName.clear();
							password.clear();
							this.windowStage.hide();
							buyerPage.setBuyerID(buyerID);
							buyerPage.display();
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (!result) {
					wrongCred.setStyle("-fx-text-fill: red;");
					if(!userInput.getChildren().contains(wrongCred)) {
						userInput.getChildren().addAll(wrongCred);
					}
					wrongCred.setVisible(true);
				}
			});
			
			signUp.setOnAction(event-> {
				signUp(super.windowStage);
				for(int i =0 ; i < userInput.getChildren().size(); i ++) {
					if(userInput.getChildren().get(i) instanceof Label) {
						Label remove = (Label) userInput.getChildren().get(i);
						if(remove.getText().equals("You have entered the wrong credentials")) {
							userInput.getChildren().remove(i);
						}
					}
				}
				userName.clear();
				password.clear();
			});
		}

		public boolean validate(String name, String password, String userType) throws FileNotFoundException {
			int bID = db.validateFromUsername(name, password, userType);
			if (bID != -1) {
				buyerID = bID;
				return true;
			}
			return false;
		}

		public AdminView getAdminPage() {
			return adminPage;
		}
		
		public void signUp(Stage stage) {
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
			signUpButton.setOnAction(event-> {
				if(!email.getText().strip().isEmpty() && !password.getText().strip().isEmpty() && !first.getText().strip().isEmpty() && !last.getText().strip().isEmpty()) {
					String role = roles.getValue();
					if (db.ExistingUser(email.getText().strip(), role) == false)
					{
						//note: Only buyer and seller can signup, admin comes preloaded in the text file.
						db.addUser(first.getText(), last.getText(), email.getText(), password.getText(), role);
						signUp.close();
						success.display();
						error.setText("");
					}
					else
					{
						error.setText("An account with that email already exists");
					}
				} else {
					error.setText("There is at least 1 field that is empty");
					error.setStyle("-fx-text-fill: red;");
				}
			});
			
			backToLogin.setOnAction(event-> {
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