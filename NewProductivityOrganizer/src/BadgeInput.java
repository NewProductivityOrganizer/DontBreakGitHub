import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author palesa
 *
 */
public class BadgeInput {
	
	private  ArrayList<Badge> badges = new ArrayList<Badge>();
	ApplyForBadgeCommand applyForBadge;
	public static final int UPDATION = 0; 
    public static final int THE_XANDER = 1; 
    public static final int HUMANITARIAN = 2; 
    public static final int SEVEN_LEAGUE_BOOTS = 3;
    public static final int  ITS_MANAGER = 4; 
    public static final int NEW_IS_BETTER = 5; 
    
	

	/*public BadgeInput() {
	   /*
		     Badge updation = new Badge("Updation","Upgrade firmware on a printer",101,null);
		     badges.add(updation);
		     
		     Badge theXander = new Badge("theXander","develop a mobile app for CC",102,null);
		     
		     badges.add(theXander);
		     Badge humanitarian = new Badge("Humanitarian","develop a set of cards for ITS: Cards Against Humanity",103,null);
		     
		     badges.add(humanitarian);
		     Badge sevenLeagueBoots = new Badge("Seven league boots","Help someone in facilities or HVAC",104,null);
		     
		      badges.add(sevenLeagueBoots);
		     Badge itsManager = new Badge("ITS Manager","Talk to a sales vendor for at least 5 minutes",105,null);
		     badges.add(itsManager);
		     Badge newIsBetter = new Badge("New is Better","Upgrade the OS on a computer",106,null);
		     badges.add(newIsBetter);*/
		     
		   
		     
	//}	     
	
	/**
	 * This player menu allows a user to choose from a selection of options and then a decision is amde based off of that
	 * @ author jburge
	 * */
	private static int playerMenu()
	{
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("/**************************************************************/");
			System.out.println("\nChoose the badge you would like to apply for: ");
			System.out.println("0: Upgration -- Upgraded firmware on a printer");
			System.out.println("1: The Xander -- developed a mobile app for CC ");
			System.out.println("2: Humanitarian -- develop set of cards for ITS: Cards Against Humanity");
			System.out.println("3: Seven League Boots --Help someone in facilities or HVAC");
			System.out.println("4: ITS Manager -- Talk to a sales venfor for at least 5 minutes");
			System.out.println("5: New is better -- Upgrade the OS on a computer");
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection >= 0) && (selection <= 5))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 0 and 5");
			}
		}
		return selection;
	}
	

	/**
	 * Manually create each badge until a way to do this using a database is found
	 * This is merely for the first iteration purpose
	 */
	public void initializeBadges() {
		
	    	Badge updation = new Badge("Updation","Upgrade firmware on a printer",101,null);
	     badges.add(updation);
	     
	     Badge theXander = new Badge("theXander","develop a mobile app for CC",102,null);
	     
	     badges.add(theXander);
	     Badge humanitarian = new Badge("Humanitarian","develop a set of cards for ITS: Cards Against Humanity",103,null);
	     
	  
	     
	     badges.add(humanitarian);
	     Badge sevenLeagueBoots = new Badge("Seven league boots","Help someone in facilities or HVAC",104,null);
	     
	      badges.add(sevenLeagueBoots);
	     Badge itsManager = new Badge("ITS Manager","Talk to a sales vendor for at least 5 minutes",105,null);
	     badges.add(itsManager);
	     Badge newIsBetter = new Badge("New is Better","Upgrade the OS on a computer",106,null);
	     badges.add(newIsBetter);
		
	}
	
	
	 
	
	public  void sendBadgeApplication() {
		 

		   initializeBadges(); // initialize simulated badge values
	
		
		int badgeSelection = playerMenu();
		
		switch(badgeSelection) {
		
		case UPDATION :
			applyForBadge = new ApplyForBadgeCommand(badges.get(UPDATION));
			applyForBadge.executeCommand();
		    applyForBadge.toString();
		
			break;
		case THE_XANDER:
			applyForBadge = new ApplyForBadgeCommand(badges.get(THE_XANDER));
			applyForBadge.executeCommand();
			break;
		case HUMANITARIAN:
			applyForBadge = new ApplyForBadgeCommand(badges.get(HUMANITARIAN));
			applyForBadge.executeCommand();
			applyForBadge.toString();
			break;
		case SEVEN_LEAGUE_BOOTS : 
			applyForBadge = new ApplyForBadgeCommand(badges.get(SEVEN_LEAGUE_BOOTS));
			applyForBadge.executeCommand();
			applyForBadge.toString();
			break;
		case  ITS_MANAGER : 
			applyForBadge = new ApplyForBadgeCommand(badges.get(ITS_MANAGER));
			applyForBadge.executeCommand();
			applyForBadge.toString();
			break;
		
		case  NEW_IS_BETTER: 
			applyForBadge = new ApplyForBadgeCommand(badges.get(NEW_IS_BETTER));
			applyForBadge.executeCommand();
			applyForBadge.toString();
			break;
		}
		
		
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BadgeInput badgeInput = new BadgeInput();
		badgeInput.sendBadgeApplication();

	}

}
