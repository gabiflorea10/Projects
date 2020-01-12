$(document).ready(() => {

    $('#add_button').on('click', function () {

        console.log("Am apasat adauga");
        const name = $('#name').val();
        const suma = $('#suma').val();
        const date = $('#data_finalizare').val();
        const mod_economire = $('#mod_economisire').val();
        if (!name || !suma || !date || !mod_economire) {
            document.getElementById("suma_alerta").innerHTML = "Vă rugăm completați toate câmpurile!";
            return;
        } else if (!checkSum(suma)) {
            document.getElementById("suma_alerta").innerHTML = "Vă rugăm introduceți o sumă corectă!";
            return
        } else {
            document.getElementById("suma_alerta").innerHTML = "";
        }

        const newElement = {name: name, date: date, suma: suma, mod_economisire: mod_economire, economisiti: 0 };
        console.log(name + " " + suma + " " + date + " " + mod_economire);

        let obiectiveList = sessionStorage.getItem('obiectiveList');
        if (obiectiveList == null) {
            obiectiveList = [newElement];
        } else {
            obiectiveList = JSON.parse(obiectiveList);
            obiectiveList = obiectiveList.concat(newElement);
            console.log('2,', obiectiveList);

        }
        sessionStorage.setItem('obiectiveList', JSON.stringify(obiectiveList));
        window.location = "./13_copil_vizualizare_obiective.html";
    });

});

function checkSum(sum) {
    var digits = /^[0-9]+$/;

    if (sum.match(digits) && sum.length > 0 && sum[0] != 0) {
        return true;
    } else {
        return false;
    }
}
