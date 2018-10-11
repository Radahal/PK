import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FigureComponent extends JPanel {
	protected int biasX, biasY; 
	
	ArrayList<Polygon> polygons = new ArrayList<>();
	Figure[] figures;
	protected boolean isFirstRun = true;
	
	
	
	public void paintComponent(Graphics g) {

			if(isFirstRun) {
				initObjects();
				isFirstRun=false;
			}
			
			System.out.println("Table of polygons:");
			for (Polygon polygon : polygons) {
				 polygon.draw(g, biasX, biasY,10);
				 System.out.println("Number: "+polygon.getId() + "\tArea: "+polygon.countArea()+"\tPerimeter: "+polygon.countPerimeter()+"\tNumber of polygons: "+polygon.getDrawed());
				 
			}
			System.out.println("\nTable of figures:");
			for(Figure figure : figures) {
				figure.draw(g,biasX,biasY,10);
				System.out.println("Area: "+figure.countArea() +"\tPerimeter: "+figure.countPerimeter());
			}
	}

	public void initObjects() {
		try {
			polygons.add( new Triangle( new Point(0, 5), new Point(5), new Point(6, 8)));
			polygons.add( new Triangle( new Point(37, -7), new Point(15,17), new Point(2,4)));
			polygons.add( new Quadrangle(new Point(-5.43), new Point(-3.35, 7.54), new Point(2.43, 8.34), new Point(4.56, -12.5)));
			polygons.add( new Quadrangle(new Point(), new Point(3), new Point(3.7,  2), new Point(1,-1)));
			Quadrangle q1 = new Quadrangle(new Point(25,4), new Point(33,16), new Point(14,25), new Point(21, 0));
			Triangle t1 = new Triangle( new Point(3, -7), new Point(-2.54,2.33), new Point(4,3.25));
			figures = new Figure[] {q1, t1};
			setBiasXandBiasY(polygons, figures);
		} catch (TriangleException | DivisionException | QuadrangleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void setBiasXandBiasY(ArrayList<Polygon> polygons) {
		// TODO Auto-generated method stub
		double minX = 0;
		double maxY = 0;
		double x,y; 
		
		for (Polygon polygon : polygons) {
			x = polygon.getMinX();
			y = polygon.getMaxY();
			if(x < minX)
				minX = x;
			if(y > maxY)
				maxY = y;
		}
		
		biasX = (int) -minX;
		biasY = (int) maxY;
	}
	
	private void setBiasXandBiasY(ArrayList<Polygon> polygons, Figure[] figures) {
		// TODO Auto-generated method stub
		double minX = 0;
		double maxY = 0;
		double x,y; 
		
		for (Polygon polygon : polygons) {
			x = polygon.getMinX();
			y = polygon.getMaxY();
			if(x < minX)
				minX = x;
			if(y > maxY)
				maxY = y;
		}
		
		for (Figure figure : figures) {
			x = ((Polygon) figure).getMinX();
			y = ((Polygon) figure).getMaxY();
			if(x < minX)
				minX = x;
			if(y > maxY)
				maxY = y;
		}

		biasX = (int) -minX;
		biasY = (int) maxY;
	}
}
