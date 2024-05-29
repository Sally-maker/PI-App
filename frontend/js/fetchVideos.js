document.addEventListener("DOMContentLoaded", async function () {
  try {
    const response = await fetch(`http://localhost:8080/video/lista/0?limit=100`);
    const data = await response.json();

    const catalogosDaDireita = document.getElementById("catalogosDaDireita");
    const catalogosDaEsquerda = document.getElementById("catalogosDaEsquerda");
    const videoContainer = document.getElementById("videoContainer");

    const shuffledVideos = shuffleArray(data.content).slice(0, 8);

    shuffledVideos.forEach((video, index) => {
      const videoDiv = document.createElement("div");
      videoDiv.classList.add(index % 2 === 0 ? "catalogosDireita" : "catalogosEsquerda");

      const videoElement = document.createElement("div");
      videoElement.classList.add("video-container");
      videoElement.innerHTML = `
        <iframe src="${video.url}" title="${video.titulo}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
      `;

      const overlayDiv = document.createElement("div");
      overlayDiv.classList.add("overlay-div");

      // Adiciona um evento de clique para a camada div de sobreposição
      overlayDiv.addEventListener('click', function(event) {
        event.preventDefault(); // Impede o comportamento padrão do clique
        localStorage.setItem('selectedVideoId', video.id); // Armazena o ID do vídeo no localStorage
        window.location.href = './telaDeVideoIndividual.html'; // Redireciona para a página de vídeo individual
      });

      videoDiv.appendChild(videoElement);
      videoDiv.appendChild(overlayDiv);

      if (index % 2 === 0) {
        catalogosDaDireita.appendChild(videoDiv);
      } else {
        catalogosDaEsquerda.appendChild(videoDiv);
      }
    });

    const centerVideo = shuffledVideos[0];
    const centerVideoElement = document.createElement("div");
    centerVideoElement.classList.add("video-container");
    centerVideoElement.innerHTML = `
          <iframe src="${centerVideo.url}" title="${centerVideo.titulo}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
      `;

    videoContainer.appendChild(centerVideoElement);
  } catch (error) {
    console.error("Error fetching videos:", error);
    alert("Erro ao carregar os vídeos.");
  }
});

function shuffleArray(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}
