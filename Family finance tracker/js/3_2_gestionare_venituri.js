$(document).ready(() => {
    $('#gest-venituri-add-income-btn-adauga').on('click', function () {

        let tip = $('#gest-venituri-add-tip').val();
        let descriere = $('#gest-venituri-add-description').val();
        let suma = $('#gest-venituri-add-suma').val();

        if(descriere === "" || suma === ""){
            $("#mesaj-eroare").text("Completati toate campurile")
        }
        else{
            let incomeList = JSON.parse(sessionStorage.getItem('incomeList'));

            sessionStorage.setItem('totalIncome', parseInt(sessionStorage.getItem('totalIncome')) + parseInt(suma));

            sessionStorage.setItem('addedIncome', 'true');

            incomeList.push({
                'tip': tip,
                'descriere': descriere,
                'suma': suma + ' RON'
            });

            sessionStorage.setItem('incomeList', JSON.stringify(incomeList));
            window.location = "./3_1_gestionare_venituri.html";
        }

    });

});