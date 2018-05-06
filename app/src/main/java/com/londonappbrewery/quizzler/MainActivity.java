package com.londonappbrewery.quizzler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here
    final int Progress_bar_increment = 8;

    // TODO: Declare member variables here:
    Button mtrueButton;
    Button mfalseButton;

    TextView mQuestionTextView;
    TextView mScoreTextView;

    ProgressBar mProgressBar;

    int mIndex;
    int mQuestion;
    int mscore;

    // TODO: Uncomment to create question bank
    private truefalse[] mQuestionBank = new truefalse[] {
            new truefalse(R.string.question_1, true),
            new truefalse(R.string.question_2, true),
            new truefalse(R.string.question_3, true),
            new truefalse(R.string.question_4, true),
            new truefalse(R.string.question_5, true),
            new truefalse(R.string.question_6, false),
            new truefalse(R.string.question_7, true),
            new truefalse(R.string.question_8, false),
            new truefalse(R.string.question_9, true),
            new truefalse(R.string.question_10, true),
            new truefalse(R.string.question_11, false),
            new truefalse(R.string.question_12, false),
            new truefalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            mscore = savedInstanceState.getInt("scorekey");
            mIndex = savedInstanceState.getInt("indexkey");
        }else {
            mscore = 0;
            mIndex = 0;
        }
        mtrueButton = findViewById(R.id.true_button);
        mfalseButton = findViewById(R.id.false_button);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mScoreTextView = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);
         mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mScoreTextView.setText("score" + mscore + "/" + mQuestionBank.length);


        mtrueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mfalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void updateQuestion(){
        mIndex = (mIndex + 1) % mQuestionBank.length;
        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mscore + " points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(Progress_bar_increment);
        mScoreTextView.setText("score" + mscore + "/" + mQuestionBank.length);
    }
    private void checkAnswer(Boolean userInput){
        Boolean correctAnswer = mQuestionBank[mIndex].getAnswer();

        if (userInput == correctAnswer){
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mscore = mscore + 1;
        } else{
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putInt("scorekey", mscore);
        outstate.putInt("indexkey",mIndex);

    }

}