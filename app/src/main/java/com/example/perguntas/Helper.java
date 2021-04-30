package com.example.perguntas;

import android.content.Context;
import com.google.firebase.database.DatabaseReference;

public class Helper {
    private static Helper mInstance;
    private static Context context;
    public ListaPerguntas lista;
    public DatabaseReference mDatabase;
    public String nomePlayer;
    public int index;
    public Jogos jogos;
    public int score;
    public int time;

    private Helper(Context context) {
        this.context = context;
    }

    public static synchronized Helper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Helper(context);
        }
        return mInstance;
    }

    public String getNomePlayer() { return nomePlayer; }

    public void setNomePlayer(String nomePlayer) { this.nomePlayer = nomePlayer; }

    public void setLista(ListaPerguntas lista ){
        this.lista=lista;
    }

    public void setJogos(Jogos jogos){this.jogos=jogos;}

    public Jogos getJogos(){ return jogos;}

    public void setTime(int time){
        this.time=time;
    }

    public int getTime(){
        return time;
    }

    public ListaPerguntas getLista(){
        return lista;
    }

    public void setIndex(int aux){
        this.index=aux;
    }

    public int getIndex(){
        return index;
    }

    public void setScore(int aux){
        this.score=aux;
    }

    public int getScore(){
        return score;
    }

    ColocarPergunta delegate = null;

    public void callSomething(Fragment1 frag){
        //quando acabar
        delegate = frag;
        delegate.novaPergunta();
    }

}
