function submitRegisterForm() {
    const form = document.getElementById('registerForm');
    const formData = new FormData(form);

    const jsonObject = {};
    formData.forEach((value, key) => {
        jsonObject[key] = value;
    });
    const jsonData = JSON.stringify(jsonObject);

    fetch('http://localhost:8080/carro/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => {
            if (!response.ok) {
                console.log("bbbbbbbb")
                throw new Error('Erro ao enviar o formulário.');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            window.location.href = '../telaLogin.html';
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}
