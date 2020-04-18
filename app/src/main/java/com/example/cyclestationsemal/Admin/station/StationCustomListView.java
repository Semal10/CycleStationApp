package com.example.cyclestationsemal.Admin.station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyclestationsemal.Admin.user.UserCustomListView;
import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StationCustomListView extends ArrayAdapter<String> {

    Activity context;
    List<Station> list = new ArrayList<Station>();
    DatabaseReference dbdel;

    StationCustomListView(Activity context , List list){
        super(context,R.layout.activity_station_custom_list_view,list);
        this.context=context;
        this.list=list;
    }

    @Override

    public View getView(final int position , View view , ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.activity_station_custom_list_view,null,false);

        TextView tvAdminStationViewStationName = rowView.findViewById(R.id.tvAdminStationViewStationName);
        TextView tvAdminStationViewDescription = rowView.findViewById(R.id.tvAdminStationViewDescription);
        final TextView tvAdminStationViewConductedBy = rowView.findViewById(R.id.tvAdminStationViewConductedBy);
        Button bt_cancelStation = rowView.findViewById(R.id.bt_cancelStation);

        tvAdminStationViewStationName.setText("Station Name : "+list.get(position).stationName);
        tvAdminStationViewDescription.setText("Description : "+list.get(position).description);
        tvAdminStationViewConductedBy.setText("Conducted By : "+list.get(position).conductedBy);

        bt_cancelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),tvAdminStationViewConductedBy.getText().toString(),Toast.LENGTH_LONG).show();
                dbdel = FirebaseDatabase.getInstance().getReference();

                Query delq = dbdel.child("station").orderByChild("sid").equalTo(list.get(position).sid);
                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren()){

                            shot.getRef().removeValue();
                            list.remove(position);
                            list.clear();
                        }

                        Toast.makeText(getContext(),"USER DELETED SUCCESSFULLY.....",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                StationCustomListView.this.notifyDataSetChanged();

            }
        });

        return rowView;
    }
}
