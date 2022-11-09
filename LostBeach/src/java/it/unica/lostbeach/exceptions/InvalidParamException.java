/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.exceptions;

public class InvalidParamException extends Exception {

    public InvalidParamException(String errorMessage) {

        super(errorMessage);//eccezione: da un messaggio di errore passato in input

    }

}
