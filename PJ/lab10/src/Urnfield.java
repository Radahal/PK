
public class Urnfield implements Numeral {
	public int toArabic(String value) 
	{
		int arabicValue = 0;
		for(int i=0; i<value.length(); i++) 
		{
			switch(value.charAt(i))
			{
				case '/':
					arabicValue+=1;
					break;
				case '\\':
					arabicValue+=5;
					break;
			}
		}
		return arabicValue;
	}
	
	public String fromArabic(int value) 
	{
		String urnfieldValue = "";
		if(value<1 || value>29)
			throw new IllegalArgumentException();
		else
		{
			int tmp1 = value%5;
			int tmp2 = value/5;
			
			for(int i=0;i<tmp1;i++)
				urnfieldValue+="/";
			
			for(int i=0;i<tmp2;i++)
				urnfieldValue+="\\";
			
		}
		return urnfieldValue;
	}
}
