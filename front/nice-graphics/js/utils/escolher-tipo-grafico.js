import { highlightColors } from './conversor-de-cores.js';

const areaDoTexto = document.getElementById("txtarea-texto-usuario")
const container = document.getElementById('container-graficos-gerados');
const selectElement = document.getElementById('select-opcoes-ia');
const resumoTexto = document.getElementById('resumo-texto');
const botaoHistorico = document.getElementById("btn-historico")



const coresPrincipaisSemOpacidade = [
    '#FF6384',
    '#FF9F40',
    '#FFCD56',
    '#4BC0C0',
    '#36A2EB'
];

const coresPrincipais = [
    '#FF6384', '#FF9F40', '#FFCD56', '#4BC0C0', '#36A2EB',
    '#9966FF', '#C9CBCF', '#8B0000', '#008080', '#00FF7F',
    '#DC143C', '#7FFF00', '#FFD700', '#DA70D6', '#00CED1',
    '#1E90FF', '#FF1493', '#00BFFF', '#FF4500', '#ADFF2F',
    '#FA8072', '#20B2AA', '#9370DB', '#B0C4DE', '#66CDAA',
    '#FF69B4', '#4682B4', '#7CFC00', '#BA55D3', '#5F9EA0',
    '#FF6347', '#40E0D0', '#D2691E', '#9ACD32', '#6A5ACD',
    '#87CEEB', '#FFB6C1', '#BDB76B', '#00FA9A', '#F08080',
    '#CD5C5C', '#3CB371', '#8FBC8F', '#483D8B', '#DDA0DD',
    '#778899', '#6B8E23', '#A0522D', '#48D1CC', '#FF00FF'
];




// Função para criar um gráfico em um canvas específico
function criarGraficoPeloTipoEscolhido(tipo, ctx) {
    if (ctx.grafico) {
        ctx.grafico.destroy(); // Remove o gráfico do tipo anterior
    }

    ctx.grafico = new Chart(ctx.canvas, {
        type: tipo,
        data: dadosGrafico,
        options: getOpcoesGrafico("teste", "tipo de dado (vou fz no back)", true)
    });
}

function criarGraficoPelosDadosAPI(dados, cores, ctx, isDataLabelsActive) {
    if (ctx.grafico) {
        ctx.grafico.destroy(); // Remove o gráfico do tipo anterior
    }


    ctx.grafico = new Chart(ctx.canvas, {
        type: dados.tipo,
        data: {
            labels: dados.labels,
            datasets: [{
                label: dados.descricao,
                data: dados.dados,
                backgroundColor: cores,
                borderColor: cores,
                borderWidth: 2
            }]
        },
        options: getOpcoesGrafico(dados, highlightColors(cores, 50), isDataLabelsActive)
    });
}

// Dados e opções reutilizáveis
const dadosGrafico = {
    labels: ["Eletrônicos", "Vestuário", "Alimentos", "Móveis", "Cosméticos"],
    datasets: [{
        label: 'Faturamento por Categoria',
        data: [50000, 30000, 40000, 35000, 25000],
        backgroundColor: coresPrincipaisSemOpacidade,
        borderColor: coresPrincipais,
        borderWidth: 2
    }],
};

// Adicionando o plugin Chart.js Datalabels
Chart.register(ChartDataLabels);

function getOpcoesGrafico(dados, cores, isDataLabelsActive) {
    return {
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: dados.descricao,
                font: { size: 20, weight: 'bold' },
                color: '#222',
                padding: { bottom: 20 }
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        return `Valor: ${tooltipItem.raw} vendas`;
                    }
                }
            },
            legend: {
                display: (dados.tipo == "doughnut" || dados.tipo == "pie"),
                position: 'top',
                labels: {
                    font: {
                        size: 14,
                        weight: 'bold'
                    },
                    color: '#333',
                    padding: 15,
                    boxWidth: 20,
                    boxHeight: 15,
                    generateLabels: function (chart) {
                        const data = chart.data;
                        if (data.labels.length && data.datasets.length) {
                            return data.labels.map((label, i) => {
                                const dataset = data.datasets[0];
                                const value = dataset.data[i];
                                return {
                                    text: label + ' (' + value + ')',
                                    fillStyle: dataset.backgroundColor[i],
                                    strokeStyle: dataset.borderColor ? dataset.borderColor[i] : '#000',
                                    lineWidth: dataset.borderWidth ? dataset.borderWidth[i] : 1,
                                    hidden: isNaN(value) || chart.getDatasetMeta(0).data[i].hidden,
                                    index: i
                                };
                            });
                        }
                        return [];
                    }
                }
            },
            datalabels: {
                display: isDataLabelsActive,
                color: cores,
                font: {
                    weight: 'bold',
                    size: 16
                },
                anchor: dados.tipo == "line" ? 'bottom' : 'mid',
                align: dados.tipo == "line" ? 'bottom' : 'mid',
                padding: { bottom: 10 },
                formatter: (value) => `${value}`
            }
        },
        scales: {

            x: {
                grid: {
                    display: !(dados.tipo == "doughnut" || dados.tipo == "pie")
                },
                title: {
                    display: false,
                    text: 'Categorias de Produtos',
                    font: {
                        size: 18,
                        weight: 'bold'
                    },
                    color: '#333',
                    padding: { top: 40 } // Espaçamento entre os rótulos e a legenda
                },
                ticks: {
                    font: {
                        size: (dados.tipo == "doughnut" || dados.tipo == "pie") ? 0 : 16
                    },
                    color: '#555'
                }
            },
            y: {
                grid: {
                    display: !(dados.tipo == "doughnut" || dados.tipo == "pie")
                },
                title: {
                    display: true,
                    text: dados.numeroRepresentado,
                    font: {
                        size: 16,
                        weight: 'bold'
                    },
                    color: '#555',
                    padding: { bottom: 22 }, // Maior espaço entre os números do eixo Y e a legenda
                    align: 'center'
                },
                ticks: {
                    font: {
                        size: (dados.tipo == "doughnut" || dados.tipo == "pie") ? 0 : 16
                    },
                    color: '#555'
                },
                beginAtZero: !(dados.tipo == "line" || dados.tipo == "radar")
            }
        },
        animation: {
            duration: 1500,
            easing: 'easeInOutBounce'
        }
    };
}





