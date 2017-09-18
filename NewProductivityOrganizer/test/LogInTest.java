import static org.junit.Assert.*;

import org.junit.Test;

import applogic.ProductivityIncentivizer;

public class LogInTest {

	@Test
	public void LogInSuccessTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertTrue(test.LogIn("SW1", "12345abc"));
	}
	
	@Test
	public void LogInWrongPasswordTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertFalse(test.LogIn("SW1", "12345bc"));
	}
	
	@Test
	public void LogInWrongUserNameTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertFalse(test.LogIn("SW3", "12345abc"));
	}


}
