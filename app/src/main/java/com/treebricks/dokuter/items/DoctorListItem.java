package com.treebricks.dokuter.items;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.treebricks.dokuter.R;

import java.util.List;

public class DoctorListItem extends AbstractItem<DoctorListItem, DoctorListItem.ViewHolder> {

    private String doctorName;
    private String nextVisit;
    private String doctorSpeciality;
    private String doctorChamber;
    private String doctorChamberLocation;
    private String doctorExperience;
    private String doctorFee;

    public DoctorListItem(String doctorName, String nextVisit, String doctorSpeciality, String doctorChamber, String doctorChamberLocation, String doctorExperience, String doctorFee) {
        this.doctorName = doctorName;
        this.nextVisit = nextVisit;
        this.doctorSpeciality = doctorSpeciality;
        this.doctorChamber = doctorChamber;
        this.doctorChamberLocation = doctorChamberLocation;
        this.doctorExperience = doctorExperience;
        this.doctorFee = doctorFee;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.doctor_list_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.doctor_card_layout;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<DoctorListItem> {
        TextView tvDoctorName;
        TextView tvNextVisit;
        TextView tvDoctorSpeciality;
        TextView tvDoctorChamber;
        TextView tvDoctorChamberLocation;
        TextView tvDoctorExperience;
        TextView tvDoctorFee;

        ViewHolder(View view) {
            super(view);
            tvDoctorName = view.findViewById(R.id.tv_doctor_name);
            tvNextVisit = view.findViewById(R.id.tv_next_visit);
            tvDoctorSpeciality = view.findViewById(R.id.tv_doctor_speciality);
            tvDoctorChamber = view.findViewById(R.id.tv_doctor_chamber);
            tvDoctorChamberLocation = view.findViewById(R.id.tv_doctor_chamber_location);
            tvDoctorExperience = view.findViewById(R.id.tv_doctor_exp);
            tvDoctorFee = view.findViewById(R.id.tv_doctor_fee);
        }

        @Override
        public void bindView(@NonNull DoctorListItem item, @NonNull List<Object> payloads) {
            Context ctx = itemView.getContext();
            tvDoctorName.setText(item.doctorName);

        }

        @Override
        public void unbindView(@NonNull DoctorListItem item) {
            tvDoctorName.setText(null);
        }
    }
}
