<?php

header('Content-Type: application/json');

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

define("DEBUG", true);

require_once("../../api/load.php");


//$test = ["username"       => "user2",
//         "password"       => "world",
//         "firstName"      => "Hello",
//         "lastName"       => "World",
//         "gender"         => "male",
//         "age"            => "30",
//         "emailAddress"   => "email@email.com",
//         "bloodGroup"     => "A",
//         "dob"            => "123",
//         "allergies"      => ["123",
//                              "123"],
//         "chronicDisease" => ""];
//
//foreach ($test as $key => $value) {
//    $test[$key] = gettype($value);
//}
//
//echo json_encode($test, JSON_PRETTY_PRINT);
//
//exit();
//
output(json_encode(MedicalRecordBlockchain::getSharedForPatientId(1), JSON_PRETTY_PRINT));
exit();
//DoctorApi::register(["username" => ""])
/*output(MedicalRecordApi::add(["patientId"          => "10",
                              "doctorId"           => "1",
                              "encounterTime"      => "2019-11-22T20:05:09.354Z",
                              "eventToIllness"     => "",
                              "sitePain"           => "",
                              "severityPain"       => "",
                              "painCharacter"      => "",
                              "patternPain"        => "",
                              "siteBleed"          => "",
                              "severityBleed"      => "",
                              "symptoms"           => [],
                              "diagnosis"          => "test 123",
                              "bloodTest"          => ["1",
                                                       "2",
                                                       "3"],
                              "patientScans"       => [],
                              "departmentTransfer" => [],
                              "patientMedication"  => [],]));*/

//output(json_encode(DoctorApi::register(["username" => "doctor", "password" => "password", "firstName" => "doc1", "lastName" => "doc1", "hospitalId" => "1", "gender" => "male", "mobile" => "123", "emailAddress" => "123", "department" => "Test", "position" => "Manager", "street" => "123 ST.", "city" => "Dubai", "country" => "UAE", "zipCode" => "123"]), JSON_PRETTY_PRINT));

//output(json_encode(PatientApi::register(["username" => "test1", "password" => "world", "firstName" => "Hello", "lastName" => "World", "gender" => "male", "age" => "30", "emailAddress" => "email@email.com", "bloodGroup" => "A", "allergies" => ["123", "123"], "chronicDisease" => ""]), JSON_PRETTY_PRINT));

//output(PatientApi::login(["username" => "hello4500",
//                          "password" => "world"]));

//exit();

echo system('curl -X POST --header \'Content-Type: application/json\' --header \'Accept: application/json\' -d \'{
  "$class": "org.reco.health.Patient",
  "patientId": "1",
  "firstName": "Sameer",
  "lastName": "Shabbir",
  "gender": "Male",
  "emailAddress": "sms9092@live.com",
  "bloodGroup": "AB+",
  "dob": "11/27/2019",
  "allergies": [
    "Drug Allergy",
    "Food Allergy",
    "Insect Allergy",
    "Latex Allergy",
    "Mold Allergy",
    "Pet Allergy",
    "Pollen Allergy"
  ],
  "chronicDisease": "nothing"
}\' \'159.65.153.0:3000/api/addPatient\' 2>&1');

exit();

//$text = "Peter Piper picked a peck of pickled peppers";
//$RSA = new RSA_Handler();
//$keys = $RSA->generate_keypair(1024);
//$encrypted = $RSA->encrypt($text, $keys[0]);
//$decrypted = $RSA->decrypt($encrypted, $keys[1]);
//echo $decrypted; //Will print Peter Piper picked a peck of pickled peppers

// Create the keypair

//PatientDB::add("helloworld", "huy78hu17238");

//MedicalRecordDB::add("1", "1", "data");
//$patient = PatientDB::getIdentity(1);
//
//$privateKey = AES::decrypt($patient["privateKey"], "huy78hu17238");
//
//var_dump(RSA::decrypt("T1krWjAyTDI1aFBWYjI0bXRxcmhMRVIzSXg3ZUU4SEpqVU15RTVMOU5IMmxNUCtqTms1aHczM1V6dXFnN0RQMlVSUEhWRDN5Z2t3dTZCeXMyWHM2OHFhdE5tUmJHdzRsVVRQQUpIVlhXK01kempBMFpLYWVpUjV4UmJkUjV3Y3hBQys5V0FMNExtN2FMREUxcmY4Zkc1b2JRVHc3U0JCOTF0a2o0YjZ4cFJoOS9pNHloOXJjWVYvRmttZW50N2dsZDJMbDViWGp2T2NKcGV5ZmhyUHVNeElkUlRRWHEvNXNRQnZDM3JCOEJwaE11U0lld2g2Q2dNQWxOMDRGdnpPN3BFb2M1RkdFTkFQVDlEakJKWXcva3RuQnY1aWpPVFhzTjFOY2EwVDBkQ2tPOHlRTXRxMVpRQkZ4ZTMzOXJtNmd2aTVXMWloUmZCV2QzSlppcmR4bURRPT0=", $privateKey));

exit();

$userPassword = "Hello234";

$keys = RSA::generate();

// Private key encrypted with userPassword
$encryptedPrivateKey = AES::encrypt($keys["private"], $userPassword);

echo $encryptedPrivateKey;

echo AES::decrypt($encryptedPrivateKey, $userPassword);

exit();

output("Hello");

output("World");

//PatientDB::add("helloworld", "huy78hu17238");
$id = PatientDB::login("helloworld", "huy78hu17238");

output($id);


