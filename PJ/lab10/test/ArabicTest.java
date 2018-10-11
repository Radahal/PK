import static org.junit.Assert.*;


import org.junit.Test;


public class ArabicTest 
{
	
	@Test
	
	public void testItConvertsToArabic() 
	{
		
		Arabic arabic = new Arabic();
		
		assertEquals(1, arabic.toArabic("1"));

	}
	
	

	@Test
	
	public void testItConvertsFromArabic()
	{
		
		Arabic arabic = new Arabic();
		
		assertEquals("1", arabic.fromArabic(1));
	
	} 
	
	

	@Test(expected=IllegalArgumentException.class)
	
	public void testItThrowsExceptionOnInvalidArabicString()
	{
		
		Arabic arabic = new Arabic();
		
		arabic.toArabic("test");
	
	}

}
