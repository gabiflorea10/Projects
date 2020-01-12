import {HOST} from '../../../commons/hosts';
import RestApiClient from "../../../commons/api/rest-client";


const endpoint = {
    get_patients: '/patient/allPatients',
    get_patient_by_username: '/patient/getPatientByUsername',
    get_patients_by_caregiver_username: '/patient/allPatientsByCaregiverUsername',
    post_patients: "/patient/insertPatient",
    put_patients: "/patient/updatePatient",
    delete_patients: "/patient/deletePatient",

    get_patient_activities: '/doctor/getActivityForPatient',
    get_charts: '/doctor/getActivityCharts',
    get_monitors: '/doctor/getMonitors',
    post_addRecommendation: '/doctor/addRecommendation',
    put_annotateActivity: '/doctor/annotateActivity',

};

function getPatients(username, role, callback) {

    if(!role.localeCompare("caregiver")){
        let request = new Request(HOST.backend_api + endpoint.get_patients_by_caregiver_username + '?username=' + username, {
            method: 'GET',
            headers : {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        });
        console.log(request.url);
        RestApiClient.performRequest(request, callback);
    }
    else {
        let request = new Request(HOST.backend_api + endpoint.get_patients, {
            method: 'GET',
        });
        console.log(request.url);
        RestApiClient.performRequest(request, callback);
    }

}

function getPatientByUsername(username, callback) {

    let request = new Request(HOST.backend_api + endpoint.get_patient_by_username + '?username=' + username, {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getPatientsById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.get_patients + params.id, {
        method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postPatient(patient, callback){
    let request = new Request(HOST.backend_api + endpoint.post_patients, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function putPatient(patient, callback){
    let request = new Request(HOST.backend_api + endpoint.put_patients, {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(patient)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deletePatient(patient, callback){
    let request = new Request(HOST.backend_api + endpoint.delete_patients + '?username=' + patient, {
        method: 'DELETE',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


function getActivities(patient, callback){

    let request = new Request(HOST.backend_api + endpoint.get_patient_activities + '?patientName=' + patient, {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function changeAnnotation(activityId, callback){
    let request = new Request(HOST.backend_api + endpoint.put_annotateActivity + '?activityId=' + activityId, {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMonitors(patient, date, callback){
    let request = new Request(HOST.backend_api + endpoint.get_monitors + '?patientName=' + patient + '&givenDate=' + date , {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function addRecommendation(patient, description, callback) {
    let request = new Request(HOST.backend_api + endpoint.post_addRecommendation + '?patientName=' + patient + '&description=' + description , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getCharts(patient, date, callback){
    let request = new Request(HOST.backend_api + endpoint.get_charts + '?patientName=' + patient + '&date=' + date , {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

export {
    getPatients,
    getPatientsById,
    getPatientByUsername,
    postPatient,
    putPatient,
    deletePatient,
    getActivities,
    changeAnnotation,
    getMonitors,
    addRecommendation,
    getCharts
};
