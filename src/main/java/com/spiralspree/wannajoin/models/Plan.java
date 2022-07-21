package com.spiralspree.wannajoin.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

// TODO: implement Lombok for getters and reasonable setters
public class Plan {

    @Getter @Setter
    private String title;
    @Getter
    private final User host;
    @Getter @Setter
    private ArrayList<User> participants = new ArrayList<>();
    @Getter @Setter
    private Date dueDate;
    @Getter @Setter
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
