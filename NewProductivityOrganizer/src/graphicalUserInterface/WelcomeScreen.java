package graphicalUserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import applogic.ProductivityIncentivizer;

public class WelcomeScreen extends JPanel implements ActionListener{
	private JTextField username;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblPrompt;
	private JButton btnCreateAccount;
	private char[] password;
	private JPanel accountPane;
	private ProductivityIncentivizer mainClass = new ProductivityIncentivizer();
	private JLabel printout;

	public WelcomeScreen() {
		JLabel lblUSERNAME = new JLabel("USERNAME");
		lblUSERNAME.setBounds(20, 315, 181, 16);
		add(lblUSERNAME);
		username = new JTextField(10);
		username.setBounds(140, 148, 135, 32);
		add(username);
		username.setColumns(10);
		JLabel lblPASSWORD = new JLabel("PASSWORD:");
		lblPASSWORD.setBounds(49, 212, 79, 16);
		add(lblPASSWORD);
		passwordField = new JPasswordField(10);
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		add(passwordField);
		btnLogin = new JButton("Login");
		btnLogin.setBounds(162, 261, 96, 22);
		btnLogin.setVisible(true);
		add(btnLogin);
		lblPrompt = new JLabel("Don't have an account?");
		lblPrompt.setBounds(20, 315, 181, 16);
		add(lblPrompt);
		btnCreateAccount = new JButton("Create Account>>");
		btnCreateAccount.setBounds(6, 343, 160, 29);
		add(btnCreateAccount);
		btnCreateAccount.setVisible(true);
		/**
		 * ActionListener for the create account button
		 * Proceed to creating account interface
		 * */
		btnCreateAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				JFrame frame = new JFrame("Productivity Incentivizer");

				CreateAccSupervisor supervisor = new CreateAccSupervisor ();

				frame.setSize(700,400);
				frame.getContentPane().add(supervisor);

				frame.setVisible(true);

				GraphicalUserInterface guiFrame = new GraphicalUserInterface();

				guiFrame.disposeframe();
			}
		});
		/**k
		 * actionListener for Login button
		 * should lead to interface for login
		 * Read in user name and password input
		 * */
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{

				if(event.getSource() == btnLogin) {
					String userName = username.getText();
					System.out.println(userName);
					password = passwordField.getPassword();//password input

					String passwordInString = new String(password);


					String result = mainClass.LogIn(userName,passwordInString);

					JFrame frame = new JFrame("Productivity Incentivizer");


					if (result == "StudentWorker") {
						StudengWorkerLoginScreen student = new StudengWorkerLoginScreen();
						frame.getContentPane().add(student);
						frame.setSize(700,400);
					}
					else if (result == "Supervisor") {
						SupervisorLoginScreen supervisor = new SupervisorLoginScreen();
						frame.getContentPane().add(supervisor);
						frame.setSize(700, 400);
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong UserName or Password!");
						WelcomeScreen back = new WelcomeScreen();
						frame.getContentPane().add(back);
						frame.setSize(700,400);
					}

					frame.setVisible(true);

					GraphicalUserInterface guiFrame = new GraphicalUserInterface();

					guiFrame.disposeframe();
				}
			}
		});

		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(300, 200));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	/*public static void main(String args[]){
	   LoginScreen student = new LoginScreen();


}*/
}