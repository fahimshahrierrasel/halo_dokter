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
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.glide.slider.library.svg.GlideApp;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.api.ApiUtils;
import com.treebricks.dokuter.api.QuestionService;
import com.treebricks.dokuter.items.AnswerItem;
import com.treebricks.dokuter.models.Answer;
import com.treebricks.dokuter.models.QuestionDetails;
import com.treebricks.dokuter.utils.AppPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

public class AnswerDetailsFragment extends Fragment {

    private CircleImageView ivDocImage;
    private TextView tvDocName;
    private TextView tvDocEdu;
    private TextView tvDocExp;
    private TextView tvAnswerDate;
    private TextView tvAnswerBody;

    public static AnswerDetailsFragment newInstance(Answer answer) {
        AnswerDetailsFragment qdf = new AnswerDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("ANS", answer);
        qdf.setArguments(args);
        return qdf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_answer_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivDocImage = view.findViewById(R.id.iv_doc_image);
        tvDocName = view.findViewById(R.id.tv_doc_name);
        tvDocEdu = view.findViewById(R.id.tv_doc_edu);
        tvDocExp = view.findViewById(R.id.tv_doc_exp);
        tvAnswerDate = view.findViewById(R.id.tv_answer_date);
        tvAnswerBody = view.findViewById(R.id.tv_answer_body);

        if (getArguments() != null) {
            Answer answer = getArguments().getParcelable("ANS");
            setAnswerUi(answer);
        }
    }

    private void setAnswerUi(Answer answer) {
        String imageUrl = getActivity().getResources().getString(R.string.dokuter_root) +
                getActivity().getResources().getString(R.string.doc_image_link, answer.getPic());

        GlideApp.with(getActivity())
                .load(imageUrl)
                .into(ivDocImage);

        tvDocName.setText(answer.getName());
        tvDocEdu.setText(answer.getEducation());
        tvDocExp.setText(getActivity().getResources().getString(R.string.doc_exp, answer.getExperience()));
        tvAnswerDate.setText(answer.getCreatedAt());
        tvAnswerBody.setText(answer.getAnswer());

    }
}
