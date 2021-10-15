package com.example.appn1;

public class Cadastro {

    public int id;

    public String nome, editora, descricao;


    public Cadastro() {

    }

    public Cadastro(String nome, String editora, String descricao) {
        this.nome = nome;
        this.editora = editora;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return   nome + "  |  " + editora + "  |  " + descricao ;
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

    public String getDescricao(){ return descricao; }

    public void setDescricao (String descricao) { this.descricao = descricao; }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}