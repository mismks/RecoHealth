# Doctor API

## Example

    Api.POST(name, action, parameters) function (response) {

          if (response.error) {
              alert(response.error);
          } else {
              console.log(response);
          }
     });

## Login
#### Request

    "Doctor", "login", { "username", "password" }    

#### Response

    {"$class":"org.reco.health.Doctor","doctorId":"15","hospital":"resource:org.reco.health.Hospital#1","firstName":"doc1","lastName":"doc1","gender":"male","address":{"$class":"org.reco.health.Address","street":"123 ST.","city":"Dubai","country":"UAE","zipCode":"123"},"mobile":"123","emailAddress":"123","department":"Test","position":"Manager"}
    
Response should be stored in cookies

## Register

#### Request

    "Doctor", "register", {
        "username": "string",
        "password": "string",
        "firstName": "string",
        "lastName": "string",
        "hospitalId": "string",
        "gender": "string",
        "mobile": "string",
        "emailAddress": "string",
        "department": "string",
        "position": "string",
        "street": "string",
        "city": "string",
        "country": "string",
        "zipCode": "string"
    }
    
#### Response

    {"$class":"org.reco.health.addDoctor","doctor":{"$class":"org.reco.health.Doctor","hospital":"resource:org.reco.health.Hospital","doctorId":16,"firstName":"doc1","lastName":"doc1","gender":"male","mobile":"123","emailAddress":"123","department":"Test","position":"Manager","address":{"$class":"org.reco.health.Address","street":"123 ST.","city":"test","country":"test","zipCode":"123"}},"hospitalId":"1"}{
    "$class": "org.reco.health.Doctor",
    "doctorId": "16",
    "hospital": "resource:org.reco.health.Hospital",
    "firstName": "doc1",
    "lastName": "doc1",
    "gender": "male",
    "address": {
        "$class": "org.reco.health.Address",
        "street": "123 ST.",
        "city": "Dubai",
        "country": "UAE",
        "zipCode": "123"
    },
    "mobile": "123",
    "emailAddress": "123",
    "department": "Test",
    "position": "Manager"
}
    
Response should be stored in cookies

    
## Add Record

    "Doctor", "addRecord", {
        "patientId": "string",
        "doctorId": "string",
        "encounterTime": "string",
        "eventToIllness": "string",
        "sitePain": "string",
        "severityPain": "string",
        "painCharacter": "string",
        "patternPain": "string",
        "siteBleed": "string",
        "severityBleed": "string",
        "symptoms": "array",
        "diagnosis": "string",
        "bloodTest": "array",
        "patientScans": "array",
        "departmentTransfer": "array",
        "patientMedication": "array"
    }
    
    
## Get Records

#### Request
    "Doctor", "getRecords"
   
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
   
