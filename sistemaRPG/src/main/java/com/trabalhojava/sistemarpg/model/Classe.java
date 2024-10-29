package com.trabalhojava.sistemarpg.model;

public class Classe {
    private int classeId;
    private String nomeClasse;
    private String descricao;
    private int hpInicial;
    private int hpNivel;
    private int nivelMax;
    private int sistemaId;

    public Classe() {}

    public Classe(int classeId, String nomeClasse, String descricao, int hpInicial,
                  int hpNivel, int nivelMax, int sistemaId) {
        this.classeId = classeId;
        this.nomeClasse = nomeClasse;
        this.descricao = descricao;
        this.hpInicial = hpInicial;
        this.hpNivel = hpNivel;
        this.nivelMax = nivelMax;
        this.sistemaId = sistemaId;
    }

    public int getClasseId() {
        return classeId;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getHpInicial() {
        return hpInicial;
    }

    public int getHpNivel() {
        return hpNivel;
    }

    public int getNivelMax() {
        return nivelMax;
    }

    public int getSistemaId() {
        return sistemaId;
    }

    public void setClasseId(int classeId) {
        this.classeId = classeId;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setHpInicial(int hpInicial) {
        this.hpInicial = hpInicial;
    }

    public void setHpNivel(int hpNivel) {
        this.hpNivel = hpNivel;
    }

    public void setNivelMax(int nivelMax) {
        this.nivelMax = nivelMax;
    }

    public void setSistemaId(int sistemaId) {
        this.sistemaId = sistemaId;
    }

    public String toString() {
        return "Classe {classeId=" + classeId +
                ", nomeClasse=" + nomeClasse +
                ", descricao=" + descricao +
                ", hpInicial=" + hpInicial +
                ", hpNivel=" + hpNivel +
                ", nivelMax=" + nivelMax +
                ", sistemaId=" + sistemaId + "}";
    }
}
