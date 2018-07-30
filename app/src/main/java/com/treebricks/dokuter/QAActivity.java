package com.treebricks.dokuter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.treebricks.dokuter.api.ApiUtils;
import com.treebricks.dokuter.api.QuestionService;
import com.treebricks.dokuter.items.QuestionItem;
import com.treebricks.dokuter.models.Question;
import com.treebricks.dokuter.utils.AppPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class QAActivity extends AppCompatActivity {

    AppPreferenceManager appPrefs;
    private List<Question> myQuestion;
    private RecyclerView rvQuestion;
    private ItemAdapter<QuestionItem> questionsItemAdapter;
    private FastAdapter<QuestionItem> questionsFastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Questions");
        }

        rvQuestion = findViewById(R.id.rv_questions);

        questionsItemAdapter = items();
        questionsFastAdapter = FastAdapter.with(questionsItemAdapter);

        rvQuestion.setLayoutManager(new LinearLayoutManager(this));
        rvQuestion.setItemAnimator(new DefaultItemAnimator());
        rvQuestion.setAdapter(questionsFastAdapter);
        questionsFastAdapter.withSelectable(true);

        myQuestion = new ArrayList<>();
        appPrefs = new AppPreferenceManager(this);
        int patientId = appPrefs.getPatientId();

        QuestionService questionService = ApiUtils.getQuestionService();
        Call<List<Question>> call = questionService.getMyQuestions(String.valueOf(patientId));
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    myQuestion = response.body();
                    initQuestionRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                // TODO Exception need to be handled
            }
        });
    }

    private void initQuestionRecyclerView() {
        List<QuestionItem> questionItems = new ArrayList<>();
        for (Question q : myQuestion) {
            questionItems.add(new QuestionItem(q.getTitle(), q.getName(), q.getCreatedAt()));
        }
        questionsItemAdapter.add(questionItems);
        questionsFastAdapter.notifyAdapterDataSetChanged();
    }
}
