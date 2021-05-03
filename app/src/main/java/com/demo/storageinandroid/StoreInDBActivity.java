package com.demo.storageinandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.storageinandroid.model.User;
import com.demo.storageinandroid.storage.DatabaseHelper;

public class StoreInDBActivity extends AppCompatActivity {
    EditText ed_username, ed_user_number, ed_user_age, ed_user_email, ed_user_address;
    TextView tv_savedData;
    Button btn_submit, btn_user_list;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_in_d_b);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            Log.d("External Storage", "Permission Granted");
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 120);
        }
        init();
    }

    private void init() {
        databaseHelper = new DatabaseHelper(this);
        ed_user_address = (EditText) findViewById(R.id.ed_user_address);
        ed_user_age = (EditText) findViewById(R.id.ed_user_age);
        ed_user_email = (EditText) findViewById(R.id.ed_user_email);
        ed_user_number = (EditText) findViewById(R.id.ed_user_number);
        ed_username = (EditText) findViewById(R.id.ed_user_name);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        tv_savedData = (TextView) findViewById(R.id.tv_savedData);
        btn_user_list = (Button) findViewById(R.id.btn_viewList);

        btn_user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreInDBActivity.this, UserListActivity.class));
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setU_name(ed_username.getText().toString());
                user.setU_phone(ed_user_number.getText().toString());
                user.setU_email(ed_user_email.getText().toString());
                user.setU_address(ed_user_address.getText().toString());
                user.setU_age(ed_user_age.getText().toString());

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.user_name, user.getU_name());
                contentValues.put(DatabaseHelper.user_age, user.getU_age());
                contentValues.put(DatabaseHelper.user_email, user.getU_email());
                contentValues.put(DatabaseHelper.user_phone, user.getU_phone());
                contentValues.put(DatabaseHelper.user_address, user.getU_address());
                if (databaseHelper!=null){
                    boolean isInserted = databaseHelper.insert(contentValues);
                    if (isInserted){
                        Toast.makeText(StoreInDBActivity.this, "Inserted", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else {
                        Toast.makeText(StoreInDBActivity.this, "not Inserted", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }
        });
    }

}