package com.cookandroid.first_dairy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;

public class MainActivity extends AppCompatActivity {

    public String selectedDate;
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


/*
                String saved_fileName = ((writing_window)writing_window.context).set_fileName;
*/

               //선택 날짜에 저장된 일기가 있는 경우 일기 제목을 보여줌
                String str = readDiary(selectedDate);
                showDiary.setVisibility(View.VISIBLE);
                showDiary.setText(str);


                //편집창 열기
                Intent intent = new Intent(getApplicationContext(),writing_window.class);
                startActivity(intent);
            }

        });


        context=this;

    }
        //저장된 일기를 불러오는 함수
        String readDiary(String fName){
            String diaryStr = null;
            FileInputStream inFs;
            try{
                inFs = openFileInput(fName);
                byte[] txt = new byte[500];//500=>diaryStr.available()
                inFs.read(txt);
                inFs.close();
                diaryStr = (new String(txt)).trim();

            }catch (IOException e){
                Toast.makeText(getApplicationContext(), "저장된 일기가 없습니다.", Toast.LENGTH_SHORT).show();

                //편집창 열기
                /*Intent intent = new Intent(getApplicationContext(),writing_window.class);
                startActivity(intent);*/

            }
            return diaryStr;
        }



}
