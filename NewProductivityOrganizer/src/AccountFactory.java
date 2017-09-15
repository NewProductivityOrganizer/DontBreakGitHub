/**
 * Factory creates student worker accounts and supervisor accounts
 */
public class AccountFactory {

	public AccountFactory() {
	}
	
	/**
	 * Uses account type to create either a student or supervisor account
	 * @param accountType
	 */
	public void getAccount(String employeeType, String employeeName) {
		if (employeeType.equals("1")) { //student worker
			Account studentWorker = new StudentWorkerAccount();
			studentWorker.createAccount(employeeName);
		}
		else if (employeeType.equals("2")) { //supervisor
			Account supervisor = new SupervisorAccount();
			supervisor.createAccount(employeeName);
		}
	}
}
