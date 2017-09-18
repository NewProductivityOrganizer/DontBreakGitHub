/**
 * this class is of the Badge object with attributes, name and value
 * @param name is the name of the badge and @param value is the value of the badge
 * */
public class Badge {
	
	private String badgeName;
	private String badgeDescription;
	private int creatorUserId;
	private String badgeStatus;
	private int badgeApproverUserId;
	private int applicantUserId;
	private int applicantApproverUserId;
	private int badgeId;

	public Badge(String badgeName, String badgeDescription) {
		this.badgeName = badgeName;
		this.badgeDescription = badgeDescription;
	}
	
	public String getBadgeName() {
		return badgeName;
	}
	
	public String getBadgeDescription(){
		return badgeDescription;
	}
	
	public int getCreatorUserId(){
		return creatorUserId;
	}
	
	public String getBadgeStatus(){
		return badgeStatus;
	}

	public int getBadgeApproverUserId() {
		return badgeApproverUserId;
	}

	public void setBadgeApproverUserId(int badgeApproverUserId) {
		this.badgeApproverUserId = badgeApproverUserId;
	}

	public int getApplicantUserId() {
		return applicantUserId;
	}

	public void setApplicantUserId(int applicantUserId) {
		this.applicantUserId = applicantUserId;
	}

	public int getApplicantApproverUserId() {
		return applicantApproverUserId;
	}

	public void setApplicantApproverUserId(int applicantApproverUserId) {
		this.applicantApproverUserId = applicantApproverUserId;
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public void setBadgeDescription(String badgeDescription) {
		this.badgeDescription = badgeDescription;
	}

	public void setCreatorUserId(int creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public void setBadgeStatus(String badgeStatus) {
		this.badgeStatus = badgeStatus;
	}
	
	/**
	 * Used for printing badge information in the main method
	 */
	public String toString(){
		return "BadgeID: " + this.badgeId +"     Badge: " + this.badgeName + "\nDescription: " + badgeDescription;

	}
	
}
