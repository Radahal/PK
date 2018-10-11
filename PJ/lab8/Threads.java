
public class Threads  {
	
    private final static int maxSize = 6;
    private final static int threadsNumber = 3;
	
    private static int threadsCounter = 0;
    
    private static myThread [] threads = new myThread[threadsNumber];
    
	public static void main(String[] args) throws ThreadException {
		// TODO Auto-generated method stub
		
		//creating vectors
		Vector v1 = new Vector(maxSize, 4.0);
		Vector r = new Vector(maxSize,0.0);
		
		//creating matrix
		double [][] mat1 = 
			{
					{1,  1,  1,  1,  1,  1},
					{2,  3,  4,  5,  6,  7},
					{3,  5,  7,  9, 11, 13},
					{4,  7, 10, 13, 16, 19},
					{5,  9, 13, 17, 21, 25},
					{6, 11, 16, 21, 26, 31}
			};
		Matrix m1 = new Matrix(mat1);
		
		
		System.out.println(v1);
		System.out.println(m1);
		if(threadsNumber>maxSize)
			throw new ThreadException("Wielkosc macierzy, wektora oraz zadana ilosc wyjatkow niezgodne z zalozeniem programu");
		
		for(int i=0; i< threadsNumber; i++) {
			threads[i] = new myThread(threadsCounter++, m1, v1, r, maxSize, threadsNumber);
			
		}
		
		for(int i=0; i< threadsNumber; i++) {
			threads[i].start();
		}
		
		
	}



}
