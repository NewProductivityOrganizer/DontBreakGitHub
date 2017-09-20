package applogic;

/**
 * Class implements login strategy for supervisors
 */
public class SupervisorLogIn implements LogInStrategy {

	@Override
	public String CallInterfaceAfterLoggedIn(int userId) {
		return "Supervisor";
	}

}
