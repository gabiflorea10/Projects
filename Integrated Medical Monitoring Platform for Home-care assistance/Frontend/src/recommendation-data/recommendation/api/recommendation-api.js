import {HOST} from '../../../commons/hosts';
import RestApiClient from "../../../commons/api/rest-client";

const endpoint = {
    get_recommendations: '/caregiver/viewRecommendations/'
};

function getRecommendations(caregiverUsername, callback){

    let request = new Request(HOST.backend_api + endpoint.get_recommendations + '?caregiverUsername=' + caregiverUsername, {
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
    getRecommendations
};
