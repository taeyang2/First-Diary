package com.cookandroid.first_dairy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;

public class MainActivity extends AppCompatActivity {

    public String selectedDate, fileName;
    public static Context context;

    ImageView search, write;
    CalendarView calendar;
    EditText showDiary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (ImageView) findViewById(R.id.btn_search);
        write = (ImageView) findViewById(R.id.btn_write);
        calendar = (CalendarView) findViewById(R.id.calendar);
        showDiary = (EditText) findViewById(R.id.show_diary);

        //버튼으로 편집창 열기
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),writing_window.class);
                startActivity(intent);
            }
        });

        //날짜 선택 후 편집창 열기
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int selectYear = year;
                int selectMonth = month+1;
                int selectDay = dayOfMonth;




                //선택한 날짜 저장
                selectedDate = (Integer.toString(selectYear) + "년 "
                        + Integer.toString(selectMonth) + "월 "
                        + Integer.toString(selectDay) + "일");

                //편집창 열기
                Intent intent = new Intent(getApplicationContext(),writing_window.class);
                startActivity(intent);

            }

        });


        context=this;

    }



}
