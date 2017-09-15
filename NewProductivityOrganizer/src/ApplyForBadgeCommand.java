/**
 * this class defines the process for applying for a badge
 * A student worker sends in a list of all the badges he/she is applying for 
 * */
public class ApplyForBadgeCommand implements BadgeCommand {

	//private ArrayList<Badge> badgeList;
	//private String studentWorkerName;
	// private String status = "";
	 //private int iD;
	 Badge badge;
	 private  final String BADGE_IN_PROGRESS = "In Progress";
	

	public ApplyForBadgeCommand(Badge badgeRequested) {
		this.badge = badgeRequested;
	}

	@Override
	public void executeCommand() {
		
		/*for(Badge badges: badgeList) {
			
			if(badges!= null) {
			   
				updateBadgeStatus(badges);
		 
				//In process == status
				//method that deals with database
				
			   //change applicant user ID at the same time
			}
		}*/	
	}
	

	@Override
	public void undoCommnd() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * method meant to simulate what changing a badge status would look like
	 * @param badge is the badge received
	 * */
   public void updateBadgeStatus() {
	   
	   badge.setBadgeStatus(BADGE_IN_PROGRESS);
   }
   
   public boolean statusUpdate() {
	   boolean result = false;
	    if(badge.getBadgeStatus().equalsIgnoreCase("In Progress")) {
	    	 result = true;
	    }
	   return result;
   }
   
	public String toString() {
		
		return "Badge Name:"+ badge.getBadgeName()+ " \n"+"Badge Description: "+ badge.getBadgeDescription()+ "\n"+" Badge Status:"+ badge.getBadgeStatus();
	}


	@Override
	public void executeCommand(Badge badge) {
		// TODO Auto-generated method stub
		
	}


	

}