// Encontra todos os containers de gráficos
document.querySelectorAll('.container-grafico-acoes').forEach(container => {
    // Identifica o canvas dentro do container
    const ctx = container.querySelector('canvas').getContext('2d');

    // Criar o gráfico inicial para o container
    criarGraficoPeloTipoEscolhido('pie', ctx); // Gráfico inicial aqui, como 'bar'

    // Adiciona eventos aos botões dentro de cada container
    container.querySelectorAll('.opcao-grafico').forEach(botao => {
        botao.addEventListener('click', function () {
            let tipoGrafico = '';

            if (this.classList.contains('grafico-barra')) tipoGrafico = 'bar';
            else if (this.classList.contains('grafico-linha')) tipoGrafico = 'line';
            else if (this.classList.contains('grafico-pizza')) tipoGrafico = 'pie';
            else if (this.classList.contains('grafico-rosquinha')) tipoGrafico = 'doughnut';
            else if (this.classList.contains('grafico-radar')) tipoGrafico = 'radar';

            criarGraficoPeloTipoEscolhido(tipoGrafico, ctx);
        });
    });
});

async function fetchDados(texto) {
    try {
        const selectedValue = selectElement.value;
        console.log(selectedValue);

        const authToken = localStorage.getItem("authToken");

        const headers = {
            "Content-Type": "application/json"
        };

        if (authToken) {
            headers["Authorization"] = `Bearer ${authToken}`;
        }

        const response = await fetch(`http://localhost:8080/user/analyze/${selectedValue}`, {
            method: "POST",
            headers: headers,
            body: JSON.stringify({ text: texto })
        });

        if (!response.ok) {
            throw new Error(`Erro: ${response.status} - ${response.statusText}`);
        }

        const data = await response.json();
        resumoTexto.innerText = data.textAnalysis.summary;
        const dadosProntos = prepararDados(data.textAnalysis.contexts);
        return dadosProntos;
    } catch (error) {
        console.error("Erro ao buscar os dados:", error);
        return null;
    }
}


const botaoGerar = document.getElementById("btn-gerar")
botaoGerar.addEventListener("click", async function () {



    const dadosProntos = await fetchDados(areaDoTexto.value)

    container.innerHTML = ''
    console.log("dados: " + dadosProntos)
    dadosProntos.forEach(dados => {
        adicionarGrafico(dados)
    })


})

function prepararDados(data) {

    let dadosProntos = []

   // const contextos = data.textAnalysis.contexts
    const contextos = data

    let dados = []
    let labels = []

    console.log(contextos)

    contextos.forEach(contexto => {

        dados = []
        labels = []

        contexto.data.forEach(dado => {
            dados.push(dado.value)
            labels.push(dado.field)
        })

        dadosProntos.push({
            tipo: contexto.type,
            descricao: contexto.description,
            labels: labels,
            dados: dados,
            numeroRepresentado: contexto.numberRepresented
        })
    })

    console.log(dadosProntos)
    return dadosProntos

}

