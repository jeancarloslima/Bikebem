package com.example.testeregistro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testeregistro.R;

import java.util.Locale;

public class ConfirmPaymentActivity extends AppCompatActivity {
    private TextView campoTimer;
    private CountDownTimer timer;
    private int time = 3600000;
    private int restantTime = 3600000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        campoTimer = findViewById(R.id.textCampoTimer);
        iniciarTimer();
    }

    private void iniciarTimer() {
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long horas = (millisUntilFinished / 1000) / 3600;
                long minutos = ((millisUntilFinished / 1000) % 3600) / 60;
                long segundos = (millisUntilFinished / 1000) % 60;
                String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", horas, minutos, segundos);
                campoTimer.setText(timeFormatted);
                restantTime -= 1000;
            }

            @Override
            public void onFinish() {
                campoTimer.setText("00:00:00");
                Toast.makeText(ConfirmPaymentActivity.this, "Tempo esgotado", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void adicionaTempo(View view) {
        time = restantTime + 3600000;
        restantTime += 3600000;
        paraTempo();
        iniciarTimer();
    }

    private void paraTempo() {
        timer.cancel();
    }

}