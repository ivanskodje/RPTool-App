/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import rptool.app.MainController;
import rptool.app.itemlist.classes.*;

/**
 *
 * @author Ivan Skodje
 */
public class ItemListManager
{

	public static MainController mainController;

	/**
	 * Each Category is loaded from separate .json files.
	 */
	public static ArrayList<Category> categories = new ArrayList<>();

	// Setup a Gson 
	private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	// List folder path
	private final static String PATH = "data/lists/";

	/**
	 * Loads all json lists found in PATH
	 *
	 * @return
	 */
	public static boolean load()
	{
		// If the PATH does not exist, stop.
		if (!new File(PATH).exists())
		{
			return false;
		}

		// Iterate through all files inside PATH directory
		File folder = new File(PATH);
		File[] listOfFiles = folder.listFiles();

		for (File listOfFile : listOfFiles)
		{
			try
			{
				// If we have a file
				if (listOfFile.isFile())
				{
					// Get full file name
					String fileName = listOfFile.getName();
					// Get file extension
					String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					// If we have a JSON file
					if (extension.equals("json"))
					{
						// Load Item List and store as Category
						byte[] encoded = Files.readAllBytes(Paths.get(PATH + fileName));
						String jsonData = new String(encoded, "UTF-8");

						// Store as Category
						Category category = GSON.fromJson(jsonData, new TypeToken<Category>()
						{
						}.getType());

						// Store category in array
						categories.add(category);
					}
				}
			}
			catch (Exception ex)
			{
				System.err.println("<ERROR START> ItemListManager, load() EXCEPTION ERROR: \n" + ex.getMessage() + "\n<ERROR END>");
			}
		}

		return false;
	}

	/**
	 * Saves given Item List (from the top, Categories)
	 *
	 * @param category
	 * @return
	 */
	public static boolean save(Category category)
	{
		// Convert Item List Data into a JSON string
		String jsonData = GSON.toJson(category);

		try
		{
			// Make sure we have created folders
			Files.createDirectories(Paths.get(PATH));
		}
		catch (IOException ex)
		{
			System.err.println("IOException: " + ex.getMessage());
		}

		try
		{
			// Create a clean file name
			String fileName = category.getName().toLowerCase().replaceAll("[^\\w\\s]", "");

			// Write to file, using the category name as the file name
			PrintWriter writer = new PrintWriter(PATH + fileName + ".json");
			writer.println(jsonData);
			writer.close();
			return true;
		}
		catch (IOException ex)
		{
			System.err.println("DataManager, save() EXCEPTION ERROR: \n" + ex.getMessage());
		}
		return false;
	}

	/**
	 * DEBUG METHOD:
	 *
	 * Adding dummy data
	 *
	 * @param categoryName
	 */
	public static void populateDummyData(String categoryName)
	{
		// Generic description for Properties
		String description = "Lorem ipsum dolor sit amet, rhoncus diam vivamus libero amet fermentum erat, "
				+ "eget velit tincidunt suscipit velit, et class vel tempus nec, pariatur "
				+ "tempor vivamus ultricies. Adipiscing quis lorem placerat felis habitant "
				+ "et, aliquam vitae dui quos eu, quo mauris erat. Pede mauris bibendum";

		// Create Category 1
		Category category1 = new Category(categoryName);

		// Create List 1
		List list1 = new List("Ground Units");

		// Create Items for List 1
		Item item1 = new Item("Marine");
		Item item2 = new Item("Marauder");
		Item item3 = new Item("SCV");
		Item item4 = new Item("Reaver");

		// Create Properties for Items
		Property prop1 = new Property("Property 1", description);
		Property prop2 = new Property("Property 2", description);
		Property prop3 = new Property("Property 3", description);

		// Assign Properties to Items
		item1.addProperty(prop1);
		item2.addProperty(prop1);
		item2.addProperty(prop2);
		item3.addProperty(prop1);
		item3.addProperty(prop2);
		item3.addProperty(prop3);
		item4.addProperty(prop1);

		// Assign Items to List 1
		list1.addItem(item1);
		list1.addItem(item2);
		list1.addItem(item3);
		list1.addItem(item4);

		// Assign List 1 to Category 1
		category1.addList(list1);

		// Create List 2
		List list2 = new List("Air Units");

		// Create Items for List 2
		Item item5 = new Item("Battlecruiser");
		Item item6 = new Item("Raven");

		// Assign Properties to Items
		// item5 is not given properties for testing purposes
		item6.addProperty(prop1);

		// Assign Items to List 2
		list2.addItem(item5);
		list2.addItem(item6);

		// Assign List 2 to Category 1
		category1.addList(list2);

		// Add Category1 to Categories List
		categories.add(category1);
	}
}
