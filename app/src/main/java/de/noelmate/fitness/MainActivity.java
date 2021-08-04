package de.noelmate.fitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    FitnessDB myDB;
    ArrayList<String> uebung_id, uebung_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new FitnessDB(MainActivity.this);
        uebung_id = new ArrayList<>();
        uebung_name = new ArrayList<>();

        storeDataInArrays();


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(MainActivity.this, uebung_id, uebung_name);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton mFab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItem();
            }
        });

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                uebung_id.add(cursor.getString(0));
                uebung_name.add(cursor.getString(1));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.settings){
            openSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openSettings(){
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }

    public void openAddItem(){
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }

    public void openItem(View v){
        Intent intent = new Intent(this, Uebung.class);
        intent.putExtra("text", String.valueOf(123));
        startActivity(intent);
    }
}