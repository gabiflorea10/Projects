$(document).ready(() => {
    $('#conturi-copii-add-button').on('click', function () {

        let name = $('#conturi-copii-add-name').val();
        let money = $('#conturi-copii-add-money').val();

        let childrenList = JSON.parse(sessionStorage.getItem('childrenList'));

        sessionStorage.setItem('populateList', 'true');

        if (verifyName()) {
            if (verifyMoney()) {
                childrenList.push({
                    'name': name,
                    'money': money + ' RON'
                });
                sessionStorage.setItem('childrenList', JSON.stringify(childrenList));
                window.location = "./8_9_conturi_copii.html";
            } else {
                document.getElementById("mesaj").innerHTML = "Campul acesta trebuie sa contina doar cifre sau sa nu inceapa cu 0";
            }
        } else {
            document.getElementById("mesaj").innerHTML = "Numele este nul sau contine si alte caractere care nu sunt litere";
        }
    });
});

function verifyName() {
    var name = $('#conturi-copii-add-name').val();
    var letters = /^[a-zA-Z]+$/;
    if (name.match(letters) && name.length != 0) {
        return true;
    } else {
        return false;
    }
}

function verifyMoney() {
    var money = $('#conturi-copii-add-money').val();
    var digits = /^[0-9]+$/;
    if (money.match(digits) && money.length != 0 && money[0]!=0) {
        return true;
    } else {
        return false;
    }
}