
public class Quadrangle implements Comparable<Quadrangle> {
	private Point A,B,C,D;
	private Division a,b,c,d;
	private boolean isError=false;
	
	public Quadrangle(Point A, Point B,Point C,Point D) throws QuadrangleException, DivisionException {
		//quadrangle ABCD with divisions a, b, c, d located between the vertex at the same name and the next vertex
		if(A==null || B==null || C==null || D==null) {
			this.isError=true;
			throw new QuadrangleException("new Quadrangle: NullPoint");
		}
		if(!canItBeQuadrangle(A, B, C, D)) {
			this.isError=true;
			throw new QuadrangleException("new Quadrangle: IneligibleCondition");
		}
		
		this.A = new Point(A);
		this.B = new Point(B);
		this.C = new Point(C);
		this.D = new Point(D);
		this.a = new Division(this.A, this.B);
		this.b = new Division(this.B, this.C);
		this.c = new Division(this.C, this.D);
		this.d = new Division(this.D, this.A);
	}
	
	public Quadrangle(Quadrangle quadrangle) {
		//create copy of existing quadrangle
				this.A = new Point(quadrangle.getA());
				this.B = new Point(quadrangle.getB());
				this.C = new Point(quadrangle.getC());
				this.D = new Point(quadrangle.getD());
				this.a = new Division(quadrangle.getDivisionA());
				this.b = new Division(quadrangle.getDivisionB());
				this.c = new Division(quadrangle.getDivisionC());
				this.d = new Division(quadrangle.getDivisionD());
				this.isError = quadrangle.isError();
		
	}

	public static boolean canItBeQuadrangle(Point A, Point B, Point C, Point D) throws DivisionException {
		//return true if is possible to create quadrangle of four points A, B, C, D
		double a, b, c, d, max;
		a = Division.length(A,B);
		b = Division.length(B,C);
		c = Division.length(C,D);
		d = Division.length(D,A);
		
		max = Math.max(a, Math.max(b, Math.max(c, d)));
		if(max<(a+b+c+d-max))
			return true;
		else
			return false;
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
	
	public Point getD() {
		//get point C
		return this.C;
	}
	
	public void setD(Point D) throws DivisionException {
		//set point C and refresh divisions
		this.D = new Point(D);
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
	
	public Division getDivisionD() {
		//get division c of beginning in point A and end in point B
		return this.d;
	}
	
	public void refreshDivisions() throws DivisionException {
		//update begin and end points of divisions
		this.a.update(this.A, this.B);
		this.b.update(this.B, this.C);
		this.c.update(this.C, this.D);
		this.d.update(this.D, this.A);
	}
	
	public double circuit() {
		//return value of circuit of quadrangle
		return a.length() + b.length() + c.length() + d.length();
	}
	
	public double area() throws DivisionException, TriangleException  {
		//return value of area of quadrangle object
		double p1, p2;
		p1 = Triangle.area(A, B, C);
		p2 = Triangle.area(A, C, D);
		return p1+p2;
	}
	
	@SuppressWarnings("null")
	public static double area(Point A, Point B, Point C, Point D) throws DivisionException, TriangleException {
		//return value of area between points A,B,C
		double p1, p2;
		if(canItBeQuadrangle(A,B,C,D)) {
			p1 = Triangle.area(A, B, C);
			p2 = Triangle.area(A, C, D);
			return p1+p2;
		} else {
			return (Double) null;
		}
	}
	
	public Division getDiagonalAC() throws DivisionException {
		//return diagonal between points A and C
		return new Division(getA(), getC());
	}
	
	public Division getDiagonalBD() throws DivisionException {
		//return diagonal between points B and D
		return new Division(getB(), getD());
	}
	
	public double lengthDiagonalAC() throws DivisionException {
		//return value of length of diagonal between points A and C
		return Division.length(getA(), getC());
	}
	
	public double lengthDiagonalBD() throws DivisionException {
		//return value of length of diagonal between points B and D
		return Division.length(getB(), getD());
	}
	
	public void translate(double x, double y) throws DivisionException {
		//translate all cords by x and y
		this.getA().translate(x, y);
		this.getB().translate(x, y);
		this.getC().translate(x, y);
		this.getD().translate(x, y);
		refreshDivisions();
	}
	
	public void translateX(double x) throws DivisionException {
		//translate all x-cords by x
		this.getA().translateX(x);
		this.getB().translateX(x);
		this.getC().translateX(x);
		this.getD().translateX(x);
		refreshDivisions();
	}
	
	public void translateY(double y) throws DivisionException {
		//translate all y-cords by y
		this.getA().translateY(y);
		this.getB().translateY(y);
		this.getC().translateY(y);
		this.getD().translateY(y);
		refreshDivisions();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "A"+getA()+", B"+getB()+", C"+getC()+", D"+getD();
	}

	@SuppressWarnings("null")
	@Override
	public int compareTo(Quadrangle o) {
		// TODO Auto-generated method stub
			try {
				if( this.area() < o.area() )
					return -1;
				else if(this.area() == o.area())
					return 0;
				else
					return 1;
			} catch (DivisionException | TriangleException e) {
				e.printStackTrace();
			}
			return (Integer) null;
			
	
	}
}


