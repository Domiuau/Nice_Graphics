window.onload = function definirHeaderUsuario() {

    const opcoesUsuarioStatus = document.getElementById('opcoes-status-usuario');
    let userName = localStorage.getItem("userName");

    //FAZER O IF DO USUÁRIO ESTAR LOGADO OU NÃO

    //Se ESTIVER logado faça
    if (userName) {

        opcoesUsuarioStatus.innerHTML = ``;
        opcoesUsuarioStatus.innerHTML = `
            <div class="icone-usuario">
                <div class="user-name"> 
                    <p> Bem vindo(a), ${userName}!</p>
                </div>
                
                <button class="btn-icone-visualizar-historico" title="Visualizar Histórico" onclick="window.location.href = '../pages/historico.html'"><i class='bx bx-history'></i></button>
                <button class="btn-icone-usuario" title="Ver opções perfil"><i class='bx bx-user'></i></button>
            </div>
        `;

        menuFlutuanteCliente();

    } else { //Se NÃO ESTIVER logado faça
        opcoesUsuarioStatus.innerHTML = ``;
        opcoesUsuarioStatus.innerHTML = `
            <div>
                <ul class="navbar-header-buttons">
                    <li>
                        <button class="btn-entrar" onclick="window.location.href='../pages/login.html'">Entrar</button>
                    </li>
                    <li>
                        <button class="btn-cadastro" onclick="window.location.href='../pages/login.html'">Cadastre-se</button>
                    </li>
                </ul>
            </div>
        `;

    }



}


function menuFlutuanteCliente() {
    const iconeCliente = document.querySelector('.bx-user');
    const header = document.querySelector('.header');
    let userName = localStorage.getItem("userName");
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
                        <p>${userName}</p>
                    </li>
                    <li class="opcao-usuario" title="Meu Perfil" onclick="window.location.href = '../pages/perfil-usuario.html'">
                        <i class='bx bxs-user-detail'></i>
                        <p>Meu Perfil</p>
                    </li>
                    <li class="opcao-usuario" id="menu-sair" title="Sair" onclick="window.location.href = '../index.html'">
                        <i class='bx bx-log-out'></i>
                        <p>Sair</p>
                    </li>
                </ul>
            `;
            header.appendChild(menuElement);
            menuAberto = true;

            document.getElementById("menu-sair").addEventListener('click', () => {

                localStorage.clear()
        
            })


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


