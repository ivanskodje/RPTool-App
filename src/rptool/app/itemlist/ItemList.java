/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist;

import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;

import javafx.scene.control.Tab;
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
		itemListNode = getContentFromList(ItemListManager.lists, null);
	}

	private int counter = 0;

	/**
	 * Returns the Item List Window, using an Tab Pane as the root node
	 *
	 * @param list
	 * @return
	 */
	private Node getContentFromList(ArrayList<List> lists, List rootList)
	{
		// Create ListTabPane for the Tabs
		JFXTabPane listTabPane = new JFXTabPane();

		/**
		 * Check if the list contains sublists. If it does, repeat. If it does
		 * not have lists, create the items and window.
		 */
		for (List list : lists)
		{
			List firstRootList = null;

			// If rootList is null, it means that we are on our root lists
			if (rootList == null)
			{
				// Store the first root list
				firstRootList = list;
			}
			else
			{
				firstRootList = rootList;
			}

			if (!list.getLists().isEmpty())
			{
				// Tab for the List
				Tab listTab = new Tab();

				// Tab name for the List
				listTab.setText(list.getName());

				// Get content from List
				listTab.setContent(getContentFromList(list.getLists(), firstRootList));

				// Add to Category Tab Pane
				listTabPane.getTabs().add(listTab);
			}
			else
			{
				// Tab for the List
				Tab listTab = new Tab();

				// Tab name for the List
				listTab.setText(list.getName());

				// Set tab content from the lists
				listTab.setContent(getContentWindow(list, firstRootList));

				// Set the tab with the lists to list pane
				listTabPane.getTabs().add(listTab);
			}
		}

		return listTabPane;
	}

	private Node getContentWindow(List list, List rootList)
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
			windowController.populateData(list, rootList); // Send the list reference to Window Controller
			return page;

		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: ItemList, loadWindow: \n" + ex.getMessage());
		}

		return null;
	}

	/**
	 * Loads Window.fxml that contains the ListView and window
	 *
	 * @param tab
	 * @param list
	 */
	private void loadWindow(Tab tab, List list, List category)
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
			// windowController.setListAndCategory(list, category); // Send the list reference to Window Controller
			tab.setContent(page);

		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: ItemList, loadWindow: \n" + ex.getMessage());
		}
	}
}
