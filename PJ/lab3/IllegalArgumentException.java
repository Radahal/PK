
public class IllegalArgumentException extends Exception {
	public IllegalArgumentException(String info) {
		super("\n"+info+"\n");
	}
}
