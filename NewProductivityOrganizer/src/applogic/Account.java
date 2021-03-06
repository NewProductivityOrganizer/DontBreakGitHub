package applogic;

/**
 * Interface defines create and delete account, also has getters
 * Used by AccountFactory
 */
public interface Account {
	
	public void createAccount(String employeeName, String username, String password);
	
	public void deleteAccount();
	
	public String getName();
	
	public String getPassword();
	
	public String getUserIdentity();
	
	public String getUsername();
	
}
