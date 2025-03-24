const areaDoTexto = document.getElementById("txtarea-texto-usuario")
const container = document.getElementById('container-graficos-gerados');
const selectElement = document.getElementById('select-opcoes-ia');
const resumoTexto = document.getElementById('resumo-texto');




// Função para criar um gráfico em um canvas específico
function criarGraficoPeloTipoEscolhido(tipo, ctx) {
    if (ctx.grafico) {
        ctx.grafico.destroy(); // Remove o gráfico do tipo anterior
    }

    ctx.grafico = new Chart(ctx.canvas, {
        type: tipo,
        data: dadosGrafico,
        options: getOpcoesGrafico("teste", "tipo de dado (vou fz no back)", false)
    });
}

function criarGraficoPelosDadosAPI(dados, ctx) {
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
                backgroundColor: [
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(255, 159, 64, 0.6)',
                    'rgba(255, 205, 86, 0.6)',
                    'rgba(75, 192, 192, 0.6)',
                    'rgba(54, 162, 235, 0.6)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)'
                ],
                borderWidth: 2
            }]
        },
        options: getOpcoesGrafico(dados)
    });
}

// Dados e opções reutilizáveis
const dadosGrafico = {
    labels: ["Eletrônicos", "Vestuário", "Alimentos", "Móveis", "Cosméticos"],
    datasets: [{
        label: 'Faturamento por Categoria',
        data: [50000, 30000, 40000, 35000, 25000],
        backgroundColor: [
            'rgba(255, 99, 132, 0.6)',
            'rgba(255, 159, 64, 0.6)',
            'rgba(255, 205, 86, 0.6)',
            'rgba(75, 192, 192, 0.6)',
            'rgba(54, 162, 235, 0.6)'
        ],
        borderColor: [
            'rgb(255, 99, 132)',
            'rgb(255, 159, 64)',
            'rgb(255, 205, 86)',
            'rgb(75, 192, 192)',
            'rgb(54, 162, 235)'
        ],
        borderWidth: 2
    }],
};

// Adicionando o plugin Chart.js Datalabels
Chart.register(ChartDataLabels);

function getOpcoesGrafico(dados) {
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
                    boxHeight: 15
                }
            },
            datalabels: {
                display: true,
                color: [
                    'rgb(122, 47, 63)',
                    'rgb(102, 65, 27)',
                    'rgb(85, 69, 30)',
                    'rgb(22, 56, 56)',
                    'rgb(18, 47, 66)'
                ],
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
                        size: 14
                    },
                    color: '#555'
                }
            },
            y: {
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
                        size: 16
                    },
                    color: '#555'
                },
                beginAtZero: true
            }
        },
        animation: {
            duration: 2000,
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
        const dadosProntos = await prepararDados(data);
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

async function prepararDados(data) {

    let dadosProntos = []

    const contextos = data.textAnalysis.contexts
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

    // Cria a div que irá conter o gráfico e os botões
    const divGrafico = document.createElement('div');
    divGrafico.classList.add('container-grafico-acoes');

    // Define o HTML interno da div, com o canvas que receberá o gráfico
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
        <button class="opcao-download opcao-download-pdf">
          <i class='bx bxs-file-pdf'></i>
          <p>Download em PDF</p>
        </button>
        <button class="opcao-download opcao-download-png">
          <i class='bx bxs-file-png'></i>
          <p>Download em PNG</p>
        </button>
      </div>
    `;

    divGrafico.querySelectorAll('.opcao-grafico').forEach(botao => {
        botao.addEventListener('click', function () {
            let tipoGrafico = '';

            if (this.classList.contains('grafico-barra')) tipoGrafico = 'bar';
            else if (this.classList.contains('grafico-linha')) tipoGrafico = 'line';
            else if (this.classList.contains('grafico-pizza')) tipoGrafico = 'pie';
            else if (this.classList.contains('grafico-rosquinha')) tipoGrafico = 'doughnut';
            else if (this.classList.contains('grafico-radar')) tipoGrafico = 'radar';
            dadosDoGrafico.tipo = tipoGrafico


            criarGraficoPelosDadosAPI(dadosDoGrafico, cfx);
        });
    });

    // Adiciona a div ao container principal
    container.appendChild(divGrafico);

    // Seleciona o canvas que acabou de ser inserido
    const cfx = divGrafico.querySelector('canvas').getContext('2d');

    criarGraficoPelosDadosAPI(dadosDoGrafico, cfx);
}

fetch('http://localhost:8080/user/models/details', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    }
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        data.models.forEach(modelo => {
            const newOption = document.createElement('option');
            newOption.value = modelo.name;
            newOption.textContent = modelo.name;
            newOption.disabled = modelo.userRole != "ROLE_USER"
            selectElement.appendChild(newOption);
        })
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });

