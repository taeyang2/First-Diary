package com.cookandroid.first_dairy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class writing_window extends Activity {

    private DatePickerDialog.OnDateSetListener callbackMethod;

    ImageView header;
    Button cancle, save;
    EditText set_date, set_title, writing_area;
    String set_fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing_window);


        header = (ImageView) findViewById(R.id.header);
        cancle = (Button) findViewById(R.id.btn_cancle);
        save = (Button) findViewById(R.id.btn_save);
        set_date = (EditText) findViewById(R.id.date);
        set_title = (EditText) findViewById(R.id.setTitle);
        writing_area = (EditText) findViewById(R.id.wirtingArea);


        //편집 취소 후 홈화면으로 돌아가기
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //캘린더에서 선택된 날짜 자동 입력
        String date = ((MainActivity)MainActivity.context).selectedDate;
        set_date.setText(date);

        //일기 저장
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    set_fileName = set_title.getText().toString();
                    FileOutputStream outFs = openFileOutput(set_fileName, Context.MODE_PRIVATE);
                    String str = writing_area.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), set_fileName+"이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
                            }
        });




    }
}
