package graphicalUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * Class for Login screen*/
public class SupervisorLoginScreen extends JPanel implements ActionListener{
 private JButton displayLeadingBoard;
 private JButton  displayMyBadge;
 private JButton  displayIncompleteBadges;
 private JButton  displayMyPerformance ;
 private JButton   displayMyBadgeInProgress ;
 private JButton createNewBadge;
 private JButton applyForBadge;
 private JButton undoMyPreviousAction;
 private JTextArea display = new JTextArea(10,40);
/**
* Create the panel.
*/
	public SupervisorLoginScreen () {

		 add(display);


displayLeadingBoard = new JButton("displayLeadingBoard");
displayLeadingBoard.setBounds(207,315,117,29);
add( displayLeadingBoard );
displayLeadingBoard.setVisible(true);

displayLeadingBoard.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent event)
{

   display.setText("leading board");

       }
});

}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

}
}





