package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testeregistro.R;

public class CardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
    }

    public void abreAddMeioPagamento(View view) {
        Intent i = new Intent(this, AddMeioPagamentoActivity.class);
        startActivity(i);
    }
}