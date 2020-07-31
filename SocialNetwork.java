import java.io.*;
import java.net.*;
import java.util.*;

public class SocialNetwork implements Runnable{

	public static ArrayList<UserAccount> userBase = new ArrayList<UserAccount>(Arrays.asList(
		new UserAccount("testUsername", "testFirstName", "testLastName", 21, "testPassword", 1998, 1, 1, "this is a test"),
		new UserAccount("testUsername2", "testFirstName2", "testLastName2", 21, "testPassword", 1998, 1, 1, "this is a test")
	));
	private Socket socket;
	
	
	
	public SocialNetwork(Socket socket) {
		this.socket = socket;
		
	}

	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(2001);
    	
    	while (true) {
    		Socket socket = serverSocket.accept();
    		System.out.println("Waiting for the client to connect...");
    		SocialNetwork server = new SocialNetwork(socket);
    		new Thread(server).start();
    		System.out.printf("Client connected!\n");
    	}
	}
	
	public void run() {

    	try (
    	BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	PrintWriter writer = new PrintWriter(socket.getOutputStream())
    	) {
    		writer.write(String.valueOf(userBase.size()));
    		writer.println();
    		writer.flush();
    		boolean hasUser = false; //boolean value to keep the loop going. Sorry, this part of the code is super 
    		//confusing, but I can explain it all to you over discord.
    		while(!hasUser) {
	    		
    			//If client selects existing account
    			if (reader.readLine().equals("yes")) {
		    		String username = reader.readLine();
		    		String password = reader.readLine();
		    		
		    		String usernameCheck; //temp username variable for the loop
		    		String passwordCheck; //temp password ____________________
		    		UserAccount account = null;  // initialization of temp account variable, just less code? 
		    		
		    		for(int i = 0; i < userBase.size(); i++) {
		    			usernameCheck = userBase.get(i).getUserName();
		    			passwordCheck = userBase.get(i).getPassword();
		    			if (usernameCheck.equals(username) && passwordCheck.equals(password)) {
		    				hasUser = true;
		    				account = userBase.get(i);
		    			}
		    		}
		    		
		    		
		    		ArrayList<String> accountInfo;  //account info to be sent to client
		    		
		    		if (hasUser) {
	    				writer.write(String.valueOf(hasUser));
	    				writer.println();
	    				writer.flush();
		    			
		    			accountInfo = account.toArrayList();
						
	    				for (int j = 0; j < accountInfo.size(); j++) {
	    					writer.write(accountInfo.get(j));
	    					writer.println();
	    					writer.flush();
	    				}
	    			}
		    		
		    		else {
		    			writer.write(String.valueOf(hasUser));
		    			writer.println();
	    				writer.flush();
		    		}
		    		
		    		// if client wants to create new account
	    		} else {
	    			String newUsername = reader.readLine();
	    			String newFirstname = reader.readLine();
	    			String newLastname = reader.readLine();
	    			int newAge = Integer.valueOf(reader.readLine());
	    			String newPassword = reader.readLine();
	    			int newYear = Integer.valueOf(reader.readLine());
	    			int newDate = Integer.valueOf(reader.readLine());
	    			int newMonth = Integer.valueOf(reader.readLine());
	    			String newMessage = reader.readLine();
	    			
	    			//add new account to userBase
	    			userBase.add(new UserAccount(newUsername, newFirstname, newLastname, newAge, newPassword, newYear, newDate, newMonth, newMessage));
	    			writer.write("New account created! Welcome to the Social Network " + userBase.get(userBase.size() - 1).getUserName());
	    			writer.println();
	    			writer.flush();
	    			hasUser = true;
	    			
	    			System.out.println("New account created!");
	    			userBase.get(userBase.size() - 1).printAccountInfo();
	    			
	    		}
    		}
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	
	
	
}
