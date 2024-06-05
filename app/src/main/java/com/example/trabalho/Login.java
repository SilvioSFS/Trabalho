package com.example.trabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class Login extends AppCompatActivity {
    Usuario usuario;
    EditText campoEmail, camposSnha;
    Button Acessar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = ConfiguraBD.Fireautenticacao();
        inicializarComponente();

    }
    public void Entrar(View view) {
        String email = campoEmail.getText().toString();
        String senha = camposSnha.getText().toString();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha); //

                login(usuario);
            } else {
                Toast.makeText(this, "Preencher a senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o e-mail", Toast.LENGTH_SHORT).show();
        }
    }
    public void login(Usuario usuario){


        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Login.this, PaginaInicial.class);
                    startActivity(intent);
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não está cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email ou senha incorreto";
                    } catch (Exception e) {
                        excecao = "Erro ao logar: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(Login.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void inicializarComponente() {
        campoEmail = findViewById(R.id.TextLogin);
        camposSnha = findViewById(R.id.textSenha);
        Acessar = findViewById(R.id.buttonEntrar);
    }
    public void Cadastro(View v){
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }
    public void EsqueceuSenha(View v){
        Intent intent = new Intent(Login.this, EsqueceuSenha.class);
        startActivity(intent);
    }

}