$(document).ready(() => {
    $('.camera-button').on('click', function () {
        $('.camera-button').css('color', 'black');
        let billList = JSON.parse(sessionStorage.getItem('billList'));

        billList.push({'name' : 'Factura' + randomNumberGenerator(1, 100), 'price': randomNumberGenerator(50, 550) + ' RON'});
        sessionStorage.setItem('billList', JSON.stringify(billList));
        sessionStorage.setItem('populate', 'true');
        window.location = './6_1_gestionare_facturi.html';
    });
});


function randomNumberGenerator(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}