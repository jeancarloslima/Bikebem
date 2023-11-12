package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testeregistro.R;

public class CodeActivity extends AppCompatActivity {

    EditText campoCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        inicializa();
    }

    public void verificaCodigo(View view) {
        String codigo = campoCodigo.getText().toString();

        if (!codigo.isEmpty()) {
            if (codigo.equals("40028922")) {
                abrirBike();
            } else {
                Toast.makeText(this, "Insira um código válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Insira um código de uma alguma bicicleta", Toast.LENGTH_SHORT).show();
        }

    }

    private void abrirBike() {
        Intent i = new Intent(this, BikeActivity.class);
        startActivity(i);
    }

    private void inicializa() {
        campoCodigo = findViewById(R.id.editTextCode);
    }
}