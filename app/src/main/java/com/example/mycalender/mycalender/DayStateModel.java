package com.example.mycalender.mycalender;

import java.io.Serializable;


public class DayStateModel implements Serializable {

    public String day;
    public int dayState;

    public DayStateModel(String day, int dayState) {
        this.day = day;
        this.dayState = dayState;
    }
}
