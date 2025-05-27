preencherFormularioComDados()

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('formulario');
  
    form.addEventListener('submit', async (event) => {
      event.preventDefault();
  
      const name = document.getElementById('input-nome-usuario').value.trim();
      const email = document.getElementById('input-email').value.trim();
      const password = document.getElementById('input-senha').value;
      const senhaConfirmacao = document.getElementById('input-senha-confirmacao').value;
  
      if (password !== senhaConfirmacao) {
        alert('As senhas não coincidem.');
        return;
      }
  
      const payload = {
        name,
        email,
        password
      };
  
      try {
        const response = await fetch('http://localhost:8080/auth/update', {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("authToken")
          },
          body: JSON.stringify(payload)
        });
  
        const result = await response.json();
  
        if (!response.ok) {
          alert(`Erro ao atualizar: ${result.message || 'Erro desconhecido'}`);
          return;
        }
  
        localStorage.setItem('authToken', result.token);
        localStorage.setItem('userName', result.username)

        document.getElementById('input-nome-usuario').value = result.username;
        document.getElementById('input-email').value = result.email;

        document.getElementById('input-senha').value = '';
        document.getElementById('input-senha-confirmacao').value = '';

        location.reload();

        alert('Dados atualizados com sucesso!');



      } catch (error) {
        console.error('Erro ao enviar os dados:', error);
        alert('Ocorreu um erro na comunicação com o servidor.');
      }
    });

    const btnDesativar = document.querySelector('.btn-desativacao-temporaria');
    const btnExcluir  = document.querySelector('.btn-exclusao-conta');
  
    async function sendRequest(url, method = 'POST') {
      try {
        const resp = await fetch(url, {
          method,
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('authToken')}`,
            'Accept': 'application/json'
          }
        });
        if (resp.status === 204) {
          alert('Operação realizada com sucesso!');
        } else {
          const texto = await resp.text();
          throw new Error(`Status ${resp.status}: ${texto}`);
        }
      } catch (err) {
        console.error(err);
        alert('Ocorreu um erro ao processar sua solicitação.');
      }
    }
  
    btnDesativar.addEventListener('click', () => {
      if (confirm('Tem certeza de que deseja desativar temporariamente sua conta?')) {
        sendRequest('http://localhost:8080/auth/disable', 'POST');
        localStorage.clear()
        window.location.href = "/nice-graphics/index.html";
      }
    });
  
    btnExcluir.addEventListener('click', () => {
      if (confirm('Esta ação apagará todos os seus dados permanentemente. Deseja continuar?')) {
        sendRequest('http://localhost:8080/auth/delete/data', 'DELETE');
      }
    });

  });

  async function preencherFormularioComDados() {
    try {
        const response = await fetch('http://localhost:8080/auth/' + localStorage.getItem('authToken'));
        
        if (!response.ok) {
            throw new Error(`Erro ao buscar dados: ${response.status}`);
        }

        const dados = await response.json();

        document.getElementById('input-nome-usuario').value = dados.username || '';
        document.getElementById('input-email').value = dados.email || '';
        
    } catch (erro) {
        console.error('Erro ao preencher o formulário:', erro);
    }
}
  