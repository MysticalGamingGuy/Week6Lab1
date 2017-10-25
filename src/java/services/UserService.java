package services;

import beans.User;
import java.util.ArrayList;
import java.util.Arrays;

public class UserService {
    
    private static final ArrayList<User> USERS = new ArrayList(Arrays.asList(new User("adam","password"),new User("betty","password")));
    
    public static boolean login(User user){
        return USERS.stream().anyMatch( u -> u.equals(user) );
    }
}
