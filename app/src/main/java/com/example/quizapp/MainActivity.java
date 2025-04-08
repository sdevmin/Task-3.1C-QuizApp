package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button startQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextName);
        startQuizButton = findViewById(R.id.buttonStartQuiz);

        startQuizButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
                finish();
            } else {
                nameInput.setError("Please enter your name");
            }
        });
    }
}
