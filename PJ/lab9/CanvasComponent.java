import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CanvasComponent extends JPanel {

	private ArrayList<MyThread> threads;
	
	public CanvasComponent(ArrayList<MyThread> threads) {
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.threads = threads;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		for(int i =0; i<threads.size(); i++){
			threads.get(i).getSquare().drawFigure(g);
			//System.out.println(threads.get(i).getID());
		}
	}
	
}
