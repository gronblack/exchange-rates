document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("currency").addEventListener("change", ev => {
        let value = ev.target.value;
        if (value) {
            let gif = JSON.parse(get("/api/?symbols=" + value).responseText);
            if (gif.error) {
                alert(gif.message);
                return;
            }
            document.getElementById("gif").setAttribute("src", gif.url);
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