package com.example.mycalender.mycalender;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private GridView calendarView;
    private TextView tvDate;
    private int currentYear, currentMonth, currentDay;
    private ArrayList<MonthModel> monthStructure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        drawMonthStructure();

        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        setAdapter();
    }

    /*
    * Initializing different views of layout
    * */
    private void init() {

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        ImageView prevMonth = (ImageView) findViewById(R.id.prevMonth);
        ImageView nextMonth = (ImageView) findViewById(R.id.nextMonth);

        calendarView = (GridView) findViewById(R.id.calendar);
        tvDate = (TextView) findViewById(R.id.tvDate);

        fab_add.setOnClickListener(this);
        prevMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
    }

    /*
    * prevMonth : Handles data needed to be updated in previous month
    * nextMonth : Handles data needed to be updated in next month
    * btnAdd : allowing user to add new event
    * */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.prevMonth:

                if(currentMonth == 1) {
                    currentMonth = 12;
                    currentYear = currentYear - 1;
                } else {
                    currentMonth--;
                }

                setAdapter();
                break;

            case R.id.nextMonth:

                if (currentMonth == 12) {
                    currentMonth = 1;
                    currentYear++;
                } else {
                    currentMonth++;
                }

                setAdapter();
                break;

            case R.id.fab_add:

                Intent i = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(i);
                break;
        }
    }


    private void setAdapter() {

        tvDate.setText(monthStructure.get(currentMonth - 1).monthName + " " + currentYear);
        CalenderAdapter adapter = new CalenderAdapter(MainActivity.this, currentYear, currentMonth, currentDay, monthStructure);
        calendarView.setAdapter(adapter);
    }


    /*
    * This method is populating days count to different month
    * */
    private void drawMonthStructure() {

        monthStructure = new ArrayList<>();

        monthStructure.add(new MonthModel("January" , 31));
        monthStructure.add(new MonthModel("February" , 28));
        monthStructure.add(new MonthModel("March" , 31));
        monthStructure.add(new MonthModel("April" , 30));
        monthStructure.add(new MonthModel("May" , 31));
        monthStructure.add(new MonthModel("June" , 30));
        monthStructure.add(new MonthModel("July" , 31));
        monthStructure.add(new MonthModel("August" , 31));
        monthStructure.add(new MonthModel("September" , 30));
        monthStructure.add(new MonthModel("October" , 31));
        monthStructure.add(new MonthModel("November" , 30));
        monthStructure.add(new MonthModel("December" , 31));
    }
}