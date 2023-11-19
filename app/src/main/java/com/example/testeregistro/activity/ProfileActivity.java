package com.example.testeregistro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testeregistro.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView campoNome, campoTelefone;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        inicializaCampos();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Carregar informações do usuário automaticamente ao criar a tela
        carregarInformacoesUsuario();
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.textPerfilNome);
        campoTelefone = findViewById(R.id.textPerfilTelefone);
    }

    private void carregarInformacoesUsuario() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference currentUserDb = mDatabase.child("usuarios").child(userId);

            // Adicionar um ouvinte para recuperar informações do usuário do banco de dados
            currentUserDb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String nome = dataSnapshot.child("nome").getValue(String.class);
                        String telefone = dataSnapshot.child("telefone").getValue(String.class);

                        // Atualizar a interface do usuário com as informações recuperadas
                        campoNome.setText(nome);
                        campoTelefone.setText(telefone);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Tratar erro de leitura do banco de dados, se necessário
                    Toast.makeText(ProfileActivity.this, "Erro ao carregar informações do usuário.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void abreCards(View view) {
        Intent i = new Intent(this, CardsActivity.class);
        startActivity(i);
    }
}
