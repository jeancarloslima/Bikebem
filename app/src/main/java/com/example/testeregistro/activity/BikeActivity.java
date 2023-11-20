package com.example.testeregistro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.testeregistro.R;
import com.example.testeregistro.model.Cartao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BikeActivity extends AppCompatActivity {
    TextView campoCartao1;
    ImageView imageCartao1;
    RadioButton radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);
        inicializa();
        adicionaCartoes();
    }

    private void inicializa() {
        campoCartao1 = findViewById(R.id.textCartaoUm);
        imageCartao1 = findViewById(R.id.imageCartaoUm);
        radioButton1 = findViewById(R.id.radioButtonUm);
    }

    private void adicionaCartoes() {
        DatabaseReference cartoesRef = FirebaseDatabase.getInstance().getReference().child("cartoes");
        cartoesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cartao cartao = snapshot.getValue(Cartao.class);
                    if (cartao != null) {
                        String nomeBandeira = cartao.getNomeBandeira();
                        String numeroCartao = cartao.getNumeroCartao();
                        campoCartao1.setText(nomeBandeira + " - " + numeroCartao);
                        imageCartao1.setImageResource(R.drawable.ic_card);
                        int corNova = getResources().getColor(R.color.white);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            radioButton1.setButtonTintList(ColorStateList.valueOf(corNova));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erros, se necessário
            }
        });
    }

    public void abreAddMeioPagamento(View view) {
        Intent i = new Intent(this, AddMeioPagamentoActivity.class);
        startActivity(i);
    }

    public void abreConfirma(View view) {
        Intent i = new Intent(this, ConfirmPaymentActivity.class);
        startActivity(i);
    }
}