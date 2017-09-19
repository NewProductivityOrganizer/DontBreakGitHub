package applogic;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/

/**
 * Command used when student worker wants to create a badge
 */
public class CreateBadgeCommand implements BadgeCommand{
	private int creatorUserId;
	private String badgeName;
	private String badgeDescription;
	private int badgeId;
	private StudentWorkerFuncs studentWorkerOperation;
	
	public CreateBadgeCommand(int creatorUserId, String badgeName, String badgeDescription, StudentWorkerFuncs studentWorkerOperation) {
		this.creatorUserId = creatorUserId;
		this.badgeDescription = badgeDescription;
		this.badgeName = badgeName;
		this.studentWorkerOperation = studentWorkerOperation;
	}
	
	/**
	 * calls AddNewBadge -- method that adds a badge when a student worker creates a badge
	 * added badge status is 'Waiting' -- meaning it has not yet been approved by the supervisor
	 */
	public void execute() {
		this.badgeId = studentWorkerOperation.AddNewBadge(this.creatorUserId, this.badgeName, this.badgeDescription);
	}
	
	/**
	 * calls UndoNewBadgeHelper, and removes badge from table by using badgeId
	 */
	public void undo() {
		studentWorkerOperation.UndoNewBadgeHelper(badgeId);
	}
}
