import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Supervisor account is one of the classes within AccountFactory
 */
public class SupervisorAccount implements Account{

	private String userIdentity = "Supervisor";
	private String accountName;
	private String username;
	private String password;
	
	public SupervisorAccount() {
	}
	
	/**
	 * creates supervisor account
	 * @param employeeName
	 */
	public void createAccount(String employeeName, String inputUsername, String inputPassword) {
		accountName = employeeName;
		username = inputUsername;
		password = inputPassword;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return accountName
	 */
	public String getSupervisorName() {
		return accountName;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return userIdentity
	 */
	public String getUserIdentity() {
		return userIdentity;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * used when adding a supervisor account into the database
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	public void deleteAccount() {
		
	}
	
	/**
	 * The giveTopTenAwards() method gives an award to the top ten student workers 
	 * with the highest number of points. 
	 */
	
	public void giveTopTenAwards(){
		try (
				// Step 1: Allocate a database "Connection" object
			Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + ProductivityIncentivizer.PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
			Statement stmt = conn.createStatement();
		) {
			String getTopTen = "SELECT UserID FROM StudentWorker ORDER BY Point DESC LIMIT 10";
			ResultSet topTen = stmt.executeQuery(getTopTen);
			while (topTen.next()){
				int topTenUserID = topTen.getInt(1);
				String statement = "SELECT Awards FROM StudentWorker WHERE UserID =" + topTenUserID;
				ResultSet rs = stmt.executeQuery(statement);
				while (rs.next()){
					int awards = rs.getInt(1);
					awards++;
					String updateAward = "UPDATE StudentWorker SET Awards =" + awards + "WHERE UserID =" + topTenUserID;		
				}						
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	 }
	
	/**
	 * The giveBottomHalfWarnings() method gives a warning to the bottom 50% of
	 * student workers (the 50% with the lower than average points). 
	 */
	
	public void giveBottomHalfWarnings(){
		try (
				// Step 1: Allocate a database "Connection" object
			Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + ProductivityIncentivizer.PORT_NUMBER + "/ProductivityIncentivizerDatabase?user=root&password=root"); // MySQL

				// Step 2: Allocate a "Statement" object in the Connection
			Statement stmt = conn.createStatement();
		 ) {
			String getBottomHalf = "SELECT UserID FROM StudentWorker EXCEPT TOP 50 PERCENT";
			ResultSet bottom = stmt.executeQuery(getBottomHalf);
				int bottomUserID = bottom.getInt(1);
				String statement = "SELECT Warnings FROM StudentWorker WHERE UserID =" + bottomUserID;
				ResultSet rs = stmt.executeQuery(statement);
				while (rs.next()){
					int warnings = rs.getInt(1);
					warnings++;
					String updateWarnings = "UPDATE StudentWorker SET Warnings =" + warnings + "WHERE UserID =" + bottomUserID;		
				}						

		    }catch(SQLException e) {
			 e.printStackTrace();
		     }
		
	 }
  }
	
