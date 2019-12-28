/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

function add() {

    // patientId onsetDate illnessCause painArea painSeverity painCharacter painPattern bleedingArea bleedingSeverity symptoms(checkboxes) diagnosis bloodTestSelection scanSelection departmentSelection medicationSelection

    let doctor = JSON.parse(getCookie("doctor"));


    let patientId = document.getElementById("patientId").value;

    let onsetDate = document.getElementById("onsetDate").value;
    let illnessCause = document.getElementById("illnessCause").value;

    let painArea = document.getElementById("painArea").value;
    let painSeverity = document.getElementById("painSeverity").value;
    let painCharacter = document.getElementById("painCharacter").value;
    let painPattern = document.getElementById("painPattern").value;

    let bleedingArea = document.getElementById("bleedingArea").value;
    let bleedingSeverity = document.getElementById("bleedingSeverity").value;

    let symptoms = [];
    let checkboxes = document.getElementsByName('symptoms');

    for (var i = 0, n = checkboxes.length; i < n; i++) {

        let checkbox = checkboxes[i];

        if (checkbox.checked === false) continue;

        symptoms.push(checkbox.value);
    }

    let diagnosis = document.getElementById("diagnosis").value;

    let bloodTestSelection = getChoices("bloodTestSelection");

    let scanSelection = getChoices("scanSelection");

    let departmentSelection = getChoices("departmentSelection");

    let medicationSelection = getChoices("medicationSelection");

    console.log(bloodTestSelection);

    console.log(symptoms);

    console.log(onsetDate + " " + illnessCause);

    Api.POST("Doctor", "addRecord", {
        patientId: patientId,
        doctorId: doctor.doctorId,
        encounterTime: onsetDate,
        eventToIllness: illnessCause,
        sitePain: painArea,
        severityPain: painSeverity,
        painCharacter: painCharacter,
        patternPain: painPattern,
        siteBleed: bleedingArea,
        severityBleed: bleedingSeverity,
        symptoms: JSON.stringify(symptoms),
        diagnosis: diagnosis,
        bloodTest: bloodTestSelection,
        departmentTransfer: departmentSelection,
        patientMedication: medicationSelection,
        patientScans: scanSelection
    }, function (response) {

        console.log(response);

        if (response.error) {
            alert(response.error);
        } else {
            alert("Record Added");
        }

    });
}

function getChoices(elementId) {

    let chosen = document.getElementById(elementId + "_chosen").getElementsByClassName("search-choice");

    let choices = [];

    for (let i = 0, n = chosen.length; i < n; i++) {
        let choice = chosen[i];
        let choiceValue = choice.getElementsByTagName("span")[0].innerText;
        choices.push(choiceValue);
    }

    return JSON.stringify(choices);
}

window.addEventListener('load', function () {
    $('#bloodTestSelection').chosen();
    $('#scanSelection').chosen();
    $('#medicationSelection').chosen();
    $('#departmentSelection').chosen();
});

