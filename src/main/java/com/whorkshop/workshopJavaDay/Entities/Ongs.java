package com.whorkshop.workshopJavaDay.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ongs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ongid;
    private String nome;
    private String descricao;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String cnpj;
    private String pix;
    private String senha;
    private Boolean ativo;

    public Ongs(){}

    public Ongs(Integer ongid, String nome, String descricao, String email, String telefone, String endereco,
            String cidade, String estado, String cnpj, String pix, String senha, Boolean ativo) {
        this.ongid = ongid;
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cnpj = cnpj;
        this.pix = pix;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Integer getOngid() {
        return ongid;
    }

    public void setOngid(Integer ongid) {
        this.ongid = ongid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
