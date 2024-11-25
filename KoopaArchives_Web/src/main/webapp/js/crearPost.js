function agregarSubtema() {
    const subtemas = document.getElementById('subtemas');
    const nuevoSubtema = document.createElement('div');
    nuevoSubtema.className = 'subtema';
    const subtemaId = Date.now(); // Genera un ID único para el subtema

    nuevoSubtema.innerHTML = `
    <div class="subtema-header">
        <h3>Nuevo subtema</h3>
        <button type="button" class="remove-subtema" onclick="this.parentElement.parentElement.remove()">
            Eliminar
        </button>
    </div>
    <div class="form-group">
        <label for="subtitulo_${subtemaId}">Subtítulo:</label>
        <input type="text" id="subtitulo_${subtemaId}" name="subtitulos[]" required>
    </div>
    <div class="form-group">
        <label for="contenidoSubtema_${subtemaId}">Contenido del subtema:</label>
        <textarea id="contenidoSubtema_${subtemaId}" name="contenidosSubtema[]" required></textarea>
    </div>
    <div class="form-group">
        <label for="imagenSubtema_${subtemaId}">Imagen del subtema</label>
        <div class="file-input-container">
            <div class="file-input-button">Seleccionar imagen</div>
            <input type="file" id="imagenSubtema_${subtemaId}" name="imagenesSubtema[]" accept="image/*" onchange="previewImage(this, 'imagenSubtemaPreview_${subtemaId}')">
        </div>
        <div id="imagenSubtemaPreview_${subtemaId}" class="image-preview"></div>
    </div>
    `;

    subtemas.appendChild(nuevoSubtema);
}

function previewImage(input, previewId) {
    const preview = document.getElementById(previewId);
    preview.innerHTML = '';

    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            img.alt = 'Vista previa de la imagen';
            preview.appendChild(img);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

// Manejo del formulario
document.getElementById('createPostForm').addEventListener('submit', function (e) {
    e.preventDefault();
    // Aquí iría la lógica para enviar el formulario
    console.log('Formulario enviado');
}); 
