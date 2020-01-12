import {HOST} from '../../../commons/hosts';
import RestApiClient from "../../../commons/api/rest-client";
import axios from 'axios';

const endpoint = {
    get_medications: '/medication/allMedication',
    post_medications: "/medication/insertMedication/",
    put_medications: "/medication/updateMedication/",
    delete_medications: "/medication/deleteMedication/"
};

function getMedications(callback) {
    let request = new Request(HOST.backend_api + endpoint.get_medications, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getMedicationById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.get_medications + params.id, {
        method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}


// Function for performing a request skipping the architecture
// function postMedication(medication){
//     console.log(medication)
//     axios.post(HOST.backend_api + endpoint.post_medications, medication).then(response => {console.log(response)})
//         .catch(function (error) {
//             console.log(error);
//         });
// }


function postMedication(medication, callback){

    let request = new Request(HOST.backend_api + endpoint.post_medications, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medication)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function putMedication(medication, callback){

    let request = new Request(HOST.backend_api + endpoint.put_medications, {
        method: 'PUT',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(medication)
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function deleteMedication(medication, callback){
    console.log("Step 2");
    let request = new Request(HOST.backend_api + endpoint.delete_medications + '?name=' + medication, {
        method: 'DELETE',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

export {
    getMedications,
    getMedicationById,
    postMedication,
    putMedication,
    deleteMedication
};
