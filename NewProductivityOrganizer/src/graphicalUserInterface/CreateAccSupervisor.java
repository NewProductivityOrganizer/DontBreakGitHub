package graphicalUserInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateAccSupervisor extends JPanel implements ActionListener{

	public CreateAccSupervisor() {

		setBounds(200,200,400, 450);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblRoleSelection = new JLabel("Create account below");
		lblRoleSelection.setBounds(140,40, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		add(lblRoleSelection);

		JLabel lblActualName = new JLabel("NAME:");
		lblActualName.setBounds(49, 123, 79, 16);
		add(lblActualName);


		JLabel lblUSERNAME = new JLabel("USERNAME:");
		lblUSERNAME.setBounds(49, 150, 79, 16);
		add(lblUSERNAME);



		JTextField name = new JTextField("");
		name.setBounds(140, 120, 135, 32);
		add(name);
		name.setColumns(10);


		JTextField username = new JTextField();
		username.setBounds(140, 148, 135, 32);
		add(username);
		username.setColumns(10);


		JLabel lblPASSWORD = new JLabel("PASSWORD:");
		lblPASSWORD.setBounds(49, 212, 79, 16);
		add(lblPASSWORD);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		add(passwordField);

		JLabel lblVerify = new JLabel("CONFIRM PASSWORD:");
		lblVerify .setBounds(49,260, 200, 16);
		add(lblVerify);

		JPasswordField passwordField2 = new JPasswordField();
		passwordField2.setBounds(140,270, 135, 32);
		passwordField2.setEchoChar('*');
		add(passwordField2);

		JButton btnNext = new JButton("Confirm");
		btnNext.setBounds(6, 350, 160, 29);
		add(btnNext);

		JButton btnBack = new JButton("<<Back");
		btnBack.setBounds(207,350,117,29);
		add(btnBack);

		/**
		 * Action Listener for the back button*/
		btnNext.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				JFrame frame = new JFrame("Productivity Incentivizer");

				JTextField display = new JTextField();
				display.setBounds(150, 250, 135, 32);
				display.setColumns(10);
				display.setText("Successfully created");
				add(display);


				GraphicalUserInterface guiFrame = new GraphicalUserInterface();
				guiFrame.disposeframe();

			}
		});

		/*	@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
		});*/









	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
