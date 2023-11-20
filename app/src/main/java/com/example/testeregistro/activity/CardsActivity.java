package com.example.testeregistro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testeregistro.R;
import com.example.testeregistro.model.Cartao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CardsActivity extends AppCompatActivity {
    TextView campoCartao1;
    ImageView imageCartao1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        inicializar();
        adicionaCartoes();
    }

    private void inicializar() {
        campoCartao1 = findViewById(R.id.textCard1);
        imageCartao1 = findViewById(R.id.imageCartao1);
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
                        imageCartao1.setImageResource(R.drawable.ic_card_green);
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
}