$(document).ready(() => {

    let tip = sessionStorage.getItem("tipContModificare");
    let nume = sessionStorage.getItem("numeContModificare");
    let suma = sessionStorage.getItem("sumaContModificare");

    $('#numeContModificareLabel').text("Denumire: " + nume);
    $('#sumaContModificareLabel').text("Suma actuala: " + suma);

    $('#gest-conturi-add-btn-adauga').on('click', function () {

        let suma = $('#cont-curent-add-suma').val();

        if(suma === ""){
            $("#mesaj-eroare").text("Completati suma")
        }
        else{
            if(tip === "curente"){

                let conturiCurenteList = JSON.parse(sessionStorage.getItem('conturiCurenteList'));
                conturiCurenteList.forEach((cont) => {
                    if(cont.nume === nume){
                        cont.suma = suma;
                    }
                });

                sessionStorage.setItem('conturiCurenteList', JSON.stringify(conturiCurenteList));
                sessionStorage.setItem('conturiCurente', 'true');
                window.location = "./7_1_gestionare_conturi.html";
            }
            else if(tip === "economii"){

                let conturiEconomiiList = JSON.parse(sessionStorage.getItem('conturiEconomiiList'));
                conturiEconomiiList.forEach((cont) => {
                    if(cont.nume === nume){
                        cont.suma = suma;
                    }

                });

                sessionStorage.setItem('conturiEconomiiList', JSON.stringify(conturiEconomiiList));
                sessionStorage.setItem('conturiEconomii', 'true');
                window.location = "./7_1_gestionare_conturi.html";
            }
        }

    });

});
