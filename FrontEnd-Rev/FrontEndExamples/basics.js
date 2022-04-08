//Datatypes

//maintain strict coding with semi-colons ;

// Numbertypes
num = 55;
num2 = 44.4;

console.log(num)
console.log(num2)

// BigInt for large number proccessing
num3 = 7236296555677965n

// Booleans
bool = true;
bool = false;

//Null
emptyImplied = null;

//NaN (not a number)
notNumber = NaN;
// Usually happens due to bad code
console.log(5/"hello");

// Symbol
Sym = Symbol("Hello");
Sym2 = Symbol("Hello");

//Symbols are meant to be unique
console.log(Sym === Sym2); // this will be false


// Objects
// First you need to make a class
class Food{
    constructor(name, cost){
        this.name = name;
        this.cost = cost;
    }
}
// to make an object from a class use the keyword 'new' and the 'name' of the class
const Apple = new Food("Apple", 0.50);


    console.log(Apple);
    console.log(Apple.name);
    console.log(Apple.cost);


   

const Person = {
    firstName : "Greg",
    lastName : "Jones",
    age : 60
};

console.log(Person);
//Changing variables inside of object
Person.age = 40;
console.log(Person);

//Changing the types of the fields in a class is fine
Person.age = 54;
console.log(Person);

// Adding a field in a class can also happen
Person.hairColor = "Black";
console.log(Person);

// Functions are treated like objects in JS
// the syntax to declare a function is below
//not every function needs to return something


function sum(a,b){
    return a + b;
};
const banana = new Food("banana", 0.70);

console.log(sum(Apple.cost, banana.cost));

function diff(a,b){
    return a - b;
};

function printThis(a){
    console.log(a);
};

printThis(sum(Apple.cost, banana.cost));

//There are two ways to acces fields inside a object
// <objectNmae>.<fieldName>
// <objectName>["<fieldName>"]

//Arrays in JavaScript
const cars = ["Saab", "Volvo", "BMW"];
console.log(cars);

//longer way
// bad way of coding in js
const food = [];
food[0] = "bread";
food[1] = "curry";
food[2] = "chips";

// Instead of memorizing what the size of the array is
// push just adds it to the end of the array
food.push("chocolate");
food[food.length] = "Apple";

console.log(food);
console.log(typeof food);

food.push(Apple);
console.log(food);

// This is not readable
// You can do this , but other languages do not so it is not encouraged
const strangeArray = new Array("Dont do this");
console.log(strangeArray);

console.log(strangeArray.length);
console.log(strangeArray.sort());

// looping thru an array
//for loops in JS
// basic generic for loop as seen in Java
for(let i = 0; i < food.length; i++){
    console.log(food[i]);
}
// for each loop in JS
// for if prints out each value of array
for(obj of food){
    console.log(obj);
}

// for in prints out the key of the arrays
for(key in food){
    console.log(food);
}


// normal notation

let sss = 1;
// var lets you redeclare and reassign a variable
var g = 123;
var g = 11;

// let does not let you redeclare an variable but lets you reassign
let x = 11;


 x = 123;
//
 const y = 11;
 //y = 3234;

 //Scopes used to be an issue in JS as var let you redeclare variables even
 // in different scopes this meant a variable inside a function or method could reassign a variable 
 //  outside of it
 //UNINTENTIONALLY

 var replaceable = 4123;

 function varTest(){
     replaceable = 44;
     console.log(replaceable);
 }

 // I am calling the function here
 varTest();

 // I am printing the global variable here
 console.log(replaceable)

 // we use let because of runtime safety
 // It has block scope

 let letVar = "Goodbye";

 function showLetScope(){
     let letVar = "hello";
     console.log(letVar);
 }

 showLetScope();
 console.log(letVar);

 //Different types of scope
 // Global Scope, anything outside of a function or class  has global scope
 // Local scope/ Function scope
 // There used to be only function and global scope
 // Basically things within {} are not accessible outside of them unless explicit

 //There is another thing called Variable hoisting and function hoisting

 functionHoist();

 function functionHoist(){
     console.log("this is fine");
 }

 //Document Object Model
 //DOM manipulation
 // DOM traversal
 // Linking JS with HTML




