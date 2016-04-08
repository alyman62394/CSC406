package csc406;

import java.io.*;

public class DMWG extends DM
{
	//This constructor calls its super class (AdjacencyMatrix)
	//with the number of vertices as an argument to set numVertices.
	protected DMWG()
	throws IOException
	{
		createGraph();
	}
}
