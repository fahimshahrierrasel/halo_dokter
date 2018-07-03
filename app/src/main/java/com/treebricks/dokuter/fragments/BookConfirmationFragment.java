package com.treebricks.dokuter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.treebricks.dokuter.R;

import java.util.ArrayList;

public class BookConfirmationFragment extends Fragment {

    private ArrayList<String> items;

    public static BookConfirmationFragment newInstance(String doctorCategory) {
        BookConfirmationFragment dsf = new BookConfirmationFragment();
        Bundle args = new Bundle();
        args.putString("DOC_CATE", doctorCategory);
        dsf.setArguments(args);
        return dsf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_book_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
