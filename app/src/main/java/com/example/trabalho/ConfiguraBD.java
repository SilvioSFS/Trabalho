package com.example.trabalho;
import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraBD {

    private static FirebaseAuth auth;


    public static FirebaseAuth Fireautenticacao(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}