package com.demo.storageinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViewById(R.id.btn_shared_pref).setOnClickListener(this);
        findViewById(R.id.btn_in_storage).setOnClickListener(this);
        findViewById(R.id.btn_ex_storage).setOnClickListener(this);
        findViewById(R.id.btn_db).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_shared_pref){
            startActivity(new Intent(this, StoreInSPActivity.class));
        }
        else  if (view.getId()==R.id.btn_in_storage){
            startActivity(new Intent(this, StoreFileInternalActivity.class));
        }

        if (view.getId()==R.id.btn_ex_storage){
            startActivity(new Intent(this, StoreFileExternalActivity.class));
        }

        if (view.getId()==R.id.btn_db){
            startActivity(new Intent(this, StoreInDBActivity.class));
        }
    }
}