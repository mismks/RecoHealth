/*
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class _BlockchainServer {

    login(id, password, completion) {

        let parameters = {
            "$class": "com.reco.health.DoctorLogin",
            "doctorId": id,
            "password": password
        };

        Api.POST("DoctorLogin", parameters, completion);
    };

    addBloodTestType(name, low, high, unit, lowRecommendation, highRecommendation, description, completion) {

        Api.POST("BloodTestType", {
            "$class": "com.reco.health.BloodTestType",
            "name": name,
            "low": low,
            "high": high,
            "unit": unit,
            "lowRecommendation": lowRecommendation,
            "highRecommendation": highRecommendation,
            "description": description
        }, function (request) {
            console.log(request.response);
            completion(request);
        });

    }

    // START BloodTestResults
    addBloodTestResult(patientId, bloodTestName, value, completion) {

        Api.POST("AddBloodTestResult", {
            "$class": "com.reco.health.AddBloodTestResult",
            "value": value,
            "bloodTestName": bloodTestName,
            "patientId": patientId
        }, function (request) {
            console.log(request.response);
            completion(request);
        });
    }

    getBloodTestResults(patientId, completion) {

        Api.GET("queries/Q1", {patient: "resource:com.reco.health.Patient#" + patientId}, function (request) {

            let results = request.response;

            for (let index = 0; index < results.length; index++) {

                let result = this.parseResult(results[index]);

                //result.bloodTestType = this.bloodTestsType[getNum(result.bloodTestType)];
            }

            completion(results)

        }.bind(this));

    }

    setBloodTestsType(completion) {

        Api.GET("BloodTestType", null, function (request) {

            this.bloodTestsType = this.parseResults(request.response);

            console.log(request.response)

            completion();

        }.bind(this))
    }

    // END BloodTestResults

    parseResult(result) {

        delete result["$class"];

        return result;
    }

    parseResults(results) {
        console.log(results);
        return results.map(result => this.parseResult(result))
    }
}

var BlockchainServer = new _BlockchainServer();


function getNum(str) {
    return str.replace(/[^0-9]/g, '');
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {

    var name = cname + "=";

    var decodedCookie = decodeURIComponent(document.cookie);

    var ca = decodedCookie.split(';');

    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
}