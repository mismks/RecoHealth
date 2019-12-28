<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */
class MedicalRecordApi {

    public static function add($parameters) {

        $result = MedicalRecordDB::add($parameters["doctorId"], $parameters["patientId"], $parameters);

        $parameters = array_merge($parameters, $result);

        return MedicalRecordBlockchain::add($parameters);
    }
}