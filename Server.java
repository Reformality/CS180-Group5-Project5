import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    private Socket socket;
    public static ArrayList<UserAccount> userBase = new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1515);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Waiting for the client to connect...");
            Server server = new Server(socket);
            new Thread(server).start();
            System.out.printf("Client connected!\n");
        }
    }

    public void run() {
        try(ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {

            Integer option;
            
            boolean option_relogin = true; // login page
            boolean option_accountPage = true; //account page
            
            //this loop returns to the log in page
            while (option_relogin) {
            	option = (Integer) is.readObject();
            	if(option == 0) { // existing account
	                // login
	                String username;
	                String password;
	                Boolean correctAcc = false;
	                do {
	                    username = (String) is.readObject();
	                    password = (String) is.readObject();
	                    for (int i = 0; i < userBase.size(); i++) {
	                        if (userBase.get(i).getUserName().equals(username) &&
	                                userBase.get(i).getPassword().equals(password)) {
	                            correctAcc = true;
	                            option_relogin = false;
	                        }
	                    }
	                    os.writeObject(correctAcc);
	                    os.flush();
	                } while (!correctAcc);
	                
	                while (option_accountPage) {
		                //select operations
		                Integer optionInt = (Integer) is.readObject();
		                if(optionInt == 0) { // account info
		                    Integer accountOptionInc = (Integer) is.readObject();
		                    switch (accountOptionInc) {
		                        case 0: // change password
		                            String newPassword = (String) is.readObject();
		                            for (int i = 0; i < userBase.size(); i++) {
		                                if (userBase.get(i).getUserName().equals(username)) {
		                                    userBase.get(i).setPassword(newPassword);
		                                    option_relogin = true; //goes back to log in menu
		                                }
		                            }
		                            break;
		                        case 1: // delete account
		                            Integer deleteInt = (Integer) is.readObject();
		                            if (deleteInt == 0) {
		                                for (int i = 0; i < userBase.size(); i++) {
		                                    if (userBase.get(i).getUserName().equals(username)) {
		                                        userBase.remove(i);
		                                        option_relogin = true; //goes back to log in menu
		                                    }
		                                }
		                            }
		
		                            break;
		                    }
		                } else if (optionInt == 1) { //Profile
		                    Integer profileOptionInc = (Integer) is.readObject();
		                    if (profileOptionInc == 0) { // create and edit
		                        ArrayList<String> profileInfo = (ArrayList<String>) is.readObject(); //fn, ln, mon, day, y, age, email, like, interest, status, about
		                        for (int i = 0; i < userBase.size(); i++) {
		                            if (userBase.get(i).getUserName().equals(username)) {
		                                userBase.get(i).setFirstName(profileInfo.get(0));
		                                userBase.get(i).setLastName(profileInfo.get(1));
		                                userBase.get(i).setBMonth(Integer.parseInt(profileInfo.get(2)));
		                                userBase.get(i).setBDate(Integer.parseInt(profileInfo.get(3)));
		                                userBase.get(i).setBYear(Integer.parseInt(profileInfo.get(4)));
		                                userBase.get(i).setAge(Integer.parseInt(profileInfo.get(5)));
		                                userBase.get(i).setEmail(profileInfo.get(6));
		                                userBase.get(i).setLikes(profileInfo.get(7));
		                                userBase.get(i).setInterests(profileInfo.get(8));
		                                userBase.get(i).setStatus(profileInfo.get(9));
		                                userBase.get(i).setAbout(profileInfo.get(10));
		                                option_accountPage = true; // back to account page
		                            }
		                        }
		
		
		                    } else if (profileOptionInc == 1){
		                        for (int i = 0; i < userBase.size(); i++) {
		                            if (userBase.get(i).getUserName().equals(username)) {
		                                os.writeObject(userBase.get(i));
		                                os.flush();
		                                option_accountPage = true; // back to account page
		                            }
		                        }
		                    } else if (profileOptionInc == 2) {
		                        Integer deleteProInt = (Integer) is.readObject();
		                        if (deleteProInt == 0) {
		                            for (int i = 0; i < userBase.size(); i++) {
		                                if (userBase.get(i).getUserName().equals(username)) {
		                                    userBase.get(i).setFirstName(null);
		                                    userBase.get(i).setLastName(null);
		                                    userBase.get(i).setBMonth(0);
		                                    userBase.get(i).setBDate(0);
		                                    userBase.get(i).setBYear(-1);
		                                    userBase.get(i).setAge(-1);
		                                    userBase.get(i).setEmail(null);
		                                    userBase.get(i).setLikes(null);
		                                    userBase.get(i).setInterests(null);
		                                    userBase.get(i).setStatus(null);
		                                    userBase.get(i).setAbout(null);
		                                    System.out.println(userBase.get(i).profileToString());
		                                    option_accountPage = true; // back to account page
		                                }
		                            }
		                        }
		                    }
		
		
		
		                } else if (optionInt == 2) { //friend
		
		                } else if (optionInt == 3) {
		                	option_relogin = true; // back to login page
			            	option_accountPage = false; // back to login page
			            }
            		}
	            
	
	
	            } else if (option == 1) { // creating account
	                //checking if the username already exists in the userBase.
			String isNew = null;
			String isOld = null;
			String itsOld;
			System.out.println(isNew + " yay");
			do {                  
			    isNew = (String) is.readObject();
			    if (userBase.size() != 0) {

				for (int i = 0; i < userBase.size(); i++) {

				    if (userBase.get(i).getUserName().equals(isNew)) {
					isOld = userBase.get(i).getUserName();

				    } 

				}
			    }
			    os.writeObject(isOld);

			    os.flush(); 
			    itsOld = (String) is.readObject();
			} while (itsOld != null);
			System.out.println("yay");
			userBase.add((UserAccount) is.readObject());  
	            } else if (option == 2) {
	            	return;
	            }
            }
            //System.out.println(userBase.size());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
