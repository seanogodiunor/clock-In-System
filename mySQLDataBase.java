package clockIn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySQLDataBase {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		isConnected();
		connect();
		//Show
		//qurry("select * from loginData.loginDaten where Username like 'SeanBrown'");
		//Insert
		//update("INSERT INTO loginData.loginDaten (FirstName, Lastname, UserName, Passcode) VALUES ('bigJop', 'jo','Sunior', 'Juniior2222');");
				
		//login("Sean", "1111");
		
		//getPersonlID();
		
		
		//update("INSERT INTO loginData.loginDaten (FirstName, Lastname, UserName, Passcode) VALUES ('bigJop', 'jo','Sunior', 'Juniior2222');");

		//workingHour(password, host, database);
		
	}	
	
	
	private static final String host = "localhost";
	private static final String database = "loginData";
	private static final String username = "root";
	private static final String password = "playboy22";
	private static final int port = 3306;

	
	
	private static Connection conn;
	private static Statement stat;
	
	
	static clockInScreen clockin = new clockInScreen();
	
	
	public static boolean isConnected() {
		if (conn == null) {	
			return false;
		}else {
			return true;
		}
	}
	
	
	public static void connect() throws ClassNotFoundException {
		if (!isConnected()) {
			
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database+ "?useUnicode=true&characterEncoding=utf8";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, username, password);
				
				System.out.println("MySQL: Connected");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("MySQL: NOT connected");
		}
	}
	
	
	
	
	
	public static void update(String qurry) {		
		try {
			PreparedStatement ps = conn.prepareStatement(qurry);
			ps.execute();
			System.out.println("MySQL: Updated");			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	
	
	
	public static void disConnected() {	
		if (isConnected()) {
			try {
				conn.close();
				System.out.println("MySQL: Disconnected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("MySQL: NOT disconnected");
		}
	}
	
	
	
	//update("INSERT INTO loginData.loginDaten (FirstName, Lastname, UserName, Passcode) VALUES ('bigJop', 'jo','Sunior', 'Juniior2222');");

	public static void workingHour(String TimeID, String Start, String End, String WorkingHour) {	
		
		
		String insert = "INSERT INTO loginData.timeTable (TimeID, Start, End, WorkingHour) "
				+ "VALUES ('" + TimeID + "', '" + Start + "','" + End + "', '" + WorkingHour + "');";
		
		try {
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.execute();
			System.out.println("Saved in the Database");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	public static String getPersonlID() throws SQLException {	
		String abfrage = "SELECT id FROM loginData.loginDaten";
		String personalID = null;
		
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(abfrage);
		
		while (rSet.next()) {
				personalID = rSet.getString("PersonlID");
				
				System.out.println(personalID);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return personalID;
		
	}
	
	
	
	
	public static boolean login(String checkUser, String checkPassword) {
		String abfrage = "select * from loginDaten where UserName=" + "'" +checkUser+ "' and Passcode= '"+checkPassword+"' " ;
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(abfrage); 
			
			// from Database
			String username;
			String password;
			
			// display  user information
			String firtsname;
			String lastname;
			
			while (rSet.next()) {
				// from Database
				username = rSet.getString("UserName");
				password = rSet.getString("Passcode");
				
				if (checkUser.compareTo(username) == 0 && checkPassword.compareTo(password) == 0) {
					
					// display  user information
					firtsname = rSet.getString("FirstName");
					lastname = rSet.getString("LastName");
					
					String userString = lastname.toUpperCase() + " " + firtsname.toUpperCase();
					clockin.userInfoLabel.setText(userString);
					System.out.println("equal");
					
					return true;
				}
				System.out.println("not equal");
				//System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	public static String username() {
		return null;
		
		
	}
	
	
	

	
	
	
	public static void register(String firstname, String lastname, String username, String password ) {	
		String insert = "INSERT INTO loginData.loginDaten (FirstName, Lastname, UserName, Passcode) "
				+ "VALUES ('" + firstname + "', '" + lastname + "','" + username + "', '" + password + "');";
		
		try {
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.execute();
			System.out.println("Registragtion sucessful");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void writePaaword( ) {	
		
		try {
            String filePath = "output.txt"; // Change this to your desired output file path
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.write("Hello, World!");
            writer.newLine(); // Add a newline
            writer.write("This is a test.");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	
	
	public static void readPaaword( ) {	
		
		try {
            String filePath = "example.txt"; // Change this to your file path
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public static void qurry(String abfrage) {	
		
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(abfrage); 
			
			String FirstName;
			String LastName;
			String UserName;
			String Passcode;
			
			
			while (rSet.next()) {
				
				if (abfrage.contains("FirstName")) {
					FirstName = rSet.getString("FirstName");
					System.out.println("First Name: " + FirstName);
					
				}if (abfrage.contains("LastName")) {
					LastName = rSet.getString("LastName");
					System.out.println("Last Name: " + LastName);

				}if (abfrage.contains("UserName")) {
					UserName = rSet.getString("UserName");
					System.out.println("Username: " + UserName);

					
				}if (abfrage.contains("Passcode")) {
					Passcode = rSet.getString("Passcode");
					System.out.println("Passcode: " + Passcode);

				}else {
					
					FirstName = rSet.getString("FirstName");
					LastName = rSet.getString("LastName");
					UserName = rSet.getString("UserName");
					UserName = rSet.getString("UserName");
					Passcode = rSet.getString("Passcode");

					
					int id = rSet.getInt("id");
					
					System.out.println("First Name: " + FirstName  +
										"\nLast Name: " + LastName + 
										"\nUsername: " + UserName + 
										"\nPasscode: " + Passcode + 
										"\nID: " + id );
					
					
					
					System.out.println("_____________________________________");
				}
				
				
			}
//			System.out.println("MySQL: No entry found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




