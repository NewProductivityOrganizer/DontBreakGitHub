import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseBuilder creates a database that stores everything
 */
public class DatabaseBuilder {
	public static final String PORT_NUMBER = "8889";

	public static void main(String[] args) {
		//Build up a new database for ProductivityIncentizer
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", 
						"root", "root"); // MySQL
				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			// Step 3 - create our database
			String sql = "create database ProductivityIncentivizerDatabase";
			stmt.execute(sql);


		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//Build up tables in the database
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			// Step 3 - create our new table
			String createLogIn = "create table LogInInformation ( " +
					"UserName varchar(10), " +
					"UserPassword varchar(9), " +
					"UserID int AUTO_INCREMENT, " +
					"UserIdentity varchar(20)," +
					"unique(UserName), " +
					"primary key(UserID));"; 
			
			String createStudentWorker = "create table StudentWorkerInformation ( " +
					"UserName varchar(10), " +
					"UserID int, " +
					"ActualName varchar(50), " +
					"Warnings int, " +
					"Awards int, " +
					"Point int, " +
					"BadgesEarned varchar(80), " +
					"BadgesInProcess varchar(50))";
			
			String createSupervisor = "create table Supervisor ( " +
					"UserName varchar(10), " +
					"UserID int," +
					"ActualName varchar(50))";
			
			String createBadge = "create table Badge ( " + 
					"BadgeName varchar(50), " +
					"BadgeDescription varchar(100), " +
					"CreatorUserID int, " +
					"ApproveUserID int," +
					"BadgeID int AUTO_INCREMENT, " +
					"ClaimingUserID int,"+
					"ApproveClaminUserID int,"+
					"BadgeStatus varchar(50) NOT NULL," +
					"primary key (BadgeID), " +
					"UNIQUE (BadgeName))";
			
			String createAwardHistory = "create table AwardHistory (" +
					"StudentWorkerID int,"+
					"DateOfAwarding datetime)";
			
			String createWarningHistory = "create table WarningHistory (" +
					"StudentWorkerID int,"+
					"DateOfAwarding datetime)";
			
			stmt.execute(createLogIn);
			stmt.execute(createStudentWorker);
			stmt.execute(createSupervisor);
			stmt.execute(createBadge);
			stmt.execute(createAwardHistory);
			stmt.execute(createWarningHistory);
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
