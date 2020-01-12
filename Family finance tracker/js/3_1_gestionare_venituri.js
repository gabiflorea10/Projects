$(document).ready(() => {
    let listStringPopulator = "";
    let addedIncome = sessionStorage.getItem('addedIncome');
    console.log(addedIncome);
    if (addedIncome === null) {
        console.log("enters here");
        let incomeList = [{'tip': 'Chirie', 'descriere': 'Chirie spatiu', 'suma': '1500 RON'}, {'tip': 'Salariu',
            'descriere': 'Salariu firma',
            'suma': '4500 RON'
        }, {'tip': 'Altele', 'descriere': 'Profit afacere', 'suma': '2500 RON'}];
        let totalIncome = 8500;
        sessionStorage.setItem('incomeList', JSON.stringify(incomeList));
        sessionStorage.setItem('totalIncome', totalIncome);
        incomeList.forEach((income) => {
            listStringPopulator +=
                '<li class="gest-venituri-main-list-element">' +
                '<p class="gest-venituri-main-list-element-name">' + income.tip + " - " + income.descriere + '</p>' +
                '<p class="gest-venituri-main-list-element-suma">' + income.suma + '</p>' +
                '</li>';
        });
        $('#gest-venituri-main-list').html(listStringPopulator);
    } else {
        let incomeList = JSON.parse(sessionStorage.getItem('incomeList'));
        incomeList.forEach((income) => {
            listStringPopulator +=
                '<li class="gest-venituri-main-list-element">' +
                '<p class="gest-venituri-main-list-element-name">' + income.tip + " - " + income.descriere + '</p>' +
                '<p class="gest-venituri-main-list-element-suma">' + income.suma + '</p>' +
                '</li>';
        });
        $('#gest-venituri-main-list').html(listStringPopulator);
    }

    let totalIncome = sessionStorage.getItem('totalIncome');
    $('#pagina-venituri-total-value').text(totalIncome + ' RON');
});