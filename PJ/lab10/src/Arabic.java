
public class Arabic implements Numeral {

	public int toArabic(String value) throws IllegalArgumentException 
	{
		int arabicValue = 0;
		try
		{
			arabicValue = Integer.parseInt(value);
		}
		catch (NumberFormatException  e) {
			// TODO: handle exception
			throw new IllegalArgumentException();
		}
		
		
		return arabicValue;
	}
	
	public String fromArabic(int value) 
	{
		
		String val = Integer.toString(value);
		return val;
	}
}
