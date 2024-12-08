'use strict';
const urlParams = new URLSearchParams(window.location.search);
const newsId = urlParams.get('id');
const contenedorNoticia = document.getElementsByClassName('article-header')[0];
let sesion = null;
let n = null;
let sectionComentario = null;

class Noticia {
    constructor(noticia) {
        this.noticia = noticia;
        this.contenedor = document.getElementsByClassName('article-header')[0];
        this.mostrarNoticia();
        if (sesion !== null && sesion.rol === 'Admin')
            this.crearOptionsMenu();
    }

    mostrarNoticia() {
        const titulo = document.getElementsByClassName('article-title')[0];
        const fecha = document.getElementById('article-fecha');
        const categoria = document.getElementsByClassName('category')[0];
        const imgNoticia = document.getElementsByClassName('article-image')[0];
        const autor = document.getElementsByClassName('article-image-caption')[0];
        let imagen = this.noticia.imagen;

        imgNoticia.src = `data:${imagen.tipoImagen};base64,${imagen.url}`;
        imgNoticia.alt = imagen.nombreArchivo;
        
        
        categoria.textContent = `Categoria: ${this.noticia.categoria}`;
        titulo.textContent = this.noticia.titulo;
        this.crearDescripcion(this.noticia.texto);
        autor.textContent = this.noticia.autor.username;
        fecha.textContent = `Fecha de publicación: ${this.formatDateTime(this.noticia.fechaCreacion)}`;
    }


    crearDescripcion(texto) {
        const descripcion = document.getElementsByClassName('article-content')[0];
        const lineas = texto.split('\n');
        lineas.forEach((element) => {
            const newsContent = document.createElement('p');
            newsContent.className = 'article-text';
            newsContent.textContent = element;
            descripcion.appendChild(newsContent);
        });

    }

    formatDateTime(dateString) {
        const date = new Date(dateString);
        return date.toLocaleString('es-ES', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    }

    crearOptionsMenu() {
        const optionsMenu = document.createElement('div');
        optionsMenu.className = 'options-menu';

        const optionsMenuDots = document.createElement('div');
        optionsMenuDots.className = 'options-menu-dots';

        const optionsMenuContent = document.createElement('div');
        optionsMenuContent.className = 'options-menu-content';

        if (this.noticia.destacada) {
            this.crearItemOpcion('Quitar destacado', () => this.quitarDestacado(), optionsMenuContent, optionsMenuDots);
        } else {
            this.crearItemOpcion('Destacar noticia', () => this.destacarNoticia(), optionsMenuContent, optionsMenuDots);
        }
        this.crearItemOpcion('Eliminar', () => this.eliminarNoticia(), optionsMenuContent, optionsMenuDots);

        optionsMenu.appendChild(optionsMenuDots);
        optionsMenu.appendChild(optionsMenuContent);
        this.contenedor.appendChild(optionsMenu);
    }

    crearItemOpcion(texto, handler, contenedor, dots) {
        const dot = document.createElement('div');
        dot.className = 'options-menu-dot';
        dots.appendChild(dot);

        const item = document.createElement('div');
        item.className = 'options-menu-item';
        item.textContent = texto;
        item.addEventListener('click', handler);
        contenedor.appendChild(item);
    }

    async destacarNoticia() {
        // Implementar lógica para eliminar noticia
        const url = `/Noticia?action=destacar`;
        let json = {
            codigo: newsId,
        };
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(json)
        });
        if (response.ok) {
            console.log("Noticia anclada")
        }
    }
    async quitarDestacado() {
        // Implementar lógica para eliminar noticia
        const url = `/Noticia?action=desanclar`;
        let json = {
            codigo: newsId,
        };
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(json)
        });
        if (response.ok) {
            console.log("Noticia desanclada");
        }
    }



    async eliminarNoticia() {
        // Implementar lógica para eliminar noticia
        console.log('eliminar con una modal perro');
        // await borrarNoticia();
        document.getElementById('modalOverlay').style.display = 'flex';
    }

}


async function cargarDatosSesion() {
    const r = await fetch(`/ProcesarNoticia?action=datosSesion`);

    if (r.ok) {
        sesion = await r.json();
    }
}
async function cargarNoticia() {
    await fetch(`/ProcesarNoticia?action=mostrarNoticia&id=${newsId}`)
        .then(response => {

            return response.json();
        })
        .then(data => {
            n = data;
            new Noticia(data);
            sectionComentario = new Comentario();
            sectionComentario.mostrarComentarios();
        })
        .catch(error => {
            console.error('Error fetching news details:', error);
            // Handle error (show error message to user)
        });
}
async function borrarNoticia() {
    const url = `/Noticia`;
    let json = {
        codigo: newsId
    };
    console.log("hola");
    const response = await fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
    });
    return response;
}


// Populate the page with news details
async function cargarElementos() {
    await cargarDatosSesion();
    await cargarNoticia();
}

cargarElementos();

function openModal() {
    let formsCompleto = validarCampos();
    if (formsCompleto) {

        document.getElementById('modalOverlay').style.display = 'flex';
    }
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
}

async function confirmAction() {
    // Aquí puedes agregar la lógica para borrar la noticia
    let response = await borrarNoticia();
    console.log(response);
    console.log("dirigete");
    closeModal();
    if (response.ok) {
        window.location.href = 'Noticia?action=cargarInicio';
    }
}

document.getElementById('modalOverlay').addEventListener('click', function (e) {
    if (e.target === this) {
        closeModal();
    }
});

// Modificar el evento de eliminación en el menú de opciones
document.querySelectorAll('.options-menu-item').forEach(item => {
    if (item.textContent === 'Eliminar') {
        item.addEventListener('click', openModal);
    }
});

