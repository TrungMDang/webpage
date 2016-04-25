
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Graph<V, E> implements GraphADT<V, E>
{
    protected final int DEFAULT_CAPACITY = 10;
    private int numVertices;     // number of vertices in the graph
    private Edge<E>[][] adjMatrix;   // adjacency matrix with weights
    private V[] vertices;        // values of vertices
    private List<ArrayList<V>> paths;

    /******************************************************************
     Creates an empty graph.
     ******************************************************************/
    public Graph(int row, int col)
    {
        numVertices = 0;
        adjMatrix = new Edge[row][col];
        vertices = (V[])(new Object[row]);
        paths = new ArrayList<ArrayList<V>>();
       
    }
    public List<ArrayList<V>> getPossiblePaths() {
    	return this.paths.subList(0, paths.size());
    }

    /******************************************************************
     Returns a string representation of the adjacency matrix. 
     ******************************************************************/
    public String toString()
    {
        if (numVertices == 0)
            return "Graph is empty";

        String result = new String("");

        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\n";

        for (int i = 0; i < numVertices; i++) 
        {
            result += "\t" + i;
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++)
            {
                if (adjMatrix[i][j] == null)
                    result += null + "\t";
                else
                    result += adjMatrix[i][j].getWeight() + "\t";
            }
            result += "\n";
        }

        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }
        result += "\n";
        return result;
    }
    
    
    /******************************************************************
    Adds a vertex to the graph, expanding the capacity of the graph
    if necessary.  It also associates an object with the vertex.
    ******************************************************************/
    @Override
    public void addVertex(V v) {
        if (numVertices == vertices.length)
            expandCapacity();
        if(!containsVertex(v)) {
            vertices[numVertices] = v;
            /*for (int i = 0; i <= numVertices; i++)
            {
                adjMatrix[numVertices][i] = null;
                adjMatrix[i][numVertices] = null;
            }       */   
            numVertices++;  
        }
    }
    
    /******************************************************************
    Inserts an edge between two vertices of the graph.
    ******************************************************************/
     
    @Override
    public void addEdge(V v1, V v2) {
        addEdge(v1, v2, 1);      
    }
    
    @Override
    public void addEdge(V v1, V v2, int weight) {
        int index1 = getIndex(v1);
        int index2 = getIndex(v2);
        if (index1 > -1 && index2 > -1) {
            adjMatrix[index1][index2] = new Edge(weight, index1, index2);
            //adjMatrix[index2][index1] = new Edge(weight, index2, index1);
        }
                  
    }

    
    /******************************************************************
    Returns the index value of the first occurrence of the vertex.
    Returns -1 if the key is not found.
    ******************************************************************/
    private int getIndex(V v) {
        for (int i = 0; i < vertices.length; i++)
            if (vertices[i].equals(v))
                return i;
        return -1;
    }
    
    protected void expandCapacity()
    {
        V[] largerVertices = (V[])(new Object[vertices.length*2]);
        Edge<E>[][] largerAdjMatrix = 
                new Edge[vertices.length*2][vertices.length*2];

        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < numVertices; j++)
            {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }
	@Override
    public void dFS(V start, V end) {
    	boolean visitedVertices[] = new boolean[numVertices + 1];
    	
    	//System.out.println(Arrays.toString(visitedVertices));
 
    	//List<ArrayList<V>> paths = new ArrayList<ArrayList<V>>();
    	ArrayList<V> path = new ArrayList<V>();
    	path.add(start);
    	visitedVertices[(int) start] = true;
    	//System.out.println("VisitedVertices: " + Arrays.toString(visitedVertices));
    	
    	
    	//Set<V> neighbors = neighbors(start);
    	//System.out.println("Neighbors of " + start + ": " + Arrays.deepToString(neighbors.toArray()));
    	dFSHelper(start, end, visitedVertices, path);
//    	Iterator<V> itr = neighbors.iterator();
//    	while (itr.hasNext()) {
//    		V v = itr.next();
//    		ArrayList<V> temp = new ArrayList<V>();
//    		temp.add(start);
//    		temp.add(v);
//    		//paths.add(temp);
//    		//if (visitedVertices[(int) v] == false) {
//    		//ArrayList<V> currentPath = new ArrayList<V>();
//    		//copy(paths.get(paths.size() - 1), currentPath);
//    		//paths.clear();
//    		//System.out.println("First path: " + Arrays.toString(paths.get(paths.size() - 1).toArray()));
//    			paths.add(dFSHelper(v, visitedVertices, temp));
//    		//}
//    	}
    	//System.out.println(Arrays.deepToString(this.paths.toArray()));
		//return paths; 	
    }
    
    private ArrayList<V> dFSHelper(V destination, V end, boolean[] visitedVertices, ArrayList<V> path) {
    	//System.out.println("------------------------");
    	
    	//System.out.println("At " + destination + " with path " + path.toString());
    	visitedVertices[(int) destination] = true;
    	//System.out.println("Visit vertex " + destination + " " + Arrays.toString(visitedVertices));
    	Set<V> neighbors = neighbors(destination);
    	//System.out.println("Neighbors of " + destination + ": " + Arrays.deepToString(neighbors.toArray()));
    	Iterator<V> itr = neighbors.iterator();
    	ArrayList<V> temp = null;
    	while (itr.hasNext()) {
    		V neighbor = itr.next();
    		temp = new ArrayList<V>();
    		temp = copy(path, temp);
    		temp.add(neighbor);
    		//System.out.println("Added " + neighbor + " to temp path: " + Arrays.deepToString(temp.toArray()));
    		if (neighbor == end) {
        		this.paths.add(temp);
        		//System.out.println("Added " + Arrays.deepToString(temp.toArray()) + " to paths");
        	}
    		//if (visitedVertices[(int) destination] == false) {
    			temp = dFSHelper(neighbor, end, visitedVertices, temp);
    		//}
    	}
    	return path;
    }
    
    private ArrayList<V> copy(ArrayList<V> from, ArrayList<V> to) {
    	int i = 0;
    	while (!from.isEmpty() && i < from.size()) {
    		to.add(from.get(i));
    		i++;
    	}   	
		return to;
    }
    
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearEdges() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsEdge(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsEdge(V v1, V v2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsPath(List<V> path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsVertex(V v) {
        return false;
    }

    @Override
    public int cost(List<V> path) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int degree(V v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public E edge(V v1, V v2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int edgeCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<E> edges() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int edgeWeight(V v1, V v2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int inDegree(V v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<V> inverseNeighbors(V v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDirected() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isReachable(V v1, V v2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isWeighted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<V> minimumWeightPath(V v1, V v2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<V> neighbors(V v) {
       Edge<E>[] row = adjMatrix[(int) v - 1];
       //System.out.println(Arrays.toString(row));
       Set<V> neighbors = new TreeSet<V>();
       for (int i = (int) v; i < row.length; i++) {
    	   //System.out.println("Row at index: " + i +" weight: " + row[i].weight);
    	   if (row[i].weight > 0) {
    		   neighbors.add(vertices[i]);
    	   }
       }
      // System.out.println(neighbors.toString());
        
        
        return neighbors;
        
    }

    @Override
    public int outDegree(V v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void removeEdge(E e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeEdge(V v1, V v2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeVertex(V v) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<V> shortestPath(V from, V to) {
        Queue<V> queue = new PriorityQueue<V>();
        int distance[] = new int[numVertices];
        int prev[] = new int[numVertices];
        distance[(int) from - 1] = 0;
        for (int i = 1; i < numVertices; i++) {
        	distance[i] = (int) Double.POSITIVE_INFINITY;
        }
        System.out.println(Arrays.toString(distance));
        
        for (int i = 0; i < numVertices; i++) {
        	
        }
        return null;
    }

    @Override
    public int vertexCount() {
        return numVertices;
    }

    @Override
    public Set<V> vertices() {
        
        return null;
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }
    
    private static class Edge<E> {
       // E   value;
        int weight;
        int v1;
        int v2;
              
        public Edge(int weight, int ver1, int ver2) {
            this.weight = weight;
            v1 = ver1;
            v2 = ver2;
        }
        
        public int getWeight() {
            return weight;
        }
        
    }
    
//    public static void main(String[] args) {
//        GraphADT<String, String> graph = new Graph<String, String>(10, 10);
//        System.out.println(graph);
//        
//        System.out.println("adding vertices (airports) ...");
//        graph.addVertex("DFW");
//        graph.addVertex("HNL");
//        graph.addVertex("LAX");
//        graph.addVertex("LGA");
//        graph.addVertex("MIA");
//        graph.addVertex("ORD");
//        graph.addVertex("PVD");
//        graph.addVertex("SFO");
//        System.out.println(graph);
//        
//        System.out.println("adding edges (flights) ...");
//        graph.addEdge("DFW", "LAX", 120);
//        graph.addEdge("DFW", "LGA", 140);
//        graph.addEdge("DFW", "MIA", 110);
//        graph.addEdge("DFW", "ORD", 80);
//        graph.addEdge("HNL", "LAX", 250);
//        graph.addEdge("HNL", "MIA", 500);
//        graph.addEdge("HNL", "SFO", 130);
//        graph.addEdge("LAX", "SFO", 60);
//        graph.addEdge("LAX", "ORD", 170);
//        graph.addEdge("LGA", "MIA", 100);
//        graph.addEdge("LGA", "PVD", 200);
//        graph.addEdge("ORD", "PVD", 50);
//        graph.addEdge("ORD", "SFO", 70);
//        System.out.println(graph);
//    }   
}