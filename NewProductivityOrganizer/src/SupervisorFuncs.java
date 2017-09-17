import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupervisorFuncs {
	private static final String PORT_NUMBER = "8889";
	private int userId;
	
	
	public SupervisorFuncs(int userId) {
		this.userId = userId;
	}
	
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
	
	public void AutoAssess() {
		GiveAwards();
		GiveWarnings();
	}
	
	public void GiveAwards() {
		List<Integer> topTenStudentWorker = getTopTen();
		for (int topTenUserID : topTenStudentWorker) {
			String giveAward = "UPDATE StudentWorker SET Awards = Award + 1 WHERE UserID = " + topTenUserID;
			editDatabase(giveAward);
		}
	}
	
	public void GiveWarnings() {
		int threshold = GetHalfAvg();
		List<Integer> badStudentWorker = getBadStudentWorker(threshold);
		for (int badStudentWorkerID : badStudentWorker) {
			String giveWarning = "UPDATE StudentWorker SET Warnings = Warnings + 1 WHERE UserID = " + badStudentWorkerID;
			editDatabase(giveWarning);
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
}