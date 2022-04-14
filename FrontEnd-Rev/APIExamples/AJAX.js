// JSON
// Javascript Object Notation

// Object Notation In JavaScript
const obj = {
    name: "Harry",
    age: 55

};

/**
 * Json version
 * {
 * "name": "Harry",
 * "age": "55"
 * 
 * }
 * 
 * JSon is a text-based open standard desinged for human
 * readable data interchange
 * It is also self describing
 * 
 * Syntax
 * - Data is in name /value pairs
 * -Data is seperated by commas
 * -Curly braces hold objects
 * -Square brackets hold arrays
 * 
 * {
 * "employees": [
 * {"firstname": "john", "lastname": "doe"},
 * {"firstname": "john", "lastname": "doe"},
 * {"firstname": "john", "lastname": "doe"},
 * {"firstname": "john", "lastname": "doe"},
 * 
 * ]
 * 
 * 
 * 
 * 
 * }
 * 
 */


// This is an example of text JSon
let textJson = '{"name": "Greg", "age":"55"}';


// In order to convert from json notation text to an object
// use JSON.parse()
let jsonObject = JSON.parse(textJson);

console.log(textJson);

console.log(jsonObject);
console.log(jsonObject.name);


//Asynchronous Javascript and XML
//AJAX
// used mostly for creating asynchronous applications
// web apps casn send and retrieve data from the server sync without interfering with the display
// and behaviot of the page

function loadJSON(){
    // the url that we are trying to hit
    const dataURL = `https://pokeapi.co/api/v2/pokemon/${randomNumber()}`;

    // This is a built in browser object
    let httpRequest = new XMLHttpRequest();
    //Prepare the method for the httpRequest
    httpRequest.open("GET", dataURL);
    //Send the httpRequest
    httpRequest.send();

    /**
 * What is the readyState and why does it change
 * 
 * 
    It holds the status of the XMLHttpRequest
    0 - request is not initialized
    1 - server connection established
        2 - request received
    3 - proccessing request
    4 - request is finished and response is ready



 */


    httpRequest.onreadystatechange = () => {
    if(httpRequest.readyState == 4) {
        // We will use the JSon parse to convert the response to an obj
        let dataObj = JSON.parse(httpRequest.responseText);
        addImage(dataObj);
    }
}
}

let button = document.createElement('button');
button.addEventListener('click',loadJSON);
button.innerText="Click Me";
let body = document.getElementsByTagName("body")[0];
body.appendChild(button);
console.log(button);



function addImage(data){
    let imgageHolder = document.getElementById("imageHolder");
    //dataObj.sprites.front.shiny
    let imageURL = data.sprites.front_shiny;
    let image = document.createElement("img");
    image.setAttribute('src', imageURL);
    imgageHolder.appendChild(image);
}


function randomNumber(){
    return Math.round(Math.random()*500);
}



// Example of using post request with Async
// async function postData(url = '', data = {}){
//     const response = await fetch(url,{
//         method: 'POST', // by default this is GET
//         mode: 'cors', // cross-origin, default this
//         credentials:'same-origin',
//         headers:{
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(data) // body data needS to match our header content type
//     });
//     return response.json();
// }

// async function getData(){
//     let url = 'http://pokeapi.co/api/v2/poekmon/2';
//     const response = await fetch(url);
//     return response.json();
// }

// let button2 = document.createElement('button');
// button2.addEventListener('click', getData);
// button2.innerText="Async Button";
// body.appendChild(button2);

