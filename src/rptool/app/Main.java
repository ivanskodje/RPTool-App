/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app;

import rptool.app.preloader.AppPreloader;
import com.sun.javafx.application.LauncherImpl;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import rptool.app.helpers.StageHelper;
import rptool.app.itemlist.classes.List;
import rptool.app.itemlist.manager.ItemListManager;

/**
 *
 * @author Ivan Skodje
 */
public class Main extends Application
{

	/* App */
	String app_name = "RPTool";

	/* Versioning */
	int ver_major = 0;	// Major updates that changes the app as an whole
	int ver_minor = 2;	// Minor changes that may improve upon existing features
	int ver_mini = 1;	// Mini changes such as bug fixes

	/**
	 * Launch Preloader before Main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		LauncherImpl.launchApplication(Main.class, AppPreloader.class, args);
	}

	/* Preload */
	@Override
	public void init() throws Exception
	{
		// TODO: Load Locale from config
		Locale.setDefault(new Locale("en", "US"));

		// Load all Item List Data
		ItemListManager.load();

		// debug_save();
	}

	/**
	 * Display Main FXML (after Preloader has finished)
	 *
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("/rptool/app/Main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		StageHelper.setupIcons(primaryStage);
		primaryStage.setTitle(app_name + " - v" + ver_major + "." + ver_minor + "." + ver_mini);
		primaryStage.show();
	}

	/**
	 * DEBUGGING FUNCTION
	 */
	private void debug_save()
	{
		// DEBUG---
		// Populate data
		ItemListManager.populateDummyData("Terran");
		ItemListManager.populateDummyData("Zerg");
		ItemListManager.populateDummyData("Protoss");

		// Get first category
		List category = ItemListManager.lists.get(2);

		// Save Category
		ItemListManager.save(category);
	}
}
