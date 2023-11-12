package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testeregistro.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void abrirScan(View view) {
        Intent i = new Intent(this, ScanActivity.class);
        startActivity(i);
    }

    public void abrirCode(View view) {
        Intent i = new Intent(this, CodeActivity.class);
        startActivity(i);
    }
}