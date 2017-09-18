package applogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Supervisor account is one of the classes within AccountFactory
 */
public class SupervisorAccount implements Account{

	private String userIdentity = "Supervisor";
	private String accountName;
	private String username;
	private String password;
	
	public SupervisorAccount() {
	}
	
	/**
	 * creates supervisor account
	 * @param employeeName
	 */
	public void createAccount(String employeeName, String inputUsername, String inputPassword) {
		accountName = employeeName;
		username = inputUsername;
		password = inputPassword;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return accountName
	 */
	public String getName() {
		return accountName;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return userIdentity
	 */
	public String getUserIdentity() {
		return userIdentity;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	
	public void deleteAccount() {
		
	}	
  }
	
