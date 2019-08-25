package com.example.mydoctor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AppoimentAdapter extends RecyclerView.Adapter<AppoimentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Appoinment> appoinmentArrayList;

    public AppoimentAdapter(Context context, ArrayList<Appoinment> appoinmentArrayList) {
        this.context = context;
        this.appoinmentArrayList = appoinmentArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_appoinment_details,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Appoinment currentApoinment =appoinmentArrayList.get(i);

        viewHolder.email.setText(currentApoinment.getUserId());
        viewHolder.mobileNumber.setText(currentApoinment.getMobilenumber());
        viewHolder.date.setText(currentApoinment.getDate());
        viewHolder.time.setText(currentApoinment.getTime());

    }

    @Override
    public int getItemCount() {
        return appoinmentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView email,mobileNumber,date,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.patientEmailTV_iad);
            mobileNumber = itemView.findViewById(R.id.mobilenumberPatient_iad);
            date = itemView.findViewById(R.id.dateTV_iad);
            time = itemView.findViewById(R.id.timeTV_iad);
        }
    }
}
