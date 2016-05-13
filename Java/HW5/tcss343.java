/*
 * TCSS 343 - Design and Analysis of Algorithm
 * 
 */

import java.io.*;
import java.util.*;

/**
 * @author Trung Dang
 * Date:	June 2, 2015
 * 
 * This class attempts to find the shortest path between two vertices using three different approach:
 * brute force, divide & conquer, and dynamic.
 * 
 */
public class tcss343 {
	
	/** Indicate there is no path.*/
	private static final int NULL = -1;
	
	/** The shortest path to reach from source vertex to destination vertex.*/
	private static List<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

	
	/**
	 * Main method of the program.
	 * 
	 * @param args args[0] is the name of the input file.
	 */
	public static void main(String[] args) {
		
		/** Run this once to generate all the input files.*/
		//generateInputs();						
		
		List<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		
		File input = null;
		Scanner fileInput = null;
		try {
			if (args.length > 0) {
				input = new File(args[0]);
			} else {
				input = new File("input.txt");
			}
			fileInput = new Scanner(input);
			File output = new File("output.txt");
			PrintStream out = new PrintStream(output);

			loadData(fileInput, data);
			ArrayList<Integer> path = new ArrayList<Integer>();			
			bruteForce(data, out, path);
			divideConquer(data, out, path);
			dynamic(data, out);
		} catch (FileNotFoundException e) {
			System.err.println("Error! Cannot read file or the file does not exist.");
		}							
	}
	
