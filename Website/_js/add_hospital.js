/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

function add() {

    let hospitalId = document.getElementById("hospitalId").value;
    let hospitalName = document.getElementById("hospitalName").value;
    let hospContactNum = document.getElementById("hospContactNum").value;
    let hospAltContactNum = document.getElementById("hospAltContactNum").value;
    let contactEmailAddress = document.getElementById("contactEmailAddress").value;

    let street = document.getElementById("street").value;
    let city = document.getElementById("city").value;
    let country = document.getElementById("country").value;
    let zipCode = document.getElementById("zipCode").value;

    let hospRegistrationNum = document.getElementById("hospRegistrationNum").value;

    let hospManager = document.getElementById("hospManager").value;
    let hospManagerEmail = document.getElementById("hospManagerEmail").value;
    let hospManagerContact = document.getElementById("hospManagerContact").value;

    Api.POST("Hospital", "add", {
        hospitalId: hospitalId,
        hospitalName: hospitalName,
        hospContactNum: hospContactNum,
        hospAltContactNum: hospAltContactNum,
        contactEmailAddress: contactEmailAddress,
        street: street,
        city: city,
        country: country,
        zipCode: zipCode,
        hospRegistrationNum: hospRegistrationNum,
        hospManager: hospManager,
        hospManagerEmail: hospManagerEmail,
        hospManagerContact: hospManagerContact
    }, function (response) {

        console.log(response);

        if (response && response.error) {
            alert(response.error);
        } else {

            alert("Added");
        }
    });

}