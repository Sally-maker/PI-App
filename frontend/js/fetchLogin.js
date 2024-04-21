document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.getElementById("loginButton");

    loginButton.addEventListener("click", function () {
        const form = document.getElementById("loginForm");
        const formData = new FormData(form);

        // Convertendo os dados do formulário para JSON
        const jsonObject = {};
        formData.forEach((value, key) => {
            jsonObject[key] = value;
        });
        const jsonData = JSON.stringify(jsonObject);

        fetch("http://localhost:8080/carro/login", {
            method: "POST",
            body: jsonData,
            headers: {
                "Content-Type": "application/json" // Definindo o tipo de conteúdo como JSON
            }
        })
        .then((response) => {
            console.log("aaaaaaa")
            if (!response.ok) {
                throw new Error("Erro ao enviar o formulário.");
            }
            return response.json();
        })
        .then((data) => {
            // Verificar a resposta recebida do servidor
            console.log("Resposta do servidor:", data);
            
            // Verificar se o redirecionamento é alcançado
            console.log("Redirecionando para a próxima página...");
            window.location.href = '../telaDeVideoIndividual.html';
        })
        .catch((error) => {
            console.error("Erro:", error);
        });
    });
});
