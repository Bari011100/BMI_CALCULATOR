package com.example.bmicalculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView bmiResultText, detailsText;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bmiResultText = findViewById(R.id.bmiResultText);
        detailsText = findViewById(R.id.detailsText);
        buttonback = findViewById(R.id.Btnback);

        double bmi = getIntent().getDoubleExtra("BMI_RESULT", 0.0);
        String gender = getIntent().getStringExtra("GENDER");
        int age = getIntent().getIntExtra("AGE", 0);

        bmiResultText.setText(String.format("Your BMI: %.2f", bmi));
        detailsText.setText(String.format("Gender: %s, Age: %d", gender, age));

        String bmiCategory = getBMICategory(bmi);
        detailsText.append("\nBMI Category: " + bmiCategory);

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi > 25 ) {
            return "Overweight";
        } else {
            return "You are heatlhy weight";
        }



    }
}