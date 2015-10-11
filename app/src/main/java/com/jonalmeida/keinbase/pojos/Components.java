package com.jonalmeida.keinbase.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Components {
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
