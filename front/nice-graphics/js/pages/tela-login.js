const tituloContainerLogin = document.querySelector('.titulo-container-login');
const btnFormLogin = document.getElementById('btn-form-login');
const btnFormCadastro = document.getElementById('btn-form-cadastro');
const formulario = document.getElementById('formulario');

function criarElemento(tag, atributos = {}, texto = '') {
    const elemento = document.createElement(tag);
    Object.entries(atributos).forEach(([chave, valor]) => {
        elemento.setAttribute(chave, valor);
    });
    if (texto) elemento.textContent = texto;
    return elemento;
}

function atualizarFormulario(tipo) {
    formulario.innerHTML = '';

    if (tipo === 'login') {
        tituloContainerLogin.textContent = "Entre com a sua conta";

        formulario.appendChild(criarElemento('label', { for: 'input-email' }, 'E-mail'));
        formulario.appendChild(criarElemento('input', { type: 'email', name: 'input-email', id: 'input-email', placeholder: 'exemplo@email.com', required: 'yes' }));

        formulario.appendChild(criarElemento('label', { for: 'input-senha' }, 'Senha'));
        formulario.appendChild(criarElemento('input', { type: 'password', name: 'input-senha', id: 'input-senha', placeholder: '********', required: 'yes' }));

        formulario.appendChild(criarElemento('button', { type: 'submit', class: 'btn-formulario' }, 'Entrar'));

        btnFormLogin.classList.add('ativo');
        btnFormCadastro.classList.remove('ativo');
    } else {
        tituloContainerLogin.textContent = "Crie a sua conta";

        formulario.appendChild(criarElemento('label', { for: 'input-email' }, 'E-mail'));
        formulario.appendChild(criarElemento('input', { type: 'email', name: 'input-email', id: 'input-email', placeholder: 'exemplo@email.com', required: 'yes' }));

        formulario.appendChild(criarElemento('label', { for: 'input-senha' }, 'Senha'));
        formulario.appendChild(criarElemento('input', { type: 'password', name: 'input-senha', id: 'input-senha', placeholder: '********', required: 'yes' }));

        formulario.appendChild(criarElemento('label', { for: 'input-senha-confirmacao' }, 'Confirme a senha'));
        formulario.appendChild(criarElemento('input', { type: 'password', name: 'input-senha-confirmacao', id: 'input-senha-confirmacao', placeholder: '********', required: 'yes' }));

        const divAceite = criarElemento('div', { class: 'container-ckb-aceite' });
        const checkbox = criarElemento('input', { type: 'checkbox', name: 'ckb-aceite-termos-politica', id: 'ckb-aceite-termos-politica', required: 'yes' });
        const label = criarElemento('label', { for: 'ckb-aceite-termos-politica' });

        label.innerHTML = `Ao fazer a inscrição, aceito os <span><a href="#">Termos de Serviço</a></span> e concordo com a <span><a href="#">Política de Privacidade</a></span> da Nice Graphics.`;

        divAceite.appendChild(checkbox);
        divAceite.appendChild(label);
        formulario.appendChild(divAceite);

        formulario.appendChild(criarElemento('button', { type: 'submit', class: 'btn-formulario' }, 'Cadastrar'));

        btnFormCadastro.classList.add('ativo');
        btnFormLogin.classList.remove('ativo');
    }
}

// Adicionando eventos
btnFormLogin.addEventListener('click', () => atualizarFormulario('login'));
btnFormCadastro.addEventListener('click', () => atualizarFormulario('cadastro'));

document.addEventListener("DOMContentLoaded", () => {
    atualizarFormulario('login');
});
