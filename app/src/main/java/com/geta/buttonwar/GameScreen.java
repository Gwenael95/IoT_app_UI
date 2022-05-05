package com.geta.buttonwar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                    TextView scoreJ1;
                    scoreJ1 = findViewById(R.id.score1);
                    scoreJ1.setText(snapshot.getData().get("scoreJ1").toString());
                    TextView scoreJ2;
                    scoreJ2 = findViewById(R.id.score2);
                    scoreJ2.setText(snapshot.getData().get("scoreJ2").toString());
                    Log.i("Listen réussie", "Current data: " + snapshot.getData());
                } else {
                    Log.i("Listen NULL", "Current data: null");
                }
            }
        });


    }
}