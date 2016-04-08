package csc406;

abstract class UM extends UnDirected
{
	//Create the matrix (array of arrays) for the graphs and 
	//initializes everything to 0.
	int[][] matrix = null;
	void init()
	{
		matrix = new int[numVertices][numVertices];
	}
	
	//Determine if an edge exists once given source and destination
	//vertices. First, check the range of both possible vertices. If
	//they are not in the range of available vertices, return false.
	//If they are in the range, check the matrix at location [i][j]and [j][i].
	//If the locations are ==0, return false. If the location is != 0
	//return true.
	boolean existsEdge(int i, int j)
	{
		if(checkRange(i)&&checkRange(j))
			return ((matrix[i][j] != 0)&&(matrix[j][i] != 0));
		return false;
	}
	
	//putEdge creates an edge in the graph. It accepts source and
	//destination vertices and a weight. If it is an unweighted graph, 
	//the weight is 1. First, check if the edge exists. If it does not,
	//add the weight of the edge to the array at location [i][j] and [j][i].
	//Increment the count of numEdges. Increment the degree in the deg array 
	//at j. Increment the degree in the deg array at i. If the edge already 
	//exists, do nothing.
	void putEdge(int i, int j, int w)
	{
		if(!existsEdge(i, j)&&!existsEdge(j, i))
		{
			matrix[i][j] = w;
			matrix[j][i] = w;
			numEdges ++;
			deg[j] ++;
			deg[i]++;
			pq.add(new Edge(i, j, w));
		}
	}
	
	//removeEdge removes an edge from the graph. It accepts source and
	//destination vertices. First, check if the edge exists. If it does,
	//set the matrix = 0 at the locations [i][j] and [j][i]. Decrement the 
	//count of numEdges. Decrement the degree in the deg array at j. Decrement the 
	//degree in the deg array at i. If the edge does not exists, do nothing.
	void removeEdge(int i, int j)
	{
		if(existsEdge(i, j))
		{
			matrix[i][j] = 0;
			matrix[j][i] = 0;
			numEdges --;
			deg[i]--;
			deg[j]--;
		}
	}
	
	//This toString method is used to display the matrix in  an easy to read form.
	//It prints the matrix by row, a 0 indication no edge and any other positive number
	//indicating an edge by its weight.
	public String toString()
	{
		String mat = "";
		
		for(int j=0; j<numVertices; j++)
		{
			mat += "\r\n";
			for(int i=0; i<numVertices; i++)
			{
				mat += matrix[j][i] + " ";
			}
		}
		return mat;
	}
}
