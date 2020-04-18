package com.example.cyclestationsemal.Admin.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertNewUser extends Activity {

    EditText et_AdminInsertUsername,et_AdminInsertEmail,et_AdminInsertPhone,et_AdminInsertAddress,et_AdminInsertPassword,et_AdminInsertGender;
    Button  bt_addUser,bt_cancel;
    DatabaseReference dbref;
    FirebaseAuth fAuth;
    private static String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_user);

        et_AdminInsertUsername=findViewById(R.id.et_AdminInsertUsername);
        et_AdminInsertEmail=findViewById(R.id.et_AdminInsertEmail);
        et_AdminInsertPhone=findViewById(R.id.et_AdminInsertPhone);
        et_AdminInsertAddress=findViewById(R.id.et_AdminInsertAddress);
        et_AdminInsertGender=findViewById(R.id.et_AdminInsertGender);
        et_AdminInsertPassword=findViewById(R.id.et_AdminInsertPassword);
        bt_addUser=findViewById(R.id.bt_addUser);
        bt_cancel=findViewById(R.id.bt_cancel);

        dbref= FirebaseDatabase.getInstance().getReference("user");

        bt_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fAuth = FirebaseAuth.getInstance();

                String name=et_AdminInsertUsername.getText().toString();
                String email=et_AdminInsertEmail.getText().toString();
                long phone=Long.parseLong(et_AdminInsertPhone.getText().toString());
                String gender=et_AdminInsertGender.getText().toString();
                String address=et_AdminInsertAddress.getText().toString();
                String password=et_AdminInsertPassword.getText().toString();

                if(TextUtils.isEmpty(uid)){

                    //String mail=et_AdminInsertEmail.getText().toString();
                    //String pass=et_AdminInsertPassword.getText().toString();

                    fAuth.createUserWithEmailAndPassword(email,password)
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
                    User u1=new User(id,name,email,phone,gender,address,password);
                    dbref.child(id).setValue(u1);
                    Toast.makeText(getApplicationContext(),"USER SUCCESSFULLY ADDED....",Toast.LENGTH_LONG).show();
                }

                et_AdminInsertUsername.setText("");
                et_AdminInsertEmail.setText("");
                et_AdminInsertPhone.setText("");
                et_AdminInsertAddress.setText("");
                et_AdminInsertGender.setText("");
                et_AdminInsertPassword.setText("");
            }
        });

    }
}
