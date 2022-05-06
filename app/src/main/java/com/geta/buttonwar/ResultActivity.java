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
    TextView result1;
    TextView result2;
    TextView ptJ1;
    TextView ptJ2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int PtJoueur1 = getIntent().getIntExtra("scoreJ1", 0);
        int PtJoueur2 = getIntent().getIntExtra("scoreJ2", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        button = findViewById(R.id.replay);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        ptJ1 = findViewById(R.id.pointJ1);
        ptJ2 = findViewById(R.id.pointJ2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> party = new HashMap<>();
                party.put("dateParty", LocalDateTime.now());
                party.put("dureeParty", 15);
                party.put("scoreJ1", 0);
                party.put("scoreJ2", 0);
                party.put("scoreJ3", 0);
                db.collection("Partie")
                        .add(party)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.i("added", "DocumentSnapshot added with ID: ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("TAG", "Error adding document", e);
                            }
                        });
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ptJ1.setText(Integer.toString(PtJoueur1) + " pts");
        ptJ2.setText(Integer.toString(PtJoueur2) + " pts");
        if (PtJoueur1< PtJoueur2){
            result1.setText("Joueur 2 a gagné");
        }else if(PtJoueur1> PtJoueur2){
            result1.setText("Joueur 1 a gagné");
        }else{
            result2.setText("Egalité");
        }

    }
}