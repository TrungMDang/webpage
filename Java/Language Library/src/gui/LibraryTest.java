package gui;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Test methods of library.java: search and create
 * 
 * @author Siyuan Zhou, Trung Dang
 * @version 2.0 December 17, 2015
 *
 */
public class LibraryTest {

	/**
	 * Set up the library before every test.
	 */
	private Library lib;

	/**
	 * Set up the library before every test.
	 * <strong>Although new library objects are created each time before every test, they all 
	 * use a single csv file to store and search for data. This test class must run only once.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		lib = new Library();
	}

	/**
	 * Test searchKeyword method of Library
	 * 
	 * Author: Siyuan Zhou
	 */
	@Test
	public void testSearchKeyword() {
		String[] cell = { "Trung", "Banana", "Something about Trung", "Trung likes to eat bananas and pine candies" };
		String keyword = "Banana";
		System.out.println("----------------------------------------------------------------");
		System.out.println("Testing search for keyword...");
		System.out.println("Expected: " + Arrays.toString((cell)));
		String[] result = lib.searchKeyword(keyword);
		System.out.println("Actual: " + Arrays.toString(result));
		assertArrayEquals(cell, result);
		System.out.println("Passed");
	}

	/**
	 * Test searchTitle of Library
	 * 
	 * Author: Siyuan Zhou
	 */
	@Test
	public void testSearchTitle() {
		String[] cell = { "Trung", "Banana", "Something about Trung", "Trung likes to eat bananas and pine candies" };
		String title = "Trung";
		System.out.println("----------------------------------------------------------------");
		System.out.println("Testing search for title...");
		System.out.println("Expected: " + Arrays.toString((cell)));
		String[] result = lib.searchTitle(title);
		System.out.println("Actual: " + Arrays.toString(result));
		assertArrayEquals(cell, result);
		System.out.println("Passed");
	}

	/**
	 * Test create of library. The test added a new entry to the library and search for it
	 * immediately with the entry's keyword or title. 
	 * The search results should match the new entry.
	 * 
	 * Author: Trung Dang
	 */
	@Test
	public void testCreate() {
		String[] test = { "TCSS 360", "Quality", "About software", "Use effective prototyping techniques" };
		lib.add(test[0], test[1], test[2], test[3]);
		String[] keyResult = lib.searchKeyword(test[1]);
		if (keyResult != null) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("Testing create with keyword");
			assertArrayEquals(test, keyResult);
			System.out.println("Passed");
		}
		String[] titleResult = lib.searchTitle(test[0]);
		if (titleResult != null) {	
			System.out.println("----------------------------------------------------------------");
			System.out.println("Testing create with title");
			assertArrayEquals(test, titleResult);
			System.out.println("Passed");
		}
	}
	
	/**
	 * Test create of library with unique keyword. 
	 * New entry with same keyword that already exists in a clause should not be added.
	 * 
	 * Author: Trung Dang
	 */
	@Test
	public void testCreateWithSameKeyword() {
		String keyword = "Agile";
		String[] test = { "Method", keyword, "About software", "Use agile method"};
		lib.add(test[0], test[1], test[2], test[3]);		
		String[] test1 = {"SCRUM", keyword, "About software", "Use agile method"};
		lib.add(test1[0], test1[1], test1[2], test1[3]);
		System.out.println("----------------------------------------------------------------");
		System.out.println("Testing create with unique keyword...");
		System.out.println("Adding new data: " + Arrays.toString(test1));
		System.out.println("Expected: " + Arrays.toString(test));
		System.out.println("Actual: " + Arrays.toString(lib.searchKeyword(keyword)));
		assertArrayEquals(test, lib.searchKeyword(keyword));
		System.out.print("Passed. Data with same keyword was not added.\n");
	}

	/**
	 * Test create of library with same title. 
	 * New entry with same title that already exists in a clause should not be added.
	 * 
	 * Author: Trung Dang
	 */
	@Test
	public void testCreateWithSameTitle() {
		String title = "Techniques";
		String[] test = { title, "Effective", "About software", "Use agile method"};
		lib.add(test[0], test[1], test[2], test[3]);		
		String[] test1 = {title, "Diagram", "About software", "Use UML diagrams"};
		lib.add(test1[0], test1[1], test1[2], test1[3]);
		System.out.println("----------------------------------------------------------------");
		System.out.println("Testing create with unique title...");
		System.out.println("Adding new data: " + Arrays.toString(test1));
		System.out.println("Expected: " + Arrays.toString(test));
		System.out.println("Actual: " + Arrays.toString(lib.searchTitle(title)));
		assertArrayEquals(test, lib.searchTitle(title));
		System.out.print("Passed. Data with same title was not added.\n");
	}
}
