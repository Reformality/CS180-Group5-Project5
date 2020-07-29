import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

    
    public class UserAccount {
        
        private String userName;
        private String firstName;
        private String lastName;
        private int age;
        private String password;
        private int bYear; //input needs to be validated
        private int bDate; //input needs to be validated
        private int bMonth; //input needs to be validated
        
        private String messageStatus; //not sure if this needs to be included, but it's for the user's status.
        

        
        public UserAccount(String userName, String firstName, String lastName, int age, String password, int bYear, int bDate, int bMonth, String messageStatus) {
            this.userName = userName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.password = password;
            this.bYear = bYear;
            this.bDate = bDate;
            this.bMonth = bMonth;
            
            this.messageStatus = messageStatus;
            
        }

        public UserAccount() {

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


        public static void main(String[] args) { //Ignore this. Just testing the format for DOB
            UserAccount start = new UserAccount();
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
