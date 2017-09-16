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
/**
 * graphical user interface for system
 * @author palesa
 * */
public class GraphicalUserInterface extends JFrame  {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblPrompt;
	private JButton btnCreateAccount;

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
		
		/**
		 * ActionListener for the create account button*/
		btnCreateAccount.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent event)
			{
			btnLogin.setVisible(true);
	        }
		});
		
		contentPane.add(btnCreateAccount);
		
		
	}

	
}
