
public class Matrix {
	private int n, m;
	private int id;
	private double[][] matrix;
	static int licznik = 0;
	
	public Matrix(int n, int m) {
		// rectangle matrix full of 0
		try
		{
			this.n=n;
			this.m=m;
			this.matrix= new double[this.n][this.m];
		}
		catch (OutOfMemoryError e) {
			System.out.println("Tworzenie macierzy "+e.getMessage());
		}
		this.id = ++licznik;
	}
	
	public Matrix(int n, int m, double value) {
		// rectangle matrix full of value
		try
		{
			this.n=n;
			this.m=m;
			this.matrix= new double[this.n][this.m];
		}
		catch (OutOfMemoryError e) {
			System.out.println("Tworzenie macierzy "+e.getMessage());
		}
		try
		{
			for(n--;n>=0;n--)
			{
				for(m--;n>=0;m--)
					this.matrix[n][m]=value;
			}
		} 
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Tworzenie macierzy "+e.getMessage());
		}
		this.id = ++licznik;
	}
	
	public Matrix(int n) {
		// squared matrix full of 0
		try
		{
			this.n=n;
			this.m=n;
			this.matrix= new double[this.n][this.m];
		}
		catch (OutOfMemoryError e) {
			System.out.println("Tworzenie macierzy "+e.getMessage());
		}
		this.id = ++licznik;
	}
	
	public Matrix(int n, double value) {
		// squared matrix full of value
		int i, j;
		try
		{
			this.n=n;
			this.m=n;
			this.matrix= new double[this.n][this.m];
		}
		catch (OutOfMemoryError e) {
			System.out.println("Tworzenie macierzy "+e.getMessage());
		}
		
		try
		{
			
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
					this.matrix[i][j]=value;
			}
		} 
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Tworzenie macierzy "+e.getMessage());
		}
		this.id = ++licznik;
	}
	
	public Matrix(double[][] matrix) {
		// new matrix copy of 2d array
		try
		{
			this.n = matrix.length;
			this.m = matrix[0].length;
			this.matrix = new double[matrix.length][];
			for(int i = 0; i < matrix.length; i++)
			{
			  this.matrix[i] = new double[matrix[i].length];
			  for (int j = 0; j < matrix[i].length; j++)
			  {
			    this.matrix[i][j] = matrix[i][j];
			  }
			}
		}
		catch( ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Tworzenie macierzy " + e.getMessage());
		}
		this.id = ++licznik;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getN() {
		return this.n;
	}
	
	public int getM() {
		return this.m;
	}
	
	public double getElement(int n, int m) {
		try
		{
			return this.matrix[n][m];
		}
		catch ( ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Matrix get "+e.getMessage());
		}
		return 0;
	}
	
	public void setElement(int n, int m, double value) {
		try
		{
			this.matrix[n][m] = value;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Matrix set "+e.getMessage());
		}
	}
	
	public void setElementWithLabel(int n, int m, double value) {
		
		try
		{
			this.matrix[n][m] = value;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Matrix set "+e.getMessage());
		}
		System.out.println("Matrix"+this.getId()+" po umieszczeniu elementu "+value+" na pozycji ["+n+","+m+"]"+this);
		System.out.println("Element umieszczony na pozycji ["+n+","+m+"]: "+value+"\n");
		
	}
	
	public void addMatrix(Matrix m) throws MatrixException {
		int i, j;
		if( (this.getN()!= m.getN()) || (this.getM() != m.getM()) )
			throw new MatrixException("Dodawanie różnej wielkości");
		
		for(i=0;i<this.getN();i++) 
		{
			for(j=0;j<this.getM();j++)
				this.setElement(i, j, this.getElement(i, j) + m.getElement(i, j));
		}
	}
	
	public void subbstractMatrix(Matrix m) throws MatrixException {
		int i, j;
		if((this.getN()!= m.getN()) || (this.getM() != m.getM()))
			throw new MatrixException("Dodawanie różnej wielkości");
		
		for(i=0;i<this.getN();i++) 
		{
			for(j=0;j<this.getM();j++)
				this.setElement(i, j, this.getElement(i, j) - m.getElement(i, j));
		}
	}
	
	public Vector multiplyMatrix(Vector v) throws MatrixException {
		
		int i, j;
		double sum = 0;
		Vector vector;
		
		if(this.getN() != v.getSize())
			throw new MatrixException("Mnożenie różnej wielkości");
		
		vector = new Vector(v.getSize());
		
		for(i = 0; i<this.getN(); i++) {
			sum = 0;
			for(j=0;j<this.getM();j++) {
				sum += this.getElement(i, j) *v.getElement(i);
			}
			vector.setElement(i, sum);
		}
		
		return vector;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string="";
		int i, j;
		for(i=0; i<this.getN(); i++) {
			string+="\n";
			for(j=0; j<this.getM(); j++) {
				string+="["+i+","+j+"]"+this.getElement(i, j)+"\t";
			}
		}
		return string;
	}
}
