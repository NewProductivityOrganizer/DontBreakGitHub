package applogic;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

/**
 * 
 */
public class SupervisorFuncs {
	private static final String PORT_NUMBER = "8889";
	private int userId;
	
	public SupervisorFuncs(int userId) {
		this.userId = userId;
	}
	
	/** 
	 * The getTopTen method returns a list of the top ten student workers with
	 * the highest number of points
	 * @return studentWorkerList
	 */
	public List<Integer> getTopTen() {
		List<Integer> studentWorkerList = new ArrayList<Integer>();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getTopTen = "SELECT UserID FROM StudentWorkerInformation ORDER BY Point DESC LIMIT 10";
			ResultSet topTen = stmt.executeQuery(getTopTen);
			while (topTen.next()){
				int studentUserID = topTen.getInt(1);
				studentWorkerList.add(studentUserID);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return studentWorkerList;
	}
	
	/**
	 * The GetHalfAvg method finds half of the average number of points
	 * student workers have
	 * @return pointAverage/2
	 */	
	public int GetHalfAvg() {
		int pointAverage = 0;
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getAvg = "SELECT AVG(Point) FROM StudentWorker";
			ResultSet avg = stmt.executeQuery(getAvg);
			if (avg.next()) {
				pointAverage = avg.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return (pointAverage/2);
	}
	
	/**
	 * the getBadStudentWorker method returns a list of student workers whose
	 * Points are below a given threshold 
	 * @param threshold
	 * @return badStudentWorker
	 */
	
	public List<Integer> getBadStudentWorker(int threshold){
		List<Integer> badStudentWorker = new ArrayList<Integer>();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getStudentWorker = "SELECT UserID FROM StudentWorker WHERE Point < " + threshold;
			ResultSet badStudentWorkerId = stmt.executeQuery(getStudentWorker);
			while (badStudentWorkerId.next()) {
				int studentUserID = badStudentWorkerId.getInt(1);
				badStudentWorker.add(studentUserID);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return badStudentWorker;
	}
	
	/**
	 * 
	 */
	public void AutoAssess() {
		GiveAwards();
		GiveWarnings();
	}
	
	/**
	 * The GiveAwards method is responsible for incrementing the number of Awards a
	 * student worker has by +1
	 */
	public void GiveAwards() {
		List<Integer> topTenStudentWorker = getTopTen();
		for (int topTenUserID : topTenStudentWorker) {
			String giveAward = "UPDATE StudentWorker SET Awards = Award + 1 WHERE UserID = " + topTenUserID;
			editDatabase(giveAward);
		}
	}
	
	/** 
	 * The GiveWarnings method is responsible for incrementing the number of Warnings a 
	 * student worker has by +1
	 */
	public void GiveWarnings() {
		int threshold = GetHalfAvg();
		List<Integer> badStudentWorker = getBadStudentWorker(threshold);
		for (int badStudentWorkerID : badStudentWorker) {
			String giveWarning = "UPDATE StudentWorker SET Warnings = Warnings + 1 WHERE UserID = " + badStudentWorkerID;
			editDatabase(giveWarning);
		}
	}
	/**
	 * The editDatabase method takes in a statement and executes it in MySql
	 * @param statement
	 */
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
	 * Display new-created badge to let supervisor to choose to approve
	 * @return badge selected
	 */	
	public Badge DisplayNewBadgeInProcess() {
		HashMap<Integer, Badge> badgeDictionary = getBadgeDictionary("Waiting");
		if (!badgeDictionary.isEmpty()) {
			int badgeIndex = badgeDictionary.size();
			int selection = getBadgeChoice(badgeIndex);
			return badgeDictionary.get(selection);
		}
		else {
			Badge faultBadge = new Badge ("0","0");
			return faultBadge;
		}
		
	}
	
	/**
	 * The getBadgeDictionary method is used to get all the unapproved new badges
	 *  in the database and put them in a dictionary
	 * @return dictionary of unapproved new badges
	 */
	public HashMap<Integer, Badge> getBadgeDictionary(String badgeStatus){
		HashMap<Integer, Badge> badgeDictionary = new HashMap<Integer, Badge>();
		int badgeIndex = 0;
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getBadges = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE BadgeStatus = '" + badgeStatus +"'" ;
			ResultSet badges = stmt.executeQuery(getBadges);
			while (badges.next()){
				badgeIndex += 1;
				int badgeID = badges.getInt(1);
				String badgeName = badges.getString(2);
				String badgeDescription =badges.getString(3);
				Badge badge = new Badge(badgeName, badgeDescription);
				badge.setBadgeId(badgeID);
				System.out.println( "\n" + badgeIndex +". Badge ID: " + badgeID + "    Badge Name: " + badgeName + "\nBadge Description: " + badgeDescription);
				badgeDictionary.put(badgeIndex, badge);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return badgeDictionary;
	}
	
	/**
	 * The getBadgeChoice method is responsible for taking the user's choice of badge
	 * @param size of badge dictionary
	 * @return badge chosen
	 */
	public int getBadgeChoice(int badgeIndex) {
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
		return selection;
	}
	
	/**
	 * The ApproveNewBadge method approves student-created new badges and puts 
	 * them into the database
	 */
	public void ApproveNewBadge() {
		Badge badge = DisplayNewBadgeInProcess();
		if (!(badge.getBadgeName()=="0")) {
			String approveNewBadge = "UPDATE Badge SET BadgeStatus = 'Uncompleted' WHERE BadgeID = " + badge.getBadgeId();
			editDatabase(approveNewBadge);
		}
		else {
			System.out.println("No New Badge Waiting for approve");
		}
	}
	
	/**
	 * The approvateBadgeApplication method takes in a badge awaiting approval,
	 * and allows the Supervisor to either approve the badge (which updates the status
	 * of the badge to "Completed"), or reject the badges (which updates the status of
	 * the badge to uncompleted)
	 * @param badge
	 * @return boolean
	 */
	public Badge DisplayBadgeInProcess() {
		HashMap<Integer, Badge> badgeDictionary = getBadgeDictionary("InProgress");
		int badgeIndex = badgeDictionary.size();
		int selection = getBadgeChoice(badgeIndex);
		return badgeDictionary.get(selection);
	}
	
	public void ApproveBadgeApplication() {
		Badge badge = DisplayBadgeInProcess();
		if (!(badge.getBadgeName() == "0")) {
			String approveNewBadge = "UPDATE Badge SET BadgeStatus = 'Completed' WHERE BadgeID = " + badge.getBadgeId();
			editDatabase(approveNewBadge);
		}
		else {
			System.out.println("No New Badge Waiting for approve");
		}
	}
	/*public boolean approveBadgeApplication(){
		Scanner sc = new Scanner(System.in);
		String user_input = sc.nextLine();
		Badge badge = DisplayNewBadgeInProcess();
		String approveBadgeApplication = "UPDATE Badge SET BadgeStatus = 'Completed' WHERE BadgeID =" + badge.getBadgeId();
		String denyBadgeApplication = "UPDATE Badge SET BadgeStatus = 'Uncompleted' WHERE BadgeID =" + badge.getBadgeId();
		System.out.println("Would you like to approve this application (1) or deny it(2)?");
		if (user_input.equals("1")){
			editDatabase(approveBadgeApplication);
			return true;
		}
		else if (user_input.equals("2")){
			editDatabase(denyBadgeApplication);
			return true;
		}
		System.out.println("Invalid input.");
		return false;
		
	}*/
	
	public static void main (String[]args) {
		SupervisorFuncs test = new SupervisorFuncs(2);
		test.ApproveNewBadge();
		test.ApproveBadgeApplication();
	}

		
}

