package csc406;

import java.io.*;

public class DMUG extends DM
{
	//This constructor calls its super class (AdjacencyMatrix)
	//with the number of vertices as an argument to set numVertices.
	protected DMUG()
	throws IOException
	{
		createGraph();
	}
}
