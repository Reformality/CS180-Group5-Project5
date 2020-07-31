import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

import java.util.Scanner;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class StatusClient {
    private ArrayList<UserAccount> user;
    private static final String TITLE = "Status";
    
    private UserAccount accountHolder;

    public StatusClient() {
        this.user = new ArrayList<UserAccount>();
        this.accountHolder = new UserAccount();
    }

    public ArrayList<UserAccount> getUser() {
    	return this.user;
    }

    public void setUser(ArrayList<UserAccount> user) {
        this.user = user;
    }
    public static int showWelcomeOptionDialog() {

       
        String[] nextStep = { "Yes, I already have an account", "No, I would like to create an account" };
        
        int next = (JOptionPane.showOptionDialog(null, 
                                             "Hi there! Do you already have an account?", TITLE, 
                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                                             null, nextStep, nextStep[0]));

        return next;
    }


    public UserAccount createAccountInputDialog() {
        
        String username;
        do {
            username = JOptionPane.showInputDialog(null, "What would you like your username to be?", TITLE, 
                                                     JOptionPane.QUESTION_MESSAGE);
            if (username.isBlank() || username == null) { //set it up so that only one username can be used//
            JOptionPane.showMessageDialog(null, "This username is not valid/is already taken. Please choose another.", 
                                        TITLE, JOptionPane.ERROR_MESSAGE);

            }                                       
            

        } while (username.isBlank() || username == null);
        accountHolder.setUserName(username);
        String firstname;
        do {
            firstname = JOptionPane.showInputDialog(null, "What is your first name?", TITLE, 
                                                     JOptionPane.QUESTION_MESSAGE);
            if (firstname.isBlank() || firstname == null) {
                JOptionPane.showMessageDialog(null, "Please enter a valid first name.", 
                                            TITLE, JOptionPane.ERROR_MESSAGE);

            }
        } while (firstname.isBlank() || firstname == null);
        accountHolder.setFirstName(firstname);
        String lastname;
        do {
            lastname = JOptionPane.showInputDialog(null, "What is your surname?", TITLE, 
                                                     JOptionPane.QUESTION_MESSAGE);
            if (lastname.isBlank() || lastname == null) {
                                                        JOptionPane.showMessageDialog(null, "Please enter a valid last name.", 
                                                                                      TITLE, JOptionPane.ERROR_MESSAGE);
                                        
            }                                        
            

        } while (lastname.isBlank() || lastname == null);
        accountHolder.setLastName(lastname);
        String year;
        String[] doB;
        do {
            year = JOptionPane.showInputDialog(null, "Please enter your date of Birth in the format MM/DD/YYYY.", TITLE, 
                                                    JOptionPane.QUESTION_MESSAGE);
            if (!year.matches("[0-1][0-2]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") || (year.matches("")) || year.isBlank()) {
                                                        JOptionPane.showMessageDialog(null, "Please enter a valid year.", 
                                                                                    TITLE, JOptionPane.ERROR_MESSAGE);
            }                                         
            

        } while (!year.matches("[0-1][0-2]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") || (year.matches("")));
        doB = year.split("/");
        int month = Integer.parseInt(doB[0]);
        int day = Integer.parseInt(doB[1]);
        int yr = Integer.parseInt(doB[2]);
        accountHolder.setDOB(year);
        int age = LocalDate.now().getYear() - yr;
        accountHolder.setAge(age);
        String password;
        do {
            password = JOptionPane.showInputDialog(null, "Enter a strong password.", TITLE, 
                                                    JOptionPane.QUESTION_MESSAGE);
            if ((password.matches("")) || password.isBlank()) {
                                                        JOptionPane.showMessageDialog(null, "Please enter a stronger password.", 
                                                                                    TITLE, JOptionPane.ERROR_MESSAGE);
            }                                         
            

        } while (password.isBlank() || (password.matches("")));
        accountHolder.setPassword(password);
        user.add(accountHolder);

        JOptionPane.showMessageDialog(null, String.format("Your account has been created, %s", accountHolder.getUserName()),
        TITLE, JOptionPane.INFORMATION_MESSAGE);
        
        return accountHolder;
   }

   public String newUserMessageDialog() {

        JOptionPane.showMessageDialog(null, "Welcome to STATUS where you can connect with friends, family, and foe!!",
        TITLE, JOptionPane.INFORMATION_MESSAGE);
        String message = JOptionPane.showInputDialog(null, "Do you have anything on your mind?", TITLE, 
                                                     JOptionPane.QUESTION_MESSAGE);
        accountHolder.setStatus(message);                                          
        return message;                                             
   }

   public String profileInfo() {
    return String.format("First:" + accountHolder.getFirstName() + ";" + "Last:" + accountHolder.getLastName() + ";" + "Username:" + accountHolder.getUserName() + ";" + "Age:" + accountHolder.getAge() + ";" + "DOB:" + accountHolder.getDOB() + ";" + "Password:" + accountHolder.getPassword());
}

   public void showprofileDialog() {
        
    StringBuilder sb = new StringBuilder();
    String[] yes = {"YES", "NO"};
    int view = JOptionPane.showOptionDialog(null, 
                                             "Would you like to view your profile?", TITLE, 
                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yes, yes[0]);

    for (UserAccount count : user) {
        sb.append("Name: ");
        sb.append(accountHolder.getFirstName());
        sb.append(" ");
        sb.append(accountHolder.getLastName());
        sb.append("\n");

        sb.append("Username: ");
        sb.append(accountHolder.getUserName());
        sb.append("\n");
        sb.append("DOB: ");
        sb.append(accountHolder.getDOB());
        sb.append("\n");
    }
    JOptionPane.showMessageDialog(null, sb.toString(), TITLE, JOptionPane.INFORMATION_MESSAGE); 
    
}


    public static void main(String[] args) {
        
        try {
            StatusClient start = new StatusClient();
           Socket socket = new Socket("localhost", 4040);
           showWelcomeOptionDialog();
            start.createAccountInputDialog();
            start.showprofileDialog();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
           
            System.out.println("Request has been sent to server: " + start.profileInfo());
        
            
            
            writer.write(start.profileInfo());
            writer.println();
            writer.flush();

            String end = reader.readLine();
            /*end = end.replaceAll(":", ";");
            String[] create = end.split(";");
            
            start.accountHolder = new UserAccount();
            start.accountHolder.setFirstName(create[1]);
            start.accountHolder.setLastName(create[3]);
            start.accountHolder.setUserName(create[5]);
            start.accountHolder.setAge(Integer.parseInt(create[7]));
            start.accountHolder.setDOB(create[9]);
            start.accountHolder.setPassword(create[11]);
            start.user.add(start.accountHolder);*/
            System.out.println(end);
            
            writer.close();
            reader.close();   
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}
