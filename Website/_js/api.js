/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

//
//
function _Api() {

    let server = "http://134.209.144.24/api/";

    this.GET = function (name, action, parameters, completion) {

        console.log("GET");

        parameters["class"] = name;
        parameters["action"] = action;

        this.call("GET", server + "?" + formatParams(parameters), null, completion);
    };

    this.POST = function (name, action, parameters, completion) {
        parameters["class"] = name;
        parameters["action"] = action;
        this.call("POST", server, parameters, completion);
    };

    this.call = function (type, url, parameters, completion) {

        let xhttp = new XMLHttpRequest();

        xhttp.open(type, url, true);
        xhttp.responseType = 'json';

        if (type === "POST") {
            //xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xhttp.send(formatParams(parameters));
        } else {
            xhttp.send();
        }


        xhttp.onload = function () {
            console.log(xhttp.response);
            completion(xhttp.response);
        };
    };

    function formatParams(params) {

        if (!params) return "";

        return "" + Object
            .keys(params)
            .map(function (key) {
                return key + "=" + encodeURIComponent(params[key])
            })
            .join("&")
    }
}

var Api = new _Api();

//Api.GET("Patient", "login", {"username": "helloworld", "password": "huy78hu17238"});