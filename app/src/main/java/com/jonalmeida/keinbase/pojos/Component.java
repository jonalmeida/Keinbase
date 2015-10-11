package com.jonalmeida.keinbase.pojos;

public class Component {
    private String val;
    private float score;

    public static final String VAL = "val";
    public static final String SCORE = "score";

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
}
