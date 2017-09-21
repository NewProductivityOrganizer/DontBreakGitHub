import static org.junit.Assert.*;

import org.junit.Test;

import applogic.ApplyForBadgeCommand;
import applogic.BadgeCommand;
import applogic.CreateBadgeCommand;
import applogic.StudentWorkerAccount;
import applogic.StudentWorkerFuncs;

/**
 * Tests method in student worker function that allowS user to create a new badge
 */
public class CreateNewBadgeTest {
	
	@Test 
	public void testCreateNewBadge() { 
		StudentWorkerFuncs studentWorkerFunctions = new StudentWorkerFuncs(123);
		assertTrue(studentWorkerFunctions.CreateNewBadge("badgeName", "badgeDescription"));
	}
}
