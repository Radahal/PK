import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RomanTest 
{
	private String romanNumber;
    private int arabicNumber;
	
    /**
     * @param romanNumber
     * @param arabicNumber
     */
    public RomanTest(String romanNumber, int arabicNumber) 
    {
        this.romanNumber = romanNumber;
        this.arabicNumber = arabicNumber;
    }
	
	@Parameterized.Parameters
	public static Collection<Object[]> romanNumbersProvider() {
	      return Arrays.asList(new Object[][] {
	         { "XXVI", 26 },
	         { "LXXVI", 76 },
	         { "DCLXVI", 666},
	         { "I", 1},
	         { "VI", 6},
	         { "XII", 12 }
	      });
	}
	
	@Test
	public void testConvertsArabicNumberToRomanNumber()
	{
		Roman roman = new Roman();
		assertEquals(romanNumber, roman.fromArabic(arabicNumber));
	}
	
	@Test
	public void testConvertsRomanNumberToArabic()
    {
		Roman roman = new Roman();
		assertEquals(arabicNumber, roman.toArabic(romanNumber));
    }
	
	@Test(expected=IllegalArgumentException.class)
	public void testRangeOfNumbers()
	{
		Roman roman = new Roman();
		assertEquals("MMMCCCXXXVIII", roman.fromArabic(3338));
	}
}
