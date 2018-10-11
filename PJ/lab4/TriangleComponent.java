import java.awt.Graphics;

import javax.swing.JPanel;

public class TriangleComponent extends JPanel {

	Triangle triangle;
	
	public TriangleComponent(Triangle triangle) {
		this.triangle = new Triangle(triangle);
	}
	
	public void paintComponent(Graphics g) {
		triangle.drawFigure(g);
	}
}
