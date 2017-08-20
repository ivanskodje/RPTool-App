/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rptool.app.itemlist.classes;

import com.jfoenix.controls.JFXTabPane;
import java.util.ArrayList;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ivan Skodje
 */
public class ListTest
{

	public ListTest()
	{
	}

	@BeforeClass
	public static void setUpClass()
	{
	}

	@AfterClass
	public static void tearDownClass()
	{
	}

	@Before
	public void setUp()
	{
	}

	@After
	public void tearDown()
	{
	}

	/**
	 * Test of setName method, of class List.
	 */
	@Test
	public void testSetName()
	{
		System.out.println("setName");
		String name = "Storage List";
		List instance = new List("Bathroom Supplies");
		instance.setName(name);
		assertEquals("Expected List name to be " + name + ", but it was not.", name, instance.getName());
	}

	/**
	 * Test of getName method, of class List.
	 */
	@Test
	public void testGetName()
	{
		System.out.println("getName");
		String name = "Items";
		List instance = new List(name);
		assertEquals("Expected to get name " + name + ".", name, instance.getName());
	}

	/**
	 * Test of setLists method, of class List.
	 */
	@Test
	public void testSetLists()
	{
		System.out.println("setLists");

		// Prepare List Array
		ArrayList<List> lists = new ArrayList<>();
		List list1 = new List("List 1");
		List list2 = new List("List 2");
		lists.add(list1);
		lists.add(list2);

		// Create the instance we are going to test setLists on
		List instance = new List("");

		// Set lists
		instance.setLists(lists);
	}

	/**
	 * Test of getLists method, of class List.
	 */
	@Test
	public void testGetLists()
	{
		System.out.println("getLists");

		// Create List instance
		List instance = new List("A List");

		// Create the List Array we add to List instance, and what we expect
		ArrayList<List> expResult = new ArrayList<>();
		expResult.add(new List("List 1"));
		expResult.add(new List("List 2"));

		// Set array list to List instance
		instance.setLists(expResult);

		// Test
		assertSame(expResult, instance.getLists());
	}

	/**
	 * Test of addList method, of class List.
	 */
	@Test
	public void testAddList()
	{
		System.out.println("addList");
		List list = new List("New List");
		List instance = new List("Main List");
		instance.addList(list);
	}

	/**
	 * Test of removeList method, of class List.
	 */
	@Test
	public void testRemoveList()
	{
		System.out.println("removeList");
		List list = new List("List to be removed");
		List instance = new List("Main List");

		// Add list to instance so that we may attempt to remove it
		instance.addList(list);

		// Test removing it
		instance.removeList(list);
	}

//	/**
//	 * Test of setItems method, of class List.
//	 */
//	@Test
//	public void testSetItems()
//	{
//		System.out.println("setItems");
//		ArrayList<Item> items = null;
//		List instance = null;
//		instance.setItems(items);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of getItems method, of class List.
//	 */
//	@Test
//	public void testGetItems()
//	{
//		System.out.println("getItems");
//		List instance = null;
//		ArrayList<Item> expResult = null;
//		ArrayList<Item> result = instance.getItems();
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of addItem method, of class List.
//	 */
//	@Test
//	public void testAddItem()
//	{
//		System.out.println("addItem");
//		Item item = null;
//		List instance = null;
//		instance.addItem(item);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of removeItem method, of class List.
//	 */
//	@Test
//	public void testRemoveItem()
//	{
//		System.out.println("removeItem");
//		Item item = null;
//		List instance = null;
//		instance.removeItem(item);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of setRootList method, of class List.
//	 */
//	@Test
//	public void testSetRootList()
//	{
//		System.out.println("setRootList");
//		List rootList = null;
//		List instance = null;
//		instance.setRootList(rootList);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of getRootList method, of class List.
//	 */
//	@Test
//	public void testGetRootList()
//	{
//		System.out.println("getRootList");
//		List instance = null;
//		List expResult = null;
//		List result = instance.getRootList();
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of setTab method, of class List.
//	 */
//	@Test
//	public void testSetTab()
//	{
//		System.out.println("setTab");
//		Tab tab = null;
//		List instance = null;
//		instance.setTab(tab);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of getTab method, of class List.
//	 */
//	@Test
//	public void testGetTab()
//	{
//		System.out.println("getTab");
//		List instance = null;
//		Tab expResult = null;
//		Tab result = instance.getTab();
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of setTabPane method, of class List.
//	 */
//	@Test
//	public void testSetTabPane()
//	{
//		System.out.println("setTabPane");
//		JFXTabPane tabPane = null;
//		List instance = null;
//		instance.setTabPane(tabPane);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of getTabPane method, of class List.
//	 */
//	@Test
//	public void testGetTabPane()
//	{
//		System.out.println("getTabPane");
//		List instance = null;
//		JFXTabPane expResult = null;
//		JFXTabPane result = instance.getTabPane();
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of setParentList method, of class List.
//	 */
//	@Test
//	public void testSetParentList()
//	{
//		System.out.println("setParentList");
//		List parentList = null;
//		List instance = null;
//		instance.setParentList(parentList);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of getParentList method, of class List.
//	 */
//	@Test
//	public void testGetParentList()
//	{
//		System.out.println("getParentList");
//		List instance = null;
//		List expResult = null;
//		List result = instance.getParentList();
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of refreshData method, of class List.
//	 */
//	@Test
//	public void testRefreshData()
//	{
//		System.out.println("refreshData");
//		List instance = null;
//		instance.refreshData();
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
}
