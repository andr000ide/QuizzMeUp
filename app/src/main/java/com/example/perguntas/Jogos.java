package com.example.perguntas;

import com.example.perguntas.Jogo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Jogos {

    @SerializedName("jogos")
    @Expose
    public List<Jogo> jogos = null;
}
