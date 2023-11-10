package com.example.testeregistro.Util;

public class ConfiguraBd {
    private static FirebaseAuth auth;
    public static FirebaseAuth Firebaseautenticacao() {
        if(auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
