package com.example.brcomboricagrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.brcomboricagrid.models.Card;
import com.example.brcomboricagrid.util.AppConstants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CreateCardActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nomeCard;
    EditText tipoCard;
    EditText manaNumberCard;
    Button cadastrarBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        getSupportActionBar().hide();

        nomeCard = (EditText) findViewById(R.id.cardName);
        tipoCard = (EditText) findViewById(R.id.cardType);
        manaNumberCard = (EditText) findViewById(R.id.numberMana);
        cadastrarBtn = (Button) findViewById(R.id.cadastrarBtn);

        cadastrarBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(valid()) {

            Log.i(this.getClass().getName(), "Inicio fluxo create card");
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            Gson gson = new Gson();

            final String saveData = gson.toJson(new Card(nomeCard.getText().toString(), Integer.parseInt(manaNumberCard.getText().toString()), tipoCard.getText().toString()));

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.getFullRoute(AppConstants.CREATE_CARD_ROUTE), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i(this.getClass().getName(), "MILESTONE: CHEGOU NO RESPONSE");
                        if(response != null) {
                            JSONObject responseJson = new JSONObject(response);

                            if(responseJson.getString("status").equals("success")) {
                                Toast.makeText(CreateCardActivity.this, "Cadastro realizado com Sucesso !", Toast.LENGTH_LONG).show();
                            } else {
                                Log.i(this.getClass().getName(), response);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CreateCardActivity.this, "Erro ao Salvar!", Toast.LENGTH_LONG).show();
                    Log.i(this.getClass().getName(), "MILESTONE: CHEGOU NO ERROR RESPONSE");
                    Log.i(this.getClass().getName(), error.getMessage());
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

        } else {
            Toast.makeText(CreateCardActivity.this, "Favor inserir as informações obrigatórias! ", Toast.LENGTH_LONG).show();
        }
    }

    private Boolean valid() {
        Boolean valid = true;

        if(nomeCard.getText().toString().isEmpty() || nomeCard.getText().toString() == null)
            valid = false;
        if(tipoCard.getText().toString().isEmpty() || tipoCard.getText().toString() == null)
            valid = false;
        if(manaNumberCard.getText().toString().isEmpty() || manaNumberCard.getText().toString() == null)
            valid = false;

        return valid;
    }
}