import java.util.Arrays;

public class Vector {
	private double[] vector;
	private int id;
	static int licznik = 0;
	
	
	public Vector(int x) {
		// Vector of length equal x and filled with 0
		try
		{
			this.vector = new double[x];
			
		}
		catch( OutOfMemoryError e)
		{
			System.out.println("Tworzenie wektora " + e.getMessage());
		}
		this.id = ++licznik;
	}
	
	public Vector(int x, double value) {
		// Vector of length equal x and filled of value
		int i;
		try
		{
			this.vector = new double[x];
			for(i=0;i<x;i++)
				this.vector[i]=value;
			}
		catch( OutOfMemoryError e) 
		{
			System.out.println("Tworzenie wektora " + e.getMessage());
		}
		this.id = ++licznik;
	} 
	
	public Vector(double[] array)
	{
		this.vector = Arrays.copyOf(array, array.length);
		this.id = ++licznik;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getElement(int x) 
	{
		try
		{
			return this.vector[x];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Vector get " + e.getMessage());
		}
		return 0;
	}
	
	public void setElement(int x, double value)
	{
		try
		{
			this.vector[x]=value;
		}
		catch( ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Vector set " + e.getMessage());
		}	
	}
	
	public void setElementWithLabel(int x, double value)
	{
		try
		{
			this.vector[x]=value;
		}
		catch( ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Vector set " + e.getMessage());
		}	
		System.out.println("Vector"+this.getId()+" po umieszczeniu elementu "+value+" na pozycji ["+x+"]: "+this);
		System.out.println("Element umieszczony na pozycji "+x+": "+value+"\n");
	}
	
	public int getSize() {
		return  vector.length;
	}
	
	public void addVector(Vector v) throws VectorException {
		int i=0, k;
		if(this.getSize()!=v.getSize())
			throw new VectorException("Dodawanie różnej wielkości");
		
		k=this.getSize();
		for(i=0;i<k;i++)
		{
			this.setElement(i, (this.getElement(i) + v.getElement(i)));
		}
	}
	
	public double scalarProduct(Vector v) throws VectorException {
		double solve = 0;
		int k = 0, i=0;
		if(this.getSize()!=v.getSize())
			throw new VectorException("Skalar różnej wielkości");
		
		k=this.getSize();
		for(i=0;i<k;i++)
		{
			solve+= this.getElement(i)*v.getElement(i);
		}
		return solve;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string ="";
		int i;
		for(i=0; i<this.getSize(); i++)
			string+="["+i+"]"+this.getElement(i)+"\t";
		return string;
	}
}
