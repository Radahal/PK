
public class Roman implements Numeral {

	public int toArabic(String value) 
	{
		int arabicValue = 0;
		for(int i=0; i<value.length(); i++) {
			switch(value.charAt(i))
			{
				case 'M':
					arabicValue+=1000;
					break;
				case 'D':
					arabicValue+=500;
					break;
				case 'C':
					arabicValue+=100;
					break;
				case 'L':
					arabicValue+=50;
					break;
				case 'X':
					arabicValue+=10;
					break;
				case 'V':
					arabicValue+=5;
					break;
				case 'I':
					arabicValue+=1;
					break;
			}
		}
		
		return arabicValue;
	}
	
	public String fromArabic(int value) 
	{
		String romanValue = "";
		
		if(value<1 || value>3000)
			throw new  IllegalArgumentException();
		else
		{
		
			int tmp = value;
			System.out.println(tmp);
			int m = tmp/1000;
			tmp-=m*1000;
			int d = tmp/500;
			tmp-=d*500;
			int c = tmp/100;
			tmp-=c*100;
			int l = tmp/50;
			tmp-=l*50;
			int x = tmp/10;
			tmp-=x*10;
			int v = tmp/5;
			tmp-=v*5;
			
			for(int i = 0; i<m;i++)
				romanValue+="M";
			
			for(int i = 0; i<d;i++)
				romanValue+="D";
			
			for(int i = 0; i<c;i++)
				romanValue+="C";
			
			for(int i = 0; i<l;i++)
				romanValue+="L";
			
			for(int i = 0; i<x;i++)
				romanValue+="X";
			
			for(int i = 0; i<v;i++)
				romanValue+="V";
			
			
			for(int i = 0; i<tmp;i++)
				romanValue+="I";
			
			
		}
		return romanValue;
	}
}
