package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quizapp.helpers.QuestionData;
import com.example.quizapp.models.Question;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textQuestion, textWelcome;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3, option4;
    private Button buttonSubmit;
    private ProgressBar progressBar;

    private List<Question> questionList;
    private int currentIndex = 0;
    private int score = 0;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userName = getIntent().getStringExtra("USER_NAME");

        textWelcome = findViewById(R.id.textWelcome);
        textQuestion = findViewById(R.id.textQuestion);
        radioGroup = findViewById(R.id.radioGroupOptions);
        option1 = findViewById(R.id.radioOption1);
        option2 = findViewById(R.id.radioOption2);
        option3 = findViewById(R.id.radioOption3);
        option4 = findViewById(R.id.radioOption4);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        progressBar = findViewById(R.id.progressBar);

        questionList = QuestionData.getQuestions();

        if (questionList == null || questionList.isEmpty()) {
            Toast.makeText(this, "No questions found.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        textWelcome.setText("Hello " + userName + "!");
        loadQuestion();

        buttonSubmit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) return;

            RadioButton selectedRadio = findViewById(selectedId);
            int selectedIndex = radioGroup.indexOfChild(selectedRadio);
            Question current = questionList.get(currentIndex);

            if (selectedIndex == current.getCorrectAnswerIndex()) {
                selectedRadio.setBackgroundColor(Color.GREEN);
                score++;
            } else {
                selectedRadio.setBackgroundColor(Color.RED);
                radioGroup.getChildAt(current.getCorrectAnswerIndex()).setBackgroundColor(Color.GREEN);
            }

            buttonSubmit.setEnabled(false);

            new Handler().postDelayed(() -> {
                currentIndex++;
                if (currentIndex < questionList.size()) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("TOTAL", questionList.size());
                    intent.putExtra("USER_NAME", userName);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        });
    }

    private void loadQuestion() {
        radioGroup.clearCheck();
        resetRadioColors();

        Question q = questionList.get(currentIndex);
        textQuestion.setText(q.getQuestionText());
        option1.setText(q.getOptions()[0]);
        option2.setText(q.getOptions()[1]);
        option3.setText(q.getOptions()[2]);
        option4.setText(q.getOptions()[3]);

        int progress = (int) (((float) currentIndex / questionList.size()) * 100);
        progressBar.setProgress(progress);
        buttonSubmit.setEnabled(true);
    }

    private void resetRadioColors() {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
