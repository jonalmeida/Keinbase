package com.jonalmeida.keinbase.pojos;

public class WebsiteComponent {
    private String val;
    private String protocol;
    private float score;

    public static final String VAL = "val";
    public static final String SCORE = "score";
    public static final String PROTOCOL = "protocol";

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
