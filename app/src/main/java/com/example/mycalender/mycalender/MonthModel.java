package com.example.mycalender.mycalender;

import java.io.Serializable;

public class MonthModel implements Serializable {

    public String monthName;
    public int monthCount;

    public MonthModel(String monthName, int monthCount) {
        this.monthName = monthName;
        this.monthCount = monthCount;
    }
}
