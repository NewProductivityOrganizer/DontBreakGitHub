
public class SupervisorAccount implements Account{

	private String accountName;
	
	public SupervisorAccount() {
		
	}
	
	public void createAccount(String employeeName) {
		accountName = employeeName;
		System.out.println(employeeName);
	}
	
	public void deleteAccount() {
		
	}
	
}
