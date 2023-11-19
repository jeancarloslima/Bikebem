package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testeregistro.R;

public class CardsActivity extends AppCompatActivity {
    TextView campoCartao1, campoCartao2, campoCartao3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        inicializar();
        adicionaCartoes();
    }

    private void inicializar() {
        campoCartao1 = findViewById(R.id.textCard1);
        campoCartao2 = findViewById(R.id.textCard2);
        campoCartao3 = findViewById(R.id.textCard3);
    }

    private void adicionaCartoes() {

    }

    public void abreAddMeioPagamento(View view) {
        Intent i = new Intent(this, AddMeioPagamentoActivity.class);
        startActivity(i);
    }
}