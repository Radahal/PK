import java.awt.Graphics;

public interface Figure {

	public double countPerimeter();
	public double countArea();
	
	public void draw(Graphics g);
	public void drawWithoutFill(Graphics g);
	public void draw(Graphics g, int biasX, int biasY);
	public void drawWithoutFill(Graphics g,int biasX, int biasY);
	public void draw(Graphics g,int biasX, int biasY,int scale);
	public void drawWithoutFill(Graphics g, int biasX, int biasY, int scale);
	
	
	public double getMaxX();
	public double getMinX();
	public double getMaxY();
	public double getMinY();
}
