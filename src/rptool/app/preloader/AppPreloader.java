/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.preloader;

import java.io.IOException;
import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rptool.app.helpers.AlertDialog;
import rptool.app.helpers.StageHelper;

/**
 *
 * @author ivanskodje
 */
public class AppPreloader extends Preloader
{

	// Preloader reference for closing it
	private Stage preloaderStage;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// Store reference to the Main stage,
			// in order to run it when loading has completed
			this.preloaderStage = primaryStage;

			// Setup preloader (loading bar)
			Parent root = FXMLLoader.load(getClass().getResource("AppPreloader.fxml")); // Display a "loading" bar
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			StageHelper.setupIcons(primaryStage);
			primaryStage.show();
		}
		catch (IOException ex)
		{
			AlertDialog.showAndWait(
					null,
					"Exception",
					"AppPreloader.java -> start",
					"\n" + ex.getMessage()
			);
		}
	}

	@Override
	public void handleStateChangeNotification(Preloader.StateChangeNotification stateChangeNotification)
	{
		if (stateChangeNotification.getType() == Type.BEFORE_START)
		{
			preloaderStage.hide();
		}
	}
}
