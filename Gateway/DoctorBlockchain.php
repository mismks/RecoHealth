<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class DoctorBlockchain {

    public static function get($id) {

        output("Get doctor identity from blockchain");

        $filter["include"] = "resolve";

        return json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/Doctor/$id?filter=" . urlencode(json_encode($filter)) . "'"), true);
    }

    public static function add($parameters) {

        output("Add doctor identity to blockchain");

        // {
        //  "$class": "org.reco.health.addDoctor",
        //  "doctor": {
        //    "$class": "org.reco.health.Doctor",
        //    "doctorId": "string",
        //    "firstName": "string",
        //    "lastName": "string",
        //    "gender": "string",
        //    "address": {
        //      "$class": "org.reco.health.Address",
        //      "street": "string",
        //      "city": "string",
        //      "country": "string",
        //      "zipCode": "string",
        //      "id": "string"
        //    },
        //    "mobile": "string",
        //    "emailAddress": "string",
        //    "department": "string",
        //    "position": "string"
        //  },
        //  "hospitalId": "string"
        //}

        $requiredParameters = ["doctorId",
                               "firstName",
                               "lastName",
                               "gender",
                               "mobile",
                               "emailAddress",
                               "department",
                               "position"];

        $doctor = ['$class'   => "org.reco.health.Doctor",
                   "hospital" => "resource:org.reco.health.Hospital"];

        verifyParameters($requiredParameters, $parameters, $doctor);

        $address = ['$class' => "org.reco.health.Address"];

        verifyParameters(["street",
                          "city",
                          "country",
                          "zipCode"], $parameters, $address);

        // Assign address to doctor
        $doctor["address"] = $address;

        // Construct addDoctor transaction
        $addDoctor = ['$class'     => "org.reco.health.addDoctor",
                      "doctor"     => $doctor,
                      "hospitalId" => forceGet("hospitalId", $parameters)];

        //echo json_encode($addDoctor);

        $response = distribute('curl -X POST --header \'Content-Type: application/json\' --header \'Accept: application/json\' -d \'' . json_encode($addDoctor) . '\' \'$blockchainIp:3000/api/addDoctor\'');

        $decodedResponse = json_decode($response, true);

        if (!$decodedResponse || isset($decodedResponse["error"])) {
            throw new Exception("Invalid response from blockchain $response");
        }

        return $decodedResponse["doctor"];
    }


}