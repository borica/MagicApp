package com.example.brcomboricagrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining cards
    private CardView cardCreateCard;
    private CardView cardListCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Instanciating cards
        cardCreateCard = (CardView) findViewById(R.id.cardCreateCard);
        cardListCard = (CardView) findViewById(R.id.cardListCard);

        //Add onclick event
        cardCreateCard.setOnClickListener(this);
        cardListCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.cardCreateCard:
                i = new Intent(this, CreateCardActivity.class);
                startActivity(i);
                break;
            case R.id.cardListCard:
                i = new Intent(this, ListCardActivity.class);
                startActivity(i);
                break;
            default: break;
        }
    }
}