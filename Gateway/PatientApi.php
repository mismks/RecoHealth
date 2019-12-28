<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class PatientApi {

    public static function login($parameters) {

        $patientId = PatientDB::login($parameters["username"], $parameters["password"]);

        return PatientBlockchain::get($patientId);
    }

    public static function register($parameters) {

        $parameters["patientId"] = PatientDB::add($parameters["username"], $parameters["password"]);

        try {

            return PatientBlockchain::add($parameters);

        } catch (Exception $e) {

            PatientDB::remove($parameters["patientId"]);

            throw $e;
        }

    }

    public static function getRecords($parameters) {

        return MedicalRecordBlockchain::getAllForPatientId(forceGet("patientId", $parameters));
    }

    public static function getSharedRecords($parameters) {

        return MedicalRecordBlockchain::getSharedForPatientId(forceGet("patientId", $parameters));
    }

    public static function shareRecord($parameters) {

        output("Share record");
        output("Get medical record encrypted key");

        // Get record key
        $medicalRecord = MedicalRecordDB::get(forceGet("medicalRecordId", $parameters));

        $secret = $medicalRecord["secret"];

        output("Key: $secret");

        // Decrypt secret using patient private key
        $patient = PatientDB::getIdentity(forceGet("patientId", $parameters));

        output("Decrypt private key using patient password");

        $privateKey = AES::decrypt($patient["privateKey"], forceGet("password", $parameters));

        output("Private key: $privateKey");
        output("Decrypt record key using patient private key");

        $recordKey = RSA::decrypt($secret, $privateKey);

        output("Record key: $recordKey");

        // Encrypt secret using doctor public key
        $doctor = DoctorDB::getIdentity(forceGet("doctorId", $parameters));

        output("Encrypt record key using doctor public key");
        output("Doctor public key: " . $doctor["publicKey"]);

        $key = AES::encrypt($recordKey, $doctor["publicKey"]);

        output("Encrypted key: $key");

        $parameters["key"] = $key;

        return MedicalRecordBlockchain::share($parameters);
    }

    public static function revokeSharedRecord($parameters) {

        return MedicalRecordBlockchain::revokeShared($parameters);
    }
}