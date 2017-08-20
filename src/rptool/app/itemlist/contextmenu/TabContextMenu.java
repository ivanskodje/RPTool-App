/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.contextmenu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Popup;
import rptool.app.itemlist.ItemList;
import rptool.app.itemlist.classes.List;
import rptool.app.itemlist.ui.list.create.CreateListController;

/**
 *
 * @author Ivan Skodje
 */
public class TabContextMenu extends ContextMenu
{

	/**
	 * Creates a Context Menu for each lists' tab
	 *
	 * @param list
	 */
	public TabContextMenu(List list)
	{
		// Tab Menu Context Items
		// Create New List
		MenuItem newItem = new MenuItem("Create New List");
		newItem.setOnAction(event ->
		{
			ItemList.getInstance().loadWindow("/rptool/app/itemlist/ui/list/create/CreateList.fxml", "Create New List");
		});

		// Separator
		SeparatorMenuItem separator = new SeparatorMenuItem();

		// Add List
		MenuItem addListItem = new MenuItem("Create Sub List in " + list.getName());
		addListItem.setOnAction(event ->
		{
			System.out.println("Add list to " + list.getName());
			CreateListController controller = (CreateListController) ItemList.getInstance().loadWindow("/rptool/app/itemlist/ui/list/create/CreateList.fxml", "Create New List");
			controller.setParentList(list);
		});

		// Edit List
		MenuItem editItem = new MenuItem("Edit " + list.getName());
		editItem.setOnAction(event ->
		{
			String item = list.getName();
			System.out.println("Edit item: " + item);
		});

		// Delete List
		MenuItem deleteItem = new MenuItem("Delete " + list.getName());
		deleteItem.setOnAction(event ->
		{
			String item = list.getName();
			System.out.println("Delete item: " + item);
		});

		// Separator
		SeparatorMenuItem separator2 = new SeparatorMenuItem();

		// Move list left
		MenuItem moveLeftItem = new MenuItem("Move Left");
		deleteItem.setOnAction(event ->
		{
			System.out.println("Move item left");
		});

		// Move list right
		MenuItem moveRightItem = new MenuItem("Move Right");
		deleteItem.setOnAction(event ->
		{
			System.out.println("Move item right");
		});

		// Add items
		getItems().addAll(newItem, separator, addListItem, editItem, deleteItem, separator2, moveLeftItem, moveRightItem);
	}
}
