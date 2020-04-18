package com.example.cyclestationsemal.Admin.station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminUpdateStation extends AppCompatActivity {

    EditText et_StationUpdateStationName,et_StationUpdateLatitude,et_StationUpdateLongitude,et_StationUpdateDescription,et_StationUpdateOpeningTime,et_StationUpdateClosingTime,et_StationUpdateConductedBy,et_StationUpdateNoOfCycle,et_StationUpdateAvailableCycle;
    Button bt_UpdateStation,bt_UpdateCancelStation;
    Spinner spStationUpdateSpinner;
    DatabaseReference dbref,dbupd,dblist;
    List<Station> listStation= new ArrayList<Station>();
    List<String> listId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_station);

        et_StationUpdateStationName=findViewById(R.id.et_StationUpdateStationName);
        et_StationUpdateLatitude=findViewById(R.id.et_StationUpdateLatitude);
        et_StationUpdateLongitude=findViewById(R.id.et_StationUpdateLongitude);
        et_StationUpdateDescription=findViewById(R.id.et_StationUpdateDescription);
        et_StationUpdateOpeningTime=findViewById(R.id.et_StationUpdateOpeningTime);
        et_StationUpdateClosingTime=findViewById(R.id.et_StationUpdateClosingTime);
        et_StationUpdateConductedBy=findViewById(R.id.et_StationUpdateConductedBy);
        et_StationUpdateNoOfCycle=findViewById(R.id.et_StationUpdateNoOfCycle);
        et_StationUpdateAvailableCycle=findViewById(R.id.et_StationUpdateAvailableCycle);
        bt_UpdateStation=findViewById(R.id.bt_UpdateStation);
        spStationUpdateSpinner = findViewById(R.id.spStationUpdateSpinner);
        bt_UpdateCancelStation=findViewById(R.id.bt_UpdateCancelStation);

        dblist = FirebaseDatabase.getInstance().getReference("station");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()){
                    Station s1 = shot.getValue(Station.class);
                    listId.add(s1.sid);
                    listStation.add(s1);
                }

                ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listId);
                spStationUpdateSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spStationUpdateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_StationUpdateStationName.setText(listStation.get(position).stationName);
                et_StationUpdateLatitude.setText(""+listStation.get(position).latitude);
                et_StationUpdateLongitude.setText(""+listStation.get(position).longitude);
                et_StationUpdateDescription.setText(listStation.get(position).description);
                et_StationUpdateOpeningTime.setText(listStation.get(position).openingTime);
                et_StationUpdateClosingTime.setText(listStation.get(position).closingTime);
                et_StationUpdateConductedBy.setText(listStation.get(position).conductedBy);
                et_StationUpdateNoOfCycle.setText(""+listStation.get(position).noOfCycle);
                et_StationUpdateAvailableCycle.setText(""+listStation.get(position).availableCycle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bt_UpdateStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = spStationUpdateSpinner.getSelectedItem().toString();
                final String stationName = et_StationUpdateStationName.getText().toString();
                final double latitude = Double.parseDouble(et_StationUpdateLatitude.getText().toString());
                final double longitude = Double.parseDouble(et_StationUpdateLongitude.getText().toString());
                final String description = et_StationUpdateDescription.getText().toString();
                final String openingTime = et_StationUpdateOpeningTime.getText().toString();
                final String closingTime = et_StationUpdateClosingTime.getText().toString();
                final String conductedBy = et_StationUpdateConductedBy.getText().toString();
                final int noOfCycle = Integer.parseInt(et_StationUpdateNoOfCycle.getText().toString());
                final int availableCycle = Integer.parseInt(et_StationUpdateAvailableCycle.getText().toString());

                dbref = FirebaseDatabase.getInstance().getReference("station");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot shot : dataSnapshot.getChildren()) {

                            Station s2 = shot.getValue(Station.class);
                            if (s2.sid.equals(id)) {

                                dbupd = FirebaseDatabase.getInstance().getReference("station").child(id);
                                Station st = new Station(id, stationName, latitude, longitude, description, openingTime, closingTime, conductedBy, noOfCycle, availableCycle);
                                dbupd.setValue(st);
                                break;
                            }

                        }

                        Toast.makeText(getApplicationContext(),"STATION UPDATED SUCCESSFULLY......",Toast.LENGTH_LONG).show();

                        et_StationUpdateAvailableCycle.setText(" ");
                        et_StationUpdateClosingTime.setText(" ");
                        et_StationUpdateConductedBy.setText(" ");
                        et_StationUpdateLatitude.setText(" ");
                        et_StationUpdateLongitude.setText(" ");
                        et_StationUpdateNoOfCycle.setText(" ");
                        et_StationUpdateOpeningTime.setText(" ");
                        et_StationUpdateDescription.setText(" ");
                        et_StationUpdateStationName.setText(" ");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });

    }

    }