function menuFlutuanteCliente() {
    const iconeCliente = document.querySelector('.bx-user');
    const header = document.querySelector('.header');
    
    iconeCliente.addEventListener('click', () => {

        header.innerHTML += `
            <div class="opcoes-visualizacao-cliente">
                <ul>
                    <li title="Meu Cadastro" onclick="window.location.href = './perfil-usuario.html'">
                        <i class='bx bxs-user-detail'></i>
                        <p>Meu Perfil</p>
                    </li>
                    <li title="Meus Pedidos" onclick="window.location.href = './historico-usuario.html'">
                        <i class='bx bx-history'></i>
                        <p>Meu Histórico</p>
                    </li>
                </ul>
            </div>
        `;

    });
}

menuFlutuanteCliente();