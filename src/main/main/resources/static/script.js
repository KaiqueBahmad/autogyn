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


const selectBox = document.querySelector('.abrirMultiSelect');
const optionsList = document.querySelector('.select-box ul');
const selectedOptionsDiv = document.querySelector('.selected-options');

selectBox.addEventListener('click', function() {
    selectBox.parentNode.classList.toggle('open')
});

const checkboxes = document.querySelectorAll('.select-box input[type="checkbox"]');
checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function() {
    const selected = [];
    checkboxes.forEach(cb => {
        if (cb.checked) {
        selected.push(cb.parentElement.textContent.trim());
        }
    });
    selectedOptionsDiv.textContent = `Acessórios: ${selected.join(', ')}`;
    });
});

// mostrar o certo
const os = document.querySelectorAll('.listaOs')
const editorOs = document.querySelector('.edicaoOs')
os.forEach(aberta =>{
    aberta.addEventListener('click', () =>{
        editorOs.removeAttribute("style")
        aberta.parentNode.setAttribute('style', 'display: none')
    })
})

editorOs.parentNode.children[1].children[5].children[0].addEventListener('click', () =>{
    os.forEach(aberta =>{
        aberta.parentNode.removeAttribute("style")
    })
    editorOs.setAttribute('style', 'display: none')
})

