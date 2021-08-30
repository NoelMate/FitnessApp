package de.noelmate.fitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Uebung extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String id, name;
    FitnessDB myDB;
    ArrayList<String> al_saetze, al_gewicht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebung);
        getIntentData();
        this.setTitle(name);
        myDB = new FitnessDB(Uebung.this);
        al_saetze = new ArrayList<>();
        al_gewicht = new ArrayList<>();

        storeDataInArrays();

        mRecyclerView = findViewById(R.id.recyclerView_Secondary);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(Uebung.this, al_saetze, al_gewicht);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.floatingActionButton_Secondary);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTag();
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.getSpecificItemsFromSecondaryTable(Integer.parseInt(id));
        
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                al_saetze.add(cursor.getString(2));
                al_gewicht.add(cursor.getString(3));
            }
        }
    }

    private void openAddTag() {
        Intent intent = new Intent(this, AddTag.class);
        intent.putExtra("ffid", id);
        Log.v("UEBUNG", "ID: " + id);
        startActivity(intent);
    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && (getIntent().hasExtra("name"))){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
        }else{
            Toast.makeText(this, "Nicht gefunden!", Toast.LENGTH_SHORT).show();
        }
    }
}