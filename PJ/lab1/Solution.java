
public class Solution {
	private int solutionType, extremumType;	// 0 - liniowe, 1-kwadratowe rzeczywiste podwojne, 2 - kwadratowe rzeczywiste, 3 - kwadratowe zespolone
	private double x1, x1_Im, x2, x2_Im, extremumX, extremumY;
	
	public int getSolutionType() {
		return solutionType;
	}
	public void setSolutionType(int solutionType) {
		this.solutionType = solutionType;
	}
	public int getExtremumType() {
		return extremumType;
	}
	public void setExtremumType(int extremumType) {
		this.extremumType = extremumType;
	}
	public double getExtremumX() {
		return extremumX;
	}
	public void setExtremumX(double extremumX) {
		this.extremumX = extremumX;
	}
	public double getExtremumY() {
		return extremumY;
	}
	public void setExtremumY(double extremumY) {
		this.extremumY = extremumY;
	}
	
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getX1_Im() {
		return x1_Im;
	}
	public void setX1_Im(double x1_Im) {
		this.x1_Im = x1_Im;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getX2_Im() {
		return x2_Im;
	}
	public void setX2_Im(double x2_Im) {
		this.x2_Im = x2_Im;
	}
	
}

