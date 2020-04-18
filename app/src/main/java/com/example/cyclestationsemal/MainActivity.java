package com.example.cyclestationsemal;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationsemal.Admin.AdminHome;
import com.example.cyclestationsemal.Admin.user.AdminUserHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {

    Button bt_login,bt_regis;
    EditText et_email,et_pass;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email=findViewById(R.id.et_email);
        et_pass=findViewById(R.id.et_pass);
        bt_login=findViewById(R.id.bt_login);
        bt_regis=findViewById(R.id.bt_Register);

        fAuth=FirebaseAuth.getInstance();

        bt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String mail=et_email.getText().toString();
                String pass=et_pass.getText().toString();

                et_email.setText("");
                et_pass.setText("");

                fAuth.createUserWithEmailAndPassword(mail,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"USER REGISTERED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"USER REGISTRATION FAILED...",Toast.LENGTH_LONG).show();
                                }
                            }
                        });*/

                Intent i2=new Intent(getApplicationContext(),RegisterNewUser.class);
                startActivity(i2);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail=et_email.getText().toString();
                final String pass=et_pass.getText().toString();

                et_email.setText("");
                et_pass.setText("");

                if(mail.equals("admin@gmail.com") && pass.equals("admin123")){
                    Intent ii=new Intent(getApplicationContext(), AdminHome.class);
                    startActivity(ii);
                }

                else {
                    fAuth.signInWithEmailAndPassword(mail, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "WELCOME AUTHORISED USER", Toast.LENGTH_LONG).show();

                                        Intent i =new Intent(getApplicationContext(),UserHomeScreen.class);
                                        i.putExtra("E-Mail",mail);
                                        Log.i("E-Mail",mail);
                                        //i.putExtra("Password",pass);
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "LOGIN FAILED...", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }

            }
        });

    }

}
