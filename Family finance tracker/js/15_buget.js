$(document).ready(() =>{
    if (sessionStorage.getItem("buget_value") === null){
        sessionStorage.setItem("buget_value", "3500 RON");
        $('.buget-value').text("3500 RON");
    }
    else{
        let bugetValue = sessionStorage.getItem("buget_value");
        $('.buget-value').text(bugetValue + " RON");
    }

    $('#buget-change-button').on('click', function () {
        let bugetInputVal = $('#buget-change-input').val();
        if (bugetInputVal !== "") {
            sessionStorage.setItem("buget_value", bugetInputVal);
            $('.buget-value').text(bugetInputVal + " RON");
            $('#buget-input-error').text("");

        }
        else{
            $('#buget-input-error').text("Campul nu poate fi gol");
        }

    })
});