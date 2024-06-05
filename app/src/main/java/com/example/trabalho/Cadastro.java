package com.example.trabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializar();
    }
    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoEmail, camposSnha;
    Button botaoCadastrar;

    public void inicializar() {
        campoNome = findViewById(R.id.TextLogin);
        campoEmail = findViewById(R.id.TextEmail);
        camposSnha = findViewById(R.id.TextSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }
    public void Salvar(View view) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String pass = camposSnha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!pass.isEmpty()) {

                    usuario = new Usuario();
                    usuario.setUsuario(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(pass);


                    cadastrarUsuario();

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
        autenticacao = ConfiguraBD.Fireautenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Cadastro.this, "Sucesso ao Cadastrar o usuario", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Cadastro.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Cadastro.this, "Erro, Tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}