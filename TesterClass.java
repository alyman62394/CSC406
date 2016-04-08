package csc406;
import java.io.*;
import java.util.*;

public class TesterClass 
{
	public static void main(String args[])
	throws IOException
	{
		//get the current directory
		String currentDir = new File(".").getAbsolutePath();
		currentDir = currentDir.substring(0, currentDir.length()-1);
		
		//prepare an output file in the current directory named outputFile.txt
		//if the output file does not exist, create a new one
		File outFile = new File(currentDir+"src\\csc406\\outputFile.txt");
		if(!outFile.exists())
			outFile.createNewFile();
		
		//create the FileWriter and BufferedWriter to write to the output file
		FileWriter fw = new FileWriter(outFile);
		BufferedWriter bw = new BufferedWriter(fw);

		ChainMatrix cm = new ChainMatrix();
//		//directed unweighted graphs
//		DMUG dMUg = new DMUG();
		DLUG dLUg = new DLUG();
		//unweightedGraphs(bw, dMUg, dLUg);
		
//		//undirected weighted graphs
//		UMWG uMWg = new UMWG();
		ULWG uLWg = new ULWG();
//		weightedUndirectedGraphs(bw, uMWg, uLWg);
		algorithms(bw, uLWg, dLUg, cm);

		//Print a statement to show it was successful and the location the output file
		System.out.println("The file was written and is located at:");
		System.out.println(outFile);
		//close the BufferedWriter	
		bw.close();
	}
	
	//Function to write the tests for the unweighted graphs to the output file.
		public static void algorithms(BufferedWriter bw, ULWG uliW, DLUG liUnw, ChainMatrix cm)
		throws IOException
		{
			
			int [] ar = new int [] {10, 20, 50, 1, 100};
			//TOPO SORT is to be tested on DLUG!!!!!
			
			bw.write("Kahn's Topo Sort Algorithm:\r\n");
			bw.write(liUnw.printTopoArr(liUnw.kahns()));
			
			bw.write("\r\nTarjan's Topo Sort Algorithm:\r\n");
			TarjansTopo tt = new TarjansTopo();
			bw.write(tt.toString(tt.tarjans(liUnw)));

			int [][] chain = cm.chainMatrixMult();			
			bw.write("\r\nChain Matrix Multiplication: D[]={"+cm.printDim()+"}\r\n");
			bw.write(cm.toString(chain));
			
			//KRUSKALS is to be tested on ULWG!!!!!!
			//Out put edges and weight of tree
			bw.write("\r\nKruskal's MST Algorithm:\r\n");
			Edge [] ear =  uliW.kruskals();
			for(int i=0; i<ear.length; i++)
				bw.write(ear[i]+"\r\n");
			bw.write("\r\nMST total weight: "+uliW.mstWeight(ear));
		}
}