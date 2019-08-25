package com.example.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TypesOfDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner typeOfDoctor;
    Spinner citySP, areaSP;
    Button findDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types_of_doctor);

        typeOfDoctor = (Spinner) findViewById(R.id.spinner_typeOfDoctor);
        citySP = (Spinner) findViewById(R.id.spinner_City);
        areaSP = (Spinner) findViewById(R.id.spinner_Area);

        findDoctor = (Button) findViewById(R.id.btn_findDoctor);

        final String doctorsType[] = getResources().getStringArray(R.array.type_of_doctor);
        final String city[] = getResources().getStringArray(R.array.division);

        final String Dhaka[] = getResources().getStringArray(R.array.Dhaka);
        final String Rajshahi[] = getResources().getStringArray(R.array.Rajshahi);
        final String Rangpur[] = getResources().getStringArray(R.array.Rangpur);

        ArrayAdapter adapter_typesOfDoctor = ArrayAdapter.createFromResource(this, R.array.type_of_doctor, android.R.layout.simple_spinner_item);
        typeOfDoctor.setAdapter(adapter_typesOfDoctor);
        typeOfDoctor.setOnItemSelectedListener(this);

//        ArrayAdapter adapter_city = ArrayAdapter.createFromResource(this, R.array.division, android.R.layout.simple_spinner_item);
//        citySP.setAdapter(adapter_city);
//
//        citySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String itemSelect = city[position];
//
//                Toast.makeText(TypesOfDoctor.this, "Select " + itemSelect, Toast.LENGTH_SHORT).show();
//                if (position == 0) {
//                    ArrayAdapter adapter_Dhaka = ArrayAdapter.createFromResource(TypesOfDoctor.this, R.array.Dhaka, android.R.layout.simple_spinner_item);
//                    areaSP.setAdapter(adapter_Dhaka);
//                }
//                if (position == 1) {
//                    ArrayAdapter adapter_Rajshahi = ArrayAdapter.createFromResource(TypesOfDoctor.this, R.array.Rajshahi, android.R.layout.simple_spinner_item);
//                    areaSP.setAdapter(adapter_Rajshahi);
//                }
//                if (position == 2) {
//                    ArrayAdapter adapter_Rangpur = ArrayAdapter.createFromResource(TypesOfDoctor.this, R.array.Rangpur, android.R.layout.simple_spinner_item);
//                    areaSP.setAdapter(adapter_Rangpur);
//                }
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TypesOfDoctor.this, "Doctor Finding", Toast.LENGTH_SHORT).show();
                openRecycler();


            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "You Selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openRecycler() {
        Intent intent = new Intent(this, Recycler.class);
        startActivity(intent);
    }
}
