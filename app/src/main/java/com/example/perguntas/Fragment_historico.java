package com.example.perguntas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Fragment_historico extends Fragment {
    public Jogos arrayJogos;
    public RecyclerView.Adapter adapter;
    public Fragment_historico() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_historico, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_historico);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        arrayJogos = Helper.getInstance(getActivity().getApplicationContext()).getJogos();
        String player = Helper.getInstance(getActivity().getApplicationContext()).getNomePlayer();

        ArrayList<Jogo> aux = new ArrayList<>();

        for (int i = 0; i < arrayJogos.jogos.size(); i++) {
            if (arrayJogos.jogos.get(i).players.size() == 2 && arrayJogos.jogos.get(i).players.contains(player)) {
                aux.add(arrayJogos.jogos.get(i));
            }
        }

        adapter = new RecicleAdapter(getActivity(),aux,player);

        recyclerView.setAdapter(adapter);
        return view;
    }




}
