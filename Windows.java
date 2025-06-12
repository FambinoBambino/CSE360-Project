import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Windows
{
	private Rectangle2D windowBounds = Screen.getPrimary().getBounds();
	// extracts the system's width and height

	final public double WINDOW_WIDTH = windowBounds.getWidth();
	final private double WINDOW_HEIGHT = windowBounds.getHeight();
	final private Image transparentImage = new Image("https://cactussports.com/cdn/shop/products/pmaziaeydikzclf4nt1z_1400x.jpg?v=1649726209");
	BackgroundImage bgImage = new BackgroundImage(transparentImage, null, null, BackgroundPosition.CENTER, null);

	public GridPane pane = new GridPane();
	private ScrollPane scroll = new ScrollPane();
	Stage windowStage = new Stage();

	private boolean isVisible = false;

	public Windows()
	{
		pane.setBackground(new Background(bgImage));
		scroll.setContent(pane);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		windowStage.setScene(new Scene(scroll, WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	public void add(Node node, int x, int y)
	{// since we are using gridpane, this method add an object to grid pane with
		// coordinates
		pane.add(node, x, y);
	}

	public void display()
	{
		if (!isVisible)
		{
			isVisible = true;
			windowStage.show();
		}
	}

	public void close()
	{
		if (isVisible)
		{
			windowStage.hide();
			isVisible = false;
		}
	}

	public double getWINDOW_WIDTH()
	{
		return WINDOW_WIDTH;
	}

	public double getWINDOW_HEIGHT()
	{
		return WINDOW_HEIGHT;
	}

	public void setVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}

	public boolean getVisibility()
	{
		return isVisible;
	}

	public void changeSize(int width, int height)
	{// change size of image
		BackgroundSize newSize = new BackgroundSize(width, height, false, false, true, true);
		bgImage = new BackgroundImage(transparentImage, null, null, BackgroundPosition.CENTER, newSize);
	}
}
