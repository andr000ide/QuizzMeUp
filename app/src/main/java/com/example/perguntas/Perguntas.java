package com.example.perguntas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Perguntas {

    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("difficulty")
    @Expose
    public String difficulty;
    @SerializedName("question")
    @Expose
    public String question;
    @SerializedName("correct_answer")
    @Expose
    public String correctAnswer;
    @SerializedName("incorrect_answers")
    @Expose
    public List<String> incorrectAnswers = null;

}