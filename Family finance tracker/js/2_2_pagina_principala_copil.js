$(document).ready(() => {

    $('#pagina-principala-li-banii-mei').on('click', function () {
        window.location = "./14_copil_banii_mei.html";
    });

    $('#pagina-principala-li-adauga-obiectiv').on('click', function () {
        window.location = "./12_copil_adaugare_obiectiv.html";
    });

    $('#pagina-principala-li-obiective-adaugate').on('click', function () {
        window.location = "./13_copil_vizualizare_obiective.html";
    });

    $('#pagina-principala-reguli').on('click', function () {
        window.location = "./17_copil_reguli.html";
    });

    $('#logout-button-copil').on('click', function () {
        sessionStorage.clear();
        window.location = "./1_login.html";
    });

        let obiectiveList = JSON.parse(sessionStorage.getItem('obiectiveList' ));

        if (obiectiveList === null) {

            const newElement1 = {name: "Bicicletă", date: "01/02/2020", suma: "500", mod_economisire: "săptămânal", economisiti: "100"};
            const newElement2 = {name: "Excursie Disneyland", date: "01/02/2020", suma: "2000", mod_economisire: "săptămânal", economisiti: "1200"};

            obiectiveList = [newElement1];
            obiectiveList = obiectiveList.concat(newElement2);
            sessionStorage.setItem('obiectiveList', JSON.stringify(obiectiveList));

        }


});