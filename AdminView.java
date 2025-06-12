import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminView extends Windows
{
	Stage loginStage = new Stage();

	public AdminView(Stage loginStage)
	{
		super();
		// call initializer for parent class Windows
		this.loginStage = loginStage;

		// customizing the looks for the windows.
		Label homePageLabel = new Label("Admin Home Page");
		// label for displaying home page message.

		BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(140, 29, 64), // maroon?
				new CornerRadii(0, 2, 2, 0, true),
				// CornerRadii: (top-left, top-right, bottom-right, bottom-left)
				Insets.EMPTY);

		Button transactionButton = new Button("Transaction");
		transactionButton.setPadding(new Insets(20, 30, 20, 30));
		// Padding: top, right, bottom, left
		transactionButton.setTextFill(Color.rgb(255, 198, 39));
		transactionButton.setBackground(new Background(backgroundFill));
		transactionButton.setOnAction(event ->
		{
			System.out.println("transaction");
		});

		Button statsButton = new Button("View Stats");
		statsButton.setPadding(new Insets(20, 30, 20, 30));
		// Padding: top, right, bottom, left
		statsButton.setTextFill(Color.rgb(255, 198, 39));
		statsButton.setBackground(new Background(backgroundFill));
		statsButton.setOnAction(event ->
		{
			System.out.println("stats");
		});

		Button manageButton = new Button("Manage");
		manageButton.setPadding(new Insets(20, 30, 20, 30));
		// Padding: top, right, bottom, left
		manageButton.setTextFill(Color.rgb(255, 198, 39));
		manageButton.setBackground(new Background(backgroundFill));
		manageButton.setOnAction(event ->
		{
			System.out.println("manage");
		});

		Button logoutButton = new Button("Log Out");
		logoutButton.setPadding(new Insets(20, 30, 20, 30));
		// Padding: top, right, bottom, left
		logoutButton.setTextFill(Color.rgb(255, 198, 39));
		logoutButton.setBackground(new Background(backgroundFill));
		logoutButton.setOnAction(event ->
		{
			System.out.println("Log out");
			this.close();
			loginStage.show();
		});

		VBox homePageBox = new VBox(10);
		homePageBox.getChildren().addAll(homePageLabel, transactionButton, statsButton, manageButton, logoutButton);
		super.add(homePageBox, 0, 0);
		super.pane.setAlignment(Pos.CENTER_LEFT);

	}

}
