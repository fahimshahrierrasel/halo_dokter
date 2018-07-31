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
import com.treebricks.dokuter.fragments.DoctorListFragment;
import com.treebricks.dokuter.fragments.QuestionListFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Questions");
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.questions_fragment_placeholder, QuestionListFragment.newInstance())
                .commit();
    }
}
