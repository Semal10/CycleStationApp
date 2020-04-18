package com.example.cyclestationsemal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterNewUser extends Activity {

    EditText et_RegisterUser,et_RegisterEmail,et_RegisterPass,et_RegisterConfpass,et_RegisterGender,et_RegisterAddress,et_RegisterPhone;
    Button bt_add,bt_can;
    String verificarionCode;
    String name,email,pass,confpass,phone,gender,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        et_RegisterUser=findViewById(R.id.et_RegisterUser);
        et_RegisterEmail=findViewById(R.id.et_RegisterEmail);
        et_RegisterPass=findViewById(R.id.et_RegisterPass);
        et_RegisterConfpass=findViewById(R.id.et_RegisterConfpass);
        et_RegisterPhone=findViewById(R.id.et_RegisterPhone);
        et_RegisterGender=findViewById(R.id.et_RegisterGender);
        et_RegisterAddress=findViewById(R.id.et_RegisterAddress);

        bt_add=findViewById(R.id.bt_add);
        bt_can=findViewById(R.id.bt_can);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=et_RegisterUser.getText().toString();
                email=et_RegisterEmail.getText().toString();
                pass=et_RegisterPass.getText().toString();
                confpass=et_RegisterConfpass.getText().toString();
                phone="+91"+et_RegisterPhone.getText().toString();
                gender=et_RegisterGender.getText().toString();
                address=et_RegisterAddress.getText().toString();

                et_RegisterAddress.setText(" ");
                et_RegisterConfpass.setText(" ");
                et_RegisterEmail.setText(" ");
                et_RegisterGender.setText(" ");
                et_RegisterPass.setText(" ");
                et_RegisterPhone.setText(" ");
                et_RegisterUser.setText(" ");

                PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

                mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(getApplicationContext(),"PHONE VERIFIED...",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(),"PHONE VERIFICATION FAILED...",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(String reqcode,PhoneAuthProvider.ForceResendingToken forceResendingToken){
                        super.onCodeSent(reqcode,forceResendingToken);
                        verificarionCode=reqcode;
                        Toast.makeText(getApplicationContext()," CODE : "+verificarionCode,Toast.LENGTH_LONG).show();

                        if(pass.equals(confpass)){
                            Intent i1 = new Intent(getApplicationContext(),Otp.class);
                            i1.putExtra("VeriCode",verificarionCode);
                            i1.putExtra("Username",name);
                            i1.putExtra("E-Mail",email);
                            i1.putExtra("Password",pass);
                            i1.putExtra("Gender",gender);
                            i1.putExtra("Phone",phone);
                            i1.putExtra("Address",address);
                            startActivity(i1);
                        }

                    }
                };

                PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60l, TimeUnit.SECONDS,RegisterNewUser.this,mCallbacks);
            }
        });

        Intent i2=getIntent();
    }
}
