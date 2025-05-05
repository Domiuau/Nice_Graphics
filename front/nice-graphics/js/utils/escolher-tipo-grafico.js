import { adicionarGrafico, prepararDados, definirContainer} from './adicionar-grafico-na-tela.js'

definirContainer("container-graficos-gerados")

const areaDoTexto = document.getElementById("txtarea-texto-usuario")
const container = document.getElementById('container-graficos-gerados');
const selectElement = document.getElementById('select-opcoes-ia');
const resumoTexto = document.getElementById('resumo-texto');
const iconeCarregamentoContainer = document.getElementById('container-carregador');
const botaoGerar = document.getElementById("btn-gerar")
const botaoCorAcessivel = document.getElementById("btn-acessibilidade-alternar-paleta")

const coresPrincipaisSemOpacidade = [
    '#FF6384',
    '#FF9F40',
    '#FFCD56',
    '#4BC0C0',
    '#36A2EB'
];

let jsonInicial = prepararDados([
    {
        "description": "Gráfico de exemplo",
        "type": "bar",
        "numberRepresented": "Número do exemplo",
        "data": [
            {
                "field": "Exemplo 1",
                "value": 600
            },
            {
                "field": "Exemplo 2",
                "value": 300
            },
            {
                "field": "Exemplo 3",
                "value": 1000
            },
            {
                "field": "Exemplo 4",
                "value": 700
            }
        ]
    }
])

let dadosProntosGeral









// Adicionando o plugin Chart.js Datalabels
Chart.register(ChartDataLabels);







// Encontra todos os containers de gráficos
document.querySelectorAll('.container-grafico-acoes').forEach(container => {
    // Identifica o canvas dentro do container
    const ctx = container.querySelector('canvas').getContext('2d');

    // Criar o gráfico inicial para o container
  //  criarGraficoPeloTipoEscolhido('pie', ctx); // Gráfico inicial aqui, como 'bar'

    // Adiciona eventos aos botões dentro de cada container
    container.querySelectorAll('.opcao-grafico').forEach(botao => {
        botao.addEventListener('click', function () {
            let tipoGrafico = '';

            if (this.classList.contains('grafico-barra')) tipoGrafico = 'bar';
            else if (this.classList.contains('grafico-linha')) tipoGrafico = 'line';
            else if (this.classList.contains('grafico-pizza')) tipoGrafico = 'pie';
            else if (this.classList.contains('grafico-rosquinha')) tipoGrafico = 'doughnut';
            else if (this.classList.contains('grafico-radar')) tipoGrafico = 'radar';

          //  criarGraficoPeloTipoEscolhido(tipoGrafico, ctx);
        });
    });
});

async function fetchDados(texto) {
    try {
        const selectedValue = selectElement.value;
        const authToken = localStorage.getItem("authToken");

        const headers = { "Content-Type": "application/json" };
        if (authToken) headers["Authorization"] = `Bearer ${authToken}`;

        const response = await fetch(
            `http://localhost:8080/user/analyze/${selectedValue}`,
            {
                method: "POST",
                headers,
                body: JSON.stringify({ text: texto })
            }
        );

        const data = await response.json();

        if (!response.ok) {

            container.innerHTML = `
            <div class="error-container">
                <div class="error-message">
                  <p><strong>Erro ao gerar gráficos:</strong></p>
                  <p>${data.causa} — ${data.message}</p>
                </div>
            </div>
            `;


  

            return null
        }

        iconeCarregamentoContainer.style.display = "none";
        resumoTexto.innerText = data.textAnalysis.summary;
        const dadosProntos = prepararDados(data.textAnalysis.contexts);
        return dadosProntos;

    } catch (error) {
        console.error("Erro ao buscar os dados:", error);
        return null;
    }
}

if (botaoGerar != null) {
    botaoGerar.addEventListener("click", async function () {

        container.innerHTML = ''
        container.innerHTML = `
                <div id="container-carregador" class="container-carregador">
            <i class='bx bx-loader-alt bx-spin icone-carregamento' ></i>
            <p>Gerando gráficos...</p>
            </div>
            `
        const iconeCarregamentoContainer = document.getElementById('container-carregador');
    
        iconeCarregamentoContainer.style.display = "flex";
    
    
        dadosProntosGeral = await fetchDados(areaDoTexto.value)
    
        if (dadosProntosGeral != null) {
    
            colocarGraficosNaTela()
    
        }
    
    
    
    })
}



function colocarGraficosNaTela() {

    container.innerHTML = ''

    dadosProntosGeral.forEach(dados => {
        adicionarGrafico(dados)
    })

}





async function validateToken(token) {
    try {
        const response = await fetch('http://localhost:8080/auth/' + token, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        //aqui estão os dados do usuário, se ele estiver logado
        const usuario = await response.json()
        //const paragrafo = document.getElementById("usuario-logado")
        // paragrafo.textContent = "username: " + usuario.username
        console.log(usuario)

        return usuario;
    } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
    }
}

async function fetchModels() {
    try {
        const response = await fetch('http://localhost:8080/user/models/details', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data);

        const tokenData = localStorage.getItem('authToken')
        console.log('Token recuperado:', tokenData);

        const user = await validateToken(tokenData);
        console.log("Usuário validado no fetchModels:", user);

        data.models.forEach(modelo => {
            const newOption = document.createElement('option');
            const modelRole = modelo.userRole
            newOption.value = modelo.name;
            newOption.textContent = modelo.name + ", até " + modelo.charactersLimit + " caracteres";
            console.log(user == null)
            newOption.disabled = modelRole == "ROLE_USER" ? false : (modelRole == "ROLE_USER_AUTHENTICATED" ? user == null : (user == null ? true : user.role != "PREMIUM_USER"))
            selectElement.appendChild(newOption);
        });
    } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
    }
}

fetchModels()
fetchGeracoesPreviews()

async function fetchGeracoesPreviews() {
    console.log("asd")
    const authToken = localStorage.getItem("authToken");
    const params = new URLSearchParams(window.location.search);
    const idAnalise = params.get('valor') || '';

    if (idAnalise) {
        fetch('http://localhost:8080/user/analyze/generation/' + idAnalise, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${authToken}`
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {

                console.log(data)
                let dadosProntos = prepararDados(data.contexts)
                container.innerHTML = ''
                console.log("dados prontos" + dadosProntos)
                dadosProntos.forEach(dados => {
                    adicionarGrafico(dados)
                })

                resumoTexto.innerText = data.summary;
                areaDoTexto.innerText = data.analyzedText;
                //window.history.replaceState({}, document.title, window.location.pathname);



            })
    } else {
        console.log("nao")
    }



}



botaoCorAcessivel.addEventListener('click', () => {

    if (dadosProntosGeral != null) {
        colocarGraficosNaTela()

    } else {
        window.location.reload();
    }





})

adicionarGrafico(jsonInicial[0])

