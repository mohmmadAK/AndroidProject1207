package com.example.mycalender.mycalender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CalenderAdapter extends BaseAdapter
{
    private final Context _context;
    private final List<DayStateModel> list;
    private ArrayList<MonthModel> monthStructure;

    private int currentDay;
    private int currentMonth;
    private int currentYear;

    private final int CURRRENT_MONTH = 1;
    private final int OTHER_MONTH = 2;

    public CalenderAdapter(Context context, int currentYear, int currentMonth, int currentDay,
                           ArrayList<MonthModel> monthStructure)
    {
        this._context = context;
        this.list = new ArrayList<>();

        this.currentYear = currentYear;
        this.currentMonth = currentMonth -1;
        this.currentDay = currentDay;

        this.monthStructure = monthStructure;

        if(currentYear % 4 == 0) monthStructure.get(1).monthCount = 29;

        calculateMonth();
    }

    public DayStateModel getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    private void calculateMonth() {

        Calendar calendar = Calendar.getInstance();

        if(calendar.get(Calendar.MONTH) != currentMonth ||
                calendar.get(Calendar.YEAR) != currentYear) {

            currentDay = -1;
        }
        calendar.set(currentYear, currentMonth, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        int previousMonthDays = monthStructure.get(currentMonth).monthCount;

        for (int i = firstDayOfWeek; i > 1; i--) {

            list.add(new DayStateModel(String.valueOf(previousMonthDays--), OTHER_MONTH));
        }

        Collections.reverse(list);

        for (int i = 1; i <= monthStructure.get(currentMonth).monthCount; i++) {

            list.add(new DayStateModel(String.valueOf(i), CURRRENT_MONTH));
        }

        for (int i = 1; i + list.size() < 42; i++) {

            list.add(new DayStateModel(String.valueOf(i), OTHER_MONTH));
        }
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.gridcell, parent, false);

        LinearLayout item = (LinearLayout) convertView.findViewById(R.id.item);
        TextView tvDay = (TextView) convertView.findViewById(R.id.tvDay);
        final ImageView ivMood = (ImageView) convertView.findViewById(R.id.ivMood);

        tvDay.setText(list.get(position).day);

        if (list.get(position).dayState == OTHER_MONTH)
        {
            tvDay.setTextColor(Color.LTGRAY);
        }
        if (list.get(position).dayState == CURRRENT_MONTH)
        {
            if(list.get(position).day.equals(String.valueOf(currentDay))) {
                tvDay.setTextColor(Color.RED);
            }
            else {
                tvDay.setTextColor(Color.BLACK);
            }
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(_context);
                    builder1.setMessage("Do you want to set mood \nIts complete functionality will be added in next release");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ivMood.setVisibility(View.VISIBLE);
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });
        }

        return convertView;
    }
}