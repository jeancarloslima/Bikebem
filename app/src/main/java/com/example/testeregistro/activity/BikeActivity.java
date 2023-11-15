package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testeregistro.R;

public class BikeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);
    }

    public void abreCartoes(View view) {
        Intent i = new Intent(this, CardsActivity.class);
        startActivity(i);
    }

    public void abreConfirma(View view) {
        Intent i = new Intent(this, ConfirmPaymentActivity.class);
        startActivity(i);
    }
}