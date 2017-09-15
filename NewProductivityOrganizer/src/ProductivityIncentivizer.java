import java.util.ArrayList;
import java.util.Scanner;

/**
 * Productivity Incentivizer is a program that helps foster student worker productivity at the 
 * ITS Solutions Center
 * @author Sophie Mittelstadt, Stan Zhang, Palesa Mokoena, Nadia Banks
 */
public class ProductivityIncentivizer {
	
	ArrayList<Badge> badges; //used to store badges
	
	public ProductivityIncentivizer() {

	}
	
	/**
	 * Prompts user to create account, calls the AccountFactory
	 */
	public void promptUserToCreateAccount(){
		String employeeName;
		String employeeType;
		String supervisorCode = "";

		Scanner in = new Scanner(System.in);

		AccountFactory accountFactory = new AccountFactory();
		
		System.out.println("Please enter your name.");
		employeeName = in.nextLine();
		
		System.out.println("Please select student worker (1) or supervisor (2).");
		employeeType = in.nextLine();

		if (employeeType.equals("2")){
			while(!supervisorCode.equals("123")) {
				System.out.println("Please enter supervisor code: ");
				supervisorCode = in.nextLine();	
			}
				
		}
		addAccount(employeeName, employeeType, supervisorCode);
		in.close();
	}
	
	/**
	 * Adds account, called by promptUserToCreateAccount
	 * @param employeeName
	 * @param employeeType
	 * @return boolean used for testing
	 */
	public boolean addAccount(String employeeName, String employeeType, String supervisorCode) {
		AccountFactory accountFactory = new AccountFactory();
		if (employeeType.equals("1")) {
			accountFactory.getAccount(employeeType, employeeName);		
			return true;
		}
		else if (employeeType.equals("2")){
			if (supervisorCode.equals("123")) {
				accountFactory.getAccount(employeeType, employeeName);
				return true;
			}
			else { //invalid code input	
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return arrayList of badges
	 * This method is not a permanent component of our program. For the sake of the first iteration and testing
	 * our other methods, this method creates an ArrayList of badges that can be accessed and printed. In
	 * our actual program, this information will come from the database.
	 */
	public ArrayList<Badge> makeArtificialBadgeList(){

		Badge updation = new Badge("Updation", "Update firmware on a printer");

		Badge theXander = new Badge("The Xander", "Develop mobile app for CC");

		Badge humanitarian = new Badge("Humanitarian", "Develop set of card for ITS");

		Badge sevenLeagueBoots = new Badge("Seven League Boots", "Help someone in facilities or HVAC");

		Badge itsManager = new Badge("ITS Manager", "Talk to a sales vendor for at least 5 minutes");

		Badge newIsBetter = new Badge("New is Better", "Upgrade the OS on a computer");

		Badge doubleVision = new Badge("Double Vision", "Hook up dual monitors");

		Badge prankster = new Badge("Prankster", "Prank the Help Desk team leader");

		Badge noCapes = new Badge("No Capes", "Make and wear an ITS cape");

		Badge intern = new Badge("Intern", "Work a full 8 hour shift");

		Badge itsQuiet = new Badge("It's quiet...too quiet", "Work a weekend shift");

		Badge breakTheFourthWall = new Badge("Break the 4th Wall", "Make a video segment of the Help Desk similar to The Office");

		badges.add(updation);

		badges.add(theXander);

		badges.add(humanitarian);

		badges.add(sevenLeagueBoots);

		badges.add(itsManager);

		badges.add(newIsBetter);

		badges.add(doubleVision);

		badges.add(prankster);

		badges.add(noCapes);

		badges.add(intern);

		badges.add(itsQuiet);

		badges.add(breakTheFourthWall);

		return badges;

	}
	

	public static void main(String[] args) {
		ProductivityIncentivizer runProgram = new ProductivityIncentivizer();
		runProgram.promptUserToCreateAccount();
	}

}