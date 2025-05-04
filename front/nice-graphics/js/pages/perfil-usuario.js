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
  });

  async function preencherFormularioComDados() {
    try {
        const response = await fetch('http://localhost:8080/auth/' + localStorage.getItem('authToken'));
        
        if (!response.ok) {
            throw new Error(`Erro ao buscar dados: ${response.status}`);
        }

        const dados = await response.json();

        // Preenche os campos do formulário
        document.getElementById('input-nome-usuario').value = dados.username || '';
        document.getElementById('input-email').value = dados.email || '';
        // senha não vem no JSON, então o campo permanece vazio
    } catch (erro) {
        console.error('Erro ao preencher o formulário:', erro);
    }
}
  