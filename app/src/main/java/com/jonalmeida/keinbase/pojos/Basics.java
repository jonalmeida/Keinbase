package com.jonalmeida.keinbase.pojos;

public class Basics {
//    "basics": {
//        "username": "jonalmeida",
//        "ctime": 1398722032,
//        "mtime": 1398722032,
//        "id_version": 118,
//        "track_version": 7,
//        "last_id_change": 1442959862,
//        "username_cased": "jonalmeida",
//        "salt": "a6270431095c5136de2dfa0fdefa6d3c",
//        "passphrase_generation": 1
//    }
    private String username;
    private String ctime;
    private String mtime;
    private int id_version;
    private int track_version;
    private int last_id_change;
    private String username_cased;
    private String salt;
    private int passphrase_generation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public int getId_version() {
        return id_version;
    }

    public void setId_version(int id_version) {
        this.id_version = id_version;
    }

    public int getTrack_version() {
        return track_version;
    }

    public void setTrack_version(int track_version) {
        this.track_version = track_version;
    }

    public int getLast_id_change() {
        return last_id_change;
    }

    public void setLast_id_change(int last_id_change) {
        this.last_id_change = last_id_change;
    }

    public String getUsername_cased() {
        return username_cased;
    }

    public void setUsername_cased(String username_cased) {
        this.username_cased = username_cased;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getPassphrase_generation() {
        return passphrase_generation;
    }

    public void setPassphrase_generation(int passphrase_generation) {
        this.passphrase_generation = passphrase_generation;
    }
}
