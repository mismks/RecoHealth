<?php
/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class HospitalBlockchain {

    public static function add($parameters) {

        output("Add Hospital identity to blockchain");

        $requiredParameters = ["hospitalId",
                               "hospitalName",
                               "hospContactNum",
                               "hospAltContactNum",
                               "hospRegistrationNum",
                               "contactEmailAddress",
                               "hospManager",
                               "hospManagerEmail",
                               "hospManagerContact"];

        $hospital = ['$class' => "org.reco.health.Hospital"];
        verifyParameters($requiredParameters, $parameters, $hospital);

        $address = ['$class' => "org.reco.health.Address"];

        verifyParameters(["street",
                          "city",
                          "country",
                          "zipCode"], $parameters, $address);

        // Assign address to doctor
        $hospital["address"] = $address;

        $response = distribute('curl -X POST --header \'Content-Type: application/json\' --header \'Accept: application/json\' -d \'' . json_encode($hospital) . '\' \'$blockchainIp:3000/api/Hospital\'');

        $decodedResponse = json_decode($response, true);

        if (!$decodedResponse || isset($decodedResponse["error"])) {
            throw new Exception("Invalid response from blockchain $response");
        }

        return $decodedResponse;
    }
}