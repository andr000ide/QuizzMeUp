package com.example.perguntas;

import com.example.perguntas.Perguntas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    @SerializedName("score")
    @Expose
    public List<Integer> scores = null;
    @SerializedName("questions")
    @Expose
    public List<Perguntas> questions = null;
    @SerializedName("players")
    @Expose
    public List<String> players = null;

}
