
public class Calculations {

	public static void main(String[] args) throws Exception {
		Vector v1, v2, v3, v4;
		Matrix m1, m2;
		double[][] matrixArray = {
				{0,0,0},
				{1,2,3},
				{2,4,6}
				};
		
		v1 = new Vector(6, 1.0);
		v2 = new Vector(6, 2.0);
		v3 = new Vector(3, 4.0);
		
		m1 = new Matrix(3, 6.0);
		m2 = new Matrix(matrixArray);
		
		System.out.println("Vector"+v1.getId() + ": "+v1+"\n"
				+ "Vector"+v2.getId() + ": "+v2+"\n"
				+ "Vector"+v3.getId() + ": "+v3+"\n");
		
		v1.addVector(v2);
		System.out.println("Vector"+v1.getId() + " + Vector"+v2.getId() +": " + v1 +"\n");
		v2.setElementWithLabel(3, 10);
		
		System.out.println("Skalar Vector"+v1.getId()+" i Vector"+v2.getId()+": "+v1.scalarProduct(v2));
		
		System.out.println("Matrix"+m1.getId() + ": "+m1+"\n");
		System.out.println("Matrix"+m2.getId() + ": "+m2+"\n");
		m1.addMatrix(m2);
		System.out.println("Matrix"+m1.getId() +" + Matrix"+m2.getId()+":" + m1 +"\n");
		m2.setElementWithLabel(2, 1, 7);
		v4 = m2.multiplyMatrix(v3);
		System.out.println("Vector"+v4.getId()+" utworzony po przemnozeniu Vector"+v3.getId()+" przez Matrix"+m2.getId()+": " + v4);
	}

}
