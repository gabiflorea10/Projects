$(document).ready(() => {

    let page = sessionStorage.getItem('gest-chelt-category');
    switch (page) {
        case "car": {
            $('#gest-cheltuieli-listare-top-title').text('Auto');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-car gest-cheltuieli-listare-top-left-icon');

            chooseList('car');

            break;
        }

        case "shirt": {
            $('#gest-cheltuieli-listare-top-title').html('Haine');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-tshirt gest-cheltuieli-listare-top-left-icon');

            let cheltuieliList = JSON.parse(sessionStorage.getItem('cheltuieliList-' + page));

            chooseList('shirt');

            break;
        }

        case "gamepad": {
            $('#gest-cheltuieli-listare-top-title').html('Divertisment');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-gamepad gest-cheltuieli-listare-top-left-icon');

            chooseList('gamepad');

            break;
        }
        case "hamburger": {
            $('#gest-cheltuieli-listare-top-title').html('Mancare');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-hamburger gest-cheltuieli-listare-top-left-icon');

            chooseList('hamburger');

            break;
        }
        case "gas-pump": {
            $('#gest-cheltuieli-listare-top-title').html('Combustibil');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-gas-pump gest-cheltuieli-listare-top-left-icon');

            chooseList('gas-pump');

            break;
        }
        case "cart": {
            $('#gest-cheltuieli-listare-top-title').html('General');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-shopping-cart gest-cheltuieli-listare-top-left-icon');

            chooseList('cart');

            break;
        }
        case "gift": {
            $('#gest-cheltuieli-listare-top-title').html('Cadouri');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-gift gest-cheltuieli-listare-top-left-icon');


            chooseList('gift');

            break;
        }
        case "pills": {
            $('#gest-cheltuieli-listare-top-title').html('Sanatate');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-pills gest-cheltuieli-listare-top-left-icon');

            chooseList('pills');

            break;
        }
        case "plane": {
            $('#gest-cheltuieli-listare-top-title').html('Vacante');
            $('#gest-cheltuieli-top-left-icon').attr('class', 'fas fa-plane gest-cheltuieli-listare-top-left-icon');

            chooseList('plane');

            break;
        }
    }

});

function chooseList(page) {
    let cheltuieliList = JSON.parse(sessionStorage.getItem('cheltuieliList-' + page));
    if (cheltuieliList === null) {
        switch (page) {
            case "car": {
                cheltuieliList = [{'description': 'Reparatie garnitura', 'price': '300 RON', 'day': '02', 'month': 'Oct', 'year': '2018'},
                    {'description': 'Sincronizare', 'price': '235 RON', 'day': '04', 'month': 'Dec', 'year': '2019'},
                    {'description': 'ITP', 'price': '70 RON', 'day': '10', 'month': 'Nov', 'year': '2017'}];
                break;
            }

            case "shirt": {
                cheltuieliList = [{'description': 'Tricou', 'price': '38 RON', 'day': '03', 'month': 'Nov', 'year': '2018'},
                    {'description': 'Blugi', 'price': '125 RON', 'day': '11', 'month': 'Aug', 'year': '2019'},
                    {'description': 'Camasa', 'price': '72 RON', 'day': '08', 'month': 'Jun', 'year': '2018'}];

                break;
            }

            case "gamepad": {
                cheltuieliList = [{'description': 'GTA', 'price': '245 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Gamepad XBOX', 'price': '223 RON', 'day': '11', 'month': 'Aug', 'year': '2019'},
                    {'description': 'G2a Gift Card', 'price': '100 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];

                break;
            }
            case "hamburger": {
                cheltuieliList = [{'description': 'Salata', 'price': '34 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Ulei', 'price': '20 RON', 'day': '11', 'month': 'Nov', 'year': '2019'},
                    {'description': 'Zahar', 'price': '15 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];

                break;
            }
            case "gas-pump": {
                cheltuieliList = [{'description': 'Benzina', 'price': '34 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Motorina', 'price': '20 RON', 'day': '11', 'month': 'Nov', 'year': '2019'},
                    {'description': 'GPL', 'price': '15 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];

                break;
            }
            case "cart": {
                cheltuieliList = [{'description': 'Baterii', 'price': '13 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Perie', 'price': '10 RON', 'day': '11', 'month': 'Nov', 'year': '2019'},
                    {'description': 'Hartie', 'price': '20 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];

                break;
            }
            case "gift": {
                cheltuieliList = [{'description': 'Parfum', 'price': '200 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Ciocolata', 'price': '15 RON', 'day': '11', 'month': 'Nov', 'year': '2019'},
                    {'description': 'XBOX One', 'price': '1310 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];
                break;
            }
            case "pills": {
                cheltuieliList = [{'description': 'Algocalmin', 'price': '200 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Paracetamol', 'price': '15 RON', 'day': '11', 'month': 'Nov', 'year': '2019'},
                    {'description': 'Nurofen', 'price': '100 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];
                break;
            }
            case "plane": {
                cheltuieliList = [{'description': 'Excursie Japonia Bilet', 'price': '4000 RON', 'day': '03', 'month': 'Dec', 'year': '2018'},
                    {'description': 'Excursie Elvetia Bilet', 'price': '15222 RON', 'day': '11', 'month': 'Nov', 'year': '2019'},
                    {'description': 'Excursie America Bilet', 'price': '13546 RON', 'day': '07', 'month': 'Jun', 'year': '2018'}];
                break;
            }
        }
    }

    sessionStorage.setItem('cheltuieliList-' + page, JSON.stringify(cheltuieliList));
    populateList(cheltuieliList);
}

function populateList(list) {
    let listStringPopulator = "";
    list.forEach((bill) => {
        listStringPopulator += '<tr>' +
                                    '<td class="gest-cheltuieli-list-date-td" rowspan="2">' +
                                        '<p class="gest-cheltuieli-list-date-day">' + bill.day + '</p>' +
                                        '<p class="gest-cheltuieli-list-date-month">' + bill.month + '</p>' +
                                        '<p class="gest-cheltuieli-list-date-year">' + bill.year + '</p>' +
                                    '</td>' +
                                    '<td class="gest-cheltuieli-list-price-td"><p>' + 'Suma : ' + bill.price + '</p></td>' +
                                '</tr>' +
                                '<tr>' +
                                    '<td class="gest-cheltuieli-list-description-td"><p>' + bill.description + '</p></td>' +
                                '</tr>';
    });
    $('.gest-cheltuieli-list-table').html(listStringPopulator);
}