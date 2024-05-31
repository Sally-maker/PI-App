document.addEventListener("DOMContentLoaded", async function () {
    const videoId = localStorage.getItem('selectedVideoId');

    if (!videoId) {
        alert('ID do vídeo não encontrado!');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/video/lista/0`);
        const data = await response.json();
        const video = data.content.find(v => v.id == videoId);

        if (!video) {
            alert('Vídeo não encontrado!');
            return;
        }

        const videoPrincipal = document.getElementById('videoPrincipal');
        const tituloVideo = document.getElementById('tituloVideo');
        const visualizacoes = document.getElementById('visualizacoes');
        const tempoPublicacao = document.getElementById('tempoPublicacao');

        videoPrincipal.src = video.url;
        tituloVideo.textContent = video.titulo;
        visualizacoes.textContent = `${video.visualizacoes} visualizações`;
        tempoPublicacao.textContent = `Postado em: ${video.dataDePostagem}`;

        const relatedVideos = data.content.filter(v => v.id != videoId).slice(0, 10);
        const listaVideosSecundarios = document.getElementById('listaVideosSecundarios');

        relatedVideos.forEach(relatedVideo => {
            const li = document.createElement('li');
            li.classList.add('container-videos-secundarios');

            const videoSecundario = document.createElement('iframe');
            videoSecundario.classList.add('videosSecundarios');
            videoSecundario.src = relatedVideo.url;
            videoSecundario.width = "200";
            videoSecundario.height = "113";
            videoSecundario.frameborder = "0";
            videoSecundario.allow = "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture";
            videoSecundario.allowFullscreen = true;
            li.appendChild(videoSecundario);

            const infoSecundaria = document.createElement('div');
            infoSecundaria.classList.add('infoSecundaria');

            const titulo = document.createElement('p');
            titulo.textContent = relatedVideo.titulo;
            infoSecundaria.appendChild(titulo);

            const dataPostagem = document.createElement('p');
            dataPostagem.textContent = `Postado em: ${relatedVideo.dataDePostagem}`;
            infoSecundaria.appendChild(dataPostagem);

            li.appendChild(infoSecundaria);

            li.addEventListener('click', function () {
                localStorage.setItem('selectedVideoId', relatedVideo.id);
                window.location.href = `telaDeVideoIndividual.html`;
            });

            listaVideosSecundarios.appendChild(li);
        });

    } catch (error) {
        console.error('Error fetching video details:', error);
        alert('Erro ao carregar os detalhes do vídeo.');
    }
});
