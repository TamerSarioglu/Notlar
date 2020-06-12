package com.tamersarioglu.not;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private NotlarAdapter adapter;
    private ArrayList<Notlar> notlarArrayList;
    private Veritabani vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Not Uygulaması");
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        vt = new Veritabani(this);

        notlarArrayList = new NotlarDAO().tumNotlar(vt);

        adapter = new NotlarAdapter(this,notlarArrayList);

        rv.setAdapter(adapter);

        double toplam = 0 ;

        for (Notlar n: notlarArrayList){
            toplam = toplam + (n.getNot1()+n.getNot2())/2 ;
        }

        toolbar.setSubtitle("Ortalama : "+toplam/notlarArrayList.size());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,YeniKayitActivity.class));
            }
        });


    }
}
