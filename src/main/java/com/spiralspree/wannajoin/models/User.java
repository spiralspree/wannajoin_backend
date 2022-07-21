package com.spiralspree.wannajoin.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

// TODO: implement Lombok for getters and reasonable setters
public class User {

    @Getter
    private final String name;
    @Getter @Setter
    private ArrayList<User> friends = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    // TODO: implement
    public void addFriend() {
        return;
    }

    // TODO: implement
    public void removeFriend() {
        return;
    }

}
