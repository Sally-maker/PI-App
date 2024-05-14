const jsonObject = {};
formData.forEach((value, key) => {
    jsonObject[key] = value;
});

const jsonData = JSON.stringify(jsonObject)

const api = fetch("http://localhost:8080/video/lista/0", {
    method: "GET",
    body: jsonData,
    headers: {
        "Content-Type": "application/json"
    }
})

const ListContainerVideo = document.getElementById("videoContainer")

const data = await api.json();

data.content.forEach(video => {
    const videoElement = document.createElement("div");
    videoElement.innerHTML(`
      <video controls 
        <source src="${video.url}" type="video/mp4"
      </video>
    `)
    ListContainerVideo.appendChild(videoElement)
})
