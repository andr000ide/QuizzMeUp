package com.example.perguntas;


import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class GsonRequest<T> extends Request<T> {
    private final Gson gson;
    private final Class<T> clazz;
    private final Response.Listener<T> listener;

    public GsonRequest(Gson gson, String url, Class<T> clazz, Response.Listener<T> successListener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = successListener;
        this.gson = gson;
        this.clazz = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
