$(document).ready(() => {
    $('#gest-cheltuieli-add-receipt-picture').on('click', function () {
        window.location = "./4_5_4_gestionare_cheltuieli_adaugare_automata_poza.html";
    });

    $('#gest-cheltuieli-add-receipt-btn-adauga').on('click', function () {
        let price = $('#gest-cheltuieli-add-price').val();
        let description = $('#gest-cheltuieli-add-description').val();
        let date = $('#gest-cheltuieli-add-date').val();
        let page = sessionStorage.getItem('gest-chelt-category');
        let cheltuieliList = JSON.parse(sessionStorage.getItem('cheltuieliList-' + page));

        let day = date.split("-")[2];
        let month = date.split('-')[1];
        let year = date.split('-')[0];

        if (price === "" || description === "" || date === "") {
            $('#gest-cheltuieli-error-message').text("Campurile nu pot fi goale");
        }
        else{

            cheltuieliList.push({
                'description': description,
                'price': price + 'RON',
                'day' : day,
                'month' : getMonth(month),
                'year': year
            });

            $('#gest-cheltuieli-error-message').text("");
            sessionStorage.setItem('cheltuieliList-' + page, JSON.stringify(cheltuieliList));
            window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
        }



    });

});

function getMonth(month) {
    let monthString = "";
    switch (month) {
        case '1': {
            monthString = 'Jan';
            break;
        }
        case '2': {
            monthString = 'Feb';
            break;
        }
        case '3': {
            monthString = 'Mar';
            break;
        }
        case '4': {
            monthString = 'Apr';
            break;
        }
        case '5': {
            monthString = 'May';
            break;
        }
        case '6': {
            monthString = 'Jun';
            break;
        }
        case '7': {
            monthString = 'Jul';
            break;
        }
        case '8': {
            monthString = 'Aug';
            break;
        }
        case '9': {
            monthString = 'Sep';
            break;
        }
        case '10': {
            monthString = 'Oct';
            break;
        }
        case '11': {
            monthString = 'Nov';
            break;
        }
        case '12': {
            monthString = 'Dec';
            break;
        }

    }
    return monthString;
}