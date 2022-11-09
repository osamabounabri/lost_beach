/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





var utenti = document.querySelectorAll("#listaNomiCognomi li"), suggerimenti =  [];

for(var i = 0; i < utenti.length; i++){
    
    suggerimenti.push(utenti[i].innerHTML);
    
    
}




