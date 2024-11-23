const abrirVeiculos = document.querySelectorAll('.abrirVeiculo');

abrirVeiculos.forEach(abrir => {
    abrir.addEventListener('click', () => {
        if (abrir.classList.contains('menu')) {
            abrirVeiculos[1].toggleAttribute('hidden');
        }
    });
});

const tipoPessoa = document.querySelectorAll(".tipoPessoa")
const cpf = document.querySelector('.pessoaFisica')
const cnpj = document.querySelector('.pessoaJuridica')
tipoPessoa.forEach(tipo =>{
        tipo.addEventListener('click', () =>{
            if(tipo.value == "cpf"){
                cpf.setAttribute("style", "display: flex;")
                cnpj.removeAttribute("style")
            }

            if(tipo.value == "cnpj"){
                cnpj.setAttribute("style", "display: flex;")
                cpf.removeAttribute("style")
            }
        })
})