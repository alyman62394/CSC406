package csc406;

abstract class UnDirected extends Graph
{
	//Create the inDeg array and outDeg array protected in the 
	//Directed class so all classes extended from it can use them.
	int [] deg = null;
	
	//Method used to initialize the size of the degree array
	void initializeSize()
	{
		deg = new int[numVertices];
	}
	
	//Determine the degree of a vertex. If the node is in the range of
	//available vertices, return the degree from the deg array. If 
	//it is not in the available range, return -1.
	int degree(int i)
	{
		if(checkRange(i))
			return deg[i];
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
	
	//degPrint is an equivalent of toString. This displays the deg
	// array nicely.
	String degPrint()
	{
		String myString = "\r\nDegree Array:\r\n";
		for(int i=0; i<numVertices; i++)
		{
			myString +=i+":"+deg[i]+"  ";
		}
		
		return myString;
	}
	
	//These abstract methods are parts of a directed graph but cannot be 
	//defined until other classes because it must be known if it is 
	//a matrix or list representation, or weighted or unweighted.
	abstract boolean existsEdge(int i, int j);
	abstract void putEdge(int i, int j, int w);
	abstract void removeEdge(int i, int j);
	abstract void init();

}
