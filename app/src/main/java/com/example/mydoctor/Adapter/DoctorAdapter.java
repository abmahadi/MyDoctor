package com.example.mydoctor.Adapter;



import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.mydoctor.Activity.DoctorDetailsActivity;
import com.example.mydoctor.Class.Doctor;
import com.example.mydoctor.R;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {

    Context context;
    ArrayList<Doctor> doctorsData;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorsData) {
        this.context = context;
        this.doctorsData = doctorsData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Doctor currentDoctor =  doctorsData.get(position);

        holder.fname.setText(currentDoctor.getFirstName());
        holder.lname.setText(currentDoctor.getLastName());
        holder.degree.setText(currentDoctor.getDegree());
        holder.speclization.setText(currentDoctor.getSpecialization());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DoctorDetailsActivity.class);

                intent.putExtra("doctorId",currentDoctor.getDoctorId());


                intent.putExtra("id",currentDoctor.getDoctorRegistrationNo());
                intent.putExtra("firstName",currentDoctor.getFirstName());
                intent.putExtra("lastName",currentDoctor.getLastName());
                intent.putExtra("specalization",currentDoctor.getSpecialization());
                intent.putExtra("degree",currentDoctor.getDegree());
                intent.putExtra("city",currentDoctor.getCity());
                intent.putExtra("area",currentDoctor.getArea());
                intent.putExtra("street",currentDoctor.getStreet());
                intent.putExtra("startTime",currentDoctor.getStartTime());
                intent.putExtra("endTime",currentDoctor.getEndTime());
                intent.putExtra("specialNotice",currentDoctor.getSpecialNotice());





                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return doctorsData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView fname,lname,degree,speclization;

        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            degree =  itemView.findViewById(R.id.degree);
            speclization =  itemView.findViewById(R.id.specialization);


        }

    }
}
