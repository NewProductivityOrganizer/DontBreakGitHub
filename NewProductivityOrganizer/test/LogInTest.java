import static org.junit.Assert.*;

import org.junit.Test;

import applogic.ProductivityIncentivizer;

public class LogInTest {

	@Test
	public void LogInStudentWorkerTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertEquals("StudentWorker",test.LogIn("SW1", "12345abc"));
	}
	
	@Test
	public void LogInWrongPasswordTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertEquals("Fail",test.LogIn("SW1", "12345bc"));
	}
	
	@Test
	public void LogInWrongUserNameTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertEquals("Fail",test.LogIn("SW3", "12345abc"));
	}
	
	@Test
	public void LogInSupervisorTest() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		assertEquals("Supervisor",test.LogIn("BOSS1", "12345abc"));
	}


}
