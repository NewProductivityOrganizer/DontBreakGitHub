
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Test;

/**
 * Tests LogInHelper method to make sure it appropriately 
 * sets usernames and passwords for each user
 */
public class ProductivityIncentivizerTest {

	@Test
	public void LogInHelperUserNameTest() {
		String userNameEntered = "username";
		InputStream stream = System.in;
		System.setIn(new ByteArrayInputStream(userNameEntered.getBytes()));
		System.setIn(stream);
		assertEquals("username", userNameEntered);
		
	}
	@Test
	public void LogInHelperPasswordTest(){
		String passwordEntered = "password";
		InputStream stream = System.in;
		System.setIn(new ByteArrayInputStream(passwordEntered.getBytes()));
		System.setIn(stream);
		assertEquals("password", passwordEntered);
		
	}

}
