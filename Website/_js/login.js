/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

function login() {

    console.log("Hello");

    let username = document.getElementById("loginName").value;
    let password = document.getElementById("loginPass").value;

    Api.POST("Doctor", "login", {"username": username, "password": password}, function (response) {

        if (response.error) {
            alert(response.error);
        } else {

            setCookie("doctor", JSON.stringify(response.result), 10);

            let doctor = getCookie("doctor");

            console.log(doctor);

            document.location = "doctor.html"
        }
    });
}