package com.treebricks.dokuter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.treebricks.dokuter.fragments.DoctorListFragment;
import com.treebricks.dokuter.items.DoctorListItem;
import com.treebricks.dokuter.R;

import java.util.ArrayList;
import java.util.List;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class DoctorsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.doctor_list_fragment_placeholder, DoctorListFragment.newInstance("doc"))
                .commit();
    }

}
