package com.example.cyclestationsemal.Admin.station;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationsemal.Admin.user.AdminDeleteUser;
import com.example.cyclestationsemal.R;

public class AdminStationHome extends Activity {

    Button bt_AdminInsertStation,bt_AdminUpdateStation,bt_AdminDeleteStation,bt_AdminViewStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_home);

        bt_AdminInsertStation=findViewById(R.id.bt_AdminInsertStation);
        bt_AdminUpdateStation=findViewById(R.id.bt_AdminUpdateStation);
        bt_AdminDeleteStation=findViewById(R.id.bt_AdminDeleteStation);
        bt_AdminViewStation=findViewById(R.id.bt_AdminViewStation);


        bt_AdminInsertStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),InsertNewStation.class);
                startActivity(i1);
            }
        });

        bt_AdminViewStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminStationView.class);
                startActivity(i);
            }
        });

        bt_AdminUpdateStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminUpdateStation.class);
                startActivity(i);
            }
        });

        bt_AdminDeleteStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminDeleteStation.class);
                startActivity(i);
            }
        });
    }
}
