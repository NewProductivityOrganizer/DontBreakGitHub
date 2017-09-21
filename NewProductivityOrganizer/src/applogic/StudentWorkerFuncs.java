package applogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Class defines functionality for student workers
 */
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
	public void CreateNewBadge(String BadgeName, String BadgeDescription) {
		BadgeCommand command = new CreateBadgeCommand(BadgeName, BadgeDescription, this);
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
	 * method takes in selected uncompleted badge id and call the command to execute to change the badge status in database
	 */
	public void ApplyBadge(int badgeId) {
		BadgeCommand command = new ApplyForBadgeCommand(badgeId, this);
		command.execute();
		commandStack.push(command);
	}
	
	/**
	 * Undo the last action with badges
	 */
	public void UndoApplication() {
		BadgeCommand command = commandStack.pop();
		command.undo();
	}
	
	/**
	 * method takes in new badge info and add it to database and returns the badgeID
	 * @param creatorUserId
	 * @param BadgeName
	 * @param BadgeDescription
	 * @return BadgeID
	 */
	public int AddNewBadge(String BadgeName, String BadgeDescription) {
		int badgeId = 0;
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String createNewBadge = "INSERT INTO Badge (CreatorUserID, BadgeName, BadgeDescription, BadgeStatus) VALUES ("+userId+", '"+BadgeName+"','" + BadgeDescription +"', 'Waiting')";
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
	 * method takes in badgeId to mark the badge in database as wasted
	 * @param badgeId
	 */
	public void UndoNewBadgeHelper(int badgeId) {
		String undoNewBadge = "UPDATE Badge SET BadgeStatus = 'Wasted' WHERE BadgeID = "+ badgeId;
		editDatabase(undoNewBadge);
	}
	
	/**
	 * Method used to change the Badge Status after someone applied for the badge
	 */
	public void ChangeAppliedBadgeStatus(int badgeId) {
		String changeBadgeStatus = "UPDATE Badge SET BadgeStatus = 'InProgress' WHERE BadgeID = " +badgeId;
		editDatabase(changeBadgeStatus);
		String changeClaimingId = "UPDATE Badge SET ClaimingUserID = " + this.userId +" WHERE BadgeID = "+ badgeId;
		editDatabase(changeClaimingId);
	}
	
	/**
	 * method takes in badgeId to change the badge status back to uncompleted
	 * @param badgeId
	 */
	public void UndoAppliedBadge(int badgeId) {
		String undoApplication = "UPDATE Badge SET BadgeStatus = 'Uncompleted' WHERE BadgeID = "+ badgeId;
		String changeClaimingId = "UPDATE Badge SET ClaimingUserID = 0 WHERE BadgeID = " + badgeId;
		editDatabase(undoApplication);
		editDatabase(changeClaimingId);
	}
	
	/**
	 * Display uncompleted badge
	 */
	public List<Badge> DisplayUncompletedBadge() {
			String getUncompletedBadge = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE BadgeStatus = 'Uncompleted'";
			List<Badge> badgeList = getBadgeInfo(getUncompletedBadge);
			printBadge(badgeList);
			return badgeList;
	}
	
	/**
	 * Display Badge completed by this student worker
	 */
	public List<Badge> DisplayMyBadge() {
		String myCompletedBadge = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE ClaimingUserID = " +this.userId + " AND BadgeStatus = 'Completed'";
		List<Badge> badgeList = getBadgeInfo(myCompletedBadge);
		printBadge(badgeList);
		return badgeList;
	}
	
	/**
	 * Display Badge completed by this student worker
	 */
	public List<Badge> DisplayMyBadgeInProcess() {
		String myBadgeInProcess = "SELECT BadgeID, BadgeName, BadgeDescription FROM Badge WHERE ClaimingUserID = " +this.userId + " AND BadgeStatus = 'InProgress'";
		List<Badge> badgeList = getBadgeInfo(myBadgeInProcess);
		printBadge(badgeList);
		return badgeList;
	}
	
	/**
	 * Method takes in statement and based on it retrieving the badge information
	 * @param statement for sql stored as String
	 * @return list of badges matches the requirements
	 */
	public List<Badge> getBadgeInfo(String statement) {
		List<Badge> badgeList = new ArrayList<Badge>();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			ResultSet badges = stmt.executeQuery(statement);
			while (badges.next()) {
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
	
	/**
	 * method takes in a statement and execute it in MySql
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
	
	//Just to test student worker functions in console;
	public static void main(String [] args) {
		DatabaseBuilder db = new DatabaseBuilder();
		db.dropDatabase();
		db.setupDatabase();
		StudentWorkerFuncs studentWorker1 = new StudentWorkerFuncs(1);
		studentWorker1.checkMyPerformance();
		//studentWorker1.DisplayMyBadge();
		//studentWorker1.DisplayUncompletedBadge();
		studentWorker1.CreateNewBadge("BadgeNew1", "TestAdd1");
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String statement = "SELECT BadgeName,BadgeStatus FROM Badge";
			ResultSet rs= stmt.executeQuery(statement);
			System.out.println("After created a new Badge, Badge in database: ");
			while (rs.next()) {
				System.out.println(rs.getString(1) + ": " + rs.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		studentWorker1.UndoApplication();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String statement = "SELECT BadgeName,BadgeStatus FROM Badge";
			ResultSet rs= stmt.executeQuery(statement);
			System.out.println("After undo, Badge in database: ");
			while (rs.next()) {
				System.out.println(rs.getString(1)+ ": " + rs.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		studentWorker1.ApplyBadge(4);
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String statement = "SELECT BadgeName,BadgeStatus FROM Badge";
			ResultSet rs= stmt.executeQuery(statement);
			System.out.println("After apply for Badge with ID of 4, Badge in database: ");
			while (rs.next()) {
				System.out.println(rs.getString(1)+ ": " + rs.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		studentWorker1.UndoApplication();
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String statement = "SELECT BadgeName,BadgeStatus FROM Badge";
			ResultSet rs= stmt.executeQuery(statement);
			System.out.println("After undo, Badge in database: ");
			while (rs.next()) {
				System.out.println(rs.getString(1)+ ": " + rs.getString(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
