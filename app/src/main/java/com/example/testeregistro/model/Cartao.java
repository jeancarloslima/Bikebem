package com.example.testeregistro.model;

public class Cartao {
    private String nomeBandeira;
    private String numeroCartao;

    public Cartao() {

    }

    public Cartao(String nomeBandeira, String numeroCartao) {
        this.nomeBandeira = nomeBandeira;
        this.numeroCartao = numeroCartao;
    }

    public String getNomeBandeira() {
        return nomeBandeira;
    }

    public void setNomeBandeira(String nomeBandeira) {
        this.nomeBandeira = nomeBandeira;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
