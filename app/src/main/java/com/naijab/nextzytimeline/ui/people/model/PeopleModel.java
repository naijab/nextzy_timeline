package com.naijab.nextzytimeline.ui.people.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PeopleModel extends RealmObject implements Parcelable{

    @PrimaryKey
    private int id;
    private String name;
    private String job;
    private String birthDay;
    private String jobStart;
    private String jobDescription;
    private String game;
    private String smartPhone;
    private String profile;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getJobStart() {
        return jobStart;
    }

    public void setJobStart(String jobStart) {
        this.jobStart = jobStart;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(String smartPhone) {
        this.smartPhone = smartPhone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.job);
        dest.writeString(this.birthDay);
        dest.writeString(this.jobStart);
        dest.writeString(this.jobDescription);
        dest.writeString(this.game);
        dest.writeString(this.smartPhone);
        dest.writeString(this.profile);
        dest.writeString(this.photo);
    }

    public PeopleModel() {
    }

    protected PeopleModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.job = in.readString();
        this.birthDay = in.readString();
        this.jobStart = in.readString();
        this.jobDescription = in.readString();
        this.game = in.readString();
        this.smartPhone = in.readString();
        this.profile = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<PeopleModel> CREATOR = new Creator<PeopleModel>() {
        @Override
        public PeopleModel createFromParcel(Parcel source) {
            return new PeopleModel(source);
        }

        @Override
        public PeopleModel[] newArray(int size) {
            return new PeopleModel[size];
        }
    };
}
