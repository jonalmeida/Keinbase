package com.jonalmeida.keinbase;

public class Profile {
//    "profile": {
//        "mtime": 1412748135,
//        "full_name": "Jonathan Almeida",
//        "location": "Toronto, Canada",
//        "bio": "Software Developer and Student"
//    },
    private String mtime;
    private String full_name;
    private String location;
    private String bio;

    public static final String MTIME = "mtime";
    public static final String FULL_NAME = "full_name";
    public static final String LOCATION = "location";
    public static final String BIO = "bio";

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
