import {HOST} from '../../../commons/hosts';
import RestApiClient from "../../../commons/api/rest-client";

const endpoint = {
    get_medication_plans: '/medication_plan/allMedicationPlanViews/',
    get_medication_plans_by_username: '/medication_plan/allMedicationPlansByUsername/',
    get_medication_plans_by_caregiver_username: "/medication_plan/allMedicationPlansByCaregiverUsername/",
    post_medication_plans: "/medication_plan/insertMedicationPlanView/"
};

function getMedicationPlanViews(callback) {
    let request = new Request(HOST.backend_api + endpoint.get_medication_plans, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMedicationPlansById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.get_medications + params.id, {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMedicationPlansByUsername(username, callback){

    let request = new Request(HOST.backend_api + endpoint.get_medication_plans_by_username + '?username=' + username, {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMedicationPlansByCaregiverUsername(username, callback){

    let request = new Request(HOST.backend_api + endpoint.get_medication_plans_by_caregiver_username + '?username=' + username, {
        method: 'GET',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postMedicationPlan(medicationPlan, callback){

    let request = new Request(HOST.backend_api + endpoint.post_medication_plans, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medicationPlan)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

export {
    getMedicationPlanViews,
    getMedicationPlansByUsername,
    getMedicationPlansByCaregiverUsername,
    postMedicationPlan
};
