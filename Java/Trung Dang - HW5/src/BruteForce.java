import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BruteForce {
	
	GraphADT<Integer, Integer> myGraph;
	List<ArrayList<Integer>> data;
	List<ArrayList<Integer>> allPaths;
	public BruteForce(GraphADT<Integer, Integer> graph, List<ArrayList<Integer>> data) {
		myGraph = graph;
		this.data = data;
		System.out.println("Data in brute force: " + Arrays.deepToString(this.data.toArray()));
		allPaths = new ArrayList<ArrayList<Integer>>();
	}
	
	public ArrayList<ArrayList<Integer>> generateAllPaths(int start, int end) {
		myGraph.dFS(start, end);
		allPaths = myGraph.getPossiblePaths();
		System.out.println("All paths: " + Arrays.deepToString(allPaths.toArray()));
		
		return null;
	}

	public void shortestPath(PrintStream out) {
		out.println("Brute Force-----------------------------------------------");
		int[] possibleResults = new int[allPaths.size()];
		int weight = 0;
		for (int i = 0; i < allPaths.size(); i++) {
			ArrayList<Integer> temp = allPaths.get(i);
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
		System.out.println(Arrays.toString(possibleResults));
		findMinSequence(possibleResults, out);
		
	}
	
	private void findMinSequence(int[] possibleResults, PrintStream out) {
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
			out.println("\tCheapest sequence: " + Arrays.deepToString(allPaths.get(index).toArray()));			
		}
		out.println("\tCost: " + min);
	}
//	private ArrayList<ArrayList<Integer>> generateAllPaths() {
//		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
//		ArrayList<Integer> path = new ArrayList<Integer>();
//		for (int i = 0; i < data.length; i++) {
//			path.add(1);
//			for (int j = 1; j < data[0].length; j++) {
//				if (data[i][j] > 0) {
//					path.add(j+1);
//				}
//			}
//		}
//		
//		
//		return null;	
//	}
}
