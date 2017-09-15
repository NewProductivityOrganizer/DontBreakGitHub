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
		System.out.println(employeeName);
	}
	
	public void deleteAccount() {
		
	}
	
}
