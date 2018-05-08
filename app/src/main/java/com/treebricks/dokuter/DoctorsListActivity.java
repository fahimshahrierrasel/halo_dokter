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
import com.treebricks.dokuter.items.DoctorListItem;
import com.treebricks.dokuter.R;

import java.util.ArrayList;
import java.util.List;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class DoctorsListActivity extends AppCompatActivity {

    private FastAdapter<DoctorListItem> doctorListItemFastAdapter;
    private ItemAdapter<DoctorListItem> doctorListItemItemAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        recyclerView = findViewById(R.id.rv_doctor_list);

        doctorListItemItemAdapter = items();
        doctorListItemFastAdapter = FastAdapter.with(doctorListItemItemAdapter);

        List<DoctorListItem> items = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            DoctorListItem item = new DoctorListItem("Fahim Shahrier Rasel", null,
                    null, null,null, null,
                    null);
            items.add(item);
        }

        doctorListItemItemAdapter.add(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(doctorListItemFastAdapter);

        doctorListItemFastAdapter.withSelectable(true);

        doctorListItemFastAdapter.withOnClickListener(new OnClickListener<DoctorListItem>() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter<DoctorListItem> adapter, DoctorListItem item, int position) {
                System.out.println(position);
                System.out.println(item.toString());
                return true;
            }
        });
    }

}
