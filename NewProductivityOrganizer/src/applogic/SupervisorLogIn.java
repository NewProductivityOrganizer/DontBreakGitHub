package applogic;

public class SupervisorLogIn implements LogInStrategy {

	@Override
	public void CallInterfaceAfterLoggedIn(int userId) {
		// TODO Auto-generated method stub
		System.out.println("Supervisor  " + userId);
	}

}
