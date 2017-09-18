import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import applogic.Badge;
import applogic.ProductivityIncentivizer;

public class TestDatabaseConnection {

	@Test
	public void TestGetBadgeDictionary() {
		ProductivityIncentivizer test = new ProductivityIncentivizer();
		HashMap<Integer,Badge> badgeDictionary = test.getBadgeDictionary();
		assertTrue(!badgeDictionary.isEmpty());
	}

}
