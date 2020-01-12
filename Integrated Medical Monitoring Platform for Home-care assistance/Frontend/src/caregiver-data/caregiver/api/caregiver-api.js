import {HOST} from '../../../commons/hosts';
import RestApiClient from "../../../commons/api/rest-client";


const endpoint = {
    get_caregivers: '/user/allCaregivers/',
    post_caregivers: "/user/insertCaregiver/",
    put_caregivers: "/user/updateCaregiver/",
    delete_caregivers: "/user/deleteCaregiver/"
};

function getCaregivers(callback) {
    let request = new Request(HOST.backend_api + endpoint.get_caregivers, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getCaregiverById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.get_caregivers + params.id, {
        method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postCaregiver(caregiver, callback){
    console.log(caregiver);
    let request = new Request(HOST.backend_api + endpoint.post_caregivers, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caregiver)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function putCaregiver(caregiver, callback){
    console.log(caregiver);
    let request = new Request(HOST.backend_api + endpoint.put_caregivers, {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caregiver)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deleteCaregiver(caregiver, callback){
    console.log(caregiver);
    let request = new Request(HOST.backend_api + endpoint.delete_caregivers + '?username=' + caregiver, {
        method: 'DELETE',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

export {
    getCaregivers,
    getCaregiverById,
    postCaregiver,
    putCaregiver,
    deleteCaregiver
};
