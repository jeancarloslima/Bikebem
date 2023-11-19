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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {
    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoEmail, campoSenha, campoTelefone;
    Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
    }

    private void inicializar() {
        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);
        campoTelefone = findViewById(R.id.editTextPhone);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }

    public void validarCampos(View v) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        String telefone = campoTelefone.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {
                    if (!telefone.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        usuario.setTelefone(telefone);
                        cadastrarUsuario();
                    } else {
                        Toast.makeText(this, "Preencha o telefone", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguraBd.Firebaseautenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    salvarDadosUsuario();
                    abrirHome();
                } else {
                    String excecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Digite um email válido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Esta conta já existe";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(RegistroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void salvarDadosUsuario() {
        DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        String idUsuario = autenticacao.getCurrentUser().getUid();
        DatabaseReference usuarios = referenciaFirebase.child("usuarios").child(idUsuario);

        usuarios.setValue(usuario);
    }

    private void abrirHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
