package com.example.cyclestationsemal.Admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationsemal.R;

public class AdminUserHome extends AppCompatActivity {

    Button bt_insert,bt_update,bt_delete,bt_view;
    String mail = "admin@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_home);

        bt_insert=findViewById(R.id.bt_insert);
        bt_update=findViewById(R.id.bt_update);
        bt_delete=findViewById(R.id.bt_delete);
        bt_view=findViewById(R.id.bt_view);

        Intent i1=getIntent();

        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),InsertNewUser.class);
                startActivity(i1);
            }
        });

        bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminUserView.class);
                startActivity(i);
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(getApplicationContext(),AdminUpdateUser.class);
                i5.putExtra("E-Mail",mail);
                Log.i("E-Mail",mail);
                startActivity(i5);
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminDeleteUser.class);
                startActivity(i);
            }
        });
    }
}
