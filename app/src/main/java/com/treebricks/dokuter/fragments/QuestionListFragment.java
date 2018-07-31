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

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.api.ApiUtils;
import com.treebricks.dokuter.api.QuestionService;
import com.treebricks.dokuter.items.DoctorListItem;
import com.treebricks.dokuter.items.QuestionItem;
import com.treebricks.dokuter.models.Question;
import com.treebricks.dokuter.utils.AppPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class QuestionListFragment extends Fragment {

    AppPreferenceManager appPrefs;
    private List<Question> myQuestion;
    private RecyclerView rvQuestion;
    private ItemAdapter<QuestionItem> questionsItemAdapter;
    private FastAdapter<QuestionItem> questionsFastAdapter;

    public static QuestionListFragment newInstance() {
        QuestionListFragment dsf = new QuestionListFragment();
        return dsf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_questions_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvQuestion = view.findViewById(R.id.rv_questions);

        questionsItemAdapter = items();
        questionsFastAdapter = FastAdapter.with(questionsItemAdapter);

        rvQuestion.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvQuestion.setItemAnimator(new DefaultItemAnimator());
        rvQuestion.setAdapter(questionsFastAdapter);
        questionsFastAdapter.withSelectable(true);

        myQuestion = new ArrayList<>();
        appPrefs = new AppPreferenceManager(getActivity());
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

        questionsFastAdapter.withOnClickListener(new OnClickListener<QuestionItem>() {
            @Override
            public boolean onClick(@Nullable View v, @NonNull IAdapter<QuestionItem> adapter, @NonNull QuestionItem item, int position) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.questions_fragment_placeholder, QuestionDetailsFragment.newInstance(myQuestion.get(position).getId()))
                        .addToBackStack(null)
                        .commit();

                return true;
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
