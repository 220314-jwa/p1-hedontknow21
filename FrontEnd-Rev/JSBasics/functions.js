// Looking at functions and what they can do


basic();
console.log(basicReturn());

function basic(){
    console.log("Hello World");
};



function basicReturn(){
    return "Different";
}

console.log(sum(444,555,666,777,888));
// default values in the function parameters
function sum(a=1,b=2){
    console.log("This is only 2 numbers being added ");
    return a + b;
}



// REST parameters
function sum(...rest){
    console.log("This is a bunch of numbers being added");
    console.log(rest);
    let total = 0;
    for(item of rest){
        total += item
    }
    return total;
}
// var makes hoisting bring it to the top
// const ,  , x is global access is dangerous public to the user
// Use let it makes a blocked scoped

// blocked scope
//{
//    x = 2;
//}
//console.log(x);

const helloThere = {
    message: "hello there",
    amount: 5
    //printer: function() {
      //  for (let i = 0; i < helloThere.amount; i++){
      //      console.log(helloThere.message);
      //  }
    //}
    
};

//helloThere.printer();

function runThid(func, a, b){
    //callback function
    func(a,b);
}

//arrow function are a shorthand anonymous functions
// let summer = function(){console.log("hello");}
let summer = (word) => {console.log(word)};

summer("Hello");
summer("asdaf");

let test = (a = 123, b = 22, ...rest) =>{
    console.log(a + b + rest.reduce((previous, current) => previous + current));

};
// creating a iteration loop of adding the previous then current number being added and so on
test(1, 35, 4);

let a = function(x){
    return x = 100;
}
let b = (a) => {
    let x = 100;
    return x + 100;
}

let c = (x) => x + 100;

let d = x => x + 100;

console.log(d(45));

//Example of use functional programming

let names =["Gary", "Mike", "Barbara"];

function upper(names){
    let arr = [];
    for(item in names){
        arr.push(item.toUpper());
    }
    return arr;
}

console.log(upper(names));

// Conditional boilerplate reduction
// Truthy and falsy
'Hello' // truthy
if(1){} // truthy
new Boolean(false); // truthy because object is always true
new String('') ; // is truthy values because of object

let isMorning = false;
let isNoon = true;
let isEvening = false;

// Version 1
function greetPerson(){
    if (isMorning === true && isNoon === false && isEvening === false){
        console.log("Good Morning");
    }else if (isMorning === false && isNoon === true && isEvening === false){
        console.log("Good Afternoon");
    }else if (isMorning === false && isNoon === false && isEvening === true){
        console.log("Good Evening");
    }else{
        console.log("Hi there");
    }
}


// Version 2
function greetPersonBetter(){
    if (isMorning === true){
        console.log("Good Morning");
    }else if (isNoon === true){
        console.log("Good Afternoon");
    }else if (isEvening === true){
        console.log("Good Evening");
    }else{
        console.log("Hi there");
    }
}

// Version 3
function greetPersonEvenBetter(){
    if (isMorning){
        console.log("Good Morning");
    }else if (isNoon){
        console.log("Good Afternoon");
    }else if (isEvening){
        console.log("Good Evening");
    }else{
        console.log("Hi there");
    }
}

greetPerson();
greetPersonBetter();
greetPersonEvenBetter();

// These will all evaluate to Falsy
console.log(Boolean(false));
console.log(Boolean(0));
console.log(Boolean(-0));
console.log(Boolean(""));
console.log(Boolean(null));
console.log(Boolean(undefined));
console.log(Boolean(NaN));

// Everything else will evaluate to Truthy
console.log(Boolean("0"));
console.log(Boolean([]));
console.log(Boolean({}));

// Only use === to check values are equal not true or false
console.log(5 === '5');
