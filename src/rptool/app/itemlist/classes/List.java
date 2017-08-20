/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.classes;

import com.jfoenix.controls.JFXTabPane;
import java.util.ArrayList;
import javafx.scene.control.Tab;

/**
 *
 * @author Ivan Skodje
 */
public class List
{

	// Item Name : Title of the item
	private String name;

	// Root List (ignored by gson due to transient)
	private transient List rootList;

	// This lists' tab
	private transient Tab tab;

	// This lists Tab Pane
	private transient JFXTabPane tabPane;

	// Parent list
	private transient List parentList;

	// Category Lists : Array of Lists belonging to Category
	private ArrayList<List> lists;

	// Items : Array of Item objects contained in List
	private ArrayList<Item> items;

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
			lists = new ArrayList<>();
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
		if (lists == null)
		{
			lists = new ArrayList<>();
		}

		// Set us as parent
		list.setParentList(this); // We are the parent

		// Set root list
		if (rootList == null)
		{
			list.setRootList(this); // we are the root
		}
		else
		{
			list.setRootList(rootList); // send same root to child
		}

		// Add list to array
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
	 * Set all items, and store the List (self) object to Item for later use
	 *
	 * @param items
	 */
	public void setItems(ArrayList<Item> items)
	{
		for (Item item : items)
		{
			item.setParentList(this);
		}
		this.items = items;
	}

	/**
	 * Return all items
	 *
	 * @return
	 */
	public ArrayList<Item> getItems()
	{
		if (items == null)
		{
			items = new ArrayList<>();
		}
		return items;
	}

	/**
	 * Add a new item to List
	 *
	 * @param item
	 */
	public void addItem(Item item)
	{
		if (items == null)
		{
			items = new ArrayList<>();
		}
		item.setParentList(this);
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

	/**
	 * Sets root list
	 *
	 * @param rootList
	 */
	public void setRootList(List rootList)
	{
		this.rootList = rootList;
	}

	/**
	 * Returns root list. Used to store all lists in a file.
	 *
	 * @return
	 */
	public List getRootList()
	{
		return rootList;
	}

	/**
	 * Sets tabpane
	 *
	 * @param tab
	 */
	public void setTab(Tab tab)
	{
		this.tab = tab;
	}

	/**
	 * Returns the tabpane this list is in
	 *
	 * @return
	 */
	public Tab getTab()
	{
		return tab;
	}

	/**
	 * Sets tabpane
	 *
	 * @param tabPane
	 */
	public void setTabPane(JFXTabPane tabPane)
	{
		this.tabPane = tabPane;
	}

	/**
	 * Returns the tabpane this list is in
	 *
	 * @return
	 */
	public JFXTabPane getTabPane()
	{
		return tabPane;
	}

	/**
	 * Sets the parent list of this item
	 *
	 * @param parentList
	 */
	public void setParentList(List parentList)
	{
		this.parentList = parentList;
	}

	/**
	 * Returns the parent list of this item
	 *
	 * @return
	 */
	public List getParentList()
	{
		return parentList;
	}

	/**
	 * Check and set parents and root lists to children
	 */
	public void refreshData()
	{
		if (lists != null)
		{
			for (List list : lists)
			{
				// We are always the parent
				list.setParentList(this);

				// Set root list
				if (rootList == null)
				{
					list.setRootList(this);
				}
				else
				{
					list.setRootList(rootList);
				}

				// Refresh data
				list.refreshData();
			}
		}
	}
}
