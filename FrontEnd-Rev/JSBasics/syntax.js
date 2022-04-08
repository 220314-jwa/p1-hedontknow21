// Line comments

/*
Block comments
*/

"use strict"; // See Line 83

/* Functions */
function helloLog(){
    console.log('Hello from helloLog');
}

function logMessage(message){
    console.log(message);
}

helloLog();
logMessage('Hello from logMessage argument');
logMessage(1234567890);
logMessage(20/5);
logMessage(8 == '8'); // True (coercion)
logMessage(8 === '8'); //False
logMessage(null);
var m; //Undefined
logMessage(m);

function logMessageFromInput() {
    /* JavaScript is dynamically-typed. */
    var message = 5;
    message = true;
    message = document.getElementById('messageInput').value;
    console.log(message);
}

var logBtn = document.getElementById('logBtn');
/* Two different ways of adding an event Listener */
logBtn.addEventListener('click', logMessageFromInput);
//LogBtn.onclick = LogMessageFromInput

/* Object and Arrays */
var obj = {
    'id':1,
    'name':'Revature',
    'favoriteColor':'orange',
    'isObject':true
};
var arr = [1, 'Revature', 'orange', true];

arr[10] = 'hello';

console.log(obj);
console.log(arr);
console.log(arr[6]);
console.log(typeof obj + ',' + typeof arr);

var obj2 = {
    'firstName':'Hello',
    'lastName':'World',
    'fullName':function(){
        console.log(this.firstName + '' + this.lastName);
    }
}

obj2.fullName();
obj2.firstName = 'Goodbye';
obj2.fullName();

// Scopes
var globalVar = 'I\m a global-scope variable';

function myFunction(){
    // var functionVar = 'i\m' a function-scope variable.'

    /* ES6+ added block scope */
    if(true){
        let blockVar = 'I\m a block-scope variable.';
        const blockConst = 'I\m also a block-scope variable.';
        // blockConst = 'something else'; // throws an error

    }

    // functionVar = ''; // will throw an error in strict mode

    console.log(functionVar);
    var functionVar = 'I\m a function-scope variable.'

}

// /* Immediately InvokeFunction Expression (IIFE) */
//(function(){ console.log('Hello from the IIFE')}); when the function is in-cased with parentheses it becomes an IIFE
//var returnVal = (function() {return 'IIFE result'});
// console.log(returnVal);

// x = "Hello"; // If  "use strict" is used at the top of the file , this will throw an error in the console
myFunction(); // will throw an error in strict mode

/* Arrow Functions */
logBtn.addEventListener('click', () =>{
    console,log('called the callback function that we made with the arrow notation')
});

let myArrowFunction = msg =>{
    console.log('calling the arrow function')
    console.log('msg is: ' + msg);
};

myArrowFunction('my message');
myArrowFunction();