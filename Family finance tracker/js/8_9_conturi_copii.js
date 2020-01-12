$(document).ready(() => {
    let showList = "";
    //let populateList = sessionStorage.getItem('populateList');
    let populateList = sessionStorage.getItem('childrenList');

    console.log(populateList);
    if (populateList === null) {
        let childrenList = [{'name': 'Marius', 'money': '350 RON'}];
        sessionStorage.setItem('childrenList', JSON.stringify(childrenList));
        childrenList.forEach((child) => {
            showList += '<li class="conturi-copii-main-list-element">' +
                '<p class="conturi-copii-main-list-element-name" onclick="myScript">' + child.name + '</p>' +
                '<p class="conturi-copii-main-list-element-money">' + child.money + '</p>' +
                '<button class="conturi-copii-main-list-element-buttons1" onclick="seteaza(\'' + child.name + '\',\'' + child.money.toString() + '\')">Seteaza</button>' +
                '<button class="conturi-copii-main-list-element-buttons2" onclick="adauga(\'' + child.name + '\',\'' + child.money.toString() + '\')">Adauga</button>' +
                '</li>';
        });
        $('#conturi-copii-main-list').html(showList);
    } else {
        let childrenList = JSON.parse(sessionStorage.getItem('childrenList'));
        childrenList.forEach((child) => {
            showList += '<li class="conturi-copii-main-list-element">' +
                '<p class="conturi-copii-main-list-element-name" onclick="myScript">' + child.name + '</p>' +
                '<p class="conturi-copii-main-list-element-money">' + child.money + '</p>' +
                '<button class="conturi-copii-main-list-element-buttons1" onclick="seteaza(\'' + child.name + '\',\'' + child.money.toString() + '\')">Seteaza</button>' +
                '<button class="conturi-copii-main-list-element-buttons2" onclick="adauga(\'' + child.name + '\',\'' + child.money.toString() + '\')">Adauga</button>' +
                '</li>';
        });
        $('#conturi-copii-main-list').html(showList);
    }
});

function myScript() {
    console.log("MERGE");
}

function seteaza(arg1, arg2) {
    sessionStorage.setItem('childName', arg1);
    sessionStorage.setItem('childMoney', arg2);
    console.log(arg1 + " " + arg2);
    window.location="./8_9_conturi_copii_seteaza.html"
}

function adauga(arg1, arg2) {
    sessionStorage.setItem('childName', arg1);
    sessionStorage.setItem('childMoney', arg2);
    console.log(arg1 + " " + arg2);
    window.location="./8_9_conturi_copii_adauga.html"
}