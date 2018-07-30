package com.treebricks.dokuter.items;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.treebricks.dokuter.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuestionItem extends AbstractItem<QuestionItem, QuestionItem.ViewHolder> {

    private String questionTitle;
    private String questionType;
    private String questionDate;

    public QuestionItem(String questionTitle, String questionType, String questionDate) {
        this.questionTitle = questionTitle;
        this.questionType = questionType;
        this.questionDate = questionDate;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.question_list_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.question_card;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<QuestionItem> {
        TextView tvQuestionTitle;
        TextView tvQuestionType;
        TextView tvQuestionDate;

        ViewHolder(View view) {
            super(view);
            tvQuestionTitle = view.findViewById(R.id.tv_question_title);
            tvQuestionType = view.findViewById(R.id.tv_question_type);
            tvQuestionDate = view.findViewById(R.id.tv_question_date);
        }

        @Override
        public void bindView(@NonNull QuestionItem item, @NonNull List<Object> payloads) {
            Context ctx = itemView.getContext();
            tvQuestionTitle.setText(item.questionTitle);
            tvQuestionType.setText(item.questionType);
            Date questionDate = TimeUtils.string2Date(item.questionDate,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US));

            long days = TimeUtils.getTimeSpanByNow(questionDate, TimeConstants.DAY);
            String daysAgo = String.format(Locale.US,"%d days ago", Math.abs(days));

            tvQuestionDate.setText(daysAgo);
        }

        @Override
        public void unbindView(@NonNull QuestionItem item) {
            tvQuestionTitle.setText(null);
        }
    }
}
