import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Square {
	private Point A,B,C,D;
	private Color figureColor;
	private static int size = 5;
	
	public Square(Point A) {
		this.A = new Point(A);
		this.B = new Point(A.getX()+size,A.getY());
		this.C = new Point(A.getX()+size, A.getY()+size);
		this.D = new Point(A.getX(), A.getY()+size);
		this.setRandomColor();
	}
	
	public Square(int Ax) {
		this.A = new Point(Ax,0);
		this.B = new Point(Ax+size, 0);
		this.C = new Point(Ax+size, size);
		this.D = new Point(Ax,size);
		this.setRandomColor();
	}
	
	void move(int Ax) {
		this.A.newCoordinates(Ax,0);
		this.B.newCoordinates(Ax+size, 0);
		this.C.newCoordinates(Ax+size, size);
		this.D.newCoordinates(Ax, size);
	}
	
	void setRandomColor() {
		Random random = new Random();
		int R = (int) random.nextInt(255);
		int G = (int) random.nextInt(255);
		int B = (int) random.nextInt(255);
		this.figureColor= new Color(R, G, B);
	}
	
	public void translate(double x, double y) {
		//translate all cords by x and y
		this.getA().translate(x, y);
		this.getB().translate(x, y);
		this.getC().translate(x, y);
		this.getD().translate(x, y);
	}
	
	public void translateX(double x) {
		//translate all x-cords by x
		this.getA().translateX(x);
		this.getB().translateX(x);
		this.getC().translateX(x);
		this.getD().translateX(x);
	}
	
	public Point getA() {
		//get point A
		return this.A;
	}
	
	public void setA(Point A) {
		//set new point A and refresh divisions
		this.A = new Point(A);
	}
	
	public Point getB() {
		//get point B
		return this.B;
	}
	
	public void setB(Point B) {
		//set point B and refresh divisions
		this.B = new Point(B);
	}
	
	public Point getC() {
		//get point C
		return this.C;
	}
	
	public void setC(Point C) {
		//set point C and refresh divisions
		this.C = new Point(C);
	}
	
	public Point getD() {
		//get point D
		return this.D;
	}

	public void setD(Point D) {
		//set point C and refresh divisions
		this.D = new Point(D);
	}
	
	public static int getSize() {
		return size;
	}
	
	public void translateY(int y) {
		//translate all y-cords by y
		this.getA().translateY(y);
		this.getB().translateY(y);
		this.getC().translateY(y);
		this.getD().translateY(y);
	}
	
	public Color getFigureColor() {
		return figureColor;
	}

	public void setFigureColor(Color figureColor) {
		this.figureColor = figureColor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "A"+getA()+", B"+getB()+", C"+getC()+", D"+getD();
	}
	
	public void drawFigure(Graphics g) {
		int[] xPoints = {(int) A.getX(), (int) B.getX(), (int) C.getX(), (int) D.getX()};
		int[] yPoints = {(int) A.getY(), (int) B.getY(), (int) C.getY(), (int) D.getY()};
		int nPoints = xPoints.length;
		g.setColor(getFigureColor());
		g.drawPolygon(xPoints, yPoints, nPoints);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public void drawFigureWithoutFill(Graphics g) {
		int[] xPoints = {(int) A.getX(), (int) B.getX(), (int) C.getX(), (int) D.getX()};
		int[] yPoints = {(int) A.getY(), (int) B.getY(), (int) C.getY(), (int) D.getY()};
		int nPoints = xPoints.length;
		g.setColor(getFigureColor());
		g.drawPolygon(xPoints, yPoints, nPoints);
	}
	
	
}
