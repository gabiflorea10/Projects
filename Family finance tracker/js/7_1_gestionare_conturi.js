$(document).ready(() => {

    let listStringPopulator = "";
    let conturiCurente = sessionStorage.getItem('conturiCurente');
    console.log(conturiCurente);
    if (conturiCurente === null) {
        let conturiCurenteList = [{'nume': 'BRD - lei', 'suma': '1500', 'moneda':'Lei'}, {'nume': 'BRD - euro',
            'suma': '900', 'moneda':'Euro'
        }, {'nume': 'BT - lei', 'suma': '2500', 'moneda':'Lei'}];
        sessionStorage.setItem('conturiCurenteList', JSON.stringify(conturiCurenteList));
        conturiCurenteList.forEach((cont) => {
            listStringPopulator +=
                '<li class="conturi-main-list-element">' +
                '<i class="fas fa-dollar-sign conturi-icon-dollar"></i>'+
                '<p class="conturi-list-element-name">' + cont.nume+ '</p>' +
                '<p class="conturi-main-list-element-suma">' + cont.suma + " " + cont.moneda +'</p>' +
                '</li>';
        });
        $('#conturi-curente-main-list').html(listStringPopulator);
    } else {
        let conturiCurenteList = JSON.parse(sessionStorage.getItem('conturiCurenteList'));
        conturiCurenteList.forEach((cont) => {
            listStringPopulator +=
                '<li class="conturi-main-list-element">' +
                '<i class="fas fa-dollar-sign conturi-icon-dollar"></i>'+
                '<p class="conturi-list-element-name">' + cont.nume+ '</p>' +
                '<p class="conturi-main-list-element-suma">' + cont.suma + " " + cont.moneda +'</p>' +
                '</li>';
        });
        $('#conturi-curente-main-list').html(listStringPopulator);
    }

    listStringPopulator = "";
    let conturiEconomii = sessionStorage.getItem('conturiEconomii');
    console.log(conturiEconomii);
    if (conturiEconomii === null) {
        let conturiEconomiiList = [{'nume': 'ING - lei', 'suma': '6000', 'moneda':'Lei'}, {'nume': 'CEC - euro',
            'suma': '5500', 'moneda':'Euro'
        }];
        sessionStorage.setItem('conturiEconomiiList', JSON.stringify(conturiEconomiiList));
        conturiEconomiiList.forEach((cont) => {
            listStringPopulator +=
                '<li class="conturi-main-list-element">' +
                '<i class="fas fa-dollar-sign conturi-icon-dollar"></i>'+
                '<p class="conturi-list-element-name">' + cont.nume+ '</p>' +
                '<p class="conturi-main-list-element-suma">' + cont.suma + " " + cont.moneda +'</p>' +
                '</li>';
        });
        $('#conturi-economii-main-list').html(listStringPopulator);
    } else {
        let conturiEconomiiList = JSON.parse(sessionStorage.getItem('conturiEconomiiList'));
        conturiEconomiiList.forEach((cont) => {
            listStringPopulator +=
                '<li class="conturi-main-list-element">' +
                '<i class="fas fa-dollar-sign conturi-icon-dollar"></i>'+
                '<p class="conturi-list-element-name">' + cont.nume+ '</p>' +
                '<p class="conturi-main-list-element-suma">' + cont.suma + " " + cont.moneda +'</p>' +
                '</li>';
        });
        $('#conturi-economii-main-list').html(listStringPopulator);
    }

    listStringPopulator = "";
    let carduri = sessionStorage.getItem('carduri');
    console.log(carduri);
    if (carduri === null) {
        let carduriList = [{'nume': 'Visa BT - lei', 'suma': '2500', 'moneda':'Lei', 'expiration':'10/22', 'proprietar': 'Popescu Ion', 'banca': "BT"},
            {'nume': 'Master BRD - lei', 'suma': '4100', 'moneda':'Lei', 'expiration':'11/21', 'proprietar': 'Ionescu Vasile', 'banca': "BRD"},
            {'nume': 'Master BCR - euro', 'suma': '600', 'moneda':'Euro', 'expiration':'10/20', 'proprietar': 'Ivanescu Mircea', 'banca': "BCR"}];
        sessionStorage.setItem('carduriList', JSON.stringify(carduriList));
        carduriList.forEach((card) => {
            var identificatorCard = "cardID" + card.nume;
            identificatorCard = identificatorCard.replace(/ /g, '');
            var identificatorDiv = "div" + identificatorCard;
            listStringPopulator +=
                '<li class="carduri-main-list-element" id=' + identificatorCard + '>' +
                '<i class="fas fa-credit-card credit-card-icon"></i>'+
                '<p class="carduri-list-element-name">' + card.nume+ '</p>' +
                '<p class="conturi-main-list-element-suma">' + card.suma + " " + card.moneda +'</p>' +
                '</li>' +
                '<div class="informatii-aditionale-card" style="display: none" id='+ identificatorDiv +'>'
                + '<p>' +'Data expirarii: ' + card.expiration + '</p>'
                + '<p>' +'Proprietar: ' + card.proprietar + '</p>'
                + '<p>' +'Banca: ' + card.banca + '</p>' + '</div>';

        });
        $('#carduri-main-list').html(listStringPopulator);
    } else {
        let carduriList = JSON.parse(sessionStorage.getItem('carduriList'));
        carduriList.forEach((card) => {
            var identificatorCard = "cardID" + card.nume;
            identificatorCard = identificatorCard.replace(/ /g, '');
            var identificatorDiv = "div" + identificatorCard;
            listStringPopulator +=
                '<li class="carduri-main-list-element" id=' + identificatorCard + '>' +
                '<i class="fas fa-credit-card credit-card-icon"></i>'+
                '<p class="carduri-list-element-name">' + card.nume+ '</p>' +
                '<p class="conturi-main-list-element-suma">' + card.suma + " " + card.moneda +'</p>' +
                '</li>' +
                '<div class="informatii-aditionale-card" style="display: none" id='+ identificatorDiv +'>'
                + '<p>' +'Data expirarii: ' + card.expiration + '</p>'
                + '<p>' +'Proprietar: ' + card.proprietar + '</p>'
                + '<p>' +'Banca: ' + card.banca + '</p>' + '</div>';

        });
        $('#carduri-main-list').html(listStringPopulator);
    }

    var toggleStateCurente = false;

    $('#clickable-curente').click(function(){

        var sageata;
        if(toggleStateCurente === false)
        {
            $('#right-arrow-curente').remove();
            sageata = '<i class="fas fa-chevron-down gest-conturi-down-arrow" id="down-arrow-curente"></i>';
            $('#clickable-curente').prepend(sageata);
            toggleStateCurente = true;
        }
        else{
            $('#down-arrow-curente').remove();
            sageata = '<i class="fas fa-chevron-right gest-conturi-right-arrow" id="right-arrow-curente"></i>';
            $('#clickable-curente').prepend(sageata);
            toggleStateCurente = false;
        }

        $('.conturi-curente-list').slideToggle();


    });

    var toggleStateEconomii = false;

    $('#clickable-economii').click(function(){
        var sageata;
        if(toggleStateEconomii === false)
        {
            $('#right-arrow-economii').remove();
            sageata = '<i class="fas fa-chevron-down gest-conturi-down-arrow" id="down-arrow-economii"></i>';
            $('#clickable-economii').prepend(sageata);
            toggleStateEconomii = true;
        }
        else{
            $('#down-arrow-economii').remove();
            sageata = '<i class="fas fa-chevron-right gest-conturi-right-arrow" id="right-arrow-economii"></i>';
            $('#clickable-economii').prepend(sageata);
            toggleStateEconomii = false;
        }

        $('.conturi-economii-list').slideToggle();
    });

    var toggleStateCarduri = false;

    $('#clickable-carduri').click(function(){

        var sageata;
        if(toggleStateCarduri === false)
        {
            $('#right-arrow-carduri').remove();
            sageata = '<i class="fas fa-chevron-down gest-conturi-down-arrow" id="down-arrow-carduri"></i>';
            $('#clickable-carduri').prepend(sageata);
            toggleStateCarduri = true;
        }
        else{
            $('#down-arrow-carduri').remove();
            sageata = '<i class="fas fa-chevron-right gest-conturi-right-arrow" id="right-arrow-carduri"></i>';
            $('#clickable-carduri').prepend(sageata);
            toggleStateCarduri = false;
        }

        $('.carduri-list').slideToggle();
    });

    $('.carduri-main-list-element').click(function(){

        var identificatorDiv = "#div" + $(this).attr('id');

        $(identificatorDiv).slideToggle();

    });

    $('.conturi-main-list-element').click(function(){

        let tipCont = $(this).closest("ul").attr('id');

        if(tipCont === 'conturi-curente-main-list'){
            sessionStorage.setItem("tipContModificare", "curente");
        }
        else{
            sessionStorage.setItem("tipContModificare", "economii");
        }

        let nume = $(this).find('.conturi-list-element-name').text();
        sessionStorage.setItem("numeContModificare", nume);

        let suma = $(this).find('.conturi-main-list-element-suma').text();
        sessionStorage.setItem("sumaContModificare", suma);

        window.location = "./7_3_gestionare_conturi.html";

    });

});