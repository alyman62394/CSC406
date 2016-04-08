package csc406;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ChainMatrix
{
	int numDim;
	int dimensions [];
	
	ChainMatrix() throws IOException
	{
		createDimArr();
	}
	
	int [] [] chainMatrixMult()
	{
		int [][] N = new int [numDim-1][numDim-1];
		for(int i=0; i<numDim-1; i++)
			N[i][i] = 0;
		for(int n=1; n<numDim-1; n++)
			for(int i=0; i<numDim-n-1;i++)
			{
				int j = n+i;
				for(int k=i; k<j; k++)
					if(N[i][j] == 0 || N[i][j] > N[i][k] + N[k+1][j] +dimensions[i]*dimensions[k+1]*dimensions[j+1])
					{
						N[i][j] =  N[i][k] + N[k+1][j] +dimensions[i]*dimensions[k+1]*dimensions[j+1];
						N[j][i] = k;
					}
			}
		return N;
	}
	
	void createDimArr()
		throws IOException
		{
			try{
				String currentDir = new File(".").getAbsolutePath();
				currentDir = currentDir.substring(0, currentDir.length()-1);
				
				String file = currentDir + "src\\csc406\\"+getFileName();
				
				//create the FileReader and BufferedReader to read the file line by line
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				//'s' is to be used by the BufferedReader to store each line as a string.
				String s = br.readLine();
				int count = 0;
				//Create the string array of the line
				numDim = Integer.parseInt(s);
				dimensions = new int [numDim];
				while((s = br.readLine()) != null)
				{
						dimensions[count]= Integer.parseInt(s);
						count ++;
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
		static String getFileName()
		{
			System.out.println("Please input the dimension file name (for chain matrix mult): ");
			
			Scanner scan = new Scanner(System.in);
			String file = scan.nextLine();
					
			return file;
		}
		
		String printDim()
		{
			String out="";
			for(int i=0; i<numDim; i++)
				out+=" "+dimensions[i]+" ";
			return out;
		}
		//toString to print out the chain matrix result
		public String toString(int [][] arr)
		{
			String out ="";
			for(int i=0; i<arr.length; i++)
			{
				out+="\r\n";
				for(int j=0; j<arr.length; j++)
					out+="\t"+arr[i][j];
			}
			return out;
		}
}
