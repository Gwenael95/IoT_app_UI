package com.geta.buttonwar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    TextView resulat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int PtJoueur1 = getIntent().getIntExtra("scoreJ1", 0);
        int PtJoueur2 = getIntent().getIntExtra("scoreJ2", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        button = findViewById(R.id.replay);
        resulat = findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        if (PtJoueur1< PtJoueur2){
            resulat.setText("Joueur 2 a gagné");
        }else if(PtJoueur1> PtJoueur2){
            resulat.setText("Joueur 1 a gagné");
        }else{
            resulat.setText("égalitéé");
        }

    }
}