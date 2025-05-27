const ctx = document.getElementById('meuGrafico').getContext('2d');

const meuGraficoRosquinha = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["Eletrônicos", "Vestuário", "Alimentos", "Móveis", "Cosméticos"],
        datasets: [{
            label: 'Faturamento por Categoria',
            data: [50000, 30000, 40000, 35000, 25000],
            backgroundColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(255, 159, 64, 0.6)',
                'rgba(255, 205, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(201, 203, 207, 0.6)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
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



  

