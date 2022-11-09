/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.model;

import java.util.Date;

/**
 *
 * @author fpw
 */
public class Slot {

    private int id;
    private String orario;
    private Date data;
    private int posti;
    private String bagnino;

    public String getBagnino() {
        return bagnino;
    }

    public void setBagnino(String bagnino) {
        this.bagnino = bagnino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

}
