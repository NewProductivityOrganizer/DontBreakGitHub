package applogic;
import java.util.ArrayList;
import java.util.Scanner;

import graphicalUserInterface.GraphicalUserInterface;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Productivity Incentivizer is a program that helps foster student worker productivity at the 
 * ITS Solutions Center!
 * @author Sophie Mittelstadt, Stan Zhang, Palesa Mokoena, Nadia Banks
 */
public class ProductivityIncentivizer {
	
	private ArrayList<Badge> badges = new ArrayList<Badge>(); //used to store badges
	public static final String PORT_NUMBER = "8889";
	
	public static final int UPDATION = 0; 
	public static final int THE_XANDER = 1; 
	public static final int HUMANITARIAN = 2; 
    public static final int SEVEN_LEAGUE_BOOTS = 3;
	public static final int  ITS_MANAGER = 4; 
    public static final int NEW_IS_BETTER = 5; 
	
	public ProductivityIncentivizer() {
		//empty constructor
	}
	
	/**
	* This logInHelper() method handles taking in user input for the username and password
	* and returns an ArrayList. At index 0 the username is stored, at index 1 the password is stored.
	* @return ArrayList<String> loginInfo
	*/
	
	/*public ArrayList<String> LogInUserInput(){
		ArrayList<String> loginInfo = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your username");
		String enteredUserName = in.nextLine();
		loginInfo.add(enteredUserName);
		System.out.println("Please enter your password");
	    String enteredPassword = in.nextLine();
	    loginInfo.add(enteredPassword);
	    return loginInfo;
	}*/
	/**
	* This logIn() method uses the username and password from the logInHelper() method and
	* returns the corresponding userID
	* @return int userID
	*/
	
