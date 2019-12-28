/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

window.addEventListener('load', function () {

    let doctor = JSON.parse(getCookie("doctor"));

    let table = document.getElementById("info");

    console.log(getCookie("doctor"));

    function process(array) {

        for (let key in array) {

            console.log(key);

            let value = array[key];

            console.log(typeof (value));

            if (typeof (value) === "object") {
                process(value);
                continue;
            }

            let element = document.getElementById(key);

            if (!element) {
                console.log("Unable to find element for " + key);
                continue;
            }

            element.innerText = value;

            // // Add row
            // let row = table.insertRow(index);
            //
            // // Add columns
            // let fieldName = row.insertCell(0);
            // fieldName.innerHTML = key;
            // fieldName.setAttribute("class", "field");
            //
            // row.insertCell(1).innerHTML = value;
            //
            // index++;
        }
    }

    process(doctor);

});
