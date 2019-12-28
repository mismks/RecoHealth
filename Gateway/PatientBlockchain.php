<?php
/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class PatientBlockchain {

    public static function get($id) {

        output("Get patient identity from blockchain");

        $filter["include"] = "resolve";

        return json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/Patient/$id?filter=" . urlencode(json_encode($filter)) . "'"), true);
    }

    public static function add($parameters) {

        output("Add patient identity to blockchain");

        // "allergies"
        $requiredParameters = ["patientId", "firstName", "lastName", "gender", "emailAddress", "bloodGroup", "dob", "chronicDisease"];

        $patient = ['$class' => "org.reco.health.Patient"];

        verifyParameters($requiredParameters, $parameters, $patient);

        $response = distribute('curl -X POST --header \'Content-Type: application/json\' --header \'Accept: application/json\' -d \'{  
   "$class": "org.reco.health.addPatient",  
   "patient": ' . json_encode($patient) . '  
 }\' \'$blockchainIp:3000/api/addPatient\'');

        $decodedResponse = json_decode($response, true);

        if (!$decodedResponse || isset($decodedResponse["error"])) {
            throw new Exception("Invalid response from blockahin $response");
        }

        output("Patient added, all ok");

        return $decodedResponse["patient"];
    }

}