	public void LogIn(String enteredUserName, String enteredPassword){
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			boolean rightLogIn = false;
			while (!rightLogIn) {
				/*ArrayList<String> logInUserInput = LogInUserInput();
				String enteredUserName = logInUserInput.get(0);
				String enteredPassword = logInUserInput.get(1);*/
			    String statement = "SELECT UserID,UserIdentity FROM LogInInformation WHERE UserName = '" + enteredUserName + "' AND UserPassword ='" + enteredPassword + "'";
			    ResultSet rs = stmt.executeQuery(statement);
			    if (rs.next()){
			    		int userId = rs.getInt(1);
			    		String userIdentity = rs.getString(2);
			    		LogInHelper(userIdentity, userId);
			    		rightLogIn = true;
			    } 
			    else{
			    		System.out.println("Wrong username or password");
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
	 }
   }
	
	public void LogInHelper(String userIdentity, int userId) {
		switch(userIdentity) {
		case "StudentWorker":
			LogInOperation studentWorkerLogInOperation = new LogInOperation(new StudentWorkerLogIn(), userId );
			studentWorkerLogInOperation.CallInterfaceAfterLoggedIn(userId);
			break;
		case "Supervisor":
			LogInOperation supervisorLogInOperation = new LogInOperation(new SupervisorLogIn(), userId );
			supervisorLogInOperation.CallInterfaceAfterLoggedIn(userId);
			break;
		}
	}

	
	/**
	 * Prompts user to create account, calls the AccountFactory
	 */
	public void promptUserToCreateAccount(){
		String employeeName;
		String employeeType;
		String supervisorCode = "";
		String username;
		String password;

		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter your name.");
		employeeName = in.nextLine();
		
		System.out.println("Please select student worker (1) or supervisor (2).");
		employeeType = in.nextLine();

		if (employeeType.equals("2")){
			while(!supervisorCode.equals("123")) {
				System.out.println("Please enter supervisor code: ");
				supervisorCode = in.nextLine();	
			}
		}
		
		System.out.println("Select a username:");
		username = in.nextLine();
		
		System.out.println("Create a password:");
		password = in.nextLine();
		
		addAccount(employeeName, employeeType, supervisorCode, username, password);
	}
	
	/**
	 * Adds account, called by promptUserToCreateAccount
	 * @param employeeName
	 * @param employeeType
	 * @param supervisorCode
	 * @param username
	 * @param password
	 * @return boolean used for testing
	 */
	public boolean addAccount(String employeeName, String employeeType, String supervisorCode, String username, String password){
	try (
			// Step 1: Allocate a database "Connection" object
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

			// Step 2: Allocate a "Statement" object in the Connection
			Statement stmt = conn.createStatement();
			) {
		AccountFactory accountFactory = new AccountFactory();
		if (employeeType.equals("1")) {
			//Account account = new StudentWorkerAccount();
			Account account = accountFactory.getAccount(employeeType, employeeName, username, password);	
			String addStudentWorkerAccount = "INSERT INTO LogInInformation (" + account.getUsername() + "," + account.getPassword() +
					"," + "'Student Worker')";
			editDatabase(addStudentWorkerAccount);
			return true;
		}
		else if (employeeType.equals("2")){
			Account account = accountFactory.getAccount(employeeType, employeeName, username, password);
			String addSupervisorAccount = "INSERT INTO LogInInformation (" + account.getUsername() + "," + account.getPassword() +
					"," + "'Supervisor')";
			editDatabase(addSupervisorAccount);
			return true;
		}
		else { //invalid code input
			System.out.println("Invalid input.");
			return false;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return false;
}
	
	
		
	/**
	 * Check the Leading Board
	 * Print out ten student workers with highest points
	 */
	public List<StudentWorker> getLeadingBoard() {
		List<StudentWorker> studentWorkerList = new ArrayList<StudentWorker>();
		try (
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				Statement stmt = conn.createStatement();
				) {
			String getTopTen = "SELECT UserID, ActualName, Point FROM StudentWorkerInformation ORDER BY Point DESC LIMIT 10";
			ResultSet topTen = stmt.executeQuery(getTopTen);
			while (topTen.next()){
				int userID = topTen.getInt(1);
				String actualName = topTen.getString(2);
				int point = topTen.getInt(3);
				StudentWorker studentWorker = new StudentWorker(userID, actualName, point);
				studentWorkerList.add(studentWorker);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return studentWorkerList;
	}
	/**
	 * Display the leading board
	 */
	public void displayLeadingBoard() {
		List<StudentWorker> studentWorkerList = getLeadingBoard();
		for (StudentWorker studentWorker : studentWorkerList) {
			System.out.println(studentWorker);
		}
	}
	
	/**
	 * Print out the start menu for user to choose
	 * @return user's choice
	 */
	public int StartMenu(){
		Scanner startChoice = new Scanner(System.in);
		boolean valid = false;
		int selection = 1;
		while (!valid)
		{
			System.out.println("\nChoose what to do: ");
			System.out.println("1: Log In");
			System.out.println("2: Create Account");
			System.out.print("> ");
			try {
				selection = startChoice.nextInt();

				startChoice.nextLine();
				if ((selection > 0) && (selection <= 2))
				{
					valid = true;
				}
			}
			catch(InputMismatchException ex)
			{
				startChoice.nextLine();
			}
			if (!valid)
			{
			
				System.out.println("Invalid entry -- enter a number between 1 and 2");
			}
		}
		return selection;
	}
	
	/**
	 * operator of the software
	 */
	public void operation() {
		int startChoice = StartMenu();
		switch (startChoice) {
		case 1:
			//LogIn();
			break;
		case 2:
			promptUserToCreateAccount();
			break;
		}
	}
	
	public  void editDatabase(String statement) {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			stmt.execute(statement);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
/**
 * change name of the gui*/
	public static void main(String[] args) {
		GraphicalUserInterface gui = new GraphicalUserInterface();
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface frame = new GraphicalUserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		ProductivityIncentivizer runProgram = new ProductivityIncentivizer();
		//runProgram.ApproveNewBadge();
		runProgram.operation();
		//commented out because it's not completely working right now

	}

}