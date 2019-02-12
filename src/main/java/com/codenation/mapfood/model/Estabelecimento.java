package com.codenation.mapfood.model;

import java.math.BigDecimal;

public class Estabelecimento {

    private int id;
    private String nome;
    private String municipio;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String tipoPratos;

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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getTipoPratos() {
        return tipoPratos;
    }

    public void setTipoPratos(String tipoPratos) {
        this.tipoPratos = tipoPratos;
    }
}
