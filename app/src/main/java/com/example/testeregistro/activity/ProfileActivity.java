package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testeregistro.R;
import com.example.testeregistro.model.Usuario;

public class ProfileActivity extends AppCompatActivity {

    TextView campoNome, campoEmail, campoTelefone;
    RegistroActivity registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        inicializaCampos();
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.textPerfilNome);
        campoEmail = findViewById(R.id.textPerfilEmail);
        campoTelefone = findViewById(R.id.textPerfilTelefone);
    }

    public void abreCards(View view) {
        Intent i = new Intent(this, CardsActivity.class);
        startActivity(i);
    }
}