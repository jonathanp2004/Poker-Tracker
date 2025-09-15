package com.example.poke_trackerapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner levelSpinner;

    private EditText numberEt, nameET, speciesEt, heightET,weightET, hpET, attackET, defenseET;
    private RadioButton maleButton, unkButton, femaleButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        levelSpinner = findViewById(R.id.levelSpinner);

        numberEt = findViewById(R.id.numberET);
        nameET = findViewById(R.id.nameET);
        speciesEt = findViewById(R.id.speicesET);
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackTE);
        defenseET = findViewById(R.id.defenseET);

        femaleButton = findViewById(R.id.femaleButton);
        maleButton = findViewById(R.id.maleButton);
        unkButton = findViewById(R.id.unkButton);

        resetButton = findViewById(R.id.resetButton);



        ArrayList<String> lvls = new ArrayList<>();
        for(int i =1; i<=50; i++) lvls.add(String.valueOf(i));
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, lvls);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.attackTE), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}