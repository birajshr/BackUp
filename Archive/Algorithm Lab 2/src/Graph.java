/*
 * Name: Biraj Shrestha
 * EID: BS29898
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;


public class Graph implements Comparator<Vertex>{
	
	protected Vector<Vertex> vertex;
    protected Vector<Edge> edges;
    protected double transmissionRange;
    Double[] dist;
	Vertex[] previous;
	

	public Graph()
	{
		
	}
	
	public Graph(ArrayList<Vertex> v,ArrayList<Edge> e,Double transmission)
	{
		vertex = new Vector<Vertex>(v);
		edges = new Vector<Edge>(e);
		transmissionRange = transmission;
	}
	
	public Vertex getVertex(int i)
	{
		return vertex.get(i);
	}
	
    public ArrayList<Vertex> neighborList(Vertex source){
    	ArrayList<Vertex> neighborlist = new ArrayList<Vertex>();
    	
    	for(Vertex v : vertex)
    	{
    		if(source.distance(v) <= transmissionRange && source.distance(v) !=0) { neighborlist.add(v); }
    	}
    	return neighborlist;
    }
    
    public Vector<Vertex> getVertexList()
    {
    	return vertex;
    }
    
    public int getVertexIndex(Vertex v)
    {
    	return vertex.indexOf(v);
    }
    
    public double getEdgeWeight(Vertex u, Vertex v)
    {
    	int indexU = this.getVertexIndex(u);
    	int indexV = this.getVertexIndex(v);
    	
    	double weight = 0;
    	
    	if(u.distance(v) > this.transmissionRange) { return Double.POSITIVE_INFINITY;}
    	
    	for(Edge e : this.edges)
    	{
    		if(e.getU() == indexU && e.getV() == indexV)
    		{
    			weight = e.getW();
    			break;
    		}
    		
    		if(e.getV() == indexU && e.getU() == indexV)
    		{
    			weight = e.getW();
    			break;
    		}	
    	}
    	return weight;
    }

	@Override
	public int compare(Vertex o1, Vertex o2) {
		// TODO Auto-generated method stub
		if(vertex.contains(o1) && vertex.contains(o1))
		 return getDistAt(o1).compareTo(getDistAt(o2));
		else return 0;
	}
	
	public void initalizeGreedy(int size)
	{
		dist = new Double[size];
		previous  = new Vertex[size];
    	for (Vertex v : vertex)
    	{
    		int index = vertex.indexOf(v);
    		dist[index] = Double.POSITIVE_INFINITY;
    		previous[index] = null;
    		
    	}	
	}
	
	public void setDist(int index, double value)
	{
		dist[index] = value;
	}
	
	public void setPrev(int index, Vertex v)
	{
		previous[index] = v;
	}
	
	public Double getDist(int index){
		return dist[index];
	}
	
	public Double getDistAt(Vertex v){
		
		return dist[vertex.indexOf(v)];
	}
	
	public Vertex getPrevious(int index)
	{
		return previous[index];
	}

}
