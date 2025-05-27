const container = document.getElementById('historicoContainer');

fetchGeracoesPreviews();



function formatarData(isoString) {
    const d = new Date(isoString);
    const dia = String(d.getDate()).padStart(2, '0');
    const mes = String(d.getMonth() + 1).padStart(2, '0');
    const ano = d.getFullYear();
    return `${dia}/${mes}/${ano}`;
}

async function fetchGeracoesPreviews() {
    console.log("asd")
    const authToken = localStorage.getItem("authToken");

    fetch('http://localhost:8080/user/analyze/generations/previews', {
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

            data.analisys.forEach(item => {
                const divAnalise = document.createElement('div');
                divAnalise.classList.add('texto-analisado');
            
                const pData = document.createElement('p');
                pData.classList.add('data-analise');
                pData.textContent = formatarData(item.creationDate);
                divAnalise.appendChild(pData);
            
                const pResumo = document.createElement('p');
                pResumo.classList.add('resumo-analise');
                pResumo.textContent = item.summary?.trim().length
                    ? item.summary
                    : 'Nenhum resumo disponível.';
                divAnalise.appendChild(pResumo);
            
                const pGraf = document.createElement('p');
                pGraf.classList.add('qtd-graficos-container');
                pGraf.innerHTML = `
                <i class='bx bxs-pie-chart-alt-2'></i>
                <span class="qtd-graficos-gerados-analise">${item.chartsCount}</span>
              `;
                divAnalise.appendChild(pGraf);
            
                const btn = document.createElement('button');
                btn.classList.add('btn-visualizar-analise');
                btn.innerHTML = `
                <span>Visualizar</span>
                <i class='bx bx-right-arrow-alt'></i>
              `;
                btn.addEventListener('click', () => visualizarAnalise(item.id));
                divAnalise.appendChild(btn);
            
                container.appendChild(divAnalise);
            });

        })
}

function visualizarAnalise(id) {
    console.log('Visualizar análise com id:', id);
    window.location.href = `gerar-grafico.html?valor=${id}`;


}

