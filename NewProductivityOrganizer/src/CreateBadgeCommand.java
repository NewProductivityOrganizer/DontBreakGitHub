/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
	public void execute() {
		this.badgeId = studentWorkerOperation.AddNewBadge(this.creatorUserId, this.badgeName, this.badgeDescription);
	}
	public void undo() {
		studentWorkerOperation.UndoNewBadgeHelper(badgeId);
	}
}
