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

            Integer option = (Integer) is.readObject();
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
                        }
                    }
                    os.writeObject(correctAcc);
                    os.flush();
                } while (!correctAcc);

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
                                }
                            }
                            break;
                        case 1: // delete account
                            Integer deleteInt = (Integer) is.readObject();
                            if (deleteInt == 0) {
                                for (int i = 0; i < userBase.size(); i++) {
                                    if (userBase.get(i).getUserName().equals(username)) {
                                        userBase.remove(i);
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
                            }
                        }


                    } else if (profileOptionInc == 1){
                        for (int i = 0; i < userBase.size(); i++) {
                            if (userBase.get(i).getUserName().equals(username)) {
                                os.writeObject(userBase.get(i));
                                os.flush();
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
                                }
                            }
                        }
                    }



                } else if (optionInt == 2) { //friend

                }


            } else if(option == 1) { // creating account
                userBase.add((UserAccount) is.readObject());
            }
            //System.out.println(userBase.size());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
