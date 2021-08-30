package de.noelmate.fitness;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AddTag extends AppCompatActivity {

    private MainActivity m = new MainActivity();
    EditText saetze_input, gewicht_input;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);
        getIntentData();
        Log.v("ADDTAG", "ID: " + id);

        saetze_input = findViewById(R.id.saetzeEditText);
        gewicht_input = findViewById(R.id.gewichtEditText);
        Button add = (Button) findViewById(R.id.addButton_Secondary);
        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                FitnessDB myDB = new FitnessDB(AddTag.this);
                int id_int = Integer.parseInt(id);
                int saetze_int = Integer.parseInt(saetze_input.getText().toString().trim());
                int gewicht_int = Integer.parseInt(gewicht_input.getText().toString().trim());
                myDB.addItem(id_int, saetze_int, gewicht_int);
                openMain();
            }
        });



    }
    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void getIntentData(){
        if(getIntent().hasExtra("ffid")){
            id = getIntent().getStringExtra("ffid");
        }else{
            Toast.makeText(this, "Nicht gefunden!", Toast.LENGTH_SHORT).show();
        }
    }
}