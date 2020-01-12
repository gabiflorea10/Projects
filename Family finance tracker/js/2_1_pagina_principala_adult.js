$(document).ready(() => {
    if(sessionStorage.getItem('buget_value') === null &&
        sessionStorage.getItem('cheltuieli_value') === null
    )
    {
    sessionStorage.setItem('buget_value', 3500);
    sessionStorage.setItem('cheltuieli_value', 1000);
    }

    let buget_value = sessionStorage.getItem('buget_value');
    let cheltuieli_value = sessionStorage.getItem('cheltuieli_value');

    $('#pagina-principala-disponibil-value').text(buget_value + ' RON');

    $('#pagina-principala-ramas-value').text(buget_value-cheltuieli_value + ' RON');

    let percentage = (buget_value-cheltuieli_value)/buget_value *100;
    $('#percentage_span').text(Math.round(percentage,2)+"%");

    if(percentage < 25) {
        document.getElementById("indicator_progres").className = "c100 p25 big";
    } else if (percentage > 25 && percentage < 50) {
        document.getElementById("indicator_progres").className = "c100 p50 big";
    } else if (percentage>50&&percentage<75) {
        document.getElementById("indicator_progres").className = "c100 p75 big";
    } else {
        document.getElementById("indicator_progres").className = "c100 p100 big";

    }

    $('#pagina-principala-li-cheltuieli').on('click', function () {
        window.location = "./4_5_1_gestionare_cheltuieli_principala.html";
    });

    $('#pagina-principala-li-venituri').on('click', function () {
        window.location = "./3_1_gestionare_venituri.html";
    });

    $('#pagina-principala-li-facturi').on('click', function () {
        window.location = "./6_1_gestionare_facturi.html";
    });

    $('#pagina-principala-li-buget').on('click', function () {
        window.location = "./15_buget.html";
    });

    $('#pagina-principala-li-conturi').on('click', function () {
        window.location = "./7_1_gestionare_conturi.html";
    });

    $('#pagina-principala-li-economisire').on('click',function () {
        window.location = "./11_economisire.html";
    });

    $('#pagina-principala-li-istoric').on('click', function () {
        window.location = "./10_vizualizare_istoric.html";
    });

    $('#pagina-principala-li-copii').on('click', function () {
        window.location = "./8_9_conturi_copii.html";
    });

    $('#logout-button-adult').on('click', function () {
        sessionStorage.clear();
        window.location = "./1_login.html";
    });

});