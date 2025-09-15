package com.example.poke_trackerapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner levelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        levelSpinner = findViewById(R.id.levelSpinner);
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