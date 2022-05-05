package com.geta.buttonwar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

public class GameScreen extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        TextView gameTime= findViewById(R.id.gameTime);
        ProgressBar playerBar1 = findViewById(R.id.playersBar1);
        TextView score1= findViewById(R.id.score1);
         Handler hdlr = new Handler();
         // Timer
         new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                gameTime.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                gameTime.setText("done!");
            }
        }.start();

         // Progress bar update
            i = playerBar1.getProgress();
            new Thread(new Runnable() {
                public void run() {
                    while (i < 100) {
                        i += 1;
                        // Update the progress bar and display the current value in text view
                        hdlr.post(new Runnable() {
                            public void run() {
                                playerBar1.setProgress(i);
                                score1.setText(i+"/"+playerBar1.getMax());
                            }
                        });
                        try {
                            // Sleep for 1000 milliseconds to show the progress slowly.
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

    }


    }




