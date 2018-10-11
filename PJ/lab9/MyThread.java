import java.awt.Dimension;

public class MyThread implements Runnable {

	private int ID;
	private Square square;
	private CanvasComponent squareComponent;
	private int width, height; 
	private int speed;
	private int sleepTime;
	
	public static int maxSpeed = 15;
	/* state:
	 * @0 - nowy
	 * @1 - w ruchu
	 * @2 - zatrzymany
	 */
	private int state = 0;
	public Thread t;
	
	
	public MyThread(int ID, int width, int height) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.width= width;
		this.height= height;
		int Ax = (int) ((Math.random()*(width-Square.getSize())));
		this.speed = (int) ((Math.random()*this.maxSpeed) +1);
		this.sleepTime = (int) (1/speed);
		state=0;
		
		square = new Square(Ax);
		t= new Thread(this);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(square.getA().getY()>height) {
			int Ax = (int) ((Math.random()*(width-Square.getSize())));
			square.move(Ax);
		}
		square.translateY(speed);
		//System.out.println(square);
		try 
		{
			t.sleep(sleepTime);
			
		} catch( InterruptedException e) 
		{	
			System.out.println("Wątek został przerwany: "+ID);
			e.printStackTrace();
		}
		//System.out.println("Wątek zakonczony: "+ID);
	}
	
	
	public void start() {
		state=1;
		t.start();
		//run();
		
	}
	
	
	public void stop() {
		state=2;
		t.interrupt();
	}
	
	public void resume() {
		state=1;
		run();
	}
	
	public Square getSquare() {
		return square;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void join() {
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Wątek został przerwany: "+ID);
			e.printStackTrace();
		}
	}
}

