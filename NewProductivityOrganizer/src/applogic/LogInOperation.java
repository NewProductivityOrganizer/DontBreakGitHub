
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
	public void CallInterfaceAfterLoggedIn(int userId) {
		logInStrategy.CallInterfaceAfterLoggedIn(userId);
	}
}
