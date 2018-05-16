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
import android.widget.Button;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.items.DoctorListItem;

import java.util.ArrayList;
import java.util.List;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class TimeSlotSelectFragment extends Fragment {

    private ArrayList<String> items;

    public static TimeSlotSelectFragment newInstance(String doctorCategory) {
        TimeSlotSelectFragment dsf = new TimeSlotSelectFragment();
        Bundle args = new Bundle();
        args.putString("DOC_CATE", doctorCategory);
        dsf.setArguments(args);
        return dsf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_select_time_slot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnTimeConfirm = view.findViewById(R.id.btn_time_confirm);

        btnTimeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.doctor_list_fragment_placeholder, BookConfirmationFragment.newInstance("doc"))
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}
