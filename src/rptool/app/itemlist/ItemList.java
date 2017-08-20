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
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;

import javafx.scene.control.Tab;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rptool.app.helpers.AlertDialog;
import rptool.app.helpers.StageHelper;
import rptool.app.itemlist.classes.List;
import rptool.app.itemlist.contextmenu.TabContextMenu;
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
		itemListNode = getContentFromList(ItemListManager.lists);
	}

	public void reloadUI()
	{
		itemListNode = getContentFromList(ItemListManager.lists);
	}

	/**
	 * Creates a brand new list, and stores it
	 *
	 * @param list
	 * @param parentList
	 */
	public void createNewList(List list)
	{
		// Create ListTabPane for the Tabs (if we have a parent tab with only '1' list)
		JFXTabPane listTabPane = new JFXTabPane();

		/// ---- SETTING UP THE NEW LIST
		// Create Tab Context Menu for each List
		ContextMenu contextMenu = new TabContextMenu(list);

		// Tab for the List
		Tab listTab = new Tab();

		// Set context menu
		listTab.setContextMenu(contextMenu);

		// Tab name for the List
		listTab.setText(list.getName());

		// Create window for items
		listTab.setContent(getContentWindow(list));

		// Add tab to either an existing tabpane or the root tabpane
		if (list.getParentList() != null)
		{
			// Check if we are the first list added to parent from UI
			if (list.getParentList().getLists().size() == 1)
			{
				ArrayList<List> myList = new ArrayList<>();
				myList.add(list);
				JFXTabPane tabPaneWithList = (JFXTabPane) getContentFromList(myList);
				list.getParentList().getTab().setContent(tabPaneWithList);
			}
			else
			{

			}
		}
		else
		{
			// Add the new List (TAB) to the existing JFXTabPane on root
			((JFXTabPane) itemListNode).getTabs().add(listTab);
		}

//		if (parentList != null)
//		{
//			// We need to add our tab to a new TabPane that will appear underneath the existing List
//			listTabPane.getTabs().add(listTab);
//		}
//		else
//		{
//			// Since we have no existing
//			((JFXTabPane) itemListNode).getTabs().add(listTab);
//		}
	}

	/**
	 * Returns the Item List Window, using an Tab Pane as the root node
	 *
	 * @param list
	 * @return
	 */
	private Node getContentFromList(ArrayList<List> lists)
	{
		// Create ListTabPane for the Tabs
		JFXTabPane listTabPane = new JFXTabPane();

		/**
		 * Check if the list contains sublists. If it does, repeat. If it does
		 * not have lists, create the items and window.
		 */
		for (List list : lists)
		{
			// Create Tab Context Menu for each List
			ContextMenu contextMenu = new TabContextMenu(list);

			// Tab for the List
			Tab listTab = new Tab();

			// Store the TabPane this list is placed in (as a tab)
			// list.setTab(listTabPane);
			// Set context menu
			listTab.setContextMenu(contextMenu);

			// Tab name for the List
			listTab.setText(list.getName());

			// If the list contains other lists
			if (!list.getLists().isEmpty())
			{
				// Get content from List
				listTab.setContent(getContentFromList(list.getLists()));
			}
			// List does not contain other lists: Load window for items
			else
			{
				// Set tab content from the lists
				listTab.setContent(getContentWindow(list));
			}

			// Add list tab to tabpane
			listTabPane.getTabs().add(listTab);
		}

		return listTabPane;
	}

	private Node getContentWindow(List list)
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
			windowController.populateData(list); // Send the list reference to Window Controller
			return page;

		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: ItemList, loadWindow: \n" + ex.getMessage());
		}

		return null;
	}


	/*
	Loads scene given a path and window title
	 */
	public Initializable loadWindow(String resourcePath, String title)
	{
		try
		{
			// Create loader and add locale
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
			Parent parent = (Parent) loader.load();
			Initializable controller = loader.getController();
			// Setup Stage
			Stage stage = new Stage(StageStyle.DECORATED);
			StageHelper.setupIcons(stage);
			stage.setTitle(title);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(parent));
			stage.show();

			return controller;
		}
		catch (IOException ex)
		{
			AlertDialog.showAndWait(
					null,
					"IOException",
					getClass().getName(),
					"\n" + ex.getMessage()
			);
		}

		return null;
	}
}