function adicionarGrafico(dadosDoGrafico) {

    const divGrafico = document.createElement('div');
    divGrafico.classList.add('container-grafico-acoes');

    divGrafico.innerHTML = `
    <div class="opcoes-tipos-de-grafico">
      <button class="opcao-grafico grafico-barra">
        <i class='bx bx-bar-chart-alt-2'></i>
        <p>Barra</p>
      </button>
      <button class="opcao-grafico grafico-linha">
        <i class='bx bx-line-chart'></i>
        <p>Linha</p>
      </button>
      <button class="opcao-grafico grafico-pizza">
        <i class='bx bx-pie-chart-alt-2'></i>
        <p>Pizza</p>
      </button>
      <button class="opcao-grafico grafico-rosquinha">
        <i class='bx bx-doughnut-chart'></i>
        <p>Rosquinha</p>
      </button>
      <button class="opcao-grafico grafico-radar">
        <i class='bx bx-radar'></i>
        <p>Radar</p>
      </button>
    </div>
  
    <div class="container-grafico-gerado">
      <canvas></canvas>
    </div>

      <div class="opcoes-download" id="opcoes-download">
        <button class="opcao-download opcao-download-pdf" id="opcao-download-pdf">
          <i class='bx bxs-file-pdf'></i>
          <p>Download em PDF</p>
        </button>
        <button class="opcao-download opcao-download-png" id="opcao-download-png">
          <i class='bx bxs-file-png'></i>
          <p>Download em PNG</p>
        </button>
      </div>
  

    <div class="toggle-wrapper">
      <label class="switch">
        <input type="checkbox" id="toggle-btn" checked>
        <span class="slider round"></span>
      </label>
      <span class="toggle-label">Números no gráfico</span>
    </div>
  


  `;


    let indexCor = 0;

    dadosDoGrafico.dados.forEach(dado => {

        divGrafico.innerHTML += `
        <input type="color" 
           id="seletorCores-${indexCor}" 
           value="${coresPrincipais[indexCor % coresPrincipais.length]}" 
           class="seletor-cor">
    `;

        indexCor++;
    });

    container.appendChild(divGrafico);
    const cfx = divGrafico.querySelector('canvas').getContext('2d');
    const toggle = divGrafico.querySelector('#toggle-btn');

    divGrafico.querySelectorAll('input[type="color"]').forEach(cor => {
        cor.addEventListener('input', function () {
            console.log('Cor selecionada:', this.value);
            let cores = []

            divGrafico.querySelectorAll('input[type="color"]').forEach(corNova => {

                cores.push(corNova.value)

            })

            criarGraficoPelosDadosAPI(dadosDoGrafico, cores, cfx, toggle.checked);


        });


    })



    toggle.addEventListener('change', () => {

        let cores = []

        divGrafico.querySelectorAll('input[type="color"]').forEach(corNova => {


            cores.push(corNova.value)

        })

        criarGraficoPelosDadosAPI(dadosDoGrafico, cores, cfx, toggle.checked);
    });

    divGrafico.querySelectorAll('.opcao-grafico').forEach(botao => {
        botao.addEventListener('click', function () {
            let tipoGrafico = '';

            if (this.classList.contains('grafico-barra')) tipoGrafico = 'bar';
            else if (this.classList.contains('grafico-linha')) tipoGrafico = 'line';
            else if (this.classList.contains('grafico-pizza')) tipoGrafico = 'pie';
            else if (this.classList.contains('grafico-rosquinha')) tipoGrafico = 'doughnut';
            else if (this.classList.contains('grafico-radar')) tipoGrafico = 'radar';
            dadosDoGrafico.tipo = tipoGrafico

            let cores = []

            divGrafico.querySelectorAll('input[type="color"]').forEach(corNova => {


                cores.push(corNova.value)

            })

            criarGraficoPelosDadosAPI(dadosDoGrafico, cores, cfx, toggle.checked);
        });
    });


    divGrafico.querySelector('#opcao-download-png').addEventListener('click', function () {

        const canvas = divGrafico.querySelector('canvas');
        const link = document.createElement('a');
        link.href = canvas.toDataURL('image/png');
        link.download = dadosDoGrafico.tipo + '-' + dadosDoGrafico.descricao + '.png';

        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    });

    divGrafico.querySelector('#opcao-download-pdf').addEventListener('click', function () {

        const canvas = divGrafico.querySelector('canvas');

        const { jsPDF } = window.jspdf;
        const doc = new jsPDF({
            orientation: 'portrait',
            unit: 'mm',
            format: 'a4'
        });

        const pdfWidth = 150;
        const pdfHeight = (canvas.height * pdfWidth) / canvas.width;

        doc.addImage(canvas.toDataURL('image/png'), 'PNG', 30, 10, pdfWidth, pdfHeight);
        doc.save(dadosDoGrafico.tipo + '-' + dadosDoGrafico.descricao + '.pdf');
    });



    criarGraficoPelosDadosAPI(dadosDoGrafico, coresPrincipais, cfx, toggle.checked);
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
        const paragrafo = document.getElementById("usuario-logado")
        paragrafo.textContent = "username: " + usuario.username
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


botaoHistorico.addEventListener('click', () => {
    window.location.href = `historico.html`;
});








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
                window.history.replaceState({}, document.title, window.location.pathname);



            })
    } else {
        console.log("nao")
    }


}

