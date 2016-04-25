import java.io.*;
import java.sql.Time;
import java.time.Instant;
import java.util.*;

public class tcss343 {

	public static void main(String...args) {
		List<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		GraphADT<Integer, Integer> myGraph;
		//System.out.println(args.toString());
		try {
			File input = new File("input.txt");
			File output = new File("output.txt");
			Scanner fileInput = new Scanner(input);
			PrintStream out = new PrintStream(output);


			loadData(fileInput, data);
			System.out.println("Data loaded: " + Arrays.deepToString(data.toArray()));
			
			myGraph = new Graph<Integer, Integer>(data.size(), data.get(0).size());	
			loadGraph(myGraph, data);
			//System.out.println(myGraph);

			BruteForce bf = new BruteForce(myGraph, data);
			long start = System.currentTimeMillis();
			//bf.generateAllPaths(1, data.size());
			//bf.shortestPath(out);
			//System.out.println("Cost: " + bf_Result);
			long end = System.currentTimeMillis();
			out.println("\tBrute force time: " + (end - start) + "ms");
			out.println("End Brute Force-------------------------------------------");
			
			//DivideConquer dc = new DivideConquer(data);
			
			
			
			Dynamic d = new Dynamic(myGraph, data);
			d.shortestPath(1, data.size());


		} catch (FileNotFoundException e) {
			System.err.println("Error! Cannot read file or the file does not exist.");
		}							
	}
	
	private static void loadGraph(GraphADT<Integer, Integer> graph, List<ArrayList<Integer>> data) {
		
		for (int row = 0; row < data.size(); row++) {
			graph.addVertex(row + 1);
		}
		for (int row = 0; row < data.size(); row++) {
			for (int col = 0; col < data.get(0).size(); col++) {
				int weight = data.get(row).get(col);
				if (weight >= 0) {
					//System.out.println("Adding edge at: " + (row) + " " + col + " with weight: " + weight);
					graph.addEdge(row + 1, col + 1, weight);										
				}
				
			}
		}
		
	}

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
