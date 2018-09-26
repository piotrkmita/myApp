package com.example.piotr.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int sum;
    int points;
    int total;
    boolean isFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goClick(View view){

        TextView goTextView = findViewById(R.id.goTextView);
        goTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);

        setUpGame();

    }

    private void setQuest() {
        TextView questTextView = findViewById(R.id.questTextView);
        int number1=randomize();
        int number2=randomize();
        sum=number1+number2;
        questTextView.setText(number1+" + "+number2);
    }

    public void onClickAnswer(View view){
        if(isFinished){

        }else {
            TextView textView = (TextView) view;
            if (textView.getText().equals(Integer.toString(sum))) {
                points++;
            }

            total++;

            setQuest();
            setRandomAnswers();
            setCorrectAnswer();

            TextView scoreTextView = findViewById(R.id.scoreTextView);
            scoreTextView.setText(points + " / " + total);
        }

    }

    public int randomize(){
        Random r = new Random();
        int number = r.nextInt(100) + 1;
        return number;
    }

    public void setCorrectAnswer(){
        Random r = new Random();
        int number = r.nextInt(3);
        String resourceName = "textView"+number;
        int resourceID = getResources().getIdentifier(resourceName, "id", getPackageName());
        TextView textView = (TextView) findViewById(resourceID);
        textView.setText(Integer.toString(sum));

    }

    public void setRandomAnswers(){ //int i
        for(int i=0;i<4;i++) {
            String resourceName = "textView" + i;
            int resourceID = getResources().getIdentifier(resourceName, "id", getPackageName());
            TextView textView = (TextView) findViewById(resourceID);
            textView.setText(Integer.toString(randomize()));
        }
    }

    public void newGame(View view){
        Button playButton = findViewById(R.id.playButton);
        TextView doneTextView = findViewById(R.id.doneTextView);
        doneTextView.setText("Done");
        doneTextView.setVisibility(View.INVISIBLE);
        playButton.setText("Play again");
        playButton.setVisibility(View.INVISIBLE);
        setUpGame();
    }

    public void setUpGame(){
        isFinished=false;
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("0/0");

        final Button playButton = findViewById(R.id.playButton);
        final TextView doneTextView = findViewById(R.id.doneTextView);
        final TextView timerTextView = findViewById(R.id.timerTextView);

        new CountDownTimer(30000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000+"s"));
            }

            @Override
            public void onFinish() {
                doneTextView.setText("Done");
                doneTextView.setVisibility(View.VISIBLE);
                playButton.setText("Play again");
                playButton.setVisibility(View.VISIBLE);
                isFinished=true;
            }

        }.start();

        setQuest();
        setRandomAnswers();
        setCorrectAnswer();
    }
}
