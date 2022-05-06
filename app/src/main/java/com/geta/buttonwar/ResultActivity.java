package com.geta.buttonwar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    Button button;
    TextView result;
    TextView ptJ1;
    TextView ptJ2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int PtJoueur1 = getIntent().getIntExtra("scoreJ1", 0);
        int PtJoueur2 = getIntent().getIntExtra("scoreJ2", 0);
        final MediaPlayer endMp = MediaPlayer.create(this, R.raw.theend);
        endMp.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        button = findViewById(R.id.replay);
        result = findViewById(R.id.result1);
        ptJ1 = findViewById(R.id.nbrPt1);
        ptJ2 = findViewById(R.id.nbrPt2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ptJ1.setText(Integer.toString(PtJoueur1) + " pts");
        ptJ2.setText(Integer.toString(PtJoueur2) + " pts");
        if (PtJoueur1< PtJoueur2){
            result.setText("Joueur 2 a gagné");
        }else if(PtJoueur1> PtJoueur2){
            result.setText("Joueur 1 a gagné");
        }else{
            result.setText("Egalité");
        }

    }
}