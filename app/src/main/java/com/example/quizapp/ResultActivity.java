package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.R;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button buttonRestart, buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.textFinalScore);
        buttonRestart = findViewById(R.id.buttonRestart);
        buttonFinish = findViewById(R.id.buttonFinish);

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        resultText.setText(userName + ", your final score is: " + score + "/" + total);

        buttonRestart.setOnClickListener(v -> {
            Intent restartIntent;
            restartIntent = new Intent(ResultActivity.this, com.example.quizapp.QuizActivity.class);
            restartIntent.putExtra("USER_NAME", userName);
            startActivity(restartIntent);
            finish();
        });

        buttonFinish.setOnClickListener(v -> finishAffinity());
    }
}
