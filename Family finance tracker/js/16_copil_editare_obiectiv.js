

$(document).ready(() => {
    let obiectiveList = sessionStorage.getItem('obiectiveList');
    let objectiveName = sessionStorage.getItem('objectiveName');
    let necessaryMoney = sessionStorage.getItem('necessaryMoney');
    let savedMoney = sessionStorage.getItem('savedMoney');

    let obiectiveListParsed = JSON.parse(sessionStorage.getItem('obiectiveList' ));

    if(obiectiveList!=null) {
        obiectiveListParsed.forEach((objective) => {

            if (objective.name === objectiveName) {
                document.getElementById('data_finalizarii').innerHTML = "Data finalizării : "+''+objective.date+'';
                document.getElementById('mod_economisire').innerHTML = "Mod economisire : "+''+objective.mod_economisire+'';
                document.getElementById('suma_necesara').innerHTML = "Suma totală necesară : "+''+objective.suma+'';
                document.getElementById('suma_economisita').innerHTML = "Suma economisită : "+''+objective.economisiti+'';
                document.getElementById('h1_id').innerHTML = ''+objective.name+'';


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
                var imageParent = document.getElementById("tree_from_edit");
                imageParent.src=img;


            }
        });
    }



    $('#economiseste').on('click', function () {
    let obiectiveList = sessionStorage.getItem('obiectiveList');
    let objectiveName = sessionStorage.getItem('objectiveName');
    let necessaryMoney = sessionStorage.getItem('necessaryMoney');
    let savedMoney = sessionStorage.getItem('savedMoney');

    const suma = $('#suma_adaugata').val();

    if (!checkSum(suma)) {
        document.getElementById("suma_alerta").innerHTML = "Vă rugăm introduceți o sumă corectă!";
        return
    } else {
        document.getElementById("suma_alerta").innerHTML = "";
    }

    let obiectiveListParsed = JSON.parse(sessionStorage.getItem('obiectiveList' ));

    if(obiectiveList!=null) {
        obiectiveListParsed.forEach((objective) => {

            if (objective.name === objectiveName) {
                console.log("Necesara" + objective.suma + "Economisiti" + objective.economisiti);
                console.log("Suma adaugata"+suma);
                var integerValue1 = parseInt(objective.economisiti);
                var integerValue2 = parseInt(suma);

                var needed = objective.suma-objective.economisiti;
                if (integerValue2 > needed) {
                    document.getElementById("suma_alerta").innerHTML = "Mai trebuie economisiți doar " + needed +" lei!";
                    return;
                } else if (integerValue2==needed){
                    window.location = "./13_copil_vizualizare_obiective.html";
                }

                var sum = integerValue1+integerValue2;

                objective.economisiti = sum+"";

                console.log("Economisiti" + objective.economisiti);

                document.getElementById('suma_economisita').innerHTML = "Suma economisită : "+''+objective.economisiti+'';

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
                var imageParent = document.getElementById("tree_from_edit");
                imageParent.src=img;

            }
        });
        sessionStorage.setItem('obiectiveList', JSON.stringify(obiectiveListParsed));

    }

    });

});

function checkSum(sum) {
    var digits = /^[0-9]+$/;

    if (sum.match(digits) && sum.length > 0 && sum[0] != 0) {
        return true;
    } else {
        return false;
    }
}

