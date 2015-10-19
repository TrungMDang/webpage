import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Dynamic {
	
	List<ArrayList<Integer>> data;
	GraphADT<Integer, Integer> myGraph;
	
	public Dynamic(GraphADT<Integer, Integer> graph, List<ArrayList<Integer>> data) {
		this.data = data;
		this.myGraph = graph;
	}
	
	public ArrayList<Integer> shortestPath(int start, int end) {
		System.out.println("Data in dynamic: " + Arrays.deepToString(data.toArray()));
		myGraph.shortestPath(start, end);
		
		
		return null;		
	}
	
}
