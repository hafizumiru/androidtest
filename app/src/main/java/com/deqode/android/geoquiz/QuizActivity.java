package com.deqode.android.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mLoveButton;
    private Button mAiButton;
    private Button mNextButton;
    private TextView mQuestionText;
    private Button mPreviousButton;

    private int mCurrentIndex = 0;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    Question[] listOfQuestion = new Question[]{
            new Question(R.string.question_1,true),
            new Question(R.string.question_2,false),
            new Question(R.string.question_3,true),
            new Question(R.string.question_4,false),
            new Question(R.string.question_5,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mLoveButton = (Button) findViewById(R.id.Love);
        mLoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showTrueAnswer(v);
                checkAnswer(true);
            }
        });

        mAiButton = (Button) findViewById(R.id.Ai);
        mAiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showFalseAnswer(v);
                checkAnswer(false);
            }
        });

        mQuestionText = (TextView) findViewById(R.id.questionText);
//        int question = listOfQuestion[mCurrentIndex].getTextRestId();
//        mQuestionText.setText(question);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();

        mNextButton = (Button) findViewById(R.id.buttonNext);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(mCurrentIndex!=listOfQuestion.length-1){
//                    mCurrentIndex++;
//                } else {
//                    mCurrentIndex = 0;
//                }

                mCurrentIndex = (mCurrentIndex + 1) % listOfQuestion.length;
//                int question = listOfQuestion[mCurrentIndex].getTextRestId();
//                mQuestionText.setText(question);
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.buttonPrevious);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCurrentIndex =(mCurrentIndex - 1) % listOfQuestion.length;
                if(mCurrentIndex != 0){
                    mCurrentIndex--;
                } else {
                    mCurrentIndex = listOfQuestion.length - 1;
                }

                updateQuestion();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion(){
        int question = listOfQuestion[mCurrentIndex].getTextRestId();
        mQuestionText.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIs = listOfQuestion[mCurrentIndex].isAnswerTrue();

        if (userPressedTrue == answerIs){
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
        }
    }

//    public void showTrueAnswer(View view){
//        Toast.makeText(this, "Cintaku padamu bagaikan apaya", Toast.LENGTH_LONG).show();
//    }
//
//    public void showFalseAnswer(View view){
//        Toast.makeText(this, "Aishiteru yo", Toast.LENGTH_LONG).show();
//    }
}
