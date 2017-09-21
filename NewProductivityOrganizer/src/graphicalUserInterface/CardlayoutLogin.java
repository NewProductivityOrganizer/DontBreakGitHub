package graphicalUserInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class CardlayoutLogin {
	private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JLabel usernameLabel;
	   private JPanel controlPanel;
	   private JPanel controlPanel2;
	   private JPanel controlPanel3;
	   private JLabel  msglabel;
	   private JLabel  passwordLabel;
	   private JPasswordField password;
	   private JTextField username;
	   private JButton displayLeadingBoard;
	   private JButton displayIncompleteBadges;
	   private JButton displayMyPerformance;
	   private JButton displayBadgeInProgress;
	   private JTextArea display;

	
	
	public CardlayoutLogin() {
		 prepareGUI();
		
	}
	public static void main(String[] args){
		CardlayoutLogin swingLayoutDemo = new CardlayoutLogin();  
	      swingLayoutDemo.showCardLayoutDemo();       
	   }
	   private void prepareGUI(){
		   
	      mainFrame = new JFrame("Productivity Incentivizer ");
	      mainFrame.setSize(600,500);
	      mainFrame.setLayout(new GridLayout(4, 1));
	      
	       
	      
	      

	      displayLeadingBoard= new JButton("Leading board");
	      displayIncompleteBadges = new JButton("Incomplete Badges");
	      displayMyPerformance = new JButton("My Performance");
	      displayBadgeInProgress = new JButton("Badge in progress");
	      
	
	      
	      
		  headerLabel = new JLabel("WELCOME",JLabel.CENTER);
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());
	      
	      controlPanel2 = new JPanel();
	      controlPanel2.setLayout(new FlowLayout());
	      
	      
	      controlPanel3 = new JPanel();
	      controlPanel3.setLayout(new FlowLayout());
          
	      mainFrame.add(headerLabel);
	   
	      mainFrame.add(controlPanel); 
	      mainFrame.add(controlPanel2);
	      mainFrame.add(controlPanel3);
	     
	     
	      mainFrame.setVisible(true);  
	   }
	   private void showCardLayoutDemo(){
		   
		   display = new JTextArea();
	     
		     display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		   
			  JScrollPane scrollPane = new JScrollPane(display);
			 
			  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       
		  

	      final JPanel panel = new JPanel();
	      panel.setBackground(Color.CYAN);
	      panel.setSize(500,500);
	      
	      final JPanel creationPanel = new JPanel();
	      creationPanel .setBackground(Color.CYAN);
	      creationPanel .setSize(500,500);
	      
	      
	      final JPanel displayPanel = new JPanel();
	      displayPanel.setBackground(Color.CYAN);
	      displayPanel.setSize(500,500);

	      CardLayout layout = new CardLayout();
	      layout.setHgap(5);
	      layout.setVgap(5);
	      panel.setLayout(layout);
	      

	      CardLayout layout2 = new CardLayout();
	      layout2.setHgap(5);
	      layout2.setVgap(5);
	      creationPanel.setLayout(layout);
	      
	      CardLayout layout3 = new CardLayout();
	      layout3.setHgap(5);
	      layout3.setVgap(5);
	      displayPanel.setLayout(layout);
	      
	      
	      
	      
	       JPanel textArea = new JPanel(new FlowLayout()) ;
	      
	       textArea.add(new JTextArea(10,50));
	      
	      
	      JPanel buttonPanel1 = new JPanel(new FlowLayout());
	      JPanel buttonPanel2 = new JPanel(new FlowLayout());
	      buttonPanel1.add(new JButton("Leading Board"));
	      buttonPanel1.add(new JButton("My Performance"));
	      buttonPanel2.add(new JButton("Create New Badge"));
	      buttonPanel2.add(new JButton("Apply for Badge"));
	      buttonPanel1.add(new JButton("Badge In Progress"));
	      buttonPanel1.add(new JButton("Incomplete Badges"));
	      buttonPanel2.add(new JButton("Undo my previous action"));
	      
	      panel.add("Buttons",buttonPanel1);
	      creationPanel.add("Buttons", buttonPanel2);
	      
	      displayPanel.add("JTextArea",textArea);
	      
	      
	     
	   
	     
	    
	     
	        displayLeadingBoard.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	        	 System.out.println("FOR TEST"); 
	                       
	            }              
	            
	         
	      }); 
	   
	     
	     controlPanel.add(panel);
	     controlPanel2.add( creationPanel);
	     controlPanel3.add(displayPanel);
	      mainFrame.setVisible(true);  
	   }
	}

