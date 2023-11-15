package com.example.testeregistro.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testeregistro.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class HomeActivity extends AppCompatActivity {

    Button btn_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_scan = findViewById(R.id.buttonScan);
    }

    /*public void abrirScan(View view) {
        Intent i = new Intent(this, ScanActivity.class);
        startActivity(i);
    }*/

    public void scanCode(View view) {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Escaneie o código de uma bicicleta");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(ScanActivity.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null) {
            String strResult = result.getContents().toString();
            if(strResult.equals("40028922")) {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                abrirBike();
            } else {
                Toast.makeText(this, "Insira um código válido", Toast.LENGTH_SHORT).show();
            }
        }
    });

    public void abrirCode(View view) {
        Intent i = new Intent(this, CodeActivity.class);
        startActivity(i);
    }

    private void abrirBike() {
        Intent i = new Intent(this, BikeActivity.class);
        startActivity(i);
    }

    public void abrePerfil(View view) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
}