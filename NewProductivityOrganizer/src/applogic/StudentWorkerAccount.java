package applogic;
/**
 * Student worker account is one of the classes within AccountFactory
 */
public class StudentWorkerAccount implements Account{
	
	private String userIdentity = "Student Worker";
	private String accountName;
	private String username;
	private String password;

	public StudentWorkerAccount() {
	}

	/**
	 * creates student worker account
	 * @param employeeName
	 */
	public void createAccount(String employeeName, String inputUsername, String inputPassword) {
		accountName = employeeName;
		username = inputUsername;
		password = inputPassword;
	}
	
	/**
	 * used when adding a student worker account into the database
	 * @return accountName
	 */
	public String getStudentWorkerName() {
		return accountName;
	}
	
	/**
	 * used when adding a student worker account into the database
	 * @return userIdentity
	 */
	public String getUserIdentity() {
		return userIdentity;
	}
	
	/**
	 * used when adding a student worker account into the database
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * used when adding a student worker account into the database
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	public void deleteAccount() {
		
	}

	public String getName() {
		return accountName;
	}
}
