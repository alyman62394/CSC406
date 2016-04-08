package csc406;

import java.util.*;

public class TarjansTopo
{
	int numVert;
	int [] visited;
	Stack<Integer> s;
	Stack<Integer> temp;
	Directed g;
	boolean cycle;
	int [] tarjans(Directed graph)
	{
		g = graph;
		numVert = graph.numVertices();
		visited = new int[numVert];
		s = new Stack<Integer>();
		temp = new Stack<Integer>();
		cycle = false;
		
		//initialize topo sort array
		int [] out = new int [numVert];
		//find a node to start with. look for a node with an
		//in degree of zero
		for(int i=0; i<numVert; i++)
		{
			if(g.inDeg[i]==0)
			{
				//call dfs on node with indegree of zero
				dfs(i);
				//break out of for look
				break;
			}
			//if no node has indegree of 0, it is a cycle so end method
			else
			{
				cycle=true;
				return out;
			}
		}

		for(int j=0; j<numVert; j++)
		{
			if(visited[j] == 0)
				dfs(j);
		}
		
		int i = 0;
		while(!temp.isEmpty())
		{
			out[i] = temp.pop();
			i++;
		}
		return out;
	}
	
	 void dfs(int i)
	{
		int j;
		visited[i] = 1;  // Mark node as "visited"

		s.push(i);
		
		for ( j = 0; j < numVert; j++ )
		{
			//check all adjacent nodes of i
			if (g.adjacentVertices(i).contains(j))
			{
				//if j i unvisited, 
				if(visited[j] ==0)       
					dfs(j);       // Visit node
				//if node j has previously been visited, it is a cycle
				else if(visited[j] == 1)
					cycle = true;
			}
		}
		//push the top of stack s to temp to flip to the right direction
		//and so it is in the proper topo order
		temp.push(s.pop());
		//mark node as "pushed to stack"
		visited[i] = 2;
	}
	 
	 //toString method to print topo sort array
	 public String toString(int[] ar)
	 {
		 String ans ="";
		 if(cycle == true)
			 ans = "The graph is cyclic";
		else
			for(int i=0; i<numVert; i++)
				ans += "\t" + ar[i];
		 
		 ans +="\r\n";
		 return ans;
	 }
}
