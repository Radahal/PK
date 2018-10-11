import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UrnfieldTest 
{
	private String urnfieldNumber;
    private int arabicNumber;
    
    /**
     * @param urnfieldNumber
     * @param arabicNumber
     */
    public UrnfieldTest (String urnfieldNumber, int arabicNumber) 
    {
        this.urnfieldNumber = urnfieldNumber;
        this.arabicNumber = arabicNumber;
    }
	
	@Parameterized.Parameters
	public static Collection<Object[]> urnfieldNumbersProvider() 
	{
		  //watch out for string escaping
	      return Arrays.asList(new Object[][] {
	         { "/", 1 }, // print /
	         { "///", 3 }, // print ///
	         { "/\\" , 6}, // print /\
	         { "//\\\\", 12}, // print //\\
	         { "//\\\\\\\\", 22}, // print //\\\\
	         { "////\\\\\\\\\\", 29 } // print ////\\\\\
	      });
	}
	
	@Test
	public void testConvertsArabicNumberToUrnfieldNumber() 
	{
		Urnfield urnfield = new Urnfield();
		assertEquals(urnfieldNumber, urnfield.fromArabic(arabicNumber));
	}
	
	@Test
	public void testConvertsUrnfieldNumberToArabic() 
	{
		Urnfield urnfield = new Urnfield();
		assertEquals(arabicNumber, urnfield.toArabic(urnfieldNumber));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRangeOfNumbers()
	{
		Urnfield urnfield = new Urnfield();
		assertEquals("//\\\\", urnfield.fromArabic(31));
	}
}
