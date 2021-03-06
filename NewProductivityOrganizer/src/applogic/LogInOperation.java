package applogic;

/**
 * login operation uses login Strategy interface in order to allow user to log in!
 *
 */
public class LogInOperation {
	private LogInStrategy logInStrategy;
	private int userId;
	/**
	 * Initialize the LogInOperation
	 * @param logInStrategy
	 * @param userId
	 */
	public LogInOperation(LogInStrategy logInStrategy, int userId) {
		this.logInStrategy = logInStrategy;
		this.userId = userId;
	}
	
	/**
	 * Call the interface after user logged in
	 * @param userId
	 */
	public String CallInterfaceAfterLoggedIn() {
		return logInStrategy.CallInterfaceAfterLoggedIn(userId);
	}
}
