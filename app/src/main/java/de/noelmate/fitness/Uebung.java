package de.noelmate.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Uebung extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebung);
        Bundle b = getIntent().getExtras();
        String titel = b.getString("text");
        this.setTitle(titel);
    }
}