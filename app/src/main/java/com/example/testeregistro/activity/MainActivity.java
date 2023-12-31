package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testeregistro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mudaTelaRegistrar(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

    public void mudaTelaEntrar(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}