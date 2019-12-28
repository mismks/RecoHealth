<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

class DoctorApi {

    public static function login($parameters) {

        $doctorId = DoctorDB::login($parameters["username"], $parameters["password"]);

        return DoctorBlockchain::get($doctorId);
    }

    public static function register($parameters) {

        $parameters["doctorId"] = DoctorDB::add($parameters["username"], $parameters["password"]);

        try {

            return DoctorBlockchain::add($parameters);

        } catch (Exception $e) {

            DoctorDB::remove($parameters["doctorId"]);

            throw $e;
        }
    }

    public static function addRecord($parameters) {

        // Add a record for doctor
        return MedicalRecordApi::add($parameters);
    }

    public static function getAddedRecords($parameters) {

        return MedicalRecordBlockchain::getAllForDoctorId(forceGet("doctorId", $parameters));
    }

    public static function getSharedRecords($parameters) {

        return MedicalRecordBlockchain::getSharedForDoctorId(forceGet("doctorId", $parameters));
    }
}