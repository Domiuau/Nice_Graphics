const ctx = document.getElementById('meuGrafico').getContext('2d');

const meuGraficoRosquinha = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["Eletrônicos", "Vestuário", "Alimentos", "Móveis", "Cosméticos"],
        datasets: [{
            label: 'Faturamento por Categoria',
            data: [50000, 30000, 40000, 35000, 25000],
            backgroudColor: [
                'rgba(255, 99, 132, 0.5)',
                'rgba(54, 162, 235, 0.5)',
                'rgba(255, 206, 86, 0.5)',
                'rgba(75, 192, 192, 0.5)',
                'rgba(153, 105, 255, 0.5)'
            ],
            // borderColor: [
            //     'rgba(255, 99, 132, 1)',
            //     'rgba(54, 162, 235, 1)',
            //     'rgba(255, 206, 86, 1)',
            //     'rgba(75, 192, 192, 1)',
            //     'rgba(153, 105, 255, 1)'
            // ],
            borderWidth: 2
        }]
    },
    options: {
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: 'Faturamento por Categoria',
                font: {
                    size: 22
                }
            },
            tooltip: {
                callbacks: {
                    label: function (tooltipItem) {
                        return `Valor: ${tooltipItem.raw} vendas`;
                    }
                }
            }
        },
        animation: {
            duration: 2000,
            easing: 'easeInOutBounce'
        }
    }
});