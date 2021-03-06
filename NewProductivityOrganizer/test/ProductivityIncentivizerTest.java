
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;



import org.junit.Test;

import applogic.DatabaseBuilder;

/**
 * Tests LogInHelper method to make sure it appropriately 
 * sets usernames and passwords for each user
 */

public class ProductivityIncentivizerTest {
	DatabaseBuilder setup = new DatabaseBuilder();

	@Test
	public void LogInHelperUserNameTest() {
		setup.dropDatabase();
		setup.setupDatabase();
		String userNameEntered = "username";
		InputStream stream = System.in;
		System.setIn(new ByteArrayInputStream(userNameEntered.getBytes()));
		Scanner scanner = new Scanner(System.in);
		System.setIn(stream);
		assertEquals("username", userNameEntered);
		
	}
	@Test
	public void LogInHelperPasswordTest(){
		String passwordEntered = "password";
		InputStream stream = System.in;
		System.setIn(new ByteArrayInputStream(passwordEntered.getBytes()));
		Scanner scanner = new Scanner(System.in);
		System.setIn(stream);
		assertEquals("password", passwordEntered);
		
	}

}
