package com.demo.storageinandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StoreFileExternalActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_write, btn_read;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_file_external);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){
            Log.d("External Storage", "Permission Granted");
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 120);
        }
        init();

    }

    private void init() {
        btn_write = (Button) findViewById(R.id.btn_write);
        btn_read = (Button) findViewById(R.id.btn_read);
        editText = (EditText) findViewById(R.id.ed_add_something);

        btn_read.setOnClickListener(this);
        btn_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_read){
            readFile();
        }
        else if (view.getId()==R.id.btn_write){
            writeFile();
        }
    }

    private void readFile() {

        StringBuffer stringBuffer = new StringBuffer();
        String aDataRow = "";
        String aBuffer = "";
        try {
            File myFile = new File("/sdcard/"+"UKTechians.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),aBuffer,Toast.LENGTH_LONG).show();
    }

    private void writeFile() {
        FileOutputStream fos;
        OutputStreamWriter osw;

        try {
            File file = new File("/sdcard/UKTechians.txt");
            file.createNewFile();
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos);
            osw.append(editText.getText().toString());
            osw.close();
            fos.close();
            Toast.makeText(getApplicationContext(),"Write file in to External Storage",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}