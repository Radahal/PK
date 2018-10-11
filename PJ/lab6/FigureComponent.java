import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FigureComponent extends JPanel {
	
	protected int biasX, biasY; 
	int clickedID=0;
	int clickedCounter=0;
	protected ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	protected boolean isFirstRun = true, isDragged = false;
	protected boolean defaultCursorSetted = true;
	protected int mouseX, mouseY, xDragged=0, yDragged=0, idDragged= -1;
	
	public FigureComponent() {
		// TODO Auto-generated constructor stub
		super();
		addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMoutionHandler());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Triangle triangle : triangles) {
				triangle.draw(g);
			}
	}
	
	public void add(int x, int y) {
		int biasX = 50;
		int biasY = 50;
		try {
			triangles.add(new Triangle(new Point(x,y), new Point(x+biasX, y), new Point(x+biasX,y+biasY)));
		} catch (TriangleException | DivisionException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(int id) {
		int i=0;
		for(Triangle triangle : triangles) {
			if(triangle.getId()==id) {
				triangles.remove(i);
				break;
			}
			i++;
		}
	}
	
	public int find(int id) {
		int i=0;
		for(Triangle triangle : triangles) {
			if(triangle.getId()==id)
				return i;
			i++;
		}
		return -1;
	}
	
	public int find(int x, int y) {
		ArrayList<Integer> matched = new ArrayList<Integer>();
		for(Triangle triangle : triangles) {
			if(triangle.isItInside(x, y))
				matched.add(triangle.getId());
		}
		if(matched.size()>=1)
			return matched.get(matched.size()-1);
		else
			return -1;
	}
	
	
	public class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			int i=-1;
			int id = find(mouseX,mouseY);
			if( id != -1) {
				i=find(id);
				if(id==clickedID)
					clickedCounter++;
				else {
					clickedID=id;
					clickedCounter=1;
				}
				if(clickedCounter==2) {
					remove(id);
					invalidate();
					repaint();
					clickedCounter=0;
					clickedID=0;
				}
			} else {
				clickedCounter=0;
				clickedID=0;
			}
		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);
			int id = find(mouseX,mouseY);
			if( id ==-1) 
				add(mouseX,mouseY);
			else {
				xDragged = mouseX;
				yDragged = mouseY;
				idDragged = id;
			}
			
			invalidate();
			repaint();
		
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			int xDelta, yDelta,i;
			Triangle t;
			super.mouseReleased(e);
			if(isDragged && (idDragged!=-1)) {
				xDelta = mouseX-xDragged;
				yDelta = mouseY-yDragged;
				i=find(idDragged);
				t=triangles.get(i);
				try {
					t.translate(xDelta, yDelta);;
				} catch (DivisionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				xDragged=0;
				yDragged=0;
				idDragged=-1;
				isDragged=false;
				invalidate();
				repaint();
			}
		}
		

	}
	
	public class MouseMoutionHandler implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			isDragged = true;
			mouseX=arg0.getX();
			mouseY=arg0.getY();
		}


		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouseX=arg0.getX();
			mouseY=arg0.getY();
			if(find(mouseX,mouseY)!=-1) {
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				defaultCursorSetted=false;
			} else
				if(!defaultCursorSetted) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					defaultCursorSetted=true;
				}
		}
		
	}

}
