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

import javax.swing.ButtonGroup;
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
	private JPanel supervisorAccountPane;//pane for individual accounts
	private JPanel studentworkerAccountPane;
	private JTextField username;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JButton btnLogin;
	private JLabel lblPrompt;
	private JButton btnCreateAccount;
	private JButton btnNext;
	private JButton btnBack;
	private JRadioButton supervisorButton;
	private JRadioButton studentWorkerButton;
	private JButton btnNewButton;
	private char [] password;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

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
		
		username = new JTextField(" ");
		username.setBounds(140, 148, 135, 32);
		contentPane.add(username);
		username.setColumns(10);
		
		
		
		
		JLabel lblUSERNAME = new JLabel("USERNAME");
		lblUSERNAME.setBounds(49, 156, 79, 16);
		contentPane.add(lblUSERNAME);
	
		
		
		
		
		JLabel lblPASSWORD = new JLabel("PASSWORD:");
		lblPASSWORD.setBounds(49, 212, 79, 16);
		contentPane.add(lblPASSWORD);
	
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		 password = passwordField.getPassword();
		
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(162, 261, 96, 22);
		btnLogin.setVisible(true);
	
		contentPane.add(btnLogin);
		
		lblPrompt = new JLabel("Don't have an account?");
		lblPrompt.setBounds(20, 315, 181, 16);
		contentPane.add(lblPrompt);
	
		
		btnCreateAccount = new JButton("Create Account>>");
		btnCreateAccount.setBounds(6, 343, 160, 29);
		contentPane.add(btnCreateAccount);
		btnCreateAccount.setVisible(true);
	
	
		
		/**
		 * ActionListener for the create account button
		 * Proceed to creating account interface
		 * */
		
		btnCreateAccount.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			   btnLogin.setVisible(false);
			    createAccount();//method to take to create account window
			
	        }
		});
		
		/**
		 * actionListener for Login button
		 * should lead to interface for login
		 * make template
		 * */
		btnLogin.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			   btnLogin.setVisible(false);
			    createAccount();//method to take to create account window
			
	        }
		});
		
		

	}
	/**
	 * GUI for create account 
	 * with options to choose to create a student worker account or a supervisor account
	 * */
	
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
		
		ButtonGroup optionsButtonGroup= new ButtonGroup();
		optionsButtonGroup.add(studentWorkerButton);
		optionsButtonGroup.add(supervisorButton);
		
		
		
		
		JLabel lblRoleSelection = new JLabel("      Create Account");
		lblRoleSelection.setBounds(6, 70, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		accountPane.add(lblRoleSelection);
		
	
		
		btnNext = new JButton("Proceed>>");
		btnNext.setBounds(6, 343, 160, 29);
		accountPane.add(btnNext);
		btnNext.setVisible(false);
		
		
		/**
		 * Action Listener for proceed button
		 * FIX this
		 * */
	
		
		supervisorButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			if(event.getSource() == supervisorButton) {
				  btnNext.setVisible(true);
				  btnBack.setVisible(false);
				  supervisorAccount();//method to take to create account window
				  System.out.println("TEST");
			  }
	        }
		});
		
		
		studentWorkerButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			if(event.getSource() == studentWorkerButton) {
				  btnNext.setVisible(true);
				  btnBack.setVisible(false);
				  studentworkerAccount();//method to take to create account window
				  System.out.println("TEST");
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
		btnBack.setVisible(true);
/**
 * FIX THIS */
	
		btnBack.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				btnNext.setVisible(false);
				System.out.println("supposed to be Back");
				 
		
	        }
		});
		

		
	}
	
	
	/**
	 * GUI based on option chosen in previous GUI
	 * */
	public void supervisorAccount() {
		setTitle("Create Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,400, 450);
		supervisorAccountPane = new JPanel();
		supervisorAccountPane.setBackground(new Color(205, 133, 63));
		supervisorAccountPane.setForeground(Color.BLACK);
		supervisorAccountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(supervisorAccountPane);
		supervisorAccountPane.setLayout(null);
		

		JLabel lblRoleSelection = new JLabel("Create account below");
		lblRoleSelection.setBounds(6, 70, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		supervisorAccountPane.add(lblRoleSelection);
		
		
		JLabel lblUSERNAME = new JLabel("USERNAME:");
		lblUSERNAME.setBounds(49, 156, 79, 16);
		supervisorAccountPane.add(lblUSERNAME);
		
		
		username = new JTextField();
		username.setBounds(140, 148, 135, 32);
		supervisorAccountPane.add(username);
		username.setColumns(10);
		
		
		JLabel lblPASSWORD = new JLabel("PASSWORD:");
		lblPASSWORD.setBounds(49, 212, 79, 16);
		supervisorAccountPane.add(lblPASSWORD);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		supervisorAccountPane.add(passwordField);
		
		
		JLabel lblVerify = new JLabel("VERIFY PASSWORD:");
		lblVerify .setBounds(49,300, 200, 16);
		supervisorAccountPane.add(lblVerify);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(150,312, 135, 32);
		passwordField2.setEchoChar('*');
		supervisorAccountPane.add(passwordField2);
		

		btnNext = new JButton("Confirm");
		btnNext.setBounds(6, 350, 160, 29);
		supervisorAccountPane.add(btnNext);
		btnNext.setVisible(true);
		
		btnBack = new JButton("<<Back");
		btnBack.setBounds(207,350,117,29);
		supervisorAccountPane.add(btnBack);
		btnBack.setVisible(true);
		
		
		
		
	}
	
	public void studentworkerAccount() {
		setTitle("Create Student Worker Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200,350, 400);
		studentworkerAccountPane = new JPanel();
		studentworkerAccountPane.setBackground(new Color(205, 133, 63));
		studentworkerAccountPane.setForeground(Color.BLACK);
		studentworkerAccountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(studentworkerAccountPane);
		studentworkerAccountPane.setLayout(null);
		

		
		
		
		JLabel lblSUSERNAME = new JLabel("USERNAME:");
		lblSUSERNAME.setBounds(49, 156, 79, 16);
		studentworkerAccountPane.add(lblSUSERNAME);
		
		
		username = new JTextField();
		username.setBounds(140, 148, 135, 32);
		studentworkerAccountPane.add(username);
		username.setColumns(10);
		
		
		JLabel lblSPASSWORD = new JLabel("PASSWORD:");
		lblSPASSWORD.setBounds(49, 212, 79, 16);
		studentworkerAccountPane.add(lblSPASSWORD);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(140, 207, 135, 32);
		passwordField.setEchoChar('*');
		studentworkerAccountPane.add(passwordField);
		
		
		JLabel lblVerify = new JLabel("VERIFY PASSWORD:");
		lblVerify .setBounds(49, 300, 200, 16);
		studentworkerAccountPane.add(lblVerify);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 312, 135, 32);
		passwordField.setEchoChar('*');
		studentworkerAccountPane.add(passwordField);
		
		
		btnNext = new JButton("Confirm");
		btnNext.setBounds(6, 350, 160, 29);
		studentworkerAccountPane.add(btnNext);
		btnNext.setVisible(true);
		
		btnBack = new JButton("<<Back");
		btnBack.setBounds(207,350,117,29);
		studentworkerAccountPane.add(btnBack);
		btnBack.setVisible(true);
		
		
		
		
	}
	
	
	/**
	 * TO DO FOR THE CLASS:
	 * REFACTOR TO REMOVE REPETITION, NO NEED TO HAVE SOME VARIABLES RENAMES
	 * */
	
	
	
}
