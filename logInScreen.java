package clockIn;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Window.Type;

public class logInScreen extends JFrame {

	private JPanel contentPane;
	private Panel panel;
	private JPasswordField passwordField;

	private JButton registerButton;
	private JButton loggInButton;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel messageLabel;
	
	private JCheckBox showPasswordCheckBox;

	
	backEndCode code = new backEndCode();
	mySQLDataBase dataBase = new mySQLDataBase();
	
	
	static logInScreen loginScreen = new logInScreen();
	static clockInScreen clockin = new clockInScreen();
	static resgisterScreen register = new resgisterScreen();
	private JTextField usernametField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginScreen.setVisible(true);
					
					 register.backButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							register.setVisible(false);
							loginScreen.setVisible(true);
						}
					});
					 
					 
//					int optionPanel = register.res;
//					 if (optionPanel == 0) {
//							
//							loginScreen.setVisible(true);
//							register.setVisible(false);
//							
//						}else {
//							System.exit(0);
//						}
						
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public logInScreen() {
		setTitle("Login");
		setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// LOGIN PANEL
		panel = new Panel();
		panel.setBounds(16, 33, 378, 231);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// LOGG IN BUTTON
		loggInButton = new JButton("Loggin");
		loggInButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		loggInButton.setBounds(71, 185, 84, 29);
		panel.add(loggInButton);
		loggInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernametField.getText();
				char[] passwordCha = passwordField.getPassword();
				String password = String.valueOf(passwordCha);
				

				dataBase.isConnected();
				
				try {
					dataBase.connect();
				} catch (ClassNotFoundException e1) {
					
					messageLabel.setText("Server is down. Try again later");
					e1.printStackTrace();
				}
				
				boolean susseful = dataBase.login(username, password);
				
				if (susseful){
					// enable the new screen
					clockin.setVisible(true);
					
					// disable the login screen
					loginScreen.setVisible(false);
					
					//dataBase.disConnected();

				}else {
					
					usernametField.setText("");
					passwordField.setText("");
					
					messageLabel.setText("Wrong Password or Username");
					

					}
					
				}	

			});
		
		
		// REGISTER BUTTON
		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				register.setVisible(true);
				loginScreen.setVisible(false);
				
			}
		});
		registerButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		registerButton.setBounds(222, 185, 84, 29);
		panel.add(registerButton);
		
		
		
		// MESSAGE LABEL
		messageLabel = new JLabel("");
		messageLabel.setBounds(6, 147, 362, 16);
		panel.add(messageLabel);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(Color.RED);
		messageLabel.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 11));
		
		
		// PASSWORD TEXT FIELD
		passwordField = new JPasswordField();
		passwordField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 11));
		passwordField.setBounds(99, 81, 184, 26);
		panel.add(passwordField);
		
		// USERNAME TEXT FIELD
		usernametField = new JTextField();
		usernametField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		usernametField.setColumns(10);
		usernametField.setBounds(99, 38, 184, 26);
		panel.add(usernametField);
		
		
		// USERNAME LABEL
		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		usernameLabel.setBounds(105, 10, 175, 16);
		panel.add(usernameLabel);
		
		// PASSWORD LABEL
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		passwordLabel.setBounds(105, 63, 175, 16);
		panel.add(passwordLabel);
		
		// CHECKBOX
		showPasswordCheckBox = new JCheckBox("show password");
		showPasswordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (showPasswordCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
					
				}else {
					passwordField.setEchoChar('●');

				}
			}
		});
		showPasswordCheckBox.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		showPasswordCheckBox.setBounds(99, 112, 129, 23);
		
		panel.add(showPasswordCheckBox);
		
		
		
		}
	
}



















