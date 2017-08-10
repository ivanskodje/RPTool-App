/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.helpers;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;


/* Helper class to display Alert Dialogs without needing a lot of extra methods in each class*/
public class AlertDialog
{

	// Static Alert Dialog
	private static AlertDialog alertDialog = null;

	/**
	 * Display an Error alert dialog Box
	 *
	 * @param stageOwner
	 * @param title
	 * @param headerText
	 * @param message
	 */
	public static void showAndWait(Stage stageOwner, String title, String headerText, String message)
	{
		showAndWait(stageOwner, title, headerText, message, Alert.AlertType.ERROR);
	}

	/**
	 * Display a defined dialog box
	 *
	 * @param stageOwner
	 * @param title
	 * @param headerText
	 * @param message
	 * @param alertType
	 * @return
	 */
	public static boolean showAndWait(Stage stageOwner, String title, String headerText, String message, Alert.AlertType alertType)
	{
		Optional<ButtonType> result = null;

		// On first run, make sure we have created an instance of ourselves
		if (alertDialog == null)
		{
			alertDialog = new AlertDialog();
		}

		// Print Error to Debugging / Output
		if (alertType == Alert.AlertType.ERROR)
		{
			System.err.print("ERROR:\n" + headerText + " : " + message + "\n\n");
		}

		// Display AlertDialog
		try
		{
			Alert alert = new Alert(alertType, message);
			alert.setHeaderText(headerText);
			alert.setTitle(title);
			alert.initModality(Modality.APPLICATION_MODAL);
			if (stageOwner != null)
			{
				alert.initOwner(stageOwner);
			}
			result = alert.showAndWait();
		}
		catch (Exception ex)
		{
			// (Something went *really* bad when the error gives an error - It still has to be handled! #determination)
			ex.printStackTrace();
		}

		return (result.isPresent() && result.get() == ButtonType.OK);
	}
}
