'use strict';
const newsId = urlParams.get('id');
let usuario;
let sesion;
const contenedorNoticia = document.getElementsByClassName('article-header')[0];
const contenedorComentarios = document.getElementsByClassName('article-header')[0];


async function cargarDatosSesion() {
    await fetch(`/ProcesarNoticia?action=datosSesion`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            sesion = data;

            // Load comments
            // loadComments(id);
        })
        .catch(error => {
            console.error('Error fetching news details:', error);
            // Handle error (show error message to user)
        });
}
async function fetchNewsDetails(id) {
    await fetch(`/ProcesarNoticia?action=mostrarNoticia&id=${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);

            // Load comments
            // loadComments(id);
        })
        .catch(error => {
            console.error('Error fetching news details:', error);
            // Handle error (show error message to user)
        });
}


function loadComments(newsId) {
    fetch(`/api/comentarios?noticiaId=${newsId}`)
        .then(response => response.json())
        .then(comments => {
            const commentsContainer = document.querySelector('.comments-list');

            comments.forEach(comment => {
                const commentElement = createCommentElement(comment);
                commentsContainer.appendChild(commentElement);
            });
        })
        .catch(error => {
            console.error('Error loading comments:', error);
        });
}



// Populate the page with news details
//                document.getElementById('news-title').textContent = data.titulo;
//                document.getElementById('publication-date').textContent = formatDate(data.fechaCreacion);
//                document.getElementById('news-category').textContent = data.categoria;
//                
//                // Set image
//                const newsImage = document.getElementById('news-image');
//                newsImage.src = `data:${data.tipoArchivo};base64,${data.url}`;
//                newsImage.alt = data.nombreArchivo;
//                
//                document.getElementById('image-author').textContent = `Autor: ${data.autor.username}`;
//
//                // Populate article content
//                const contentContainer = document.getElementById('article-content');
//                data.parrafos.forEach(parrafo => {
//                    const p = document.createElement('p');
//                    p.classList.add('article-text');
//                    p.textContent = parrafo;
//                    contentContainer.appendChild(p);
function cargarElementos() {
    contenedorNoticia = document.getElementsByClassName()[0];
    // Get the news ID from the URL
const urlParams = new URLSearchParams(window.location.search);
cargarDatosSesion();
cargarElementos();
if (newsId) {
    fetchNewsDetails(newsId);
} else {
    console.error('No news ID provided');
}
}

function mostrarNoticia(contenedor) {
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
