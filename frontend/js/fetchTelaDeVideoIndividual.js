document.addEventListener("DOMContentLoaded", async function () {
    const videoId = localStorage.getItem('userId');

    if (!videoId) {
        alert('ID do vídeo não encontrado!');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/video/lista/${videoId}/0`);
        const video = await response.json();

        const videoPrincipal = document.getElementById('videoPrincipal');
        const tituloVideo = document.getElementById('tituloVideo');
        const visualizacoes = document.getElementById('visualizacoes');
        const tempoPublicacao = document.getElementById('tempoPublicacao');

        videoPrincipal.src = video.url;
        tituloVideo.textContent = video.titulo;
        visualizacoes.textContent = `${video.visualizacoes} visualizações`;
        tempoPublicacao.textContent = `Postado em: ${video.dataDePostagem}`;

        // Fetch and display related videos
        const relatedResponse = await fetch(`http://localhost:8080/video/lista/${videoId}/0`);
        const relatedVideos = await relatedResponse.json();
        const listaVideosSecundarios = document.getElementById('listaVideosSecundarios');

        relatedVideos.forEach(relatedVideo => {
            const li = document.createElement('li');
            li.classList.add('container-videos-secundarios');

            const videoSecundario = document.createElement('video');
            videoSecundario.classList.add('videosSecundarios');
            videoSecundario.src = relatedVideo.url;
            videoSecundario.controls = true;
            li.appendChild(videoSecundario);


            li.addEventListener('click', function () {
                localStorage.setItem('videoId', relatedVideo.id);
                window.location.href = `telaDeVideoIndividual.html`;
            });

            listaVideosSecundarios.appendChild(li);
        });

    } catch (error) {
        console.error('Error fetching video details:', error);
        alert('Erro ao carregar os detalhes do vídeo.');
    }
});
