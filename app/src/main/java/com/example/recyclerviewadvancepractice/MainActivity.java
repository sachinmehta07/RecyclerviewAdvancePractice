package com.example.recyclerviewadvancepractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  ItemAdapter adapter;
    private ArrayList<String> items;
    private int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        items.add("SACHIN");
        items.add("VIRAT");
        items.add("KHOLI");
        items.add("SHUBHAM GILL");
        items.add("MS DHONI");
        items.add("PIYUSH SHARMA");

        items.add("RAVI BHAI");
        items.add("JAY SHAH");
        items.add("MODI");

        items.add("KARAN PATEL");
        items.add("BABA");
        items.add("GANPAT");


        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);




    }
}