'use strict';
console.log("fashfid");
function openModal() {
    let formsCompleto = validarCampos();
    if(formsCompleto){
        
        document.getElementById('modalOverlay').style.display = 'flex';
    }
}

function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
}

function confirmAction() {
    // Aquí puedes agregar la lógica para borrar la noticia
    alert('Noticia borrada');
    closeModal();
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
        };
        
        reader.readAsDataURL(input.files[0]);
    }
}

function validarCampos(){
    const txtTitulo = document.getElementById("titulo");
    const imgPrincipal = document.getElementById("imagenPrincipal");
    const lblCategoria = document.getElementById("categoria");
    const txtContenido = document.getElementById("contenido");

    
    if(imgPrincipal.value ==='' 
        || txtTitulo.value === ''
        || lblCategoria
        || txtContenido.value === ''){
        console.log("complete el forms");
        return false;
    }
    console.log("si se termino el forms");
    return true;
}