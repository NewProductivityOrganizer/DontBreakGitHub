import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class StudentWorkerFuncs {
	private static final String PORT_NUMBER = "8889";
	private int userId;
	private Stack<BadgeCommand> commandStack= new Stack<BadgeCommand>();
	
	public StudentWorkerFuncs(int userId) {
		this.userId = userId;
	}
	
	/*public  void editDatabase(String statement) {
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
	}*/
	/**
	 * method takes in new badge information and call the command to execute to add new badge to database
	 * @param creatorUserId
	 * @param BadgeName
	 * @param BadgeDescription
	 */
	public void CreateNewBadge(int creatorUserId, String BadgeName, String BadgeDescription) {
		BadgeCommand command = new CreateBadgeCommand(creatorUserId, BadgeName, BadgeDescription, this);
		command.execute();
		commandStack.push(command);
	}
	/**
	 * method call to undo last action
	 */
	public void UndoNewBadge() {
		BadgeCommand command = commandStack.pop();
		command.undo();
	}
	
	/**
	 * method takes in badgeId to mark the badge in database as wasted
	 * @param badgeId
	 */
	public void UndoNewBadgeHelper(int badgeId) {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String undoNewBadge = "UPDATE Badge Set BadgeStatus = 'Wasted' WHERE BadgeID = "+ badgeId;
			stmt.execute(undoNewBadge);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * method takes in new badge info and add it to database and returns the badgeID
	 * @param creatorUserId
	 * @param BadgeName
	 * @param BadgeDescription
	 * @return
	 */
	public int AddNewBadge(int creatorUserId, String BadgeName, String BadgeDescription) {
		int badgeId = 0;
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String createNewBadge = "INSERT INTO Badge (CreatorUserID, BadgeName, BadgeDescription) VALUES (creatorUserId, '"+BadgeName+"','" + BadgeDescription +"')";
			String getNewBadgeId = "SELECT LAST_INSERT_ID()";
			stmt.execute(createNewBadge);
			ResultSet rs = stmt.executeQuery(getNewBadgeId);
			while (rs.next()) {
				badgeId = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return badgeId;
	}
	/**
	 * Display uncompleted badge
	 */
	public void DisplayUncompletedBadge() {
			String getUncompletedBadge = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE BadgeStatus = 'Uncompleted'";
			List<Badge> badgeList = getAndPrintBadgeInfo(getUncompletedBadge);
			printBadge(badgeList);
	}
	
	/**
	 * Display Badge completed by this student worker
	 */
	public void DisplayMyBadge() {
		String myCompletedBadge = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE ClaimingUserID = " +this.userId + "AND BadgeStatus = 'Completed'";
		List<Badge> badgeList = getAndPrintBadgeInfo(myCompletedBadge);
		printBadge(badgeList);
	}
	
	/**
	 * 
	 */
	public void DisplayMyBadgeInProcess() {
		String myBadgeInProcess = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE ClaimingUserID = " +this.userId + "AND BadgeStatus = 'InProcess'";
		List<Badge> badgeList = getAndPrintBadgeInfo(myBadgeInProcess);
		printBadge(badgeList);
	}
	
	/**
	 * Method takes in statement and based on it retrieving the badge information
	 * @param statement for sql stored as String
	 * @return list of badges matches the requirements
	 */
	public List<Badge> getAndPrintBadgeInfo(String statement) {
		List<Badge> badgeList = new ArrayList<Badge>();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			ResultSet badges = stmt.executeQuery(statement);
			int badgeIndex = 0;
			while (badges.next()) {
				badgeIndex += 1;
				int badgeID = badges.getInt(1);
				String badgeName = badges.getString(2);
				String badgeDescription = badges.getString(3);
				Badge badge = new Badge(badgeName, badgeDescription);
				badge.setBadgeId(badgeID);
				badgeList.add(badge);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return badgeList;
	}
	
	/**
	 * method to give output, printing out the selected badges
	 * @param badgeList
	 */
	public void printBadge(List<Badge> badgeList) {
		int badgeIndex = 0;
		for (Badge badge : badgeList) {
			badgeIndex++;
			System.out.println(badgeIndex + ".");
			System.out.println(badge);
		}
	}
	/**
	 * retrieving and printing out the student worker's performance
	 */
	public void checkMyPerformance() {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String getMyPerformance = "SELECT Awards, Warnings From StudentWorker WHERE UserID = " + this.userId;
			ResultSet myPerformance = stmt.executeQuery(getMyPerformance);
			while (myPerformance.next()) {
				int awardNum = myPerformance.getInt(1);
				int warningNum = myPerformance.getInt(2);
				System.out.println( "Num of Awards recieved :"+ awardNum +"\nNum of Warnings recieved :" + warningNum);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
