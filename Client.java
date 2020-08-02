import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 1515);
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

        // Welcome
        JOptionPane.showMessageDialog(null, "Welcome to Profile",
                "Profile", JOptionPane.INFORMATION_MESSAGE);

        // Create a account?
        String[] options = {"Yes", "No"};
        Integer result = JOptionPane.showOptionDialog(null, "Existing account? (yes/no)",
                "Profile",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        os.writeObject(result); // give server 2 options: create account mode or access account mode
        os.flush();

        if(result == 0) { // existing account
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
                }
            } while (!correctAcc);
            JOptionPane.showMessageDialog(null, "Account Found! Logging in...",
                    "Profile", JOptionPane.INFORMATION_MESSAGE);

            // Choose from operations
            String[] mainOption = {"Account Info","Profile", "Friend"};
            Integer optionInt = JOptionPane.showOptionDialog(null,
                    "Do you want to like to do next? \n" +
                            "Change Account Information, View Profile or View Friend",
                    "Profile", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, mainOption, mainOption[1]);
            os.writeObject(optionInt);
            os.flush();
            if(optionInt == 0) { // password
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
                        break;
                    case 1:
                        Integer deleteInt = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " +
                                "your account?", "Profile", JOptionPane.YES_NO_OPTION);
                        os.writeObject(deleteInt);
                        os.flush();
                        if (deleteInt == 0) {
                            JOptionPane.showMessageDialog(null, "Account delete successful!",
                                    "Profile", JOptionPane.INFORMATION_MESSAGE);
                            return;
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
                } else if (profileOptionInc == 1){
                    UserAccount viewUser = (UserAccount) is.readObject();
                    JOptionPane.showMessageDialog(null, viewUser.profileToString(),
                            "Profile", JOptionPane.INFORMATION_MESSAGE);
                } else if (profileOptionInc == 2) {
                    Integer deleteProInt = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " +
                            "your profile information?", "Profile", JOptionPane.YES_NO_OPTION);
                    os.writeObject(deleteProInt);
                    os.flush();
                }
            } else if (optionInt == 2) { //friend

            }



        } else if(result == 1) { // creating account
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
            JOptionPane.showMessageDialog(null, "Account Created! Please login again!",
                    "Profile", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}

