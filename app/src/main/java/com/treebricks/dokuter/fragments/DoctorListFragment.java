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
import com.treebricks.dokuter.items.DoctorListItem;

import java.util.ArrayList;
import java.util.List;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class DoctorListFragment extends Fragment {

    private ArrayList<String> items;

    public static DoctorListFragment newInstance(String doctorCategory) {
        DoctorListFragment dsf = new DoctorListFragment();
        Bundle args = new Bundle();
        args.putString("DOC_CATE", doctorCategory);
        dsf.setArguments(args);
        return dsf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_doctor_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FastAdapter<DoctorListItem> doctorListItemFastAdapter;
        ItemAdapter<DoctorListItem> doctorListItemItemAdapter;
        RecyclerView recyclerView;

        String category = "doc";
        if (getArguments() != null) {
            category = getArguments().getString("DOC_CATE");
        }

        recyclerView = view.findViewById(R.id.rv_doctor_list);

        doctorListItemItemAdapter = items();
        doctorListItemFastAdapter = FastAdapter.with(doctorListItemItemAdapter);

        List<DoctorListItem> items = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            DoctorListItem item = new DoctorListItem("Fahim Shahrier Rasel", null,
                    null, null, null, null,
                    null);
            items.add(item);
        }

        doctorListItemItemAdapter.add(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(doctorListItemFastAdapter);

        doctorListItemFastAdapter.withSelectable(true);

        doctorListItemFastAdapter.withOnClickListener(new OnClickListener<DoctorListItem>() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter<DoctorListItem> adapter, DoctorListItem item, int position) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.doctor_list_fragment_placeholder, DoctorDetailsFragment.newInstance("doc"))
                        .addToBackStack(null)
                        .commit();
                return true;
            }
        });
    }
}
