package com.example.cyclestationsemal.Admin.cycle;

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

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminDeleteCycle extends Activity {

    Spinner spCycleDeleteSpinner;
    Button bt_spAdminDeleteCycle;
    String cid="";
    DatabaseReference dblist,dbdel;
    List<Cycle> listCycle = new ArrayList<Cycle>();
    List<String> listId = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_cycle);

        spCycleDeleteSpinner = findViewById(R.id.spCycleDeleteSpinner);
        bt_spAdminDeleteCycle = findViewById(R.id.bt_spAdminDeleteCycle);

        dblist = FirebaseDatabase.getInstance().getReference("cycle");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()){
                    Cycle c1 = shot.getValue(Cycle.class);
                    listCycle.add(c1);
                    listId.add(c1.cid);
                }

                ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,listId);
                spCycleDeleteSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spCycleDeleteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cid = spCycleDeleteSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_spAdminDeleteCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbdel = FirebaseDatabase.getInstance().getReference();

                Query delq = dbdel.child("cycle").orderByChild("cid").equalTo(cid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren()){
                            shot.getRef().removeValue();
                        }

                        Toast.makeText(getApplicationContext(),"CYCLE DELETED SUCCESSFULLY.....",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
