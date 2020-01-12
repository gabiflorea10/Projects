$(document).ready(() => {
    let showList = "";
    let populateList = sessionStorage.getItem('populateList');
    if (populateList === null) {
        let childrenList = [{'name': 'Marius', 'money': '350 RON'}];
        sessionStorage.setItem('childrenList', JSON.stringify(childrenList));
        childrenList.forEach((child) => {
            console.log(child.name + child.money);
            showList += '<li class="conturi-copii-main-list-element">' +
                '<p class="conturi-copii-main-list-element-name" onclick="myScript">' + child.name + '</p>' +
                '<p class="conturi-copii-main-list-element-money">' + child.money + '</p>' +
                '<button class="conturi-copii-main-list-element-buttons1" onclick="seteaza(\'' + child.name + '\',\'' + child.money.toString() + '\')">Seteaza</button>' +
                '<button class="conturi-copii-main-list-element-buttons2" onclick="adauga(\'' + child.name + '\',\'' + child.money.toString() + '\')">Adauga</button>' +
                '</li>';
        });
    } else {
        let childrenList = JSON.parse(sessionStorage.getItem('childrenList'));
    childrenList.forEach((child) => {
        console.log(child.name + child.money);
    });
    }

    let childrenList = JSON.parse(sessionStorage.getItem('childrenList'));
    childrenList.forEach((child) => {
        if(child.name === "Marius") {

            let budget = child.money;
            $('#my_money').html(budget);
            var res = budget.split(" ");

            var childBudget = parseInt(350);
            console.log(childBudget);
            divideMoney(childBudget);
        }
    });
});

function divideMoney(sum) {

    var sum500 = 0;
    var sum200 = 0;
    var sum100 = 0;
    var sum50 = 0;
    var sum10 = 0;
    var sum5 = 0;
    var sum1 = 0;

    var coinValue = [ 500, 200, 100, 50, 20, 10, 5, 2, 1];

    var result = []
    var remaining = sum;

    for (var i = 0; i < coinValue.length; i++) {
        result[i] = Math.trunc(remaining / coinValue[i]);
        if (result[i] > 0)
            remaining = remaining % coinValue[i];
    }

    document.getElementById('label_1leu').innerHTML = "1 leu x " + ''+result[6]+'';
    document.getElementById('label_5lei').innerHTML = "5 lei x " + ''+result[5]+'';
    document.getElementById('label_10lei').innerHTML = "10 lei x " + ''+result[4]+'';
    document.getElementById('label_50lei').innerHTML = "50 lei x " + ''+result[3]+'';
    document.getElementById('label_100lei').innerHTML = "100 lei x " + ''+result[2]+'';
    document.getElementById('label_200lei').innerHTML = "200 lei x " + ''+result[1]+'';
    document.getElementById('label_500lei').innerHTML = "500 lei x " + ''+result[0]+'';


}