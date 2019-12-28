/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

//var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

function _BlockchainApi() {

    let server = "http://ec2-34-244-40-81.eu-west-1.compute.amazonaws.com:3000/api/"

    this.GET = function (endpoint, parameters, completion) {
        this.call("GET", server + endpoint + formatParams(parameters), null, completion);
    };

    this.POST = function (endpoint, parameters, completion) {
        this.call("POST", server + endpoint, parameters, completion);
    };

    this.call = function (type, url, parameters, completion) {

        let xhttp = new XMLHttpRequest();

        xhttp.open(type, url, true);
        xhttp.responseType = 'json';
        xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhttp.send(JSON.stringify(parameters));

        xhttp.onload = function () {
            completion(xhttp);
        };
    }

    function formatParams(params) {

        if (!params) return "";

        return "?" + Object
            .keys(params)
            .map(function (key) {
                return key + "=" + encodeURIComponent(params[key])
            })
            .join("&")
    }
}

var BlockchainApi = new _BlockchainApi();

