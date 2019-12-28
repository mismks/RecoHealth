/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

function addDoctor() {

    console.log("Hello");

    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let gender = document.getElementById("gender").value;

    let street = document.getElementById("street").value;
    let city = document.getElementById("city").value;
    let country = document.getElementById("country").value;
    let zipCode = document.getElementById("zipCode").value;

    let mobile = document.getElementById("mobile").value;
    let emailAddress = document.getElementById("emailAddress").value;
    let department = document.getElementById("department").value;
    let position = document.getElementById("position").value;

    let hospitalId = document.getElementById("hospitalId").value;

    Api.POST("Doctor", "register", {
        username: username,
        password: password,
        firstName: firstName,
        lastName: lastName,
        gender: gender,
        street: street,
        city: city,
        country: country,
        zipCode: zipCode,
        mobile: mobile,
        emailAddress: emailAddress,
        department: department,
        position: position,
        hospitalId: hospitalId
    }, function (response) {

        console.log(response);

        if (response && response.error) {
            alert(response.error);
        } else {

            alert("Added");
        }
    });
}