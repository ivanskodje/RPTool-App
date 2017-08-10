/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.ui.paste;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import rptool.app.helpers.AlertDialog;
import rptool.app.itemlist.classes.Item;
import rptool.app.itemlist.classes.Property;
import rptool.app.itemlist.ui.window.WindowController;

/**
 * FXML Controller class
 *
 * @author Ivan Skodje
 */
public class PasteController implements Initializable
{

	@FXML
	private Label label;
	@FXML
	private TextArea textArea;

	// Window Controller parent
	private WindowController parentController;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// TODO
	}

	/**
	 * Used by WindowController (parent) in order for us to let it know when we
	 * want to edit this item.
	 *
	 * @param parentController
	 */
	public void setParentController(WindowController parentController)
	{
		this.parentController = parentController;
	}

	@FXML
	private void onHelpPressed(ActionEvent event)
	{
		AlertDialog.showAndWait(null, "Help", "Formatting", "NAME\n"
				+ "PROPERTY 1: DESCRIPTION\n"
				+ "PROPERTY 2: DESCRIPTION ON SAME LINE\n"
				+ "MORE DESCRIPTION FOR PROPERTY 2\n"
				+ "PROPERTY 3: DESCRIPTION FOR THIRD PROPERTY", Alert.AlertType.INFORMATION);
	}

	@FXML
	private void onClearPressed(ActionEvent event)
	{
		textArea.clear();
	}

	@FXML
	private void onAddPressed(ActionEvent event)
	{
		// Get text
		String text = textArea.getText();

		// If it is empty
		if (text.equals(""))
		{
			// FIXME: Also check for empty spaces
			return;
		}

		// Get pasted string (separated by newline)
		String[] pastedString = text.split("\\r?\\n");

		// Properties belonging to the Artifact
		ArrayList<Property> properties = new ArrayList<>();

		// Artifact - First line will always be the title
		Item newItem = new Item(pastedString[0]);

		// Iterate each line (except the first)
		for (int i = 1; i < pastedString.length; i++)
		{
			// Check if we have a : that indicates a "type".
			if (pastedString[i].contains(":"))
			{
				// Set Property name
				String[] splitText = pastedString[i].split(":");
				properties.add(new Property(splitText[0]));

				// Set property description
				String description = pastedString[i].replace(splitText[0] + ":", "").replaceFirst("^\\s*", "");
				properties.get(properties.size() - 1).setDescription(description);
			}
			else
			{
				// Does not contain : means we are appending the description to the last one
				// Get last description string
				if (!properties.isEmpty())
				{
					String description = properties.get(properties.size() - 1).getDescription();

					// Append to description string
					description += pastedString[i] + " ";

					// Set new description
					properties.get(properties.size() - 1).setDescription(description);
				}

			}
		}

		// Set properties
		newItem.setProperties(properties);

		// Add new Item
		parentController.addItem(newItem);

		// Clear text
		textArea.clear();
	}

}
