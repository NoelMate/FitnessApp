package de.noelmate.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {

    private MainActivity m = new MainActivity();
    EditText name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name_input = findViewById(R.id.uebungEditText);
        Button add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FitnessDB myDB = new FitnessDB(AddItem.this);
                myDB.addUebung(name_input.getText().toString().trim());
            }
        });



    }
}