	/**
	 * Run once to generate all the input files.
	 */
	private static void generateInputs() {
		String[] inputFiles = {"input100.txt", "input200.txt", "input400.txt", "input600.txt", "input800.txt"};
		int[] inputSizes = {100, 200, 400, 600, 800};
		for (int i = 0; i < inputFiles.length; i++) {
			File input = new File(inputFiles[i]);
			PrintStream inputOut = null;
			try {
				inputOut = new PrintStream(input);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			generateRandomInput(inputOut, inputSizes[i]);
		}
	}

	/**
	 * Generate a table of random values. 
	 * In each row of the table, the values are generated in increasing value.
	 * 
	 * @param inputOut The input file that need to be generated
	 * @param size The size of the table that need to be generated
	 */
	private static void generateRandomInput(PrintStream inputOut, int size) {
		Random rand = new Random();
		if (inputOut != null) {
			for (int i = 0; i < size; i++) {
				for (int skip = 0; skip < i; skip++) {
					inputOut.print("NA");
					inputOut.print("\t");
				}
				int prev = 0;
				for (int j = i; j < size; j++) {				
					if (i == j) {
						inputOut.print(0);
						inputOut.print("\t");
					} else {
						int next = rand.nextInt(5) + prev;
						while (next == 0) {
							next = rand.nextInt(5) + prev;
						}
						prev = next;
						inputOut.print(next);
						inputOut.print("\t");
					}										
				}
				inputOut.println();
			}
		}	
	}
	
	/**
	 * Attempt to find the shortest path using divide and conquer approach.
	 * 
	 * @param data The adjacency matrix with weights
	 * @param out The output file
	 * @param path To store the path
	 */
	private static void divideConquer(List<ArrayList<Integer>> data,
			PrintStream out, ArrayList<Integer> path) {
		long start = System.currentTimeMillis();
		out.println("Divide and Conquer---------------------------------");
		out.print("\tDivide conquer cheapest cost: ");
		out.print(divideConquer(data.size(), data, out, path) + "\n");
		out.print("\tSequence: ");
		for (int i = 0; i < path.size(); i++) {
			out.print(path.get(i));
			out.print(" ");
		}
		out.print(data.size());
		out.println();
		long end = System.currentTimeMillis();
		out.println("\tTotal time: "  + (end - start) + "ms");
		out.println("End Divide and Conquer------------------------------");
		
	}
	/**
	 * Helper method for divide and conquer.
	 * 
	 * @param start The destination vertex
	 * @param data The adjacency matrix with weights
	 * @param out The output file
	 * @param path To store the path
	 * @return integer The minimum cost to reach destination starting from vertex 1
	 */
	private static int divideConquer(int start, List<ArrayList<Integer>> data, PrintStream out, ArrayList<Integer> path) {
		int[] minimum = new int[1];
		int min = Integer.MAX_VALUE;
		if (start == 1) {
			return 0;
		} else {
			ArrayList<Integer> neighbors = neighborsDivideConquer(start, data);
			//System.out.println("Neighbors of " + start +": " + Arrays.deepToString(neighbors.toArray()));
			//Queue<Integer> queue = new PriorityQueue<Integer>();
			
			for (int i = 0; i < neighbors.size(); i++) {
				
				//System.out.println("----------------------------------");
				int v = neighbors.get(i);
				//System.out.println("At neighbor " + v + " of " + start);
				//System.out.println("New weight: "+ data.get(neighbors.get(i) - 1).get(start - 1));
				int current = divideConquer(neighbors.get(i), data, out, path);
				//System.out.println("Current value of " + v + ": " + current);		
				int newMin = current + data.get(neighbors.get(i) - 1).get(start - 1);
				if (min > newMin) {
					min = newMin;
					minimum[0] = v;
				}			
			}
			if (!path.contains(minimum[0]))
				path.add(minimum[0]);
		}
		return min;
	}

	/**
	 * Find the neighbor of current vertex backwards. 
	 * If there is a directed edge from v to start, v is a neighbor of start.
	 * 
	 * @param start The current vertex
	 * @param data The adjacency matrix with weights
	 * @return ArrayList<Integer> All the vertices adjacent to start
	 */
	private static ArrayList<Integer> neighborsDivideConquer(int start,
			List<ArrayList<Integer>> data) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < start - 1; i++) {
			if (data.get(i).get(start - 1) > 0) {
				neighbors.add(i + 1);
			}
		}
		return neighbors;
	}

	/**
	 * Attempt to find the shortest path using dynamic programming, particularly using Dijsktra's Algorithm.
	 * 
	 * @param data The adjacency matrix with weights
	 * @param out The output file
	 */
	private static void dynamic(List<ArrayList<Integer>> data, PrintStream out) {
		long startTime = System.currentTimeMillis();
		out.println("Dynamic-----------------------------------------------");
		//System.out.println(data.size());
		shortestPath(data, 1, data.size(), out);
		long endTime = System.currentTimeMillis();
		out.println("\tDynamic time: " + (endTime - startTime) + "ms");
		out.println("End Dynamic-------------------------------------------");
	}
	
	/**
	 * Attempt to find the shortest path using dynamic programming, particularly using Dijsktra's Algorithm.
	 * 
	 * @param data The adjacency matrix with weights
	 * @param start The starting vertex
	 * @param end The ending vertex
	 * @param out The output file
	 */
	private static void shortestPath(List<ArrayList<Integer>> data,
			int start, int end, PrintStream out) {
		int[] distance = new int[data.size() + 1];
		distance[0] = -1;
		distance[start] = 0;
		int[] path = new int[data.size()];
		ArrayList<Integer> unvisited = new ArrayList<Integer>();
		for (int i = 3; i < distance.length + 1; i++) {
			distance[i - 1] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < data.size(); i++) {
			unvisited.add(i+1);
		}
		while (!unvisited.isEmpty()) {
			int min = minVertex(distance, unvisited, unvisited.get(0));
			ArrayList<Integer> neighbors = neighbors(min, data);
			for (int i = 0; i < neighbors.size(); i++) {

				int u = neighbors.get(i);
				int newDistance = distance[min] + data.get(min - 1).get(u - 1);
				if (newDistance < distance[u]) {
					distance[u] = newDistance;
					path[u - 1] = min;
				}
			}
			unvisited.remove((Integer) min);
		}
		
		Stack<Integer> result = backtracking(start, end, path, data, out);
		out.print("\tCheapest sequence: ");
		while (!result.isEmpty()) {
			out.print(result.pop() + " ");
		}
		out.println();
		out.println("\tCost: " + distance[end]);
		
	}

	/**
	 * Method to backtrack to find the shortest path for dynamic approach.
	 * 
	 * @param start The start vertex
	 * @param end The ending vertex
	 * @param path To store the shortest path
	 * @param data The adjacency matrix with weights
	 * @param out the output file
	 * @return
	 */
	private static Stack<Integer> backtracking(int start, int end, int[] path,
			List<ArrayList<Integer>> data, PrintStream out) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(end);
		int u = end;
		while (path[u - 1] != NULL) {
			if (!s.contains(path[u - 1]))
				s.push(path[u - 1]);
			if (path[u - 1] == start) break;
			
			u = u - 1;
		}
		return s;
	}

	/**
	 * Find the minimum vertex that is adjacent to start.
	 * 
	 * @param distance Array of distances of all the vertices
	 * @param unvisited Array of vertex not yet to be checked
	 * @param start Starting vertex the need to be checked
	 * @return
	 */
	private static int minVertex(int[] distance, ArrayList<Integer> unvisited, int start) {
		int min = start;
		int minDistance = distance[start];
		for (int i = start + 1; i < distance.length; i++) {
			if (distance[i] < minDistance) {
				minDistance = distance[i];
				min = i;
			}
		}
		return min;
	}

	/**
	 * Attempt to find the shortest path using brute force approach.
	 * 
	 * @param data The adjacency matrix with weights
	 * @param out Output file
	 * @param path Current shortest path
	 */
	private static void bruteForce(List<ArrayList<Integer>> data, PrintStream out, ArrayList<Integer> path) {
		long startTime = System.currentTimeMillis();
		out.println("Brute Force-----------------------------------------------");
		generateAllPaths(data, 1, data.size(), path);
		//System.out.println("All paths: " + Arrays.deepToString(paths.toArray()));
		int[] possibleResults = new int[paths.size()];
		int weight = 0;
		for (int i = 0; i < paths.size(); i++) {
			//System.out.println("stuck at path" +i + path.size());
			ArrayList<Integer> temp = paths.get(i);
			int startIndex = 0;
			int endIndex = 1;
			weight = 0;
			while (endIndex < temp.size()) {
				int start = temp.get(startIndex);
				int end = temp.get(endIndex);
				//System.out.println("Indexes: " + start + " " + end + " - Weight: " + data.get(start - 1).get(end - 1));
				weight += data.get(start - 1).get(end - 1);
				startIndex++;
				endIndex++;
			}
			possibleResults[i] = weight;
		}	
		//System.out.println(Arrays.toString(possibleResults));
		findMinSequence(possibleResults, out);
		long endTime = System.currentTimeMillis();
		out.println("\tBrute force time: " + (endTime - startTime) + "ms");
		out.println("End Brute Force-------------------------------------------");
	}
			
	/**
	 * Find the minimum sequence out of all the possible sequence obtained from brute force approach.
	 * 
	 * @param possibleResults All the possible paths generated by brute force.
	 * @param out Output file.
	 */
	private static void findMinSequence(int[] possibleResults, PrintStream out) {
		int min = possibleResults[0];
		int i = 1, index = 0;
		for (i = 1; i < possibleResults.length; i++) {
			int temp = possibleResults[i];
			if (temp < min) {
				min = temp;
				index = i;
			}
		}
		if (index != 0) {
			out.println("\tCheapest sequence: " + Arrays.deepToString(paths.get(index).toArray()));			
		}
		out.println("\tCost: " + min);
	}
	
	/**
	 * Generate all possible paths starting from vertex start and ending at end.
	 * 
	 * @param data The adjacency matrix with weights
	 * @param start Starting vertex
	 * @param end Ending vertex
	 * @param path Current shortest path
	 */
	private static void generateAllPaths(List<ArrayList<Integer>> data, int start, int end, ArrayList<Integer> path) {
		path.add(start);
		recursiveGeneratePaths(data, start, end, path);
	}

	/**
	 * Recursive helper to generate all possible paths for brute force approach.
	 * 
	 * @param start Starting vertex
	 * @param end Ending vertex
	 * @param path Current shortest path
	 */
	private static void recursiveGeneratePaths(List<ArrayList<Integer>> data, int start, int end, ArrayList<Integer> path) {
		//System.out.println("Generating...");
		ArrayList<Integer> neighbors = neighbors(start, data);
		//System.out.println("Neighbors of " + start + Arrays.deepToString(neighbors.toArray()));
		for (int i = 0; i < neighbors.size(); i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp = copy(path, temp);
			temp.add(neighbors.get(i));
			//System.out.println("Temp: " + Arrays.deepToString(temp.toArray()));
			if (neighbors.get(i) == end) {
				paths.add(temp);
			}
			recursiveGeneratePaths(data, neighbors.get(i), end, temp);
		}

	}

	/**
	 * Find the adjacent vertices of start vertex.
	 * 
	 * @param start Starting vertex
	 * @param data The adjacency matrix with weights
	 * @return ArrayList<Integer> all the vertices adjacent to the start vertex
	 */
	private static ArrayList<Integer> neighbors(int start, List<ArrayList<Integer>> data){
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i = start; i < data.get(start - 1).size(); i++) {
			int weight = data.get(start - 1).get(i);
			if (weight > 0) {
				neighbors.add(i + 1);
			}
		}
		return neighbors;
	}

	/**
	 * Utility to copy two arraylists.
	 * 
	 * @param from 
	 * @param to
	 * @return The arraylist that was copied to.
	 */
	private static ArrayList<Integer> copy(ArrayList<Integer> from, ArrayList<Integer> to) {
		int i = 0;
		while (!from.isEmpty() && i < from.size()) {
			to.add(from.get(i));
			i++;
		}   	
		return to;
	}
	
	/**
	 * Load the data from the input file and store in an adjacency matrix with weights.
	 * 
	 * @param fileInput input file
	 * @param data The adjacency matrix with weights
	 */
	private static void loadData(Scanner fileInput, List<ArrayList<Integer>> data) {

		int row = 0, col = 0;
		while (fileInput.hasNextLine()) {
			ArrayList<Integer> lineData = new ArrayList<Integer>();
			String nextLine = fileInput.nextLine();
			Scanner lineInput = new Scanner(nextLine);
			//System.out.println(nextLine);
			for (int skip = 0; skip < row; skip++) {
				lineInput.next();
				lineData.add(-1);
			}


			while (lineInput.hasNext()) {
				int num = Integer.parseInt(lineInput.next());
				if (num >= 0){
					lineData.add(num);
				}
			}
			data.add(lineData);
			lineInput.close();
			row++;
		}			

		System.out.println();		
	}
}
