$(document).ready(() => {
    $('#economisire-add-button').on('click', function () {

        let name = $('#economisire-add-name').val();

        let economyList = JSON.parse(sessionStorage.getItem('economyList'));

        sessionStorage.setItem('populateEconomisireList', 'true');

        if (verifyName()) {
            economyList.push({
                'name': name,
                'money': 0 + ' RON'
            });
            sessionStorage.setItem('economyList', JSON.stringify(economyList));
            window.location = "./11_economisire.html";
        } else {
            document.getElementById("mesaj").innerHTML = "Numele este nul sau contine si alte caractere care nu sunt litere";
        }
    });
});

function verifyName() {
    var name = $('#economisire-add-name').val();
    var letters = /^[a-zA-Z]+$/;
    if (name.match(letters) && name.length != 0) {
        return true;
    } else {
        return false;
    }
}
