package com.example.testeregistro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class RegistroActivity extends AppCompatActivity {
    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoEmail, campoSenha;
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
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }

    private void validarCampos() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {
                    usuario = new Usuario();

                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    cadastrarUsuario();
                } else {
                    Toast.makeText(this, "Preencha a seha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            }
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
                    Toast.makeText(RegistroActivity.this, "Seucesso", Toast.LENGTH_SHORT).show();
                } else {
                    String excecao = "";
                    
                    try{
                        throw task.getException();
                    } catch(FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte";
                    } catch(FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Digite um email válido";
                    } catch(FirebaseAuthUserCollisionException e) {
                        excecao = "Esta conta já existe";
                    } catch(Exception e) {
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(RegistroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}