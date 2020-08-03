
Project Title
Getting Started
Prerequisites
Running the tests
Built With
Authors
Acknowledgments

Project Title: Social Network, Profile
The Social Network, Profile, allows a user to have a social media account and use some functionalities through a simple GUI. In creating the Social media network, Profile, three programs were written. The first program, UserClass, is a java program that implements the account of every user of the social network. The other two programs are Server and Client programs. Both programs use a socket server to aid communication between the user (Client) and the network (Server). With the Server and Client programs, multiple users can be connected at once to the server and run multiple actions at the same time.


Getting Started
To run the program, simply compile and run the Server class first, and then the Client class next. Once the code is compiled and running, a GUI will show up on the screen and guide you through the rest of the program. The buttons/options set up in the JOptionPane include all the actions that a user might want to perform once they have created an account and logged in. 
Prerequisites
UserAccount Class
This class contains all the methods necessary for creating an account for any user of the Social Network, Profile. There are two things worthy of note in this class:
The most important information for any user, is the password and the username which helps the user to create and have an account with the Social Network.
Any other user information outside of the password and username, can be added or left empty based on the user’s preference. 
The UserAccount program implements the getters and setters for the parameters which make up the profile of each user. The fields in the class are:
 firstName, implemented as a string method;
 lastName, implemented as a string method;
userName, implemented as a string method;
passWord, implemented as a string method;
age, implemented as an integer method;
Email, implemented as a string method;
Likes, implemented as a string method;
Interests,  implemented as a string method;
friendList, implemented as an arraylist of UserAccount;
pendingList, implemented as an arraylist of UserAccount;
sendingList, implemented as an arraylist of UserAccount;
The UserAccount class has two constructors:
UserAccount empty constructor (parameterless): Used to create a new UserAccount variable and has no parameters.  
UserAccount constructor primarily for the setting up the account of each user. The parameters in this constructor are the String username and the String password. The idea behind setting up a constructor with just these two parameters is that since the most important information for a user would be the login and the password, it would be easy to create users and add them to an arraylist of UserAccounts with just their username and password; every other method could be implemented and called when the user chooses to do so.
All the methods are implemented with getters and setters of the fields. This program also implements a profileToString() method that is used to display the profile information of any user. The profileToString() method includes the entire profile information of a user, except the user’s password.

Server Class
This class works in tandem with the Client class. It receives information from the Client class, and uses this information to implement the methods in the UserAccount class. It also keeps the arraylist of all the users and their respective friends. The fields in this class include an arraylist of UserAccounts.
The program uses a server socket to communicate with the client (the user). 
while (true) {
     Socket socket = serverSocket.accept();
     System.out.println("Waiting for the client to connect...");
     Server server = new Server(socket);
     new Thread(server).start();
     System.out.printf("Client connected!\n");
}
This socket is always up and is never closed, to ensure constant communication between both the Client and Server programs. All information from and to the Client program, is written and read through an object output and input stream.

Client Class
This class works in tandem with the Server class. It includes a JOptionPane where the user can choose what actions they would like to carry out. The Client program then sends whatever information is received from the actions of the user, to the Server program. In order to improve the functionality of the server class, some of the buttons (or options) provided in the Client program return a boolean which is then written as an object to the server side.

Both the client and server side have the booleans seen above in order to encourage consistency throughout the java program. Based on the value of these booleans returned, written and read intermittently by the client and server, the JOptionPane loops back to either a login page or a user account page. All information from and to the Server program, is written and read through an object output and input stream.



Running the tests
Since all of this program is executed using GUI, please refer to the Deployment and Tests on GUI section of this document.
Deployment and Tests for GUI
All three classes (UserAccount, Server and Client) contribute towards running the GUI in the Client class.
To begin, make sure the Server class is compiled and running first, then run and compile the Client class.
Login Page
Account Login: The welcome and login pages are the first windows that show up when the program is run. On the login window, the user has the option to select either an existing account, or a new account. If the user decides to create a new account, a username and password is required. Both of these fields cannot be left blank or an error message GUI will show up. Also, if the user chooses a username that already exists in the arraylist of user accounts, an error message is displayed to inform the user so that they can choose another username. While multiple users can have the exact same profile details (i.e. firstname, last name, age, etc.), their username must be unique to them. Once an account is created, the user is required to re-login in order to continue with the rest of the program. For an existing account, after the username and password is entered by the user, the client sends this information to the Server. The Server returns a boolean to the Client for the existence of that account. If true, the account page for the user is displayed by the GUI, else, the GUI on the client side displays an error message that the account doesn’t exist. 
Once the account is created, the user is asked to login again. After this, the account page is displayed. The account page gives the user the option to view their account information, profile information, or friend information.
Profile Page
Create and Edit profile: The create and edit profile button allows the user to add their first name, last name, age, date of birth, email address, likes, interests, a status message and a note about them. The user can decide to leave the likes, interests, status message and about me note blank and no input validation was done to allow this. Input validation was done for the first name, last name, age, date of birth and email address. 
View profile: This option allows the user to see their profile information which consists of their first name, last name, age, date of birth, likes, interests, about me, and a status message. Also, if two users are friends, both can view others’ profile information.
Delete Profile: This functionality allows the user to delete their entire profile information. To confirm this, after a user chooses this option, their entire profile information in the view profile option is set to null. 
Friend page
View Friends: This option allows the user to view their list of friends. From this option, if a user chooses a friend’s name, they can view the friend’s profile information (first name, last name, date of birth, likes, dislikes, status and about me).
Send friend request: This option allows a user to send a friend request to other users on the network.
Pending Friend request: This option allows a user to view a list of friend requests sent by other users. Once a user chooses another user’s username from the send request list, they are added to the pending list of the other user. If the other user wants to add them to their friend list, they can simply select the name of the user who sent the friend request from their pending list. Once a user is added to the friend list, they can view each other’s profile information.
Account Page
Edit Password: Users can change their password with this option. 
Delete Account: Users are allowed to delete their account completely from the Server. This works well since the Server class keeps an arraylist of all the users and their accounts. Once a user elects to completely delete their account, this information is sent over from the Client which displays a message to confirm the user’s choice. After confirmation, the Server deletes that user’s account from the arraylist of UserAccount. 
Overall, the functionality of the GUI is highly dependent on the communication between the Server and the Client.
 
Built With
IntelliJ
Authors
Justin Wang
Peace Umoru
Zekun Wu
Acknowledgments
Dr. Buster Dunsmore
Logan Kulinski

