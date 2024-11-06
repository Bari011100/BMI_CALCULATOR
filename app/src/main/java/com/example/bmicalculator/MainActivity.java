package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText ageInput, heightInput, weightInput;
    private RadioGroup genderGroup;
    private RadioButton selectedGender;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ageInput = findViewById(R.id.Textage);
        heightInput = findViewById(R.id.Textheight);
        weightInput = findViewById(R.id.Textweight);
        genderGroup = findViewById(R.id.RGgroup);
        calculateButton = findViewById(R.id.button);

        // Result text view to display the BMI result
        resultText = new TextView(this);
        resultText.setTextSize(18);
        ((androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.main)).addView(resultText);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Validate input
        if (TextUtils.isEmpty(ageInput.getText().toString()) ||
                TextUtils.isEmpty(heightInput.getText().toString()) ||
                TextUtils.isEmpty(weightInput.getText().toString()) ||
                genderGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get user input
        int age = Integer.parseInt(ageInput.getText().toString());
        float height = Float.parseFloat(heightInput.getText().toString()) / 100; // Convert cm to meters
        float weight = Float.parseFloat(weightInput.getText().toString());
        double bmi = weight / (height * height);

        // Get selected gender
        selectedGender = findViewById(genderGroup.getCheckedRadioButtonId());
        String gender = selectedGender.getText().toString();

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("BMI_RESULT", bmi);
        intent.putExtra("GENDER",gender);
        intent.putExtra("AGE",age);
        startActivity(intent);


    }


}
