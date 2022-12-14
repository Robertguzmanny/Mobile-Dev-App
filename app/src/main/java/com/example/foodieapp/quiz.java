package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class quiz extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button answerA, answerB, answerC, answerD;
    Button submitBtn;

    int score = 0;
    final int quizLength = 8;


    int overallQuestionNumber;
    String selectedAnswer = "";
    Integer [] randomlyPickedQuestion = new Integer[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewsAndOnClicks();

        randomizeQuestions();

        loadNewQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences share = getSharedPreferences("P3", MODE_PRIVATE);
        String result = share.getString("QUIZ",null);

        if(result != null){
            LinearLayout layout = findViewById(R.id.q);
            Resources res = getResources();
            if(result.equals("blue")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.color.blue, null);
                layout.setBackground(drawable);
            }
            else if(result.equals("grey")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.color.grey, null);
                layout.setBackground(drawable);
            }
            else{
                Drawable drawable = ResourcesCompat.getDrawable(res, R.color.teal_200, null);
                layout.setBackground(drawable);
            }
        }
        else{
            Log.d("MAIN", "error in changing picture");
        }
    }

    private void randomizeQuestions() {
        for(int i = 0; i < 20; i++) {
            randomlyPickedQuestion[i] = i;
        }

        List<Integer> randomizeQuestion = Arrays.asList(randomlyPickedQuestion);
        Collections.shuffle(randomizeQuestion);
        randomizeQuestion.toArray(randomlyPickedQuestion);
    }

    public void viewsAndOnClicks(){
        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.curr_question);
        answerA = findViewById(R.id.answer_a);
        answerB = findViewById(R.id.answer_b);
        answerC = findViewById(R.id.answer_c);
        answerD = findViewById(R.id.answer_d);
        submitBtn = findViewById(R.id.submit_button);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        answerA.setBackgroundColor(Color.WHITE);
        answerB.setBackgroundColor(Color.WHITE);
        answerC.setBackgroundColor(Color.WHITE);
        answerD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_button){
            if(selectedAnswer.equals(ChapterQuiz1.correctAnswers[randomlyPickedQuestion[overallQuestionNumber]])){
                score++;
            }
            overallQuestionNumber++;
            loadNewQuestion();
        }else{
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }

    void loadNewQuestion(){
        totalQuestionsTextView.setText( (overallQuestionNumber + 1) + " / " + quizLength);

        if(overallQuestionNumber == quizLength){
            overallQuestionNumber = 0;
            finishQuiz();
            return;
        }

        questionTextView.setText(ChapterQuiz1.question[randomlyPickedQuestion[overallQuestionNumber]]);


        String[] answers = ChapterQuiz1.choices[randomlyPickedQuestion[overallQuestionNumber]];
        List<String> randomizeAnswers = Arrays.asList(ChapterQuiz1.choices[randomlyPickedQuestion[overallQuestionNumber]]);
        Collections.shuffle(randomizeAnswers);
        randomizeAnswers.toArray(answers);


        answerA.setText(answers[0]);
        answerB.setText(answers[1]);

        if(answers.length > 2) {
            answerC.setVisibility(View.VISIBLE);
            answerD.setVisibility(View.VISIBLE);
            answerC.setText(answers[2]);
            answerD.setText(answers[3]);
        } else{
            answerC.setVisibility(View.INVISIBLE);
            answerD.setVisibility(View.INVISIBLE);
        }

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > quizLength * 0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }



        new AlertDialog.Builder(this)
                .setTitle("Your Score is: ")
                .setMessage("Score is " + score + " out of " + quizLength)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setNegativeButton("Cancel",(dialogInterface, i) -> returnToMain() )
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score = 0;
        overallQuestionNumber = 0;
        randomizeQuestions();
        loadNewQuestion();
    }

    void returnToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}