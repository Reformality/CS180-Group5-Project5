import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;

public class StatusServer implements Runnable {
    private int id; 
    private Socket socket; 
    
    private ArrayList<UserAccount> user;
    private static final String TITLE = "Status";
    
    private UserAccount accountHolder;

    public ArrayList<UserAccount> getUser() {
    	return this.user;
    }

    public void setUser(ArrayList<UserAccount> user) {
        this.user = user;
    }
    public StatusServer(Socket socket, int id) {
        this.id = id; 
        this.socket = socket; 
    }

    public void run() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());) {
            
            String message = reader.readLine();
            System.out.println("Received from client " + id + ": " + message);
            message = message.replaceAll(":", ";");
            String[] create = message.split(";");
            
            accountHolder = new UserAccount();
            user = new ArrayList<UserAccount>();
            accountHolder.setFirstName(create[1]);
            accountHolder.setLastName(create[3]);
            accountHolder.setUserName(create[5]);
            accountHolder.setAge(Integer.parseInt(create[7]));
            accountHolder.setDOB(create[9]);
            accountHolder.setPassword(create[11]);
            user.add(accountHolder);

            System.out.printf("Sent to Client %d: \n", id);
            
            writer.write(accountHolder.profileInfo());
            writer.println();
            writer.flush(); // Ensure data is sent to the client immediately.

            writer.close();
            reader.close();           
           
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static void main(String[] args) {
        try {
           ServerSocket serverSocket = new ServerSocket(4040);
            //notice that the same port (4242) is used for both client and server

            int purduePete = 1; 

            while (true) {
                System.out.println("Waiting for the client to connect...");
                Socket socket = serverSocket.accept(); 
                StatusServer server = new StatusServer(socket, purduePete); 
                new Thread(server).start(); 
                System.out.printf("Client %d connected!\n", purduePete);
                purduePete++; 
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
