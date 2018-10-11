import java.util.Stack;

public class ReversPolishNotation {
	protected Stack stack = new Stack();
	protected String result = "";
	protected String statement = "";
	protected boolean sign = true;
	
	
	@SuppressWarnings("unchecked")
	public ReversPolishNotation(String expression) {
		this.statement=statement;
		this.statement.replaceAll(" ", "");
		makeRPNForm();
	}
	
	public void setNewStatement(String statement) {
		stack.clear();
		this.statement=statement;
		this.statement.replaceAll(" ", "");
		makeRPNForm();
	}
	
	public void makeRPNForm() {
		for (int i=0; i<statement.length(); i++) {
			if( statement.charAt(i) == '(' ) {
				stack.push("(");
				sign = true;
				result += " ";
			} else if (statement.charAt(i) == ')') {
				result += " " + getFromStackUntilBracket();
				sign = false;
			} else if ((statement.charAt(i) == '+' || statement.charAt(i) == '-' || statement.charAt(i) == '*' || statement.charAt(i) == '/') && !sign) {
				result += " " + getFromStack(statement.substring(i, i+1));
				sign = true;
			} else {
				if (sign && statement.charAt(i) == '-') {
					result += " ";
				}
				result += statement.charAt(i);
				sign = false;              
			}
		}
		result += getAllFromStack();
		result = result.replaceAll("  ", " ");
	}
	
	private String getFromStackUntilBracket() {
		String result = "";
		String c = null;
		if (!stack.empty()) {
			c = (String) stack.pop();
			while (!c.equals("(")){
				result = result + " " + c;
				if (stack.empty()) break;
				c = (String) stack.pop();
			}
		}
		if (result.length() > 0) {
			result = " " + result;
		}
		return result;
	}
	
	private String getFromStack(String operator) {
		String result = "";
		String c = null;
		if (!stack.empty()) {
			c = (String) stack.pop();
			while (((operator.equals("+") || operator.equals("-")) && !c.equals("(")) || ((operator.equals("/") || operator.equals("*")) && (c.equals("/") || c.equals("*")))){
				result += " " + c;
				if (stack.empty()) 
					break;
				c = (String) stack.pop();
			}
			stack.push(c);
		}
		stack.push(operator);
		 
		return result;
	}
	
	private String getAllFromStack() {
		String result = "";
		String c = null;
		while (!stack.empty()){
			c = (String) stack.pop();
			result += " " + c;
		}
		return result;
	}  
	
	public String getRPNForm() {
		return result;
	}
	
	public double countRPNForm() {
		String wejscie = result+" =";
		Stack<Double> stos = new Stack<Double>();
		double a=0;//przechowuje dane ze stosu
		double b=0;//przechowuje dane ze stosu
		double w=0;//wynik operacji arytmetycznej
		
		String buduj="";
		String spacja=" ";
		char sp=' ';
		
		int licznik=0;
		

		//1: Czytaj el z we
		do {
			char myChar=wejscie.charAt(licznik);
			//2: Jeśli el nie jest liczbą, to idź do kroku 5
			if(myChar=='+' || myChar == '-' || myChar == '*' || myChar == '/') {
				if(!stos.empty()) {
					//: Pobierz ze stosu dwie liczby a i b
					b= stos.pop();
					a= stos.pop();
					//: Wykonaj nad liczbami a i b operację określoną przez el i umieść wynik w w
					if(myChar=='+'){
						w=a+b;
					} else if(myChar=='-'){
						w=a-b;
					} else if(myChar=='*'){
						w=a*b;
					} else if(myChar=='/'){
						w=a/b;
					}
					//3:  Umieść w na stosie
					stos.push(w);
				}
			//4:    Umieść el na stosie
			} else if(myChar == sp) {
				if(buduj.compareTo("")!=0) {
					double tmp = Double.parseDouble(buduj);
					stos.push(tmp);
					buduj="";
				}
			//5:     Jeśli el jest znakiem '=', to idź do kroku 10
			} else if(myChar=='=') {
				if(!stos.empty()){
					//10:    Prześlij na wyjście zawartość wierzchołka stosu
					w= stos.pop();
					break;
				}
			}
			//buduj  liczbe
			else if(myChar>='0' && myChar <='9' || myChar=='.') {
				buduj+=myChar;
			}
			licznik++;
		//4: Idź do kroku 1
		} while(licznik<wejscie.length());
	
		return w;
		
	}
	
}
