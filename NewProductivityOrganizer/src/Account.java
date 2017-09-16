
/**
 * Interface defines create and delete account
 * Used by AccountFactory
 */
public interface Account {
	
	public void createAccount(String employeeName, String username, String password);
	
	public void deleteAccount();
	
}
