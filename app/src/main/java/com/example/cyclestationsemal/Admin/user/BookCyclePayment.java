package com.example.cyclestationsemal.Admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.example.cyclestationsemal.UserHomeScreen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookCyclePayment extends Activity {

    TextView tv_bookCycleTotalHour,tv_bookCycleTotalPayment;
    Button bt_bookCycleCardBookPay;
    DatabaseReference dbref,dbpay;
    long endy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cycle_payment);

        final Intent i = getIntent();
        final String hours = i.getStringExtra("hours");
        final String sid = i.getStringExtra("stid");
        final String uid =i.getStringExtra("uid");

        tv_bookCycleTotalHour = findViewById(R.id.tv_bookCycleTotalHours);
        tv_bookCycleTotalPayment = findViewById(R.id.tv_bookCycleTotalPayment);
        bt_bookCycleCardBookPay = findViewById(R.id.bt_bookCycleCardBookPay);

        tv_bookCycleTotalHour.setText(hours);
        int hr = Integer.parseInt(hours)*100;
        final String hrs = ""+hr;
        tv_bookCycleTotalPayment.setText(hrs);

        bt_bookCycleCardBookPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");;
                int duration = Integer.parseInt(hours);
                long tim = (System.currentTimeMillis()/1000);
                endy = (((tim/3600)%24)*3600 + (((tim)/60)%60)*60 +(((tim)%60)))+(duration*3600);
                String time = ""+((tim/3600)%24)+" : "+(((tim)/60)%60)+" : "+(((tim)%60));
                String stationId = sid;
                String userId = uid;
                String startTime = time;
                String endTime = ""+((((tim/3600)%24)+duration)%24)+" : "+(((tim)/60)%60)+" : "+(((tim)%60));
                String date = ""+cal.get(Calendar.DATE)+" : "+(cal.get(Calendar.MONTH)+1)+" : "+cal.get(Calendar.YEAR);
                //Toast.makeText(getApplicationContext(),"Station ID : "+stationId+"\n User ID : "+userId+"\n Start Time : "+startTime+" UTC\n End Time : "+endTime+"\n UTC Duration : "+duration+"\n Date : "+date,Toast.LENGTH_LONG).show();

                dbref = FirebaseDatabase.getInstance().getReference("bookCycle");

                String id = dbref.push().getKey();
                BookCycle bc = new BookCycle(id,stationId,userId,startTime+" UTC",endTime+" UTC",duration,date);
                dbref.child(id).setValue(bc);

                dbpay = FirebaseDatabase.getInstance().getReference("payment");

                String pid = dbpay.push().getKey();
                Payment p = new Payment(pid,id,50,Integer.parseInt(hrs));
                dbpay.child(pid).setValue(p);

                //Toast.makeText(getApplicationContext(),"PAYMENT SUCCESSFUL",Toast.LENGTH_LONG).show();
                Log.i("endy",""+endy);
                Intent i1 = new Intent(getApplicationContext(), UserHomeScreen.class);
                String paid = "50";
                i1.putExtra("paid",""+paid);
                i1.putExtra("hours",""+hours);
                i1.putExtra("endy",""+endy);
                startActivity(i1);

            }
        });
    }
}
