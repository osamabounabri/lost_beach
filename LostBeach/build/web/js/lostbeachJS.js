/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Controlli lunghezze input di registrazione */
document.getElementById('nome').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 50)
        if (!(key === 46 || key === 8))
            e.preventDefault();

};
document.getElementById('cognome').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 50)
        if (!(key === 46 || key === 8))
            e.preventDefault();

};
document.getElementById('cf').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 16)
        e.preventDefault();

};
document.getElementById('email').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 50)
        if (!(key === 46 || key === 8))
            e.preventDefault();

};
document.getElementById('num').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 10)
        e.preventDefault();

};
document.getElementById('regUsername').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 20)
        e.preventDefault();

};
document.getElementById('regPassword').onkeydown = function (e) {

    var key = e.keyCode || e.charCode;

    if (this.value.length > 15)
        e.preventDefault();

};

/* Messaggi di errore input di prenotazione*/

var slot = document.getElementById('prenotaSlot');
var avviso = document.createElement("p");
avviso.id = "messaggioErrore";
var testo = document.createTextNode("Parametro non valido");


function insertAfter(newNode, referenceNode) {

    referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);

}

function controllaInput() {

    if (slot.value < 1) {

        slot.style.color = "red";
        insertAfter(avviso, slot);

    }

}

slot.onchange = controllaInput;



