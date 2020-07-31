import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class AccessAccount {

	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//TODO: Welcome window with options to log in or create account
		
		//Create Account 
		String existingAccount; //use GUI to set this to "yes" for an existing account or "no" for a new account
		String username; //use GUI to set this, delete the initialization I have now
		String password; //use GUI to set this
		
		Socket socket = new Socket("localhost", 2001);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        
        System.out.println(reader.readLine());
        
        boolean hasUser = false;
        UserAccount account = null;
		
        while (!hasUser) {
	        Scanner scan = new Scanner(System.in);
	        System.out.println("Existing account? (yes/no)");
	        existingAccount = scan.nextLine();
	        
        	
        	writer.write(existingAccount);
	        writer.println();
	        writer.flush();
	        
	        if (existingAccount.equals("yes")) {

	        	System.out.println("Username:");
		        username = scan.nextLine();
		        System.out.println("Password: ");
		        password = scan.nextLine();
		        
		        writer.write(username);
		        writer.println();
		        writer.flush();
		        
		        writer.write(password);
		        writer.println();
		        writer.flush();
		        
		        hasUser = Boolean.valueOf(reader.readLine());
		        
		        String loginResponse;
		        
		        if (hasUser) {
		        	loginResponse = "Account Found! Logging in...";
		        	System.out.println(loginResponse);
		        	
		        	String accUsername = reader.readLine();
		        	String accFirstName = reader.readLine();
		        	String accLastName = reader.readLine();
		        	int accAge = Integer.valueOf(reader.readLine());
		        	String accPassword = reader.readLine();
		        	int accYear = Integer.valueOf(reader.readLine());
		        	int accDay = Integer.valueOf(reader.readLine());
		        	int accMonth = Integer.valueOf(reader.readLine());
		        	String accMessage = reader.readLine();
		        	
		        	account = new UserAccount(accUsername, accFirstName, accLastName, accAge, accPassword, accYear, accDay, accMonth, accMessage);
		        	account.printAccountInfo();
		        }
		        else {
		        	loginResponse = "Error: Account not found.  Verify that username and password are correct.";
		        	System.out.println(loginResponse);
		        }
		        
	        } else {
	        	hasUser = true;
	        	String line;
	        	System.out.println("Create username:");
	        	line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter first name: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter last name: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter age: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Create password: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter birth year: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter birth day: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter birth month: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        System.out.println("Enter status message: ");
		        line = scan.nextLine();
	        	writer.write(line);
		        writer.println();
		        writer.flush();
		        
		        System.out.println(reader.readLine());
	        }
        }
        
        
		

	}
	
	

}
