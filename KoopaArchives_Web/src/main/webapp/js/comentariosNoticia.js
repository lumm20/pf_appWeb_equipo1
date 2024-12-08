'use strict';

class Comentario {
    constructor() {
        this.contenedor = document.getElementsByClassName('comments-section')[0];
        if (sesion !== null && sesion.rol === 'Normal') {
            this.mostrarTextArea();
        }
    }

    mostrarComentariosNormales(comentarios) {
        comentarios.forEach(comentario => {
            this.mostrarComentario(comentario);
        });
    }
    mostrarComentariosDestacados(comentarios) {
        comentarios.forEach(comentario => {
            this.mostrarComentario(comentario);
        });
    }


    async mostrarComentarios() {
        this.borrarTodo();
        let destacos = await cargarDestacados();

        this.mostrarComentariosDestacados(destacos);
        let normales = await cargarNormales();
        console.log(destacos)

        this.mostrarComentariosNormales(normales);
    }

    borrarTodo() {
        const commentsToRemove = this.contenedor.querySelectorAll('.comment');
        commentsToRemove.forEach(comment => {
            this.contenedor.removeChild(comment);
        });
    }

    mostrarTextArea() {
        const commentsHeader = document.createElement('div');
        commentsHeader.className = 'comment-form';

        const textArea = document.createElement('textarea');
        textArea.placeholder = 'Escribe algo...';
        textArea.id = 'txtComentario';

        const btnComentar = document.createElement('button');
        btnComentar.className = 'load-more';
        btnComentar.id = 'enviar-comentario';
        btnComentar.textContent = 'Enviar comentario';
        btnComentar.addEventListener('click', () => this.enviarComentario());

        this.contenedor.appendChild(commentsHeader);
        commentsHeader.appendChild(textArea);
        commentsHeader.appendChild(btnComentar);
    }

    mostrarComentario(comentario) {
        const articleComentario = document.createElement('article');
        if (comentario.anclado) {
            articleComentario.className = 'comment comment-featured';
        } else {
            articleComentario.className = 'comment';
        }

        const avatar = document.createElement('div');
        avatar.className = 'comment-avatar';
        // const inputOculto = document.createElement('input');
        // inputOculto.type = 'hidden';
        // inputOculto.value = comentario.idComentario;
        // console.log(inputOculto);

        let img = comentario.perfilUsuario;
        const imgAvtar = document.createElement('img');
        imgAvtar.className = 'comment-img';
        imgAvtar.src = `data:${img.tipoImagen};base64,${img.url}`;
        imgAvtar.alt = img.nombreArchivo;

        const contenidoComentario = document.createElement('div');
        contenidoComentario.className = 'comment-content';

        const autorComentario = document.createElement('h3');
        autorComentario.className = 'comment-author';

        const fechaComentario = document.createElement('div');
        fechaComentario.className = 'comment-date';

        if (typeof comentario.fechaModificacion === 'undefined') {
            fechaComentario.textContent = comentario.fechaPublicacion;

        } else {
            fechaComentario.textContent = `${comentario.fechaModificacion} [Modificado]`;
        }

        const textoComentario = document.createElement('p');
        textoComentario.textContent = comentario.contenido;

        if (comentario.anclado) {
            autorComentario.innerHTML = `${comentario.autor} <span class="featured-label">Destacado</span>`;
        } else {
            autorComentario.textContent = comentario.autor;
        }

        contenidoComentario.appendChild(autorComentario);
        contenidoComentario.appendChild(fechaComentario);
        contenidoComentario.appendChild(textoComentario);
        
        avatar.appendChild(imgAvtar);
        // articleComentario.appendChild(inputOculto);
        articleComentario.appendChild(avatar);
        articleComentario.appendChild(contenidoComentario);
        if(sesion.rol === 'Admin' || comentario.autor === sesion.autor)
        this.crearOptionsMenu(articleComentario, comentario);

        this.contenedor.appendChild(articleComentario);
    }




    crearOptionsMenu(articleComentario, comentario) {
        const optionsMenu = document.createElement('div');
        optionsMenu.className = 'options-menu';

        const optionsMenuDots = document.createElement('div');
        optionsMenuDots.className = 'options-menu-dots';

        const optionsMenuContent = document.createElement('div');
        optionsMenuContent.className = 'options-menu-content';
        const dot = document.createElement('div');
        dot.className = 'options-menu-dot';

        optionsMenuDots.appendChild(dot);
        optionsMenuDots.appendChild(dot);
        optionsMenuDots.appendChild(dot);

        this.crearEventsOptionMenu(comentario,optionsMenuContent);
        optionsMenu.appendChild(optionsMenuDots);
        optionsMenu.appendChild(optionsMenuContent);
        articleComentario.appendChild(optionsMenu);
    }

    crearEventsOptionMenu(comentario,optionsMenuContent){
        if(sesion === null) return;

        if(sesion.rol === 'Admin'){
            if(comentario.anclado){
                this.crearItemOpcion('Quitar destacado', () => this.desanclarComentario(comentario.idComentario), optionsMenuContent);
            }else{
                this.crearItemOpcion('Destacar comentario', () => this.destacarComentario(comentario.idComentario), optionsMenuContent);
            }
            this.crearItemOpcion('Eliminar', () => this.eliminarComentario(comentario.idComentario), optionsMenuContent);
        } else {
            
            if(comentario.autor === sesion.autor){
                this.crearItemOpcion('Eliminar', () => this.eliminarComentario(comentario.idComentario), optionsMenuContent);
            }

        }

    }

    crearItemOpcion(texto, handler, contenedor) {
        const item = document.createElement('div');
        item.className = 'options-menu-item';
        item.textContent = texto;
        item.addEventListener('click', handler);
        contenedor.appendChild(item);
    }


    async desanclarComentario(id){
        // Implementar lógica para eliminar noticia
        const url = `/Comentario?action=desanclar`;
        let json = {
            idComentario: id,
        };
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(json)
        });
        if (response.ok) {
            this.mostrarComentarios();
        }
    }
    async destacarComentario(id) {
        // Implementar lógica para eliminar noticia
        const url = `/Comentario?action=destacar`;
        let json = {
            idComentario: id,
        };
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(json)
        });
        if (response.ok) {
            this.mostrarComentarios();
        }
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


    async eliminarComentario(id) {
        // Implementar lógica para eliminar noticia
        const url = `/Comentario`;
        let json = {
            idComentario: id,
        };
        const response = await fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(json)
        });
        if (response.ok) {
            this.mostrarComentarios();
        }
    }

    async enviarComentario() {
        const textArea = document.getElementById('txtComentario');
        if (this.validarTxt(textArea.value)) {
            this.registrarComentario(textArea.value);
            textArea.value = "";
        } else {
            console.log('texto invalido');
            textArea.textContent = '';
        }

    }

    async registrarComentario(texto) {
        const url = `/Comentario`;
        let json = {
            codigoNoticia: newsId,
            contenido: texto,
            autor: sesion.autor
        };
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(json)
        });
        if (response.ok) {
            this.mostrarComentarios();
        }
    }

    async 


    validarTxt(texto) {
        return texto.trim().length > 0;
    }
}


async function cargarDestacados() {
    const url = `/Comentario?action=destacados&codigo=${newsId}`;
    return await fetch(url).then(response => response.json());
}
async function cargarNormales() {
    const url = `/Comentario?action=normales&codigo=${newsId}`;
    return await fetch(url).then(response => response.json());
}




function cargarUsuario(idUsuario) {
    fetch(`/Comentario?action=consulta&codigo=${idUsuario}`)
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


