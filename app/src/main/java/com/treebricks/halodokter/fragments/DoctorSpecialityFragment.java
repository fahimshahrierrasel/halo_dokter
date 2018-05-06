package com.treebricks.halodokter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treebricks.halodokter.R;
import com.treebricks.halodokter.adapters.DoctorSpecialityListAdapter;

import java.util.ArrayList;

public class DoctorSpecialityFragment extends Fragment {

    private ArrayList<String> items;

    public static DoctorSpecialityFragment newInstance(String doctorCategory){
        DoctorSpecialityFragment dsf = new DoctorSpecialityFragment();
        Bundle args = new Bundle();
        System.out.println("On New Instance " + doctorCategory);
        args.putString("DOC_CATE", doctorCategory);
        dsf.setArguments(args);
        return dsf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_doctor_specialities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView searchRecyclerView = view.findViewById(R.id.rv_doctor_specialities);

        String category = "doc";
        if (getArguments() != null) {
            category = getArguments().getString("DOC_CATE");
        }

        setArrayListItems(category);

        DoctorSpecialityListAdapter adapter = new DoctorSpecialityListAdapter(items, getActivity());
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchRecyclerView.setAdapter(adapter);
    }

    private void setArrayListItems(String category){
        items = new ArrayList<>();
        if(category.equals("doc")) {
            items.clear();
            items.add("Ophthalmologist");
            items.add("Dermatologist");
            items.add("Cardiologist");
            items.add("Gastroenterologist");
            items.add("Psychiatrist");
            items.add("Eat-Nose-Throat (ENT) Specialist");
            items.add("Gynecologist/Obstetrician");
            items.add("Neurologist");
            items.add("Urologist");
        }else if(category.equals("den")){
            items.clear();
            items.add("Dentist");
            items.add("Prosthodonist");
            items.add("Orthodontist");
            items.add("Pediatric Dentist");
            items.add("Endodontist");
            items.add("Implantologist");
        }
    }
}
