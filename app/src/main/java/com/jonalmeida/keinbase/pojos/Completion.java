package com.jonalmeida.keinbase.pojos;

import java.util.Map;

public class Completion {
//{
//    "total_score": 0.024924242424242425,
//    "components": {
//        "username": {
//            "val": "galmeida",
//            "score": 0.006111111111111111
//        },
//        "key_fingerprint": {
//            "val": "040ef7c3bd5e1a00a66cdce518b2db0571cae11b",
//            "algo": 1,
//            "nbits": 4096,
//            "score": 0
//        },
//        "twitter": {
//            "val": "galmeida_13",
//            "score": 0.004166666666666667
//        },
//        "github": {
//            "val": "galmeida",
//            "score": 0.005555555555555556
//        },
//        "websites": [{
//            "val": "almeida.io",
//            "protocol": "dns",
//            "score": 0.009090909090909092
//        }]
//    },
//    "uid": "76c9be54f974ed66e46b04ddadee8a00",
//    "thumbnail": "https://s3.amazonaws.com/keybase_processed_uploads/74ebaf5f05b454bb8341bba16c4da205_200_200_square_200.png",
//    "is_followee": false
//}
    private float total_score;
    private Map<String, Object> components;
    private String uid;
    private String thumbnail;
    private boolean is_followee;

    public static final String TOTAL_SCORE   = "total_score";
    public static final String COMPONENTS    = "components";
    public static final String UID           = "uid";
    public static final String THUMBNAIL     = "thumbnail";
    public static final String IS_FOLLOWEE   = "is_followee";

    public float getTotal_score() {
        return total_score;
    }

    public void setTotal_score(float total_score) {
        this.total_score = total_score;
    }

//    public Map<String, Components> getComponents() {
//        return components;
//    }
//
//    public void setComponents(Map<String, Components> components) {
//        this.components = components;
//    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean is_followee() {
        return is_followee;
    }

    public void setIs_followee(boolean is_followee) {
        this.is_followee = is_followee;
    }

    public Map<String, Object> getComponents() {
        return components;
    }

    public void setComponents(Map<String, Object> components) {
        this.components = components;
    }
}
