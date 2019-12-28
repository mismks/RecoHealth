<?php
/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 */

class MedicalRecordBlockchain {

    public static function get($id) {

        output("Get medical record $id from blockchain");

        return json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/MedicalRecord/$id'"), true);
    }

    public static function getAllForPatientId($patientId) {

        output("Get medical records for patient $patientId from blockchain");

        $filter["where"] = ["patient" => "resource:org.reco.health.Patient#$patientId"];
        $filter["include"] = "resolve";

        return json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/MedicalRecord?filter=" . urlencode(json_encode($filter)) . "'"), true);
    }

    public static function getAllForDoctorId($doctorId) {

        output("Get medical records for doctor $doctorId from blockchain");

        $filter["where"] = ["doctor" => "resource:org.reco.health.Doctor#$doctorId"];
        $filter["include"] = "resolve";

        return json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/MedicalRecord?filter=" . urlencode(json_encode($filter)) . "'"), true);

    }

    public static function getSharedForPatientId($patientId) {

        output("Get shared medical records for patient $patientId from blockchain");

        $filter["where"] = ["patient" => "resource:org.reco.health.Patient#$patientId"];

        $filter["include"] = "resolve";

        $shared = json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/SharedMedicalRecord?filter=" . urlencode(json_encode($filter)) . "'"), true);

        //output($shared);

        return array_map(function ($element) {

            unset($element["medicalRecord"]["patient"]);
            unset($element["patient"]);

            return $element;

            return $element["medicalRecord"];
        }, $shared);
    }

    public static function getSharedForDoctorId($doctorId) {

        output("Get shared medical records for doctor $doctorId from blockchain");

        $filter["where"] = ["doctor" => "resource:org.reco.health.Doctor#$doctorId"];

        $filter["include"] = "resolve";

        $shared = json_decode(shell_exec("curl -X GET --header 'Accept: application/json' 'http://$blockchainIp:3000/api/SharedMedicalRecord?filter=" . urlencode(json_encode($filter)) . "'"), true);

        output($shared);

        return array_map(function ($element) {

            return $element["medicalRecord"];

        }, $shared);
    }

    public static function add($parameters) {

        output("Add medical record to blockchain");

        $requiredParameters = ["recordId", "encounterTime", "eventToIllness", "sitePain", "severityPain", "painCharacter", "patternPain", "siteBleed", "severityBleed", "symptoms", "diagnosis", "bloodTest", "patientScans", "departmentTransfer", "patientMedication", "data"];

        $medicalRecord = ['$class' => "org.reco.health.MedicalRecord", "doctor" => "resource=>org.reco.health.Doctor", "patient" => "resource=>org.reco.health.Patient"];

        verifyParameters($requiredParameters, $parameters, $medicalRecord);

        // Construct addDoctor transaction
        $addRecord = ['$class' => "org.reco.health.addMedicalRecord", "medicalRecord" => $medicalRecord, "doctorId" => forceGet("doctorId", $parameters), "patientId" => forceGet("patientId", $parameters)];

        // echo json_encode($addRecord, JSON_PRETTY_PRINT);

        $response = distribute('curl -X POST --header \'Content-Type: application/json\' --header \'Accept: application/json\' -d \'' . json_encode($addRecord) . '\' \'$blockchainIp:3000/api/addMedicalRecord\'');

        $decodedResponse = json_decode($response, true);

        if (!$decodedResponse || isset($decodedResponse["error"])) {
            throw new Exception("Invalid response from blockahin $response");
        }

        return $decodedResponse["medicalRecord"];
    }


    public static function share($parameters) {

        output("Send share request to blockchain");

        $requiredParameters = ["medicalRecordId", "doctorId", "key"];

        $shareMedicalRecord = ['$class' => "org.reco.health.shareMedicalRecord"];

        verifyParameters($requiredParameters, $parameters, $shareMedicalRecord);

        // echo json_encode($addRecord, JSON_PRETTY_PRINT);

        $response = distribute('curl -X POST --header \'Content-Type: application/json\' --header \'Accept: application/json\' -d \'' . json_encode($shareMedicalRecord) . '\' \'$blockchainIp:3000/api/shareMedicalRecord\'');

        $decodedResponse = json_decode($response, true);

        if (!$decodedResponse || isset($decodedResponse["error"])) {
            throw new Exception("Invalid response from blockahin $response");
        }

        return true;
    }

    public static function revokeShared($parameters) {

        output("Revoke shared medical record");

        $key = forceGet("key", $parameters);

        $response = distribute("curl -X DELETE --header 'Accept: application/json' 'http://$blockchainIp:3000/api/SharedMedicalRecord/$key'");

        $decodedResponse = json_decode($response, true);

        if (!$decodedResponse || isset($decodedResponse["error"])) {
            throw new Exception("Invalid response from blockahin $response");
        }

        return true;
    }
}