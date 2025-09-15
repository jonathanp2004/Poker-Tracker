package com.example.poke_trackerapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView numberTV, nameTV, speciesTV, heightTV, weightTV, levelTV, genderTV, hpTV, attackTV, defenseTV;
    private int normalColor;

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
        saveButton.setOnClickListener(v -> save());

        numberTV = findViewById(R.id.numberTV);
        nameTV = findViewById(R.id.nameTV);
        speciesTV = findViewById(R.id.speicesTV);
        heightTV = findViewById(R.id.heightTV);
        weightTV = findViewById(R.id.weightTV);
        hpTV = findViewById(R.id.hpTV);
        attackTV = findViewById(R.id.attackTV);
        defenseTV = findViewById(R.id.defenseTV);

        normalColor = numberTV.getCurrentTextColor();












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
        String number = numberET.getText().toString().trim();
        String name = nameET.getText().toString().trim();
        String species = speciesEt.getText().toString().trim();
        String height = heightET.getText().toString().trim();
        String weight = weightET.getText().toString().trim();
        String hp = hpET.getText().toString().trim();
        String attack = attackET.getText().toString().trim();
        String defense = defenseET.getText().toString().trim();

        String level = (levelSpinner.getSelectedItem() != null) ? levelSpinner.getSelectedItem().toString() : "";

        String gender = unkButton.isChecked() ? "Unknown" : maleButton.isChecked() ? "Male" : femaleButton.isChecked() ? "Female" : "";

        Toast.makeText(this, "Stored in database", Toast.LENGTH_SHORT).show();
    }

    private void clearErrors()
    {
        numberTV.setTextColor(normalColor);
        nameTV.setTextColor(normalColor);
        speciesTV.setTextColor(normalColor);
        genderTV.setTextColor(normalColor);
        heightTV.setTextColor(normalColor);
        weightTV.setTextColor(normalColor);
        hpTV.setTextColor(normalColor);
        attackTV.setTextColor(normalColor);
        defenseTV.setTextColor(normalColor);
        levelTV.setTextColor(normalColor);
    }
}