package com.demo.storageinandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

import com.demo.storageinandroid.storage.StoreSharePref;

public class StoreInSPActivity extends AppCompatActivity {
    EditText ed_username, ed_user_number, ed_user_age, ed_user_email, ed_user_address;
    TextView tv_savedData;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_in_s_p);
        init();
    }

    private void init() {
        ed_user_address = (EditText) findViewById(R.id.ed_user_address);
        ed_user_age = (EditText) findViewById(R.id.ed_user_age);
        ed_user_email = (EditText) findViewById(R.id.ed_user_email);
        ed_user_number = (EditText) findViewById(R.id.ed_user_number);
        ed_username = (EditText) findViewById(R.id.ed_user_name);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        tv_savedData = (TextView) findViewById(R.id.tv_savedData);
        savedData();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreSharePref.init(StoreInSPActivity.this);
                String name, email, address, age, number;
                name = ed_username.getText().toString();
                address = ed_user_address.getText().toString();
                age = ed_user_age.getText().toString();
                number = ed_user_number.getText().toString();
                email = ed_user_email.getText().toString();
                StoreSharePref.write(StoreSharePref.mUserName, name);
                StoreSharePref.write(StoreSharePref.mUserAddress, address);
                if (age.length()>0 && !age.equals(" ")) {
                    StoreSharePref.write(StoreSharePref.mUserAge, Integer.parseInt(age));
                }
                if (number.length()>0 && !number.equals(" ")) {
                    StoreSharePref.write(StoreSharePref.mUserNumber, number);
                }
                StoreSharePref.write(StoreSharePref.mUserEmail, email);
                StoreSharePref.write(StoreSharePref.isLogin, true);
                StoreSharePref.clearData();
                tv_savedData.setText("Name: "+ StoreSharePref.read(StoreSharePref.mUserName, "Khalid")+"\n"+
                        "Email: "+ StoreSharePref.read(StoreSharePref.mUserEmail, "@adc")
                        +"\n"+
                        "Age: "+ StoreSharePref.read(StoreSharePref.mUserAge, 0)
                        +"\n"+
                        "Number: "+ StoreSharePref.read(StoreSharePref.mUserNumber, "123")
                        +"\n"+
                        "Address: "+ StoreSharePref.read(StoreSharePref.mUserAddress, "abc street"));
            }
        });
    }

    private void savedData() {
        StoreSharePref.init(this);
        if (StoreSharePref.read(StoreSharePref.isLogin, false)){
            tv_savedData.setText("Name: "+ StoreSharePref.read(StoreSharePref.mUserName, "Khalid")+"\n"+
                    "Email: "+ StoreSharePref.read(StoreSharePref.mUserEmail, "@adc")
                    +"\n"+
                    "Age: "+ StoreSharePref.read(StoreSharePref.mUserAge, 0)
                    +"\n"+
                    "Number: "+ StoreSharePref.read(StoreSharePref.mUserNumber, "123")
                    +"\n"+
                    "Address: "+ StoreSharePref.read(StoreSharePref.mUserAddress, "abc street"));
        }
    }

}