package com.geta.buttonwar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> party = new HashMap<>();
                party.put("dateParty", LocalDateTime.now());
                String duree = ((EditText)findViewById(R.id.editTextDuree)).getText().toString();
                party.put("dureeParty", Integer.parseInt(duree));
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
                Intent intent = new Intent(MainActivity.this, GameScreen.class);
                intent.putExtra("dureeParty", Integer.parseInt(duree));
                startActivity(intent);
            }
        });
    }
}