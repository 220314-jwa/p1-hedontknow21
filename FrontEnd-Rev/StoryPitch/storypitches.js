let pitchesTable = document.getElementById('pitchesTable');
checkLogin().then(sendRequest);

async function sendRequest() {
    // sending basic GET request to /pitches
    let httpResponse = await fetch('http://localhost:8080/pitches');

    if (httpResponse && httpResponse.status===200) {
        let responseBody = await httpResponse.json();
        showPitches(responseBody);
    }
}

function showPitchess(petsArr) {
    petsTable.innerHTML = `<tr>
    <th>ID</th>
    <th>Tentative Title</th>
    <th>Exp Completion</th>
    <th>Length</th>
    <th>OneSentenceBlurb</th>
    <th>Description</th>
    <th>Submit</th>
    </tr>`;
    
    // for each pitch in the pitches array from the http response
    for (let pitch of pitchesArr) {
        // these pitches are coming from Java so the fields are the same
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${pitch.id}</td>
            <td>${pitch.tentative_title}</td>
            <td>${pitch.exp_completion_date}</td>
            <td>${pitch.length_type}</td>
            <td>${pitch.one_sentence_blurb}</td>
            <td>${pitch.description}</td>
            <!-- the ID of the submit button will be "submit" plus the pitch's ID so that
            we can get the ID later and know which pitch the user is trying to submit -->
            <td><button type="button" id="submit${pitch.id}">Submit</submit></td>`;
        
            // add the row to the table
        pitchesTable.appendChild(row);
        let submitButton = document.getElementById('submit' + pitch.id);
        submitButton.addEventListener('click', submitPitch);
    }
}

async function submitPitch() {
    let pitchId = event.target.id;
    pitchId = pitchId.slice(5); // removes the "submit" part of the button ID leaving just the pitch ID
    let messageBox = document.getElementById('messageBox');

    let userJSON = JSON.stringify(loggedInUser);
    if (loggedInUser) {
        let httpResponse = await fetch('http://localhost:8080/pitches/' + pitchId + '/submitted',
            {method:'PUT', body:userJSON});
        if (httpResponse && httpResponse.status===200) {
            messageBox.innerText = 'Pitch submitted successfully.';
            loggedInUser = await httpResponse.json();
            await sendRequest();
        } else {
            messageBox.innerText = 'Something went wrong, please try again.';
        }
    } else {
        messageBox.innerText = 'You have to be logged in to submit a pitch. (You may need to refresh the page.)';
    }
}
