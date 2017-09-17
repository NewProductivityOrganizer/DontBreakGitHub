package graphicalUserInterface;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
/**
 * graphical user interface for the system
 * currently displays login screen at first run
 * if logging in for the first time, create account button will call a method that has 
 * as frame with option to create an account
 * @author palesa
 * */
public class GraphicalUserInterface extends JFrame  {

	private JPanel contentPane;
	private JPanel accountPane;//panel for create account
	private JPanel individualAccountPane;//pane for individual accounts
	private JTextField username;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblPrompt;
	private JButton btnCreateAccount;
	private JButton btnNext;
	private JButton btnBack;
	private JRadioButton supervisorButton;
	private JRadioButton studentWorkerButton;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface frame = new GraphicalUserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraphicalUserInterface() {
		setTitle("Productivity Incentivizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200,350, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(205, 133, 63));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("                               WELCOME");
		lblNewLabel.setBounds(6, 70, 338, 38);
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(140, 148, 135, 32);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUSERNAME = new JLabel("USERNAME:");
		lblUSERNAME.setBounds(49, 156, 79, 16);
		contentPane.add(lblUSERNAME);
		
		JLabel lblPASSWORD = new JLabel("PASSWORD:");
		lblPASSWORD.setBounds(49, 212, 79, 16);
		contentPane.add(lblPASSWORD);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(162, 261, 96, 22);
		btnLogin.setVisible(false);
		contentPane.add(btnLogin);
		
		lblPrompt = new JLabel("Don't have an account?");
		lblPrompt.setBounds(20, 315, 181, 16);
		contentPane.add(lblPrompt);
		
		btnCreateAccount = new JButton("Create Account>>");
		btnCreateAccount.setBounds(6, 343, 160, 29);
		contentPane.add(btnCreateAccount);
		
		
		
		/**
		 * ActionListener for the create account button*/
		
		btnCreateAccount.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				btnLogin.setVisible(true);
			    createAccount();//method to take to create account window
	        }
		});
		
		
		
		
		
	}
	
	public void createAccount() {
		setTitle("Productivity Incentivizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200,350, 400);
		accountPane = new JPanel();
		accountPane.setBackground(new Color(205, 133, 63));
		accountPane.setForeground(Color.BLACK);
		accountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(accountPane);
		accountPane.setLayout(null);
		
		
		supervisorButton= new JRadioButton("Supervisor");
		supervisorButton.setBounds(140, 148, 135, 32);
		accountPane.add(supervisorButton);
		
		studentWorkerButton= new JRadioButton("Student Worker");
		studentWorkerButton.setBounds(140, 207, 135, 32);
		accountPane.add(studentWorkerButton);
		
		
		JLabel lblRoleSelection = new JLabel("      Select your role below:");
		lblRoleSelection.setBounds(6, 70, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		accountPane.add(lblRoleSelection);
		
	
		
		btnNext = new JButton("Proceed>>");
		btnNext.setBounds(6, 343, 160, 29);
		accountPane.add(btnNext);
		btnNext.setVisible(true);
		
		
		/**
		 * Action Listener for proceed button
		 * FIX this
		 * */
		
		btnNext.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			  if((event.getSource()==supervisorButton) ||(event.getSource() == studentWorkerButton)) {
				  btnNext.setVisible(false);
			      individualAccount();//method to take to create account window
			  }
	        }
		});
		
		
		/**
		 * Action Listener for back button
		 * FIX this
		 * */
		
		btnBack = new JButton("<<Back");
		btnBack.setBounds(207,343,117,29);
		accountPane.add(btnBack);

	
		btnBack.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				
				
	        }
		});
		
		
		
		
		
		
		
	}
	
	
	
	public void individualAccount() {
		setTitle("Create Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200,350, 400);
		individualAccountPane = new JPanel();
		individualAccountPane.setBackground(new Color(205, 133, 63));
		individualAccountPane.setForeground(Color.BLACK);
		individualAccountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(individualAccountPane);
		individualAccountPane.setLayout(null);
		

		JLabel lblRoleSelection = new JLabel("Enter details below");
		lblRoleSelection.setBounds(6, 70, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		individualAccountPane.add(lblRoleSelection);
		
		
		JLabel lblUSERNAME = new JLabel("USERNAME:");
		lblUSERNAME.setBounds(49, 156, 79, 16);
		contentPane.add(lblUSERNAME);
		
		
		username = new JTextField();
		username.setBounds(140, 148, 135, 32);
		individualAccountPane.add(username);
		username.setColumns(10);
		
		
		JLabel lblPASSWORD = new JLabel("PASSWORD:");
		lblPASSWORD.setBounds(49, 212, 79, 16);
		individualAccountPane.add(lblPASSWORD);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		individualAccountPane.add(passwordField);
		
		
		JLabel lblVerify = new JLabel("VERIFY PASSWORD:");
		lblVerify .setBounds(49, 200, 79, 16);
		individualAccountPane.add(lblVerify);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		individualAccountPane.add(passwordField);
		
		
		
		
		
		
	}
	

	
}
