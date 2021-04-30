package com.example.perguntas;


import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class RecicleAdapter extends RecyclerView.Adapter<RecicleAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<Jogo> jogosAcabados;
    public String player;


    public RecicleAdapter(Context context, ArrayList<Jogo> jogos, String player) {
        this.player = player;
        this.context = context;
        jogosAcabados =  jogos;
    }


    @NonNull
    @Override
    //inflate the row layout from xml when needed
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recicler_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        if(jogosAcabados.get(i).players.get(1).equals(player)){
            String aux = jogosAcabados.get(i).players.get(0);
            jogosAcabados.get(i).players.set(0,jogosAcabados.get(i).players.get(1));
            jogosAcabados.get(i).players.set(1,aux);

            int aux2 = jogosAcabados.get(i).scores.get(0);
            jogosAcabados.get(i).scores.set(0,jogosAcabados.get(i).scores.get(1));
            jogosAcabados.get(i).scores.set(1,aux2);
        }

        int aux, aux2;
        aux = jogosAcabados.get(i).scores.get(0);
        aux2 = jogosAcabados.get(i).scores.get(1);
        holder.playerName1.setText(jogosAcabados.get(i).players.get(0));
        holder.playerName2.setText(jogosAcabados.get(i).players.get(1));
        String p1 = aux+"";
        String p2 = aux2+"";
        holder.scoreP1.setText(p1);
        holder.scoreP2.setText(p2);
        if (aux > aux2) {
            holder.resultadoP1.setText("Winner");
            holder.linearP1.setBackgroundColor(ContextCompat.getColor(context,R.color.green));
            holder.resultadoP2.setText("Loser");
            holder.linearP2.setBackgroundColor(ContextCompat.getColor(context,R.color.red));
        } else if (aux2 > aux) {
            holder.resultadoP2.setText("Winner");
            holder.linearP2.setBackgroundColor(ContextCompat.getColor(context,R.color.green));
            holder.resultadoP1.setText("Loser");
            holder.linearP1.setBackgroundColor(ContextCompat.getColor(context,R.color.red));
        } else {
            holder.resultadoP1.setText("Tie");
            holder.resultadoP2.setText("Tie");
        }
    }

    @Override
    public int getItemCount() {
        return jogosAcabados.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView playerName1, playerName2, scoreP1, scoreP2, resultadoP1, resultadoP2;
        private LinearLayout linearP1, linearP2;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.playerName1 = itemView.findViewById(R.id.nome_jogador);
            this.playerName2 = itemView.findViewById(R.id.nome_jogador2);
            this.scoreP1 = itemView.findViewById(R.id.texto_score);
            this.scoreP2 = itemView.findViewById(R.id.texto_score2);
            this.resultadoP1 = itemView.findViewById(R.id.resultado);
            this.resultadoP2 = itemView.findViewById(R.id.resultado2);
            this.linearP1 = itemView.findViewById(R.id.linear_jogador1);
            this.linearP2 = itemView.findViewById(R.id.linear_jogador2);
        }
    }
}