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


    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        ProgressBar playerBar1 = findViewById(R.id.scoreBar1);
        ProgressBar playerBar2 = findViewById(R.id.scoreBar2);

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

                int s1 = 0;
                int s2 = 0;
                if (snapshot != null && snapshot.exists()) {
                    scoreJ1.setText(snapshot.getData().get("scoreJ1").toString());
                    scoreJ2.setText(snapshot.getData().get("scoreJ2").toString());
                    s1+=Integer.parseInt((String) scoreJ1.getText());
                    s2+=Integer.parseInt((String) scoreJ2.getText());
                    Log.i("score bar j1", String.valueOf(s1));
                    setMaxBar(s1, s2, playerBar1, playerBar2);
                    playerBar1.setProgress(s1);
                    playerBar2.setProgress(s2);
                    Log.i("Listen réussie", "Current data: " + snapshot.getData());
                } else {
                    Log.i("Listen NULL", "Current data: null");
                }
            }
        });

        TextView gameTime= findViewById(R.id.gameTime);

         // Timer
         new CountDownTimer(6000, 1000) {
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
    }
}