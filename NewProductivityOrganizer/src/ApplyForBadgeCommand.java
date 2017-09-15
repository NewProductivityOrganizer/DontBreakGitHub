
/**
 * this class defines the process for applying for a badge
 * A student worker selects a badge number that he/she wants to apply for
 * It is sent into this class and has its status updated to In Progress 
 * */
public class ApplyForBadgeCommand implements BadgeCommand {


	Badge badge;
	private  final String BADGE_IN_PROGRESS = "In Progress";
	private final String ACTION_CANT_BE_PERFORMED = "Badge cannot be In Progress because it does not have an empty status";
	String badgeName;
	String badgeDescription;
	String badgeStatus;
	String toReturn = "";
	/**
	 * @param badgeRequested is the badge received that is to be processed for approval
	 * */
	public ApplyForBadgeCommand(Badge badgeRequested) {
		this.badgeName = badgeRequested.getBadgeName();
		this.badgeDescription = badgeRequested.getBadgeDescription();
		this.badgeStatus = badgeRequested.getBadgeStatus();
		badge  = new Badge(badgeName,badgeDescription,badgeStatus);
	}

	/**this method is meant to check the badge status for null
	 * if true, meaning it has no request associated with it, then it is assigned to In progress*/
	@Override
	public String executeCommand() {
		if(isNull()) {
			badge.setBadgeStatus(BADGE_IN_PROGRESS);
			toReturn+= "Badge Name: "+ badge.getBadgeName();
			toReturn+= "\n"+ "Badge Description "+ badge.getBadgeDescription();
			toReturn+= "\n"+ "Badge Status: "+ badge.getBadgeStatus();
			return toReturn;
		} else {
		return ACTION_CANT_BE_PERFORMED;
	
		}
	}
	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
	}

	/**
	 * Method that checks the badge status against against a null value and if they match then a boolean of true is sent back so that its status can be changed
	 * */
	public boolean isNull() {
		boolean result = false;   

		if(badge.getBadgeStatus() == null) {
			result = true;
		}
		return result;
	}

	

	/*@Override
	public void executeCommand(Badge badge) {
		// TODO Auto-generated method stub
		
	}*/

}