package csc406;

public class Edge
{
	//Set int variables to represent source(v1), destination (v2),
	//and weight(w) of an edge
	int v1, v2, w;
	
	//This constructor takes in 3 arguments and sets the source,
	//destination, and weight variables accordingly for a weighted graph.
	public Edge(int vertice1, int vertice2, int weight)
	{
		v1 = vertice1;
		v2 = vertice2;
		w = weight;
	}
	
	//This constructor takes in 2 arguments and sets the source and
	//destination variables accordingly for an unweighted graph. It
	//sets the weight to 1.
	public Edge(int vertice1, int vertice2)
	{
		v1 = vertice1;
		v2 = vertice2;
		w = 1;
	}
	
	//getV1 returns the source vertex of an edge
	public int getV1()
	{
		return v1;
	}
	
	public void setV1(int vertice1)
	{
		v1 = vertice1;
	}

	//getV2 returns the destination vertex of an edge
	public int getV2()
	{
		return v2;
	}
	
	public void setV2(int vertice2)
	{
		v2 = vertice2;
	}
	
	//getW returns the weight of an edge
	public int getW()
	{
		return w;
	}
	
	public void setW(int weight)
	{
		w = weight;
	}
	
	//auto generated equals method for edge equality
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (v1 != other.v1)
			return false;
		if (v2 != other.v2)
			return false;
		return true;
	}
	
	//toString nicely displays an edge in the form:
	//(source, destination, weight)
	public String toString()
	{
		return "(" + getV1() + ", " + getV2() + ", " + getW() + ")\n";
	}
}
