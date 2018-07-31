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

public class AnswerItem extends AbstractItem<AnswerItem, AnswerItem.ViewHolder> {

    private String docName;
    private String docEdu;
    private String answerDate;
    private String answerBody;

    public AnswerItem(String docName, String docEdu, String answerDate, String answerBody) {
        this.docName = docName;
        this.docEdu = docEdu;
        this.answerDate = answerDate;
        this.answerBody = answerBody;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.answer_list_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.answer_card;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<AnswerItem> {
        TextView tvDocName;
        TextView tvDocEdu;
        TextView tvAnsDate;
        TextView tvAnsBody;

        ViewHolder(View view) {
            super(view);
            tvDocName = view.findViewById(R.id.tv_doc_name);
            tvDocEdu = view.findViewById(R.id.tv_doc_edu);
            tvAnsDate = view.findViewById(R.id.tv_ans_date);
            tvAnsBody = view.findViewById(R.id.tv_ans_body);
        }

        @Override
        public void bindView(@NonNull AnswerItem item, @NonNull List<Object> payloads) {
            Context ctx = itemView.getContext();
            tvDocName.setText(item.docName);
            tvDocEdu.setText(item.docEdu);
            Date questionDate = TimeUtils.string2Date(item.answerDate,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US));

            long days = TimeUtils.getTimeSpanByNow(questionDate, TimeConstants.DAY);
            String daysAgo = String.format(Locale.US, "%d days ago", Math.abs(days));

            tvAnsDate.setText(daysAgo);

            String answerPart = item.answerBody.substring(0, Math.min(99, item.answerBody.length() - 1));
            
            tvAnsBody.setText(String.format("%s...", answerPart));
        }

        @Override
        public void unbindView(@NonNull AnswerItem item) {
            tvDocName.setText(null);
        }
    }
}
