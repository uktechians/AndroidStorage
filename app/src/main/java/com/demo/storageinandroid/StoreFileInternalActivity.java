package com.demo.storageinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StoreFileInternalActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_write_file;
    Button btn_write_data, btn_read_data;
    String fileName = "UKTechiansInfo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_file_internal);
        init();
    }

    private void init() {
        ed_write_file = (EditText) findViewById(R.id.ed_add_something);
        btn_read_data = (Button) findViewById(R.id.btn_read);
        btn_write_data = (Button) findViewById(R.id.btn_write);

        btn_read_data.setOnClickListener(this);
        btn_write_data.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_write){
            writeToFile();
        }
        if (view.getId()==R.id.btn_read){
            readToFile();
        }
    }

    private void writeToFile() {
        try {
            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(ed_write_file.getText().toString().getBytes());
            fos.close();
            ed_write_file.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readToFile() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            FileInputStream fis = openFileInput(fileName);

            int i = 0;
            while ((i = fis.read())!=-1){
                stringBuffer.append((char)i);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ed_write_file.setText(stringBuffer);
    }
}