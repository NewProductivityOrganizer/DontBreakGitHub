/**
 * Supervisor account is one of the classes within AccountFactory
 */
public class SupervisorAccount implements Account{

	private String accountName;
	
	public SupervisorAccount() {
		
	}
	
	/**
	 * creates supervisor account
	 * @param employeeName
	 */
	public void createAccount(String employeeName) {
		accountName = employeeName;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return accountName
	 */
	public String getSupervisorName() {
		return accountName;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return userIdentity
	 */
	public String getUserIdentity() {
		return "Supervisor";
	}
	
	public void deleteAccount() {
		
	}
	
}
