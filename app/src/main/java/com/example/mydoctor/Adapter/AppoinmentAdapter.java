package com.example.mydoctor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydoctor.Activity.AppoinmentDetailsActivity;
import com.example.mydoctor.Class.Appoinment;
import com.example.mydoctor.R;

import java.util.ArrayList;

public class AppoinmentAdapter extends RecyclerView.Adapter<AppoinmentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Appoinment> appoinmentArrayList;

    public AppoinmentAdapter(Context context, ArrayList<Appoinment> appoinmentArrayList) {
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
        final Appoinment currentApoinment =appoinmentArrayList.get(i);

        viewHolder.email.setText(currentApoinment.getUserId());
        viewHolder.mobileNumber.setText(currentApoinment.getMobilenumber());
        viewHolder.date.setText(currentApoinment.getDate());
        viewHolder.time.setText(currentApoinment.getTime());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AppoinmentDetailsActivity.class);

                intent.putExtra("id",currentApoinment.getAppoinmentId());
                intent.putExtra("drId",currentApoinment.getDrId());

                intent.putExtra("email",currentApoinment.getUserId());
                intent.putExtra("mobileNumber",currentApoinment.getMobilenumber());
                intent.putExtra("date",currentApoinment.getDate());
                intent.putExtra("time",currentApoinment.getTime());


                context.startActivity(intent);


            }
        });

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
