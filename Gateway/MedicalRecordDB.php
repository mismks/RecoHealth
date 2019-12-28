<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class MedicalRecordDB {

    private static $dbName = "MedicalRecords";

    public static function get($id) {

        global $db;

        $db->where("id", $id);

        $result = $db->getOne(self::$dbName);

        output($result);

        if (!$result) {
            throw new Exception("Identity doesn't exist");
        }

        return $result;
    }

    public static function add($doctorId, $patientId, $data) {

        global $db;

        output("Add medical record");

        $patient = PatientDB::getIdentity($patientId);

        output("Generate random secret");

        // Generate a medical record key
        $recordSecret = random(12);

        output("Random secret: $recordSecret");
        output("Encrypt record data with secret");

        // Encrypt record data
        $encryptedData = AES::encrypt(json_encode($data), $recordSecret);

        output("Encrypted record:");
        output($encryptedData);
        output("Encrypt record secret using patient public key");

        // Encrypt record key with patient public key
        $encryptedRecordSecret = RSA::encrypt($recordSecret, $patient["publicKey"]);

        output("Encrypted secret: $encryptedRecordSecret");

        // Add record secret
        $id = $db->insert(self::$dbName, ["secret" => $encryptedRecordSecret]);

        return ["recordId" => "$id",
                "data"     => $encryptedData];

    }

}