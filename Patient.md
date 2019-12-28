## Patient API
    http://134.209.144.24/api/

## Login

#### Request
    "Patient", "login", {"username": "string", "password" : "string"}
    
#### Response

    {
        "$class": "org.reco.health.Patient",
        "patientId": "22",
        "firstName": "Hello",
        "lastName": "World",
        "gender": "male",
        "age": "30",
        "emailAddress": "email@email.com",
        "bloodGroup": "A",
        "dob": 123,
        "allergies": [
            "123",
            "123"
        ],
        "chronicDisease": ""
    }
Response should be saved

    
## Register

#### Request
    "Patient", "register", {
        "username": "string",
        "password": "string",
        "firstName": "string",
        "lastName": "string",
        "gender": "string",
        "age": "string",
        "emailAddress": "string",
        "bloodGroup": "string",
        "dob": "string",
        "allergies": "array",
        "chronicDisease": "string"
    }
    
#### Response

    {
        "$class": "org.reco.health.Patient",
        "patientId": "22",
        "firstName": "Hello",
        "lastName": "World",
        "gender": "male",
        "age": "30",
        "emailAddress": "email@email.com",
        "bloodGroup": "A",
        "dob": 123,
        "allergies": [
            "123",
            "123"
        ],
        "chronicDisease": ""
    }
    
Response should be saved

    
## Get Records

#### Request
    "Patient", "getRecords", {"patientId" : "string"}

#### Response
    [
    {
        "$class": "org.reco.health.MedicalRecord",
        "recordId": "6641",
        "doctor": "resource:org.reco.health.Doctor#1",
        "patient": "resource:org.reco.health.Patient#1",
        "encounterTime": "2019-11-23T13:00:43.104Z",
        "eventToIllness": "Esse anim veniam ut.",
        "sitePain": "Aliqua culpa consectetur nisi eu.",
        "severityPain": "Reprehenderit amet dolore esse pariatur.",
        "painCharacter": "Amet cillum veniam.",
        "patternPain": "In id eiusmod.",
        "siteBleed": "Nisi ut do magna excepteur.",
        "severityBleed": "Sint cillum cillum.",
        "symptoms": [
            "Est minim deserunt qui in."
        ],
        "diagnosis": "Dolore ut.",
        "bloodTest": [
            "Reprehenderit culpa."
        ],
        "patientScans": [
            "Irure irure do quis excepteur."
        ],
        "departmentTransfer": [
            "Eiusmod est commodo irure."
        ],
        "patientMedication": [
            "Eiusmod."
        ],
        "data": "Occaecat."
    }
    ]

An array of medical records

## Get Shared Records

#### Request
    "Patient", "getSharedRecords", {"patientId" : "string"}
    
#### Response
    [
    {
        "$class": "org.reco.health.SharedMedicalRecord",
        "doctor": {
            "$class": "org.reco.health.Doctor",
            "doctorId": "1",
            "hospital": {
                "$class": "org.reco.health.Hospital",
                "hospitalId": "1",
                "address": {
                    "$class": "org.reco.health.Address",
                    "street": "Ullamco ea.",
                    "city": "Ex id.",
                    "country": "Aliquip.",
                    "zipCode": "Commodo aliqua ea incididunt commodo."
                },
                "hospitalName": "Mollit.",
                "hospContactNum": "Quis non.",
                "hospAltContactNum": "Cillum.",
                "hospRegistrationNum": "Officia.",
                "contactEmailAddress": "Culpa magna nisi laborum aliquip.",
                "hospManager": "Laboris.",
                "hospManagerEmail": "Aliquip duis consequat laborum in.",
                "hospManagerContact": "Aute nostrud laborum."
            },
            "firstName": "Est enim sunt qui proident.",
            "lastName": "Ad cupidatat.",
            "gender": "Eiusmod enim dolor exercitation.",
            "address": {
                "$class": "org.reco.health.Address",
                "street": "Eiusmod tempor minim culpa consectetur.",
                "city": "Ut culpa.",
                "country": "Occaecat occaecat deserunt aliquip.",
                "zipCode": "Cupidatat eiusmod enim."
            },
            "mobile": "Eiusmod occaecat.",
            "emailAddress": "Ut duis.",
            "department": "Qui esse ad.",
            "position": "Dolore excepteur."
        },
        "medicalRecord": {
            "$class": "org.reco.health.MedicalRecord",
            "recordId": "1",
            "doctor": {
                "$class": "org.reco.health.Doctor",
                "doctorId": "1",
                "hospital": {
                    "$class": "org.reco.health.Hospital",
                    "hospitalId": "1",
                    "address": {
                        "$class": "org.reco.health.Address",
                        "street": "Ullamco ea.",
                        "city": "Ex id.",
                        "country": "Aliquip.",
                        "zipCode": "Commodo aliqua ea incididunt commodo."
                    },
                    "hospitalName": "Mollit.",
                    "hospContactNum": "Quis non.",
                    "hospAltContactNum": "Cillum.",
                    "hospRegistrationNum": "Officia.",
                    "contactEmailAddress": "Culpa magna nisi laborum aliquip.",
                    "hospManager": "Laboris.",
                    "hospManagerEmail": "Aliquip duis consequat laborum in.",
                    "hospManagerContact": "Aute nostrud laborum."
                },
                "firstName": "Est enim sunt qui proident.",
                "lastName": "Ad cupidatat.",
                "gender": "Eiusmod enim dolor exercitation.",
                "address": {
                    "$class": "org.reco.health.Address",
                    "street": "Eiusmod tempor minim culpa consectetur.",
                    "city": "Ut culpa.",
                    "country": "Occaecat occaecat deserunt aliquip.",
                    "zipCode": "Cupidatat eiusmod enim."
                },
                "mobile": "Eiusmod occaecat.",
                "emailAddress": "Ut duis.",
                "department": "Qui esse ad.",
                "position": "Dolore excepteur."
            },
            "encounterTime": "Enim deserunt aliqua ex.",
            "eventToIllness": "Fugiat nulla ullamco.",
            "sitePain": "Velit.",
            "severityPain": "Labore culpa elit pariatur cupidatat.",
            "painCharacter": "Est ea occaecat.",
            "patternPain": "Quis.",
            "siteBleed": "Nulla dolore.",
            "severityBleed": "Reprehenderit nisi nostrud ullamco.",
            "symptoms": [
                "Sunt ullamco minim aliqua."
            ],
            "diagnosis": "Lorem.",
            "bloodTest": [
                "Duis consequat."
            ],
            "patientScans": [
                "Ea enim officia occaecat."
            ],
            "departmentTransfer": [
                "Eu voluptate sit amet."
            ],
            "patientMedication": [
                "Tempor."
            ],
            "data": "Occaecat officia reprehenderit."
        },
        "key": "zxcva"
    }
    ]

## Share Record

#### Request
    "Patient", "shareRecord", {"patientId": "string", "password" : "string", "doctorId": "string", "medicalRecordId": "string"}
    
#### Response

    {
        true
    }
    
    
## Revoke Shared Record

#### Request
    "Patient", "revokeSharedRecord", {"key": "string"}
    
#### Response

    {
        true
    }
    
