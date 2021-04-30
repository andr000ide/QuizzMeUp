package com.example.perguntas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    ConstraintLayout layout ;
    ArrayList<Integer> aux2 ;
    int score;
    DatabaseReference mDatabase;
    String id_aux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        layout = findViewById(R.id.result_main);

        Helper a = Helper.getInstance(getApplicationContext());
        score = a.getScore();
        TextView aux = findViewById(R.id.colocarScore);
        //String tempo = Helper.getInstance(getApplicationContext()).getTime()+"";
        //aux.setText(tempo);
        aux.setText(score+"");
        id_aux = FirebaseHelper.getInstance(getApplicationContext()).getIdJogo();
        //aux2.add(10);
        //aux2.set(0,10);


        //mDatabase = FirebaseHelper.getInstance(getApplicationContext()).getFireDatabase();

        mDatabase  = FirebaseHelper.getInstance(getApplicationContext()).getDatabaseJogos();

        DatabaseReference db = mDatabase.child(id_aux).child("scores");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean verificador = FirebaseHelper.getInstance(getApplicationContext()).getCriouJogo();
                aux2 =  (ArrayList<Integer>) dataSnapshot.getValue();
                // se nao criou o jogo
                if(verificador==false){
                        mDatabase.child(id_aux).child("scores").child("1").setValue(score);
                }
                // se criou o jogo
                else{
                        mDatabase.child(id_aux).child("scores").child("0").setValue(score);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button button = findViewById(R.id.voltar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doSomething();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });

    }


    public void doSomething(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("aux","Notfirst");
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layout.startAnimation(animation);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
