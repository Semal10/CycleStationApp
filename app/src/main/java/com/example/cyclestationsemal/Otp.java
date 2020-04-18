package com.example.cyclestationsemal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationsemal.Admin.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Otp extends Activity {

    EditText et_otp;
    Button bt_sendOtp;
    FirebaseAuth fAuth;
    DatabaseReference dbref;

    private static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        et_otp=findViewById(R.id.et_otp);
        bt_sendOtp=findViewById(R.id.bt_sendOtp);

        fAuth=FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("user");

        Intent i = getIntent();
        final String vericode=i.getStringExtra("VeriCode");
        final String name=i.getStringExtra("Username");
        final String email=i.getStringExtra("E-Mail");
        final String pass=i.getStringExtra("Password");
        final String gender=i.getStringExtra("Gender");
        final long phone=Long.parseLong(i.getStringExtra("Phone"));
        final String address=i.getStringExtra("Address");

        bt_sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp=et_otp.getText().toString();
                et_otp.setText("");

                PhoneAuthCredential credential= PhoneAuthProvider.getCredential(vericode,otp);

                fAuth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Toast.makeText(getApplicationContext(),"OTP REGISTERED...",Toast.LENGTH_LONG).show();

                                    fAuth.createUserWithEmailAndPassword(email,pass)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(getApplicationContext(),"USER AUTHENTICATION SUCCESSFULLY",Toast.LENGTH_LONG).show();
                                                    }
                                                    else{
                                                        Toast.makeText(getApplicationContext(),"USER AUTHENTICATION FAILED...",Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });

                                    String id=dbref.push().getKey();
                                    User u1=new User(id,name,email,phone,gender,address,pass);
                                    dbref.child(id).setValue(u1);
                                    Toast.makeText(getApplicationContext(),"USER SUCCESSFULLY ADDED....",Toast.LENGTH_LONG).show();


                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"INVALID OTP...",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}
