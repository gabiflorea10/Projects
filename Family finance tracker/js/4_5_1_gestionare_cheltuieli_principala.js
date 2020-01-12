$(document).ready(() => {
    $('.gest-cheltuieli-main-list-element').on('click', function () {
        switch (this.id) {
            case "gest-cheltuieli-li-car":{
                sessionStorage.setItem('gest-chelt-category', 'car');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }

            case "gest-cheltuieli-li-shirt":{
                sessionStorage.setItem('gest-chelt-category', 'shirt');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }

            case "gest-cheltuieli-li-gamepad":{
                sessionStorage.setItem('gest-chelt-category', 'gamepad');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }
            case "gest-cheltuieli-li-hamburger":{
                sessionStorage.setItem('gest-chelt-category', 'hamburger');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }
            case "gest-cheltuieli-li-gas-pump":{
                sessionStorage.setItem('gest-chelt-category', 'gas-pump');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }
            case "gest-cheltuieli-li-cart":{
                sessionStorage.setItem('gest-chelt-category', 'cart');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }
            case "gest-cheltuieli-li-gift":{
                sessionStorage.setItem('gest-chelt-category', 'gift');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }
            case "gest-cheltuieli-li-pills":{
                sessionStorage.setItem('gest-chelt-category', 'pills');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }
            case "gest-cheltuieli-li-plane":{
                sessionStorage.setItem('gest-chelt-category', 'plane');
                window.location = "./4_5_2_gestionare_cheltuieli_listare.html";
                break;
            }

        }
    });
});