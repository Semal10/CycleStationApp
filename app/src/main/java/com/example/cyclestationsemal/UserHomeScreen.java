package com.example.cyclestationsemal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyclestationsemal.Admin.user.AdminUpdateUser;
import com.example.cyclestationsemal.R;

public class UserHomeScreen extends Activity {

    Button bt_userViewUpdate,bt_userNearStation,bt_userComplain;//,bt_clearCookie;
    TextView tv_rideInfo,tv_userAmountPaid,tv_userTimeLeft,tv_updUser;
    SharedPreferences shad;
    long secs;
    String hours,paid,sharedEndTime;
    String endTime="#";
    SharedPreferences.Editor edit;
    //boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);

        Intent i = getIntent();
        final String mail = i.getStringExtra("E-Mail");
        Log.i("endTime before",endTime);
        if(!endTime.equals("#")) {
            endTime = i.getStringExtra("endy");
        }
        Log.i("endTime"," : "+endTime);

        //bt_clearCookie = findViewById(R.id.bt_clearCookie);
        bt_userViewUpdate = findViewById(R.id.bt_userViewUpdate);
        bt_userNearStation = findViewById(R.id.bt_userNearStation);
        bt_userComplain = findViewById(R.id.bt_userComplain);
        tv_rideInfo = findViewById(R.id.tv_rideInfo);
        tv_userAmountPaid = findViewById(R.id.tv_userAmountPaid);
        tv_userTimeLeft = findViewById(R.id.tv_userTimeLeft);
        tv_updUser = findViewById(R.id.tv_updUser);

        shad = getSharedPreferences("cookie", MODE_PRIVATE);


        hours = shad.getString("hours","0");
        paid = shad.getString("paid","0");
        sharedEndTime = shad.getString("sharedEndTime","0");
        //flag = shad.getBoolean("flag",true);

        if(hours.equals("0") && paid.equals("0") && sharedEndTime.equals("0")){
            if(sharedEndTime.equals("0")) sharedEndTime=endTime;
        }
        else{
            if(sharedEndTime.equals("0")) sharedEndTime=endTime;

                if(sharedEndTime!=null) {//sharedEndTime="0";
                    tv_userAmountPaid.setText(paid);
                    secs = Long.parseLong(sharedEndTime) - ((System.currentTimeMillis() / 1000) % 86400);
                    Log.i("secs", "" + secs);
                    Log.i("curr", "" + (System.currentTimeMillis() / 1000) % 86400);
                    Log.i("sharedTime", sharedEndTime);

                    //if(flag) {
                        new CountDownTimer(secs * 1000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                millisUntilFinished /= 1000;
                                String format = "" + ((millisUntilFinished / 3600) % 24) + " : " + (((millisUntilFinished % 3600) / 60) % 60) + " : " + (millisUntilFinished % 60);
                                tv_userTimeLeft.setText(format);
                            }

                            public void onFinish() {
                                tv_userTimeLeft.setText("Time's Over!");
                                edit = shad.edit();
                                edit.putBoolean("flag",false);
                                edit.commit();
                                edit.clear();
                            }
                        }.start();
                    //}
                }
        }

        //Toast.makeText(getApplicationContext(),mail,Toast.LENGTH_LONG).show();


        bt_userViewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminUpdateUser.class);
                i.putExtra("E-Mail",mail);
                //tv_updUser.setText("UPDATE USER....");
                startActivity(i);
            }
        });

        bt_userNearStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),StationMapsActivity.class);
                i.putExtra("E-Mail",mail);
                startActivity(i);
            }
        });

        /*bt_clearCookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit =shad.edit();
                edit.clear();
                edit.apply();
                edit.commit();
                tv_userTimeLeft.setText("");
                tv_userAmountPaid.setText("");
                Log.i("clear paid",paid);
                Log.i("clear shared",sharedEndTime);
            }
        });*/

    }

    @Override
    protected void onStop() {
        super.onStop();
        edit =shad.edit();

        //Toast.makeText(getApplicationContext(),""+endTime,Toast.LENGTH_LONG).show();

            edit.putString("hours", ""+(secs));
            edit.putString("paid", "50");
            edit.putString("sharedEndTime",sharedEndTime);
            Log.i("stopped sharedEndTime"," : "+sharedEndTime);
            Log.i("stop","Stopped");
            edit.commit();

    }

    @Override

    protected void onStart(){
        super.onStart();

        //sharedEndTime = shad.getString("sharedEndTime","0");
        hours = shad.getString("hours","0");
        paid = shad.getString("paid","0");
        Log.i("start","Started");
        Log.i("started sharedEndTime"," : "+sharedEndTime);
    }
}
