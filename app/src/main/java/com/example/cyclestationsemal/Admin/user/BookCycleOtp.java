package com.example.cyclestationsemal.Admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationsemal.R;

public class BookCycleOtp extends Activity {

    EditText et_bookCycleOtp;
    Button bt_bookCycleSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cycle_otp);

        et_bookCycleOtp = findViewById(R.id.et_bookCycleOtp);
        bt_bookCycleSendOtp = findViewById(R.id.bt_bookCycleSendOtp);

        Intent i =getIntent();
        final String ch = i.getStringExtra("hours");
        final String otpcode = i.getStringExtra("otp");
        final String sid = i.getStringExtra("stid");
        final String uid =i.getStringExtra("uid");

        bt_bookCycleSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpcode.equals(et_bookCycleOtp.getText().toString())){
                    Toast.makeText(getApplicationContext(),"CYCLE BOOKED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),BookCyclePayment.class);
                    i.putExtra("hours",ch);
                    i.putExtra("stid",sid);
                    i.putExtra("uid",uid);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"INVALID OTP",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
