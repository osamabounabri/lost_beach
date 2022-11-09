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
public class Prenotazione {

    private int id;
    private String cliente;
    private String orario;
    private Date data;
    private int posti;
    private int slot;
    private Date data_di_prenotazione;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Date getData_di_prenotazione() {
        return data_di_prenotazione;
    }

    public void setData_di_prenotazione(Date data_di_prenotazione) {
        this.data_di_prenotazione = data_di_prenotazione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
