document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("currency").addEventListener("change", ev => {
        let value = ev.target.value;
        if (value) {
            get("/api/?symbols=" + value);
        }
    })
});

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