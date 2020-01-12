$(document).ready(() => {
    let economyName = sessionStorage.getItem('economyName');
    let economyMoney = sessionStorage.getItem('economyMoney');

    console.log(economyName + " " + economyMoney);
    document.getElementById('economisire-economie').innerHTML = economyName;
    document.getElementById('economisire-economisire').innerHTML = economyMoney;

});

function adauga() {
    var suma = $('#economisire-input').val();
    if (verifyMoney(suma)) {
        var inainte = sessionStorage.getItem('economyMoney');
        var sumaFinala = parseInt(inainte) + parseInt(suma);
        document.getElementById('economisire-economisire').innerHTML = sumaFinala + " RON";
        sessionStorage.setItem('economyMoney', sumaFinala + " RON");
        document.getElementById('economisire-input').value = "";

        let economyList = JSON.parse(sessionStorage.getItem('economyList'));
        if (economyList != null) {
            economyList.forEach((economy) => {
                if (economy.name === sessionStorage.getItem('economyName')) {
                    economy.money = sumaFinala + " RON";
                    console.log(economy.money);
                }
            });
        }
        sessionStorage.setItem('economyList', JSON.stringify(economyList));
        sessionStorage.setItem('populateEconomisireList', JSON.stringify(economyList));

        console.log(sessionStorage.getItem('economyList'));
        window.location = "./11_economisire.html";
    } else {
        document.getElementById("mesaj").innerHTML = "Campul acesta trebuie sa contina doar cifre sau sa nu inceapa cu 0";
    }
}

function verifyMoney(suma) {
    var digits = /^[0-9]+$/;
    if (suma.match(digits) && suma.length != 0 && suma[0] != 0) {
        return true;
    } else {
        return false;
    }
}