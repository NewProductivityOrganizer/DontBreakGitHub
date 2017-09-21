import static org.junit.Assert.*;

import org.junit.Test;

import applogic.DatabaseBuilder;
import applogic.ProductivityIncentivizer;

/**
 * tests login information against an artificially created user with
 * login info: "SW1" , "12345abc" (very bad password!)
 */
public class LogInTest {
	DatabaseBuilder setup = new DatabaseBuilder();
	@Test
	public void LogInStudentWorkerTest() {
		setup.dropDatabase();
		setup.setupDatabase();
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
