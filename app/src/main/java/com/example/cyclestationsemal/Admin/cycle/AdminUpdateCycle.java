package com.example.cyclestationsemal.Admin.cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class AdminUpdateCycle extends Activity {

    EditText et_spCycleUpdateStation,et_spCycleUpdateStatus,et_spCycleUpdateCycleRegNo,et_spCycleUpdateImageURL;
    Button bt_spCycleUpdateCycle,bt_spCycleUpdateCancelCycle;
    Spinner spCycleUpdateSpinner;
    DatabaseReference dbref,dbupd,dblist;
    List<String> listid = new ArrayList<String>();
    List<Cycle> listCycle = new ArrayList<Cycle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_cycle);

        et_spCycleUpdateStation = findViewById(R.id.et_spCycleUpdateStation);
        et_spCycleUpdateStatus = findViewById(R.id.et_spCycleUpdateStatus);
        et_spCycleUpdateCycleRegNo = findViewById(R.id.et_spCycleUpdateCycleRegNo);
        et_spCycleUpdateImageURL = findViewById(R.id.et_spCycleUpdateImageURL);
        spCycleUpdateSpinner = findViewById(R.id.spCycleUpdateSpinner);
        bt_spCycleUpdateCycle = findViewById(R.id.bt_spUpdateCycle);
        bt_spCycleUpdateCancelCycle = findViewById(R.id.bt_spCycleUpdateCancelCycle);

        dblist = FirebaseDatabase.getInstance().getReference("cycle");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()){
                    Cycle c1 = shot.getValue(Cycle.class);
                    listid.add(c1.cid);
                    listCycle.add(c1);
                }

                ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listid);
                spCycleUpdateSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spCycleUpdateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_spCycleUpdateStation.setText(listCycle.get(position).station);
                et_spCycleUpdateStatus.setText(listCycle.get(position).status);
                et_spCycleUpdateCycleRegNo.setText(""+listCycle.get(position).cycleRegNo);
                et_spCycleUpdateImageURL.setText(listCycle.get(position).imageUrl);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_spCycleUpdateCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id = spCycleUpdateSpinner.getSelectedItem().toString();
                final String station = et_spCycleUpdateStation.getText().toString();
                final String status = et_spCycleUpdateStatus.getText().toString();
                final int cycleRegNo = Integer.parseInt(et_spCycleUpdateCycleRegNo.getText().toString());
                final String imageURL = et_spCycleUpdateImageURL.getText().toString();

                dbref = FirebaseDatabase.getInstance().getReference("cycle");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren()){
                            Cycle c2 = shot.getValue(Cycle.class);
                            if(c2.cid.equals(id)){
                                dbupd = FirebaseDatabase.getInstance().getReference("cycle").child(id);
                                Cycle cy = new Cycle(id,station,status,cycleRegNo,imageURL);
                                dbupd.setValue(cy);
                                break;
                            }
                        }

                        Toast.makeText(getApplicationContext(),"CYCLE UPDATED SUCCESSFULLY......",Toast.LENGTH_LONG).show();

                        et_spCycleUpdateCycleRegNo.setText(" ");
                        et_spCycleUpdateImageURL.setText(" ");
                        et_spCycleUpdateStation.setText(" ");
                        et_spCycleUpdateStatus.setText(" ");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
