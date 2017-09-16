import static org.junit.Assert.*;

import org.junit.Test;

/**
 * simple tests to check that create accounts for both student workers and supervisors works
 * @author sophiemittelstadt
 */
public class CreateAccountTest {

	@Test 
	public void testStudentWorkerCreateAccount() { 
		StudentWorkerAccount accountTest = new StudentWorkerAccount();
		accountTest.createAccount("James");
		assertEquals(accountTest.getStudentWorkerName(), "James");
	}
	
	@Test 
	public void testSupervisorCreateAccount() { 
		SupervisorAccount accountTest = new SupervisorAccount();
		accountTest.createAccount("Leah");
		assertEquals(accountTest.getSupervisorName(), "Leah");
	}

}
