/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.classes;

import java.util.ArrayList;

/**
 * Each list can contain several Items. Items are entries that consists of at
 * least a Name, and optionally properties or descriptions.
 *
 * @author Ivan Skodje
 */
public class Item implements Comparable<Item>
{

	// Item Name : Title of the item
	private String name;

	// Item Properties : Array of Property objects
	private ArrayList<Property> properties = new ArrayList<>();

	/**
	 * Item found in Lists. Each List may contain many Items, however items can
	 * only belong to one List.
	 *
	 * @param name
	 * @param properties
	 */
	public Item(String name)
	{
		this.name = name;
	}

	/**
	 * Returns item name
	 *
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets item name
	 *
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Returns item properties using an ArrayList
	 *
	 * @return
	 */
	public ArrayList<Property> getProperties()
	{
		return properties;
	}

	/**
	 * Sets item properties using an ArrayList
	 *
	 * @param properties
	 */
	public void setProperties(ArrayList<Property> properties)
	{
		this.properties = properties;
	}

	/**
	 * Add a new property, given a Property object
	 *
	 * @param property
	 */
	public void addProperty(Property property)
	{
		properties.add(property);
	}

	/**
	 * Removes given property from the item
	 *
	 * @param property
	 * @return
	 */
	public void removeProperty(Property property)
	{
		properties.remove(property);
	}

	/**
	 * Returns the item name. Used to display item name in ListView or Trees.
	 *
	 * @return
	 */
	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * Allows us to sort Items with each other
	 *
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Item o)
	{
		// A-Z Sorting
		return this.name.compareTo(o.getName());
	}
}
