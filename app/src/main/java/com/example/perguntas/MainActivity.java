package com.example.perguntas;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import org.angmarch.views.NiceSpinner;
import tyrantgit.explosionfield.ExplosionField;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> listAux =  new ArrayList<>();
    Jogo jogo;
    Jogo jogar;
    ConstraintLayout layout;
    String player;
    List<String> auxiliar;
    List<Integer> auxiliar_int;
    ListaPerguntas a;
    Jogos abc ;
    int ver_se_entra_again;
    String id_aux;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseJogos;
    private FirebaseDatabase firebase ;
    Button b,c,matchHistory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_chooser);

        layout = findViewById(R.id.main_animation);

        ver_se_entra_again=0;

        player= "Joao";
        Helper.getInstance(getApplicationContext()).setNomePlayer(player);

        //FirebaseApp.initializeApp(getApplicationContext());

        mDatabaseRef = FirebaseHelper.getInstance(this).getDatabaseReference();
        mDatabaseJogos = FirebaseHelper.getInstance(this).getDatabaseJogos();


        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                abc = dataSnapshot.getValue(Jogos.class);
                System.out.println(abc.toString());
                Helper.getInstance(getApplicationContext()).setJogos(abc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //mDatabase = firebase.getReference("jogos");

        final NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("Any Category","General Knowledge", "Sports", "Geography", "Mythology","Video Games"));
        niceSpinner.attachDataSource(dataset);

        final NiceSpinner niceSpinner2 = (NiceSpinner) findViewById(R.id.nice_spinner_two);
        List<String> dataset2 = new LinkedList<>(Arrays.asList("Easy", "Medium", "Hard"));
        niceSpinner2.attachDataSource(dataset2);
        final ExplosionField explosionField = ExplosionField.attach2Window(this);

        b = (Button) findViewById(R.id.start);
        c = (Button) findViewById(R.id.procurar);
        matchHistory = (Button) findViewById(R.id.match_history);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                explosionField.explode(b);
                        int aux2 = niceSpinner.getSelectedIndex();
                        int aux = niceSpinner2.getSelectedIndex();
                        String dificuldade = "";
                        String categoria = "";

                        switch (aux2){
                            case 0: categoria="";break;
                            case 1: categoria="9";break;
                            case 2: categoria="21";break;
                            case 3: categoria="22";break;
                            case 4: categoria="20";break;
                            case 5: categoria="15";break;
                        }
                        switch (aux){
                            case 0 : dificuldade="easy";break;
                            case 1 : dificuldade="medium";break;
                            case 2 : dificuldade="hard";break;
                        }
                        //procurarJogo();
                        Services.getInstance(getApplicationContext()).getQuestion(sucessListener,errorListener,"2",categoria,dificuldade,"multiple");
      }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //explosionField.explode(c);
                //historico();
                procurarJogo();
                //Services.getInstance(getApplicationContext()).getQuestion(sucessListener,errorListener,"2",categoria,dificuldade,"multiple");
            }
        });

        matchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historico();
            }
        });
    }

    Response.Listener<ListaPerguntas> sucessListener = new Response.Listener<ListaPerguntas>() {
        @Override
        public void onResponse(ListaPerguntas response) {
            System.out.println(response);
            for(int i=0;i<response.results.size();i++){
                try{
                    byte[] data = Base64.decode(response.results.get(i).category, Base64.DEFAULT);
                    String text = new String(data, "UTF-8");
                    response.results.get(i).category=text;
                }catch(UnsupportedEncodingException e){}
                try{
                    byte[] data = Base64.decode(response.results.get(i).difficulty, Base64.DEFAULT);
                    String text = new String(data, "UTF-8");
                    response.results.get(i).difficulty=text;
                }catch(UnsupportedEncodingException e){}
                try{
                    byte[] data = Base64.decode(response.results.get(i).type, Base64.DEFAULT);
                    String text = new String(data, "UTF-8");
                    response.results.get(i).type=text;
                }catch(UnsupportedEncodingException e){}
                try{
                    byte[] data = Base64.decode(response.results.get(i).correctAnswer, Base64.DEFAULT);
                    String text = new String(data, "UTF-8");
                    response.results.get(i).correctAnswer=text;
                }catch(UnsupportedEncodingException e){}
                try{
                    byte[] data = Base64.decode( response.results.get(i).question, Base64.DEFAULT);
                    String text = new String(data, "UTF-8");
                    response.results.get(i).question=text;
                }catch(UnsupportedEncodingException e){}
                for(int y=0;y<response.results.get(i).incorrectAnswers.size();y++){
                    try{
                        byte[] data = Base64.decode( response.results.get(i).incorrectAnswers.get(y), Base64.URL_SAFE);
                        String text = new String(data, "UTF-8");
                        response.results.get(i).incorrectAnswers.set(y,text);
                    }catch(UnsupportedEncodingException e){
                        Log.e("error",""+e);
                    }
                }
            }

            System.out.println(response.toString());

            player= "Joao";

            List<String> aux = new ArrayList<>();
            List<Integer> aux2 = new ArrayList<>();
            aux.add(player);
            aux2.add(0);

            jogar = new Jogo();
            jogar.questions=response.results;
            jogar.players=aux;
            jogar.scores=aux2;


            //String id = mDatabaseRef.push().getKey();
            //mDatabaseRef.child(id).setValue(jogar);
            int sizeListaJogos = abc.jogos.size();

            //DatabaseReference ref = mDatabaseJogos.child(size22+"");
            FirebaseHelper.getInstance(getApplicationContext()).insertInto(mDatabaseJogos.child(sizeListaJogos+""),jogar);


            FirebaseHelper.getInstance(getApplicationContext()).setIdJogo(sizeListaJogos+"");
            FirebaseHelper.getInstance(getApplicationContext()).setCriouJogo(true);
            //Helper.getInstance(getApplicationContext()).setFireDatabase(mDatabase);
            Helper.getInstance(getApplicationContext()).setLista(response);
            Helper.getInstance(getApplicationContext()).setIndex(0);
            Helper.getInstance(getApplicationContext()).setScore(0);
            Helper.getInstance(getApplicationContext()).setTime(0);

            Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            }
        };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("ERROR");
        }
    };



    public void procurarJogo(){
        FirebaseHelper helperFB = FirebaseHelper.getInstance(getApplicationContext());
        Helper helper = Helper.getInstance(getApplicationContext());
        for(int i =1;i<abc.jogos.size();i++){
            System.out.println("frifrjfri");
            if (abc.jogos.get(i).players.size() == 1 && (!abc.jogos.get(i).players.get(0).equals(player))){
                helperFB.setIdJogo(i+"");
                helperFB.setCriouJogo(false);
                helperFB.insertInto(mDatabaseJogos.child(i+"").child("players").child("1"),"Joao");
                helperFB.insertInto(mDatabaseJogos.child(i+"").child("scores").child("1"),0);
                a= new ListaPerguntas();
                a.results= abc.jogos.get(i).questions;
                helper.setLista(a);
                helper.setIndex(0);
                helper.setScore(0);
                helper.setTime(0);

                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
        }
    }

    public void historico(){
        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
        intent.putExtra("metodo",true);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        emptyBackStack();
        super.onBackPressed();
    }

    public void emptyBackStack(){
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; i++) {
            fm.popBackStack();
        }
    }
}
