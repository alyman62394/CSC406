package csc406;

import java.util.*;

public abstract class Directed extends Graph
{
	//Create the inDeg array and outDeg array protected in the 
	//Directed class so all classes extended from it can use them.
	int [] inDeg = null;
	int [] outDeg = null;
	
	//Constructor that is called with vertices to set the number of 
	//vertices. super is called with the number of vertices to call
	//graph's constructor.
	void initializeSize()
	{
		inDeg = new int[numVertices];
		outDeg = new int[numVertices];
	}

	//Determine the inDegree of a vertex. If the node is in the range of
	//available vertices, return the degree from the inDegree array. If 
	//it is not in the available range, return -1.
	int inDegree(int i)
	{
		if(checkRange(i))
			return inDeg[i];
		return -1;
	}
	
	//Determine the outDegree of a vertex. If the node is in the range of
	//available vertices, return the degree from the outDegree array. If 
	//it is not in the available range, return -1.
	int outDegree(int i)
	{
		if(checkRange(i))
			return outDeg[i];
		return -1;
	}
	
	//checkDegree is an equivalent of a toString. If the in or out degree
	//of a vertex is < 0, print an error message. Otherwise, return the 
	//degree of the vertex as a string.
	String checkDegree(int i)
	{
		if(i < 0)
			return "The vertice is not a node.";
		return ""+i;
	}
	
	int [] kahns()
	{
		int [] degArr = new int[numVertices];
		int[] out = new int [numVertices];
		degArr = inDeg.clone();
		Stack<Integer> s = new Stack<Integer>();
		for(int i=0; i<numVertices; i++)
			if(degArr[i] ==0){ s.push(i); degArr[i] = -1;}
		
		int y=0;
		while(!s.isEmpty())
		{
			int u = (Integer) s.pop();
			out[y] = u;
			//out += "\t"+u;
			for(int i=0; i<numVertices; i++)
			{
				if(existsEdge(u, i))
				{
					degArr[i]--;
					if(degArr[i] == 0)
						s.push(i);
				}
			}
			y++;
		}
		if(y>= numVertices)
			return out;
		return null;
	}
	
	//inOutPrint is an equivalent of toString. This displays the inDegree
	//and outDegree arrays nicely.
	String inOutPrint()
	{
		String myString = "\r\nIn Degree Array:\r\n";
		for(int i=0; i<numVertices; i++)
			myString +=i+":"+inDeg[i]+"  ";
		
		myString += "\r\nOut Degree Array:\r\n";
		for(int i=0; i<numVertices; i++)
			myString +=i+":"+outDeg[i]+"  ";
		return myString;
	}
	
	String printTopoArr(int [] arr)
	{
		if(arr == null)
			return "The graph is cyclic";
		String out ="";
		for(int i=0; i<arr.length; i++)
		{
			out+= "\t"+arr[i];
		}
		return out+= "\r\n";
	}
	//These abstract methods are parts of a directed graph but cannot be 
	//defined until other classes because it must be known if it is 
	//a matrix or list representation, or weighted or unweighted.
	abstract boolean existsEdge(int i, int j);
	abstract void putEdge(int i, int j, int w);
	abstract void removeEdge(int i, int j);
	abstract void init();
}
