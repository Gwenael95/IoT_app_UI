package com.geta.buttonwar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        TextView scoreJ1 = findViewById(R.id.score1);
        TextView scoreJ2 = findViewById(R.id.score2);

        final DocumentReference docRef = db.collection("Score").document("z5hykmgfjeS8BqryAojK");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.i("Listen Failed", "Listen failed.");
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    scoreJ1.setText(snapshot.getData().get("scoreJ1").toString());
                    scoreJ2.setText(snapshot.getData().get("scoreJ2").toString());
                    Log.i("Listen réussie", "Current data: " + snapshot.getData());
                } else {
                    Log.i("Listen NULL", "Current data: null");
                }
            }
        });

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
                Intent intent = new Intent(GameScreen.this, ResultActivity.class);
                intent.putExtra("scoreJ1", Integer.parseInt((String) scoreJ1.getText()));
                intent.putExtra("scoreJ2", Integer.parseInt((String) scoreJ2.getText()));
                startActivity(intent);
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