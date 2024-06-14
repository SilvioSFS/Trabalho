package com.example.trabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EsqueceuSenha extends AppCompatActivity {
    EditText emailRecuperacao;
    FirebaseAuth trocarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);
        iniciar();
        trocarSenha = ConfiguraBD.Fireautenticacao();
    }

    public void iniciar() {
        emailRecuperacao = findViewById(R.id.EmailRecuperacao);
    }

    public void TrocarSenha(View v) {
        String email = emailRecuperacao.getText().toString();

        if (!TextUtils.isEmpty(email)) {
            trocarSenha.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(EsqueceuSenha.this, "Link de recuperação enviado para seu email", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(EsqueceuSenha.this, Login.class);
                    startActivity(in);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EsqueceuSenha.this, "Ocorreu um erro, tente novamente: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}