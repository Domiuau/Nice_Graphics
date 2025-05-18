document.getElementById("assinar-prime").addEventListener("click", () => {

const token = localStorage.getItem('authToken');

if (!token) {
    window.location.href = `login.html`;

} else {


  fetch(`http://localhost:8080/auth/gifPremium/${encodeURIComponent(token)}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
  })
    .then(response => {
      if (!response.ok) {
        throw new Error(`Erro na requisição: ${response.status} ${response.statusText}`);
      }
        alert("Premium ativado com sucesso!")
          window.location.href = `gerar-grafico.html`;
    })

    .catch(err => {
      console.error('Falha ao chamar a API:', err);
    });
}

})
