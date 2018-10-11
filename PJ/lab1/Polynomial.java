
public class Polynomial {
	Solution solution = new Solution();
	double a,b,c;
	public Polynomial(double b, double c) 
	{
		// TODO Auto-generated constructor stub
		this.a =0;
		this.b =b;
		this.c =c;
	}
	public Polynomial(double a, double b, double c) 
	{
		// TODO Auto-generated constructor stub
		this.a =a;
		this.b =b;
		this.c =c;
	}
	
	public void Solve() 
	{
		double delta;
		double x1, x2,extremumX, extremumY;
		double x1_Im, x2_Im;
		if(a==0) 
		{
			//liniowy
			x1 =x2 = -c/b;
			solution.setSolutionType(0);
			solution.setX1(x1);
			solution.setX2(x2);
			solution.setExtremumType(-1);
		} 
		else 
		{
			//kwadratowy
			delta = b*b - 4*a*c;
			if(a>0)
				solution.setExtremumType(0);
			else
				solution.setExtremumType(1);
			extremumX= -b/2*a;
			extremumY= -delta/4*a;
			solution.setExtremumX(extremumX);
			solution.setExtremumY(extremumY);
			
			if(delta<0) 
			{
				//zespolone
				x1 = x2 = -b/2*a;
				x1_Im = -Math.sqrt(-delta)/2*a;
				x2_Im = Math.sqrt(-delta)/2*a;
				solution.setSolutionType(1);
				solution.setX1(x1);
				solution.setX1_Im(x1_Im);
				solution.setX2(x2);
				solution.setX2_Im(x2_Im);
			} 
			else if(delta==0) 
			{
				//jeden podwojny
				x1 =x2 =-b/2*a;
				solution.setSolutionType(2);
				solution.setX1(x1);
				solution.setX2(x2);
			}
			else 
			{
				//rzeczywiste
				x1 = (-b - Math.sqrt(delta))/2*a;
				x2 = (-b + Math.sqrt(delta))/2*a;
				solution.setSolutionType(3);
				solution.setX1(x1);
				solution.setX2(x2);
			}
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string = this.a+"x^2+"+this.b+"x+"+this.c+"\n";
		switch (solution.getSolutionType()) {
		case 0:
			string+="Równanie jest liniowe: x1 = "+solution.getX1()+"\n";
			break;
		case 1:
			string+="Równanie ma dwa pierwiastki zespolone:\n"+"x1 = "+solution.getX1()+" +("+solution.getX1_Im()+"i)\nx2 = "+solution.getX2()+" +("+solution.getX2_Im()+"i)\n";
			break;
		case 2:
			string+="Równanie ma jeden podwójny pierwiastek rzeczywisty: x1 = "+solution.getX1()+"\n";
			break;
		default:
			string+="Równanie ma dwa pierwiastki rzeczywiste:\nx1 = "+solution.getX1()+"\nx2 = "+solution.getX2()+"\n";
			break;
		}
		
		switch (solution.getExtremumType()) {
		case -1:
			string+="Funkcja nie ma ekstremum";
			break;
		case 0:
			string+="Funkcja ma minimum w punkcie: ("+solution.getExtremumX()+", "+solution.getExtremumY()+")\n";
			break;
		default:
			string+="Funkcja ma maximum w punkcie: ("+solution.getExtremumX()+", "+solution.getExtremumY()+")\n";
			break;
		}
			
		return string;
	}
	
}