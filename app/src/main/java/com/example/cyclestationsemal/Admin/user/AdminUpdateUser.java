package com.example.cyclestationsemal.Admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class AdminUpdateUser extends Activity {

    EditText et_spUserUpdateUsername,et_spUserUpdateEmail,et_spUserUpdatePhone,et_spUserUpdateGender,et_spUserUpdateAddress,et_spUserUpdatePassword;
    Button bt_UserUpdateAddUser,bt_UserUpdateCancel;
    Spinner spUserUpdateSpinner;
    DatabaseReference dblist , dbref , dbupd;
    List<String> listid = new ArrayList<String>();
    List<User> listUser = new ArrayList<User>();
    String id1="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_user);


        final Intent ii = getIntent();
        final String mail = ii.getStringExtra("E-Mail");
        Log.i("E-Mail",mail);

        spUserUpdateSpinner = findViewById(R.id.spUserUpdateSpinner);
        et_spUserUpdateUsername = findViewById(R.id.et_spUserUpdateUsername);
        et_spUserUpdateEmail = findViewById(R.id.et_spUserUpdateEmail);
        et_spUserUpdatePhone = findViewById(R.id.et_spUserUpdatePhone);
        et_spUserUpdateAddress = findViewById(R.id.et_spUserUpdateAddress);
        et_spUserUpdateGender = findViewById(R.id.et_spUserUpdateGender);
        et_spUserUpdatePassword = findViewById(R.id.et_spUserUpdatePassword);
        bt_UserUpdateAddUser = findViewById(R.id.bt_UserUpdateUser);
        bt_UserUpdateCancel = findViewById(R.id.bt_UserUpdateCancel);

        if(!(mail.equals("admin@gmail.com"))){

            dblist = FirebaseDatabase.getInstance().getReference("user");

            dblist.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot shot : dataSnapshot.getChildren()){

                        User u3 = shot.getValue(User.class);
                        if(u3.emailId.equals(mail)){
                            id1 = u3.uid;
                            et_spUserUpdateUsername.setText(u3.name);
                            et_spUserUpdateEmail.setText(u3.emailId);
                            et_spUserUpdatePhone.setText(""+u3.phone);
                            et_spUserUpdateAddress.setText(u3.address);
                            et_spUserUpdateGender.setText(u3.gender);
                            et_spUserUpdatePassword.setText(u3.password);
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            bt_UserUpdateAddUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String name = et_spUserUpdateUsername.getText().toString();
                    final String email = et_spUserUpdateEmail.getText().toString();
                    final Long phone = Long.parseLong(et_spUserUpdatePhone.getText().toString());
                    final String address = et_spUserUpdateAddress.getText().toString();
                    final String gender = et_spUserUpdateGender.getText().toString();
                    final String password = et_spUserUpdatePassword.getText().toString();

                    dbref = FirebaseDatabase.getInstance().getReference("user");

                    dbref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot shot : dataSnapshot.getChildren()) {

                                User u2 = shot.getValue(User.class);

                                if (u2.uid.equals(id1)) {
                                    dbupd = FirebaseDatabase.getInstance().getReference("user").child(id1);
                                    User us = new User(id1, name, email, phone, gender, address, password);
                                    dbupd.setValue(us);
                                    break;
                                }

                            }

                            Toast.makeText(getApplicationContext(), "USER UPDATED SUCCESSFULLY......", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            });
        }

        else{

        dblist = FirebaseDatabase.getInstance().getReference("user");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    User u1 = shot.getValue(User.class);
                    listid.add(u1.uid);
                    listUser.add(u1);
                }

                ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, listid);
                spUserUpdateSpinner.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        spUserUpdateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_spUserUpdateUsername.setText(listUser.get(position).getName());
                et_spUserUpdateEmail.setText(listUser.get(position).getEmailId());
                et_spUserUpdatePhone.setText("" + listUser.get(position).getPhone());
                et_spUserUpdateAddress.setText(listUser.get(position).getAddress());
                et_spUserUpdateGender.setText(listUser.get(position).getGender());
                et_spUserUpdatePassword.setText(listUser.get(position).getPassword());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_UserUpdateAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = spUserUpdateSpinner.getSelectedItem().toString();
                final String name = et_spUserUpdateUsername.getText().toString();
                final String email = et_spUserUpdateEmail.getText().toString();
                final Long phone = Long.parseLong(et_spUserUpdatePhone.getText().toString());
                final String address = et_spUserUpdateAddress.getText().toString();
                final String gender = et_spUserUpdateGender.getText().toString();
                final String password = et_spUserUpdatePassword.getText().toString();

                dbref = FirebaseDatabase.getInstance().getReference("user");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot shot : dataSnapshot.getChildren()) {

                            User u2 = shot.getValue(User.class);

                            if (u2.uid.equals(id)) {
                                dbupd = FirebaseDatabase.getInstance().getReference("user").child(id);
                                User us = new User(id, name, email, phone, gender, address, password);
                                dbupd.setValue(us);
                                break;
                            }

                        }

                        Toast.makeText(getApplicationContext(), "USER UPDATED SUCCESSFULLY......", Toast.LENGTH_LONG).show();

                        et_spUserUpdateUsername.setText(" ");
                        et_spUserUpdateEmail.setText(" ");
                        et_spUserUpdateAddress.setText(" ");
                        et_spUserUpdateGender.setText(" ");
                        et_spUserUpdatePhone.setText(" ");
                        et_spUserUpdatePassword.setText(" ");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    }
}
