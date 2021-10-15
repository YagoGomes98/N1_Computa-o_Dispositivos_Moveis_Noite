package com.example.appn1;

public class Cadastro {

    public int id;

    public String nome, editora;


    public Cadastro() {

    }

    public Cadastro(String nome, String editora) {
        this.nome = nome;
        this.editora = editora;
    }

    @Override
    public String toString() {
        return   nome + "  |  " + editora ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}