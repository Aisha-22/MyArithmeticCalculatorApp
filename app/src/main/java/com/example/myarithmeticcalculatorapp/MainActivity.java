package com.example.myarithmeticcalculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText firstNumberEditText;
    private EditText operatorEditText;
    private EditText secondNumberEditText;
    private TextView resultsTextView;
    private ProgressBar progressBar;
    private Button calculateButton;
    private boolean isFirstCalculation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        TextView arithmeticCalculatorTextView = findViewById(R.id.arithmeticCalculatorTextView);
        firstNumberEditText = findViewById(R.id.firstNumberEditText);
        operatorEditText = findViewById(R.id.operatorEditText);
        secondNumberEditText = findViewById(R.id.secondNumberEditText);
        calculateButton = findViewById(R.id.calculateButton);
        TextView results1TextView = findViewById(R.id.results1TextView);
        resultsTextView = findViewById(R.id.resultsTextView);
        progressBar = findViewById(R.id.progressBar);

        // Set an OnClickListener for the calculateButton
        calculateButton.setOnClickListener(view -> calculateResult());
    }

    private void calculateResult() {
        // Retrieve input values from EditText fields
        String firstNumberStr = firstNumberEditText.getText().toString();
        String operator = operatorEditText.getText().toString();
        String secondNumberStr = secondNumberEditText.getText().toString();

        // Check if all input fields are non-empty
        if (!firstNumberStr.isEmpty() && !operator.isEmpty() && !secondNumberStr.isEmpty()) {
            try {
                // Parse input strings to double values
                double firstNumber = Double.parseDouble(firstNumberStr);
                double secondNumber = Double.parseDouble(secondNumberStr);
                double result = 0.0;

                // Perform arithmetic calculations based on the operator
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            resultsTextView.setText("Cannot divide by zero");
                            return;
                        }
                        break;
                    default:
                        resultsTextView.setText("Invalid operator");
                        return;
                }

                // Display a progress bar during calculation
                showProgressBar();

                // Update resultsTextView with the result, and reset input fields for subsequent calculations
                if (!isFirstCalculation) {
                    resultsTextView.setText("Result: " + result);
                    resetInputFields();
                } else {
                    isFirstCalculation = false;
                    resultsTextView.setText("" + result);
                }
            } catch (NumberFormatException e) {
                resultsTextView.setText("Invalid number format");
            }
        } else {
            resultsTextView.setText("Please fill in all fields");
        }
    }

    private void showProgressBar() {
        // Disable the calculateButton and make the progressBar visible
        calculateButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        // Simulate calculation delay with a handler and re-enable the button
        new Handler().postDelayed(() -> {
            calculateButton.setEnabled(true);
            progressBar.setVisibility(View.INVISIBLE);
        }, 2000);
    }

    private void resetInputFields() {
        // Clear the input fields for first number, operator, and second number
        firstNumberEditText.setText("");
        operatorEditText.setText("");
        secondNumberEditText.setText("");
    }
}







