import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, EOFException {
        Socket socket = new Socket("localhost", 1515);
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

        boolean option_relogin = true;  //login page
        boolean option_accountPage = true; //account page
        Integer result;
        
        
        //this loop returns to the log in page
        //Menu ------------------------------------------------------------------------------------------------
        while (option_relogin) {
            // Welcome
            JOptionPane.showMessageDialog(null, "Welcome to Profile",
                    "Profile", JOptionPane.INFORMATION_MESSAGE);

            // Login menu options---------------------------------------------------------------------------------------
            String[] options = {"Yes", "No", "Quit"};
            result = JOptionPane.showOptionDialog(null, "Existing account?",
                    "Profile",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            os.writeObject(result); // give server 2 options: create account mode or access account mode
            os.flush();
            option_accountPage = true;
            // Existing Account -----------------------------------------------------------------------------------------
            if(result == 0) { 
                // Login in
                Boolean correctAcc = false;
                do {
                    String username;
                    String password;
                    do {
                        username = JOptionPane.showInputDialog(null, "Enter the username:",
                                "Profile", JOptionPane.QUESTION_MESSAGE);
                        if ((username == null) || (username.isBlank())) {
                            JOptionPane.showMessageDialog(null, "Name cannot be empty!",
                                    "Profile",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } while ((username == null) || (username.isBlank()));
                    do {
                        password = JOptionPane.showInputDialog(null, "Enter the password:",
                                "GPA Calculator", JOptionPane.QUESTION_MESSAGE);
                        if ((password == null) || (password.isBlank())) {
                            JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                                    "Profile",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } while ((password == null) || (password.isBlank()));
                    os.writeObject(username);
                    os.flush();
                    os.writeObject(password);
                    os.flush();
                    correctAcc = (Boolean) is.readObject();
                    if (!correctAcc) {
                        JOptionPane.showMessageDialog(null, "Account not Found! Please try again!",
                                "Profile", JOptionPane.ERROR_MESSAGE);
                    } else {
                        option_relogin = false;
                    }
                } while (!correctAcc);
                JOptionPane.showMessageDialog(null, "Account Found! Logging in...",
                        "Profile", JOptionPane.INFORMATION_MESSAGE);

                option_accountPage = true;
                
                
                
                
                //Account Page loop START----------------------------------------------------------------------------------
                while (option_accountPage) {
                	option_accountPage = true;
                    // Choose from operations
                    String[] mainOption = {"Account Info","Profile", "Friend", "Log Out"};

                    Integer optionInt = JOptionPane.showOptionDialog(null,
                            "Do you want to like to do next? \n" +
                                    "Change Account Information, View Profile or View Friend",
                            "Profile", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, mainOption, mainOption[1]);
                    os.writeObject(optionInt);
                    os.flush();
                    if(optionInt == 0) { // account info
                        String[] accountOption = {"Edit Password", "Delete Account"};
                        Integer accountOptionInc = JOptionPane.showOptionDialog(null,
                                "Do you want to like to do next? \n" +
                                        "Change password or Delete account",
                                "Profile", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, accountOption, accountOption[0]);
                        os.writeObject(accountOptionInc);
                        os.flush();
                        switch (accountOptionInc) {
                            case 0:
                                String password;
                                do {
                                    password = JOptionPane.showInputDialog(null, "Enter the new password:",
                                            "GPA Calculator", JOptionPane.QUESTION_MESSAGE);
                                    if ((password == null) || (password.isBlank())) {
                                        JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                                                "Profile",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                } while ((password == null) || (password.isBlank()));
                                os.writeObject(password);
                                os.flush();
                                JOptionPane.showMessageDialog(null, "Password change successful!",
                                        "Profile", JOptionPane.INFORMATION_MESSAGE);
                                option_relogin = true; //goes back to log in menu
                                option_accountPage = false;
                                break;

                            case 1:
                                Integer deleteInt = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " +
                                        "your account?", "Profile", JOptionPane.YES_NO_OPTION);
                                os.writeObject(deleteInt);
                                os.flush();
                                if (deleteInt == 0) {
                                    JOptionPane.showMessageDialog(null, "Account delete successful!",
                                            "Profile", JOptionPane.INFORMATION_MESSAGE);
                                    option_relogin = true; //goes back to log in menu
                                    option_accountPage = false;
                                }
                                break;
                        }

                    } else if (optionInt == 1) { //Profile
                        String[] profileOption = {"Create and Edit Profile", "View Profile", "Delete Profile"};
                        Integer profileOptionInc = JOptionPane.showOptionDialog(null,
                                "Do you want to like to do next? \n" +
                                        "Create, Edit, or Delete your Profile",
                                "Profile", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, profileOption, profileOption[0]);
                        os.writeObject(profileOptionInc);
                        os.flush();
                        if (profileOptionInc == 0) { // create and edit
                            ArrayList<String> profileInfo = new ArrayList<>(); //fn, ln, mon, day, y, age, email, like, interest, status, about
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter the first name:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter the last name:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter the birth month:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter the birth day:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter the birth year:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter the age:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter your email:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter your like:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter your interests:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter your status:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            profileInfo.add(JOptionPane.showInputDialog(null, "Enter your about me message:", "Profile", JOptionPane.QUESTION_MESSAGE));
                            os.writeObject(profileInfo);
                            os.flush();
                            option_accountPage = true; // back to account page
                        } else if (profileOptionInc == 1){
                            UserAccount viewUser = (UserAccount) is.readObject();
                            JOptionPane.showMessageDialog(null, viewUser.profileToString(),
                                    "Profile", JOptionPane.INFORMATION_MESSAGE);
                            option_accountPage = true; // back to account page
                        } else if (profileOptionInc == 2) {
                            Integer deleteProInt = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " +
                                    "your profile information?", "Profile", JOptionPane.YES_NO_OPTION);
                            os.writeObject(deleteProInt);
                            os.flush();
                            option_accountPage = true; // back to account page
                        }
                   
                    
                    //Friend---------------------------------------------------------------------------------------------
                    } else if (optionInt == 2) { 
                        String[] friendOption = {"View Friend List", "Pending Friend Request", "Send Friend Request"};
                        Integer friendOptionInt = JOptionPane.showOptionDialog(null,
                                "Do you want to like to do next? \n" +
                                        "Change password or Delete account",
                                "Profile", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, friendOption, friendOption[0]);
                        System.out.println("pre flush");
                        os.writeObject(friendOptionInt);
                        os.flush();
                        System.out.println("post flush sent " + friendOptionInt);


                        String friendUsername;

                        switch (friendOptionInt) {
                            case 0: // View friend list
                                ArrayList<UserAccount> friendList = (ArrayList<UserAccount>) is.readObject();
                                System.out.println(friendList.size());
                                ArrayList<String> friendListName = new ArrayList<>();
                                for (int i = 0; i < friendList.size(); i++) {
                                    friendListName.add(friendList.get(i).getUserName());
                                }
                                System.out.println(friendListName.size());
                                String[] friendListNameArr = friendListName.toArray(new String[friendListName.size()]);
                                System.out.println("Friend Option 0, pre Do");
                                do {
                                    friendUsername = (String) JOptionPane.showInputDialog(null, "Select the friend to view profile",
                                            "Profile", JOptionPane.QUESTION_MESSAGE, null, friendListNameArr,
                                            friendListNameArr[0]);
                                } while (friendUsername == null);
                                System.out.println("Friend Option 0, post Do");
                                os.writeObject(friendUsername);
                                os.flush();
                                String profileString = (String) is.readObject();
                                JOptionPane.showMessageDialog(null, profileString,
                                        "Profile", JOptionPane.INFORMATION_MESSAGE);
                                option_accountPage = true; // back to account page
                                break;
                            case 1: // Pending Friend list
                                ArrayList<UserAccount> pendingList = (ArrayList<UserAccount>) is.readObject();
                                ArrayList<String> pendingListName = new ArrayList<>();
                                for (int i = 0; i < pendingList.size(); i++) {
                                    pendingListName.add(pendingList.get(i).getUserName());
                                }
                                String[] pendingListNameArr = pendingListName.toArray(new String[pendingListName.size()]);
                                do {
                                    friendUsername = (String) JOptionPane.showInputDialog(null, "Select the user to add friend",
                                            "Profile", JOptionPane.QUESTION_MESSAGE, null, pendingListNameArr,
                                            pendingListNameArr[0]);
                                } while (friendUsername == null);
                                os.writeObject(friendUsername);
                                os.flush();
                                option_accountPage = true; // back to account page
                                break;
                            case 2: //Send Friend Request
                                ArrayList<String> users = (ArrayList<String>) is.readObject();
                                String[] usersArr = users.toArray(new String[users.size()]);
                                do {
                                    friendUsername = (String) JOptionPane.showInputDialog(null, "Select the user to add friend",
                                            "Profile", JOptionPane.QUESTION_MESSAGE, null, usersArr,
                                            usersArr[0]);
                                } while (friendUsername == null);
                                os.writeObject(friendUsername);
                                os.flush();
                                option_accountPage = true; // back to account page
                                break;
                        }


                    } else if (optionInt == 3) { // Logout
                        option_relogin = true; // back to login page
                        option_accountPage = false; // back to login page
                    }
                } 
                // Account menu loop END------------------------------------------------------------------------------------------


                
            //Create Account --------------------------------------------------------------------------------------------
            } else if(result == 1) { 
                String username;
                String password;
                do {
                    username = JOptionPane.showInputDialog(null, "Enter the username:",
                            "Profile", JOptionPane.QUESTION_MESSAGE);
                    if ((username == null) || (username.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!",
                                "Profile",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((username == null) || (username.isBlank()));

                do {
                    password = JOptionPane.showInputDialog(null, "Enter the password:",
                            "GPA Calculator", JOptionPane.QUESTION_MESSAGE);
                    if ((password == null) || (password.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                                "Profile",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((password == null) || (password.isBlank()));
                UserAccount user = new UserAccount(username, password);
                os.writeObject(user);
                os.flush();
                JOptionPane.showMessageDialog(null, "Account Created! Please login again!",
                        "Profile", JOptionPane.INFORMATION_MESSAGE);
            } else if (result == 2) {
                JOptionPane.showMessageDialog(null, "Thank you for using the Social Network!",
                        "Profile", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } // Account Page loop END
    }


}