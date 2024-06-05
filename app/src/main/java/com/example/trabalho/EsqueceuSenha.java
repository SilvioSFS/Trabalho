package com.example.trabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class EsqueceuSenha extends AppCompatActivity {
    EditText emailRecuperacao;
    FirebaseAuth trocarSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);
        iniciar();
        trocarSenha =  ConfiguraBD.Fireautenticacao();
    }
    public void iniciar(){
        emailRecuperacao = findViewById(R.id.EmailRecuperacao);
    }
    public void TrocarSenha(View v){
        
    }

}