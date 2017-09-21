package graphicalUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import applogic.Badge;
import applogic.ProductivityIncentivizer;
import applogic.StudentWorker;
import applogic.StudentWorkerFuncs;
/**
 *
 * Class for Login screen*/
public class StudengWorkerLoginScreen extends JPanel implements ActionListener{
	private JButton displayLeadingBoard;
 	private JButton  displayMyBadge;
 	private JButton  displayIncompleteBadges;
 	private JButton  displayMyPerformance ;
 	private JButton   displayMyBadgeInProgress ;
 	private JButton createNewBadge;
 	private JButton applyForBadge;
 	private JButton undoMyPreviousAction;
 	private JTextArea display = new JTextArea(10,40);
 	private StudentWorkerFuncs sw1 = new StudentWorkerFuncs(1);
/**
* Create the panel.
*/
	public StudengWorkerLoginScreen () {

		 add(display);


		 displayLeadingBoard = new JButton("displayLeadingBoard");
		 displayLeadingBoard.setBounds(207,315,117,29);
		 add( displayLeadingBoard );
		 displayLeadingBoard.setVisible(true);
		 display.setEditable(false);

		 displayLeadingBoard.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
				 display.setText("");
				 ProductivityIncentivizer pi = new ProductivityIncentivizer();
				 List<StudentWorker> swList = pi.getLeadingBoard();
				 for (StudentWorker sw : swList) {
					 display.append(sw.toString());
				 }

			 }
		 });

		 displayMyBadge = new JButton("displayMyBadge");
		 displayMyBadge.setBounds(207,310,200,300);
		 add( displayMyBadge );
		 displayMyBadge.setVisible(true);

		 displayMyBadge.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
				 display.setText("");
				List<Badge> myBadge  = sw1.DisplayMyBadge();
				int badgeIndex = 0;
				for (Badge badge : myBadge) {
					badgeIndex++;
					display.append(badgeIndex + ".");
					display.append(badge.toString());
				}

			 }
		 });


		 displayIncompleteBadges = new JButton("displayIncompleteBadge");
		 displayIncompleteBadges.setBounds(207,320,200,300);
		 add( displayIncompleteBadges );
		 displayIncompleteBadges.setVisible(true);

		 displayIncompleteBadges.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
   //display = new JTextField("");
  // add(display);
   //display.setBounds(207,315,200,300);
				 display.setText("");
				 List<Badge> myBadge  = sw1.DisplayUncompletedBadge();
					int badgeIndex = 0;
					for (Badge badge : myBadge) {
						badgeIndex++;
						display.append(badgeIndex + ".");
						display.append(badge.toString());
					}

			 }
		 });


		 displayMyPerformance =  new JButton("displayMyPerformance");
		 displayMyPerformance.setBounds(207,330,200,300);
		 add(  displayMyPerformance );
		 displayMyPerformance.setVisible(true);

		 displayMyPerformance.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
   // display = new JTextField("");
    //add(display);
 //   display.setBounds(207,315,200,300);
				 display.setText("Performance");

			 }
		 });

		 displayMyBadgeInProgress = new JButton("displayMyBadgeInProgress");
		 displayMyBadgeInProgress.setBounds(207,345,200,300);
		 add( displayMyBadgeInProgress );
		 displayMyBadgeInProgress.setVisible(true);

		 displayMyBadgeInProgress.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
  // display = new JTextField("");
 //  add(display);

				 List<Badge> myBadge  = sw1.DisplayMyBadgeInProcess();
					int badgeIndex = 0;
					for (Badge badge : myBadge) {
						badgeIndex++;
						display.append(badgeIndex + ".");
						display.append(badge.toString());
					}

			 }
		 });

		 createNewBadge =new JButton("createNewBadge");
		 createNewBadge.setBounds(207,340,117,29);
		 add( createNewBadge );
		 createNewBadge .setVisible(true);

		 createNewBadge.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
  // display = new JTextField("");
   //add(display);
   //display.setBounds(207,315,300,200);
				 display.setText("actionPerformed");

			 }
		 });

		 applyForBadge = new JButton("applyForBadge");
		 applyForBadge.setBounds(207,345,300,200);
		 add(applyForBadge );
		 applyForBadge .setVisible(true);

		 applyForBadge.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
   //display = new JTextField("");
   //add(display);
  // display.setBounds(207,315,300,200);
				 display.setText("applyBadge");

			 }
		 });

		 undoMyPreviousAction = new JButton ("undoMyPreviousAction");
		 undoMyPreviousAction.setBounds(207,350,300,200);
		 add(undoMyPreviousAction );
		 undoMyPreviousAction.setVisible(true);

		 undoMyPreviousAction.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent event)
			 {
     // display = new JTextField("");
     // add(display);
     // display.setBounds(207,315,300,200);
				 display.setText("undo Previous Action");

			 }
		 });
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

	}





}