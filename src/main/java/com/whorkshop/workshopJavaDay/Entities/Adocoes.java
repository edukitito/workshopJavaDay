package com.whorkshop.workshopJavaDay.Entities;

import java.util.Date;

import com.whorkshop.workshopJavaDay.Entities.Enums.EtapaAdocao;
import com.whorkshop.workshopJavaDay.Entities.Enums.StatusAdocao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Adocoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animais animais;

    @OneToOne
    @JoinColumn(name = "ong_id", referencedColumnName = "ongid")
    private Ongs ong;

    @Column(name = "data_adocao")
    private Date data;

    @Enumerated(EnumType.STRING)
    private EtapaAdocao etapaAdocao;

    @Enumerated(EnumType.STRING)
    private StatusAdocao statusAdocao;

    public Adocoes(){}

    public Adocoes(Integer id, Users users, Animais animais, Ongs ong, Date data, EtapaAdocao etapaAdocao,
            StatusAdocao statusAdocao) {
        this.id = id;
        this.users = users;
        this.animais = animais;
        this.ong = ong;
        this.data = data;
        this.etapaAdocao = etapaAdocao;
        this.statusAdocao = statusAdocao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Animais getAnimais() {
        return animais;
    }

    public void setAnimais(Animais animais) {
        this.animais = animais;
    }

    public Ongs getOng() {
        return ong;
    }

    public void setOng(Ongs ong) {
        this.ong = ong;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public EtapaAdocao getEtapaAdocao() {
        return etapaAdocao;
    }

    public void setEtapaAdocao(EtapaAdocao etapaAdocao) {
        this.etapaAdocao = etapaAdocao;
    }

    public StatusAdocao getStatusAdocao() {
        return statusAdocao;
    }

    public void setStatusAdocao(StatusAdocao statusAdocao) {
        this.statusAdocao = statusAdocao;
    }

    
}
