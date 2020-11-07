package com.example.brcomboricagrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.brcomboricagrid.adapter.CardAdapter;
import com.example.brcomboricagrid.models.Card;
import com.example.brcomboricagrid.util.AppConstants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCardActivity extends AppCompatActivity {

    private ListView listCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
        getSupportActionBar().hide();

        listCards = (ListView) findViewById(R.id.listCards);
        mountCards();
    }

    private void mountCards() {

        Log.i(this.getClass().getName(), "Inicio fluxo LIST card");
        RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConstants.getFullRoute(AppConstants.LIST_CARD_ROUTE), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i(this.getClass().getName(), "MILESTONE: CHEGOU NO RESPONSE");

                        if(response != null) {
                            ArrayList<Card> mountedCards = new ArrayList<Card>();

                            //GETTING JSON OBJS FROM RESPONSE
                            JSONObject reponseJson = new JSONObject(response);
                            JSONArray cardsJSON = reponseJson.getJSONArray("cards");

                            for (int i = 0; i < cardsJSON.length(); i++) {
                                JSONObject card = cardsJSON.getJSONObject(i);

                                mountedCards.add(new Card(card.getInt("id"), card.getString("name"), card.getInt("qtd_mana"),card.getString("type")));
                            }

                            listCards.setAdapter(new CardAdapter(ListCardActivity.this, R.layout.list_row, mountedCards));
                        }
                        Log.i(this.getClass().getName(), "RESPONSE JSON: " + response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(this.getClass().getName(), "MILESTONE: CHEGOU NO ERROR RESPONSE");
                    Log.i(this.getClass().getName(), "ERROR MESSAGE: " + error.getMessage());
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

            };

        //Make Request
        requestQueue.add(stringRequest);
    }

}