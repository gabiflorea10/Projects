$(document).ready(() => {
    $('.camera-button').on('click', function () {
        let page = sessionStorage.getItem('gest-chelt-category');
        let cheltuieliList = JSON.parse(sessionStorage.getItem('cheltuieliList-' + page));
        let day = randomNumberGenerator(1, 31);
        let dayString = "";
        if (day > 9) {
            dayString = day;
        } else {
            dayString = '0' + day;
        }

        let month = randomNumberGenerator(1, 12);
        let monthString = "";
        switch (month) {
            case 1: {
                monthString = 'Jan';
                break;
            }
            case 2: {
                monthString = 'Feb';
                break;
            }
            case 3: {
                monthString = 'Mar';
                break;
            }
            case 4: {
                monthString = 'Apr';
                break;
            }
            case 5: {
                monthString = 'May';
                break;
            }
            case 6: {
                monthString = 'Jun';
                break;
            }
            case 7: {
                monthString = 'Jul';
                break;
            }
            case 8: {
                monthString = 'Aug';
                break;
            }
            case 9: {
                monthString = 'Sep';
                break;
            }
            case 10: {
                monthString = 'Oct';
                break;
            }
            case 11: {
                monthString = 'Nov';
                break;
            }
            case 12: {
                monthString = 'Dec';
                break;
            }

        }
        let price  = randomNumberGenerator(20, 1320);
        let year = randomNumberGenerator(2017, 2019);
        if (window.confirm("Doriti sa adaugati articolul cu pretul " + price + " RON la data de " + dayString + "-" + monthString + "-" + year + " ?")) {
            cheltuieliList.push({
                'description': 'Notita' + randomNumberGenerator(1, 146),
                'price': price + ' RON',
                'day': dayString,
                'month': monthString,
                'year': year
            });

            sessionStorage.setItem('cheltuieliList-' + page, JSON.stringify(cheltuieliList));
            window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
        }
    });

});


function randomNumberGenerator(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}