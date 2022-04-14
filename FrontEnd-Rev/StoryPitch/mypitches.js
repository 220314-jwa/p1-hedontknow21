let pitchesTable = document.getElementById('pitchesTable');
let messageBox = document.getElementById('messageBox');

checkLogin().then(setMessageBox);

function setMessageBox() {
    if (loggedInUser) {
        if (loggedInUser.pitches && loggedInUser.pitches.length>0) {
            showPitches(loggedInUser.pitches);
        } else {
            messageBox.innerText = 'You don\'t seem to have any pitches. Create or submit one!';
        }
    } else {
        messageBox.innerText = 'You need to be logged in to view your pitches! (You may need to refresh the page.)';
    }
}

function showPitches(pitchesArr) {
    pitchesTable.innerHTML = `<tr>
    <th>ID</th>
    <th>Tentative Title</th>
    <th>ExpCompletion</th>
    <th>Length</th>
    <th>OneSenteceBlurb</th>
    <th>Description</th>
    </tr>`;
    
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
        `;
        // add the row to the table
        pitchesTable.appendChild(row);
    }
}
