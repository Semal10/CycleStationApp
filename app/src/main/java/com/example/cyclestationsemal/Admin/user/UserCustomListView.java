package com.example.cyclestationsemal.Admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.view.View.*;

public class UserCustomListView extends ArrayAdapter<String> {

    Activity context;
    List<User> list = new ArrayList<User>();
    List<UserCustomListView> lista = new ArrayList<UserCustomListView>();
    DatabaseReference dbdel;
    String sid="";

    UserCustomListView(Activity context , List list){
        super(context,R.layout.activity_user_custom_list_view,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public View getView(final int position , View view , ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        final View rowView = inflater.inflate(R.layout.activity_user_custom_list_view,null,false);

        sid = list.get(position).uid;
        TextView tvName = rowView.findViewById(R.id.tvAdminUserViewName);
        Button bt_cancelUser = rowView.findViewById(R.id.bt_cancelUser);
        final TextView tvEmail = rowView.findViewById(R.id.tvAdminUserViewMail);
        TextView tvPhone = rowView.findViewById(R.id.tvAdminUserViewPhone);
        TextView tvRoll = rowView.findViewById(R.id.tvAdminUserViewRole);
        final String st=list.get(position).emailId;
        tvName.setText("Name : "+list.get(position).name);
        tvEmail.setText("E-Mail : "+list.get(position).emailId);
        tvPhone.setText("Phone Number : "+list.get(position).phone);
        tvRoll.setText("Password : "+list.get(position).password);

        bt_cancelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),tvEmail.getText().toString(),Toast.LENGTH_LONG).show();
                // OR list.get(postition).emailId;
                dbdel = FirebaseDatabase.getInstance().getReference();

                //Query delq = dbdel.child("user").orderByChild("emailId").equalTo(st);
                Query delq = dbdel.child("user").orderByChild("uid").equalTo(list.get(position).uid);
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

                UserCustomListView.this.notifyDataSetChanged();

            }
        });


        return rowView;
    };


}
