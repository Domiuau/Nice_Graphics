// Função para criar um gráfico em um canvas específico
function criarGraficoPeloTipoEscolhido(tipo, ctx) {
    if (ctx.grafico) {
        ctx.grafico.destroy(); // Remove o gráfico do tipo anterior
    }

    ctx.grafico = new Chart(ctx.canvas, {
        type: tipo,
        data: dadosGrafico,
        options: opcoesGrafico
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

const opcoesGrafico = {
    responsive: true,
    plugins: {
        title: {
            display: true,
            text: 'Faturamento por Categoria',
            font: { size: 22, weight: 'bold' },
            color: '#222'
        },
        tooltip: {
            callbacks: {
                label: function (tooltipItem) {
                    return `Valor: ${tooltipItem.raw} vendas`;
                }
            }
        },
        legend: {
            display: true,
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
            color: 'black',
            font: {
                weight: 'light',
                size: 14
            },
            anchor: 'end',
            align: 'top',
            padding: { bottom: 10 },
            formatter: (value) => `${value}`
        }
    },
    scales: {
        x: {
            title: {
                display: true,
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
                text: 'Faturamento (R$)',
                font: {
                    size: 18, 
                    weight: 'bold'
                },
                color: '#333', 
                padding: { bottom: 40 }, // Maior espaço entre os números do eixo Y e a legenda
                align: 'center' 
            },
            ticks: {
                font: {
                    size: 14
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



// Encontra todos os containers de gráficos
document.querySelectorAll('.container-grafico-acoes').forEach(container => {
    // Identifica o canvas dentro do container
    const ctx = container.querySelector('canvas').getContext('2d');

    // Criar o gráfico inicial para o container
    criarGraficoPeloTipoEscolhido('bar', ctx); // Gráfico inicial aqui, como 'bar'

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
