package com.treebricks.dokuter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.api.Api;
import com.treebricks.dokuter.api.ApiUtils;
import com.treebricks.dokuter.api.QuestionService;
import com.treebricks.dokuter.api.SpecialityService;
import com.treebricks.dokuter.models.Speciality;
import com.treebricks.dokuter.utils.AppPreferenceManager;
import com.treebricks.dokuter.utils.MultiTextWatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQActivity extends AppCompatActivity {

    private EditText etFaqDescription;
    private TextView tvFaqDescCharCounter;
    private EditText etFaqTitle;
    private TextView tvFaqTitleCounter;
    private Spinner spProfile;
    private Spinner spProblem;
    private List<Speciality> specialities;
    AppPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        preferenceManager = new AppPreferenceManager(this);

        spProfile = findViewById(R.id.spSelectPatient);
        spProblem = findViewById(R.id.spProblemType);
        tvFaqDescCharCounter = findViewById(R.id.tvFaqDescCharCounter);
        tvFaqTitleCounter = findViewById(R.id.tvFaqTitleCharCounter);
        etFaqDescription = findViewById(R.id.etFaqDescription);
        etFaqTitle = findViewById(R.id.etFaqTitle);

        new MultiTextWatcher()
                .registerEditText(etFaqTitle)
                .registerEditText(etFaqDescription)
                .setCallback(new MultiTextWatcher.TextWatcherWithInstance() {
                    @Override
                    public void beforeTextChanged(EditText editText, CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(EditText editText, CharSequence s, int start, int before, int count) {
                        int charCounter = s.length();
                        if(editText.getId() == R.id.etFaqTitle)
                        {
                            tvFaqTitleCounter.setText(String.format(Locale.US, "%d / 40", charCounter));
                        }else  if(editText.getId() == R.id.etFaqDescription)
                        {
                            tvFaqDescCharCounter.setText(String.format(Locale.US, "%d / 1000", charCounter));
                        }
                    }
                    @Override
                    public void afterTextChanged(EditText editText, Editable editable) {

                    }
                });

        setSpeciality();

    }

    private void setSpeciality() {
        SpecialityService specialityService = ApiUtils.getSpecialityService();

        Call<List<Speciality>> call = specialityService.getSpeciality();

        call.enqueue(new Callback<List<Speciality>>() {
            @Override
            public void onResponse(@NonNull Call<List<Speciality>> call, @NonNull Response<List<Speciality>> response) {
                int statusCode = response.code();
                LogUtils.d(statusCode);
                if(statusCode == 200) {
                    specialities = response.body();
                    List<String> specialityList = new ArrayList<>();
                    specialityList.add(getResources().getString(R.string.faq_problem_type_hint));
                    if (specialities != null) {
                        for(Speciality s : specialities) {
                            specialityList.add(s.getName());
                        }
                    }
                    ArrayAdapter<String> specialityAdapter = new ArrayAdapter<>(FAQActivity.this,
                            android.R.layout.simple_spinner_dropdown_item, specialityList);
                    spProblem.setAdapter(specialityAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Speciality>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.faq_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_submit:
                submitQuestion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void submitQuestion() {
        int specialityId = getSpecialityId(spProblem.getSelectedItem().toString());
        String title = etFaqTitle.getText().toString();
        String description = etFaqDescription.getText().toString();
        int userId = preferenceManager.getPatientId();

        if(specialityId == 0) {
            Toast.makeText(this, "Please select problem type first!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(title.length() < 10) {
            Toast.makeText(this, "Title should be at least 10 character.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(description.length() < 100) {
            Toast.makeText(this, "Description should be at least 100 character.", Toast.LENGTH_SHORT).show();
            return;
        }

        QuestionService questionService = ApiUtils.getQuestionService();

        Map<String, String> fields = new HashMap<>();
        fields.put("title", title);
        fields.put("body", description);
        fields.put("patient_id", String.valueOf(userId));
        fields.put("problem_type_id", String.valueOf(specialityId));

        Call<String> call = questionService.saveQuestion(fields);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                int statusCode = response.code();
                LogUtils.d(statusCode);
                if(statusCode == 201 ){
                    Toast.makeText(FAQActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(FAQActivity.this, "Question server is not responding", Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(this, "Submitted " + specialityId, Toast.LENGTH_SHORT).show();
    }

    private int getSpecialityId(String problemName) {
        for(Speciality speciality : specialities)
        {
            if(speciality.getName().equals(problemName)){
                return speciality.getId();
            }
        }
        return 0;
    }
}
