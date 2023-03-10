package com.example.blindassistancesystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class EmergencyContact extends AppCompatActivity {

    private ArrayList<Contact> contacts;
    private RecyclerView recycler;
    private Button addButton;
    private Button delete;
    EditText NameText;
    EditText NumberText;
    RecyclerAdapter recyclerAdapter;
    AlertDialog.Builder builder;
    private RecyclerAdapter.RecyclerViewClicklistener listener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        recycler = findViewById(R.id.Recycler_view);
        contacts = new ArrayList<>();

        setContact();
        setAdapter();
        addButton = (Button) findViewById(R.id.addContact);
          recyclerAdapter = new RecyclerAdapter(contacts);


        NameText = findViewById(R.id.Name_Edit);
        NumberText = findViewById(R.id.Number_Edit);
        delete=findViewById(R.id.delete);
        addButton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String name = NameText.getText().toString();
                String number = NumberText.getText().toString();
                Contact contact = new Contact();
                contact.setName(name);
                contact.setNumber(number);
                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
                helper.AddRecord(contact);
                //Toast.makeText(EmergencyContact.this,name+" "+number,Toast.LENGTH_LONG).show();
                contacts = helper.fetchAlldata();
                Log.d("tag", String.valueOf(contacts.size()));
                Toast.makeText(EmergencyContact.this, String.valueOf(contacts.size()), Toast.LENGTH_LONG).show();
               Intent intent = new Intent(EmergencyContact.this, EmergencyContact.class);
               startActivity(intent);

               ItemTouchHelper itemTouchHelper =new ItemTouchHelper(simpleCallback);
               itemTouchHelper.attachToRecyclerView(recycler);



            }
        });

    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT|ItemTouchHelper.DOWN | ItemTouchHelper.UP) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
           //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                contacts.remove(position);
                recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(EmergencyContact.this, "on Swiped ", Toast.LENGTH_SHORT).show();


        }
    };


    private void setContact() {
       
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        contacts = databaseHelper.fetchAlldata();
    }


    private void setAdapter() {
        setOnclickListener();
          recyclerAdapter = new RecyclerAdapter(contacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(recyclerAdapter);

    }

    private void setOnclickListener() {

    }


}




