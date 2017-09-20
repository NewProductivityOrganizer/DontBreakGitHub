package graphicalUserInterface;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginScreen extends JPanel {
 private JButton displayLeadingBoard;
 private JButton  displayMyBadge;
 private JButton   
 displayIncompleteBadges;
 private JButton  displayMyPerformance ;
 private JButton   displayMyBadgeInProgress ;
 private JButton createNewBadge;
 private JButton applyForBadge;
 private JButton undoMyPreviousAction;
	/**
	 * Create the panel.
	 */
	
public LoginScreen () {
	


	
	 displayLeadingBoard = new JButton("displayLeadingBoard");
	 displayLeadingBoard.setBounds(207,315,117,29);
	 add( displayLeadingBoard );
	 displayLeadingBoard.setVisible(true);
	 
	 
	 
	 displayMyBadge = new JButton("displayMyBadge");
	 displayMyBadge.setBounds(207,310,117,29);
	 add( displayMyBadge );
	 displayMyBadge.setVisible(true);
	 
	 
     
	 displayIncompleteBadges = new JButton("displayIncompleteBadge");
	 displayIncompleteBadges.setBounds(207,320,117,29);
	 add( displayIncompleteBadges );
	 displayIncompleteBadges.setVisible(true);
	 
     
     
     displayMyPerformance =  new JButton("displayMyPerformance"); 
     displayMyPerformance.setBounds(207,330,117,29);
	  add(  displayMyPerformance );
	  displayMyPerformance.setVisible(true);
     
	 
	 
	 displayMyBadgeInProgress = new JButton("displayMyBadgeInProgress");
	 displayMyBadgeInProgress.setBounds(207,345,117,29);
     add( displayMyBadgeInProgress );
	 displayMyBadgeInProgress.setVisible(true);
	    
	 
	 
	 
	 
	 createNewBadge =new JButton("createNewBadge");
	 createNewBadge.setBounds(207,340,117,29);
	 add( createNewBadge );
	 createNewBadge .setVisible(true);
	  
	 
	 
	 
	 
	    applyForBadge = new JButton("applyForBadge");
	    applyForBadge.setBounds(207,345,117,29);
	   add(applyForBadge );
		applyForBadge .setVisible(true);
	 
	  
	    undoMyPreviousAction = new JButton ("undoMyPreviousAction");
	    undoMyPreviousAction.setBounds(207,350,117,29);
	   add(undoMyPreviousAction );
	    undoMyPreviousAction.setVisible(true);
	
	
	
	
	
}





}
