function menuFlutuanteCliente() {
    const iconeCliente = document.querySelector('.bx-user');
    const header = document.querySelector('.header');
    let menuAberto = false;
    let menuElement = null;

    iconeCliente.addEventListener('click', (event) => {
        event.stopPropagation();

        if (menuAberto) {
            menuElement.remove();
            menuAberto = false;
        } else {
            menuElement = document.createElement('div');
            menuElement.classList.add('opcoes-visualizacao-cliente');
            menuElement.innerHTML = `
                <ul>
                    <li class="nome-usuario">
                        <i class='bx bx-user'></i>
                        <p>Nome Usuário</p>
                    </li>
                    <li class="opcao-usuario" title="Meu Perfil" onclick="window.location.href = './perfil-usuario.html'">
                        <i class='bx bxs-user-detail'></i>
                        <p>Meu Perfil</p>
                    </li>
                    <li class="opcao-usuario" title="Sair" onclick="window.location.href = './login.html'">
                        <i class='bx bx-log-out'></i>
                        <p>Sair</p>
                    </li>
                </ul>
            `;
            header.appendChild(menuElement);
            menuAberto = true;
        }
    });

    // Fecha o menu ao clicar fora
    document.addEventListener('click', (event) => {
        if (menuAberto && !iconeCliente.contains(event.target) && !menuElement.contains(event.target)) {
            menuElement.remove();
            menuAberto = false;
        }
    });
}

menuFlutuanteCliente();
