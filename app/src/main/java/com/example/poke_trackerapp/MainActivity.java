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

    private EditText numberET, nameET, speciesEt, heightET,weightET, hpET, attackET, defenseET;
    private RadioButton maleButton, unkButton, femaleButton;
    private Button resetButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        levelSpinner = findViewById(R.id.levelSpinner);

        numberET = findViewById(R.id.numberET);
        nameET = findViewById(R.id.nameET);
        speciesEt = findViewById(R.id.speicesET);
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackET);
        defenseET = findViewById(R.id.defenseET);

        femaleButton = findViewById(R.id.femaleButton);
        maleButton = findViewById(R.id.maleButton);
        unkButton = findViewById(R.id.unkButton);

        resetButton = findViewById(R.id.resetButton);
        saveButton = findViewById(R.id.saveButton);







        ArrayList<String> lvls = new ArrayList<>();
        for(int i =1; i<=50; i++) lvls.add(String.valueOf(i));
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, lvls);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);

        reset();
        resetButton.setOnClickListener(v -> reset());




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.attackTE), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }
    private void reset()
    {
        numberET.setText("777");
        nameET.setText("Pikachu");
        speciesEt.setText("Mouse Pokemon");
        heightET.setText("0.4 m");
        weightET.setText("6.0 kg");
        hpET.setText("35");
        attackET.setText("55");
        defenseET.setText("40");

        if(levelSpinner.getAdapter() != null && levelSpinner.getAdapter().getCount()>0)
        {
            levelSpinner.setSelection(0);
        }
        femaleButton.setChecked(false);
        maleButton.setChecked(false);
        unkButton.setChecked(false);
    }

    private void save()
    {

    }
}