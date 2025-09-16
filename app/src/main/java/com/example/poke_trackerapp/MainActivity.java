package com.example.poke_trackerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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
        //setContentView(R.layout.linear);
        setContentView(R.layout.table);
        //setContentView(R.layout.constraint);

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
        levelTV = findViewById(R.id.levelTV);
        genderTV = findViewById(R.id.genderTV);

        normalColor = numberTV.getCurrentTextColor();


        ArrayList<String> lvls = new ArrayList<>();
        for(int i =1; i<=50; i++) lvls.add(String.valueOf(i));
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, lvls);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);

        reset();
        resetButton.setOnClickListener(v -> reset());




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootScroll), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }
    private void reset()
    {
        clearErrors();
        numberET.setText("777");
        nameET.setText("Pikachu");
        speciesEt.setText("Mouse Pokemon");
        heightET.setText("0.4");
        weightET.setText("6.0");
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
        clearErrors();

        String number = numberET.getText().toString().trim();
        String name = nameET.getText().toString().trim();
        String species = speciesEt.getText().toString().trim();
        String height = heightET.getText().toString().trim();
        String weight = weightET.getText().toString().trim();
        String hp = hpET.getText().toString().trim();
        String attack = attackET.getText().toString().trim();
        String defense = defenseET.getText().toString().trim();

        ArrayList<String> errors = new ArrayList<>();

        if(TextUtils.isEmpty(number)) {errors.add("National Number is required");
        numberTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(name)) {errors.add("Name is required");
        nameTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(species)) {errors.add("Species is required");
        speciesTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(height)) {errors.add("Height is required");
        heightTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(weight)) {errors.add("Weight is required");
        weightTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(hp)) {errors.add("HP is required");
        hpTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(attack)) {errors.add("Attack is required");
        attackTV.setTextColor(Color.RED);}
        if(TextUtils.isEmpty(defense)) {errors.add("Defense is required");
        defenseTV.setTextColor(Color.RED);}


        boolean male = maleButton.isChecked();
        boolean female = femaleButton.isChecked();
        boolean unk = unkButton.isChecked();


        if (!male && !female && !unk)
        {
        errors.add("Select Gender");
        genderTV.setTextColor(Color.RED);
        }

        //Name check
        if(!TextUtils.isEmpty(name))
        {
            if(name.length() < 3 || name.length() > 12)
            {
                errors.add("Name must be 3-12 characters");
                nameTV.setTextColor(Color.RED);
            } else if (!isLetters(name))
            {
                errors.add("Name must only contain letters");
                nameTV.setTextColor(Color.RED);
            }
        }
        //name check

        if (!TextUtils.isEmpty(name)) {
            if (name.length() < 3 || name.length() > 12) {
                errors.add("Name must be 3–12 characters");
                nameTV.setTextColor(Color.RED);
            } else if (!isNameCharsValid(name)) {
                errors.add("Name may contain only letters, dots, and spaces");
                nameTV.setTextColor(Color.RED);
            } else {
                String fixed = toTitleCase(name);
                if (!fixed.equals(name)) nameET.setText(fixed);
            }
        }

        //species check
        if (!TextUtils.isEmpty(species)) {
            if (!isSpeciesCharsValid(species)) {
                errors.add("Species may contain only letters and spaces");
                speciesTV.setTextColor(Color.RED);
            } else {
                String fixed = toTitleCase(species);
                if (!fixed.equals(species)) speciesEt.setText(fixed);
            }
        }
//height check
        if(!TextUtils.isEmpty(height))
        {
            Double h = readDouble(heightET);
            if(h == null)
            {
                errors.add("Height must be a number");
                heightTV.setTextColor(Color.RED);
            }else if (h <0.20 || h > 169.99)
            {
                errors.add("Height must be 0.20-169.99");
                heightTV.setTextColor(Color.RED);
            }

        }
        //weight check
        if(!TextUtils.isEmpty(weight))
        {
            Double w = readDouble(weightET);
            if(w == null)
            {
                errors.add("Weight must be a number");
                weightTV.setTextColor(Color.RED);
            } else if (w <0.10 || w > 992.7)
            {
                errors.add("Weight must be 0.10-992.7");
                weightTV.setTextColor(Color.RED);
            }
        }
        //hp check
        if(!TextUtils.isEmpty(hp))
        {
            Integer hpp = readInt(hpET);
            if(hpp == null)
            {
                errors.add("HP must be an integer");
                hpTV.setTextColor(Color.RED);
            } else if (hpp <1 || hpp > 362)
            {
                errors.add("HP must be 1-362");
                hpTV.setTextColor(Color.RED);
            }
        }
        //attack check
        if(!TextUtils.isEmpty(attack))
        {
            Integer att = readInt(attackET);
            if(att == null)
            {
                errors.add("Attack must be an integer");
                attackTV.setTextColor(Color.RED);
            } else if (att <0 || att > 526)
            {
                errors.add("Attack must be 0-526");
                attackTV.setTextColor(Color.RED);
            }
        }
        //defense check
        if(!TextUtils.isEmpty(defense))
        {
            Integer def = readInt(defenseET);
            if(def == null)
            {
                errors.add("Defense must be an integer");
                defenseTV.setTextColor(Color.RED);
            } else if (def <10 || def > 614)
            {
                errors.add("Defense must be 10-614");
                defenseTV.setTextColor(Color.RED);
            }
        }

        if(errors.isEmpty())
        {
            Toast.makeText(this, "Stored in database", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this,TextUtils.join("\n", errors), Toast.LENGTH_LONG).show();
        }

        /*String level = (levelSpinner.getSelectedItem() != null) ? levelSpinner.getSelectedItem().toString() : "";

        String gender = unkButton.isChecked() ? "Unknown" : maleButton.isChecked() ? "Male" : femaleButton.isChecked() ? "Female" : "";

        Toast.makeText(this, "Stored in database", Toast.LENGTH_SHORT).show();*/
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
    private Integer readInt(EditText et)
    {
        String s = et.getText().toString().trim();
        if (s.isEmpty()) return null;
        try{return Integer.parseInt(s);}
        catch (NumberFormatException e) {return null;}
    }

    private Double readDouble(EditText et)
    {
        String s = et.getText().toString().trim();
        if (s.isEmpty()) return null;
        try{return Double.parseDouble(s);}
        catch (NumberFormatException e) {return null;}
    }

    private boolean isLetters(String s)
    {
        if(s==null) return false;
        for(int i =0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(!Character.isLetter(c)) return false;
        }
        return true;
    }

    private boolean isLandS(String s)
    {
        if(s==null) return false;
        for(int i =0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if(!Character.isLetter(c) && c != ' ') return false;
        }
        return true;
    }

    private boolean isNameCharsValid(String s)
    {
        return s != null && s.matches("[a-zA-Z]+");
    }

    private boolean isSpeciesCharsValid(String s)
    {
        return s != null && s.matches("[a-zA-Z ]+");
    }

    private String toTitleCase(String s)
    {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }


}