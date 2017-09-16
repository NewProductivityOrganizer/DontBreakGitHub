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
	}
	
	/**
	 * used when adding a student worker account into the database
	 * @return accountName
	 */
	public String getStudentWorkerName() {
		return accountName;
	}
	
	public void deleteAccount() {
		
	}
}
