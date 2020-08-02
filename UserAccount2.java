import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.time.LocalDate;

    
     public class UserAccount2 {
        private ArrayList<String> friends;
        private ArrayList<UserAccount2> user;
        private String userName;
        private String firstName;
        private String lastName;
        private int age;
        private String password;
        private int bYear; //input needs to be validated
        private int bDate; //input needs to be validated
        private int bMonth; //input needs to be validated
        private String email;
        private String likes;
        
        private String interests;
        private String about;
        private String messageStatus; //not sure if this needs to be included, but it's for the user's status.
        

        
        /*public UserAccount2(String userName, String firstName, String lastName, int age, String password, int bYear, int bDate, int bMonth, String email, String messageStatus) {
            this.userName = userName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.password = password;
            this.bYear = bYear;
            this.bDate = bDate;
            this.bMonth = bMonth;
            this.email = email;
            this.messageStatus = messageStatus;
            
        }*/
        
        public UserAccount2(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public UserAccount2() {

        }
        public String getUserName() {
            return this.userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }


        public String getFirstName() {
            return this.firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }


        public String getLastName() {
            return this.lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return this.email;
        }
        public void setEmail(String email) {
            this.email = email;
        }


        public int getAge() {
            return this.age;
        }
        public void setAge(int age) {
            this.age = age;
        }


        public String getPassword() {
            return this.password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

        public int getBYear() {
            
            return this.bYear;
        }
        public void setBYear(int bYear) {
            this.bYear = bYear;
        }


        public int getBDate() {
        	return this.bDate;
        }
        public void setBDate(int bDate) {
        	this.bDate = bDate;
        }
        
        public String getLikes() {

            return likes;
        }
        
        public String getInterests() {
            return interests;
        }

        public void setLikes(String likes) {
            
            this.likes = likes;
        }
        
        public void setInterests(String interests) {
            
            this.interests = interests;
        }
         
        public String getAbout() {
            return this.about;
        }
        public void setAbout(String about) {
            this.about = about;
        }

        public int getBMonth() {
        	return this.bMonth;
        }
        public void setBMonth(int bMonth) {
        	this.bMonth = bMonth;
        }


        public String dOB() {
          
            return String.format("%d/%d/%d", getBMonth(), getBDate(), getBYear());
        }
        
        public String getStatus() {
            return this.messageStatus;
        }
    
        public void setStatus(String messageStatus) {
            this.messageStatus = messageStatus;
        }
        
        

        public ArrayList<String> toArrayList() {
        	ArrayList<String> result = new ArrayList<String>();
        	result.add(userName);
        	result.add(firstName);
        	result.add(lastName);
        	result.add(String.valueOf(age));
        	result.add(password);
        	result.add(String.valueOf(bYear));
        	result.add(String.valueOf(bDate));
        	result.add(String.valueOf(bMonth));
        	result.add(messageStatus);
        	
        	return result;
        }
        
        public String toString() {
            return String.format("First:" + this.firstName + "\n" + "Last:" + this.lastName + "\n" + "Username:" + this.userName + "\n" + "Age:" + this.age + "\n" + "DOB:" + dOB() + "\n" + "Email:" + this.email + "\n" + "About me: " + this.about + "\n" + "Likes: " + likes + "\n" + "Interest: " + interests + "\n" + "Status: " + messageStatus);
        }
        
        public void printAccountInfo() {
        	System.out.println("Username: " + userName);
        	System.out.println("First Name: " + firstName);
        	System.out.println("Last Name: " + lastName);
        	System.out.println("Age: " + age);
            System.out.println("Date of Birth: " + dOB());
            System.out.println("Contact: " + email);
        	System.out.println("Status: " + messageStatus);
        }

        public static void main(String[] args) { //Ignore this. Just testing the format for DOB
            UserAccount2 start = new UserAccount2();
            Scanner sc = new Scanner(System.in);
            
            int year = sc.nextInt();
            sc.nextLine();
            start.setBYear(year);
            int date = sc.nextInt();
            sc.nextLine();
            start.setBDate(date);
            int month = sc.nextInt();
            sc.nextLine();
            start.setBMonth(month);
            
            System.out.println(start.dOB());

        }

    }
