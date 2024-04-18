function submitRegisterForm() {
    const form = document.getElementById('registerForm');
    const formData = new FormData(form);

    // Convertendo os dados do formulário para JSON
    const jsonObject = {};
    formData.forEach((value, key) => {
        jsonObject[key] = value;
    });
    const jsonData = JSON.stringify(jsonObject);

    fetch('http://localhost:8080/carro/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // Definindo o tipo de conteúdo como JSON
        },
        body: jsonData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao enviar o formulário.');
        }
        return response.json();
    })
    .then(data => {
        // Aqui você pode lidar com a resposta do servidor
        console.log(data);
        // Redirecionar para outra página após o registro bem-sucedido, se necessário
        window.location.href = '../telaLogin.html';
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}
