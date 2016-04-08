package csc406;
import java.util.*;
import java.io.*;

public abstract class Graph
{
	//Create the numVertices and numEdges protected in the root 
	//class so all classes extended from it can use them.
	int numVertices;
	int numEdges;
	PriorityQueue<Edge> pq;
	
	//Function that returns the number of vertices
	int numVertices()
	{
		return numVertices;
	}
	//Function that returns the number of edges
	int numEdges()
	{
		return numEdges;
	}
	
	//existsEdge function takes an edge as a parameter. This
	//function calls the existsEdge function that takes two
	//vertices as parameters and uses e.getV1 and e.getV1 to get
	//the vertex elements of the edge object
	boolean existsEdge(Edge e)
	{
		return existsEdge(e.getV1(), e.getV2());
	}
	
	//putEdge function takes an edge as a parameter. This
	//function calls the putEdge function that takes two
	//vertices as parameters and uses e.getV1 and e.getV1 to get
	//the vertex elements of the edge object
	void putEdge(Edge e)
	{
		putEdge(e.getV1(), e.getV2(), e.getW());
	}
	
	//removeEdge function takes an edge as a parameter. This
	//function calls the removeEdge function that takes two
	//vertices as parameters and uses e.getV1 and e.getV1 to get
	//the vertex elements of the edge object
	void removeEdge(Edge e)
	{
		removeEdge(e.getV1(), e.getV2());
	}
	
	//If an edge exists from i to j, j is said to be adjacent to i.
	//So, if existsEdge returns true, the vertices are adjacent
	//Likewise, if existsEdge returns false, the vertices are not adjacent
	boolean areAdjacent(int i, int j)
	{
		return existsEdge(i, j);
	}
	
	//This function returns an arrayList of integers that represent the
	//vertices that are adjacent. First, check that the vertex passed in 
	//is in the available range of vertices. If it is not, add -1 to the 
	//arrayList. If it is, check if there exists an edge from i to all other
	//vertices in the graph. Where existsEdge returns true, add that vertex
	//the the arrayList.
	ArrayList<Integer> adjacentVertices(int i)
	{
		ArrayList<Integer> adjVert = new ArrayList<Integer>();
		
		if(!checkRange(i))
			adjVert = null;
		else
		{
			for (int x=0; x<numVertices; x++)
				if(existsEdge(i, x))
					adjVert.add(x);
		}
		return adjVert;
	}
	
	//checkRange checks that the integer passed in is not larger
	//that the available vertices and is not less than 0.
	boolean checkRange(int i)
	{
		if(i >= numVertices || i< 0)
			return false;
		return true;
	}
	
	//printList is equivalent to a toString method that returns a
	//string of the arrayList that is passed in if the arrayList 
	//contains 0 elements or if the first element is >= 0. If the 
	//first element is -1, it prints an error message stating the 
	//specified vertex does not exist in this graph.
	String toString(ArrayList<Integer> al)
	{
		if(al == null)
			return "The vertice does not exist in this graph.";
		return al.toString();
	}
	
	//Asks for file, reads file, and creates the graph
	void createGraph()
	throws IOException
	{
		try{
			String currentDir = new File(".").getAbsolutePath();
			currentDir = currentDir.substring(0, currentDir.length()-1);
			String fileType="";
			String weighted = "weighted";
			String unweighted = "unweighted";
			if(this instanceof UMWG || this instanceof ULWG || this instanceof DMWG || this instanceof DLWG)
				fileType =weighted;
			
			if(this instanceof UMUG || this instanceof ULUG || this instanceof DMUG || this instanceof DLUG)
				fileType =unweighted;
			
			String file = currentDir + "src\\csc406\\"+getFileName(fileType);
			
			//create the FileReader and BufferedReader to read the file line by line
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//'s' is to be used by the BufferedReader to store each line as a string.
			//'sArr' is a string array that will hold the values at each line after
			//being split on a space.
			String s = "";
			String sArr[] = new String[3];
			while((s = br.readLine()) != null)
			{
				//Create the string array of the line
				sArr = s.split(" ");
				
				//If there is one element on the line, set numNodes equal to the integer value
				if(sArr.length == 1)
				{
					numVertices = Integer.parseInt(sArr[0]);
					init();
					initializeSize();
					//Initialize the priority queue to use for kruskals algorithm
					pqInit();
				}
				//Check if there is two elements on the line.
				else if(sArr.length == 2)
				{
					//Put the first edge in the graphs. Use the first element parsed to an integer
					//as the source vertex and the second element parsed to an integer as the 
					//destination vertex.
					putEdge(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]), 1);
					
					//Put the rest of the elements into the graph. Since the first row is of length 2,
					//the graphs will be unweighted.
					while((s = br.readLine()) != null)
					{
						sArr = s.split(" ");
						putEdge(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]), 1);
					}
					//break out of the loop so unnecessary code is not run
					break;
				}
				//Check if there are three elements on the line.
				else if(sArr.length == 3)
				{
					//Put the first edge in the graphs. Use the first element parsed to an integer
					//as the source vertex and the second element parsed to an integer as the 
					//destination vertex.
					putEdge(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]));
					
					//Put the rest of the elements into the graph. Since the first row is of length 3,
					//the graphs will be weighted.
					while((s = br.readLine()) != null)
					{
						sArr = s.split(" ");
						putEdge(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]));
					}
					//break out of the loop so unnecessary code is not run
					break;
				}
			}
			//close the BufferedWriter
			br.close();
		//if file does not exist, catch and print an error message.
		}catch(FileNotFoundException e) {
			System.out.println("The inputed file does not exists or is not in the current project folder.");
			System.out.println("Please rerun the program and try again.");
		}
	}
	
	//Function to get the name of the file from the user. Prompt for the name,
	//scan in the name, and return file name.
	static String getFileName(String type)
	{
		System.out.println("Please input the "+ type +" file name: ");
		
		Scanner scan = new Scanner(System.in);
		String file = scan.nextLine();
				
		return file;
	}
	
	Edge [] kruskals()
	{
		UnionFind uf = new UnionFind();
		uf.makeSet(numVertices);
		int count = 0;
		//vertices - 1 because you cannot have a loop
		Edge [] edgeArr = new Edge [numVertices-1];
		while(count < numVertices -1)
		{
			Edge e = pq.poll();
			if(uf.find(e.getV1()) != uf.find(e.getV2()))
			{
				uf.union(e.getV1(), e.getV2());
				edgeArr[count] = e;
				count++;
			}
		}
		return edgeArr;
	}
	
	int mstWeight(Edge [] ar)
	{
		int weight=0;
		for(Edge e : ar)
			weight += e.getW();
		return weight;
	}
	
	void pqInit()
	{
		pq = new PriorityQueue<Edge>(numVertices, new Comparator<Edge>()
				{
					public int compare(Edge x, Edge y)
					{
						if(x.getW() < y.getW())
							return -1;
						if(x.getW() > y.getW())
							return 1;
						else
							return 0;
					}
				});
	}
	
	//These abstract methods are parts of a graph but cannot be defined
	//until other classes because it must be known if it is directed or 
	//undirected, a matrix or list representation, or weighted or unweighted.
	abstract boolean existsEdge(int i, int j);
	abstract void putEdge(int i, int j, int w);
	abstract void removeEdge(int i, int j);
	abstract void init();
	abstract void initializeSize();


}
