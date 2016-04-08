package csc406;

public class UnionFind
{
	int [] array;
	void makeSet(int numVert)
	{
		array = new int[numVert];
		for(int i=0; i <numVert; i++)
			array[i] = -1;
	}
	
	void union(int x, int y)
	{
		if(find(x)<find(y))
			array[find(y)]=find(x);
		else
			array[find(x)]=find(y);
	}
	
	int find(int x)
	{
        if (array[x] < 0 )
        	return x;
       else {
    	   array[x] = find(array[x]);
    	   return array[x];
       }
    }
	
}
