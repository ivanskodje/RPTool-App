/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.ui.window;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import rptool.app.itemlist.classes.Category;
import rptool.app.itemlist.classes.Item;
import rptool.app.itemlist.classes.List;
import rptool.app.itemlist.manager.ItemListManager;
import rptool.app.itemlist.ui.edit.EditController;
import rptool.app.itemlist.ui.paste.PasteController;
import rptool.app.itemlist.ui.view.ViewController;

/**
 * FXML Controller class
 *
 * @author Ivan Skodje
 */
public class WindowController implements Initializable
{

	@FXML
	private JFXListView<Item> listView;
	@FXML
	private StackPane stackPane;
	@FXML
	private TextField searchTextField;

	// Selected list
	private List list;

	// Items belonging to the list
	private ArrayList<Item> items;

	// The category data for the list and items we are on
	private Category category;

	// List Property for Filtering Data
	ListProperty<Item> listProperty = new SimpleListProperty<>();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// Populate ListView
		// ....
	}

	public void setListAndCategory(List list, Category category)
	{
		this.list = list;
		this.category = category;
		populateListView();
	}

	private void populateListView()
	{
		// Get items
		items = list.getItems();

		// Sort items in list
		Collections.sort(items);

		// Show all items on first run (otherwise it will be empty until you begin searching)
		listView.setItems(FXCollections.observableArrayList(items));

		// Set listProperty with the array
		listProperty.set(FXCollections.observableArrayList(items));

		// FilteredList for Search
		FilteredList<Item> filteredData = new FilteredList<>(listProperty, e -> true);

		// Bind 
		// On Key Released Event
		searchTextField.setOnKeyPressed((event) ->
		{
			// Add a listener to text property, to check what we are typing
			searchTextField.textProperty().addListener((observableValue, oldValue, newValue) ->
			{
				/*
				 * The predicate that will match the elements that will be in this FilteredList. 
				 * Elements not matching the predicate will be filtered-out. 
				 * Null predicate means "always true" predicate, all elements will be matched.
				 */
				filteredData.setPredicate((Predicate<? super Item>) (Item value) ->
				{
					// If the value (search string) is empty, display all
					if (newValue.isEmpty())
					{
						return true;
					}

					// Get new value as lower capital (we dont check for case)
					String lowerCaseFilter = newValue.toLowerCase();

					// If we have a match for the name
					if (value.getName().toLowerCase().contains(lowerCaseFilter)) // Continue making else if to check for other matches that may be relevant
					{
						return true;
					}

					// No match, don't show
					{
						return false;
					}
				});
			});

			// Create Sorted Data from Filtered Data
			SortedList<Item> sortedData = new SortedList<>(filteredData);

			// Set sorted data
			listView.setItems(sortedData);
		});
	}

	/**
	 * Pick a random out of the current items in ListView
	 *
	 * @param event
	 */
	@FXML
	private void onRandomPressed(ActionEvent event)
	{
		if (items.isEmpty())
		{
			return;
		}

		// Generate a random index
		Random rnd = new Random();
		int index = rnd.nextInt(listView.getItems().size());

		// Set that index as selected
		listView.getSelectionModel().select(index);

		// Get selected Artifact
		Item selected = listView.getSelectionModel().getSelectedItem();

		// Open view
		openViewWindow(selected);
	}

	@FXML
	private void onNewItemPressed(ActionEvent event)
	{
		openPasteWindow();
	}

	@FXML
	private void onListViewMousePressed(MouseEvent event)
	{
		// Get selected Artifact
		Item selectedItem = listView.getSelectionModel().getSelectedItem();

		// If we have a valid Item, open View window
		if (selectedItem != null)
		{
			openViewWindow(selectedItem);
		}
	}

	/**
	 * Opens the ViewWindow with the Item information
	 *
	 * @param item
	 * @param selected
	 */
	public void openViewWindow(Item item)
	{
		// Path to FXML
		String fxmlFileName = "/rptool/app/itemlist/ui/view/View.fxml";

		try
		{
			// Load Window
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Node page = fxmlLoader.load(getClass().getResource(fxmlFileName).openStream());

			// Send Item to controller
			ViewController viewController = (ViewController) fxmlLoader.getController();
			viewController.setItem(item);
			viewController.setParentController(this);

			// Clear existing data
			stackPane.getChildren().clear();

			// Set page to StackPane
			stackPane.getChildren().setAll(page);
		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: WindowController, openViewWindow: \n" + ex.getMessage());
		}
	}

	/**
	 * Opens edit window when given an Item
	 *
	 * @param item
	 */
	public void openEditWindow(Item item)
	{
		// Clear existing data
		stackPane.getChildren().clear();

		// Path to FXML
		String fxmlFileName = "/rptool/app/itemlist/ui/edit/Edit.fxml";

		try
		{
			// Load Window
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Node page = fxmlLoader.load(getClass().getResource(fxmlFileName).openStream());

			// Send Item to controller
			EditController controller = (EditController) fxmlLoader.getController();
			controller.setItem(item);
			controller.setParentController(this);

			// Set page to StackPane
			stackPane.getChildren().setAll(page);
		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: WindowController, openEditWindow: \n" + ex.getMessage());
		}
	}

	/**
	 * Opens paste window, for new item creation
	 *
	 * @param item
	 */
	public void openPasteWindow()
	{
		// Clear existing data
		stackPane.getChildren().clear();

		// Path to FXML
		String fxmlFileName = "/rptool/app/itemlist/ui/paste/Paste.fxml";

		try
		{
			// Load Window
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Node page = fxmlLoader.load(getClass().getResource(fxmlFileName).openStream());

			// Send Item to controller
			PasteController controller = (PasteController) fxmlLoader.getController();
			controller.setParentController(this);

			// Set page to StackPane
			stackPane.getChildren().setAll(page);
		}
		catch (IOException ex)
		{
			System.err.println("EXCEPTION: WindowController, openEditWindow: \n" + ex.getMessage());
		}
	}

	/**
	 * Returns the category we are on. Used for saving json.
	 *
	 * @return
	 */
	public Category getCategory()
	{
		return category;
	}

	/**
	 * Refreshes list view. Should be run after making changes to items.
	 */
	public void refreshListView()
	{
		listView.refresh();
	}

	/**
	 * Adds a new item to the list
	 *
	 * @param item
	 */
	public void addItem(Item item)
	{
		// Add new item to the list
		list.getItems().add(item);

		// Refresh ListView
		populateListView();

		// Save changes
		ItemListManager.save(category);
	}

	/**
	 * Deletes item
	 *
	 * @param item
	 */
	public void deleteItem(Item item)
	{
		// Remove item from the List
		list.getItems().remove(item);

		// Refresh list view by repopulating with the new and updated list
		populateListView(); // FIXME: For some reason, removing the item from the items array and refreshing does not seem to work.

		// Save
		ItemListManager.save(category);

		// Remove the item view for the item we just deleted
		stackPane.getChildren().clear();
	}

}
