import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

import java.util.Scanner;
import javax.swing.*;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashMap;

public class StatusClient {
    private static ArrayList<UserAccount> user;
    private static final String TITLE = "Status";
    private static ArrayList<String> usernames = new ArrayList<>();
    private static int nxt;
    private static UserAccount accountHolder;

    public StatusClient() {
        user = new ArrayList<UserAccount>();
        accountHolder = new UserAccount();
    }

    public ArrayList<UserAccount> getUser() {
    	return this.user;
    }

    public void setUser(ArrayList<UserAccount> user) {
        this.user = user;
    }

    public static void main(String[] args) throws IOException {
        //ArrayList<UserAccount> user = new ArrayList<UserAccount>();
        Socket socket = new Socket("localhost", 4040);
        
        StatusClient start = new StatusClient();
        showWelcomeOptionDialog();
        if (nxt != 0) {
            createAccountInputDialog();
            showprofileDialog();
        }
        continueProfileDialog();
        
        
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
    }

    public static int showWelcomeOptionDialog() {

       
        String[] nextStep = { "Yes, I already have an account", "No, I would like to create an account" };
        
        nxt = (JOptionPane.showOptionDialog(null, 
                                             "Hi there! Do you already have an account?", TITLE, 
                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                                             null, nextStep, nextStep[1]));
        
        return nxt;
    }

    public static String continueProfileDialog() {
        String cone;
        cone = JOptionPane.showInputDialog(null, "Login to your account! What is your username?", TITLE, JOptionPane.QUESTION_MESSAGE);
        for (int i = 0; i < user.size(); i++) {
            if (cone.equals(user.get(i).getUserName())) {
                String password = JOptionPane.showInputDialog(null, "What is your password?", TITLE,
                        JOptionPane.QUESTION_MESSAGE);
                if (password.equals(user.get(i).getPassword())) {
                    JOptionPane.showMessageDialog(null, "Welcome!!", TITLE, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        return cone;
    }

    public static void showFriendsDialog() {
        StringBuilder sb = new StringBuilder();
        for (String friends : usernames) {

            sb.append(friends);

        }
        JOptionPane.showMessageDialog(null, sb.toString(), TITLE, JOptionPane.INFORMATION_MESSAGE);

    }

    public static UserAccount createAccountInputDialog() {

        String username;
        do {
            username = JOptionPane.showInputDialog(null, "What would you like your username to be?", TITLE,
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println("yay");
            if (/* username.equals(user.get(i).getUserName()) || */ username.isBlank() || username == null) {
                JOptionPane.showMessageDialog(null,
                        "This username is not valid/is already taken. Please choose another.", TITLE,
                        JOptionPane.ERROR_MESSAGE);
            }
            /*
             * if (user.size() != 0) { for (int i = 0; i < user.size(); i++) {
             */

            /*
             * }
             * 
             * }
             */

        } while (username.isBlank() || username == null);
        accountHolder.setUserName(username);
        String firstname;
        do {
            firstname = JOptionPane.showInputDialog(null, "What is your first name?", TITLE,
                    JOptionPane.QUESTION_MESSAGE);
            if (firstname.isBlank() || firstname == null) {
                JOptionPane.showMessageDialog(null, "Please enter a valid first name.", TITLE,
                        JOptionPane.ERROR_MESSAGE);

            }
        } while (firstname.isBlank() || firstname == null);
        accountHolder.setFirstName(firstname);
        String lastname;
        do {
            lastname = JOptionPane.showInputDialog(null, "What is your surname?", TITLE, JOptionPane.QUESTION_MESSAGE);
            if (lastname.isBlank() || lastname == null) {
                JOptionPane.showMessageDialog(null, "Please enter a valid last name.", TITLE,
                        JOptionPane.ERROR_MESSAGE);

            }

        } while (lastname.isBlank() || lastname == null);
        accountHolder.setLastName(lastname);
        String year;
        String[] doB;
        do {
            year = JOptionPane.showInputDialog(null, "Please enter your date of Birth in the format MM/DD/YYYY.", TITLE,
                    JOptionPane.QUESTION_MESSAGE);
            if (!year.matches("[0-1][0-2]/[0-3][0-9]/[0-9][0-9][0-9][0-9]") || (year.matches("")) || year.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid year.", TITLE, JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Please enter a stronger password.", TITLE,
                        JOptionPane.ERROR_MESSAGE);
            }

        } while (password.isBlank() || (password.matches("")));
        accountHolder.setPassword(password);
        user.add(accountHolder);

        JOptionPane.showMessageDialog(null,
                String.format("Your account has been created, %s", accountHolder.getUserName()), TITLE,
                JOptionPane.INFORMATION_MESSAGE);

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
        return String.format("First:" + accountHolder.getFirstName() + ";" + "Last:" + accountHolder.getLastName() + ";"
                + "Username:" + accountHolder.getUserName() + ";" + "Age:" + accountHolder.getAge() + ";" + "DOB:"
                + accountHolder.getDOB() + ";" + "Password:" + accountHolder.getPassword());
    }

    public static void showprofileDialog() {
        
    StringBuilder sb = new StringBuilder();
    String[] yes = {"YES", "NO"};
    int view = JOptionPane.showOptionDialog(null, 
                                             "Would you like to view your profile?", TITLE, 
                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yes, yes[0]);

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
    JOptionPane.showMessageDialog(null, sb.toString(), TITLE, JOptionPane.INFORMATION_MESSAGE); 
    
}


    

}
