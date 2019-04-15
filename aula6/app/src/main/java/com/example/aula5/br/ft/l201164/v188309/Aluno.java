package com.example.aula5.br.ft.l201164.v188309;

public class Aluno {

    private String nome, ra, miniCV;
    private double altura;

    private int foto,idade,time;

    public Aluno(String nome, String ra, int foto, String miniCV, double altura, int idade, int time) {
        this.nome = nome;
        this.ra = ra;
        this.miniCV = miniCV;
        this.altura = altura;
        this.foto = foto;
        this.idade = idade;
        this.time = time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getMiniCV() {
        return miniCV;
    }

    public void setMiniCV(String miniCV) {
        this.miniCV = miniCV;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
