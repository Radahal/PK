
public class Division {
	private Point begin, end;
	
	public Division(Point begin, Point end) throws DivisionException {
		if(begin==null || end==null)
			throw new DivisionException("New Division: NullPoint");
		
		this.begin = new Point(begin);
		this.end = new Point(end);
	}
	
	public Division(Division division) {
		this.begin = new Point(division.begin);
		this.end = new Point(division.end);
	}
	
	public void update(Point begin, Point end) throws DivisionException {
		if(begin==null || end==null)
			throw new DivisionException("New Division: NullPoint");
		
		this.begin = new Point(begin);
		this.end = new Point(end);
	}
	
	public double length() {
		double deltaX, deltaY;
		deltaX=this.begin.getX() - this.end.getX();
		deltaY=this.begin.getY() - this.end.getY();
		return (Math.sqrt( deltaX*deltaX + deltaY*deltaY ));
	}
	
	public static double length(Point pointA, Point pointB) throws DivisionException {	
		double deltaX,deltaY;
		if( (pointA==null) || (pointB==null)) 
		{
			throw new DivisionException("Length of division: NullPoint");
		}
		deltaX=pointA.getX() - pointB.getX();
		deltaY=pointA.getY() - pointB.getY();
		return (Math.sqrt( deltaX*deltaX + deltaY*deltaY ));

	}
	
	public void setBegin(Point begin) {
		this.begin = new Point(begin);
	}
	
	public Point getBegin() {
		return this.begin;
	}
	
	public void setEnd(Point end) {
		this.end = new Point(end);
	}
	
	public Point getEnd() {
		return this.end;
	}
	
	public static Point getMiddle(Point pointA, Point pointB) throws DivisionException {
		if( (pointA==null) || (pointB==null)) 
		{
			throw new DivisionException("Middle of division: NullPoint");
		}
		
		return(new Point((pointB.getX()+pointA.getX())/2, (pointB.getY() + pointA.getY())/2));
	}
	
}