import static org.junit.Assert.*;
import org.junit.Test;

import applogic.ProductivityIncentivizer;

/**
 * tests for valid and invalid account creation
 * @author sophiemittelstadt
 */
public class AddAccountTest {
	
	@Test 
	public void testStudentWorkerAccount() { 
		ProductivityIncentivizer testRun = new ProductivityIncentivizer();
		assertTrue(testRun.addAccount("Joe Shmo", "1", "", "", ""));
	}
	
	@Test
	public void testSupervisorAccount() {
		ProductivityIncentivizer testRun = new ProductivityIncentivizer();
		assertTrue(testRun.addAccount("Bossy Boss", "2", "123", "", ""));
	}
	
	@Test
	public void testAccountWithInvalidAccountType() {
		ProductivityIncentivizer testRun = new ProductivityIncentivizer();
		assertFalse(testRun.addAccount("Funky Friend", "0", "123", "", ""));
	}
}
