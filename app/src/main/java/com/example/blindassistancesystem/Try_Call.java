package com.example.blindassistancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Try_Call extends AppCompatActivity {

    Button button;

    String fruits[]={"Apple","Banana","Apricot","Orange","Water Melon","Apple","Banana","Apricot","Orange","Water Melon"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_call);
        listView=(ListView) findViewById(R.id.lis_view_id);

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,R.layout.list_view_holder,R.id.text_view_list_view,fruits);
        listView.setAdapter(arrayAdapter);




    }
}