import static org.junit.Assert.*;

import org.junit.Test;

import applogic.StudentWorkerAccount;
import applogic.SupervisorAccount;

/**
 * simple tests to check that create accounts for both student workers and supervisors works
 * checking that the getter methods work
 * @author sophiemittelstadt
 */
public class CreateAccountTest {

	@Test 
	public void testStudentWorkerCreateAccount() { 
		StudentWorkerAccount accountTest = new StudentWorkerAccount();
		accountTest.createAccount("James", "", "");
		assertEquals(accountTest.getStudentWorkerName(), "James");
	}
	
	@Test 
	public void test2StudentWorkerCreateAccount() { 
		StudentWorkerAccount accountTest = new StudentWorkerAccount();
		accountTest.createAccount("James", "", "");
		assertEquals(accountTest.getUserIdentity(), "Student Worker");
	}
	
	@Test 
	public void testSupervisorCreateAccount() { 
		SupervisorAccount accountTest = new SupervisorAccount();
		accountTest.createAccount("Leah", "", "");
		assertEquals(accountTest.getName(), "Leah");
	}
	
	@Test 
	public void test2SupervisorCreateAccount() { 
		SupervisorAccount accountTest = new SupervisorAccount();
		accountTest.createAccount("Leah", "", "");
		assertEquals(accountTest.getUserIdentity(), "Supervisor");
	}

}
