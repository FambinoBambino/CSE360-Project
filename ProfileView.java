import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileView extends Windows
{
    private Label firstName;
    private Label lastName;
    private Label email;
    private Label userID;
    private Label password;
    private Button backButton, showORhideButton;
    private String hidePass = "***", showPass;
    private boolean shown = false; // tracks if password is being shown or not

    public ProfileView(Stage primaryStage, User user)
    {// Displays information current user account
        Label title = new Label("User Information");
        firstName = new Label();
        lastName = new Label();
        email = new Label();
        userID = new Label();
        password = new Label(hidePass);
        backButton = new Button("Back");
        showORhideButton = new Button("Show");

        if (user == null)
        {
            firstName.setText("No User Found");
            lastName.setText("No User Found");
            email.setText("No Email");
            userID.setText("No User Found");
        } else
        {
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            email.setText(user.geteAddress());
            userID.setText(user.getUserID());
            showPass = user.getPassword();
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(title, new HBox(10, new Label("First Name:"), firstName), new HBox(10, new Label("Last Name:"), lastName), new HBox(10, new Label("Email Address:"), email), new HBox(10, new Label("User ID:"), userID), new HBox(10, new Label("Password:"), password, showORhideButton), backButton);

        Pane profilePane = new Pane();
        profilePane.setStyle("-fx-background-color: rgb(140,29,64), rgb(255,198,39); -fx-background-insets: 0, 5");
        profilePane.getChildren().addAll(layout);
        super.add(profilePane, 0, 0);
        super.pane.setAlignment(Pos.CENTER);

        backButton.setOnAction(event -> back(primaryStage));
        showORhideButton.setOnAction(event ->
        {// allows user to show or hide their password. By default it is hidden
            if (shown == false)
            {
                showORhideButton.setText("Hide");
                password.setText(showPass);
                shown = true;
            } else if (shown == true)
            {
                showORhideButton.setText("Show");
                password.setText(hidePass);
                shown = false;
            }
        });
    }

    private void back(Stage primaryStage)
    {
        this.close();
        primaryStage.show();
    }
}
