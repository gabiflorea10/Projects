$(document).ready(() => {
    let listStringPopulator = "";
    let populate = sessionStorage.getItem('populate');
    console.log(populate);
    if (populate === null) {
        let billList = [{'name': 'RDS', 'price': '300 RON'}, {'name': 'E-ON', 'price': '235 RON'},
            {'name': 'ELECTRICA', 'price': '450 RON'}, {'name': 'DIGI', 'price': '340 RON'}];
        sessionStorage.setItem('billList', JSON.stringify(billList));
        billList.forEach((bill) => {
            listStringPopulator +=
                '<li class="gest-facturi-main-list-element">' +
                '<i class="fas fa-circle gest-facturi-main-list-element-dot"></i>' +
                '<p class="gest-facturi-main-list-element-name">' + bill.name + '</p>' +
                '<p class="gest-facturi-main-list-element-price">' + bill.price + '</p>' +
                '</li>';
        });
        $('#gest-facturi-main-list').html(listStringPopulator);
    } else {
        let billList = JSON.parse(sessionStorage.getItem('billList'));
        billList.forEach((bill) => {
            listStringPopulator +=
                '<li class="gest-facturi-main-list-element">' +
                '<i class="fas fa-circle gest-facturi-main-list-element-dot"></i>' +
                '<p class="gest-facturi-main-list-element-name">' + bill.name + '</p>' +
                '<p class="gest-facturi-main-list-element-price">' + bill.price + '</p>' +
                '</li>';
        });
        $('#gest-facturi-main-list').html(listStringPopulator);
    }


});
