/**
 * Student worker account is one of the classes within AccountFactory
 */
public class StudentWorkerAccount implements Account{
	
	private String accountName;

	public StudentWorkerAccount() {
	}

	/**
	 * creates student worker account
	 * @param employeeName
	 */
	public void createAccount(String employeeName) {
		accountName = employeeName;
		System.out.println(employeeName);
	}
	
	public void deleteAccount() {
		
	}
}
