package com.treebricks.dokuter;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.treebricks.dokuter.R;

import java.util.Calendar;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    Button datepick;
    EditText birthdate;
    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        datepick = findViewById(R.id.datepicker);
        birthdate = findViewById(R.id.dateofbirth);
        datepick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==datepick){
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    birthdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }

            },day,month,year);
            datePickerDialog.show();
        }
    }
}
