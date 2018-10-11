import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FigureComponent extends JPanel {
	
	
	public void paintComponent(Graphics g) {
	
		Triangle[] triangles;
		Quadrangle[] quadrangles;
		int biasX, biasY; 
		
		
		try {
			Triangle t1 = new Triangle( new Point(0, 5), new Point(5), new Point(6, 8));
			Triangle t2 = new Triangle( new Point(3, -7), new Point(-2.54,2.33), new Point(4,3.25));
			Triangle t3 = new Triangle( new Point(-17, -7), new Point(-5,17), new Point(12,-24));
			Quadrangle q1 = new Quadrangle(new Point(-5.43), new Point(-3.35, 7.54), new Point(2.43, 8.34), new Point(4.56, -12.5));
			Quadrangle q2 = new Quadrangle(new Point(), new Point(3), new Point(3.7,  2), new Point(1,-1));
			Quadrangle q3 = new Quadrangle(new Point(25,4), new Point(33,16), new Point(14,25), new Point(21, 0));
			
			triangles = new Triangle[] {t1,t2,t3};
			quadrangles = new Quadrangle[] {q1,q2,q3};

			biasX = (int) -getBiasX(triangles, quadrangles);
			biasY = (int) getBiasY(triangles, quadrangles);
			
			for (Triangle triangle : triangles) {
				triangle.drawFigure(g, biasX, biasY);
			}
			
			for (Quadrangle quadrangle : quadrangles) {
				quadrangle.drawFigure(g, biasX, biasY);
			}
			
		} catch (TriangleException | DivisionException | QuadrangleException e) {
			e.printStackTrace();
		}

	
	}
	
	public double getBiasX(Triangle[] triangles, Quadrangle[] quadrangles) {
		double minX = 0;
		double x; 
		for (Triangle triangle : triangles) {
			x = triangle.getMinX();
			if(x < minX)
				minX = x;
		}
		for (Quadrangle quadrangle : quadrangles) {
			x = quadrangle.getMinX();
			if(x < minX)
				minX = x;
		}
		return minX;
	} 
	
	public double getBiasY(Triangle[] triangles, Quadrangle[] quadrangles) {
		double maxY = 0;
		double y; 
		for (Triangle triangle : triangles) {
			y = triangle.getMaxY();
			if(y > maxY)
				maxY = y;
		}
		for (Quadrangle quadrangle : quadrangles) {
			y = quadrangle.getMaxY();
			if(y > maxY)
				maxY = y;
		}
		return maxY;
	} 
}
