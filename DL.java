package csc406;
import java.util.*;

public abstract class DL extends Directed
{
	//Create the array of arrayLists of edges of size numVertices.
	ArrayList<Edge>[] ar = null;
	
	//Suppress any warnings that may come from initializing the array.
	@SuppressWarnings("unchecked")
	//Method used to initialize the size of the array and set all arrayList
	//edge elements
	void init() 
	{
		ar = new ArrayList[numVertices];
		for(int i =0; i <numVertices; i++)
		{
			ar[i] = new ArrayList<Edge>();
		}
	}
	
	//Determine if an edge exists once given source and destination
	//vertices. First, check the range of both possible vertices. If
	//they are not in the range of available vertices, return false.
	//If they are in the range, iterate through the arrayList at location
	// i in the array and determine if edge i->j exists. If it exists, return
	//true. else, return false.
	boolean existsEdge(int i, int j)
	{
		if(checkRange(i)&&checkRange(j))
		{
			if(ar[i].contains(new Edge(i, j)))
				return true;
		}
		return false;
	}
	
	//putEdge creates an edge in the graph. It accepts source and
	//destination vertices and a weight. If it is an unweighted graph, 
	//the weight is 1. First, check if the edge exists. If it does not,
	//add an edge to the arrayList at index i of the array where the edge
	//is i->j with weight w. Increment the count of numEdges. Increment the 
	//in degree in the inDeg array at j. Increment the out degree in the outDeg
	// array at i. If the edge already exists, do nothing.
	void putEdge(int i, int j, int w)
	{
		if(!existsEdge(i, j))
		{
			ar[i].add(new Edge(i, j, w));
			numEdges ++;
			inDeg[j]++;
			outDeg[i]++;
			pq.add(new Edge(i, j, w));
		}
	}
	
	//removeEdge removes an edge from the graph. It accepts source and
	//destination vertices. First, check if the edge exists. If it does,
	//iterate through the arrayList at index i of the array and compare each
	//next edge's source and destination vertices with the inputed i and j.
	//If the edge is found, remove it. Decrement the count of numEdges. 
	//Decrement the in degree in the inDeg array at j. Decrement the out 
	//degree in the outDeg array at i. If the edge does not exists, do nothing.
	void removeEdge(int i, int j)
	{
		if(existsEdge(i, j))
		{
			Iterator<Edge> arIt = ar[i].iterator();
			while (arIt.hasNext())
			{
				Edge e = arIt.next();
				if(e.getV1() == i && e.getV2() == j)
				{
					ar[i].remove(e);
					numEdges --;
					outDeg[i]--;
					inDeg[j]--;
					break;
				}
			}
		}
	}
	
	//This toString method is used to display the array of arrayList of
	//edges in an easy to read form. It prints the index of the array with
	// -> pointing to the list of edges, formated as (source, destination, weight).
	public String toString()
	{		
		String myList = "";
		for(int i=0; i<numVertices; i++)
		{
			myList += "\r\n"+i+"->";
			Iterator<Edge> arIt = ar[i].iterator();
			while(arIt.hasNext())
			{
					myList += arIt.next().toString() + " ";
			}
		}
		return myList;
	}
}
