
public class Converter {

	public String convert(Numeral value1, Numeral value2, String str)  
	{
		if(value1.getClass().getName()==value2.getClass().getName()) 
		{
			switch (value1.getClass().getName()) {
				case "Arabic":
					return "1";
				case "Roman":
					return "I";
				case "Urnfield":
					return "/";
			}
		}
		
		return value2.fromArabic(value1.toArabic(str));
				
	}
	
}
