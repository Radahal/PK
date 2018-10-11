import java.util.Arrays;

public class Figures {

	public static void main(String[] args) throws TriangleException, QuadrangleException, DivisionException {
		// TODO Auto-generated method stub
		Point p1, p2, p3,p4,p5;
		Triangle t1, t2, t3, t4;
		Quadrangle q1, q2, q3;
		
		Triangle[] arrayTriangle;
		Quadrangle[] arrayQuadrangle;
		
		p1 = new Point(1, 2);
		p2 = new Point(2,-3);
		p3 = new Point(2,0);
		p4 = new Point(0,0);
		p5 = new Point(0,2);

		
		t1 = new Triangle( new Point(0, 5), new Point(5), new Point(6, 8));
		t2 = new Triangle( new Point(3, -7), new Point(-2.54,2.33), new Point(4,3.25));
		t3 = new Triangle( p3,p4,p5);
		t4 = new Triangle(new Point(), new Point(-7, -6), new Point(3,4.76));
		
		q1 = new Quadrangle(new Point(-5.43), new Point(-3.35, 7.54), new Point(2.43, 8.34), new Point(4.56, -12.5));
		q2 = new Quadrangle( q1 );
		q2.translate(-4, -7.4);
		q3 = new Quadrangle(new Point(), new Point(3), new Point(3.7,  2), new Point(1,-1));
		
		arrayTriangle = new Triangle[] {t1,t2,t3,t4};
		arrayQuadrangle = new Quadrangle[] {q1,q2,q3};
		
		System.out.println("P1="+p1+"\tP2="+p2);
		System.out.println("Coordinate y of the point P1: "+p1.getY());
		p2.newCoordinates(1, 7.56);
		System.out.println("P2 after changed coordinates: "+p2);
		System.out.println("Distance between P1 and P2: "+Division.length(p1, p2));
		p1.newCoordinates(p2);
		System.out.println("P1 after update to P2: "+p1);
		
		System.out.println("\n");
		System.out.println("Triangle: "+t1);
		System.out.println("Circuit= "+t1.circuit() +"\tArea="+t1.area()+"\tHeight from vertex A="+t1.heightOfA());
		System.out.println("Coordinate0 "+t1.getA()+"\tCoordinate1 "+t1.getB()+"\tCoordinate2 "+t1.getC());
		t1.setA(p2);
		System.out.println("Triangle after update coordinate A: "+t1);
		
		System.out.println("\n");
		System.out.println("Triangle: "+t3);
		System.out.println("Circuit= "+t3.circuit() +"\tArea="+t3.area()+"\tHeight from vertex A="+t3.heightOfA());
		System.out.println("Coordinate0 "+t3.getA()+"\tCoordinate1 "+t3.getB()+"\tCoordinate2 "+t3.getC());
		
		System.out.println("\n");
		System.out.println("Quadrangle: "+q1);
		System.out.println("Circuit= "+q1.circuit() +"\tArea="+q1.area()+"\tDiagonal from A to C="+q1.lengthDiagonalAC());
		System.out.println("Coordinate0 "+q1.getA()+"\tCoordinate1 "+q1.getB()+"\tCoordinate2 "+q1.getC()+"\tCoordinate3 "+q1.getD());
		q1.setA(p1);
		System.out.println("Quadrangle after update coordinate A: "+q1);
		
		System.out.println("\n");
		System.out.println("An array of triangles:");
		for (Triangle triangle : arrayTriangle) {
			System.out.println(triangle);
		}
		Arrays.sort(arrayTriangle);
		System.out.println("An array of triangles after sorting:");
		for (Triangle triangle : arrayTriangle) {
			System.out.println(triangle);
		}
		
		System.out.println("\n");
		System.out.println("An array of quadrangles:");
		for (Quadrangle quadrangle : arrayQuadrangle) {
			System.out.println(quadrangle);
		}
		Arrays.sort(arrayQuadrangle);
		System.out.println("An array of quadrangles after sorting:");
		for (Quadrangle quadrangle : arrayQuadrangle) {
			System.out.println(quadrangle);
		}
	}

}
