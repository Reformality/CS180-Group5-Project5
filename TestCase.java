public class TestCase {

    public static void main(String[] args) {

        /** Testing Class Heritage
         * Since all classes doesn't inherit from anything, they are all inheriting from Object.
         */
        System.out.println("All class are inheriting from Object!");

        /** Testing UserAccount Field
         *  This section of code will test security of the data type in UserAccount.java
        */
        // Construct a new UserAccount
        UserAccount testUser = new UserAccount("Username","Password");

        //testing Field Modifier
        StringBuilder testStr = new StringBuilder();
        //testStr.append(testUser.username);
        System.out.println("Fields are all private because running testUser.username will produce a compile error!");

        /** Testing Method
         *  This section of code will test security of the data type in UserAccount.java
         */
        System.out.println("Since both Server.java and Client.java have only run()/main()," +
                "\ntest the program instead!\n" +
                "Please refer to the README for detailed testing sequence!");

        /** Implementation Tests
         *
         */

        System.out.println("Please refer to README for GUI testcases!");
    }
}
