import java.util.ArrayList;
import java.util.HashMap;

public class UserFriends {
    private String userName;
    
    private ArrayList<UserFriends> friends;

    public UserFriends() {
        
    }


    public String getFriendName() {
        return this.userName;
    }
    public void setFriendName(String friendName) {
        this.userName = friendName;
    }

}
