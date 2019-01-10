package com.example.win_10.myapplication;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imv;
    private RadioGroup radAnswer;
    private String strAnswer;
    private MyAlertDialog objMyAlert;
    private Question objQuestion;
    private MyAlertDialog objMyAlertDialog;
    private int intTime = 1, intUserChoose, intUserScore;
    private int intUserChooseArray[],intTrueAnswerArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialWidget();
        intUserChooseArray = new int[5];
        intTrueAnswerArray = new  int[5];
        setVauleTrueAnswer();
        objQuestion = new Question();
        objQuestion.setOnQuestionChangeListener(new Question.OnQuestionChangeListener() {
            @Override
            public void onQuestionChangeListener(Question question) {
                switch (question.getIntQuestion()){
                    case 1:
                        imv.setImageResource(R.drawable.cow);
                        break;
                    case 2:
                        imv.setImageResource(R.drawable.cat);
                        break;
                    case 3:
                        imv.setImageResource(R.drawable.pig);
                        break;
                    case 4:
                        imv.setImageResource(R.drawable.sheep);
                        break;
                        default:
                            break;

                }
            }
        });
        radAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.radioCow:
                        strAnswer = "Com";
                        intUserChoose = 1;
                        break;
                    case R.id.radioCat:
                        strAnswer = "Cat";
                        intUserChoose = 2;
                        break;
                    case R.id.radioPig:
                        strAnswer = "Pig";
                        intUserChoose = 3;
                        break;
                    case R.id.radioSheep:
                        strAnswer = "Sheep";
                        intUserChoose = 4;
                        break;
                    default:
                        strAnswer = null;
                        break;

                }

            }

        });

    }

    private void sentValueToQuestion() {
        if (intTime == 4) {
            intTime = 0;
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("Score",intUserScore);
            startActivity(intent);

        }
        //intTime++;
        objQuestion.setIntQuestion(intTime + 1);
    }


    private void setVauleTrueAnswer(){
        intTrueAnswerArray[1] = 1;
        intTrueAnswerArray[2] = 2;
        intTrueAnswerArray[3] = 3;
        intTrueAnswerArray[4] = 4;
    }

    private void initialWidget() {
        imv = (ImageView) findViewById(R.id.imv);
        radAnswer = (RadioGroup) findViewById(R.id.radAnswer);
    }// End of ini

    public void ClickAnswer(View view) {
        checkChooseAnswer();
    }
    private void checkScore(){
        intUserChooseArray[intTime] = intUserChoose;
        Log.d("nerv","intUserChooseArray ["
        +String.valueOf(intTime)
        + "] = "+String.valueOf(intUserScore));
        if (intUserChooseArray[intTime]==intTrueAnswerArray[intTime]){
            intUserScore++;
        }
        Log.d("nerv","intUserScore = "+ String.valueOf(intUserScore));
    }

    public void checkChooseAnswer() {
        if (strAnswer != null) {
            Log.d("nerv", "strAnswer = " + strAnswer);
            sentValueToQuestion();
            checkScore();
            intTime++;
        } else {
            Log.d("nerv", "Please Choose Something");

            objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.NoChooseEveryThing(MainActivity.this);
        }

    }
}
