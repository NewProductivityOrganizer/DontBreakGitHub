package graphicalUserInterface;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import applogic.ProductivityIncentivizer;

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
 * then that option, based on the type of account chosen will display the appropriate interface
 * @author palesa
 * */
public class GraphicalUserInterface extends JFrame  {

	private JPanel contentPane;
	private JPanel accountPane;//panel for create account
	private JPanel supervisorAccountPane;//pane for individual accounts
	private JPanel studentworkerAccountPane;
	private JPanel studentLoginPane;
	private JTextField username;
	private JTextField name;
	private JTextField display;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JButton btnLogin;
	private JLabel lblPrompt;
	private JButton btnCreateAccount;
	private JButton btnNext;
	private JButton btnBack;
	private JButton displayLeadingBoard;
	private JButton displayMyBadge;
	private JButton displayIncompleteBadges;
	private JButton displayMyPerformance;
	private JRadioButton supervisorButton;
	private JButton displayMyBadgeInProgress;
	private JButton createNewBadge;
	private JButton applyForBadge;
	private JButton undoMyPreviousAction;
	private JRadioButton studentWorkerButton;
	//private JButton btnNewButton;
	private char [] password;
	
	private ProductivityIncentivizer mainClass = new ProductivityIncentivizer();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface frame = new GraphicalUserInterface();
					//frame.logInStudent();
					frame.welcomeFrame(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GraphicalUserInterface() {
	

	}
	
	
	/**
	 * Create the Welcome frame.
	 * i.e the first thing the User sees when they start running the system
	 */
	public void welcomeFrame(boolean isValid) {
		
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
		
		if (!isValid) {
			lblPrompt = new JLabel("Wrong User Name or Passord");
			lblPrompt.setBounds(49, 110, 200, 16);
			contentPane.add(lblPrompt);
		}
	
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
			   createAccount();
			
	        }
		}); 
		
		/**
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
			      password = passwordField.getPassword();//password input
			     
			      String passwordInString = new String(password);
			      
			      boolean checkValid = mainClass.LogIn(userName, passwordInString);
			      if (!checkValid) {
			    	  welcomeFrame(false);
			      }
			      else {
			    	  logInStudent();
			      }
			      //logInStudent();
			      
			} 
	        }
		}); 
		
		
	}

	/**
	 * Create frame for create account 
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
		 * to proceed to the  next interface(i.e the supervisor account creation interface
		 * * */
	
		
		supervisorButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			if(event.getSource() == supervisorButton) {
				  btnNext.setVisible(true);
				  btnBack.setVisible(false);
				  supervisorAccount();//method that creates the supervisor account create interface
				  
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
			  }
	        }
		});
		
		
		
		
		/**
		 * Action Listener for back button
		 * Move back to previous interface
		 * */
		
		btnBack = new JButton("<<Back");
		btnBack.setBounds(207,343,117,29);
		accountPane.add(btnBack);
		btnBack.setVisible(true);

	
	
		btnBack.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				
				welcomeFrame(true) ;//supposed to go back to the welcome frame
		
	        }
		});
		

		
	}
	
