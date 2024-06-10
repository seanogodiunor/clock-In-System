package clockIn;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;

public class resgisterScreen extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField usernameTextField;
	
	private JPasswordField passwordTextField;
	private JPasswordField passwordTextField1;
	
	  JButton backButton;
	private JButton registerButton;
	
	private JTextPane txtpnMandotory1;
	private JTextPane txtpnMandotory2;
	
	private JCheckBox chckbxNewCheckBox;
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel passwordLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel1;
	private JLabel detailsLabel;
	
	
	private JProgressBar progressBar;

	
	static logInScreen loginScreen = new logInScreen();
	static resgisterScreen register = new resgisterScreen();

	
	backEndCode code = new backEndCode();
	mySQLDataBase dataBase = new mySQLDataBase();
	
	
	
	// VARIABLE
	int res;
	
	String firstname;
	String lastname;
	String username;
	
	char[] password;
	char[] confirmpassword;
	
	String regex1;
	String regex2;
	
	Pattern pattern1;
	Pattern pattern2;
	
	Matcher matcher1;
	Matcher matcher2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public resgisterScreen() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(16, 18, 411, 542);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		firstNameLabel = new JLabel("First Name *");
		firstNameLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		firstNameLabel.setBounds(6, 39, 101, 16);
		panel.add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last Name *");
		lastNameLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		lastNameLabel.setBounds(6, 86, 94, 16);
		panel.add(lastNameLabel);
		
		usernameLabel = new JLabel("Username *");
		usernameLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		usernameLabel.setBounds(6, 130, 94, 16);
		panel.add(usernameLabel);
		
		passwordLabel = new JLabel("Password **");
		passwordLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		passwordLabel.setBounds(6, 209, 94, 16);
		panel.add(passwordLabel);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 13));
		firstNameTextField.setBounds(145, 34, 223, 32);
		panel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		
		lastNameTextField = new JTextField();
		lastNameTextField.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 13));
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(145, 81, 223, 32);
		panel.add(lastNameTextField);
		
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 13));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(145, 125, 223, 32);
		panel.add(usernameTextField);
		
		
		
		passwordTextField1 = new JPasswordField();
		passwordTextField1.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 12));
		passwordTextField1.setBounds(142, 309, 223, 32);
		panel.add(passwordTextField1);
		
		
		
		
		// BACK BUTTON
		backButton = new JButton("Back");
		backButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		backButton.setBounds(61, 451, 117, 29);
		panel.add(backButton);
		
		
		// REGISTER BUTTON
		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				firstname = firstNameTextField.getText();
				lastname = lastNameTextField.getText();
				username = usernameTextField.getText();
				
				password = passwordTextField.getPassword();
				confirmpassword = passwordTextField1.getPassword();
				
				
				// CHECK NUMBERS und CHECK SPECIAL CHARATERS
				regex1 = ".*\\d.*";
				regex2 = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
				
				pattern1 = Pattern.compile(regex1);
				pattern2 = Pattern.compile(regex2);
				
				matcher1 = pattern1.matcher(String.valueOf(password));
				matcher2 = pattern2.matcher(String.valueOf(password));
				
				
			
				
				if (!firstname.isEmpty() && !lastname.isEmpty() && !username.isEmpty() && password.length != 0 && confirmpassword.length != 0)  {
					
					if (password.length >= 5 && confirmpassword.length >= 5) {
						
					
						if (matcher1.matches() && matcher2.matches()) {
							
						
							System.out.println("correct");
							
							
							if (Arrays.equals(password, confirmpassword)) {
								
								
								
								
								dataBase.isConnected();
								try {
									dataBase.connect();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								String passwordString = String.valueOf(password);
								dataBase.register(firstname, lastname, username, passwordString);
								
								
								JOptionPane.showMessageDialog(null, "Registration Sucessful");
								
								
								res = JOptionPane.showOptionDialog(
										
										null,"Login ?", "Confirmation", 
										JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, null, null
										
										);
								
								
								 if (res == 0) {
										
										loginScreen.setVisible(true);
										// CLOSE THR REGISTRATION SCREEN
										
										register.setVisible(false);
										
									}else {
										System.exit(0);
									}
								
								
								
								
							}else {
								detailsLabel.setText("Password doesnt match");
							}
							
						}else {
							detailsLabel.setText("Password Not strong enough");
							System.out.println("Password Not strong enough");
							
						}
					
					}else {
						detailsLabel.setText("Password should be at least 5 latters");

					}	
					
					
				}else {
					detailsLabel.setText("Fill in all blank space");

				}
				
			}
		});
		registerButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		registerButton.setBounds(224, 451, 117, 29);
		panel.add(registerButton);
		
		
		
		txtpnMandotory1 = new JTextPane();
		txtpnMandotory1.setEnabled(false);
		txtpnMandotory1.setEditable(false);
		txtpnMandotory1.setText("* mandatory, can not be empty");
		txtpnMandotory1.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 11));
		txtpnMandotory1.setBackground(new Color(238, 237, 238));
		txtpnMandotory1.setBounds(155, 158, 210, 36);
		panel.add(txtpnMandotory1);
		
		txtpnMandotory2 = new JTextPane();
		txtpnMandotory2.setEnabled(false);
		txtpnMandotory2.setEditable(false);
		txtpnMandotory2.setText("** your password should be atleast 5 latters, inclube number and special charachter");
		txtpnMandotory2.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 11));
		txtpnMandotory2.setBackground(new Color(238, 237, 238));
		txtpnMandotory2.setBounds(155, 261, 210, 36);
		panel.add(txtpnMandotory2);
		
		passwordLabel1 = new JLabel("cofirm Password");
		passwordLabel1.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 14));
		passwordLabel1.setBounds(6, 323, 127, 16);
		panel.add(passwordLabel1);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("Hiragino Sans CNS", Font.PLAIN, 12));
		
		passwordTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				
				char[] password = passwordTextField.getPassword();
		        String passwordStr = new String(password);

		        if (passwordStr.matches(".*\\d.*") && passwordStr.matches(".*[^a-zA-Z0-9].*") && passwordStr.length() > 7) {
		            // Password meets the requirements
		        	
					progressBar.setValue(100);
					int getValu = progressBar.getValue();
					
					
		        } else {
		            // Password does not meet the requirements
					progressBar.setValue(0);
		        }
				
				
				
				
				// If password contaings Charater and number increase by 10
					
			}
		});
		
		passwordTextField.setBounds(145, 206, 223, 32);
		panel.add(passwordTextField);
		
		detailsLabel = new JLabel("");
		detailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		detailsLabel.setBounds(6, 401, 399, 16);
		detailsLabel.setForeground (Color.red);
		panel.add(detailsLabel);
		
		chckbxNewCheckBox = new JCheckBox("show password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (chckbxNewCheckBox.isSelected()) {
					passwordTextField.setEchoChar((char)0);
					passwordTextField1.setEchoChar((char)0);

				}else {
					
					passwordTextField.setEchoChar('●');
					passwordTextField1.setEchoChar('●');
				}
				
			}
		});
		
		chckbxNewCheckBox.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 11));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbxNewCheckBox.setBounds(181, 344, 187, 23);
		panel.add(chckbxNewCheckBox);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		progressBar.setBounds(145, 239, 210, 20);
		panel.add(progressBar);
		
		
	}
}
