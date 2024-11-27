package com.example.assigment2;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Age extends AppCompatActivity {
    Button nextButton,previousButton;
    EditText numberInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_age);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nextButton = findViewById(R.id.button_next);
        previousButton = findViewById(R.id.button_previous);
        numberInput = findViewById(R.id.numberInput);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberInput.getText().toString().isEmpty()){
                    numberInput.setError("Please enter your age");
                    return;
                }
                Intent intent = new Intent(Age.this, Selfie.class);
                startActivity(intent);
            }
        });

        // "Previous" button
        nextButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_arrow_forward_ios_24
                , 0);
        previousButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_arrow_back_ios_24
                , 0, 0, 0);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Age.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }
}