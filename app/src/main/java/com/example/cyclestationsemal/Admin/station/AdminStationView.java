package com.example.cyclestationsemal.Admin.station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminStationView extends Activity {

    DatabaseReference dbref;
    ListView lvAdminStationView;
    String data="";
    List<Station> list2 = new ArrayList<Station>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_view);

        lvAdminStationView=findViewById(R.id.lvAdminStationView);

        dbref = FirebaseDatabase.getInstance().getReference("station");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()){
                    Station s1 = shot.getValue(Station.class);
                    //data = data + s1.stationName + "," + s1.description + "," + s1.conductedBy + "\n";
                    list2.add(s1);
                }

                //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();

                //ArrayAdapter ad= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list2);

                StationCustomListView scl = new StationCustomListView(AdminStationView.this ,  list2);
                lvAdminStationView.setAdapter(scl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
