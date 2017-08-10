/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package rptool.app.helpers;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author ivanskodje
 */
public class StageHelper
{

	// Static Stage Helper
	private static StageHelper stageHelper = null;

	/*
	This will center on current screen (based on where the cursor currently is)
	 */
	public static void centerOnScreen(Stage stage)
	{
		// init();

		// HACK: A hack to load the stage's window (and thus load width/height) without showing it
		stage.show();
		stage.hide();

		// Get current mouse position (4000, 2333 etc.)
		Point mouse = MouseInfo.getPointerInfo().getLocation();

		// Get screens
		ObservableList<Screen> screens = Screen.getScreens();

		// If the user have multiple monitors, find out which monitor the mouse is on, and get center position
		for (Screen screen : screens)
		{
			Rectangle2D screenBounds = screen.getVisualBounds();

			// Note: We check both X and Y mouse position to determine the screen the mouse is in.
			// This is because we do not know the orientation or positioning of the screens. 
			// It could be above, below, or even have monitors on top and to the side.
			// If mouse is between the current screen's X value
			if (isBetween(mouse.getX(), screenBounds.getMinX(), screenBounds.getMaxX()))
			{
				// If the mouse is between the current screen's Y value
				if (isBetween(mouse.getY(), screenBounds.getMinY(), screenBounds.getMaxY()))
				{
					// Mouse is on this screen - calculate coordinates
					stage.setX(screenBounds.getMinX() + (screenBounds.getWidth() / 2 - stage.getWidth() / 2));
					stage.setY(screenBounds.getMinY() + (screenBounds.getHeight() / 2 - stage.getHeight() / 2));
				}
			}
		}
	}

	/*
	This will center the stage on top of parentStage
	 */
	public static void centerOnStage(Stage stage, Stage parentStage)
	{
		// Init
		// init();

		// HACK: A hack to load the stage's window (and thus load width/height) without showing it
		stage.show();
		stage.hide();

		// Set new window pos to be on top of the main stage window
		stage.setX(parentStage.getX() + parentStage.getWidth() / 2 - stage.getWidth() / 2);
		stage.setY(parentStage.getY() + parentStage.getHeight() / 2 - stage.getHeight() / 2);
	}

	/* Each stage need to setup their own icons, silly enough */
	public static void setupIcons(Stage stage)
	{
		// Path to the app icons
		final String icon_path = "/rptool/app/ui/icons/";

		// Set all icons (that will be used accordingly)
		stage.getIcons().addAll(
				new Image(icon_path + "128x128.png"),
				new Image(icon_path + "64x64.png"),
				new Image(icon_path + "32x32.png"),
				new Image(icon_path + "16x16.png")
		);
	}

	/* Returns true or false depending if the input value is between valueStart and valueEnd */
	private static Boolean isBetween(double value, double start, double end)
	{
		return (value >= start && value <= end);
	}

}
