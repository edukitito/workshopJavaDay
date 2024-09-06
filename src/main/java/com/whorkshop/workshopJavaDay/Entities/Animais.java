package com.whorkshop.workshopJavaDay.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Animais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String raca;
    private String descricao;
    private int idade;
    private String tipo;
    private String sexo;
    private byte[] imagem;
    private String proprietarioTipo;
    private Integer proprietarioId;
    private Boolean ativo = true ;

    public Animais(){}

    public Animais(Integer id, String nome, String raca, String descricao, int idade, String tipo, String sexo,
            byte[] imagem, String proprietarioTipo, Integer proprietarioId, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.descricao = descricao;
        this.idade = idade;
        this.tipo = tipo;
        this.sexo = sexo;
        this.imagem = imagem;
        this.proprietarioTipo = proprietarioTipo;
        this.proprietarioId = proprietarioId;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getProprietarioTipo() {
        return proprietarioTipo;
    }

    public void setProprietarioTipo(String proprietarioTipo) {
        this.proprietarioTipo = proprietarioTipo;
    }

    public Integer getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(Integer proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
