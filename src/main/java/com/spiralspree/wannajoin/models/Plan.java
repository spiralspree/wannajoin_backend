package com.spiralspree.wannajoin.models;

import java.util.ArrayList;
import java.util.Date;

// TODO: implement Lombok for getters and reasonable setters
public class Plan {

    private String title;
    private final User host;
    private ArrayList<User> participants = new ArrayList<>();
    private Date dueDate;
    private boolean isAchieved = false;

    public Plan(String title, User host) {
        this.title = title;
        this.host = host;
    }

    // TODO: implement
    public void removeParticipant() {
        return;
    }

    // TODO: implement
    public void addParticipant() {
        return;
    }

    // TODO: implement
    public void removeHost() {
        return;
    }


}
