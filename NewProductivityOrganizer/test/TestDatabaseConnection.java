import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import applogic.Badge;
import applogic.DatabaseBuilder;
import applogic.ProductivityIncentivizer;
import applogic.StudentWorkerFuncs;
import applogic.SupervisorFuncs;

public class TestDatabaseConnection {
	DatabaseBuilder setup = new DatabaseBuilder();

	@Test
	public void TestGetBadgeDictionary() {
		setup.dropDatabase();
		setup.setupDatabase();
		SupervisorFuncs test = new SupervisorFuncs(2);
		HashMap<Integer,Badge> badgeDictionary = test.getBadgeDictionary("Completed");
		assertTrue(!badgeDictionary.isEmpty());
	}
	
	@Test
	public void TestGetBadStudent() {
		SupervisorFuncs test = new SupervisorFuncs(2);
		List<Integer> badStudent = test.getBadStudentWorker(2);
		assertTrue(!badStudent.isEmpty());
	}
	
	@Test
	public void TestHalfPointAvg() {
		SupervisorFuncs test = new SupervisorFuncs(2);
		int avg = test.GetHalfAvg();
		assertEquals(2,avg);
	}
	
	@Test
	public void TestGetBadgeList() {
		StudentWorkerFuncs test = new StudentWorkerFuncs(1);
		String statement = "SELECT BadgeID, BadgeName, BadgeDescription From Badge";
		List<Badge> badgeList = test.getBadgeInfo(statement);
		assertTrue(!badgeList.isEmpty());
	}

}
