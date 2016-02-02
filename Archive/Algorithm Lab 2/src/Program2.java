/*
 * Name: Biraj Shrestha
 * EID: BS29898
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* Your solution goes in this file.
 *
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */

public class Program2 extends VertexNetwork {
    /* DO NOT FORGET to add a graph representation and 
       any other fields and/or methods that you think 
       will be useful. 
       DO NOT FORGET to modify the constructors when you 
       add new fields to the Program2 class. */

    
    Program2() {
        super();
    }
    
    Program2(String locationFile) {
        super(locationFile);
    }
    
    Program2(String locationFile, double transmissionRange) {
        super(locationFile, transmissionRange);
    }
    
    Program2(double transmissionRange, String locationFile) {
        super(transmissionRange, locationFile);
    }

    public ArrayList<Vertex> gpsrPath(int sourceIndex, int sinkIndex) {
        /* This method returns a path from a source at location sourceIndex 
           and a sink at location sinkIndex using the GPSR algorithm. An empty 
           path is returned if the GPSR algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements the GPSR algorithm. */
    	ArrayList<Vertex> pathToSink = new ArrayList<Vertex>(0);
    	Graph g = new Graph(location,edges,transmissionRange);
    	
    	Vertex source = g.getVertex(sourceIndex);
    	Vertex sink = g.getVertex(sinkIndex);
    	
    	Vertex nextHop = source;    	
    	while(nextHop!=null)
    	{
    		if (nextHop.equals(sink)) {break;}
	
    		pathToSink.add(nextHop);
    		ArrayList<Vertex> neighbor = g.neighborList(nextHop);
    		
    		nextHop = greedyFoward(nextHop, sink, neighbor);
    		if (nextHop == null) {return new ArrayList<Vertex>(0);}
    	}
    	
    	if (nextHop == null) {return new ArrayList<Vertex>(0);}
    	else {return pathToSink;}

    }
      
    public Vertex greedyFoward(Vertex source, Vertex sink, ArrayList<Vertex> neighbors ){
    	Vertex nextHop = null;
    	double maximumProgress = Double.POSITIVE_INFINITY;
    	double distance_SS = sink.distance(source);
    	double distance_VS;
    	
    	for(Vertex v : neighbors)
    	{
    		distance_VS = sink.distance(v);
    		if (distance_VS < distance_SS){
    			if(distance_VS < maximumProgress){
    				maximumProgress = distance_VS;
    				nextHop = v;
    			}
    		}
    	}
    	
    	if(maximumProgress != Double.POSITIVE_INFINITY) { return nextHop; }
       	else { return null; }
    }
    
    public ArrayList<Vertex> dijkstraPathLatency(int sourceIndex, int sinkIndex) {
        /* This method returns a path (shortest in terms of latency) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */
    	
    	int size = location.size();
    	double alt;
    	
    	ArrayList<Vertex> output = new ArrayList<Vertex>();
    	Graph g = new Graph(location,edges,transmissionRange);
    	
    	ArrayList <Vertex> neighbor;
    	Vertex source = g.getVertex(sourceIndex);
    	Vertex sink = g.getVertex(sinkIndex);
    	 	
    	g.initalizeGreedy(size);
    	g.setDist(sourceIndex, 0.0);
    	
    	List<Vertex> Q = new ArrayList<Vertex>(g.getVertexList());  	
    	Q.addAll(g.getVertexList());
    	
    	while(!Q.isEmpty()){
    		Vertex u = null;		    		
    		u = Collections.min(Q,g);

    		if(u==null) { break;}
    		if (g.getDistAt(u).isInfinite()) {return new ArrayList<Vertex>();}
    		Q.remove(u);
    		if (u.equals(sink)) {break;}
    		
    		neighbor = g.neighborList(u);
    		    		
    		for (Vertex v : neighbor)
    		{
    			if(Q.contains(v))
    			{	
	    			int indexOfV = g.getVertexIndex(v);
	    			
	    			alt = g.getDistAt(u) + g.getEdgeWeight(u,v);
	    			if (alt < g.getDist(indexOfV))
	    			{
	    				g.setDist(indexOfV, alt);
	    				g.setPrev(indexOfV, u);
	    			}
    			}
    		}
    	}
    	
    	int indexCurrent = sinkIndex;
    	if(g.getPrevious(sinkIndex) ==null) {return output;}
    	
    	output.add(g.getVertex(sinkIndex));
    	
    	while (g.getPrevious(indexCurrent) !=  source)
    	{
    		output.add(g.getPrevious(indexCurrent));
    		int indexInGraph = g.getVertexIndex(g.getPrevious(indexCurrent));
    		indexCurrent = indexInGraph;
    	}
    	
    	output.add(g.getPrevious(indexCurrent));
    	Collections.reverse(output);
   	
        return output;
    }
    

        
    public ArrayList<Vertex> dijkstraPathHops(int sourceIndex, int sinkIndex) {
        /* This method returns a path (shortest in terms of hops) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */
    	
    	int size = location.size();
    	double alt;
    	
    	ArrayList<Vertex> output = new ArrayList<Vertex>();
    	Graph g = new Graph(location,edges,transmissionRange);
    	
    	ArrayList <Vertex> neighbor;
    	Vertex source = g.getVertex(sourceIndex);
    	Vertex sink = g.getVertex(sinkIndex);
    	 	
    	g.initalizeGreedy(size);
    	g.setDist(sourceIndex, 0.0);
    	
    	List<Vertex> Q = new ArrayList<Vertex>(g.getVertexList());  	
    	Q.addAll(g.getVertexList());
    	
    	while(!Q.isEmpty()){
    		Vertex u = null;		    		
    		u = Collections.min(Q,g);

    		if(u==null) { break;}
    		if (g.getDistAt(u).isInfinite()) {return new ArrayList<Vertex>();}
    		Q.remove(u);
    		if (u.equals(sink)) {break;}
    		
    		neighbor = g.neighborList(u);
    		    		
    		for (Vertex v : neighbor)
    		{
    			if(Q.contains(v))
    			{	
	    			int indexOfV = g.getVertexIndex(v);
	    			
	    			alt = g.getDistAt(u) + 1.0;
	    			if (alt < g.getDist(indexOfV))
	    			{
	    				g.setDist(indexOfV, alt);
	    				g.setPrev(indexOfV, u);
	    			}
    			}
    		}
    	}
    	
    	int indexCurrent = sinkIndex;
    	if(g.getPrevious(sinkIndex) ==null) {return output;}
    	
    	output.add(g.getVertex(sinkIndex));
    	
    	while (g.getPrevious(indexCurrent) !=  source)
    	{
    		output.add(g.getPrevious(indexCurrent));
    		int indexInGraph = g.getVertexIndex(g.getPrevious(indexCurrent));
    		indexCurrent = indexInGraph;
    	}
    	
    	output.add(g.getPrevious(indexCurrent));
    	Collections.reverse(output);
   	
        return output;
    }
    
}

