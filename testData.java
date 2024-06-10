package clockIn;

import java.util.HashMap;

public class testData {
	
    static HashMap<String, String> passcode = new HashMap<String, String>();
    
    

    public static void main(String[] args) {
		
		information(null, null);
		signup("sean" , "34343");	
		login("sean" , "4");
	}
	
    
    private static void information(String username, String password) {
		
		passcode.put("brizzy", "Geeks");
		passcode.put("sean", "4");
		passcode.put("jay", "Geeks");
		
	}	
	

	private static void signup(String username, String password) {
		
		System.out.println(passcode);	
		System.out.println("_______________________________________________");
		
		if (!passcode.containsKey(username)) {
			
			System.out.println("Username NOT already in use");
			passcode.put(username, password);

			System.out.println(passcode);
			
		}else {
			
			System.out.println("Username already in use");
		}	
	}
	
	
	
	
	private static void login(String username, String password) {
		System.out.println(passcode);

		if (passcode.containsKey(username) && passcode.containsValue(password)) {
			
			System.out.println("WELLCOME !!!!");
			passcode.put(username, password);

			//System.out.println(passcode);
		}else {
			System.out.println(" WRONG Username and Password");

		}
	}
	

		
		
		
	

}
