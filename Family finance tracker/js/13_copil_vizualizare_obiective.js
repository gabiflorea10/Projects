$(document).ready(() => {

    getObjectiveList();

});

function getObjectiveList() {
    let obiectiveList = JSON.parse(sessionStorage.getItem('obiectiveList' ));

    if (obiectiveList === null) {

    }
    else {
        populateList(obiectiveList);
    }
}

function populateList(list) {
    let listStringPopulator = "";
    list.forEach((objective) => {

        let procent = objective.economisiti/objective.suma *100;
        let img = "..\\resources\\pictures\\tree0.png";
        if(procent>=0 && procent<20) {
            img = "..\\resources\\pictures\\tree0.png";
        } else if(procent>=20 && procent<40) {
            img = "..\\resources\\pictures\\tree1.png";
        } else if(procent>=40 && procent<60) {
            img = "..\\resources\\pictures\\tree2.png";
        } else if(procent>=60 && procent<80) {
            img = "..\\resources\\pictures\\tree3.png";
        } else if(procent>=80 && procent<100) {
            img = "..\\resources\\pictures\\tree4.png";
        } else {
            img = "..\\resources\\pictures\\tree5.png";
        }

        if(objective.suma == objective.economisiti) {
            listStringPopulator += '<li>' +
                '       <img src=' + '\'' + img + '\'' + '>' +
                '            <div>' +
                '                <label>' + objective.name + '</label><br>' +
                '                <label>Cost total : ' + objective.suma + ' lei</label><br>' +
                '                <label>Economisiți : ' + objective.economisiti + ' lei</label><br>' +
                '                <label style="color:greenyellow;">Obiectiv finalizat</label><br>' +

                '            </div>' +
                '</li>';
        } else {
            listStringPopulator += '<li>' +
                '       <img src=' + '\'' + img + '\'' + '>' +
                '            <div>' +
                '                <label>' + objective.name + '</label><br>' +
                '                <label>Cost total : ' + objective.suma + ' lei</label><br>' +
                '                <label>Economisiți : ' + objective.economisiti + ' lei</label><br>' +
                '                <button onclick="editeaza(\'' + objective.name + '\',\'' + objective.suma.toString() + '\'+\'' + 0 + '\')">Economisește</button>' +

                '            </div>' +
                '</li>';
        }
    });

    $('#lista_obiective').append(listStringPopulator);
}

function editeaza(p1, p2,p3) {

    sessionStorage.setItem('objectiveName', p1);
    sessionStorage.setItem('necessaryMoney',p2);
    sessionStorage.setItem('savedMoney',p3);
    console.log(p1+" "+p2+" "+p3);
    window.location = "./16_copil_editare_obiectiv.html";

}