public void logInStudent() {
	
	setTitle("Welcome Back!");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(200, 200,900, 600);
	studentLoginPane = new JPanel();
	studentLoginPane.setBackground(new Color(205, 133, 63));
	studentLoginPane.setForeground(Color.BLACK);
	studentLoginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(studentLoginPane);
	studentLoginPane.setLayout(null);
	
	

	JLabel lblTitle = new JLabel(" WELCOME BACK  ");
	lblTitle.setBounds(400, 70, 338, 38);
	lblTitle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
	studentLoginPane.add(lblTitle);
	
	
	display = new JTextField(" ");
	display.setBounds(400, 148, 400, 300);
	studentLoginPane.add(display);
	display.setColumns(10);
	
	
	 displayLeadingBoard = new JButton("displayLeadingBoard");
	 displayLeadingBoard.setBounds(58,150,200,29);
	 studentLoginPane.add( displayLeadingBoard );
	 displayLeadingBoard.setVisible(true);
	 
	
	 
	 displayMyBadge = new JButton("displayMyBadges");
	 displayMyBadge.setBounds(60,250,200,29);
	 studentLoginPane.add( displayMyBadge );
	 displayMyBadge.setVisible(true);
	 
	 
     
	 displayIncompleteBadges = new JButton("displayIncompleteBadge");
	 displayIncompleteBadges.setBounds(62,200,200,29);
	 studentLoginPane.add( displayIncompleteBadges );
	 displayIncompleteBadges.setVisible(true);
	 
     
     
     displayMyPerformance =  new JButton("displayMyPerformance"); 
     displayMyPerformance.setBounds(64,250,200,29);
	 studentLoginPane.add(  displayMyPerformance );
	  displayMyPerformance.setVisible(true);
     
	 
	 
	 displayMyBadgeInProgress = new JButton("displayMyBadgeInProgress");
	 displayMyBadgeInProgress.setBounds(66,300,200,29);
	 studentLoginPane.add( displayMyBadgeInProgress );
	 displayMyBadgeInProgress.setVisible(true);
	    
	 
	 
	 
	 
	 createNewBadge =new JButton("createNewBadge");
	 createNewBadge.setBounds(68,350,200,29);
	 studentLoginPane.add( createNewBadge );
	 createNewBadge .setVisible(true);
	  
	 
	 
	 
	 
	    applyForBadge = new JButton("applyForBadge");
	    applyForBadge.setBounds(70,400,200,29);
	    studentLoginPane.add(applyForBadge );
		applyForBadge .setVisible(true);
	 
	  
	    undoMyPreviousAction = new JButton ("undoMyPreviousAction");
	    undoMyPreviousAction.setBounds(72,450,200,29);
	    studentLoginPane.add(undoMyPreviousAction);
	    undoMyPreviousAction.setVisible(true);
	
	    
	    /**
	     * Action Listener for displayBadge button
	     * */
	    displayMyBadge.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "Display List of Badges";
				display.setText(words);
				 
		
	        }
		});
	    
	    
	    displayLeadingBoard.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "Display Leading";
				display.setText(words);
				 
		
	        }
		});
	    
	    displayIncompleteBadges.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "Incomplete Badges";
				display.setText(words);
				 
		
	        }
		});
	    
	    displayMyPerformance.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "display performance";
				display.setText(words);
				 
		
	        }
		});   
	    
	    displayMyBadgeInProgress.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "Badge(s) in progress";
				display.setText(words);
				 
		
	        }
		});    
	    
	   createNewBadge.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "Create new badge";
				display.setText(words);
				 
		
	        }
		});
	   
	   applyForBadge.addActionListener(new ActionListener()
	 		{
	 			
	 			public void actionPerformed(ActionEvent event)
	 			{
	 				display.setText("");
	 				String words = "Apply for badge";
	 				display.setText(words);
	 				 
	 		
	 	        }
	 		});  
	   
	   undoMyPreviousAction.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
				String words = "Undo  previous action";
				display.setText(words);
				 
		
	        }
		});  
  
	   
	    
	    
	    
	
}
	/**
	 * Create interface for supervisor Account
	 * */
	public void supervisorAccount() {
		setTitle("Create supervisor Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,200,400, 450);
		supervisorAccountPane = new JPanel();
		supervisorAccountPane.setBackground(new Color(205, 133, 63));
		supervisorAccountPane.setForeground(Color.BLACK);
		supervisorAccountPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(supervisorAccountPane);
		supervisorAccountPane.setLayout(null);
		

		JLabel lblRoleSelection = new JLabel("Create account below");
		lblRoleSelection.setBounds(140,40, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		supervisorAccountPane.add(lblRoleSelection);
		
		

		JLabel lblActualName = new JLabel("NAME:");
		lblActualName.setBounds(49, 123, 79, 16);
		supervisorAccountPane.add(lblActualName);
		
		JLabel lblUSERNAME = new JLabel("USERNAME:");
		lblUSERNAME.setBounds(49, 150, 79, 16);
		supervisorAccountPane.add(lblUSERNAME);
		
		name = new JTextField();
		name.setBounds(140, 120, 135, 32);
		supervisorAccountPane.add(name);
	    name.setColumns(10);
		
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
		
		
		JLabel lblVerify = new JLabel("CONFIRM PASSWORD:");
		lblVerify .setBounds(49,260, 200, 16);
		supervisorAccountPane.add(lblVerify);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(140,270, 135, 32);
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
		
		
		/**
		 * Action Listener for the back button*/
		btnBack.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				btnNext.setVisible(false);
				createAccount();
			
				 
		
	        }
		});
		
	
		
	}
	/**
	 * Create frame for student worker account
	 * */
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
		

		JLabel lblRoleSelection = new JLabel("Create account below");
		lblRoleSelection.setBounds(6, 70, 338, 38);
		lblRoleSelection.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		studentworkerAccountPane.add(lblRoleSelection);
		
		
		JLabel lblActualName = new JLabel("NAME:");
		lblActualName.setBounds(49, 123, 79, 16);
		studentworkerAccountPane.add(lblActualName);
		
		name = new JTextField();
		name.setBounds(140, 120, 135, 32);
		studentworkerAccountPane.add(name);
	    name.setColumns(10);
		
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
		
		
		JLabel lblVerify = new JLabel("CONFIRM PASSWORD:");
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
		
		btnBack.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
				
				createAccount(); 
		
	        }
		});
	
	}

	
	
	
	
}
