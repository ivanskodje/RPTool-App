/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.ui.list.create;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import rptool.app.itemlist.ItemList;
import rptool.app.itemlist.classes.List;
import rptool.app.itemlist.manager.ItemListManager;

/**
 * FXML Controller class
 *
 * @author Ivan Skodje
 */
public class CreateListController implements Initializable
{

	@FXML
	private JFXTextField textFieldName;

	private List parentList = null;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// TODO
	}

	/**
	 * Create a List with the given name
	 *
	 * @param event
	 */
	@FXML
	private void onCreatePressed(ActionEvent event)
	{
		String name = textFieldName.getText();

		if (name.equals(""))
		{
			System.err.println("ERROR: Name cannot be empty!");
			return;
		}

		// Create list using the text
		List list = new List(name);

		// If we have set a parentList
		if (parentList != null)
		{
			// If we do not have any items, it means we also do not have a JFXTabPane to add our new List (Tab) in
			if (parentList.getItems().isEmpty())
			{
				// Add new list to parent array
				parentList.addList(list);

				// Set the parent list as parent to the new list as well
				list.setParentList(parentList);
			}
			else // Error, we cannot add a list to a list that has items!
			{
				onCancelPressed(null);
				System.err.println("ERROR: Cannot create a list on a list that has items!");
				return;
			}
		}
		else // No parent list, we are our own root list
		{
			// Set list to be its own root
			list.setRootList(list);
		}

		// Save entire list (using the root)
		ItemListManager.save(list.getRootList());

		// Remove window
		onCancelPressed(null);

		// Create the new List in UI
		// ItemList.getInstance().createNewList(list);
		ItemList.getInstance().reloadUI();
	}

	/**
	 * Close Window
	 *
	 * @param event
	 */
	@FXML
	private void onCancelPressed(ActionEvent event)
	{
		Stage stage = (Stage) textFieldName.getScene().getWindow();
		stage.close();
	}

	/**
	 * Sets the parent tab, where we would add the new list on (if any)
	 *
	 * @param list
	 */
	public void setParentList(List list)
	{
		this.parentList = list;
	}
}
