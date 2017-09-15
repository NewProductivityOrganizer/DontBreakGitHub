import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Productivity Incentivizer is a program that helps foster student worker productivity at the 
 * ITS Solutions Center
 * @author Sophie Mittelstadt, Stan Zhang, Palesa Mokoena, Nadia Banks
 */
public class ProductivityIncentivizer {
	public static final String PORT_NUMBER = "8889";
	
	public ProductivityIncentivizer() {

	}
	
	/**
	 * Check the Leading Board
	 * Print out ten student workers with highest points
	 */
	public static void checkLeadingBoard() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getTopTen = "SELECT UserID, ActualName, Point FROM StudentWorkerInformation ORDER BY Point DESC LIMIT 10";
			ResultSet topTen = stmt.executeQuery(getTopTen);
			List<StudentWorker> topTenStudentWorkers = new ArrayList<StudentWorker>();
			while (topTen.next()){
				int userID = topTen.getInt(1);
				String actualName = topTen.getString(2);
				int point = topTen.getInt(3);
				StudentWorker studentWorker = new StudentWorker(userID, actualName, point);
				System.out.println(studentWorker);
			}
			BackToMain();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print out the start menu for user to choose
	 * @return user's choice
	 */
	public static int StartMenu(){
		Scanner startChoice = new Scanner(System.in);
		boolean valid = false;
		int selection = 1;
		while (!valid)
		{
			System.out.println("\nChoose what to do: ");
			System.out.println("1: Log In");
			System.out.println("2: Create Account");
			System.out.println("3: Display Leading Board");
			System.out.println("4: Display Badge Uncompleted");
			System.out.print("> ");
			try {
				selection = startChoice.nextInt();

				startChoice.nextLine();
				if ((selection > 0) && (selection <= 4))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				startChoice.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 4");
			}
		}
		return selection;
	}
	
	/**
	 * Display new-created badge to let supervisor to choose to approve
	 * @return badge selected
	 */
	public Badge DisplayNewBadgeInProcess() {
		HashMap<Integer, Badge> badgeDictionary = new HashMap<Integer, Badge>();
		int badgeIndex = 0;
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getNewBadgeInProcess = "SELECT BadgeID, BadgeName, BadgeDescription WHERE BadgeStatus = 'blabla'";
			ResultSet newBadgeInProcess = stmt.executeQuery(getNewBadgeInProcess);
			List<StudentWorker> topTenStudentWorkers = new ArrayList<StudentWorker>();
			while (newBadgeInProcess.next()){
				badgeIndex += 1;
				int badgeID = newBadgeInProcess.getInt(1);
				String badgeName = newBadgeInProcess.getString(2);
				String badgeDescription = newBadgeInProcess.getString(3);
				Badge badge = new Badge(badgeName, badgeDescription);
				badge.setID(badgeID);
				System.out.println( "\n" + badgeIndex +". Badge ID: " + badgeID + "    Badge Name: " + badgeName + "\nBadge Description: " + badgeDescription);
				badgeDictionary.put(badgeIndex, badge);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		Scanner badgeChoice = new Scanner(System.in);
		boolean valid = false;
		int selection = 1;
		while (!valid)
		{
			System.out.println("\nChoose one to approve: ");
			System.out.print("> ");
			try {
				selection = badgeChoice.nextInt();

				badgeChoice.nextLine();
				if ((selection > 0) && (selection <= badgeIndex))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				badgeChoice.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and "+ badgeIndex);
			}
		}
		return badgeDictionary.get(selection);
	}
	/**
	 * Approve student-create new badge and put it into System
	 */
	public void ApproveNewBadge() {
		Badge badge = DisplayNewBadgeInProcess();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String approveNewBadge = "UPDATE Badge SET BadgeStatus = 'Uncompleted' WHERE BadgeID = " + badge.getID();
			stmt.execute(approveNewBadge);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Display uncompleted badge
	 */
	public static void DisplayUncompletedBadge() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getUncompletedBadge = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE BadgeStatus = 'Uncompleted'";
			ResultSet uncompletedBadges = stmt.executeQuery(getUncompletedBadge);
			int badgeIndex = 0;
			while (uncompletedBadges.next()) {
				badgeIndex += 1;
				int badgeID = uncompletedBadges.getInt(1);
				String badgeName = uncompletedBadges.getString(2);
				String badgeDescription = uncompletedBadges.getString(3);
				System.out.println( "\n" + badgeIndex +". Badge ID: " + badgeID + "    Badge Name: " + badgeName + "\nBadge Description: " + badgeDescription);
			}
			BackToMain();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * The method letting user to get back to start menu
	 */
	public static void BackToMain() {
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int selection = 1;
		while (!valid)
		{
			System.out.println("\nType 0 to get back to Main Menu");
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if (selection == 0)
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter 0");
			}
		}
		StartMenu();
	}
	
	/**
	 * operator of the software
	 */
	
	public static void operation() {
		int startChoice = StartMenu();
		switch (startChoice) {
		case 1:
			System.out.println("Bazinga!");
			break;
		case 2:
			System.out.println("Bazinga!");
			break;
		case 3:
			checkLeadingBoard();
			break;
		case 4:
			DisplayUncompletedBadge();
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

		Scanner in = new Scanner(System.in);

		AccountFactory accountFactory = new AccountFactory();
		
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
		
		addAccount(employeeName, employeeType, supervisorCode);
		in.close();

	}
	
	/**
	 * Adds account, called by promptUserToCreateAccount
	 * @param employeeName
	 * @param employeeType
	 * @return boolean used for testing
	 */
	public boolean addAccount(String employeeName, String employeeType, String supervisorCode) {
		AccountFactory accountFactory = new AccountFactory();
		if (employeeType.equals("1")) {
			accountFactory.getAccount(employeeType, employeeName);		
			return true;
		}
		else if (employeeType.equals("2")){
			if (supervisorCode.equals("123")) {
				accountFactory.getAccount(employeeType, employeeName);
				return true;
			}
			else { //invalid code input	
				return false;
			}
		}
		return false;


	}

	public static void main(String[] args) {
		ProductivityIncentivizer runProgram = new ProductivityIncentivizer();
		runProgram.promptUserToCreateAccount();
	}

}