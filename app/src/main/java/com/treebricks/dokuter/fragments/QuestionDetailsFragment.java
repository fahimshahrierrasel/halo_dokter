package com.treebricks.dokuter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.api.ApiUtils;
import com.treebricks.dokuter.api.QuestionService;
import com.treebricks.dokuter.items.AnswerItem;
import com.treebricks.dokuter.items.QuestionItem;
import com.treebricks.dokuter.models.Answer;
import com.treebricks.dokuter.models.QuestionDetails;
import com.treebricks.dokuter.utils.AppPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class QuestionDetailsFragment extends Fragment {

    AppPreferenceManager appPrefs;
    private List<Answer> questionAnswers;
    private RecyclerView rvAnswers;
    private ItemAdapter<AnswerItem> answersItemAdapter;
    private FastAdapter<AnswerItem> answersFastAdapter;
    private QuestionService questionService;
    private TextView tvQuestionTitle;
    private TextView tvQuestionType;
    private TextView tvQuestionDate;
    private TextView tvQuestionBody;

    public static QuestionDetailsFragment newInstance(int questionId) {
        QuestionDetailsFragment qdf = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("QID", questionId);
        qdf.setArguments(args);
        return qdf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_question_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAnswers = view.findViewById(R.id.rv_answers);
        tvQuestionTitle = view.findViewById(R.id.tv_doc_name);
        tvQuestionType = view.findViewById(R.id.tv_doc_edu);
        tvQuestionDate = view.findViewById(R.id.tv_question_date);
        tvQuestionBody = view.findViewById(R.id.tv_question_body);

        int qid = 0;
        if (getArguments() != null) {
            qid = getArguments().getInt("QID");
        }

        answersItemAdapter = items();
        answersFastAdapter = FastAdapter.with(answersItemAdapter);

        rvAnswers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAnswers.setItemAnimator(new DefaultItemAnimator());
        rvAnswers.setAdapter(answersFastAdapter);
        answersFastAdapter.withSelectable(true);

        questionAnswers = new ArrayList<>();

        questionService = ApiUtils.getQuestionService();
        Call<QuestionDetails> call = questionService.getQuestionDetails(qid);

        call.enqueue(new Callback<QuestionDetails>() {
            @Override
            public void onResponse(Call<QuestionDetails> call, Response<QuestionDetails> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    QuestionDetails questionDetails = response.body();
                    if(questionDetails != null) {
                        getAnswers(questionDetails.getId());
                        setQuestionDetails(questionDetails);
                    }
                }
            }
            @Override
            public void onFailure(Call<QuestionDetails> call, Throwable t) {

            }
        });

        answersFastAdapter.withOnClickListener(new OnClickListener<AnswerItem>() {
            @Override
            public boolean onClick(@Nullable View v, @NonNull IAdapter<AnswerItem> adapter, @NonNull AnswerItem item, int position) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.questions_fragment_placeholder, AnswerDetailsFragment.newInstance(questionAnswers.get(position)))
                        .addToBackStack(null)
                        .commit();

                return true;
            }
        });
    }

    private void setQuestionDetails(QuestionDetails questionDetails) {
        tvQuestionTitle.setText(questionDetails.getTitle());
        tvQuestionType.setText(questionDetails.getName());
        tvQuestionDate.setText(questionDetails.getCreatedAt());
        tvQuestionBody.setText(questionDetails.getBody());
    }

    private void getAnswers(int quid) {
        Call<List<Answer>> call = questionService.getQuestionAnswers(quid);
        call.enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Answer>> call, @NonNull Response<List<Answer>> response) {
                int statusCode = response.code();
                LogUtils.d(statusCode);
                if (statusCode == 200) {
                    questionAnswers = response.body();
                    initAnswersRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {
                // TODO Exception need to be handled
            }
        });
    }

    private void initAnswersRecyclerView() {
        List<AnswerItem> answerItems = new ArrayList<>();
        for (Answer answer : questionAnswers) {
            answerItems.add(new AnswerItem(answer.getName(), answer.getEducation(), answer.getCreatedAt(), answer.getAnswer()));
        }
        answersItemAdapter.add(answerItems);
        answersFastAdapter.notifyAdapterDataSetChanged();
    }
}
