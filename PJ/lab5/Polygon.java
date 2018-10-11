import java.awt.Color;
import java.awt.Graphics;

public abstract class Polygon implements Figure{

	protected Color color;
	protected int id;
	public static int counter = 0;
	public static int drawed = 0;
	public boolean isDrawed = false;
	
	public abstract double countPerimeter();
	public abstract double countArea();
	public abstract void draw(Graphics g);
	public abstract void drawWithoutFill(Graphics g);
	public abstract void draw(Graphics g, int biasX, int biasY);
	public abstract void drawWithoutFill(Graphics g,int biasX, int biasY);
	public abstract void draw(Graphics g,int biasX, int biasY,int scale);
	public abstract void drawWithoutFill(Graphics g, int biasX, int biasY, int scale);
	public abstract void setDefaultColor();
	
	public abstract double getMaxX();
	public abstract double getMinX();
	public abstract double getMaxY();
	public abstract double getMinY();
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setId() {
		this.id = ++counter;
	}
	 
	public int getId() {
		return id;
	}
	
	public int countPolygons() {
		return counter;
	}
	
	public void incrementDrawed() {
		if(!(this.isDrawed))
			drawed++;
	}
	
	public int getDrawed() {
		return drawed;
	}
	
	public void setIsDrawed(boolean isDrawed) {
		this.isDrawed = isDrawed;
	}
	
	public boolean isNotDrawed() {
		if(isDrawed)
			return false;
		return true;
	}
}
