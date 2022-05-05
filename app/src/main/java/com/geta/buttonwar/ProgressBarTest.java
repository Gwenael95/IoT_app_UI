package com.geta.buttonwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Map;

public class ProgressBarTest extends AppCompatActivity {
    int scorePlayer1 =0;
    int scorePlayer2 =0;

    private void setMaxBar(int scorePlayer1, int scorePlayer2, ProgressBar playerBar1, ProgressBar playerBar2){
        if (scorePlayer1 > scorePlayer2) {
            playerBar2.setMax(scorePlayer1);
            playerBar1.setMax(scorePlayer1);
            //scoreP2.setText(scorePlayer2 + "/" + playerBar2.getMax());
        } else if (scorePlayer1 < scorePlayer2) {
            playerBar2.setMax(scorePlayer2);
            playerBar1.setMax(scorePlayer2);
            //scoreP1.setText(scorePlayer1 + "/" + playerBar1.getMax());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar_test);

        //Player1 component
        TextView scoreP1= findViewById(R.id.scoreP1);
        ProgressBar playerBar1 = findViewById(R.id.scoreBar1);
        Button player1= findViewById(R.id.player1);

        //Player2 component
        TextView scoreP2= findViewById(R.id.scoreP2);
        ProgressBar playerBar2 = findViewById(R.id.scoreBar2);
        Button player2= findViewById(R.id.player2);

        scorePlayer1 = playerBar1.getProgress();
        scorePlayer2 = playerBar2.getProgress();


        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePlayer1 +=1;
                setMaxBar(scorePlayer1, scorePlayer2, playerBar1, playerBar2);
                playerBar1.setProgress(scorePlayer1);
                scoreP1.setText(scorePlayer1+"/Score Bare gauge");


            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePlayer2 +=1;
                setMaxBar(scorePlayer1, scorePlayer2, playerBar1, playerBar2);
                playerBar2.setProgress(scorePlayer2);
                scoreP2.setText(scorePlayer2+"/Score Bare gauge");


            }
        });


    }

}