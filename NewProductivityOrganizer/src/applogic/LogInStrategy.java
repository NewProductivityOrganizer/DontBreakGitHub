package applogic;

/**
 * Strategy pattern for logging in created in this interface
 */
public interface LogInStrategy {
	/**
	 * Call the interface showing functionalities
	 * @param userId
	 */
	public void CallInterfaceAfterLoggedIn(int userId);
}
