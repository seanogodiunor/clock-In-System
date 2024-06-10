package clockIn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Label;

public class clockInScreen extends JFrame {

	private JPanel contentPane;
	private JPanel checkInPanel;
	private JPanel calendarPanel;
	private JPanel resultPanel;
	private Panel userInfoPanel;

	
	private JTextField kommtTextField;
	private JTextField pauseAnfangetextField;
	private JTextField pauseEndetextField;
	private JTextField gehtTextField;
	private JTextField arbeitZeitTextField;
	private JTextField aktuelleUhrzeiTtextField;
	private JTextField tagesDatumTextField;
	private JTextField pauserDauerTextField;

	private JLabel aktuelleUhrzeitLabel;
	private JLabel tagesDatumLabel;
	private JLabel pauseDauerLabel;
	private JLabel arbeitZeitLabel;
	private JLabel userLabel;
	static 	JLabel userInfoLabel;

	
	private JButton kommtButton;
	private JButton pauseAnfangButton;
	private JButton pauseEndeButton;
	private JButton gehtButton;
	private JButton logoutButton;

	
	
	// KOMMT
	int kommtMins;
	int kommtHour;
	// GEHT
	int gehtHour;
	int gehtMins;
	// PAUSE ANFANG
	int	pauseAnfangHour;
	int pauseAnfangMins;
	// PAUSE ENDE
	int pauseEndeHour;
	int	pauseEndeMins;
	// PAUSE ENDE RECHNUNG
	int pauseHourRechnung;
	int pauseMinsRechnung;
	
	
	backEndCode code = new backEndCode();
	mySQLDataBase dataBase = new mySQLDataBase();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clockInScreen frame = new clockInScreen();					
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
	public clockInScreen() {
		setTitle("Clock in Mashine");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// CALENDAR PANEL
		calendarPanel = new JPanel();
		calendarPanel.setBounds(34, 88, 392, 101);
		contentPane.add(calendarPanel);
		calendarPanel.setLayout(null);
		
		// CHECK IN PANEL
		checkInPanel = new JPanel();
		checkInPanel.setBounds(34, 229, 386, 168);
		contentPane.add(checkInPanel);
		checkInPanel.setLayout(null);
		
		// RESULT PANEL
		resultPanel = new JPanel();
		resultPanel.setBounds(34, 442, 392, 112);
		contentPane.add(resultPanel);
		resultPanel.setLayout(null);
		
		// UHRZEIT LABEL
		aktuelleUhrzeitLabel = new JLabel("Aktuelle Uhrzeit");
		aktuelleUhrzeitLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		aktuelleUhrzeitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aktuelleUhrzeitLabel.setBounds(6, 6, 380, 16);
		calendarPanel.add(aktuelleUhrzeitLabel);
		
		// DATUM LABEL
		tagesDatumLabel = new JLabel("Tagesdatum");
		tagesDatumLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		tagesDatumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tagesDatumLabel.setBounds(6, 52, 380, 16);
		calendarPanel.add(tagesDatumLabel);
		
		// UHRZEIT TEXT FIELD
		aktuelleUhrzeiTtextField = new JTextField();
		aktuelleUhrzeiTtextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 13));
		aktuelleUhrzeiTtextField.setHorizontalAlignment(SwingConstants.CENTER);
		aktuelleUhrzeiTtextField.setEnabled(false);
		aktuelleUhrzeiTtextField.setEditable(false);
		aktuelleUhrzeiTtextField.setColumns(10);
		aktuelleUhrzeiTtextField.setBackground(new Color(239, 238, 237));
		aktuelleUhrzeiTtextField.setBounds(6, 23, 377, 29);
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Die label soll jedesmal hier aktualiert werden!!!
				aktuelleUhrzeiTtextField.setText(code.time());
				tagesDatumTextField.setText(code.date());
			}
		});
		timer.start();
		calendarPanel.add(aktuelleUhrzeiTtextField);
		
		// DATUM TEXT FIELD
		tagesDatumTextField = new JTextField();
		tagesDatumTextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 13));
		tagesDatumTextField.setHorizontalAlignment(SwingConstants.CENTER);
		tagesDatumTextField.setEnabled(false);
		tagesDatumTextField.setEditable(false);
		tagesDatumTextField.setColumns(10);
		tagesDatumTextField.setBackground(new Color(239, 238, 237));
		tagesDatumTextField.setBounds(6, 68, 377, 29);
		
		calendarPanel.add(tagesDatumTextField);

		
		
		
		// KOMMMT TEXT FIELD
		kommtTextField = new JTextField();
		kommtTextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 15));
		kommtTextField.setBackground(new Color(237, 238, 238));
		kommtTextField.setBounds(255, 6, 123, 29);
		checkInPanel.add(kommtTextField);
		kommtTextField.setEnabled(false);
		kommtTextField.setEditable(false);
		kommtTextField.setColumns(10);
		// KOMMT BUTTON
		kommtButton = new JButton("Kommt");
		kommtButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		kommtButton.setBounds(6, 7, 117, 29);
		checkInPanel.add(kommtButton);
		kommtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!kommtButton.isSelected()) {
					
					// Show when clicked
					String kommtText =  code.time();
					kommtTextField.setText(kommtText);
					
					kommtHour = code.timeHour();
					kommtMins = code.timeMins();
				}
				
				kommtButton.setEnabled(false);
				pauseAnfangButton.setEnabled(true);

			}
		});
		
		
		
		
		
		// PAUSE ANFANG TEXT FIELD
		pauseAnfangetextField = new JTextField();
		pauseAnfangetextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 15));
		pauseAnfangetextField.setBackground(new Color(238, 237, 239));
		pauseAnfangetextField.setBounds(255, 47, 123, 29);
		checkInPanel.add(pauseAnfangetextField);
		pauseAnfangetextField.setEnabled(false);
		pauseAnfangetextField.setEditable(false);
		pauseAnfangetextField.setColumns(10);
		// PAUSE ANFANG BUTTON
		pauseAnfangButton = new JButton("Pause Anfang");
		pauseAnfangButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		pauseAnfangButton.setBounds(6, 47, 117, 29);
		checkInPanel.add(pauseAnfangButton);
		pauseAnfangButton.setEnabled(false);
		pauseAnfangButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!pauseAnfangButton.isSelected()) {
					// Show when clicked
					String pauseAnfangText =  code.time();
					pauseAnfangetextField.setText(pauseAnfangText);
					
					pauseAnfangHour = code.timeHour();
					pauseAnfangMins = code.timeMins();
					
				}
				pauseAnfangButton.setEnabled(false);
				pauseEndeButton.setEnabled(true);
				
			}
		});
		
		
		
		// PAUSE ENDE TEXT FIELD
		pauseEndetextField = new JTextField();
		pauseEndetextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 15));
		pauseEndetextField.setBackground(new Color(237, 237, 238));
		pauseEndetextField.setBounds(255, 88, 123, 29);
		checkInPanel.add(pauseEndetextField);
		pauseEndetextField.setEnabled(false);
		pauseEndetextField.setEditable(false);
		pauseEndetextField.setColumns(10);
		// PAUSE ENDE BUTTON
		pauseEndeButton = new JButton("Pause Ende");
		pauseEndeButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		pauseEndeButton.setBounds(6, 88, 117, 29);
		checkInPanel.add(pauseEndeButton);
		pauseEndeButton.setEnabled(false);
		pauseEndeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!kommtButton.isSelected()) {
					
					String pauseEndeText =  code.time();
					pauseEndetextField.setText(pauseEndeText);
					
					pauseEndeHour = code.timeHour();
					pauseEndeMins = code.timeMins();
					
					pauseHourRechnung = (pauseAnfangHour - pauseEndeHour);
					pauseMinsRechnung = (pauseEndeMins - pauseAnfangMins);
					
					String pauseHourTextField = String.valueOf(pauseHourRechnung);
					String pauseMinsTextField = String.valueOf(pauseMinsRechnung);
					
					
					pauserDauerTextField.setText(pauseHourTextField + " Hour " + pauseMinsTextField + " Mins.");
					System.out.println(pauseHourRechnung + " Hour " + pauseMinsRechnung + " Mins.");
					
				}
				
				pauseEndeButton.setEnabled(false);
				gehtButton.setEnabled(true);
				
			}
		});
		
		
		
		// GEHT TEXT FIELD
		gehtTextField = new JTextField();
		gehtTextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 15));
		gehtTextField.setBackground(new Color(237, 238, 238));
		gehtTextField.setBounds(255, 131, 123, 29);
		checkInPanel.add(gehtTextField);
		gehtTextField.setEnabled(false);
		gehtTextField.setEditable(false);
		gehtTextField.setColumns(10);
		// GEHT BUTTON
		gehtButton = new JButton("Geht");
		gehtButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		gehtButton.setBounds(6, 132, 117, 29);
		checkInPanel.add(gehtButton);
		gehtButton.setEnabled(false);
		gehtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (!gehtButton.isSelected()) {
					
					// Show when clicked
					String gehtText =  code.time();
					gehtTextField.setText(gehtText);
				 
					gehtHour = code.timeHour();
					gehtMins = code.timeMins();
					
					int hourRechnung = (gehtHour - kommtHour);
					int minsRechnung = (gehtMins - kommtMins);
					
					int minusHourPauseEnde = (hourRechnung - pauseHourRechnung);
					int minusMinsPauseEnde = (minsRechnung - pauseMinsRechnung);
					
					String setHourTextString = String.valueOf(minusHourPauseEnde);
					String setminsTextString = String.valueOf(minusMinsPauseEnde);
					
					arbeitZeitTextField.setText(setHourTextString + " Hour " + setminsTextString + " Mins.");
					System.out.println(setHourTextString + " Hour " + setminsTextString + " Mins.");
					
					
					
					dataBase.isConnected();
					try {
						dataBase.connect();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					String key = null;
					try {
						key = dataBase.getPersonlID();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String Start = kommtTextField.getText();
					String End = gehtTextField.getText();
					
					System.out.println("KEY " + key);
					String workingHour = arbeitZeitTextField.getText();

					
					dataBase.workingHour(key, Start, End, workingHour);
					
					
					
				}
		 		
				gehtButton.setEnabled(false);
				// CLOSE THE APPLICATION AFTER THE 
				//System.exit(0);
			
				
//				
//				String insert = "INSERT INTO loginData.Arbeitzeit (PersonlID, Start, End, workingHour) "
//						+ "VALUES ('" + PersonlID + "', '" + Start + "','" + End + "', '" + workingHour + "');";
				
				
				
				
			}
		});
		
		
		
		// PAUSER DAUER LABLE
		pauseDauerLabel = new JLabel("PAUSE DAUER");
		pauseDauerLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		pauseDauerLabel.setBounds(6, 6, 117, 16);
		resultPanel.add(pauseDauerLabel);
		
		// PAUSE DAUER TEXT FIELD
		pauserDauerTextField = new JTextField();
		pauserDauerTextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 15));
		pauserDauerTextField.setBackground(new Color(239, 238, 237));
		pauserDauerTextField.setBounds(6, 21, 377, 29);
		resultPanel.add(pauserDauerTextField);
		pauserDauerTextField.setEnabled(false);
		pauserDauerTextField.setEditable(false);
		pauserDauerTextField.setColumns(10);		
		
		// ARBEITZEIT LABLE
		arbeitZeitLabel = new JLabel("ARBEITSZEIT");
		arbeitZeitLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		arbeitZeitLabel.setBounds(6, 62, 90, 16);
		resultPanel.add(arbeitZeitLabel);
				
		// ARBEITZEIT TEXT FIELD
		arbeitZeitTextField = new JTextField();
		arbeitZeitTextField.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 15));
		arbeitZeitTextField.setBackground(new Color(238, 238, 238));
		arbeitZeitTextField.setBounds(6, 78, 377, 29);
		resultPanel.add(arbeitZeitTextField);
		arbeitZeitTextField.setEnabled(false);
		arbeitZeitTextField.setEditable(false);
		arbeitZeitTextField.setColumns(10);
		
		// LOGOUT BUTTON
		logoutButton = new JButton("loggout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dataBase.disConnected();
				System.exit(0);
				
			}
		});
		logoutButton.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		logoutButton.setBounds(356, 10, 85, 29);
		contentPane.add(logoutButton);
		
		// USER INFO PANEL
		userInfoPanel = new Panel();
		userInfoPanel.setBounds(10, 10, 340, 29);
		contentPane.add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		// USER LABEL
		userLabel = new JLabel("User: ");
		userLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		userLabel.setBounds(6, 6, 47, 16);
		userInfoPanel.add(userLabel);
		
		// USER INFORMATION
		userInfoLabel = new JLabel("");
		userInfoLabel.setFont(new Font("Hiragino Sans CNS", Font.BOLD, 13));
		userInfoLabel.setBounds(50, 9, 256, 16);
		userInfoPanel.add(userInfoLabel);
		
		
		
		
		
	}
}
