package de.noelmate.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class Uebung extends AppCompatActivity {

    String id, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebung);
        getIntentData();
        this.setTitle(name);
        Log.v("TEST", "ID: " + id);
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