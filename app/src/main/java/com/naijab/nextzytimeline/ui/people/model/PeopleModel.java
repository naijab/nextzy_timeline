package com.naijab.nextzytimeline.ui.people.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PeopleModel extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String birthday;
    private String jobstart;
    private String job;
    private String game;
    private String smartphone;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJobstart() {
        return jobstart;
    }

    public void setJobstart(String jobstart) {
        this.jobstart = jobstart;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(String smartphone) {
        this.smartphone = smartphone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
