package com.geta.buttonwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Button button;
    TextView resulat;
    int PtJoueur1 = 100;
    int PtJoueur2 = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
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
            resulat.setText("égalité");
        }

    }
}