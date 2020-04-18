package com.example.cyclestationsemal.Admin.station;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertNewStation extends Activity {

    EditText et_stationName,et_latitude,et_longitude,et_description,et_openingTime,et_closingTime,et_conductedBy,et_noOfCycle,et_availableCycle;
    Button bt_addStation,bt_cancelStation;
    DatabaseReference dbref;
    private static String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_station);

        et_stationName=findViewById(R.id.et_stationName);
        et_latitude=findViewById(R.id.et_latitude);
        et_longitude=findViewById(R.id.et_longitude);
        et_description=findViewById(R.id.et_description);
        et_openingTime=findViewById(R.id.et_openingTime);
        et_closingTime=findViewById(R.id.et_closingTime);
        et_conductedBy=findViewById(R.id.et_conductedBy);
        et_noOfCycle=findViewById(R.id.et_noOfCycle);
        et_availableCycle=findViewById(R.id.et_availableCycle);
        bt_addStation=findViewById(R.id.bt_addStation);
        bt_cancelStation=findViewById(R.id.bt_cancelStation);

        dbref= FirebaseDatabase.getInstance().getReference("station");

        bt_addStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stationName=et_stationName.getText().toString();
                double latitude=Double.parseDouble(et_latitude.getText().toString());
                double longitude=Double.parseDouble(et_longitude.getText().toString());
                String description=et_description.getText().toString();
                String openingTime=et_openingTime.getText().toString();
                String closingTime=et_closingTime.getText().toString();
                String conductedBy=et_conductedBy.getText().toString();
                int noOfCycle=Integer.parseInt(et_noOfCycle.getText().toString());
                int availableCycle=Integer.parseInt(et_availableCycle.getText().toString());

                if(TextUtils.isEmpty(sid)){
                    String id = dbref.push().getKey();
                    Station s1 = new Station(id,stationName,latitude,longitude,description,openingTime,closingTime,conductedBy,noOfCycle,availableCycle);
                    dbref.child(id).setValue(s1);
                    Toast.makeText(getApplicationContext(),"STATION SUCCESSFULLY ADDED......",Toast.LENGTH_LONG).show();
                }

                et_stationName.setText("");
                et_availableCycle.setText("");
                et_closingTime.setText("");
                et_openingTime.setText("");
                et_noOfCycle.setText("");
                et_conductedBy.setText("");
                et_description.setText("");
                et_latitude.setText("");
                et_longitude.setText("");
            }
        });

    }
}
