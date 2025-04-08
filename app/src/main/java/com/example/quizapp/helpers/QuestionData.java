package com.example.quizapp.helpers;

import com.example.quizapp.models.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "London", "Paris", "Madrid"}, 2));

        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Venus", "Mars", "Jupiter"}, 2));

        questions.add(new Question("Who wrote 'Romeo and Juliet'?",
                new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 1));

        questions.add(new Question("What is the largest ocean on Earth?",
                new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 3));

        questions.add(new Question("Which element has the chemical symbol 'O'?",
                new String[]{"Gold", "Oxygen", "Iron", "Hydrogen"}, 1));

        return questions;
    }
}
