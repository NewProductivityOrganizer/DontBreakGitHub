
public class StudentWorkerLogIn implements LogInStrategy {

	@Override
	public void CallInterfaceAfterLoggedIn(int userId) {
		// TODO Auto-generated method stub
		System.out.println("StudentWorker" + userId);
	}

}
