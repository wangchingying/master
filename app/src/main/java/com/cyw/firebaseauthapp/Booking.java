package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

public class Booking extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    Calendar calendar;
    MaterialCalendarView mcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDate = calendar.get(Calendar.DATE);
        Log.d("today", String.valueOf(currentYear) + "  " + String.valueOf(currentMonth) + "  " + String.valueOf(currentDate));
        mcv = (MaterialCalendarView) findViewById(R.id.calendarView);
        mcv.state().edit()
                .setFirstDayOfWeek(calendar.SUNDAY)
                .setMinimumDate(CalendarDay.today())
                .setMaximumDate(CalendarDay.from(currentYear, currentMonth + 6, currentDate))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mcv.setOnDateChangedListener(this);
        mcv.setOnMonthChangedListener(this);

        //mcv.setAllowClickDaysOutsideCurrentMonth(true);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

        //CalendarDay selectedDate=mcv.getSelectedDate();
        //int selectedDay=calendar.get(Calendar.DAY_OF_WEEK);
        int selectedDay=mcv.getSelectedDate().getCalendar().get(Calendar.DAY_OF_WEEK);

        Log.d("selectedDay",String.valueOf(selectedDay));
        switch (selectedDay){
             case 2:
                Intent it2=new Intent(Booking.this,TimePeriod_A.class);
                startActivity(it2);
                break;
            case 3:
                Intent it3=new Intent(Booking.this,TimePeriod_A.class);
                startActivity(it3);
                break;
            case 4:
                Intent it4=new Intent(Booking.this,TimePeriod_A.class);
                startActivity(it4);
                break;
            case 5:
                Intent it5=new Intent(Booking.this,TimePeriod_A.class);
                startActivity(it5);
                break;
            case 6:
                Intent it6=new Intent(Booking.this,TimePeriod_A.class);
                startActivity(it6);
                break;
            case 7:
                Intent it7=new Intent(Booking.this,TimePeriod_B.class);
                startActivity(it7);
                break;
        }
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //這一行沒作用,不知道怎麼寫
        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));

    }
}