document.addEventListener('DOMContentLoaded', () => {
    const researchTypeSelect = document.getElementById('research-type');
    const paperFields = document.getElementById('paper-fields');
    const bukuFields = document.getElementById('buku-fields');

    if (researchTypeSelect) {
        researchTypeSelect.addEventListener('change', (event) => {
            paperFields.style.display = 'none';
            bukuFields.style.display = 'none';
            const selectedValue = event.target.value;
            if (selectedValue === 'paper') {
                paperFields.style.display = 'block';
            } else if (selectedValue === 'buku') {
                bukuFields.style.display = 'block';
            }
        });
    }

    const mainForm = document.getElementById('main-form');

    if (mainForm) {
        mainForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const nim = document.getElementById('nim').value.trim();
            const nama = document.getElementById('nama').value.trim();
            const semester = document.getElementById('semester').value.trim();

            if (nim === '' || nama === '' || semester === '') {
                alert('Semua kolom (NIM, Nama, Semester) wajib diisi!');
                return;
            }

            if (isNaN(nim) || isNaN(semester)) {
                alert('NIM dan Semester harus berupa angka!');
                return;
            }

            const semesterAngka = parseInt(semester, 10);
            if (semesterAngka < 3) {
                alert('Mahasiswa Belum bisa mengajukan pustaka digital!\nHarus semester 2 ke atas!');
                return;
            }

            alert('Validasi berhasil! Data siap diproses.');
        });
    }
});