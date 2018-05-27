package com.treebricks.dokuter;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.treebricks.dokuter.R;
import com.treebricks.dokuter.models.User;

import java.util.Calendar;
import java.util.Locale;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    EditText birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        // Making notification bar transparent
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        birthdate = findViewById(R.id.dateofbirth);
        birthdate.setInputType(InputType.TYPE_NULL);
        birthdate.setOnClickListener(this);
        birthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    SetDateofBirth();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dateofbirth){
            SetDateofBirth();
        }
    }

    private void SetDateofBirth() {
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(UserRegistration.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = String.format(Locale.US, "%d/%02d/%d", dayOfMonth, month + 1, year);
                birthdate.setText(date);
            }

        }, day, month, year);
        datePickerDialog.show();
    }
}
