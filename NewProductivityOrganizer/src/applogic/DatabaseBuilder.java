package applogic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseBuilder creates a database that stores everything
 */
public class DatabaseBuilder {
	public static final String PORT_NUMBER = "8889";
	
	public static void InsertData (String statement) {
		try (
				// Step 1: Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				){
			stmt.execute(statement);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void setupDatabase() {
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
			
			String createStudentWorker = "create table StudentWorker ( " +
					"UserID int, " +
					"ActualName varchar(50), " +
					"Warnings int, " +
					"Awards int, " +
					"Point int)";
			
			String createSupervisor = "create table Supervisor ( " +
					"UserID int," +
					"ActualName varchar(50))";
			
			String createBadge = "create table Badge ( " + 
					"BadgeName varchar(50), " +
					"BadgeDescription varchar(100), " +
					"CreatorUserID int, " +
					"ApproveUserID int," +
					"BadgeID int AUTO_INCREMENT, " +
					"ClaimingUserID int,"+
					"ApproveClaimUserID int,"+
					"BadgeStatus varchar(50) NOT NULL," +
					"BadgePoint int," +
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
		
		try {
			Reader fakeDataFileReader = new FileReader("FakeData.csv");
			BufferedReader fakeDataReader = new BufferedReader(fakeDataFileReader);
			String statement = fakeDataReader.readLine();
			while (statement != null) {
				InsertData(statement);
				statement=fakeDataReader.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dropDatabase() {
		//Build up a new database for ProductivityIncentizer
				try (
						// Step 1: Allocate a database "Connection" object
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", 
								"root", "root"); // MySQL
						// Step 2: Allocate a "Statement" object in the Connection
						Statement stmt = conn.createStatement();
						) {
					// Step 3 - create our database
					String sql = "drop database ProductivityIncentivizerDatabase";
					stmt.execute(sql);


				} catch(SQLException ex) {
					ex.printStackTrace();
				}
	}
}
