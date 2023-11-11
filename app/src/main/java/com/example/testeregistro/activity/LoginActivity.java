package com.example.testeregistro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testeregistro.R;
import com.example.testeregistro.Util.ConfiguraBd;
import com.example.testeregistro.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    EditText campoEmail, campoSenha;
    Button botaoacessar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = ConfiguraBd.Firebaseautenticacao();
        inicializarComponentes();
    }

    public void validarAutenticacao(View view){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()) {
            if(!senha.isEmpty()) {
                Usuario usuario = new Usuario();

                usuario.setEmail(email);
                usuario.setSenha(senha);

                logar(usuario);
            } else {
                Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
        }
    }

    private void logar(Usuario usuario) {

        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirHome();
                } else {
                    String excecao = "";
                    try{
                        throw task.getException();
                    } catch(FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não está cadastrado";
                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email ou senha incorreto";
                    } catch(Exception e) {
                        excecao = "Erro ao logar " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    private void inicializarComponentes(){
        campoEmail = findViewById(R.id.editTextEmailLogin);
        campoSenha = findViewById(R.id.editTextSenhaLogin);
        botaoacessar = findViewById(R.id.buttonAcessar);
    }
}