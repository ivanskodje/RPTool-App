/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;

import javafx.scene.control.Tab;
import rptool.app.itemlist.classes.Category;
import rptool.app.itemlist.classes.List;
import rptool.app.itemlist.manager.ItemListManager;
import rptool.app.itemlist.ui.window.WindowController;

/**
 *
 * @author Ivan Skodje
 */
public class ItemList
{

	// ItemList instance : There can only be one instance!
	private static ItemList itemListInstance;

	// ItemList Node : We add this content to scene when we want this to be active
	private static Node itemListNode;

	/**
	 * Private constructor : Prevents object creation from outside the class
	 */
	private ItemList()
	{
		// Loads node data as it is instanced the first time
		loadNode();
	}

	/**
	 * Returns ItemList instance : The only way of accessing ItemList
	 *
	 * @return
	 */
	public static ItemList getInstance()
	{
		if (itemListInstance == null)
		{
			itemListInstance = new ItemList();
		}

		return itemListInstance;
	}

	/**
	 * Returns the main ItemList Node Scene, using existing data. Can only be
	 * accessed via getInstance().
	 *
	 * @return
	 */
	public Node getNode()
	{
		return itemListNode;
	}

	/**
	 * Loads node data
	 */
	private void loadNode()
	{
		// Create Category Tab Pane for all Categories
		JFXTabPane categoryTabPane = new JFXTabPane();

		// TODO: Make a good CSS
		// categoryTabPane.getStylesheets().add("/rptool/app/ui/css/default.css");
		// -
		// For each category, create and add a tab
		for (Category category : ItemListManager.categories)
		{
			// Tab
			Tab categoryTab = new Tab();
			// categoryTab.setUserData(category);
			categoryTab.setText(category.getName());

			// Get content from Category
			categoryTab.setContent(getContentFromCategory(category));

			// Add to Category Tab Pane
			categoryTabPane.getTabs().add(categoryTab);
		}

		// Store item list window
		itemListNode = categoryTabPane;
	}

	/**
	 * Returns the List Tab Pane generated from Category
	 *
	 * @param category
	 * @return
	 */
	private Node getContentFromCategory(Category category)
	{
		// Create List Tab Pane for all Categories
		JFXTabPane listTabPane = new JFXTabPane();

		// For each list, create and add a tab
		for (List list : category.getLists())
		{
			// Tab
			Tab listTab = new Tab();

			// listTab.setUserData(list);
			listTab.setText(list.getName());

			// Open window
			loadWindow(listTab, list, category);

			// Add to Category Tab Pane
			listTabPane.getTabs().add(listTab);
		}

		return listTabPane;
	}

	/**
	 * Loads Window.fxml that contains the ListView and window
	 *
	 * @param tab
	 * @param list
	 */
	private void loadWindow(Tab tab, List list, Category category)
	{
		// Path to Window.fxml
		String fxmlFileName = "/rptool/app/itemlist/ui/window/Window.fxml";

		try
		{
			// Load Window
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Node page = fxmlLoader.load(getClass().getResource(fxmlFileName).openStream());
			WindowController windowController = (WindowController) fxmlLoader.getController();
			windowController.setListAndCategory(list, category); // Send the list reference to Window Controller
			tab.setContent(page);

		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: ItemList, loadWindow: \n" + ex.getMessage());
		}
	}
}
