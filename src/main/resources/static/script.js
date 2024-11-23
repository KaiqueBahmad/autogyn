const abrirVeiculos = document.querySelectorAll('.abrirVeiculo');

abrirVeiculos.forEach(abrir => {
    abrir.addEventListener('click', () => {
        if (abrir.classList.contains('menu')) {
            abrirVeiculos[1].toggleAttribute('hidden');
        }
    });
});