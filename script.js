
let slideIndex = 0;

  function moveSlide(n) {
    const slides = document.querySelectorAll('.carrossel li');
       slideIndex += n;
    if (slideIndex < 0) slideIndex = slides.length - 1;
    if (slideIndex >= slides.length) slideIndex = 0;
    const offset = -slideIndex * 100 + '%';
    document.querySelector('.carrossel ul').style.transform = `translateX(${offset})`;

  }

  function searchFunction() {
    var input, filter;
    input = document.getElementById('searchBar');
    filter = input.value.toUpperCase();
    // Insira a lógica de pesquisa aqui
    console.log("Pesquisa realizada com: " + filter);
}