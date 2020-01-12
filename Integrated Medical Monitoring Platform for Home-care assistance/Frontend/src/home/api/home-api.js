import {HOST} from '../../commons/hosts';
import RestApiClient from "../../commons/api/rest-client";

const endpoint = {
    get_login: "/user/login/"
};

function getLogin(username, password, callback){

    console.log(HOST.backend_api + endpoint.post_medications + '?username=' + username + '&password=' + password);
    console.log();
    let request = new Request(HOST.backend_api + endpoint.get_login + '?username=' + username + '&password=' + password, {
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
    getLogin
};
