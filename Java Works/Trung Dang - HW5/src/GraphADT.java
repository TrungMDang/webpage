import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface GraphADT<V, E> {
    public void addEdge(V v1, V v2);
    //public void addEdge(V v1, V v2, E e);
    public void addEdge(V v1, V v2, int weight);
    //public void addEdge(V v1, V v2, E e, int weight);
    public void addVertex(V v);
    public void clear();
    public void clearEdges();
    public boolean containsEdge(E e);
    public boolean containsEdge(V v1, V v2);
    public boolean containsPath(List<V> path);
    public boolean containsVertex(V v);
    public int cost(List<V> path);
    public int degree(V v);
    public E edge(V v1, V v2);
    public int edgeCount();
    public Collection<E> edges();
    public int edgeWeight(V v1, V v2);
    public int inDegree(V v);
    public Set<V> inverseNeighbors(V v);
    public boolean isDirected();
    public boolean isEmpty();
    public boolean isReachable(V v1, V v2);         // depth-first search
    public boolean isWeighted();
    public List<V> minimumWeightPath(V v1, V v2);   // Dijkstra's algorithm
    public Set<V> neighbors(V v);
    public int outDegree(V v);
    public void removeEdge(E e);
    public void removeEdge(V v1, V v2);
    public void removeVertex(V v);
    public List<V> shortestPath(V v1, V v2);        // breadth-first search
    public String toString();
    public int vertexCount();
    public Set<V> vertices();
	//List<ArrayList<V>> dFS(V start, V end);
	public void dFS(V start, V end);
	public List<ArrayList<V>> getPossiblePaths();
}