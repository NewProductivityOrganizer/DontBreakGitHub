package applogic;

/**
 * Implementation of badge command for when a student worker applies for a badge
 * Contains do and undo
 */
public class ApplyForBadgeCommand implements BadgeCommand {
	private int badgeId;
	private StudentWorkerFuncs studentWorkerOperation;
	
	public ApplyForBadgeCommand(int badgeId, StudentWorkerFuncs studentWorkerOperation) {
		this.badgeId = badgeId;
		this.studentWorkerOperation = studentWorkerOperation;
	}
	
	/**
	 * removes badge student worker applied for
	 */
	@Override
	public void undo() {
		studentWorkerOperation.UndoAppliedBadge(badgeId);	
	}

	/**
	 * executes ApplyBadge (when student applies for badge)
	 */
	@Override
	public void execute() {
		studentWorkerOperation.ChangeAppliedBadgeStatus(badgeId);
	}

}
