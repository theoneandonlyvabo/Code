document.addEventListener('DOMContentLoaded', () => {

    const btnAplikasi = document.getElementById('btnAplikasi');
    const btnDataDiri = document.getElementById('btnDataDiri');
    const popup = document.getElementById('popup');
    const btnClose = document.getElementById('btnClose');

    if (btnAplikasi) {
        btnAplikasi.addEventListener('click', () => {
            window.location.href = 'form.html';
        });
    }

    if (btnDataDiri && popup) {
        btnDataDiri.addEventListener('click', () => {
            popup.style.display = 'flex';
        });
    }

    if (btnClose && popup) {
        btnClose.addEventListener('click', () => {
            popup.style.display = 'none';
        });
    }

});