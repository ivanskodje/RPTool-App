/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.classes;

import java.util.ArrayList;

/**
 *
 * @author Ivan Skodje
 */
public class List
{

	// Name : Title of the List containing the Items
	private String name;

	// Items : Array of Item objects contained in List
	private ArrayList<Item> items = new ArrayList<>();

	/**
	 * A List found in an Category. Each Category contains many lists, however
	 * Lists only have one Category.
	 *
	 * @param name
	 * @param items
	 */
	public List(String name)
	{
		this.name = name;
	}

	/**
	 * Set list name
	 *
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Return list name
	 *
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set all items
	 *
	 * @param items
	 */
	public void setItems(ArrayList<Item> items)
	{
		this.items = items;
	}

	/**
	 * Return all items
	 *
	 * @return
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}

	/**
	 * Add a new item to List
	 *
	 * @param item
	 */
	public void addItem(Item item)
	{
		items.add(item);
	}

	/**
	 * Removes given item from items list
	 *
	 * @param item
	 */
	public void removeItem(Item item)
	{
		items.remove(item);
	}

}
