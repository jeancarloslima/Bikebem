package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testeregistro.R;
import com.example.testeregistro.model.Cartao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMeioPagamentoActivity extends AppCompatActivity {

    EditText campoNumeroCartao, campoBandeiraCartao, campoDataVencimento, campoCVV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meio_pagamento);
    }

    public void cadastrarCartao(View view) {
        String numeroCartao = campoNumeroCartao.getText().toString();
        String bandeiraCartao = campoBandeiraCartao.getText().toString();

        DatabaseReference cartoesRef = FirebaseDatabase.getInstance().getReference().child("cartoes");
        Cartao novoCartao = new Cartao(bandeiraCartao, numeroCartao);
        cartoesRef.push().setValue(novoCartao);

        abrirBike();
    }

    private void inicializarCampos() {
        campoNumeroCartao = findViewById(R.id.editTextNumeroCartao);
        campoBandeiraCartao = findViewById(R.id.editTextBandeiraCartao);
        campoDataVencimento = findViewById(R.id.editTextNumeroVencimento);
        campoCVV = findViewById(R.id.editTextNumeroCVV);
    }

    private void abrirBike() {
        Intent i = new Intent(this, BikeActivity.class);
        startActivity(i);
    }
}