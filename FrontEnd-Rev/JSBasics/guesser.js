"use strict";
let correctHexCode;
let allHexCodes;

// generates the correct hexcode, then adds it in a random
// spot into the "allHexCodes" array which is also random ones
setup();

/*
    your job: create a div for each element in the
    "allHexCodes" array and set its color to the
    hexCode. this means that only one div will have
    the correct hexcode and the others will be random
    colors.
    as you create them, add an event listener to each
    div which will call "incorrectAnswer" if the color
    is not the correct one, and "correctAnswer" if it is.
    ALSO, make sure to append each div to the "guesses"
    div that is in the HTML.
*/
function createDivs() {
  let div = document.getElementById('guesses');
 //let correctHexCode = allHexCodes[];
    //let guessColor = allHexCodes[correctHexCode];
   for(let i =0; i<5 ; i++ ){
    let innerDiv = document.createElement("div");
    innerDiv.style.backgroundColor = allHexCodes[i].toString();
    div.appendChild(innerDiv);  
     if(correctHexCode == innerDiv.addEventListener("click", correctAnswer)){
         correctAnswer();
         

     }else if(correctHexCode != innerDiv.addEventListener("click", incorrectAnswer)){
         incorrectAnswer();
     }
    
    //("click", function(){
       
    //     let clickedDiv = this.style.backgroundColor;
    //     if(clickedDiv==correctHexCode.toString()){
    //         correctAnswer();
    //     } else{
    //         incorrectAnswer();
         
    //     }
    //     console.log(clickedDiv);
    // }) == correctHexCode){
    //     correctAnswer();
    // }else if(innerDiv.addEventListener("click",incorrectAnswer) == incorrectAnswer){
    //     incorrectAnswer();
    // }
    
   }
   

}

function setup() {
    let generatedHexCode = document.getElementById('generated-hexcode');
    correctHexCode = generateNewHexCode();
    generatedHexCode.innerText = correctHexCode;

    allHexCodes = [];
    let correctIndex = Math.floor(Math.random() * 5);
    allHexCodes[correctIndex] = correctHexCode;
    for (let i=0; i<5; i++) {
        if (i !== correctIndex) {
            allHexCodes[i] = generateNewHexCode();
        }
    }
}

function generateNewHexCode() {
    let hexcode = '#';
    for (let i = 0; i < 6; i++) {
        let letterOrNumber = Math.floor(Math.random() * 2);
        if (letterOrNumber === 0)
            hexcode += '' + Math.floor(Math.random() * 10);
        else {
            hexcode += '' + String.fromCharCode(Math.floor(Math.random() * 6) + 65);
        }
    }
    return hexcode;
}

function incorrectAnswer() {
    alert('Sorry, that\'s not the right one! Try again.');
}

function correctAnswer() {
    alert('You got it! Nice job!');
    setup();
}
