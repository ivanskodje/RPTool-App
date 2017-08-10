/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.ui.view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import rptool.app.helpers.AlertDialog;
import rptool.app.itemlist.classes.Item;
import rptool.app.itemlist.classes.Property;
import rptool.app.itemlist.ui.window.WindowController;

/**
 * FXML Controller class
 *
 * @author Ivan Skodje
 */
public class ViewController implements Initializable
{

	@FXML
	private Label label;
	@FXML
	private StackPane stackPane;

	// Selected item
	private Item item;

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

	public void setItem(Item item)
	{
		this.item = item;

		// Set Artifact Name
		label.setText(item.getName());

		// Text Flow
		TextFlow textFlow = new TextFlow();

		// Setup formatting and add to text flow
		for (Property description : item.getProperties())
		{
			// Description Name
			Text name = new Text();
			name.setText(description.getName() + ":\n");
			name.setFont(Font.font("Verdana", 20));
			name.setFill(Color.BROWN);

			// Description
			Text desc = new Text();
			desc.setText(description.getDescription() + "\n\n");
			desc.setFont(Font.font("Verdana", 12));
			desc.setFill(Color.BLACK);

			// Append to TextFlow
			textFlow.getChildren().addAll(name, desc);
		}

		// Add TextFlow to the StackPane
		stackPane.getChildren().add(textFlow);
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
	private void onEditPressed(ActionEvent event)
	{
		parentController.openEditWindow(item);
	}

	@FXML
	private void onDeletePressed(ActionEvent event)
	{
		if (AlertDialog.showAndWait(null, "Delete?", "", "Are you sure you wish to delete this entry?", Alert.AlertType.CONFIRMATION))
		{
			// Delete the item
			parentController.deleteItem(item);
		}
	}

	@FXML
	private void onCopyTextPressed(ActionEvent event)
	{
		// Build string
		String copyText = "__**" + item.getName() + "**__\n\n";

		// Get each description
		for (Property desc : item.getProperties())
		{
			copyText += "**" + desc.getName() + "**:\n" + desc.getDescription() + "\n\n";
		}

		// Store to clipboard
		StringSelection stringSelection = new StringSelection(copyText);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);

		// TODO: Display a message to indicate success
		// ...
		System.out.println("TODO: Display a message to indicate success");
	}

}
