package com.demo.storageinandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.storageinandroid.R;
import com.demo.storageinandroid.UpdateRecordActivity;
import com.demo.storageinandroid.UserListActivity;
import com.demo.storageinandroid.model.User;
import com.demo.storageinandroid.storage.DatabaseHelper;

import java.io.Serializable;
import java.util.List;

public class CustomAdapterUser extends BaseAdapter {

    Context context;
    List<User> list;
    View view;
    ImageView iv_delete, iv_edit;
    TextView tv_name, tv_age;

    public CustomAdapterUser(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.custom_item, viewGroup, false);
        iv_delete = view.findViewById(R.id.item_delete_user);
        iv_edit = view.findViewById(R.id.item_edit_user);
        tv_name = view.findViewById(R.id.item_user_name);
        tv_age = view.findViewById(R.id.item_user_age);

        User user = list.get(i);
        tv_name.setText(user.getU_name());
        tv_age.setText(user.getU_age());
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = databaseHelper.delete(Integer.parseInt(user.getID()));
                if (status){
                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    list.remove(i);
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateRecordActivity.class);
                intent.putExtra("User", user);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
