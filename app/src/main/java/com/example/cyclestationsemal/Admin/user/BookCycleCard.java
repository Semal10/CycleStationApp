package com.example.cyclestationsemal.Admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookCycleCard extends Activity {

    TextView tv_bookCycleCardStationName,tv_bookCycleCardOpenTime,tv_bookCycleCardCloseTime,tv_bookCycleCardAvailableCycles;
    Button bt_bookCycleCardBookCycle;
    DatabaseReference dbref;
    Spinner sp_bookCycleCardSpinner;
    List<String> list = new ArrayList<String>();
    String phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cycle_card);

        tv_bookCycleCardStationName = findViewById(R.id.tv_bookCycleCardStationName);
        tv_bookCycleCardOpenTime = findViewById(R.id.tv_bookCycleCardOpenTime);
        tv_bookCycleCardCloseTime = findViewById(R.id.tv_bookCycleCardCloseTime);
        tv_bookCycleCardAvailableCycles = findViewById(R.id.tv_bookCycleCardAvailableCycles);
        bt_bookCycleCardBookCycle = findViewById(R.id.bt_bookCycleCardBookCycle);
        sp_bookCycleCardSpinner = findViewById(R.id.sp_bookCycleCardSpinner);

        dbref = FirebaseDatabase.getInstance().getReference("user");

        Intent i = getIntent();
        final String sid = i.getStringExtra("stid");
        String openTime = i.getStringExtra("open");
        String closeTime = i.getStringExtra("close");
        String availCycles = i.getStringExtra("avail");
        String stationName = i.getStringExtra("name");
        final String mail = i.getStringExtra("E-Mail");
        final String uid = i.getStringExtra("uid");

        //Toast.makeText(getApplicationContext(),"in booke : "+mail,Toast.LENGTH_LONG).show();

        tv_bookCycleCardStationName.setText(stationName);
        tv_bookCycleCardOpenTime.setText(openTime);
        tv_bookCycleCardCloseTime.setText(closeTime);
        tv_bookCycleCardAvailableCycles.setText(availCycles);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()){
                    User u1 = shot.getValue(User.class);
                    if(u1.emailId.equals(mail)){
                        phone = ""+u1.phone;
                        break;
                    }
                }
                Toast.makeText(getApplicationContext(),""+phone,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        for(int j = 1; j <= 6 ; j++){
            String temp = "";
            temp+= (j+" Hour");
            list.add(temp);
        }

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, list);
        sp_bookCycleCardSpinner.setAdapter(ad);

        bt_bookCycleCardBookCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random ra = new Random();
                int otpcode = ra.nextInt(9999);

                String hours = sp_bookCycleCardSpinner.getSelectedItem().toString();
                final String ch = ""+hours.charAt(0);
                //Toast.makeText(getApplicationContext(),""+ch, Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(),""+otpcode+" to : "+phone,Toast.LENGTH_LONG).show();

                phone = phone.substring(2);

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone,null,"YOUR OTP CODE : "+otpcode,null,null);

                Intent i = new Intent(getApplicationContext(),BookCycleOtp.class);
                i.putExtra("otp",""+otpcode);
                i.putExtra("hours",ch);
                i.putExtra("stid",sid);
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });

    }
}
