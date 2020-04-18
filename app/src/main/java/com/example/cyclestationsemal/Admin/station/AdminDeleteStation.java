package com.example.cyclestationsemal.Admin.station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyclestationsemal.Admin.user.User;
import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminDeleteStation extends Activity {

    Button bt_spAdminDeleteStation;
    Spinner spStationDeleteSpinner;
    DatabaseReference dblist,dbdel;
    List<Station> listStation = new ArrayList<Station>();
    List<String> listId = new ArrayList<String>();
    String sid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_station);

        bt_spAdminDeleteStation = findViewById(R.id.bt_spAdminDeleteStation);
        spStationDeleteSpinner = findViewById(R.id.spStationDeleteSpinner);

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
                spStationDeleteSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spStationDeleteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sid = spStationDeleteSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_spAdminDeleteStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbdel = FirebaseDatabase.getInstance().getReference();

                Query delq = dbdel.child("station").orderByChild("sid").equalTo(sid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren()){

                            shot.getRef().removeValue();

                        }

                        Toast.makeText(getApplicationContext(),"STATION DELETED SUCCESSFULLY.....",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
