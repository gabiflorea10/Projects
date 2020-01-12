$(document).ready(() => {
    let childName = sessionStorage.getItem('childName');
    let childMoney = sessionStorage.getItem('childMoney');

    console.log(childName + " " + childMoney);
    document.getElementById('gest-conturi-copii-nume-copil').innerHTML = childName;
    document.getElementById('gest-conturi-copii-bani-copil').innerHTML = childMoney;

});

function adauga() {
    var suma = $('#gest-conturi-copii-input').val();
    if (verifyMoney(suma)) {
        var inainte = sessionStorage.getItem('childMoney');
        var sumaFinala = parseInt(inainte) + parseInt(suma);
        document.getElementById('gest-conturi-copii-bani-copil').innerHTML = sumaFinala + " RON";
        sessionStorage.setItem('childMoney', sumaFinala + " RON");
        document.getElementById('gest-conturi-copii-input').value = "";

        let childrenList = JSON.parse(sessionStorage.getItem('childrenList'));
        if (childrenList != null) {
            childrenList.forEach((child) => {
                if (child.name === sessionStorage.getItem('childName')) {
                    child.money = sumaFinala + " RON";
                    console.log(child.money);
                }
            });
        }
        sessionStorage.setItem('childrenList', JSON.stringify(childrenList));
        console.log(sessionStorage.getItem('childrenList'));
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