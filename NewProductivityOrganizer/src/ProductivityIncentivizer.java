import java.util.ArrayList;
import java.util.Scanner;
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
 * ITS Solutions Center
 * @author Sophie Mittelstadt, Stan Zhang, Palesa Mokoena, Nadia Banks
 */
public class ProductivityIncentivizer {
	
	ArrayList<Badge> badges; //used to store badges
	public static final String PORT_NUMBER = "8889";
	
	public ProductivityIncentivizer() {
		//empty constructor
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
	
	/**
	 * @return arrayList of badges
	 * This method is not a permanent component of our program. For the sake of the first iteration and testing
	 * our other methods, this method creates an ArrayList of badges that can be accessed and printed. In
	 * our actual program, this information will come from the database.
	 */
	public ArrayList<Badge> makeArtificialBadgeList(){

		Badge updation = new Badge("Updation", "Update firmware on a printer");
		Badge theXander = new Badge("The Xander", "Develop mobile app for CC");
		Badge humanitarian = new Badge("Humanitarian", "Develop set of card for ITS");
		Badge sevenLeagueBoots = new Badge("Seven League Boots", "Help someone in facilities or HVAC");
		Badge itsManager = new Badge("ITS Manager", "Talk to a sales vendor for at least 5 minutes");
		Badge newIsBetter = new Badge("New is Better", "Upgrade the OS on a computer");
		Badge doubleVision = new Badge("Double Vision", "Hook up dual monitors");
		Badge prankster = new Badge("Prankster", "Prank the Help Desk team leader");
		Badge noCapes = new Badge("No Capes", "Make and wear an ITS cape");
		Badge intern = new Badge("Intern", "Work a full 8 hour shift");
		Badge itsQuiet = new Badge("It's quiet...too quiet", "Work a weekend shift");
		Badge breakTheFourthWall = new Badge("Break the 4th Wall", "Make a video segment of the Help Desk similar to The Office");

		badges.add(updation);
		badges.add(theXander);
		badges.add(humanitarian);
		badges.add(sevenLeagueBoots);
		badges.add(itsManager);
		badges.add(newIsBetter);
		badges.add(doubleVision);
		badges.add(prankster);
		badges.add(noCapes);
		badges.add(intern);
		badges.add(itsQuiet);
		badges.add(breakTheFourthWall);

		return badges;

	}
		
	/**
	 * Check the Leading Board
	 * Print out ten student workers with highest points
	 */
	public void checkLeadingBoard() {
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
	public int StartMenu(){
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
				badge.setBadgeId(badgeID);
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
			String approveNewBadge = "UPDATE Badge SET BadgeStatus = 'Uncompleted' WHERE BadgeID = " + badge.getBadgeId();
			stmt.execute(approveNewBadge);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Display uncompleted badge
	 */
	public void DisplayUncompletedBadge() {
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
	public void BackToMain() {
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
	public void operation() {
		int startChoice = StartMenu();
		switch (startChoice) {
		case 1:
			System.out.println("Bazinga!");
			break;
		case 2:
			promptUserToCreateAccount();
			break;
		case 3:
			checkLeadingBoard();
			break;
		case 4:
			DisplayUncompletedBadge();
			break;
		}
	}

	

	public static void main(String[] args) {
		ProductivityIncentivizer runProgram = new ProductivityIncentivizer();
		runProgram.operation();
	}

}