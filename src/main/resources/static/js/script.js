document.addEventListener("DOMContentLoaded", function () {
    loadCurrencies();
});

function loadCurrencies() {
    let currencies = JSON.parse(get("/api").responseText);
    if (currencies && currencies.length > 0) {
        let select = document.getElementById("currency");
        for (let i = 0; i < currencies.length; i++) {
            let option = document.createElement("option");
            let currency = currencies[i];
            option.value = currency["code"];
            option.innerText = currency["code"] + " - " + currency["name"];
            select.appendChild(option);
        }
    }
}

function get(url) {
    let Httpreq = new XMLHttpRequest();
    Httpreq.open("GET", url, false);
    Httpreq.send();

    Httpreq.onload = function() {
        if (Httpreq.status !== 200) {
            alert(`Ошибка ${Httpreq.status}: ${Httpreq.statusText}`);
        }
    };
    return Httpreq;
}