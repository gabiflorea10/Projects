$(document).ready(() => {
    let showList = "";
    let populateList = sessionStorage.getItem('populateEconomisireList');
    console.log(populateList);
    if (populateList === null) {
        console.log("economyList is empty");
        let economyList = [{'name': 'Vacanta', 'money': '0 RON'},
            {'name': 'Ochelari','money':'100 RON'}];
        sessionStorage.setItem('economyList', JSON.stringify(economyList));
        economyList.forEach((economy) => {
            showList += '<li class="economisire-main-list-element">' +
                '<p class="economisire-main-list-element-name" onclick="myScript">' + economy.name + '</p>' +
                '<p class="economisire-main-list-element-money">' + economy.money + '</p>' +
                '<button class="economisire-main-list-element-buttons2" onclick="adauga(\'' + economy.name + '\',\'' + economy.money.toString() + '\')">Adauga</button>' +
                '</li>';
        });
        $('#economisire-main-list').html(showList);
    } else {
        let economyList = JSON.parse(sessionStorage.getItem('economyList'));
        economyList.forEach((economy) => {
            showList += '<li class="economisire-main-list-element">' +
                '<p class="economisire-main-list-element-name" onclick="myScript">' + economy.name + '</p>' +
                '<p class="economisire-main-list-element-money">' + economy.money + '</p>' +
                '<button class="economisire-main-list-element-buttons2" onclick="adauga(\'' + economy.name + '\',\'' + economy.money.toString() + '\')">Adauga</button>' +
                '</li>';
        });
        $('#economisire-main-list').html(showList);
    }
});

function myScript() {
    console.log("MERGE");
}

function adauga(arg1, arg2) {
    sessionStorage.setItem('economyName', arg1);
    sessionStorage.setItem('economyMoney', arg2);
    console.log(arg1 + " " + arg2);
    window.location="./11_economisire_adauga.html";
}