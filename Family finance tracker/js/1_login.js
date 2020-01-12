$(document).ready(() => {

    $('#login-button').on('click', function () {
        let userName = $('#login-username').val();
        let password = $('#login-password').val();

        console.log(userName);
        console.log(password);

        if(userName === "" && password === ""){
            $('#mesaj-eroare').text("Username and password are empty")
        }
        else if(userName === ""){
            $('#mesaj-eroare').text("Username is empty")
        }
        else if(password=== ""){
            $('#mesaj-eroare').text("Password is empty")
        }
        else if (userName === "vasile" && password === "vasile") {
            window.location = './2_1_pagina_principala_adult.html';
        }
        else if(userName === "marius" && password === "marius"){
            window.location = './2_2_pagina_principala_copil.html';
        }
        else {
            $('#mesaj-eroare').text("Wrong credentials")
        }
    });

});