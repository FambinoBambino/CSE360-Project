import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) 
	{
		try {
			LoginView lv = new LoginView();
			lv.display();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}