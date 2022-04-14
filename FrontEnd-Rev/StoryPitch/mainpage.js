let loggedInUser;
let nav = document.getElementsByTagName('nav')[0];
let logInButton;

async function checkLogin(){
    let usersId = sessionStorage.getItem('Auth-Token');
    loggedOutNavBar()
    let httpResponse = await fetch('http://localhost:8080/users/' + usersId);
    if(httpResponse && httpResponse.status === 200) {
        loggedInUser = await httpResponse.json();
        loggedInNavBar();
    }else{
        loggedOutNavBar();
    }
}

function loggedInNavBar() {
    nav.innerHTML = `<ul id="navList">
        <li><a href="index.html">StoryPitchApp</a></li>
        <li>&#128214;</li>
        <li><a href="authors.html">Author Page</a></li>
        <li>&#128214;</li>
        <li><a href="editors.html">Editor Page</a></li>
        <li>&#128214;</li>
    </ul>
    <form id="loginWindow">
        <span id="nameDisplay" style="padding-right:10px"></span>
        <button type="button" id="logInBtn">Log Out</button>
    </form>`;
    logInButton = document.getElementById('logInButton');
    logInButton.addEventListener('click', logOut);
    document.getElementById('nameDisplay').innerText = loggedInUser.firstName;
}



function loggedOutNavBar(){
    nav.innerHTML = `<ul id ="navList">
    <li><a href="index.html">StoryPitchApp</a><li>
    <li>&#128214;<li>
    <li><a href="authors.html">AuthorPage</a><li>
    <li>&#128214;<li>
    <li><a href="editors.html">EditorPage</a></li>
    <li>&#128214;</li>
    <ul>

    <form id+"logInWindow">
    <label for="usernameLogin">Username:</label><input type="text" id="usernameLogin">&nbsp;
    <label for="passwordLogin">Password: </labe><input type="password" id="passwordLogin">
    <button type="button" id="logInButton">Log In</button>
    </form>`;
    logInButton = document.getElementById('logInButton');
    logInButton.addEventListener('click', logIn);
}

async function logIn() {
    let userCredentials = {
        username:document.getElementById('usernameLogin').value,
        password:document.getElementById('passwordLogin').value
    };

    let credentialsJSON = JSON.stringify(userCredentials);

    let httpResponse = await fetch('http://localhost8080/auth',
    {method:'POST', body:credentialsJSON});
    if(httpResponse && httpResponse.status === 200){
        loggedInUser = await httpResponse.json();
        sessionStorage.setItem('Auth-Token', loggedInUser.id);
        await checkLogin();
        loggedInNavBar();
    }
}

function logOut(){
    sessionStorage.removeItem('Auth-Token');
    loggedInUser = null;
    loggedOutNavBar();
}