package applogic;

/**
 * login class allows student workers to log in
 * concrete class implementation of login Strategy Interface
 */
public class StudentWorkerLogIn implements LogInStrategy {

	@Override
	public String CallInterfaceAfterLoggedIn(int userId) {
		return "StudentWorker";
	}

}
