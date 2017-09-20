package graphicalUserInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class CreateAdminAcc extends JPanel implements ActionListener{

	public CreateAdminAcc() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
	    setLayout(null);

	    JRadioButton supervisorButton= new JRadioButton("Supervisor");
		supervisorButton.setBounds(140, 148, 135, 32);
	    add(supervisorButton);


	    JRadioButton studentWorkerButton= new JRadioButton("Student Worker");
		studentWorkerButton.setBounds(140, 207, 135, 32);
		add(studentWorkerButton);

		ButtonGroup optionsButtonGroup= new ButtonGroup();
		optionsButtonGroup.add(studentWorkerButton);
		optionsButtonGroup.add(supervisorButton);

		JLabel lblRoleSelection = new JLabel("      Create Account");
		lblRoleSelection.setBounds(6, 70, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));

		add(lblRoleSelection);
		JButton btnNext = new JButton("Proceed>>");
		btnNext.setBounds(6, 343, 160, 29);
		add(btnNext);

		/**
		* Action Listener for proceed button
		* to proceed to the  next interface(i.e the supervisor account creation interface
		* * */
		supervisorButton.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent event)
		{
		if(event.getSource() == supervisorButton) {
		/*
			JFrame frame = new JFrame("Productivity Incentivizer");

			CreateAccSupervisor supervisor = new CreateAccSupervisor ();

			 frame.setSize(700,400);
			 frame.getContentPane().add(supervisor);

			 frame.setVisible(true);

			 GUI guiFrame = new GUI();

			 guiFrame.disposeframe();*/

		  }
		        }
		});
		studentWorkerButton.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent event)
		{
		if(event.getSource() == studentWorkerButton) {



		  }
		        }
		});
		/**
		* Action Listener for back button
		* Move back to previous interface
		* */
		JButton btnBack = new JButton("<<Back");
		btnBack.setBounds(207,343,117,29);
	    add(btnBack);
		btnBack.setVisible(true);

		btnBack.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent event)
		{
		//
		//welcomeFrame() ;//supposed to go back to the welcome frame
		        }
		});





	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
