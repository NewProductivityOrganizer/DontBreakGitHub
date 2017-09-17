import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentWorkerFuncs {
	private static final String PORT_NUMBER = "8889";
	private int userId;
	
	
	public StudentWorkerFuncs(int userId) {
		this.userId = userId;
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
