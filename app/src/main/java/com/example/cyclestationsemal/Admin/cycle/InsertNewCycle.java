package com.example.cyclestationsemal.Admin.cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyclestationsemal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertNewCycle extends Activity {

    EditText et_station,et_status,et_imageURL,et_cycleRegNo;
    Button bt_addCycle,bt_cancelCycle;
    DatabaseReference dbref;
    private static String cid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_cycle);

        et_station=findViewById(R.id.et_station);
        et_status=findViewById(R.id.et_status);
        et_cycleRegNo=findViewById(R.id.et_cycleRegNo);
        et_imageURL=findViewById(R.id.et_imageURL);
        bt_addCycle=findViewById(R.id.bt_addCycle);
        bt_cancelCycle=findViewById(R.id.bt_cancelCycle);

        dbref=FirebaseDatabase.getInstance().getReference("cycle");

        bt_addCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String station=et_station.getText().toString();
                String status=et_status.getText().toString();
                int cycleRegNo=Integer.parseInt(et_cycleRegNo.getText().toString());
                String imageUrl=et_imageURL.getText().toString();

                if(TextUtils.isEmpty(cid)){
                    String id=dbref.push().getKey();
                    Cycle c1=new Cycle(id,station,status,cycleRegNo,imageUrl);
                    dbref.child(id).setValue(c1);
                    Toast.makeText(getApplicationContext(),"CYCLE SUCCESSFULLY ADDED.......",Toast.LENGTH_LONG).show();
                }

                et_station.setText("");
                et_status.setText("");
                et_cycleRegNo.setText("");
                et_imageURL.setText("");

            }
        });

    }
}
