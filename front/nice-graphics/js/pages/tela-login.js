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

        formulario.appendChild(criarElemento('label', { for: 'input-usuario' }, 'Nome de Usuário'));
        formulario.appendChild(criarElemento('input', { type: 'text', name: 'input-usuario', id: 'input-usuario', placeholder: 'Digite seu nome de usuário', required: 'yes' }));

        formulario.appendChild(criarElemento('label', { for: 'input-senha' }, 'Senha'));
        formulario.appendChild(criarElemento('input', { type: 'password', name: 'input-senha', id: 'input-senha', placeholder: '********', required: 'yes' }));

        const btnLogin = criarElemento('button', { type: 'submit', class: 'btn-formulario', id: 'btn-login' }, 'Entrar');
        formulario.appendChild(btnLogin);

        btnLogin.addEventListener('click', (event) => {
            event.preventDefault();
            const usuario = document.getElementById('input-usuario').value;
            const senha = document.getElementById('input-senha').value;

            fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    login: usuario,
                    senha: senha
                })
            })
                .then(response => {
                    if (!response.ok) {
                        alert("Login ou senha inválidos. Verifique seu login e senha e tenta novamente")
                        throw new Error(`Erro HTTP! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.token) {
                        localStorage.setItem('authToken', data.token);
                        localStorage.setItem('userName', data.username)
                        console.log('Token armazenado com sucesso:', localStorage.getItem('authToken'));
                        window.location.href = "gerar-grafico.html";
                    } else {
                        console.warn('Nenhum token encontrado na resposta.');
                    }
                })
                .catch(error => console.error('Erro:', error));

        });

        btnFormLogin.classList.add('ativo');
        btnFormCadastro.classList.remove('ativo');
    } else {
        tituloContainerLogin.textContent = "Crie a sua conta";

        formulario.appendChild(criarElemento('label', { for: 'input-usuario' }, 'Nome de Usuário'));
        formulario.appendChild(criarElemento('input', { type: 'text', name: 'input-usuario', id: 'input-usuario', placeholder: 'Escolha um nome de usuário', required: 'yes' }));

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

        const btnRegistro = criarElemento('button', { type: 'submit', class: 'btn-formulario', id: 'btn-registro' }, 'Cadastrar');
        formulario.appendChild(btnRegistro);

        btnRegistro.addEventListener('click', (event) => {
            event.preventDefault();
            const usuario = document.getElementById('input-usuario').value;
            const email = document.getElementById('input-email').value;
            const senha = document.getElementById('input-senha').value;
            const senhaConfirmacao = document.getElementById('input-senha-confirmacao').value;
            console.log("Cadastro confirmado");
            console.log("Usuário:", usuario, "E-mail:", email, "Senha:", senha, "Confirmação:", senhaConfirmacao);

        fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                login: usuario,
                email: email,
                senha: senha
            })
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(err => {
                        alert(err.message);
                        throw new Error(err.message);
                    });
                }
                return response.json();
            })
            .then(data => {
                if (data.token) {
                    localStorage.setItem('authToken', data.token);
                    localStorage.setItem('userName', data.username);
                    console.log('Token armazenado com sucesso:', localStorage.getItem('authToken'));
                    window.location.href = "gerar-grafico.html";
                } else {
                    console.warn('Nenhum token encontrado na resposta.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });


        });

        btnFormCadastro.classList.add('ativo');
        btnFormLogin.classList.remove('ativo');
    }
}

btnFormLogin.addEventListener('click', () => atualizarFormulario('login'));
btnFormCadastro.addEventListener('click', () => atualizarFormulario('cadastro'));

document.addEventListener("DOMContentLoaded", () => {
    atualizarFormulario('login');
});
