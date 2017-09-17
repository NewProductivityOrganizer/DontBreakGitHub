
public class ApplyBadgeCommand implements BadgeCommand {
	private int badgeId;
	private StudentWorkerFuncs studentWorkerOperation;
	
	public ApplyBadgeCommand(int badgeId, StudentWorkerFuncs studentWorkerOperation) {
		this.badgeId = badgeId;
		this.studentWorkerOperation = studentWorkerOperation;
	}
	
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		studentWorkerOperation.UndoAppliedBadge(badgeId);
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		studentWorkerOperation.ApplyBadge(badgeId);
	}

}
