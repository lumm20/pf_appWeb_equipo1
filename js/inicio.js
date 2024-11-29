'use strict';
let contador = 0;


const contenedorDestacadas = document.getElementById('add-news-button').addEventListener('click', function() {
    contador++;
    const newsContainer = document.getElementsByClassName('news-grid')[0];
    crearNoticiaDestacada(newsContainer);
});

function crearNoticiaDestacada(contenedor) {
    const article = document.createElement('article');
    article.className = 'news-item';

    const img = document.createElement('img');
    img.src = '../img/noti_ejemplo.png';
    img.alt = 'Portada Juego The Legend Of Zelda: Echo of Wisdom';

    const newsContent = document.createElement('div');
    newsContent.className = 'news-content';

    const category = document.createElement('span');
    category.className = 'news-category';
    category.textContent = 'Tecnología';

    const title = document.createElement('h2');
    title.className = 'news-title';
    title.textContent = `Noticia de nintendo #${contador}`;

    const description = document.createElement('p');
    description.className = 'news-description';
    description.textContent = 'Echoes of Wisdom aprovecha al máximo las capacidades de la consola de Nintendo';

    // Crear input oculto
    const hiddenInput = document.createElement('input');
    hiddenInput.type = 'hidden';
    hiddenInput.value = `Noticia de nintendo #${contador}`;

    // Agregar evento de clic al artículo
    article.addEventListener('click', () => {
        console.log(hiddenInput.value)
    });

    newsContent.appendChild(category);
    newsContent.appendChild(title);
    newsContent.appendChild(description);

    article.appendChild(img);
    article.appendChild(newsContent);
    article.appendChild(hiddenInput); // Agregar input oculto al artículo

    contenedor.appendChild(article);
}