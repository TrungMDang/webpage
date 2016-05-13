package gui;

import java.io.*;
import java.util.LinkedList;

/**
 * This class holds all information about the data in language library. This class' methods are
 * called to perform search, add, and view the actual data in library.
 * 
 * @author Scott Zhou, Stacie Mashnitskaya (editing), Trung Dang (comments, minor changes to constructor)
 * @version 3.0 December 11, 2015
 */
public class Library {
	
	/** A variable indicating an empty state for array.*/
	private static final int EMPTY = 1;
	
	/** The file that contains all data in the library.*/
	private String input_file;
	
	/** Used to read lines efficiently from a file.*/
	private BufferedReader br;
	
	/** A variable to hold the input for 1 line.*/
	private String line;
	
	/** The delimiter of the .csv file used to stored data of the library.*/
	private String split;
	
	/**
	 * Construct the library with default values.
	 * 
	 * @author Trung Dang (creation), Scott Zhou (file names, delimiter)
	 */
	public Library() {
		input_file = "./database.csv";
		br = null;
		line = "";
		split = ",";
	}
	/**
	 * List all the data in the library. The method reads from a .csv file stored 
	 * in the same directory as the current project/jar file.
	 * Each line is read and parsed as an array of string using ',' as delimiter.
	 * 
	 * @author	Scott Zhou
	 * 
	 * @return A list of all lines inside the .csv file. 
	 * @exception	FileNotFoundException	if the file is not found 
	 * @exception	IOException				if I/O error occurs during reading of a line
	 */
	public LinkedList<String[]> listAll() {
		
		try {
			LinkedList<String[]>  blech = new LinkedList<String[]>();
			br = new BufferedReader(new FileReader(input_file));
			while ((line = br.readLine()) != null) {
				String[] cell = line.split(split);
				blech.add(cell);
			}
			return blech;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Search for a record that matches the specified keyword inside the data of the library. 
	 * The method reads from a .csv file stored in the same directory  as the project/jar file. 
	 * The array of string returned has elements correspond to columns in data table.
	 * 
	 * @author Scott Zhou
	 * 
	 * @param keyword The keyword to be searched for.
	 * @return An array of string contains the keyword parameter 
     * @exception	FileNotFoundException		if the .csv file is not found
	 * @exception	IOException					if I/O error occurs during reading of a line
	 */
	public String[] searchKeyword(String keyword) {
		
		try {
			String goTo = input_file;
			br = new BufferedReader(new FileReader(goTo));
			while ((line = br.readLine()) != null) {
				String[] cell = line.split(split);
				//System.out.println("Cell read: " + Arrays.toString(cell) + " Length: " + cell.length);
				if (cell.length > EMPTY && cell[1].equals(keyword)) {
					//System.out.println(cell[0] + " " + cell[1] + " " + cell[2]+ " " + cell[3]);
					return cell;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Search for a record in the table that matches the specified title parameter.
	 * The method reads from a .csv file stored in the same directory  as the project/jar file. 
	 * The array of string returned has elements correspond to columns in data table.
	 * 
	 * @author Scott Zhou
	 * 
	 * @param title The title to be searched for.
	 * @return A array of string contains the title parameter.
	 * @exception	FileNotFoundException	if the file is not found
	 * @exception	IOException				if I/O error occurs during the reading of a line
	 */
	public String[] searchTitle(String title) {
		try {
			String goTo = input_file;
			br = new BufferedReader(new FileReader(goTo));
			while ((line = br.readLine()) != null) {
				String[] cell = line.split(split);
				if (cell.length > EMPTY && cell[0].equals(title)) {
					//System.out.println(cell[0] + " " + cell[1] + " " + cell[2]+ " " + cell[3]);
					return cell;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add the specified title, keyword, description, and content into the library. The method
	 * writes all parameters into a .csv file using ',' as delimiter between each parameter.
	 * 
	 * @author Scott Zhou
	 * 
	 * @param title The title to be inserted
	 * @param keyword The keyword to be inserted
	 * @param description The description to be inserted
	 * @param content The content to be inserted
	 * @precondition	Title and keyword must be unique
	 * 	
	 * @exception 	FileNotFoundException		if the file is not found
	 * @exception	IOException					if I/O error occurs during writing of a line
	 */
	public boolean add(String title, String keyword, String description, String content) {
		if (this.searchKeyword(keyword) == null && this.searchTitle(title) == null) {
			try {
				String goTo = input_file;
				BufferedWriter bw = new BufferedWriter(new FileWriter(goTo, true));
				bw.write(title + "," + keyword + "," + description + "," + content);
				bw.newLine();
				bw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} 
		return false;			
	}
} //end of class Library
