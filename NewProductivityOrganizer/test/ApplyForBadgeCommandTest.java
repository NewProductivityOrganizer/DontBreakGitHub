import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Tests for ApplyCommandTest which test for a badge status
 * null should return true because it has to be an empty status which is then changed to in progress
 * a status that's already set to something should return false
 * 
 * */
public class ApplyForBadgeCommandTest {

	@Test
	public void testForValidNullValue() {

		Badge updation = new Badge("Updation","Upgrade firmware on a printer",null);
		ApplyForBadgeCommand testRun = new ApplyForBadgeCommand(updation);

		assertTrue(testRun.isNull());
	}
	@Test
	public void testForInvalidNullValue() {

		Badge updation = new Badge("Updation","Upgrade firmware on a printer","Is in progress");
		ApplyForBadgeCommand testRun = new ApplyForBadgeCommand(updation);

		assertFalse(testRun.isNull());
	}

}