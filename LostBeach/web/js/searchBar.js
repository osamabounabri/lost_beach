/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const searchWrapper = document.querySelector(".search-input");
const inputBox = document.getElementById('ricerca');
const suggBox = searchWrapper.querySelector(".autocom-box");



inputBox.onkeyup = (e)=>{
    
    let userData = e.target.value;
    let emptyArray = [];
    if(userData){
        
        emptyArray = suggerimenti.filter((data)=>{
            
            return data.toLocaleLowerCase().startsWith(userData.toLocaleLowerCase());
            
        });
        
        emptyArray = emptyArray.map((data)=>{
            
           return data = '<li>'+ data +'</li>'; 
            
        });
        console.log(emptyArray);
        searchWrapper.classList.add("active");
        mostraSuggerimenti(emptyArray);
        let allList = suggBox.querySelectorAll("li");
        
        for(let i = 0; i < allList.length; i++){
            
            
            allList[i].setAttribute("onclick", "select(this)");
            
        }
        
    }else{
        
        searchWrapper.classList.remove("active");
        
    }
    
    
}

function select(element){
    
    let selectUserData = element.textContent;
    console.log(selectUserData);
    inputBox.value = selectUserData;
    
    
}

function mostraSuggerimenti(list){
    
    let listData;
    if(!list.length){
        
        userValue = inputBox.value;
        listData = '<li>'+ userValue +'</li>'
        
        
    }else{
        
        listData = list.join('');
        
    }
    
    suggBox.innerHTML = listData;
}