/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import rptool.app.itemlist.ItemList;

/**
 * FXML Controller class
 *
 * @author Ivan Skodje
 */
public class MainController implements Initializable
{

	@FXML
	private StackPane stackPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// ItemListManager.mainController = this;
		// By default show Item List
		stackPane.getChildren().add(ItemList.getInstance().getNode());
	}

	/**
	 * Change to Item List mode
	 *
	 * @param event
	 */
	@FXML
	private void onItemListPressed(ActionEvent event)
	{
		stackPane.getChildren().clear(); // Clear existing Scene before continuing
		stackPane.getChildren().add(ItemList.getInstance().getNode());
	}

	/**
	 * Test button, for clearing and going between two Scenes
	 *
	 * @param event
	 */
	@FXML
	private void onTestPressed(ActionEvent event)
	{
		stackPane.getChildren().clear();
	}

}
