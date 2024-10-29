package com.trabalhojava.sistemarpg.model;

public class PersonagemSistema {
    private int personagemId;
    private int sistemaId;
    private int racaId;
    private int classeId;
    private int hp;
    private int nivel;
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;

    public PersonagemSistema() {}

    public PersonagemSistema(int personagemId, int sistemaId, int racaId, int classeId, int hp, int nivel,
                             int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma) {
        this.personagemId = personagemId;
        this.sistemaId = sistemaId;
        this.racaId = racaId;
        this.classeId = classeId;
        this.hp = hp;
        this.nivel = nivel;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
    }

    public int getSistemaId() {
        return sistemaId;
    }

    public int getClasseId() {
        return classeId;
    }

    public int getRacaId() {
        return racaId;
    }

    public int getPersonagemId() {
        return personagemId;
    }

    public int getHp() {
        return hp;
    }

    public int getNivel() {
        return nivel;
    }

    public int getForca() {
        return forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setSistemaId(int sistemaId) {
        this.sistemaId = sistemaId;
    }

    public void setPersonagemId(int personagemId) {
        this.personagemId = personagemId;
    }

    public void setRacaId(int racaId) {
        this.racaId = racaId;
    }

    public void setClasseId(int classeId) {
        this.classeId = classeId;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public String toString() {
        return "Personagem_Sistema {personagemId=" + personagemId +
                ", sistemaId = " + sistemaId +
                ", racaid = " + racaId +
                ", classeId = " + classeId +
                ", hp = " + hp +
                ", nivel = " + nivel +
                ", forca = " + forca +
                ", destreza = " + destreza +
                ", constituicao = " + constituicao +
                ", inteligencia = " + inteligencia +
                ", sabedoria = " + sabedoria +
                ", carisma = " + carisma + "}";
    }
}
