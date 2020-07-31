import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

    
    public class UserAccount {
        
        private String userName;
        private String firstName;
        private String lastName;
        private int age;
        private String password;
        private int bYear;
        private int bDate;
        private int bMonth;
        private String dOB;
        private String messageStatus;
        

        
        public UserAccount(String userName, String firstName, String lastName, int age, String password, int bYear, int bDate, int bMonth, String dOB, String messageStatus) {
            this.userName = userName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.password = password;
            this.bYear = bYear;
            this.bDate = bDate;
            this.bMonth = bMonth;
            this.dOB = dOB;
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


        public String getDOB() {
          
            return String.format("%d/%d/%d", getBMonth(), getBDate(), getBYear());
        }
        public void setDOB(String dOB) {
            
            String[] input = dOB.split("/");
            int date = Integer.parseInt(input[0]);
            setBDate(date);
            int month = Integer.parseInt(input[1]);
            setBMonth(month);
            int year = Integer.parseInt(input[2]);
            setBYear(year);
            this.dOB = dOB;
        }

        public String getStatus() {
            return this.messageStatus;
        }
    
        public void setStatus(String messageStatus) {
            this.messageStatus = messageStatus;
        }

        public String profileInfo() {
            return String.format("First:" + this.firstName + ";" + "Last:" + this.lastName + ";" + "Username:" + this.userName + ";" + "Age:" + this.age + ";" + "DOB:" + this.dOB+ ";" + "Password:" + this.password);
        }

        public static void main(String[] args) {
            UserAccount start = new UserAccount();
            Scanner sc = new Scanner(System.in);
            String first = sc.nextLine();
            start.setFirstName(first);
            String last = sc.nextLine();
            start.setLastName(last);
            String password = sc.nextLine();
            start.setPassword(password);
            int age = sc.nextInt();
            sc.nextLine();
            start.setAge(age);
            int year = sc.nextInt();
            sc.nextLine();
            start.setBYear(year);
            int date = sc.nextInt();
            sc.nextLine();
            start.setBDate(date);
            int month = sc.nextInt();
            sc.nextLine();
            start.setBMonth(month);
            
            System.out.println(start.getDOB());

        }

    }
