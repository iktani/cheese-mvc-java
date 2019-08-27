package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {
    static ArrayList<User> users = new ArrayList<>();

    public static void add(User user) {
        users.add(user);
    }

    public static ArrayList<User> getAll() {
        return users;
    }

    public static User getById(int id) {
        User theUser = null;

        for (User eachUser : users) {
            if (eachUser.getUserId() == id) {
                theUser = eachUser;
            }
        }
        return theUser;
    }
}
