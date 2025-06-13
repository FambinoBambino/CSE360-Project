import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{// Need to think about introducing threads for concurrent users
	@Override
	public void start(Stage primaryStage)
	{// Displays the login page for SunDevil Books
		try
		{
			LoginView lv = new LoginView();
			lv.display();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}