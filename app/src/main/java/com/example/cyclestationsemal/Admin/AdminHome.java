package com.example.cyclestationsemal.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationsemal.Admin.cycle.AdminCycleHome;
import com.example.cyclestationsemal.Admin.station.AdminStationHome;
import com.example.cyclestationsemal.Admin.user.AdminUserHome;
import com.example.cyclestationsemal.R;

public class AdminHome extends AppCompatActivity {

    Button btstation,btcycle,btuser,btcomplaint,btfeedback,btpayhis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btstation=findViewById(R.id.btstation);
        btcycle=findViewById(R.id.btcycle);
        btuser=findViewById(R.id.btuser);
        btcomplaint=findViewById(R.id.btcomplaint);
        btfeedback=findViewById(R.id.btfeedback);
        btpayhis=findViewById(R.id.btpayhis);

        btuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(), AdminUserHome.class);
                startActivity(i1);
            }
        });

        btcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(), AdminCycleHome.class);
                startActivity(ii);
            }
        });

        btstation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AdminStationHome.class);
                startActivity(i);
            }
        });
    }
}
