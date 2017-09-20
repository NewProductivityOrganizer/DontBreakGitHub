package applogic;

/**
 * Class implements login strategy for supervisors
 */
public class SupervisorLogIn implements LogInStrategy {

	@Override
	public void CallInterfaceAfterLoggedIn(int userId) {
		System.out.println("Supervisor  " + userId);
	}

}
