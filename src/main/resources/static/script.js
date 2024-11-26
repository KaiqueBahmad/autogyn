const abrirVeiculos = document.querySelectorAll('.abrirVeiculo');
const abrirOs = document.querySelectorAll('.abrirOs')

abrirVeiculos.forEach(abrir => {
    abrir.addEventListener('click', () => {
        if (abrir.classList.contains('menu')) {
            abrirVeiculos[1].toggleAttribute('hidden');
        }
    });
});

abrirOs.forEach(abrir => {
    abrir.addEventListener('click', () => {
        if (abrir.classList.contains('menu')) {
            console.log(abrirOs[1])
            abrirOs[1].toggleAttribute('hidden');
        }
    });
});
