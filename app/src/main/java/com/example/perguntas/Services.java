package com.example.perguntas;


import android.content.Context;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Services {
    private static Services serviceInstance;
    private static Context context;
    private static Gson gson;

    public Services(Context context) {
        this.context = context;
        this.gson = new GsonBuilder().create();
    }

    public static synchronized Services getInstance(Context context) {
        if (serviceInstance == null) {
            serviceInstance = new Services(context);
        }
        return serviceInstance;
    }

    public void getQuestion(Response.Listener<ListaPerguntas> sucessoListener, Response.ErrorListener errorListener, String amount,String category, String difficulty, String type) {
        String endpoint = EndPoints.ENDPOINT + "amount=" + amount + "&category="+ category + "&difficulty="+ difficulty + "&type=" + type+"&encode=base64";
        GsonRequest<ListaPerguntas> request = new GsonRequest<>(gson, endpoint, ListaPerguntas.class, sucessoListener, errorListener);
        AppSingleton.getInstance(context).addToRequestQueue(request);
    }

}
