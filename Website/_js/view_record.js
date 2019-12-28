/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */


function loadPage() {

    console.log(getCookie("record"));

    let record = JSON.parse(getCookie("record"));

    document.getElementById("patientName").innerText = record.patient.firstName + " " + record.patient.lastName;
    document.getElementById("patientId").innerText = record.patient.patientId;
    document.getElementById("patientEmail").innerText = record.patient.emailAddress;

    document.getElementById("onsetDate").innerText = record.encounterTime;
    document.getElementById("illnessCause").innerText = record.eventToIllness;

    document.getElementById("painArea").innerHTML = "<option>" + record.sitePain + "</option>";
    document.getElementById("painSeverity").innerHTML = "<option>" + record.severityPain + "</option>";
    document.getElementById("painCharacter").innerHTML = "<option>" + record.painCharacter + "</option>";
    document.getElementById("painPattern").innerHTML = "<option>" + record.patternPain + "</option>";

    document.getElementById("bleedingArea").innerHTML = "<option>" + record.siteBleed + "</option>";
    document.getElementById("bleedingSeverity").innerHTML = "<option>" + record.severityBleed + "</option>";

    record.symptoms.forEach(function (value) {
        document.getElementById("symptoms").innerHTML += "<input type=\"checkbox\" name=\"symptoms\"/ checked" +
            " disabled> " + value + "<br/>";
    });

    document.getElementById("diagnosis").innerHTML = record.diagnosis;

    record.bloodTest.forEach(function (value) {
        document.getElementById("requestedBloodTests").innerHTML += "<option>" + value + "</option>";
    });

    record.patientScans.forEach(function (value) {
        document.getElementById("requestedScans").innerHTML += "<option>" + value + "</option>";
    });

    record.departmentTransfer.forEach(function (value) {
        document.getElementById("departmentSelection").innerHTML += "<option>" + value + "</option>";
    });

    record.patientMedication.forEach(function (value) {
        document.getElementById("medicationSelection").innerHTML += "<option>" + value + "</option>";
    });

}

document.addEventListener("DOMContentLoaded", function (event) {
    loadPage();
});