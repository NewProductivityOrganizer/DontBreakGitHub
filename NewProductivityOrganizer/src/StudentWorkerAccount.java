
public class StudentWorkerAccount implements Account{
	
	private String accountName;

	public StudentWorkerAccount() {
		
	}

	public void createAccount(String employeeName) {
		accountName = employeeName;
		System.out.println(employeeName);
	}
	
	public void deleteAccount() {
		
	}
}
