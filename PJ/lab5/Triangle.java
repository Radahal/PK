import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Polygon implements Comparable<Triangle> {
	private Point A,B,C;
	private Division a,b,c;
	private boolean isError = false;
	private Color figureColor;
	
	public Triangle(Point A, Point B, Point C) throws TriangleException, DivisionException {
		//triangle ABC with divisions a, b, c located on the opposite site to vertex at the same name
		if(A==null || B==null || C==null) {
			this.isError=true;
			throw new TriangleException("new Triangle: NullPoint");
		}
		if(!canItBeTriangle(A, B, C)) {
			this.isError=true;
			throw new TriangleException("new Triangle: IneligibleCondition");
		}
		this.A = new Point(A);
		this.B = new Point(B);
		this.C = new Point(C);
		this.a = new Division(this.B, this.C);
		this.b = new Division(this.C, this.A);
		this.c = new Division(this.A, this.B);
		this.setDefaultColor();
		this.setId();
	}
	
	public Triangle(Point A, Point B, Point C, Color color) throws TriangleException, DivisionException {
		//triangle ABC with divisions a, b, c located on the opposite site to vertex at the same name
		if(A==null || B==null || C==null) {
			this.isError=true;
			throw new TriangleException("new Triangle: NullPoint");
		}
		if(!canItBeTriangle(A, B, C)) {
			this.isError=true;
			throw new TriangleException("new Triangle: IneligibleCondition");
		}
		this.A = new Point(A);
		this.B = new Point(B);
		this.C = new Point(C);
		this.a = new Division(this.B, this.C);
		this.b = new Division(this.C, this.A);
		this.c = new Division(this.A, this.B);
		this.setColor(color);
		this.setId();
	}
	
	public Triangle(Triangle triangle) {
		//create copy of existing triangle
		this.A = new Point(triangle.getA());
		this.B = new Point(triangle.getB());
		this.C = new Point(triangle.getC());
		this.a = new Division(triangle.getDivisionA());
		this.b = new Division(triangle.getDivisionB());
		this.c = new Division(triangle.getDivisionC());
		this.isError = triangle.isError();
		this.setColor(triangle.getColor());
		this.setId();
	}
	
	public Triangle(Triangle triangle, Color color) {
		//create copy of existing triangle
		this.A = new Point(triangle.getA());
		this.B = new Point(triangle.getB());
		this.C = new Point(triangle.getC());
		this.a = new Division(triangle.getDivisionA());
		this.b = new Division(triangle.getDivisionB());
		this.c = new Division(triangle.getDivisionC());
		this.isError = triangle.isError();
		this.setColor(color);
		this.setId();
	}
	
	@Override
	public void setDefaultColor() {
		this.setColor(new Color(85, 139, 47, 120));
	}
	
	public Point getA() {
		//get point A
		return this.A;
	}
	
	public void setA(Point A) throws DivisionException {
		//set new point A and refresh divisions
		this.A = new Point(A);
		refreshDivisions();
	}
	
	public Point getB() {
		//get point B
		return this.B;
	}
	
	public void setB(Point B) throws DivisionException {
		//set point B and refresh divisions
		this.B = new Point(B);
		refreshDivisions();
	}
	
	public Point getC() {
		//get point C
		return this.C;
	}
	
	public void setC(Point C) throws DivisionException {
		//set point C and refresh divisions
		this.C = new Point(C);
		refreshDivisions();
	}
	
	public boolean isError() {
		//return statement of error in object
		return this.isError;
	}
	
	public Division getDivisionA() {
		//get division a of beginning in point B and end in point C
		return this.a;
	}
	
	public Division getDivisionB() {
		//get division b of beginning in point C and end in point A
		return this.b;
	}
	
	public Division getDivisionC() {
		//get division c of beginning in point A and end in point B
		return this.c;
	}
	
/*
 * ==================================================================
 * 
	public void setDivisionA(Point begin, Point end) throws DivisionException {
		//set new coords of division a of beginning in B and end in C
		this.a = new Division(begin, end);
		
	}

	public void setDivisionB(Point begin, Point end) throws DivisionException {
		//begin is C, end is A
		this.b = new Division(begin, end);
	}
	
	public void setDivisionC(Point begin, Point end) throws DivisionException {
		//begin is A, end is B
		this.c = new Division(begin, end);
	}
 * 
 * ==================================================================
 */
	public void refreshDivisions() throws DivisionException {
		//update begin and end points of divisions
		this.a.update(this.B, this.C);
		this.b.update(this.C, this.A);
		this.c.update(this.A, this.B);
	}
	
	
	public void typeofTriangle() throws DivisionException {
		// return to standard output typeOfTriangle
		if( (a.length() == b.length()) && (b.length()==c.length()) )
			System.out.println("equilateral triangle");
		else if((a.length() == b.length()) || (b.length()==c.length()) || (a.length()==c.length()))
			System.out.println("isosceles triangle");
		else if((a.length() != b.length()) && (b.length()!=c.length()) && (a.length()!=c.length()))
			System.out.println("multilateral triangle");
	}
	
	public static boolean canItBeTriangle(Point A, Point B, Point C) throws TriangleException, DivisionException {
		//return true if is possible to create triangle of three points A, B, C
		if(A==null || B==null || C==null)
			throw new TriangleException("canItBeTriangle: NullPoint");
		
		/*//warunek rownowazny
		 * if(A==B || A==C || B==C)
		 * 		return false;
		 */
		
		double lengthA, lengthB, lengthC, max;
		lengthA = Division.length(B, C);
		lengthB = Division.length(C, A);
		lengthC = Division.length(A, B);
		
		max = Math.max(lengthA, Math.max(lengthB, lengthC));
		if(max < (lengthA + lengthB + lengthC - max)) 
		{
			return true;
		} else {
			return false;
		}
	}
	
	public double area()  {
		//return value of area of triangle object
		double p;
		p = (a.length() + b.length() + c.length())/2;
		return Math.sqrt(p*(p-a.length())*(p-b.length())*(p-c.length()));
	}
	
	public double heightOfA() {
		//return value of height from vertex A of triangle
		return  ((2*this.area())/this.a.length());
	}
	
	public double heightOfB() {
		//return value of height from vertex B of triangle
		return  ((2*this.area())/this.b.length());
	}
	
	public double heightOfC() {
		//return value of height from vertex C of triangle
		return  ((2*this.area())/this.c.length());
	}
	
	@SuppressWarnings("null")
	public static double area(Point A, Point B, Point C) throws DivisionException, TriangleException {
		//return value of area between points A,B,C
		double a,b,c,p;
		if(canItBeTriangle(A,B,C)) {
			a = Division.length(B, C);
			b = Division.length(C, A);
			c = Division.length(A, B);
			p = (a + b + c)/2;
			return Math.sqrt(p*(p-a)*(p-b)*(p-c));
		} else {
			return (Double) null;
		}
	}
	
	public void translate(double x, double y) throws DivisionException {
		//translate all cords by x and y
		this.getA().translate(x, y);
		this.getB().translate(x, y);
		this.getC().translate(x, y);
		refreshDivisions();
	}
	
	public void translateX(double x) throws DivisionException {
		//translate all x-Cords by x 
		this.getA().translateX(x);
		this.getB().translateX(x);
		this.getC().translateX(x);
		refreshDivisions();
	}
	
	public void translateY(double y) throws DivisionException {
		//translate all y-Cords by y 
		this.getA().translateY(y);
		this.getB().translateY(y);
		this.getC().translateY(y);
		refreshDivisions();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "A"+getA()+", B"+getB()+", C"+getC();
	}

	@Override
	public int compareTo(Triangle o) {
		// TODO Auto-generated method stub
		if( this.area() < o.area() )
			return -1;
		else if(this.area() == o.area())
			return 0;
		else
			return 1;
	}

	
	public double getMaxX() {
		return Math.max(A.getX(), Math.max(B.getX(), C.getX()));
	}
	
	public double getMaxY() {
		return Math.max(A.getY(), Math.max(B.getY(), C.getY()));
	}
	
	public double getMinX() {
		return Math.min(A.getX(), Math.min(B.getX(), C.getX()));
	}
	
	public double getMinY() {
		return Math.min(A.getY(), Math.min(B.getY(), C.getY()));
	}

	@Override
	public double countPerimeter() {
		// TODO Auto-generated method stub
		return a.length() + b.length() + c.length();
	}

	@Override
	public double countArea() {
		// TODO Auto-generated method stub
		double p;
		p = (a.length() + b.length() + c.length())/2;
		return Math.sqrt(p*(p-a.length())*(p-b.length())*(p-c.length()));
	}
	
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int[] xPoints = {(int) A.getX(), (int) B.getX(), (int) C.getX()};
		int[] yPoints = {(int) A.getY(), (int) B.getY(), (int) C.getY()};
		int nPoints = xPoints.length;
		g2d.setColor(getColor());
		g2d.drawPolygon(xPoints, yPoints, nPoints);
		g2d.fillPolygon(xPoints, yPoints, nPoints);
		//if(isNotDrawed()) {
		incrementDrawed();
	//	setIsDrawed(true);
	//}
	}

	@Override
	public void drawWithoutFill(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// TODO Auto-generated method stub
		int[] xPoints = {(int) A.getX(), (int) B.getX(), (int) C.getX()};
		int[] yPoints = {(int) A.getY(), (int) B.getY(), (int) C.getY()};
		int nPoints = xPoints.length;
		g2d.setColor(getColor());
		g2d.drawPolygon(xPoints, yPoints, nPoints);
		//if(isNotDrawed()) {
		incrementDrawed();
	//	setIsDrawed(true);
	//}
	}

	@Override
	public void draw(Graphics g, int biasX, int biasY) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int[] xPoints = {(int) (A.getX()+ biasX) , (int) (B.getX()+ biasX), (int) (C.getX()+ biasX)};
		int[] yPoints = {(int) (A.getY()+ biasY)  , (int) (B.getY()+ biasY) , (int) (C.getY()+ biasY) };
		int nPoints = xPoints.length;
		g2d.setColor(getColor());
		g2d.drawPolygon(xPoints, yPoints, nPoints);
		g2d.fillPolygon(xPoints, yPoints, nPoints);
		//if(isNotDrawed()) {
		incrementDrawed();
	//	setIsDrawed(true);
	//}
	}

	@Override
	public void drawWithoutFill(Graphics g, int biasX, int biasY) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int[] xPoints = {(int) (A.getX()+ biasX) , (int) (B.getX()+ biasX), (int) (C.getX()+ biasX)};
		int[] yPoints = {(int) (A.getY()+ biasY)  , (int) (B.getY()+ biasY) , (int) (C.getY()+ biasY) };
		int nPoints = xPoints.length;
		g2d.setColor(getColor());
		g2d.drawPolygon(xPoints, yPoints, nPoints);
		//if(isNotDrawed()) {
		incrementDrawed();
	//	setIsDrawed(true);
	//}
	}

	@Override
	public void draw(Graphics g, int biasX, int biasY, int scale) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int[] xPoints = {(int) ((A.getX()+ biasX)*scale) , (int) ((B.getX()+ biasX)*scale), (int) ((C.getX()+ biasX)*scale)};
		int[] yPoints = {(int) ((A.getY()+ biasY)*scale)  , (int) ((B.getY()+ biasY)*scale) , (int) ((C.getY()+ biasY)*scale) };
		int nPoints = xPoints.length;
		g2d.setColor(getColor());
		g2d.drawPolygon(xPoints, yPoints, nPoints);
		g2d.fillPolygon(xPoints, yPoints, nPoints);
		//if(isNotDrawed()) {
		incrementDrawed();
	//	setIsDrawed(true);
	//}
	}

	@Override
	public void drawWithoutFill(Graphics g, int biasX, int biasY, int scale) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		int[] xPoints = {(int) ((A.getX()+ biasX)*scale) , (int) ((B.getX()+ biasX)*scale), (int) ((C.getX()+ biasX)*scale)};
		int[] yPoints = {(int) ((A.getY()+ biasY)*scale)  , (int) ((B.getY()+ biasY)*scale) , (int) ((C.getY()+ biasY)*scale) };
		int nPoints = xPoints.length;
		g2d.setColor(getColor());
		g2d.drawPolygon(xPoints, yPoints, nPoints);
		//if(isNotDrawed()) {
		incrementDrawed();
	//	setIsDrawed(true);
	//}
	}

}
