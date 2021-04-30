package com.example.perguntas;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaPerguntas {

    @SerializedName("response_code")
    @Expose
    public Integer responseCode;
    @SerializedName("results")
    @Expose
    public List<Perguntas> results = null;

}
