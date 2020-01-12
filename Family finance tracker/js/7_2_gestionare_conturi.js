$(document).ready(() => {

    $('#gest-conturi-add-tip').change(function () {
        let tip = $('#gest-conturi-add-tip').val();

        if(tip === "Card"){
            $('#form-carduri').css('display',"inline");
            $('#form-cont-curent').css('display',"none");
            $('#form-cont-economii').css('display',"none");
        }
        else if(tip === "ContEconomii"){
            $('#form-carduri').css('display',"none");
            $('#form-cont-curent').css('display',"none");
            $('#form-cont-economii').css('display',"inline");
        }
        else if(tip === "ContCurent"){
            $('#form-carduri').css('display',"none");
            $('#form-cont-curent').css('display',"inline");
            $('#form-cont-economii').css('display',"none");
        }

    });


    $('#gest-conturi-add-btn-adauga').on('click', function () {

        let tip = $('#gest-conturi-add-tip').val();

        if(tip === "ContCurent"){
            let descriere = $('#cont-curent-add-description').val();
            let suma = $('#cont-curent-add-suma').val();
            let moneda = $('#gest-conturi-curente-add-moneda').val();

            if(descriere === "" || suma === ""){
                $("#mesaj-eroare").text("Completati toate campurile")
            }
            else{
                let conturiCurenteList = JSON.parse(sessionStorage.getItem('conturiCurenteList'));
                sessionStorage.setItem('conturiCurente', 'true');
                conturiCurenteList.push({
                    'nume': descriere,
                    'suma': suma,
                    'moneda': moneda
                });
                sessionStorage.setItem('conturiCurenteList', JSON.stringify(conturiCurenteList));
                window.location = "./7_1_gestionare_conturi.html";
            }
        }
        else if(tip === "ContEconomii"){
            let descriere = $('#cont-economii-add-description').val();
            let suma = $('#cont-economii-add-suma').val();
            let moneda = $('#gest-conturi-economii-add-moneda').val();

            if(descriere === "" || suma === ""){
                $("#mesaj-eroare").text("Completati toate campurile")
            }
            else{
                let conturiEconomiiList = JSON.parse(sessionStorage.getItem('conturiEconomiiList'));
                sessionStorage.setItem('conturiEconomii', 'true');
                conturiEconomiiList.push({
                    'nume': descriere,
                    'suma': suma,
                    'moneda': moneda
                });
                sessionStorage.setItem('conturiEconomiiList', JSON.stringify(conturiEconomiiList));
                window.location = "./7_1_gestionare_conturi.html";
            }
        }
        else if(tip === "Card"){
            let descriere = $('#carduri-add-description').val();
            let suma = $('#carduri-add-suma').val();
            let moneda = $('#gest-carduri-add-moneda').val();
            let data = $('#carduri-add-data').val();
            let proprietar = $('#carduri-add-proprietar').val();
            let banca = $('#carduri-add-banca').val();

            if(descriere === "" || suma === "" || data === "" || proprietar === "" || banca === ""){
                $("#mesaj-eroare").text("Completati toate campurile")
            }
            else{
                let carduriList = JSON.parse(sessionStorage.getItem('carduriList'));
                sessionStorage.setItem('carduri', 'true');
                carduriList.push({
                    'nume': descriere,
                    'suma': suma,
                    'moneda': moneda,
                    'expiration': data,
                    'proprietar': proprietar,
                    'banca': banca
                });
                sessionStorage.setItem('carduriList', JSON.stringify(carduriList));
                window.location = "./7_1_gestionare_conturi.html";
            }

        }
    });

});