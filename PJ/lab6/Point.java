
public class Point {
	private double x,y;

	public Point() {
		//create point of coordinates equal [0,0]
		this.x = 0;
		this.y = 0;
	}
	
	public Point(double x, double y) {
		//create point of coordinates equal [x,y]
		this.x = x;
		this.y = y;
	}
	
	public Point(double x) {
		//create point of coordinates equal [x,x]
		this.x = x;
		this.y = x;
	}
	
	public Point(Point point) {
		//create new point based on another (copy of existed point [x,y])
		this.x = point.getX();
		this.y = point.getY();
	}
	
	public double getX() {
		//get X
		return x;
	}

	public void setX(double x) {
		//set new X
		this.x = x;
	}

	public double getY() {
		//get Y 
		return y;
	}

	public void setY(double y) {
		//set new Y
		this.y = y;
	}
	
	public void swapCoords() {
		//swap values of coordinates
		double tmp = getX();
		setX(getY());
		setY(tmp);
	}
	
	public void translate(double x, double y) {
		//translate all cords by x and y
		
		setX(getX()+x);
		setY(getY()+y);
	}
	
	public void translateX(double x) {
		//translate all x-cords by x
		setX(getX()+x);
	}
	
	public void translateY(double y) {
		//translate all y-cords by y
		setY(getY()+y);
	}
	
	public void newCoordinates(double x, double y) {
		//set new coordinates [x,y]
		setX(x);
		setY(y);
	}
	
	public void newCoordinates( Point p) {
		//set new coordinates based on point p
		setX(p.getX());
		setY(p.getY());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+getX()+","+getY()+")";
	}
}
