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

	// Item Name : Title of the item
	private String name;

	// Category Lists : Array of Lists belonging to Category
	private ArrayList<List> lists = new ArrayList<>();

	// Items : Array of Item objects contained in List
	private ArrayList<Item> items = new ArrayList<>();

	/**
	 * All lists needs a category. A category may have several lists, but lists
	 * can only belong to one category.
	 *
	 * @param name
	 */
	public List(String name)
	{
		this.name = name;
	}

	/**
	 * Set category name
	 *
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Return category name
	 *
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set category lists
	 *
	 * @param lists
	 */
	public void setLists(ArrayList<List> lists)
	{
		this.lists = lists;
	}

	/**
	 * Return category lists
	 *
	 * @return
	 */
	public ArrayList<List> getLists()
	{
		if (lists == null)
		{
			return new ArrayList<>();
		}
		return lists;
	}

	/**
	 * Add one list to lists
	 *
	 * @param list
	 */
	public void addList(List list)
	{
		lists.add(list);
	}

	/**
	 * Removes given list from lists
	 *
	 * @param list
	 */
	public void removeList(List list)
	{
		lists.remove(list);
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
