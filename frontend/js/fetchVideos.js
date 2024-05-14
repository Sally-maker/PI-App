

const jsonObject = {};
formData.forEach((value, key) => {
    jsonObject[key] = value;
});

const jsonData = JSON.stringify(jsonObject)

const api = fetch("http://localhost:8080/video/lista/0", {
    method: "POST",
    body: jsonData,
    headers: {
        "Content-Type": "application/json"
    }
}).then(
    console.log(jsonData)
).catch( (error) => {
    console.log(error)
})