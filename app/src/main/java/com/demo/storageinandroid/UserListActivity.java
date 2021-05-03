package com.demo.storageinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.storageinandroid.adapter.CustomAdapterUser;
import com.demo.storageinandroid.model.User;
import com.demo.storageinandroid.storage.DatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ListView listView;
    CustomAdapterUser adapterUser;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initRes();
    }

    private void initRes() {
        listView = findViewById(R.id.user_list);
        databaseHelper = new DatabaseHelper(this);
        if (getFetchedList().size()>0) {
            adapterUser = new CustomAdapterUser(this, getFetchedList());
            listView.setAdapter(adapterUser);
            adapterUser.notifyDataSetChanged();
        }
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int poistion = i;
//                Intent intent = new Intent(UserListActivity.this, UpdateRecordActivity.class);
//                User user = getFetchedList().get(i);
//                intent.putExtra("User", (Serializable) user);
//                startActivity(intent);
//            }
//        });
    }

    private List<User> getFetchedList() {
        List<User> users = new ArrayList<>();
        users= databaseHelper.fetchList();
        return users;
    }
}