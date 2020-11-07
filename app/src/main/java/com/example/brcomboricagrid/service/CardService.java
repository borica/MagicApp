package com.example.brcomboricagrid.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.brcomboricagrid.models.Card;
import com.example.brcomboricagrid.util.AppConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CardService {

    RequestQueue requestQueue;
    Context mContext;
    //RequestQueue queue;

    public CardService(Context context) {
        this.mContext = context;
    }

    public void saveCard2(Card card) {

        Log.i(this.getClass().getName(), "Inicio fluxo create card");
        requestQueue = Volley.newRequestQueue(mContext);

        Gson gson = new Gson();

        final String saveData = gson.toJson(card);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.CREATE_CARD_ROUTE), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i(this.getClass().getName(), "MILESTONE: CHEGOU NO RESPONSE");
                    if(response != null) {
                        JSONObject responseJson = new JSONObject(response);
                        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, new String("Response IS NULL"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(this.getClass().getName(), "MILESTONE: CHEGOU NO ERROR RESPONSE");
                Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public String getBodyContentType() {return "application/json; charset=utf-8";}

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "9fc982d0-8c55-4ec6-81d8-7efa5410b0bf");

                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return saveData == null ? null : saveData.getBytes("utf-8");
                } catch (UnsupportedEncodingException e){
                    return null;
                }
            }
        };

        Log.i(this.getClass().getName(), "MILESTONE: ADICIONANDO A STRING REQUEST NA REQUEST QUEUE  ");
        requestQueue.add(stringRequest);
    }



    public Boolean saveCard(Card card) {

        final boolean[] status = {false};

        Gson gson = new Gson();

        JSONObject requestJson = null;
        JsonObject gsonJSON = new JsonParser().parse(gson.toJson(card).toString()).getAsJsonObject();

        try {
            requestJson = new JSONObject(gsonJSON.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(mContext);

        //Make POST Request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.CREATE_CARD_ROUTE), requestJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(mContext, "Salvo com Sucesso!", Toast.LENGTH_LONG).show();
                status[0] = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage() != null){
                    Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "ERROR MESSAGE: IS NULL", Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "9fc982d0-8c55-4ec6-81d8-7efa5410b0bf");

                return headers;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

        return status[0];
    }

}
