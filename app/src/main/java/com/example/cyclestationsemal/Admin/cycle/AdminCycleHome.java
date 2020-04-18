package com.example.cyclestationsemal.Admin.cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyclestationsemal.Admin.user.AdminUpdateUser;
import com.example.cyclestationsemal.R;

public class AdminCycleHome extends Activity {

    Button bt_AdminInsertCycle,bt_AdminUpdateCycle,bt_AdminDeleteCycle,bt_AdminViewCycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_home);

        bt_AdminInsertCycle=findViewById(R.id.bt_AdminInsertCycle);
        bt_AdminUpdateCycle=findViewById(R.id.bt_AdminUpdateCycle);
        bt_AdminDeleteCycle=findViewById(R.id.bt_AdminDeleteCycle);
        bt_AdminViewCycle=findViewById(R.id.bt_AdminViewCycle);


        bt_AdminInsertCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),InsertNewCycle.class);
                startActivity(i1);
            }
        });

        bt_AdminViewCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminCycleView.class);
                startActivity(i);
            }
        });

        bt_AdminUpdateCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), AdminUpdateCycle.class);
                startActivity(ii);
            }
        });

        bt_AdminDeleteCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminDeleteCycle.class);
                startActivity(i);
            }
        });
    }
}
