
public class myThread implements Runnable{

	
	private int threadID;
	protected Matrix m;
	protected Vector v, r;
	private int division, rest, size, maxThreadID;
	
	myThread(int id, Matrix mat, Vector v1, Vector res, int size, int maxThreadID) {
		threadID = id;
		m = mat;
		v = v1;
		r = res;
		this.maxThreadID = maxThreadID -1;
		this.size = size;
		division = size/maxThreadID;
		rest = size % maxThreadID;
		System.out.println("Creating thread: " +  id );
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread number " +  threadID );
		try {
			 for(int i = threadID * division; i < (1 + threadID) * division; i++)
		        {
		            r.setElement(i, count_Vector(i));
		            System.out.println(r);
		        }
			
			//Counting other elements with last Thread
		        if((rest!=0) && (threadID==maxThreadID))
		        {
		            for(int i=0; i<rest; i++)
		            {
		                r.setElement(size-(i+1), count_Vector(size-(i+1)));
		                System.out.println(r);
		            }
		        }
			
			Thread.sleep(10);		
		} catch(InterruptedException e) {
			System.out.println("Thread " + threadID + " interrupted.");
		}
		System.out.println("Thread "+ threadID + " exiting.");
	}
	

	public void start() {
		System.out.println("Starting "+ threadID);
		this.run();
	}

	 public double count_Vector(int indeks)
	    {
	        double sum = 0;
	        for (int j=0; j<size; ++j)
	            sum+=m.getElement(indeks, j)*v.getElement(j);
	        return sum;
	    }
	
}
