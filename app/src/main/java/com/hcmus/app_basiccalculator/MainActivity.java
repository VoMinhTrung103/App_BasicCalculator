package com.hcmus.app_basiccalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                if (isNewOperation) {
                    currentNumber = "";
                    isNewOperation = false;
                }
                currentNumber += ((Button) v).getText().toString();
                resultTextView.setText(currentNumber);
            });
        }

        findViewById(R.id.buttonDot).setOnClickListener(v -> {
            if (isNewOperation) {
                currentNumber = "0";
                isNewOperation = false;
            }
            if (!currentNumber.contains(".")) {
                currentNumber += ".";
                resultTextView.setText(currentNumber);
            }
        });

        findViewById(R.id.buttonPlus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.buttonPercent).setOnClickListener(v -> calculatePercent());
        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculate());
        findViewById(R.id.buttonDel).setOnClickListener(v -> {
            currentNumber = "";
            operator = "";
            firstNumber = 0;
            isNewOperation = true;
            resultTextView.setText("0");
        });
    }

    private void setOperator(String op) {
        if (!currentNumber.isEmpty()) {
            firstNumber = Double.parseDouble(currentNumber);
            operator = op;
            currentNumber = "";
            resultTextView.setText(String.valueOf(firstNumber) + " " + operator);
        }
    }

    private void calculate() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }

            resultTextView.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            operator = "";
            isNewOperation = true;
        }
    }

    private void calculatePercent() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = number / 100;
            resultTextView.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            isNewOperation = true;
        }
    }

    private void calculateSquare() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = number * number;
            resultTextView.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            isNewOperation = true;
        }
    }

    private void calculateSqrt() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            if (number >= 0) {
                double result = Math.sqrt(number);
                resultTextView.setText(String.valueOf(result));
                currentNumber = String.valueOf(result);
                isNewOperation = true;
            } else {
                resultTextView.setText("Error");
            }
        }
    }
}
