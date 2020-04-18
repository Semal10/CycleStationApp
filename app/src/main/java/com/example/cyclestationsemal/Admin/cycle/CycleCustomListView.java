package com.example.cyclestationsemal.Admin.cycle;

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

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CycleCustomListView extends ArrayAdapter<String> {

    Activity context;
    List<Cycle> list = new ArrayList<Cycle>();
    DatabaseReference dbdel;

    CycleCustomListView(Activity context, List list){
        super(context,R.layout.activity_cycle_custom_list_view,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_cycle_custom_list_view,null,false);

        TextView tvStation = rowView.findViewById(R.id.tvAdminCycleViewStation);
        TextView tvStatus = rowView.findViewById(R.id.tvAdminCycleViewStatus);
        final TextView tvCycleRegNo = rowView.findViewById(R.id.tvAdminCycleViewCycleRegNo);
        Button bt_cancelCycle = rowView.findViewById(R.id.bt_cancelCycle);

        tvStation.setText("Station : "+list.get(position).station);
        tvStatus.setText("Status : "+list.get(position).status);
        tvCycleRegNo.setText("Cycle Reg No : "+list.get(position).cycleRegNo);

        bt_cancelCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),tvCycleRegNo.getText().toString(),Toast.LENGTH_LONG).show();
                dbdel = FirebaseDatabase.getInstance().getReference();

                Query delq = dbdel.child("cycle").orderByChild("cid").equalTo(list.get(position).cid);
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

                CycleCustomListView.this.notifyDataSetChanged();

            }
        });

        return rowView;
    }
}
